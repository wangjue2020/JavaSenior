package com.web.programming.java;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 通信要素一：IP 和端口号
 * 1、IP： 唯一的标识 Internet上的计算机（通信实体）
 * 2、在Java中使用InetAddress类代表IP
 * 3、IP分类：IPv4 和 IPv6； 万维网和局域网
 * 4、域名：ww.baidu.com  www.mi.com www.sina.com
 * 5、本地回路地址： 127.0.0.1 对应着 localhost
 * 6、如何实例化InetAddress：两个方法： getByName(String host) 、 getLocalHost();
 * 7、两个常用方法：getHostName() / getHostAddress()
 * 8、端口号：正在计算机上运行的进程
 *      要求：不同的进程有不同的端口号
 *      范围：被规定为一个16位的整数0～65535
 * 9、端口号与IP地址的组合得出一个网络套接字socket
 */
public class InetAddressTest {
    public static void main(String[] args) {
        try {
            InetAddress inet1 = InetAddress.getByName("192.168.10.14");
            System.out.println(inet1);
            InetAddress inet2 = InetAddress.getByName("www.baidu.com");
            System.out.println(inet2);
            InetAddress inet3 = InetAddress.getByName("127.0.0.1");
            System.out.println(inet3);
            // 获取本地ip
            InetAddress inet4 = InetAddress.getLocalHost();
            System.out.println(inet4);

            //getHostName();
            System.out.println(inet2.getHostName());
            //getHostAddress();
            System.out.println(inet2.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
