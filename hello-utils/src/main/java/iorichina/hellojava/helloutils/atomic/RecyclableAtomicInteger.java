package iorichina.hellojava.helloutils.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自动在0-threshold之间进行循环
 */
public class RecyclableAtomicInteger extends AtomicInteger {
    int threshold;
    int maxTry;

    /**
     * @param threshold 0 ~ threshold (include)
     * @param maxTry try without lock
     */
    public RecyclableAtomicInteger(int threshold, int maxTry) {
        super(0);
        this.threshold = threshold + 1;
        this.maxTry = maxTry;
    }

    public final int getAndIncrementWithRecycle() {
        for (int times = 0; ; times++) {
            int current = getAndIncrement();
            if (current < threshold) {
                return current;
            }
            compareAndSet(current, 0);
            if (times < maxTry) {
                continue;
            }
            synchronized (this) {
                if (get() >= threshold) {
                    set(0);
                }
            }
        }
    }

}
