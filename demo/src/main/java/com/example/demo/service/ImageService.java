package com.example.demo.service;

import com.example.demo.entity.Image;
import com.example.demo.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageMapper imgMapper;
    
    public List<String> getHistoryImageByUsername(String username) {
        ArrayList<String> ret = new ArrayList<>();
        
        List<Image> qryImage = imgMapper.selectImagesByUploader(username);
        for (int i = 0; i < qryImage.size(); i++) {
            ret.add(qryImage.get(i).getImage());
            ret.add(qryImage.get(i).getText());
            
        }
        
        System.out.println("History of : " + username);
        System.out.println(ret);
        System.out.println(qryImage);
        return ret;
    }
    
    public void InsertImage(Image img) {
        imgMapper.insert(img);
    }
    
    public void DeleteImage(String ImageHash) {
        imgMapper.deleteByPrimaryKey(ImageHash);
    }
}
