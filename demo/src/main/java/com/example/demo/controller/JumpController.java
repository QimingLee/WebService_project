package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class JumpController {

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
    
    @RequestMapping("/map")
    public String map() { 
        return "/map"; 
    }
    
    @RequestMapping("/register")
    public String register() {
        return "/register";
    }
}
