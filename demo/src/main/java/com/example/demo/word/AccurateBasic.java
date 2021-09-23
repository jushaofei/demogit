package com.example.demo.word;


import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;

/**
 * 通用文字识别（高精度版）
 * @author SiNian
 */
public class AccurateBasic {



    public static String accurateBasic(String filePath,String accessToken){
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic";
        try {
            // 本地文件路径
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String result = HttpUtil.post(url, "24.ca6ebea464095c281a68fdbbcc084863.2592000.1632562772.282335-24757893", param);

            StringBuffer sb = new StringBuffer();
            //将字符串重新转JSON
            JSONObject json = new JSONObject(result);
            //识别都内容都放在键为words_result的数组中
            JSONArray str1 = json.getJSONArray("words_result");

            for(int i = 0;i<str1.length();i++) {
                //必须进行强转，因为str1.get(i)返回的是Object对象，是最顶层的父类。get("words")只返回value值
                System.out.println(((JSONObject)str1.get(i)).get("words"));
                sb.append(((JSONObject)str1.get(i)).get("words"));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}