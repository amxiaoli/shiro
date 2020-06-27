package com.yida.springbootshirotest.controller;

import com.yida.springbootshirotest.entity.User;
import com.yida.springbootshirotest.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 谦让的小哪吒
 * @version 1.0
 * @date 2020/6/1
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 用户注册
     */
    @RequestMapping("/register")
    public String register(User user) {
        System.out.println(user);
        try {
            userService.insert(user);
            return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/register";
        }
    }

    /**
     * 用户登录
     */
    @RequestMapping("/login")
    public String login(String name,String password){
        System.out.println(name);
        // 获取主体对象
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(name,password));
            return "redirect:/index";
        }catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误!");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误!");
        }
        return "redirect:/login";
    }


    /**
     * 退出登录
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();//退出用户
        return "redirect:/login";
    }
}

