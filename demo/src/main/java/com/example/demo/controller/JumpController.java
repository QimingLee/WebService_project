package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class JumpController {

    @RequestMapping("/1")
    public String t1(){

        return "/111";
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
