package com.snscard.web.controller;

import com.snscard.web.service.InformationService;
import com.snscard.web.utils.Result_Information;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="info")
public class User_Information {
    private final InformationService informationService;

    @Autowired
    public User_Information(InformationService informationService) {
        this.informationService = informationService;
    }

    @RequestMapping ("all/{id}")
    public Result_Information queryInformation(@PathVariable String id) {
        return informationService.getInformation(id);
    }
    @RequestMapping("addInfo")
    public Result_Information addInformation(String username,String name_us,String tel,String address,String vocation,String company,String email,String introduction) {
        return informationService.insertInformation(username,name_us,tel,address,vocation,company,email,introduction);
    }
}
