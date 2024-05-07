package com.snscard.web.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Bean;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageCropper {
    private int x1;
    private int y1;
    private int x3;
    private int y3;
    private String path;
    private String name;
    @Bean
    public void imageCropper()  {
        try{
            BufferedImage originalImage = ImageIO.read(new File(path));
            Rectangle rect = new Rectangle(x1, y1, x3 - x1, y3 - y1);
            BufferedImage croppedImage = originalImage.getSubimage((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
            String uuid= UUID.randomUUID().toString();
            String imageNewName=uuid+name;
            Subject subject = SecurityUtils.getSubject();
            subject.getSession().setAttribute("uuid",uuid);
            String imageNewPath="D:/"+imageNewName+".png";
            subject.getSession().setAttribute("imageNewPath",imageNewPath);
            File outputfile = new File(imageNewPath);
            ImageIO.write(croppedImage, "png", outputfile);

        }catch ( Exception e ){
            System.out.println("image cropper error");
        }



    }
}
