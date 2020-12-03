package com.xf.controller;

import com.xf.dao.AccountMapper;
import com.xf.pojo.Account;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    AccountMapper accountMapper;
    @RequestMapping("/toConsole")
    public String toConsole(){
        return "views/home/console";
    }
    @RequestMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           Model model){
        Account account = new Account(username,password);
        accountMapper.RegisterInsert(account);
        return "redirect:/toLogin";

    }
    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        RedirectAttributes model,Model model2){

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            System.out.println("准备登录");
            subject.login(token);//用token里的username和password登录
            String cur_username = token.getUsername();
            model.addAttribute("cur_username",cur_username);

            return "redirect:/admin";
        }catch (UnknownAccountException e){
            System.out.println("未知用户");
            model.addAttribute("msg","未知用户,请注册");
            return "redirect:/toLogin";
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
            model.addAttribute("msg","密码错误");
            return "redirect:/toLogin";
        }

    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/toLogin";
    }
}
