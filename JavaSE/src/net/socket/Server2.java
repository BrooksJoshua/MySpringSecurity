package net.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Joshua.H.Brooks
 * @description 在server的基础上， 当客户端写入的信息被输出之后， 想客户端发一个确认信息，然后，客户端需要打印确认信息， 然后双方关闭socket。
 * @date 2022-08-11 11:59
 */
public class Server2 {
    public static void main(String[] args) throws IOException {
        //1. 监听端口， 等待链接, 注意：需要保证该端口没有被其他服务监听
        ServerSocket serverSocket = new ServerSocket(9998);
        System.out.println("服务端监听端口中:9998");
        //2. 客户端未链接时， 处于阻塞状态
        Socket socket = serverSocket.accept();
        System.out.println("socket = " + socket);
        //3. 获取socket输入流从中读取客户端写来的数据
        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int readLen = 0;
        System.out.print("receiving from client: ");
        while((readLen = is.read(bytes))!=-1){
            System.out.println(new String(bytes,0,readLen));
        }
        /**
         * 下面是在1的基础上的添加
         */
        socket.shutdownInput(); //设置读取结束标记， 没有这一行会一直阻塞
        OutputStream os = socket.getOutputStream();
        os.write("回应,Copy, I'm Server".getBytes());
        os.close();


        is.read();
        is.close();
        socket.close();
        serverSocket.close();
        System.out.println("服务端退出...");
    }
}
