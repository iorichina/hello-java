package iorichina.hellojava.hellosample.test.time;

import com.google.common.base.Stopwatch;
import iorichina.hellojava.hellosample.time.DateParser;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDateParser {
    @Before
    public void init_run() throws Exception {
        System.out.println("init_run");
        for (int i = 0; i < 10000000; i++) {
            DateParser.isNowBetweenLocalDate("2017-09-13", "2019-09-13");
        }
        for (int i = 0; i < 10000000; i++) {
            DateParser.isNowBetween("2017-09-13", "2019-09-13");
        }
    }

    @Test
    public void test_isNowBetween() throws Exception {
        System.out.println("run isNowBetween first");
        Stopwatch st = Stopwatch.createUnstarted();

        st.start();
        for (int i = 0; i < 100000; i++) {
            DateParser.isNowBetween("2017-09-13", "2019-09-13");
        }
        st.stop();
        System.out.println("isNowBetween          elapsed:" + st.elapsed(TimeUnit.NANOSECONDS));

        st.reset();
        st.start();
        for (int i = 0; i < 100000; i++) {
            DateParser.isNowBetweenLocalDate("2017-09-13", "2019-09-13");
        }
        st.stop();
        System.out.println("isNowBetweenLocalDate elapsed:" + st.elapsed(TimeUnit.NANOSECONDS));

        System.out.println();
    }

    @Test
    public void test_isNowBetweenLocalDate() throws Exception {
        System.out.println("run isNowBetweenLocalDate first");
        Stopwatch st = Stopwatch.createUnstarted();

        st.start();
        for (int i = 0; i < 100000; i++) {
            DateParser.isNowBetweenLocalDate("2017-09-13", "2019-09-13");
        }
        st.stop();
        System.out.println("isNowBetweenLocalDate elapsed:" + st.elapsed(TimeUnit.NANOSECONDS));

        st.reset();
        st.start();
        for (int i = 0; i < 100000; i++) {
            DateParser.isNowBetween("2017-09-13", "2019-09-13");
        }
        st.stop();
        System.out.println("isNowBetween          elapsed:" + st.elapsed(TimeUnit.NANOSECONDS));

        System.out.println();
    }

    @Test
    public void test_isNowBetweenSimpleDate() throws Exception {
        System.out.println("run isNowBetweenSimpleDate first");
        Stopwatch st = Stopwatch.createUnstarted();

        st.start();
        for (int i = 0; i < 100000; i++) {
            DateParser.isNowBetweenLocalDate("2017-09-13", "2019-09-13");
        }
        st.stop();
        System.out.println("isNowBetweenLocalDate elapsed:" + st.elapsed(TimeUnit.NANOSECONDS));

        st.reset();
        st.start();
        for (int i = 0; i < 100000; i++) {
            DateParser.isNowBetween("2017-09-13", "2019-09-13");
        }
        st.stop();
        System.out.println("isNowBetween          elapsed:" + st.elapsed(TimeUnit.NANOSECONDS));

        st.start();
        for (int i = 0; i < 100000; i++) {
            DateParser.isNowBetweenSimpleDate("2017-09-13", "2019-09-13");
        }
        st.stop();
        System.out.println("isNowBetweenSimpleDate elapsed:" + st.elapsed(TimeUnit.NANOSECONDS));

        System.out.println();
    }
}
