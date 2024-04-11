package com.snscard.web.service;

import com.snscard.web.utils.ResultVO;

public interface UserService {
    ResultVO login(String username, String password);
}
