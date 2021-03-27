package iorichina.hello.spring.boot.hook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class ShutdownHook implements ApplicationListener<ContextClosedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShutdownHook.class);

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println(ShutdownHook.class.getSimpleName());
        LOGGER.warn("shutting down, please wait for ContextClosedEvent...");
        try {
            Thread.sleep(4900L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.warn("ok, i'm done. bye mr.ContextClosedEvent");
    }
}
