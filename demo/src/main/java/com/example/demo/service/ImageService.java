package com.example.demo.service;

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
        ret.add("TestImageUrl");
        ret.add("TestImageResult");
        return ret;
    }
}
