package net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-11 10:51
 */
public class InetAddressDemo {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress host1 = InetAddress.getLocalHost();// 192.168.10.102/192.168.10.102
        System.out.println("host1 = " + host1);
        InetAddress host2 = InetAddress.getByName("www.baidu.com");
        System.out.println("host2 = " + host2);
        /**
         * get the raw IP address of this object
         */
        byte[] host2Address = host2.getAddress();
        /**
         * the raw IP address in a string format
         */
        String host2HostAddress = host2.getHostAddress();
        /**
         * the host name for this IP address, or if the operation
         * is not allowed by the security check, the textual
         * representation of the IP address.
         */
        String host2HostName = host2.getHostName();
        /**
         * the fully qualified domain name for this IP address,
         * or if the operation is not allowed by the security check,
         * the textual representation of the IP address.
         */
        String host2CanonicalHostName = host2.getCanonicalHostName();
        System.out.println("host2Address = " + host2Address);
        System.out.println("host2HostAddress = " + host2HostAddress);
        System.out.println("host2HostName = " + host2HostName);
        System.out.println("host2CanonicalHostName = " + host2CanonicalHostName);
    }
}
