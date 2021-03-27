package iorichina.hello.spring.boot.component;

import com.iorichina.utils.geo.IPExt;
import iorichina.hello.spring.boot.constant.RetEnum;
import iorichina.hello.spring.boot.dto.IpIpFindDTO;
import iorichina.hello.spring.boot.exception.IpIpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by iorihuang on 2017/1/7.
 */
@Component
@ConfigurationProperties(prefix = "ipip")
public class IpIpService implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(IpIpService.class);

    private boolean dataxEnabled;

    public boolean isDataxEnabled() {
        return dataxEnabled;
    }

    public IpIpService setDataxEnabled(boolean dataxEnabled) {
        this.dataxEnabled = dataxEnabled;
        return this;
    }

    public IpIpFindDTO findByIp(String ip) throws IpIpException {
        if (logger.isInfoEnabled()) {
            logger.info("[findByIp]ip={}", ip);
        }
        if (!IPExt.isInitialized()) {
            throw IpIpException.getException(RetEnum.SERVER_ERROR, "IPExt not initialized");
        }

        return IpIpFindDTO.valueOf(IPExt.find(ip));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (dataxEnabled) {
            IPExt.init();
        }
    }
}
