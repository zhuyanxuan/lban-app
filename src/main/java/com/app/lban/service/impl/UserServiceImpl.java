package com.app.lban.service.impl;

import com.app.lban.dao.UserDao;
import com.app.lban.model.User;
import com.app.lban.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    public Long addUser(String nickName, String avatar) {
        User user = new User();
        user.setNickName(nickName);
        user.setAvatar(avatar);
        return userDao.addUser(user);
    }

    public List<User> getAllUser() {
        return userDao.selectAllUser();
    }
}
