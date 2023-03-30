package iorichina.hellojava.hellonetwork.monitor;

import iorichina.hellojava.hellonetwork.exception.NetException;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ReachableMonitor {
    public static boolean ping(String ip, int timeoutMillis) throws NetException {
        if (timeoutMillis < 1) {
            throw new NetException("超时不应小于1");
        }
        InetAddress addr = null;
        try {
            addr = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            throw new NetException("不规范的ip：" + ip, e);
        }
        try {
            return addr.isReachable(timeoutMillis);
        } catch (IOException e) {
            throw new NetException("网络异常", e);
        }
    }
}
