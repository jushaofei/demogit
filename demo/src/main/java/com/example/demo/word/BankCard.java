package com.example.demo.word;


import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;

/**
 * 银行卡识别
 */
public class BankCard {



    public static String accurateBasic(String filePath,String accessToken){
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/bankcard";
        try {
            // 本地文件路径
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String result = HttpUtil.post(url, "24.ce7bb0a8b9e02a47b70a42e59d0296f6.2592000.1633921171.282335-24832810", param);
            //将字符串重新转JSON
            JSONObject json = new JSONObject(result);
            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}