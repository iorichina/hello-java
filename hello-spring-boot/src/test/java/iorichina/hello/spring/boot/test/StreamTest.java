package iorichina.hello.spring.boot.test;

import java.util.stream.Stream;

/**
 * Created by iorihuang on 2017/4/28.
 */
public class StreamTest {
    public static void main(String[] args) {
        Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);
    }
}
