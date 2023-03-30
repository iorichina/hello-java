package iorichina.hellojava.hellospringboot.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import iorichina.hellojava.hellospringboot.annotation.ConditionalOnNoProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by iorichina on 2017/7/21 0021.
 * <p>
 * To disable cache, just set property [spring.cache.type=none]
 */
@EnableCaching
@Configuration
@ConditionalOnNoProperty(name = "spring.cache.type", value = "none")
public class SpringCacheable extends CachingConfigurerSupport {
    public static final String SPRING_CACHE_MANAGER = "springCacheableManager";
    public static final String LOCAL_CACHE_MANAGER = "localCacheableManager";
    private static final Logger logger = LoggerFactory.getLogger(SpringCacheable.class);

    // 千万不能在这里autowired LocalCacheable，否则缓存不会生效
//    @Autowired
//    private LocalCacheable localCacheable;

    /**
     * spring.cache.type=none will use noopcachemanager to implement "disable" cache
     * <p>
     * when using <code>@ConditionalOnNoProperty(name = "spring.cache.type", value = "none")</code>
     * this property is useless.
     * <p>
     * You could set another default cacheType when
     * <code>spring.cache.type</code> is set to another value
     *
     * @see org.springframework.boot.autoconfigure.cache.CacheType
     */
    @Value("${spring.cache.type:}")
    private String type = null;

    @Autowired
    private Environment env;

    @Value("${SpringCacheable.springCacheableManager.expireAfterWrite:10000}")
    private long expireAfterWrite = 10000;
    @Value("${SpringCacheable.springCacheableManager.maximumSize:10000}")
    private long maximumSize = 10000;
    @Value("${SpringCacheable.springCacheableManager.recordStats:}")
    private Boolean recordStats;

    protected Caffeine<Object, Object> createCacheBuilder(String name) {
        Boolean recordStats = env.getProperty("SpringCacheable." + name + ".recordStats",
                Boolean.class);
        Long expireAfterWrite = env.getProperty("SpringCacheable." + name + ".expireAfterWrite",
                Long.class);
        Long maximumSize = env.getProperty("SpringCacheable." + name + ".maximumSize",
                Long.class);

        Caffeine caffeine = Caffeine.newBuilder();
        logger.info("[{}][createCacheBuilder][builder]{},{},{}",
                new Exception().getStackTrace()[1].getMethodName(),
                recordStats, expireAfterWrite, maximumSize);

        if (expireAfterWrite == null && maximumSize == null && recordStats == null) {
            return caffeine;
        }

        if (null != recordStats) {
            caffeine.recordStats();
        }
        if (null != expireAfterWrite) {
            caffeine.expireAfterWrite(expireAfterWrite, TimeUnit.MILLISECONDS);
        }
        if (null != maximumSize) {
            caffeine.maximumSize(maximumSize);
        }

        return caffeine;
    }

    protected CacheManager createNoOpCacheManager() {
        return new NoOpCacheManager();
    }
/*

    @Bean
    public CacheManager springCacheableManager() {
        if (type != null && CacheType.NONE.name().equalsIgnoreCase(type)) {
            return createNoOpCacheManager();
        }

        String name = SPRING_CACHE_MANAGER;//new Exception().getStackTrace()[0].getMethodName();
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCacheNames(Arrays.asList(name));
        Caffeine builder = Caffeine.newBuilder();
        logger.info("[{}][builder]{},{},{}", SPRING_CACHE_MANAGER, recordStats, expireAfterWrite, maximumSize);

        if (null != recordStats && recordStats.equals(true)) {
            builder.recordStats();
        }
        builder.expireAfterWrite(expireAfterWrite, TimeUnit.MILLISECONDS);
        builder.maximumSize(maximumSize);

        cacheManager.setCaffeine(builder);
        return cacheManager;
    }

    @Bean
    public CacheManager localCacheableManager() {
        if (type != null && CacheType.NONE.name().equalsIgnoreCase(type)) {
            return createNoOpCacheManager();
        }

        String name = LOCAL_CACHE_MANAGER;//new Exception().getStackTrace()[0].getMethodName();
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

        cacheManager.setCacheNames(Arrays.asList(name));
        Caffeine<Object, Object> caffeine = createCacheBuilder(name);
        if (caffeine != null) {
            cacheManager.setCaffeine(caffeine);
        }

        return cacheManager;
    }
*/

    @Override
    @Bean
    @Primary
    public CacheManager cacheManager() {
        if (type != null && CacheType.NONE.name().equalsIgnoreCase(type)) {
            return createNoOpCacheManager();
        }

        SimpleCacheManager cacheManager = new SimpleCacheManager();

        ArrayList<CaffeineCache> caches = new ArrayList<>();
        cacheManager.setCaches(caches);
        for (CacheName c : CacheName.values()) {
            caches.add(new CaffeineCache(c.name(), createCacheBuilder(c.name()).build()));
        }

        return cacheManager;
    }

    public enum CacheName {
        localCacheable;

        CacheName() {
        }
    }

}
