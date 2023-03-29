package iorichina.hellojava.hellonetwork;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) {
        try {
            String ip = "39.156.66.10";
            System.out.println("ip:" + ip);
            InetAddress addr = InetAddress.getByName(ip);
            System.out.println("ip version:" + addr.getClass().getName());
            System.out.println("ip ping:" + addr.isReachable(5000));

            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces != null) {
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface networkInterface = networkInterfaces.nextElement();
                    if (!networkInterface.isUp()) {
                        continue;
                    }
                    println("设备在操作系统中显示的名称", "：", networkInterface.getDisplayName());
                    println("网络设备在操作系统中的名称", "：", networkInterface.getName());
                    println("网络接口是否已经称启并正常工作", "：", networkInterface.isUp());
                    println("网络接口的最大传输单元（MTU）", "：", networkInterface.getMTU());
                    println("网络接口是是否是环回接口", "：", networkInterface.isLoopback());
                    Enumeration<InetAddress> inetAddressesList = networkInterface.getInetAddresses();
                    print("网络接口的硬件MAC地址", ":");
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress != null && hardwareAddress.length > 0) {
                        println(bytesToHexMac(hardwareAddress));
                    } else {
                        println(networkInterface.getHardwareAddress());
                    }
                    while (inetAddressesList.hasMoreElements()) {
                        InetAddress address = inetAddressesList.nextElement();
                        println("--主机地址：" + address.getHostAddress());
                    }

                    println("=============分隔=============");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void println(Object... strings) {
        print(strings);
        System.out.println();
    }

    private static void print(Object... strings) {
        for (int i = 0; i < strings.length; i++) {
            System.out.print(strings[i]);
        }
    }

    public static String bytesToHexMac(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            buf.append(String.format("%02x", bytes[i] & 0xff));
            if (i != bytes.length - 1) {
                buf.append(":");
            }
        }
        return buf.toString();
    }
}