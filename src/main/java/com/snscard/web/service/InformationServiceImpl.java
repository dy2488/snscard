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
        return new Result_Information(5000,userInformation);
    }

    @Override
    public Result_Information insertInformation(@RequestBody JSONObject jsonObject) {
        User_Information userInformation = new User_Information();
        userInformation.setUsername(jsonObject.getString("username"));
        userInformation.setName_us(jsonObject.getString("name_us"));
        userInformation.setTel(jsonObject.getString("tel"));
        userInformation.setAddress(jsonObject.getString("address"));
        userInformation.setVocation(jsonObject.getString("vocation"));
        userInformation.setCompany(jsonObject.getString("company"));
        userInformation.setIntroduction(jsonObject.getString("introduction"));
        Subject currentUser = SecurityUtils.getSubject();
        String username = (String)currentUser.getSession().getAttribute("username");
        userInformation.setName(username);
        informationMapper.insertUserInformation(userInformation);
        return new Result_Information(5001,userInformation);
    }

}
