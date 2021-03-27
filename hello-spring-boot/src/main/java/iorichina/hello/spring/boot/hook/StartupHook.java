package iorichina.hello.spring.boot.hook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class StartupHook implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StartupHook.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(StartupHook.class.getSimpleName());
        LOGGER.warn("initialing, please wait for ContextRefreshedEvent...");
        try {
            Thread.sleep(3900L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.warn("ok, i'm done. hi mr.ContextRefreshedEvent");
    }
}
