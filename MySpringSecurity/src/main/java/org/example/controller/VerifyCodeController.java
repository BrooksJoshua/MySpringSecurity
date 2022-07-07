package org.example.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-07-05 23:04
 */
@RestController
public class VerifyCodeController {

    @Autowired
    Producer producer;

    @RequestMapping(value = "verifyCode", method = RequestMethod.GET)
    public void getCode(HttpServletResponse response, HttpSession httpSession) throws IOException {
        WebAuthenticationDetails details = (WebAuthenticationDetails) (SecurityContextHolder.getContext().getAuthentication().getDetails());
        System.out.printf(String.format("IP:%s,\t sessionId:%s", details.getRemoteAddress(),details.getSessionId()));

        //指明返回图片
        response.setContentType("image/jpeg");
        //生成文本
        String code = producer.createText();
        System.out.println("验证码code = " + code);
        httpSession.setAttribute("verifyCode",code);
        //生成含有上述文本的图片
        BufferedImage bufferedImage = producer.createImage(code);
        //将图片bufferedImage按"JPG"格式写到输出流out返回
        try (ServletOutputStream jos = response.getOutputStream()) {
            ImageIO.write(bufferedImage, "jpg", jos);
        }
    }
}
