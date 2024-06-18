package com.snscard.web.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageCropper {
    private int x1;
    private int y1;
    private int x3;
    private int y3;
    private String path;
    private String imageAllName;
    @Bean
    public int imageCropper()  {
        try{
            BufferedImage originalImage = ImageIO.read(new File(path));
            BufferedImage croppedImage = originalImage.getSubimage(x1,y1,x3-x1,y3-y1);
            String cropperImagePath="/root/img/modifyImages/"+imageAllName;
            ImageIO.write(croppedImage, "png", new File(cropperImagePath));
            return 1;
        }catch ( Exception e ){
            return 0;
        }
    }
}
