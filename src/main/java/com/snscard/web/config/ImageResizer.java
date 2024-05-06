package com.snscard.web.config;

import lombok.NoArgsConstructor;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/*
* 전에 자유롭게 자르하는 기능을 테스트할 때 사용한다. 현제 사용하지않습니다. ImageCropper 를 사용하고 있습니다.
* 코드를 보류하는 이유는 나중에 자류롭게 자르 필요할 유구사항을 있으면 이것을 참고할 수 있습니다.
* */
@NoArgsConstructor
public class ImageResizer {
    public void getImage(String path,int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4) throws IOException {
        int dx1 = Math.min(Math.min(x1, x2), Math.min(x3, x4));
        int dy1 = Math.min(Math.min(y1, y2), Math.min(y3, y4));
        int dx2 = Math.max(Math.max(x1, x2), Math.max(x3, x4));
        int dy2 = Math.max(Math.max(y1, y2), Math.max(y3, y4));

        


        BufferedImage inputImage = ImageIO.read(new File(path));

            BufferedImage resizedImage = new BufferedImage(1024, 569, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(inputImage, 0, 0,1024,569,0,0,1024,569, null);
            g.dispose();
            ImageIO.write(resizedImage, "png", new File(path));
            System.out.println("image resized");
    }
    }
