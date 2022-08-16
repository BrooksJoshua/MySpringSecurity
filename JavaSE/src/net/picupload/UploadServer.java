package net.picupload;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-11 16:28
 */
public class UploadServer {
    public static void main(String[] args) throws IOException {
        int portListened = 9999;
        //1。 监听端口
        ServerSocket serverSocket = new ServerSocket(portListened);
        System.out.println("服务端socketp监听端口: ortListened = " + portListened);
        //2 连接建立前都是阻塞状态
        Socket socket = serverSocket.accept();
        //3. 获取输入流， 用来读取客户端上传时写到数据通道的数据
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        //4. 定义要将客户端上传的文件保存到服务器的什么路径
        //String targetPath = "/Users/JoshuaBrooks/IdeaProjects/self/JavaSE/mp4-copy.mp4";
        String targetPath = "/Users/JoshuaBrooks/IdeaProjects/self/JavaSE/Xnip2022-07-31_16-38-49-copy.png";
        // 5. 获取写入流
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetPath));
//        //6. 定义缓冲字节数组
//        byte[] bytes = new byte[1024];
//        //7. 读取数据
//        int readLen = 0;
//        while((readLen = bis.read(bytes))!=-1){
//            //8. 将读取到的字节数组写到目标文件
//            bos.write(bytes,0,readLen);
//        }
        //因为StreamUtil封装了将输入流转化为字节数组的方法，所以6-8可以以简化为：
        bos.write(StreamUtil.stream2ByteArray(bis));

        //9 刷新缓冲
        bos.flush();
        System.out.println("收到图片!");
        // 10. 给客户端反馈Ack信息
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(socket.getOutputStream())));
        bufferedWriter.write("收到图片, Thx～😊");
        bufferedWriter.flush();
        System.out.println("已发送反馈Ack信息");
        //11. 写回的结束标记
        socket.shutdownOutput();

        bufferedWriter.close();
        bos.close();
        bis.close();
        socket.close();




    }
}
