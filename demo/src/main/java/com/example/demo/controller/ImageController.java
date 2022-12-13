package com.example.demo.controller;

import com.example.demo.ocr.OCR;
import com.example.demo.service.ImageService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final ImageService imgService;
    
    @RequestMapping("/getHistory")
    @ResponseBody
    public List<String> getHistoryByUsername(String username) {
        // TODO
        username = "gnaq";
        return imgService.getHistoryImageByUsername(username);
    }
    
    // returns image hash
    @PostMapping("/uploadImage")
    public String uploadImage(MultipartFile file) {
        String hashId = UUID.randomUUID().toString();
        String originalName = file.getOriginalFilename();
        assert originalName != null;
        hashId = hashId + originalName.substring(originalName.lastIndexOf("."));
        try {
            // upload directory
            String UPLOAD_DIR = "./static/img/";
            
            try(FileOutputStream out = new FileOutputStream(new File(UPLOAD_DIR, hashId))) {
                out.write(file.getBytes());
            }
            
            System.out.println("Success: " + hashId);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
        return hashId;
    }
    
    @RequestMapping(value = "/requestOCR", method = RequestMethod.POST)
    public String requestOCR(@RequestBody String imageHash) {
        String result = "";
        if (imageHash == null) {
            System.out.println("Illegal image hash!");
            return null;
        }
        result = OCR.callOCR("./static/img/" + imageHash);
        System.out.println("OCR result of " + imageHash + " : " + result);
        return result;
    }
}
