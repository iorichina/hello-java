package iorichina.hellojava.hellospringboot.test;

import java.util.stream.Stream;

/**
 * Created by iorichina on 2017/4/28.
 */
public class StreamTest {
    public static void main(String[] args) {
        Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);
    }
}
