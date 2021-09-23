package com.example.demo.word;


import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;

/**
 * 营业执照识别
 */
public class BusinessLicense {



    public static String accurateBasic(String filePath,String accessToken){
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/business_license";
        try {
            // 本地文件路径
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String result = HttpUtil.post(url, "24.b95abb936b0987af6785847a11e36237.2592000.1633922249.282335-24832810", param);
            JSONObject json = new JSONObject(result);

            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}