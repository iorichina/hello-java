package iorichina.hellojava.hellospringboot.controller;

import iorichina.hellojava.hellospringboot.cache.cacheable.LocalCacheable;
import iorichina.hellojava.hellospringboot.service.RetryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/retry")
public class RetryController {
    private static final Logger logger = LoggerFactory.getLogger(RetryController.class);
    private final RetryService retryService;

    @Autowired
    private LocalCacheable localCacheable;

    public RetryController(RetryService retryService) {
        this.retryService = retryService;
    }

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String test(@RequestParam(required = false) String name,
                        HttpServletRequest request) throws Exception {
        System.err.println("尝试进入断路器方法，并触发异常");

        retryService.circuitBreaker(1);
        retryService.circuitBreaker(1);
        retryService.circuitBreaker(1);

        System.err.println("尝试进入断路器方法，在openTimeout时间内，触发异常超过3次，断路器打开，断路器方法不允许执行，直接执行恢复方法");
        retryService.circuitBreaker(2);
        TimeUnit.SECONDS.sleep(2);
        System.err.println("超过断路器半开时间resetTimeout，断路器半开，断路器方法运行允许3个访问进入");
        retryService.circuitBreaker(3);
        retryService.circuitBreaker(3);
        retryService.circuitBreaker(3);
        System.err.println("尝试进入断路器方法，在openTimeout时间内，触发异常超过3次，断路器打开，断路器方法不允许执行，直接执行恢复方法");
        retryService.circuitBreaker(4);
        TimeUnit.SECONDS.sleep(3);
        System.err.println("超过断路器再次超过半开时间openTimeout+resetTimeout，断路器半开，断路器方法运行允许三个访问进入");
        retryService.circuitBreaker(5);
        retryService.circuitBreaker(5);
        retryService.circuitBreaker(5);
        System.err.println("尝试进入断路器方法，在openTimeout时间内，触发异常超过3次，断路器打开，断路器方法不允许执行，直接执行恢复方法");
        retryService.circuitBreaker(6);
        TimeUnit.SECONDS.sleep(3);
        System.err.println("超过断路器再次超过半开时间openTimeout+resetTimeout，断路器半开，断路器方法运行允许三个访问进入");
        retryService.circuitBreaker(7);
        retryService.circuitBreaker(7);
        retryService.circuitBreaker(7);
        System.err.println("尝试进入断路器方法，在openTimeout时间内，触发异常超过3次，断路器打开，断路器方法不允许执行，直接执行恢复方法");
        retryService.circuitBreaker(8);
        TimeUnit.SECONDS.sleep(3);
        System.err.println("超过断路器再次超过半开时间openTimeout+resetTimeout，断路器半开，断路器方法运行允许三个访问进入,并且断路方法不再抛出异常,断路器关闭，方法可持续调用");
        retryService.circuitBreaker(9);
        retryService.circuitBreaker(9);
        retryService.circuitBreaker(9);
        retryService.circuitBreaker(9);
        retryService.circuitBreaker(9);
        retryService.circuitBreaker(9);

        System.err.println();
        System.err.println();
        System.err.println("开始重试");
        retryService.retryable(10);
        System.err.println("未抛出异常，");
        retryService.retryable(11);

        return null;
    }

}
