package com.snscard.web.service;

import com.snscard.web.mapper.InformationMapper;
import com.snscard.web.pojo.User_Information;
import com.snscard.web.utils.Result_Information;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
        User_Information userInformation = new User_Information(name,username,name_us,tel,address,vocation,company,email,introduction,null,null);
        try {
            informationMapper.insertUserInformation(userInformation);
            informationMapper.insertUserPath(name,null);
            return new Result_Information(5001,"추가성공되었습니다",userInformation);

        }catch (Exception e) {
            return new Result_Information(5004,"예측하 지않는 문제가 발생습니다.",null);
        }
    }

}
