package net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * TCP:
 *
 * UDP:
 *
 *
 * @author Joshua.H.Brooks
 * @description A对B说: 天王盖地虎, B回复A说: 宝塔镇河妖
 * @date 2022-08-11 21:52
 */
public class SideA {
    public static void main(String[] args) throws IOException {
        //1. 要监听的端口：9998
        int portListened = 9998; //这个端口和另外一方的端口一般不一样，这一点和TCP的socket很不一样。
        //2. 创建用于数据传输的socket
        DatagramSocket socket = new DatagramSocket(portListened);
        //3. 将要发送的数据装包
        byte[] bytes = "天王盖地虎".getBytes();
        //4. 创建用于接收数据的数据包, 注意， 第三和第四个参数ip，port 都是数据接收方的
        DatagramPacket packet = new DatagramPacket(bytes,bytes.length, InetAddress.getLocalHost(),9999);
        //5. 发送数据
        socket.send(packet);

        //6. 接收回信
        byte[] buf = new byte[1024];
        packet = new DatagramPacket(buf,buf.length);
        socket.receive(packet);
        System.out.printf("收到回复: %s, 长度: %d", new String(packet.getData(),0, packet.getLength()),packet.getLength());

        //7. 关闭通道 退出
        socket.close();

    }
}
