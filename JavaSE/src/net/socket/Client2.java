package net.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-11 11:59
 */
public class Client2 {

    //2. 客户端未链接时， 处于阻塞状态
    //3. 获取socket输入流从中读取数据
    public static void main(String[] args) throws IOException {
        //1. 连接服务器端口， 如果是真实的情况， 第一个参数要写服务器的真实IP，此处因为都在本季测试所以getLocalHost
        Socket socket = new Socket(InetAddress.getLocalHost(), 9998);
        System.out.println("获取到连接服务器的socket = " + socket);
        //得到和当前socket关联的输出流
        OutputStream os = socket.getOutputStream();
        System.out.println("sending to server:");
        os.write("Hello Socket, I'm Client".getBytes());
        /**
         * 下面是在1的基础上的添加
         */
        socket.shutdownOutput();//设置写入结束标记， 没有这一行会一直阻塞
        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int readLen = 0;
        while ((readLen=is.read(bytes))!=-1){
            System.out.println(new String(bytes,0,readLen));
        }
        is.close();

        os.close();
        socket.close();
        System.out.println("客户端退出");


    }
}
