package com.app.lban.service;

import com.app.lban.model.UserAuth;

import java.util.List;

public interface UserAuthService {

    Long register(String phone, String password);

    List<UserAuth> getAllUserAuth();

    String login(String phone, String password);

    Long resetPwd(String phone, String password);

    boolean genVerCode(String phone);
}
