package com.snscard.web.service;

import com.snscard.web.pojo.Users;
import com.snscard.web.utils.ResultVO;

public interface UserService {
    ResultVO login(String username, String password);
    ResultVO addUser(String username, String password);
}
