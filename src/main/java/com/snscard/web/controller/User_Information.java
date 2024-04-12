package com.snscard.web.controller;

import com.snscard.web.service.InformationService;
import com.snscard.web.utils.ResultVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="info")
public class User_Information {
    @Resource
    InformationService informationService;
    @RequestMapping("all")
    public ResultVO queryInformation(@RequestParam("id") String username) {
        return informationService.getInformation(username);
    }
}
