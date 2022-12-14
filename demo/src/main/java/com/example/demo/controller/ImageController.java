package com.example.demo.controller;

import com.example.demo.entity.Image;
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
        System.out.println("Request history of user" + username);
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
    public String requestOCR(@RequestBody String imageHashwithUploader) {
        if (imageHashwithUploader == null) {
            System.out.println("Illegal image hash!");
            return null;
        }
        String[] splitted = imageHashwithUploader.split("\\s+");
        String imageHash = splitted[0];
        String uploader = splitted[1];
        String result = "";
        
        result = OCR.callOCR("./static/img/" + imageHash);
        System.out.println("OCR result by uploader " + uploader + " of " + imageHash + " : " + result);
        
        Image img = new Image();
        img.setImage(imageHash);
        img.setText(result);
        img.setUploader(uploader);
        imgService.InsertImage(img);
        
        return result;
    }
    
    @RequestMapping(value = "/requestDel", method = RequestMethod.POST)
    public int delImage(@RequestBody String imageHash) {
        if (imageHash == null)
                return 1;
        System.out.println("Request deletion of image hash" + imageHash);
        imgService.DeleteImage(imageHash);
        return 1;
    }
}
