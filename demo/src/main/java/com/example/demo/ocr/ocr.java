package com.example.demo.ocr;

import com.baidu.ai.aip.utils.*;
import java.net.URLEncoder;

import com.baidubce.services.lss.model.Auth;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class ocr {
    
    public static String callOCR(String filePath) {
        // 请求 URL
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取 access_token，线上环境 access_token 有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, param);
            // System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String concatResult(String words) {
        JSONObject jsonObject = new JSONObject(words);
        String result = "";
        JSONObject words_result = jsonObject.getJSONObject("words_result");
        
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("OCR Debugging");
        
        System.out.println(callOCR("./test1.png"));
        
    }
}
