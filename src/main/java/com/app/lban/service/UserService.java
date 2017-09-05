package com.app.lban.service;

import com.app.lban.model.User;

import java.util.List;

public interface UserService {

    Long addUser(String nickName, String avatar);

    List<User> getAllUser();
}
