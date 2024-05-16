package com.snscard.web.service;
import com.snscard.web.DalleAPI.Generate_Image;
import com.snscard.web.config.ImageCropper;
import com.snscard.web.config.SaveImage;
import com.snscard.web.config.UploadImage;
import com.snscard.web.config.UploadUserImages;
import com.snscard.web.mapper.InformationMapper;
import com.snscard.web.pojo.UserImage;
import com.snscard.web.pojo.User_Information;
import com.snscard.web.utils.Result_Image;
import com.snscard.web.utils.Result_Information;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InformationServiceImpl implements InformationService{

    private final InformationMapper informationMapper;

    @Autowired
    public InformationServiceImpl(InformationMapper informationMapper) {
        this.informationMapper = informationMapper;
    }

    @Override
    public Result_Information getInformation(String id) {
        User_Information userInformation = informationMapper.queryUserInformation(id);
        return new Result_Information(5000,"",userInformation);
    }

    @Override
    public Result_Information insertInformation(String username,String name_us,String tel,String address,String vocation,String company,String email,String introduction) {
        Subject currentUser = SecurityUtils.getSubject();
        String  name = (String)currentUser.getSession().getAttribute("username");
        User_Information userInformation1 = informationMapper.queryUserInformation(name);
        if(userInformation1!=null){
            User_Information updateUserInformation = new User_Information(name, username, name_us, tel, address, vocation, company, email, introduction);
            informationMapper.updateUserInformation(updateUserInformation);
            return new Result_Information(5002,"사용자 정보를 수정되었습니다",updateUserInformation);
        }
        else {
            User_Information userInformation = new User_Information(name, username, name_us, tel, address, vocation, company, email, introduction);
            try {
                informationMapper.insertUserInformation(userInformation);
                return new Result_Information(5001, "추가성공되었습니다", userInformation);

            } catch (Exception e) {
                return new Result_Information(5004, "예측하 지않는 문제가 발생습니다.", null);
            }
        }
    }

    @Override
    public ResponseEntity<Resource> getUserImage() throws IOException {
            Subject subject = SecurityUtils.getSubject();
        String rootPath= (String)subject.getSession().getAttribute("rootPath");
        return new UploadImage().getImage(rootPath);
    }

    @Override
    public Result_Image CropperImage(int x1, int y1, int x3, int y3) {
        Subject subject = SecurityUtils.getSubject();
        String path = (String)subject.getSession().getAttribute("rootPath");
        String name= (String)subject.getSession().getAttribute("username");
        String imageAllName= (String)subject.getSession().getAttribute("imageAllName");
        new ImageCropper(x1,y1,x3,y3,path,name,imageAllName).imageCropper();
        String uuid= (String)subject.getSession().getAttribute("uuid");
        informationMapper.insertUserCropPath(uuid,name,imageAllName);
        return new Result_Image(6002,"수정되었습니다.");
    }

    @Override
    public ResponseEntity<Resource> getImageCropper() throws IOException {
        Subject subject = SecurityUtils.getSubject();
        String cropperImagePath= (String)subject.getSession().getAttribute("cropperImagePath");
        return new UploadImage().getImage(cropperImagePath);
    }

    @Override
    public  ResponseEntity<List<String>> queryAllUserImage(int number) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        String username = (String)subject.getSession().getAttribute("username");
//        pageNum=1;
        UserImage userImage = new UserImage(username,number);
        List<String> userImagesPath = informationMapper.queryAllUserImage(userImage);
        List<String> list = new ArrayList<>();
        String localPath;
        for (String path : userImagesPath) {
            localPath="https://ourcards.top/generatedImages/"+path;
            list.add(localPath);
        }
        return new UploadUserImages().uploadUserImages(list);
    }

    @Override
    public ResponseEntity<List<String>> queryAllUserCropperImage(int number) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        String username = (String)subject.getSession().getAttribute("username");
        UserImage userImage = new UserImage(username,number);
        List<String> userImagesPath = informationMapper.queryAllUserCropperImage(userImage);
        List<String> list = new ArrayList<>();
        String localPath;
        for (String path : userImagesPath) {
            localPath="https://ourcards.top/modifyImages/"+path;
            list.add(localPath);
        }
        return new UploadUserImages().uploadUserImages(list);

    }


    @Override
    public Result_Image addUserAnswer(String a1, String a2, String a3, String a4, String a5) {
        Subject subject = SecurityUtils.getSubject();
        String uuid= UUID.randomUUID().toString();
        String username = (String) subject.getSession().getAttribute("username");
        String imageName=uuid+username;
        try{
            subject.getSession().setAttribute("image",imageName);
            String suffix=".png";
            String imageAllName=imageName+suffix;
            subject.getSession().setAttribute("imageAllName",imageAllName);
            subject.getSession().setAttribute("uuid",uuid);
            String path="/root/img/generatedImages/";
            subject.getSession().setAttribute("rootPath",path+imageAllName);
            String image = new Generate_Image().getImage(a1, a2, a3, a4, a5);
            new SaveImage().save(image,imageName,path,suffix);
            informationMapper.insertUserAnswer(uuid,username,a1,a2,a3,a4,a5);
            informationMapper.insertUserPath(uuid,username,imageAllName);

        }catch (Exception e) {
            new Result_Image(6004,"오류가 발생했습니다.");
        }
//        new ImageResizer().getImage(path);
        return new Result_Image(6001,"이미지 생성되었습니다.");
    }

}
