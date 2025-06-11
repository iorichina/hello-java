package iorichina.hellojava.hellospringboot.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class RetryService {

    @CircuitBreaker(maxAttempts = 3, openTimeout = 1000, resetTimeout = 2000, label = "test-CircuitBreaker", include = RuntimeException.class, exclude = Exception.class)
    public void circuitBreaker(int num) throws InterruptedException {
        System.err.print(" 进入断路器方法num=" + num);
        if (num > 8) return;
        Integer n = null;
        System.err.println(1 / n);
    }


    @Retryable(label = "test-Retryable", maxAttempts = 3, backoff = @Backoff(delay = 1), include = RuntimeException.class, exclude = Exception.class)
    public void retryable(int num) throws InterruptedException {
        System.err.println(" 进入重试方法num=" + num);
        if (num > 10) return;
        Integer n = null;
        System.err.println(1 / n);
    }

    @Recover
    public void recover(NullPointerException exception) {
        System.err.println(" NullPointerException ....");
    }

    @Recover
    public void recover(RuntimeException exception) {
        System.err.println(" RuntimeException ....");
    }

    @Recover
    public void recover(Exception exception) {
        System.err.println(" exception ....");
    }

    @Recover
    public void recover(Throwable throwable) {
        System.err.println(" throwable ....");
    }

    @Recover
    public void recover() {
        System.err.println(" recover ....");
    }
}
