package com.snscard.web.service;

import com.snscard.web.mapper.InformationMapper;
import com.snscard.web.pojo.User_Information;
import com.snscard.web.utils.ResultVO;
import com.snscard.web.utils.Result_Information;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformationServiceImpl implements InformationService{

    private final InformationMapper informationMapper;

    @Autowired
    public InformationServiceImpl(InformationMapper informationMapper) {
        this.informationMapper = informationMapper;
    }

    @Override
    public Result_Information getInformation() {
        Subject subject=SecurityUtils.getSubject();
        String  id = (String)subject.getSession().getAttribute("username");
        User_Information userInformation = informationMapper.queryUserInformation(id);
        return new Result_Information(5000,userInformation);
    }
}
