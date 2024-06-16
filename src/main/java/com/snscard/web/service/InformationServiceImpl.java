package com.snscard.web.service;
import com.snscard.web.DalleAPI.Generate_Image;
import com.snscard.web.GetGithub.GetGithubInfo;
import com.snscard.web.GetNaver.GetNaverInfo;
import com.snscard.web.GetTistory.GetTistoryInfo;
import com.snscard.web.config.GetUrlInfo;
import com.snscard.web.config.ImageCropper;
import com.snscard.web.config.SaveImage;
import com.snscard.web.mapper.InformationMapper;
import com.snscard.web.pojo.*;
import com.snscard.web.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.*;

@Service
public class InformationServiceImpl implements InformationService {

    private final InformationMapper informationMapper;

    @Autowired
    public InformationServiceImpl(InformationMapper informationMapper) {
        this.informationMapper = informationMapper;
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
        subject.getSession().setAttribute("cardNumUserUrl",cardNum);
        GetGithubInfo githubInfo = new GetGithubInfo();
        GetNaverInfo naverInfo = new GetNaverInfo();
        GetTistoryInfo tistoryInfo = new GetTistoryInfo();

        GetTitleDate getGithub = new GetUrlInfo().getUrl(github_url, githubInfo);
        GetTitleDate getNaver= new GetUrlInfo().getUrl(naver_url, naverInfo);
        GetTitleDate getTistory = new GetUrlInfo().getUrl(tistory_url, tistoryInfo);
        if(!getGithub.getDate().equals("0"))
        {
            String gitHubUrl= informationMapper.queryGithubUrl(new NameCardNum(username,cardNum));
            if (gitHubUrl == null) {
                informationMapper.insertGitHubUrl(new UserGitHubUrl(username, github_url, cardNum));
                informationMapper.insertGithubInfo(new UserTitleDate(username,getGithub.getTitle(),getGithub.getDate(),cardNum));
            }else{
                informationMapper.updateGithubUrl(new UserGitHubUrl(username,github_url,cardNum));
                informationMapper.updateGithubInfo(new UserTitleDate(username,getGithub.getTitle(),getGithub.getDate(),cardNum));
            }
        }
            if(!"0".equals(getNaver.getDate()))
            {
                String naverUrl = informationMapper.queryNaverUrl(new NameCardNum(username, cardNum));
                if(naverUrl==null)
                {
                    informationMapper.insertNaverUrl(new UserNaverUrl(username,naver_url,cardNum));
                    informationMapper.insertNaverInfo(new UserTitleDate(username,getNaver.getTitle(),getNaver.getDate(),cardNum));
                }else{
                    informationMapper.updateNaverUrl(new UserNaverUrl(username,naver_url,cardNum));
                    informationMapper.updateNaverInfo(new UserTitleDate(username,getNaver.getTitle(),getNaver.getDate(),cardNum));
            }


        }
           if(!"0".equals(getTistory.getDate()))
           {
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
    public Result_Info addUserInfo(int cardNum, String info, int templateNum, String imageUrl, int x1, int y1, int x3, int y3) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getSession().getAttribute("username");
        String imageAllName = imageUrl.substring(37);
        List<String> sampleName = Arrays.asList("1.png","2.png","3.png","4.png");
        if(sampleName.contains(imageAllName))
        {
            String uuid = UUID.randomUUID().toString();
            imageAllName=uuid+imageAllName;
        }
        String rootPath= "/root/img/generatedImages/"+imageAllName;

        new ImageCropper(x1, y1, x3, y3, rootPath,imageAllName).imageCropper();
        String cropperImagePath="https://ourcards.top/modifyImages/"+imageAllName;
        String info1 = informationMapper.queryInfo(new NameCardNUmTemplateNum(username, cardNum, templateNum));
        if(info1==null)
        {
            informationMapper.insertUserInfo(new UserInfo(username,cardNum,info,templateNum));
        }else{
            informationMapper.updateUserInfo(new UserInfo(username,cardNum,info,templateNum));
        }
        informationMapper.insertUserCropPath(new ImagePath(username,imageAllName,cardNum));
        return new Result_Info(2001, new ResultInfoAndImage(username, cardNum, info,cropperImagePath, templateNum));
    }

    @Override
    public ResultNameInfoCode queryUserInfo(int cardNum) {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getSession().getAttribute("username");
        return new ResultNameInfoCode(2007,informationMapper.queryUserInfo(new NameCardNum(username, cardNum)));
    }

    @Override
    public Object updateUserInfo(int cardNum, String info, int templateNum) {
        Subject subject = SecurityUtils.getSubject();
        String username = (String)subject.getSession().getAttribute("username");
        String info1 = informationMapper.queryInfo(new NameCardNUmTemplateNum(username, cardNum, templateNum));
        if(info1==null)
        {
            return new Result_Url(2004,"수정하는 것 없습니다. 새로운 정보를 추가한 페이지 이동하세요");
        }
        informationMapper.updateUserInfo(new UserInfo(username,cardNum,info,templateNum));
        return new ResultNameInfoCode(2007,new ResultNameInfo(username,cardNum,info,templateNum));
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
    public Object queryUserUrlInfo(int cardNum) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String)subject.getSession().getAttribute("username");
        String github = informationMapper.queryGithubUrl(new NameCardNum(username, cardNum));
        String naver = informationMapper.queryNaverUrl(new NameCardNum(username, cardNum));
        String tistory = informationMapper.queryTistoryUrl(new NameCardNum(username, cardNum));
        GetTitleDate queryGithubInfo = informationMapper.queryGithubInfo(new NameCardNum(username, cardNum));
        GetTitleDate queryNaverInfo = informationMapper.queryNaverInfo(new NameCardNum(username, cardNum));
        GetTitleDate queryTistoryInfo = informationMapper.queryTistoryInfo(new NameCardNum(username, cardNum));
        informationMapper.queryTistoryInfo(new NameCardNum(username, cardNum));
        if(github!=null)
        {
            GetTitleDate githubInfo = new GetGithubInfo().getInfo(github);
            if(!queryGithubInfo.getTitle().equals(githubInfo.getTitle()))
            {
                informationMapper.updateGithubInfo(new UserTitleDate(username,githubInfo.getTitle(),githubInfo.getDate(),cardNum));
            }
        }
        if(naver!=null)
        {
            GetTitleDate naverInfo = new GetNaverInfo().getInfo(naver);
            if(!queryNaverInfo.getTitle().equals(naverInfo.getTitle()))
            {
                informationMapper.updateNaverInfo(new UserTitleDate(username,naverInfo.getTitle(),naverInfo.getDate(),cardNum));
            }
        }
        if(tistory!=null)
        {
            GetTitleDate tistoryInfo = new GetTistoryInfo().getInfo(tistory);
            if (!queryTistoryInfo.getTitle().equals(tistoryInfo.getTitle()))
            {
                informationMapper.updateTistoryInfo(new UserTitleDate(username,tistoryInfo.getTitle(),tistoryInfo.getDate(),cardNum));
            }
        }
        GetTitleDate g2 = informationMapper.queryGithubInfo(new NameCardNum(username, cardNum));
        GetTitleDate n2 = informationMapper.queryNaverInfo(new NameCardNum(username, cardNum));
        GetTitleDate t2 = informationMapper.queryTistoryInfo(new NameCardNum(username, cardNum));
        GetTitleDate github1 = new GetTitleDate();
        GetTitleDate naver1 = new GetTitleDate();
        GetTitleDate tistory1 = new GetTitleDate();
        if(g2==null)
        {
            github1.setTitle("");
            github1.setDate("");
        }else{
            github1.setTitle(g2.getTitle());
            github1.setDate(g2.getDate());
        }

        if(n2==null)
        {
           naver1.setTitle("");
           naver1.setDate("");
        }else{
            naver1.setTitle(n2.getTitle());
            naver1.setDate(n2.getDate());
        }

        if(t2==null)
        {
            tistory1.setTitle("");
            tistory1.setDate("");
        }else{
            tistory1.setTitle(t2.getTitle());
            tistory1.setDate(t2.getDate());
        }
        return new Result_Url_Info(2001,github1,naver1,tistory1);
    }


    @Override
    public Result_Image addUserAnswer(String a1, String a2, String a3, String a4, String a5) {
        try {
            String image = new Generate_Image().getImage(a1, a2, a3, a4, a5);
            String uuid = UUID.randomUUID().toString();
            Subject subject = SecurityUtils.getSubject();
            String username = (String)subject.getSession().getAttribute("username");
            String imageAllName=uuid+username+".png";
            String path="https://ourcards.top/generatedImages/";
            String savePath="/root/img/generatedImages/";
            subject.getSession().setAttribute("imageAllName",imageAllName);
            subject.getSession().setAttribute("rootPath",savePath+imageAllName);
            String imagePath=path+imageAllName;
            new SaveImage().save(image,imageAllName,savePath);
            informationMapper.insertUserPath(new ImagePath(username,imageAllName));
            return new Result_Image(2001,imagePath);
        } catch (Exception e) {
            return new Result_Image(2004,"저장 오류 발생");
        }
    }
}

