package cn.coreqi.web.controller;

import cn.coreqi.core.RespBean;
import cn.coreqi.core.TUserModel;
import cn.coreqi.core.VerificationCode;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class LoginController {
    @GetMapping("/verifyCode")
    public void verifyCode(HttpSession session, HttpServletResponse resp) throws IOException {
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        session.setAttribute("verify_code", text);
        VerificationCode.output(image,resp.getOutputStream());
    }

    @GetMapping("/currentUser")
    public RespBean currentUser(Authentication authentication){
        TUserModel user = (TUserModel) authentication.getPrincipal();
        return RespBean.ok("",user);
    }
}
