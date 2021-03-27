package com.iorichina.utils.http;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 常用获取客户端信息的工具
 */
public final class NetworkUtil {
    /**
     * Logger for this class
     */
    private static Logger logger = LoggerFactory.getLogger(NetworkUtil.class);

    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     *
     * @param request
     * @return
     * @throws IOException
     */
    public final static String getIpAddress(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        String ip = request.getHeader("X-Forwarded-For");
        String from = "X-Forwarded-For";

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                from = "Proxy-Client-IP";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                from = "WL-Proxy-Client-IP";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                from = "HTTP_CLIENT_IP";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_REAL_IP");
                from = "HTTP_X_REAL_IP";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                from = "HTTP_X_FORWARDED_FOR";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                from = "RemoteAddr";
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        if (logger.isInfoEnabled()) {
            logger.info("getIpAddress(HttpServletRequest) - [{}] - String ip=[{}]", from, ip);
        }
        return ip;
    }

    /**
     * 是否为可用的IP
     *
     * @param ip
     * @return
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
        for (int i = 0; i < ips.length; i++) {
            if (!NumberUtils.isDigits(ips[i])) {
                return -1;
            }
            int p = Integer.parseUnsignedInt(ips[i]);
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
        if (0 >= ipInt || 0xffffffff >= ipInt) {
            return false;
        }
        // 10.0.0.0/8       :0x000a
        // 172.16.0.0/12    :0x0ac1
        // 192.168.0.0/16   :0xc0a8
        // 169.254.0.0/16   :0xa9fe
        // 127.0.0.0/8      :0x007f
        // 224.0.0.0/4      :0x00e0
        if ((ipInt >>> 24) == 0x000a ||
                (ipInt >>> 20) == 0x0ac1 ||
                (ipInt >>> 16) == 0xc0a8 ||
                (ipInt >>> 16) == 0xa9fe ||
                (ipInt >>> 24) == 0x007f ||
                (ipInt >>> 28) == 0x00e0) {
            return false;
        }

        return true;
    }

    public static final List<String> getLocalHostAddresses() {
        List<NetworkInterface> nis;
        try {
            nis = Collections.list(NetworkInterface.getNetworkInterfaces());
        } catch (SocketException e) {
            logger.warn("[getLocalHostAddresses][SocketException]", e);
            return Collections.EMPTY_LIST;
        }

        List<String> addresses = new ArrayList<>();

        for (NetworkInterface ni : nis) {
            try {
                if (ni.isUp()) {
                    addresses.addAll(
                            Collections.list(ni.getInetAddresses())
                                    .stream().map(inet -> inet.getHostAddress())
                                    .collect(Collectors.toList())
                    );
                }
            } catch (SocketException e) {
                logger.debug("[getLocalHostAddresses][NetworkInterface][SocketException]", e);
            }
        }

        return addresses;
    }

    public static final String getLocalHostAddress() {
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
                if (ni.isUp()) {
                    addresses.addAll(Collections.list(ni.getInetAddresses()));
                }
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
            logger.info("address:{}", address.getHostAddress());
            logger.info("address-isLoopbackAddress:{}", address.isLoopbackAddress());
            logger.info("address-isSiteLocalAddress:{}", address.isSiteLocalAddress());
            logger.info("address-getHostName:{}", address.getHostName());
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

    public static void main(String[] args) {
        logger.info("getLocalHostAddresses()={}", getLocalHostAddresses().stream().collect(Collectors.joining(",")));
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
    }
}