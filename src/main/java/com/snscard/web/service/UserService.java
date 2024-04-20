package com.snscard.web.service;

import com.snscard.web.utils.ResultVO;
import jakarta.servlet.http.HttpSession;

public interface UserService {
    ResultVO login(String username, String password);
    ResultVO addUser(String username, String password);
    ResultVO deleteUser(HttpSession session);
    ResultVO updateUser(String password);
    ResultVO Logout();

}
