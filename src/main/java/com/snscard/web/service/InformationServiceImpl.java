package com.snscard.web.service;
import API.dto.Post;
import com.snscard.web.DalleAPI.Generate_Image;
import com.snscard.web.GetGithub.GetGithubInfo;
import com.snscard.web.GetNaver.GetNaverInfo;
import com.snscard.web.GetTistory.GetTistoryInfo;
import com.snscard.web.config.GetUrlInfo;
import com.snscard.web.config.ImageCropper;
import com.snscard.web.config.SaveImage;
import com.snscard.web.config.UploadImage;
import com.snscard.web.mapper.InformationMapper;
import com.snscard.web.pojo.*;
import com.snscard.web.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
        String uuid = UUID.randomUUID().toString();
        String imageAllName = uuid+name + ".png";

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
    public Object addUserUrl( String github_url,String naver_url, String tistory_url, int cardNum) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getSession().getAttribute("username");
        GetGithubInfo githubInfo = new GetGithubInfo();
        GetNaverInfo naverInfo = new GetNaverInfo();
        GetTistoryInfo tistoryInfo = new GetTistoryInfo();

        GetTitleDate getGithub = new GetUrlInfo().getUrl(github_url, githubInfo);
        GetTitleDate getNaver= new GetUrlInfo().getUrl(naver_url, naverInfo);
        GetTitleDate getTistory = new GetUrlInfo().getUrl(tistory_url, tistoryInfo);
        if (getGithub!=null)
        {
            if("0".equals(getGithub.getDate()))
            {
                return new Result_Url(5001,"정오 없습니다.");
            }
            String gitHubUrl= informationMapper.queryGithubUrl(new NameCardNum(username,cardNum));
                if (gitHubUrl == null) {
                    informationMapper.insertGitHubUrl(new UserGitHubUrl(username, github_url, cardNum));
                    informationMapper.insertGithubInfo(new UserTitleDate(username,getGithub.getTitle(),getGithub.getDate(),cardNum));
                }else{
                    informationMapper.updateGithubUrl(new UserGitHubUrl(username,github_url,cardNum));
                    informationMapper.updateGithubInfo(new UserTitleDate(username,getGithub.getTitle(),getGithub.getDate(),cardNum));
                }
        }
        if(getNaver!=null)
        {
            if("0".equals(getNaver.getDate()))
            {
                return new Result_Url(5001,"정보 없습니다");
            }

                String naverUrl = informationMapper.queryNaverUrl(new NameCardNum(username, cardNum));
            if(naverUrl==null)
            {
                informationMapper.insertNaverUrl(new UserNaverUrl(username,naverUrl,cardNum));            informationMapper.insertNaverInfo(new UserTitleDate(username,getNaver.getTitle(),getNaver.getDate(),cardNum));
            }else{
                informationMapper.updateNaverUrl(new UserNaverUrl(username,naverUrl,cardNum));
                informationMapper.updateNaverInfo(new UserTitleDate(username,getNaver.getTitle(),getNaver.getDate(),cardNum));
            }

        }
        if(getTistory!=null)
        {
           if("0".equals(getTistory.getDate()))
           {
               return new Result_Url(5001,"정보 없습니다");
           }

           String tistoryUrl = informationMapper.queryTistoryUrl(new NameCardNum(username, cardNum));
           if(tistoryUrl==null)
           {

               informationMapper.insertTistoryUrl(new UserTistoryUrl(username,tistory_url,cardNum));
               informationMapper.insertTistoryInfo(new UserTitleDate(username,getTistory.getTitle(),getTistory.getDate(),cardNum));
           }else {
              informationMapper.updateTistoryUrl(new UserTistoryUrl(username,tistory_url,cardNum));
              informationMapper.updateTistoryInfo(new UserTitleDate(username,getTistory.getTitle(),getTistory.getDate(),cardNum));
           }

        }
        return new Result_Url_Info(3001,new GetTitleDate(getGithub.getTitle(),getGithub.getDate()),new GetTitleDate(getNaver.getTitle(),getNaver.getDate()),new GetTitleDate(getTistory.getTitle(),getTistory.getDate()));
    }


    @Override
    public Result_Info addUserInfo(int cardNum, String info, int templateNum, @NotNull String imageUrl, int x1, int y1, int x3, int y3) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getSession().getAttribute("username");
        String uuid = UUID.randomUUID().toString();
        String imageName = uuid + username+".png";
        subject.getSession().setAttribute("image", imageName);
        String suffix = ".png";
        String imageAllName = imageName + suffix;
        subject.getSession().setAttribute("imageAllName", imageAllName);
        String path = "/root/img/generatedImages/";
        String rootPath=path+imageAllName;
        String uuid2 = UUID.randomUUID().toString();
        String imageAllName2 = uuid2+username + ".png";
        subject.getSession().setAttribute("rootPath", path + imageAllName);
        new SaveImage().save(imageUrl, imageName, path, suffix);
        new ImageCropper(x1, y1, x3, y3, rootPath, username, imageAllName2).imageCropper();
        String cropperImagePath="https://ourcards.top/modifyImages/"+imageAllName2;
        informationMapper.insertUserPath(username, imageAllName, cardNum);
        informationMapper.insertImageCoordinate(username, cardNum, x1, y1, x3, y3);
        informationMapper.insertUserInfo(username, cardNum, info, templateNum);
        return new Result_Info(2001, new ResultInfoAndImage(username, cardNum, info,cropperImagePath, templateNum));
    }

    @Override
    public Result_Info queryUserInfo(int cardNum) {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getSession().getAttribute("username");
        return new Result_Info(2007, informationMapper.queryUserInfo(username, cardNum));
    }

    @Override
    public Result_Image querySampleUrl() {
        Random random = new Random();
        int i = random.nextInt(4) + 1;
        String sampleImageAllName = informationMapper.querySampleUrl(i);
        String path="https://ourcards.top/generatedImages/"+sampleImageAllName;
        return new Result_Image(2001,path);
    }


    @Override
    public String addUserAnswer(String a1, String a2, String a3, String a4, String a5) {
        try {
            return new Generate_Image().getImage(a1, a2, a3, a4, a5);
        } catch (Exception e) {
            return "오류발생";
        }
    }
}

