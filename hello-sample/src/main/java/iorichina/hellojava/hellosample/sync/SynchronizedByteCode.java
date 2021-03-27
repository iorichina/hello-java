package iorichina.hellojava.hellosample.sync;

import java.util.ArrayList;
import java.util.List;

public class SynchronizedByteCode {
    static List<String> list = new ArrayList<>();

    public void syncAdd() {
        for (int i = 0; i < 100000; i++) {
            synchronized (list) {
                list.add("" + i);
            }
        }
    }
    public void add() {
        for (int i = 0; i < 100000; i++) {
            list.add("" + i);
        }
    }
}
