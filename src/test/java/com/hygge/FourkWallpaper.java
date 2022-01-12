package com.hygge;

import com.hygge.utils.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
 * @date: 2022/1/12 10:57
 * @description TODO    爬取彼岸图网
 */
@Slf4j
public class FourkWallpaper {
    public static void main(String[] args) throws IOException {
        long l = System.currentTimeMillis();
//        String s1 = "https://pic.netbian.com";  //主网址
//        String s2 = "https://pic.netbian.com/index_";
        String meinv = "https://pic.netbian.com/4kmeinv/index_";  //美女分类



        String path = "F:\\zeng\\壁纸\\20220112\\美女壁纸";

        //设置代理ip
        String ip = "113.125.82.236";
        int port = 8888;
        int n =162;     //分页总数
//        分页爬
        for (int i = 1; i < n; i++) {
            Connection connect = null;
            if (i < 2) {
                connect = Jsoup.connect("https://pic.netbian.com/4kmeinv/");
            } else {
                connect = Jsoup.connect(meinv + i + ".html");

            }
            connect.ignoreContentType(true)
                    .proxy(ip, port)
                    .header("Accept", "*/*")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36 Edg/97.0.1072.55")
                    .timeout(10000);
            Document document = connect.get();
            Elements select = document.select(".slist ul li a");
            for (Element item : select) {
                String href = item.absUrl("href");
                Connection connect1 = Jsoup.connect(href);
                connect1.ignoreContentType(true)
                        .proxy(ip, port)
                        .header("Accept", "*/*")
                        .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                        .header("Content-Type", "application/json;charset=UTF-8")
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36 Edg/97.0.1072.55")
                        .timeout(10000);
                Document document1 = connect1.get();
                Elements select1 = document1.select("#img img");
                for (Element item1 :
                        select1) {
                    String src = item1.absUrl("src");
                    String name = item1.attr("alt").replace(" ", "-") + ".jpg";
                    //下载图片
                    downloadImg(path, src, name);
                    log.info(name + "图片下载完成！");
                }
            }
        }
        long s = System.currentTimeMillis();
        long i = (s - l) / 1000;
        log.info("共计耗时：" + i + " s.");


    }

    /**
     * 图片下载
     *
     * @param filePath
     * @param imgUrl
     * @param fileName
     * @throws IOException
     */
    public static void downloadImg(String filePath, String imgUrl, String fileName) throws IOException {
        URL url = new URL(imgUrl);
        URLConnection urlCon = url.openConnection();
        int times = 60 * 1000;
        urlCon.setConnectTimeout(times);
        urlCon.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36 Edg/97.0.1072.55");
        InputStream inputStream = urlCon.getInputStream();
        byte[] b = new byte[1024];
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream outputStream = new FileOutputStream(filePath + "\\" + fileName);
        int len;
        while ((len = inputStream.read(b)) != -1) {
            outputStream.write(b, 0, len);
        }
        inputStream.close();
        outputStream.close();

    }
}
