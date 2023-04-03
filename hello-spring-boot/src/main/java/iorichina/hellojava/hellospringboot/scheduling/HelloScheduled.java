package iorichina.hellojava.hellospringboot.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by iorichina on 2017/5/9.
 */
@Component
public class HelloScheduled {
    private static final Logger logger = LoggerFactory.getLogger(HelloScheduled.class);

    @Scheduled(fixedRate = 13000)
    private void refreshGiftConfigCache() {
        logger.info("[refreshGiftConfigCache]{}", System.currentTimeMillis());
    }

}
