package iorichina.hellojava.hellospringboot.cache.cacheable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
//@CacheConfig(cacheManager = SpringCacheable.LOCAL_CACHE_MANAGER, cacheNames = {SpringCacheable.LOCAL_CACHE_MANAGER})
public class LocalCacheable {
    private static final Logger logger = LoggerFactory.getLogger(LocalCacheable.class);

    @Cacheable(cacheManager = "cacheManager", cacheNames = {"localCacheable"})
    public String getValue(String key) {
        logger.info("[getValue]{}", key);
        return key + ":Value";
    }
}
