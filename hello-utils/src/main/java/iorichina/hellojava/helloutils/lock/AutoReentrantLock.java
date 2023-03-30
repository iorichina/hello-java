package iorichina.hellojava.helloutils.lock;

import java.io.Closeable;
import java.util.concurrent.locks.ReentrantLock;

public class AutoReentrantLock implements Closeable {
    ReentrantLock lock;

    private AutoReentrantLock() {
        lock = new ReentrantLock();
    }

    public AutoReentrantLock(ReentrantLock lock) {
        this.lock = lock;
    }

    public boolean tryLock() {
        return lock.tryLock();
    }

    public void lock() {
        lock.lock();
    }

    @Override
    public void close() {
        lock.unlock();
    }
}
