package net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-11 21:52
 */ 
@SuppressWarnings({"all"})
public class SideB {
    public static void main(String[] args) throws IOException {
        //1. 要监听的端口：9999
        int portListened = 9999;
        //2. 创建用于数据传输的socket
        DatagramSocket socket = new DatagramSocket(portListened);
        //3. 1024字节的缓冲数据， 注意UDP的数据包上限是64K
        byte[] buf = new byte[1024];
        //4. 创建用于接收数据的数据包
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        //5. 接收数据， 如果没有数据发送过来就会阻塞
        System.out.println("等待接收接头暗号...");
        socket.receive(packet);
        //6. 拆包并显示， packet.getData()就是在拆包
        System.out.printf("收到接头暗号: %s, 长度 %d",new String(packet.getData(),0,packet.getLength()),packet.getLength());

        byte[] bytes = "宝塔镇河妖".getBytes();
        //7. 发送回信
        packet = new DatagramPacket(bytes,bytes.length, InetAddress.getLocalHost(),9998);
        socket.send(packet);
        //8. 关闭通道 退出
        socket.close();
    }
}
