package iorichina.hellojava.helloutils.lock;

import java.io.Closeable;
import java.util.concurrent.locks.ReentrantLock;

public class CloseableReentrantLock extends ReentrantLock implements Closeable {
    public CloseableReentrantLock closeableLock() {
        super.lock();
        return this;
    }

    @Override
    public void close() {
        this.unlock();
    }
}
