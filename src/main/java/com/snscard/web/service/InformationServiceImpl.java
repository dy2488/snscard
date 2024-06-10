package com.snscard.web.service;
import API.dto.Post;
import com.snscard.web.DalleAPI.Generate_Image;
import com.snscard.web.GetGithub.GetGithubInfo;
import com.snscard.web.GetNaver.GetNaverInfo;
import com.snscard.web.GetTistory.GetTistoryInfo;
import com.snscard.web.config.ImageCropper;
import com.snscard.web.config.SaveImage;
import com.snscard.web.config.UploadImage;
import com.snscard.web.mapper.InformationMapper;
import com.snscard.web.pojo.UserGitHubUrl;
import com.snscard.web.pojo.UserImage;
import com.snscard.web.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InformationServiceImpl implements InformationService {

    private final InformationMapper informationMapper;

    @Autowired
    public InformationServiceImpl(InformationMapper informationMapper) {
        this.informationMapper = informationMapper;
    }


    @Override
    public ResponseEntity<Resource> getUserImage() throws IOException {
        Subject subject = SecurityUtils.getSubject();
        String rootPath = (String) subject.getSession().getAttribute("rootPath");
        return new UploadImage().getImage(rootPath);

    }

    @Override
    public Result_Image CropperImage(int x1, int y1, int x3, int y3) {
        Subject subject = SecurityUtils.getSubject();
        String path = (String) subject.getSession().getAttribute("rootPath");
        String name = (String) subject.getSession().getAttribute("username");
        String imageAllName = (String) subject.getSession().getAttribute("imageAllName");
        new ImageCropper(x1, y1, x3, y3, path, name, imageAllName).imageCropper();
//        informationMapper.insertUserCropPath(name,imageAllName,cardNum);
        return new Result_Image(6002, "https://ourcards.top/modifyImages/" + imageAllName);
    }

    @Override
    public ResponseEntity<Resource> getImageCropper() throws IOException {
        Subject subject = SecurityUtils.getSubject();
        String cropperImagePath = (String) subject.getSession().getAttribute("cropperImagePath");
        return new UploadImage().getImage(cropperImagePath);
    }

    @Override
    public GetImageList queryAllUserImage(int number) {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getSession().getAttribute("username");
//        pageNum=1;
        UserImage userImage = new UserImage(username, number);
        List<String> userImagesPath = informationMapper.queryAllUserImage(userImage);
        List<String> list = new ArrayList<>();
        String localPath;
        for (String path : userImagesPath) {
            localPath = "https://ourcards.top/generatedImages/" + path;
            list.add(localPath);
        }
        return new GetImageList(7001, list);
    }

    @Override
    public GetImageList queryAllUserCropperImage(int number) {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getSession().getAttribute("username");
        UserImage userImage = new UserImage(username, number);
        List<String> userImagesPath = informationMapper.queryAllUserCropperImage(userImage);
        List<String> list = new ArrayList<>();
        String localPath;
        for (String path : userImagesPath) {
            localPath = "https://ourcards.top/modifyImages/" + path;
            list.add(localPath);
        }
        return new GetImageList(7003, list);

    }

    @Override
    public Result_Url addUserUrl(String github_url, String naver_url, String tistory_url, int cardNum) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getSession().getAttribute("username");
        if (github_url == null || github_url.isEmpty()) {
            github_url = "";
        }
        if (naver_url == null || naver_url.isEmpty()) {
            naver_url = "";
        }
        if (tistory_url == null || tistory_url.isEmpty()) {
            tistory_url = "";
        }
        try {
            String gitHubInfo = informationMapper.queryUserGithubInfo(cardNum);
            if (gitHubInfo == null) {
                GetTitleDate githubInfo = new GetGithubInfo().getGithubInfo(github_url);
                informationMapper.insertGitHubUrl(new UserGitHubUrl(username, github_url, cardNum));

            }
        } catch (Exception e) {
            return new Result_Url(5000, "조소 오류발생");
        }
        return null;
    }


    @Override
    public Result_Info addUserInfo(int cardNum, String info, int templateNum, @NotNull String imageUrl, int x1, int y1, int x3, int y3) {
        String imageAllName = imageUrl.substring(36);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getSession().getAttribute("username");

        informationMapper.insertUserPath(username, imageAllName, cardNum);
        informationMapper.insertImageCoordinate(username, cardNum, x1, y1, x3, y3);
        informationMapper.insertUserInfo(username, cardNum, info, templateNum);
        return new Result_Info(2001, new ResultInfoAndImage(username, cardNum, info, imageUrl, templateNum));
    }

    @Override
    public Result_Info queryUserInfo(int cardNum) {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getSession().getAttribute("username");
        return new Result_Info(2007, informationMapper.queryUserInfo(username, cardNum));
    }


    @Override
    public Result_Image addUserAnswer(String a1, String a2, String a3, String a4, String a5) {
        Subject subject = SecurityUtils.getSubject();
        String uuid = UUID.randomUUID().toString();
        String username = (String) subject.getSession().getAttribute("username");
        String imageName = uuid + username;
        subject.getSession().setAttribute("image", imageName);
        String suffix = ".png";
        String imageAllName = imageName + suffix;
        subject.getSession().setAttribute("imageAllName", imageAllName);
        String path = "/root/img/generatedImages/";
        subject.getSession().setAttribute("rootPath", path + imageAllName);
        try {
            String image = new Generate_Image().getImage(a1, a2, a3, a4, a5);
            new SaveImage().save(image, imageName, path, suffix);
            return new Result_Image(6001, "https://ourcards.top/generatedImages/" + imageAllName);
        } catch (Exception e) {
            return new Result_Image(6004, "");
        }
    }
}

