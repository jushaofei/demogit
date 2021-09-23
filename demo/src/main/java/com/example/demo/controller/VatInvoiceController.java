package com.example.demo.controller;


import com.example.demo.word.AccessToken;
import com.example.demo.word.VatInvoice;
import com.example.demo.word.Word;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
@Api(tags = "增值税发票识别")
public class VatInvoiceController {

    private static String sava_path="D:/demo/src/main/java/com/example/demo/word/images/";
    private static String[] list={"png","jpg","pdf"};

    @RequestMapping(path = "/aip/vatinvoiceRecog", method = {RequestMethod.POST})
    @ApiOperation(value = "通过增值税发票识别文字的方法")
    public String addDish(@RequestParam("fileNames") String fileNames, @RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {

       String accessToken = request.getParameter("accessToken");
       String ak = request.getParameter("ak");
       String sk = request.getParameter("sk");

       String path = null;// 文件路径
        if (file != null) {
            String type;
            String fileName = file.getOriginalFilename();
            System.out.println("上传的文件原名称:" + fileName);
            // 判断文件类型
            type = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (type != null ) {// 判断文件类型是否为空
                // 项目在容器中实际发布运行的根路径
                String realPath = request.getSession().getServletContext().getRealPath("/");
                // 自定义的文件名称
                String trueFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
                // 设置存放图片文件的路径并覆盖其中的内容
                File dire =new File(sava_path);
                if (dire.exists()) {
                    File [] fileList = dire.listFiles();
                    for (File file1 : fileList) {
                        file1.delete();
                    }
                }else {
                    dire.mkdirs();
                }
                //存放图片文件的路径
                path = sava_path + fileName;
                // 转存文件到指定的路径
                file.transferTo(new File(path));

                String word=null;
                if (type.equals("png") || type.equals("jpg")){
                    if (ak != null | sk != null) {
                        accessToken = AccessToken.getAuth(ak,sk);
                        word = VatInvoice.accurateBasic(sava_path + fileName, accessToken);
                    }else {
                        word = VatInvoice.accurateBasic(sava_path + fileName, accessToken);
                    }
                }else {
                    if (ak != null | sk != null) {
                        accessToken = AccessToken.getAuth(ak,sk);
                        word= Word.pdfpng(sava_path,fileName, accessToken);
                    }else {
                        word = Word.pdfpng(sava_path, fileName, accessToken);
                    }
                }
                JSONObject json = new JSONObject();
                json.put("fileName",fileNames);
                json.put("contents",word);
                return json.toString();
            }
        } else {
            return "文件类型为空";
        }

        return "已经成功上传到指定目录";
    }
}