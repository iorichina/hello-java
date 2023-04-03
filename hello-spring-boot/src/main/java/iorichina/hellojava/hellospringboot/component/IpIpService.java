package iorichina.hellojava.hellospringboot.component;

import iorichina.hellojava.helloutils.geo.IPExt;
import iorichina.hellojava.hellospringboot.constant.RetEnum;
import iorichina.hellojava.hellospringboot.dto.IpIpFindDTO;
import iorichina.hellojava.hellospringboot.exception.IpIpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by iorichina on 2017/1/7.
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
