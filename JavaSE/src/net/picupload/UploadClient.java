package net.picupload;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Joshua.H.Brooks
 * @description
 * 将图片路径替换成大一点的视频路径方便测试：服务端的9999是固定的， 但是发起请求的客户端的端口是随机的。
 * @date 2022-08-11 19:01
 */
public class UploadClient {
    public static void main(String[] args) throws IOException {
        int portListened = 9999;
        //1. 建立和服务端的连接
        Socket socket = new Socket(InetAddress.getLocalHost(), portListened);
        //2. 要上传的文件的路径
        String path = "/Users/JoshuaBrooks/IdeaProjects/self/JavaSE/Xnip2022-07-31_16-38-49.png";
        //String path = "/Users/JoshuaBrooks/Downloads/downieResources/马士兵/算法/【数据结构&算法】一周刷爆LeetCode，算法大神左程云耗时150天打造数据结构与算法基础到高级全家桶教程，剑指大厂offer - 003 - 链表结构、栈、队列、….mp4";
        //3. 获取要上传的文件的输入流
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
        //4. 将上面得到的输入流转为字节数组
        byte[] bytes = StreamUtil.stream2ByteArray(bis);
        //5. 获取客户端要请求服务端用到的输出流
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        //6. 将字节数组写到服务端
        bos.write(bytes);
        //7. 输出结束标记
        socket.shutdownOutput();
        //8. 打印服务端的反馈Ack信息
        String s = StreamUtil.inputStream2String(socket.getInputStream());
        System.out.println("打印服务端的反馈Ack信息: "+s);

        //善后事宜，关闭流
        bis.close();
        bos.close();
        socket.close();
    }
}
