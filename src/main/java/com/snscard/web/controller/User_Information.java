package com.snscard.web.controller;

import com.snscard.web.service.InformationService;
import com.snscard.web.utils.Result_Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="info")
public class User_Information {
    private final InformationService informationService;

    @Autowired
    public User_Information(InformationService informationService) {
        this.informationService = informationService;
    }

    @RequestMapping("all")
    public Result_Information queryInformation() {
        return informationService.getInformation();
    }
}
