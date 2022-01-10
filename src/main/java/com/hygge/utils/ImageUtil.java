package com.hygge.utils;

import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/1/10 19:46
 * @description TODO    图片工具类
 */
@Component
public class ImageUtil {


    /**
     * 图片下载
     * @param filePath
     * @param imgUrl
     * @param fileName
     * @throws IOException
     */
    public void downloadImg(String filePath, String imgUrl, String fileName) throws IOException {
        URL url = new URL(imgUrl);
        URLConnection urlCon = url.openConnection();
        int times = 60*1000;
        urlCon.setConnectTimeout(times);
        InputStream inputStream = urlCon.getInputStream();
        byte[] b = new byte[1024];
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdirs();
        }
        FileOutputStream outputStream = new FileOutputStream(filePath + "\\" + fileName);
        int len;
        while ((len = inputStream.read(b)) != -1){
            outputStream.write(b, 0 , len);
        }
        System.out.println(fileName);
        inputStream.close();
        outputStream.close();
    }


}
