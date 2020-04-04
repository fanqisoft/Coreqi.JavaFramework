package cn.coreqi.web.controller;

import cn.coreqi.core.RespBean;
import cn.coreqi.core.TUserModel;
import cn.coreqi.core.VerificationCode;
import cn.coreqi.web.model.TMenuModel;
import cn.coreqi.web.services.MenuModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private MenuModelService menuModelService;

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

    @GetMapping("/getMenusByUserId/{id}")
    public RespBean getMenusByUserId(@PathVariable("id") Integer userId){
        List<TMenuModel> datas = menuModelService.getMenusByUserId(userId);
        return RespBean.ok("",datas);
    }
}
