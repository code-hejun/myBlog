package com.hj.blog.contorller.admin;

import com.hj.blog.po.User;
import com.hj.blog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping()
    public String loginPage(){
        return "admin/login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session){
        User user = loginService.checkUser(username,password);
        if(user==null){
            return "redirect:/admin";
        }else {
            user.setPassword(null);
            session.setAttribute("user",user);
            return "redirect:/admin/blog";

        }
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
