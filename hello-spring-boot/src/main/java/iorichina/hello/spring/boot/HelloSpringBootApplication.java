package iorichina.hello.spring.boot;

import iorichina.hello.spring.boot.hook.ShutdownHook;
import iorichina.hello.spring.boot.hook.StartupHook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HelloSpringBootApplication implements EnvironmentAware {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(HelloSpringBootApplication.class);
        springApplication.addListeners(new ShutdownHook(), new StartupHook());
        springApplication.run(args);
    }

    @Override
    public void setEnvironment(Environment environment) {

    }
}
