package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class JumpController {

    @RequestMapping("/1")
    public String t1(){
        return "/dashboard";
    }

    @RequestMapping("/dashboard")
    public String gotoDashboard() {
        return "/dashboard";
    }
    
    @RequestMapping("/search")
    public String t2(){
        return "/search";
    }

    @RequestMapping("/")
    public String start(){
        return "/login";
    }
}
