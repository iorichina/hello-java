package iorichina.hellojava.hellospringboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

/**
 * Created by iorihuang on 2017/5/16.
 */
@SpringBootApplication
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = BeanInitializeTest.class)
public class BeanInitializeTest implements InitializingBean, ApplicationRunner {
    private int i = 0;

    public static void main(String[] args) {
        SpringApplication.run(BeanInitializeTest.class, args);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean.afterPropertiesSet");
        System.out.println(i);
        i = 1;
        System.out.println(i);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner.run");
        System.out.println(args);
        System.out.println(i);
        i = 2;
        System.out.println(i);
    }

    @PostConstruct
    public void post() {
        System.out.println("@PostConstruct");
        System.out.println(i);
        i = 1;
        System.out.println(i);
    }

    @Test
    public void test() {
        System.out.println("test");
    }
}
