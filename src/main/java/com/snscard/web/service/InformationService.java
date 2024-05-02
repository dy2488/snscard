package com.snscard.web.service;

import com.snscard.web.pojo.User_Information;
import com.snscard.web.utils.ResultVO;
import com.snscard.web.utils.Result_Information;
import org.json.JSONObject;

public interface InformationService {
    Result_Information getInformation(String id);
    Result_Information insertInformation(String username,String name_us,String tel,String address,String vocation,String company,String email,String introduction);
}

