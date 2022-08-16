package net.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Joshua.H.Brooks
 * @description
 * 监听9999端口， 接收客户端的请求， 打印其写入的信息，然后关闭socket
 * @date 2022-08-11 11:59
 */
public class Server {
    public static void main(String[] args) throws IOException {
        //1. 监听端口， 等待链接, 注意：需要保证该端口没有被其他服务监听
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端监听端口中:9999");
        //2. 客户端未链接时， 处于阻塞状态
        Socket socket = serverSocket.accept();
        System.out.println("socket = " + socket);
        //3. 获取socket输入流从中读取客户端写来的数据
        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int readLen = 0;
        System.out.println("receiving from client:");
        while((readLen = is.read(bytes))!=-1){
            System.out.println(new String(bytes,0,readLen));
        }
        is.read();
        is.close();
        socket.close();
        serverSocket.close();
        System.out.println("服务端退出...");
    }
}
