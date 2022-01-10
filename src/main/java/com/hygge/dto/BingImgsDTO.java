package com.hygge.dto;

import com.hygge.entity.BingWallpaper;

import java.util.List;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/1/10 15:06
 * @description TODO
 */

public class BingImgsDTO {

    private List<BingWallpaper> images;


    public BingImgsDTO(List<BingWallpaper> images) {
        this.images = images;
    }

    public List<BingWallpaper> getImages() {
        return images;
    }

    public void setImages(List<BingWallpaper> images) {
        this.images = images;
    }

}
