package com.example.demo.controller;

import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    protected HttpServletRequest request;
    
    @Autowired
    protected HttpServletResponse response;
    
    @RequestMapping("/login")
    public int login(String username, String password){

        int ret = userService.login(username, password);
        if (ret == 1) {
            Cookie cookie = new Cookie("username", username);
            response.addCookie(cookie);
        }
        return ret;
    }
    
    @RequestMapping("/dashboard")
    public void buildDashboard() {
        Cookie[] cookies = request.getCookies();
        boolean hasUser = false;
        for (Cookie ck : cookies) {
            if (Objects.equals(ck.getName(), "username") && userService.hasUser(ck.getValue()))
                hasUser = true;
        }
        if (!hasUser) {
            try {
                response.sendRedirect("/login");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                response.sendRedirect("/dashboard");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    @RequestMapping("/logout")
    public void handleLogout() {
        String username = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie ck : cookies) {
            if (Objects.equals(ck.getName(), "username"))
                username = ck.getValue();
        }
        if (!Objects.equals(username, "")) {
            Cookie ck = new Cookie("username", "");
            ck.setMaxAge(0);
            response.addCookie(ck);
        }
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
