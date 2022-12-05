package com.example.demo.ocr;

import com.baidu.ai.aip.utils.*;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;


public class OCR {

    private static final double select_rating = 0.75;
    
    /***
     * 判断字符是否为中文
     * @param ch 需要判断的字符
     * @return 中文返回true，非中文返回false
     */
    private static boolean isChinese(char ch) {
        //获取此字符的UniCodeBlock
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch);
        //  GENERAL_PUNCTUATION 判断中文的“号
        //  CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
        //  HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS 
            || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
            || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION         // 判断中文的。号
            || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS       // 判断中文的，号
            || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION                 // 判断中文的“号
        ) {
            return true;
        }
        return false;
    }
    
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
            return concatResult(result);
//            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String concatResult(String words) {
        JSONObject jsonObject = new JSONObject(words);
        JSONArray wordList = jsonObject.getJSONArray("words_result");
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < wordList.length(); i++) {
            String curStr = ((JSONObject)wordList.get(i)).getString("words");
            double appearance = 0;
            for (int j = 0; j < curStr.length(); j++) {
                if (isChinese(curStr.charAt(j)) || Character.isDigit(curStr.charAt(j)))
                    appearance += 1.0;
            }
            if (appearance >= (double)curStr.length() * OCR.select_rating) {
                result.append(curStr);
            }
        }
        return result.toString();
    }
    
    public static void main(String[] args) {
        System.out.println("OCR Debugging");
        
        System.out.println(callOCR("./test3.png"));
        
    }
}
