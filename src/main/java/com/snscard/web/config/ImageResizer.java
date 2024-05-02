package com.snscard.web.config;

import lombok.NoArgsConstructor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@NoArgsConstructor
public class ImageResizer {
    public void getImage()
    {
        try {
            BufferedImage inputImage = ImageIO.read(new File("D:/3.png"));

            int targetWidth=400;
            int targetHeight=400;
            int x=(inputImage.getWidth()-targetWidth)/2;
            int y=(inputImage.getHeight()-targetHeight)/2;


            // 获取裁剪后的子图像
            BufferedImage croppedImage = inputImage.getSubimage(x, y, targetWidth,targetHeight);
//
            // 保存裁剪后的图像
            File output = new File("D:/cropped.png");
            ImageIO.write(croppedImage, "png", output);

            System.out.println("图像裁剪并保存完成。");
        } catch (IOException ignored) {
        }
    }

    public static void main(String[] args) {
       ImageResizer imageResizer = new ImageResizer();
       imageResizer.getImage();
    }
    }
