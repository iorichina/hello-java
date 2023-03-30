package iorichina.hellojava.hellospringboot.test;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import org.slf4j.LoggerFactory;

/**
 * Created by iorihuang on 2017/1/19.
 */
public class ArrayTest {
    private static MetricRegistry metricRegistry = new MetricRegistry();
    private static Slf4jReporter reporter;

    public static void main(String[] args) {
        metricRegistry.register("jvm.memory", new MemoryUsageGaugeSet());
        reporter = Slf4jReporter.forRegistry(metricRegistry)
                .outputTo(LoggerFactory.getLogger("metrics")).build();

        testEmptyArrayMemory();
    }

    /**
     * 测试：空数组的内存占用；
     * 证明：数组的每一个元素（即使为空）都占用一个引用（引用的大小依赖于JVM、操作系统以及处理器，但通常都是4个字节）；
     * 说明：定义数组时不要随便定太大，因为空数组也会占用内存，而且是连续的内存空间；
     * 栗子：很多文章指引定义HashMap时应该指定初始长度，但定义HashMap长度时也要按需考虑；
     */
    public static void testEmptyArrayMemory() {
        int[] intTmp = new int[1];
        MemoryUsageGaugeSet[] objTmp = new MemoryUsageGaugeSet[1];
        reporter.report();

//        create null array
        int[] intArr = new int[26214400];

//        should append 100M+ new space
        reporter.report();

//        create null array
        MemoryUsageGaugeSet[] objArr = new MemoryUsageGaugeSet[26214400];

//        should append 100M+ new space
        reporter.report();

    }
}
