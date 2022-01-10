package com.hygge.scheduled;


import cn.hutool.json.JSONUtil;
import com.hygge.dto.BingImgsDTO;
import com.hygge.entity.BingWallpaper;
import com.hygge.service.BingWallpaperService;
import com.hygge.utils.ImageUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/1/10 19:44
 * @description TODO    定时任务
 */
@Component
@EnableScheduling
public class ScheduledTask {

    @Autowired
    private ImageUtil imageUtil;
    @Value("${http.bing}")
    private String bingUrl;
    @Value("${http.bing-img}")
    private String bingUrlImg;
    @Value("${images-address}")
    private String imagesAddress;
    @Autowired
    private BingWallpaperService bingWallpaperService;

    /**
     * 每天8点定时执行一次
     * 爬取必应壁纸下载并保存信息
     * //    @Scheduled(cron = "0 0/1 * * * ? ")
     */
    @Scheduled(cron = "0 0 16 1/1 * ? ")
    private void bingImages() throws IOException {
        //获取壁纸地址url
        Connection connect = Jsoup.connect(bingUrlImg);
        connect.ignoreContentType(true)
                .header("Accept", "*/*")
                .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36 Edg/97.0.1072.55")
                .timeout(10000);
        Document document = connect.get();
        String json = document.body().text();
        BingImgsDTO bingImgsDTO = JSONUtil.toBean(json, BingImgsDTO.class);
        BingWallpaper bingWallpaper = bingImgsDTO.getImages().get(0);
        //通过地址url下载图片
        int index = bingWallpaper.getUrlbase().indexOf(".");
        StringBuilder stringBuilder = new StringBuilder(bingWallpaper.getUrlbase());
        stringBuilder.delete(0, index + 1);
        imageUtil.downloadImg(imagesAddress, bingUrl + bingWallpaper.getUrl(), stringBuilder.toString() + "_1920x1080.jpg");
        bingWallpaperService.save(bingWallpaper);
    }



}
