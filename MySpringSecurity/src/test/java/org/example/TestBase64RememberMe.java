package org.example;

import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.codec.Hex;
import sun.security.provider.MD5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-07-03 23:12
 */
@SpringBootTest
public class TestBase64RememberMe {
    @Test
    void testRememberMe() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //rememberMeCookie的值
        String rememberMeCookie = "QmVuZWRpY3Q6MTY1ODEyMDU2Njk2MTphODc2NDIzMzk3Mjc3MDllNTMyMzAxZDNiMGY1MTZmNA";
        //解密
        String decoded = new String(Base64.getDecoder().decode(rememberMeCookie), "UTF-8");
        //解密后的值
        System.out.println("明文decoded = " + decoded);
        //拆解明文
        String[] split = decoded.split(":");
        String username = Arrays.asList(split).stream().skip(0).findFirst().orElse("UsernameNull");
        System.out.println("拆解后明文的第一部分: username = " + username);
        String tokenExpiryTime = Arrays.asList(split).stream().skip(1).findFirst().orElse("ExpireTimeNull");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        System.out.println("当前时间: "+sdf.format(date)) ;
        date.setTime(Long.valueOf(tokenExpiryTime));
        System.out.println("拆解后明文第二部分过期时间的格式化字符串： "+sdf.format(date));
        String md5EncodedPwdSalt = Arrays.asList(split).stream().skip(2).findFirst().orElse("Md5EncodedPwdSaltNull");
        System.out.println("md5EncodedPwdSalt = " + md5EncodedPwdSalt);
        String password = "111";
        String key = "12345"; //configure(HttpSecurity http) throws Exception中 .rememberMeParameter("remMe").key("12345")的key值
        String data = username + ":" + tokenExpiryTime + ":" + password + ":" + key;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String encoded = new String(Hex.encode(md5.digest(data.getBytes())));
        System.out.println("encoded = " + encoded);
        System.out.println("拆解出来的密文的密码+盐值 与 MD5加密password+key 密文是否相等："+encoded.equals(md5EncodedPwdSalt));
    }

    @Test
    public void testPersistentTokenDecode() throws UnsupportedEncodingException {
        /**首次登陆访问接口hello 时的 remember-me cookie 值*/
        String firstLogRemMe =      "V3VtQm5xSnRlMFNwaXFOUmwxWFNPUSUzRCUzRDpoUFFYRFRhbU1XdjZvUnpOS0YwNDd3JTNEJTNE";
        /**重启浏览器 建立第二次session会话时的 remember-me cookie 值*/
        String sencodSessionRemMe = "V3VtQm5xSnRlMFNwaXFOUmwxWFNPUSUzRCUzRDo4bUdaY2JEQmxMSGp2TmNtN3JOaEx3JTNEJTNE";
        /**清除cookie后重新登陆时的 remember-me cookie 值*/
        String secondLogRemMe     = "c1drTFlFSU9wcDhVY1RJalY2Y2NZZyUzRCUzRDpjJTJCN3lEb2tDbzF0NFlVVGxXZExya3clM0QlM0Q";
        String code ="UTF-8";
        String firstSeriesToken = new String(Base64.getDecoder().decode(firstLogRemMe), code);
        String secondSeriesToken = new String(Base64.getDecoder().decode(sencodSessionRemMe), code);
        String thirdSeriesToken = new String(Base64.getDecoder().decode(secondLogRemMe), code);
        System.out.println("firstSeriesToken = " + firstSeriesToken);
        System.out.println("secondSeriesToken = " + secondSeriesToken);
        System.out.println("thirdSeriesToken = " + thirdSeriesToken);
    }
}
