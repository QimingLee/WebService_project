package com.example.demo.controller;

import com.example.demo.service.ImageService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final ImageService imgService;
    
    @RequestMapping("/getHistory")
    @ResponseBody
    public List<String> getHistoryByUsername(String username) {
        return imgService.getHistoryImageByUsername(username);
    }
}
