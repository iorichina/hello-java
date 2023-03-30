package iorichina.hellojava.hellospringboot.controller;

import iorichina.hellojava.hellospringboot.cache.cacheable.LocalCacheable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/hello")
public class Hello {
    private static final Logger logger = LoggerFactory.getLogger(Hello.class);

    @Autowired
    private LocalCacheable localCacheable;

    @RequestMapping(path = "/world", method = RequestMethod.GET)
    public String world(@RequestParam(required = false) String name,
                        HttpServletRequest request) {
        logger.error("{}", request.getRequestURI());
        logger.warn("{}", request.getPathInfo());
        logger.info("hello, {}!", name);
        return new StringBuilder("hello, ").append(name).append("!").toString();
    }

    @GetMapping("/cache/{key}")
    public String cache(@PathVariable String key) {
        logger.info("[cache]{}", key);
        return localCacheable.getValue(key);
    }

}
