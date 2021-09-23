package com.example.demo.word;


import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;

/**
 * 名片识别
 */
public class BusinessCard {



    public static String accurateBasic(String filePath,String accessToken){
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/business_card";
        try {
            // 本地文件路径
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String result = HttpUtil.post(url, "24.31ec5b256d03470ac57559e88b4d82b5.2592000.1633924608.282335-24832810", param);
            //将字符串重新转JSON
            JSONObject json = new JSONObject(result);
            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}