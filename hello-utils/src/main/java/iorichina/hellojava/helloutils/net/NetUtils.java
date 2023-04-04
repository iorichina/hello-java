package iorichina.hellojava.helloutils.net;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

public class NetUtils {
    private static final Logger logger = LoggerFactory.getLogger(NetUtils.class);
    private final static byte INVALID_MACS[][] = {
            {0x00, 0x05, 0x69},             // VMWare
            {0x00, 0x1C, 0x14},             // VMWare
            {0x00, 0x0C, 0x29},             // VMWare
            {0x00, 0x50, 0x56},             // VMWare
            {0x08, 0x00, 0x27},             // Virtualbox
            {0x0A, 0x00, 0x27},             // Virtualbox
            {0x00, 0x03, (byte) 0xFF},       // Virtual-PC
            {0x00, 0x15, 0x5D}              // Hyper-V
    };

    public static boolean isVMMac(byte[] mac) {
        if (null == mac) {
            return false;
        }

        for (byte[] invalid : INVALID_MACS) {
            if (invalid[0] == mac[0] && invalid[1] == mac[1] && invalid[2] == mac[2]) {
                return true;
            }
        }

        return false;
    }

    public static List<String> getLocalHostAddresses() {
        List<NetworkInterface> nis;
        try {
            nis = Collections.list(NetworkInterface.getNetworkInterfaces());
        } catch (SocketException e) {
            logger.warn("[getLocalHostAddresses][SocketException]", e);
            return Collections.emptyList();
        }

        List<String> addresses = new ArrayList<>();

        for (NetworkInterface ni : nis) {
            try {
                if (!ni.isUp() || ni.isLoopback() || ni.isVirtual()) {
                    continue;
                }
                addresses.addAll(
                        Collections.list(ni.getInetAddresses())
                                .stream().map(InetAddress::getHostAddress)
                                .collect(Collectors.toList())
                );
            } catch (SocketException e) {
                logger.debug("[getLocalHostAddresses][NetworkInterface][SocketException]", e);
            }
        }

        return addresses;
    }

    public static String getLocalHostAddress() {
//        try {
//            return InetAddress.getLocalHost().getHostAddress();
//        } catch (UnknownHostException e) {
//            logger.warn("[getLocalHostAddress][UnknownHostException]", e);
//        }

        List<NetworkInterface> nis;
        try {
            nis = Collections.list(NetworkInterface.getNetworkInterfaces());
        } catch (SocketException e) {
            logger.warn("[getLocalHostAddress][SocketException]", e);
            return null;
        }

        List<InetAddress> addresses = new ArrayList<>();

        for (NetworkInterface ni : nis) {
            try {
                if (!ni.isUp() || ni.isLoopback() || ni.isVirtual()) {
                    continue;
                }
                if (isVMMac(ni.getHardwareAddress())) {
                    continue;
                }

                addresses.addAll(Collections.list(ni.getInetAddresses()));
            } catch (SocketException e) {
                logger.debug("[getLocalHostAddress][NetworkInterface][SocketException]", e);
            }
        }

        InetAddress local = findValidateIp(addresses);

        return null == local ? null : local.getHostAddress();
    }

    public static String getLocalHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            logger.warn("[getLocalHostName][UnknownHostException]", e);
        }

        List<NetworkInterface> nis;
        try {
            nis = Collections.list(NetworkInterface.getNetworkInterfaces());
        } catch (SocketException e) {
            logger.warn("[getLocalHostName][SocketException]", e);
            return null;
        }

        List<InetAddress> addresses = new ArrayList<>();

        for (NetworkInterface ni : nis) {
            try {
                if (ni.isUp()) {
                    addresses.addAll(Collections.list(ni.getInetAddresses()));
                }
            } catch (SocketException e) {
                logger.debug("[getLocalHostName][NetworkInterface][SocketException]", e);
            }
        }

        InetAddress local = findValidateIp(addresses);

        return null == local ? null : local.getHostName();
    }

    public static InetAddress findValidateIp(List<InetAddress> addresses) {
        InetAddress local = null;
        for (InetAddress address : addresses) {
            logger.debug("address:{}", address.getHostAddress());
            logger.debug("address-isLoopbackAddress:{}", address.isLoopbackAddress());
            logger.debug("address-isSiteLocalAddress:{}", address.isSiteLocalAddress());
            logger.debug("address-getHostName:{}", address.getHostName());
            if (address instanceof Inet4Address) {
                if (address.isLoopbackAddress() || address.isSiteLocalAddress()) {
                    if (local == null) {
                        local = address;
                    } else if (address.isSiteLocalAddress() && !address.isLoopbackAddress()) {
                        // site local address has higher priority than other address
                        local = address;
                    } else if (local.isSiteLocalAddress() && address.isSiteLocalAddress()) {
                        // site local address with a host name has higher
                        // priority than one without host name
                        if (local.getHostName().equals(local.getHostAddress())
                                && !address.getHostName().equals(address.getHostAddress())) {
                            local = address;
                        }
                    }
                } else {
                    if (local == null) {
                        local = address;
                    }
                }
            }
        }
        return local;
    }

    /**
     * 是否为可用的IP
     */
    public static boolean isValidIpStr(String ip) {
        return getValidIp(ip) != -1;
    }

    /**
     * 检查ip字符串是否正确，并返回正确ip字符串对应的数字格式
     *
     * @param ip ip字符串
     * @return ip的数字格式, 如果不是合法的ip字符串则返回-1
     */
    public static long getValidIp(String ip) {
        ip = StringUtils.trim(ip);

        if (StringUtils.isBlank(ip) || !StringUtils.contains(ip, ".") || ip.length() > 15) {
            return -1;
        }

        String[] ips = ip.split("\\.");
        if (ips.length != 4) {
            return -1;
        }

        long ipInt = 0;
        for (String s : ips) {
            if (!NumberUtils.isDigits(s)) {
                return -1;
            }
            int p = Integer.parseUnsignedInt(s);
            if (p < 0 || p > 255) {
                return -1;
            }

            ipInt <<= 8;
            ipInt |= p;
        }

        return ipInt;
    }

    /**
     * 是否为可用的公网IP<p>
     * 缺省路由：<p>
     * 0.0.0.0<p>
     * 限制广播地址:<p>
     * 255.255.255.255<p>
     * 私有地址：<p>
     * 10.0.0.0/8:10.0.0.0-10.255.255.255<p>
     * 00001010-<p>
     * 172.16.0.0/12:172.16.0.0-172.31.255.255<p>
     * 101011000001 0000-101011000001 1111<p>
     * 192.168.0.0/16:192.168.0.0-192.168.255.255<p>
     * 1100000010101000-<p>
     * DHCP网络故障地址：<p>
     * 169.254.0.0/16:169.254.x.x<p>
     * 1010100111111110-<p>
     * 回环地址：<p>
     * 127.0.0.0/8:127.0.0.0-127.255.255.255<p>
     * 01111111-<p>
     * 组播地址：<p>
     * 224.0.0.0/4:224.0.0.0-239.255.255.255<p>
     * 1110 0000-1110 1111<p>
     *
     * @param ip ip字符串
     * @return 公网ip地址返回true
     */
    public static boolean isValidPublicIpStr(String ip) {
        long ipInt = getValidIp(ip);
        if (0 >= ipInt) {
            return false;
        }
        // 10.0.0.0/8       :0x000a
        // 172.16.0.0/12    :0x0ac1
        // 192.168.0.0/16   :0xc0a8
        // 169.254.0.0/16   :0xa9fe
        // 127.0.0.0/8      :0x007f
        // 224.0.0.0/4      :0x00e0
        return (ipInt >>> 24) != 0x000a &&
                (ipInt >>> 20) != 0x0ac1 &&
                (ipInt >>> 16) != 0xc0a8 &&
                (ipInt >>> 16) != 0xa9fe &&
                (ipInt >>> 24) != 0x007f &&
                (ipInt >>> 28) != 0x00e0;
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

    public static String toMacAddr(byte[] mac) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, length = mac.length; i < length; i++) {
            if (i > 0) {
                sb.append("-");
            }
            int t = 0xff & mac[i];
            if (t <= 0x0f) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(t));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
     */
    public static long ipV4ToLong(String strIp) {
        long[] ip = new long[4];
        //先找到IP地址字符串中.的位置
        int position1 = strIp.indexOf('.');
        int position2 = strIp.indexOf('.', position1 + 1);
        int position3 = strIp.indexOf('.', position2 + 1);
        //将每个.之间的字符串转换成整型
        ip[0] = Long.parseLong(strIp.substring(0, position1));
        ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
        ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
        ip[3] = Long.parseLong(strIp.substring(position3 + 1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    public static void main(String[] args) {
        System.out.println(0xffffffff);
        logger.info("getLocalHostAddresses()={}", String.join(",", getLocalHostAddresses()));
        logger.info("getLocalHostAddress()={}", getLocalHostAddress());
        System.out.println("getLocalHostName()=" + getLocalHostName());
        String ip;
        ip = "172.17.13.29";
        System.out.println(ip + ":" + getValidIp(ip) + ":" + isValidPublicIpStr(ip));
        ip = "10.16.6.90";
        System.out.println(ip + ":" + getValidIp(ip) + ":" + isValidPublicIpStr(ip));
        ip = "127.0.0.1";
        System.out.println(ip + ":" + getValidIp(ip) + ":" + isValidPublicIpStr(ip));
        ip = "192.168.0.0";
        System.out.println(ip + ":" + getValidIp(ip) + ":" + isValidPublicIpStr(ip));
        ip = "10.1.112.127";
        System.out.println(ip + ":" + getValidIp(ip) + ":" + isValidPublicIpStr(ip));
        ip = "101112127";
        System.out.println(ip + ":" + getValidIp(ip) + ":" + isValidPublicIpStr(ip));
        ip = "101.124.6.9";
        System.out.println(ip + ":" + getValidIp(ip) + ":" + isValidPublicIpStr(ip));

        String ipaddr = "39.156.66.10";
        System.out.println("ipaddr:" + ipaddr);
        try {
            InetAddress addr = InetAddress.getByName(ipaddr);
            System.out.println("ipaddr version:" + addr.getClass().getName());
            System.out.println("ipaddr ping:" + addr.isReachable(5000));

            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface ni = networkInterfaces.nextElement();
                println("设备在操作系统中显示的名称", "：", ni.getDisplayName());
                println("网络设备在操作系统中的名称", "：", ni.getName());
                println("网络接口是否已经称启并正常工作", "：", ni.isUp());
                println("网络接口的最大传输单元（MTU）", "：", ni.getMTU());
                println("网络接口是是否是环回接口", "：", ni.isLoopback());

                if (addr.isReachable(ni, 0, 5000)) {
                    System.out.println("SUCCESS - ping " + ipaddr);
                } else {
                    System.out.println("FAILURE - ping " + ipaddr);
                }

                Enumeration<InetAddress> inetAddressesList = ni.getInetAddresses();
                print("网络接口的硬件MAC地址", ":");
                byte[] hardwareAddress = ni.getHardwareAddress();
                if (hardwareAddress != null && hardwareAddress.length > 0) {
                    println(bytesToHexMac(hardwareAddress));
                } else {
                    println(ni.getHardwareAddress());
                }
                while (inetAddressesList.hasMoreElements()) {
                    InetAddress address = inetAddressesList.nextElement();
                    println("--主机地址：" + address.getHostAddress());
                }

                println("=============分隔=============");
            }

            printReachableIP(InetAddress.getByName(ipaddr), 80);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void printReachableIP(InetAddress remoteAddr, int remotePort) throws IOException {
        String retIP = null;

        Enumeration<NetworkInterface> netInterfaces;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> localAddrs = ni.getInetAddresses();
                while (localAddrs.hasMoreElements()) {
                    InetAddress localAddr = localAddrs.nextElement();
                    if (isReachable(localAddr, remoteAddr, remotePort, 5000)) {
                        retIP = localAddr.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            System.out.println("Error occurred while listing all the local network addresses.");
        }
        if (retIP == null) {
            System.out.println("NULL reachable local IP is found!");
        } else {
            System.out.println("Reachable local IP is found, it is " + retIP);
        }
    }

    /**
     * 通过Socket来判断可达性
     */
    public static boolean isReachable(InetAddress localInetAddr, InetAddress remoteInetAddr, int remotePort, int timeout) throws IOException {

        boolean isReachable = false;
        Socket socket = null;
        try {
            socket = new Socket();
            // 端口号设置为 0 表示在本地挑选一个可用端口进行连接
            SocketAddress localSocketAddr = new InetSocketAddress(localInetAddr, 0);
            socket.bind(localSocketAddr);
            InetSocketAddress endpointSocketAddr =
                    new InetSocketAddress(remoteInetAddr, remotePort);
            socket.connect(endpointSocketAddr, timeout);
            System.out.println("SUCCESS - connection established! Local: " +
                    localInetAddr.getHostAddress() + " remote: " +
                    remoteInetAddr.getHostAddress() + " remotePort" + remotePort);
            isReachable = true;
        } catch (IOException e) {
            System.out.println("FAILRE - CAN not connect! Local: " +
                    localInetAddr.getHostAddress() + " remote: " +
                    remoteInetAddr.getHostAddress() + " remotePort" + remotePort);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error occurred while closing socket..");
                }
            }
        }
        return isReachable;
    }

    public static void println(Object... strings) {
        print(strings);
        System.out.println();
    }

    private static void print(Object... strings) {
        for (int i = 0; i < strings.length; i++) {
            System.out.print(i + "@" + strings[i]);
        }
    }

}
