package com.snscard.web.service;

import com.snscard.web.mapper.InformationMapper;
import com.snscard.web.pojo.User_Information;
import com.snscard.web.utils.ResultVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class InformationServiceImpl implements InformationService{
    @Resource
    private InformationMapper informationMapper;
    @Override
    public ResultVO getInformation(String username) {
        User_Information userInformation = informationMapper.queryUserInformation(username);
        return new ResultVO(2000,"사용자 모든 정보",userInformation);
    }
}
