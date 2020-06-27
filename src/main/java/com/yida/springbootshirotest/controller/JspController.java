package com.yida.springbootshirotest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 谦让的小哪吒
 * @version 1.0
 * @date 2020/6/1
 */
@Controller
public class JspController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
