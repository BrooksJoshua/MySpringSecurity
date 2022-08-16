package net.picupload;

import java.io.*;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-11 16:28
 */
public class StreamUtil {

    /**
     * 功能： 将输入流转换成字节数组， 即将文件内容存入byte[]
     * @param is
     * @return
     * @throws IOException
     */
    public static byte[] stream2ByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len;
        while((len = is.read(bytes)) !=-1){
            bos.write(bytes,0,len); //将读到的内容写入到字节数组流中
        }
        byte[] byteArray = bos.toByteArray();
        bos.close();
        return byteArray;
    }


    /**
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static String inputStream2String(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
