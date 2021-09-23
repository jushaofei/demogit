package com.example.demo.word;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Word {
    /**
     * 转换全部的pdf
     * @param fileAddress 文件地址
     * @param filename PDF文件名
     *
     * @return
     */

    public static String pdfpng(String fileAddress, String filename, String AccessToken){
        File file = new File(fileAddress+"\\"+filename);
        System.out.println("====================="+file);
        StringBuffer sb = new StringBuffer();
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 144); // Windows native DPI
                // BufferedImage srcImage = resize(image, 240, 240);//产生缩略图
                String path = fileAddress+"\\"+filename+"_"+(i+1)+".png";
                ImageIO.write(image, "png", new File(path));
                sb.append(AccurateBasic.accurateBasic(path,AccessToken));
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){
        String fileAddress = "C:\\Users\\SiNian\\IdeaProjects\\demo\\src\\main\\java\\com\\example\\demo\\word\\images\\";
        String filename = "1";
        String type = "png";
//        String ak = "6p4bZLN1WBCTnbfvKabvggkj";
//        String sk = "0dVE4yjAjEXXB7vtBeBY2VVilYtZzbD1";
//        String accessToken = AccessToken.getAuth(ak,sk);
        String accessToken= "25.3f340e9b073ca4aafa179f037ac5281d.315360000.1945328568.282335-24380104";
        pdfpng(fileAddress,filename,accessToken);
    }

}
