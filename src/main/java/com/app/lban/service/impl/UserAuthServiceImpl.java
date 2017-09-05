package com.app.lban.service.impl;

import com.app.lban.dao.UserAuthDao;
import com.app.lban.dao.UserDao;
import com.app.lban.model.User;
import com.app.lban.model.UserAuth;
import com.app.lban.service.UserAuthService;
import com.app.lban.utils.MD5Util;
import com.app.lban.utils.SharedJedisUtils;
import com.app.lban.utils.Sms;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserAuthServiceImpl implements UserAuthService {

    @Resource
    private UserDao userDao;
    @Resource
    private UserAuthDao userAuthDao;
    @Resource
    private SharedJedisUtils sharedJedisUtils;

    private static final String PREFIX_VERIFYCODE = "verificationCode";

    public Long register(String phone, String password) {

        int hasPhone = userAuthDao.hasPhone(phone);
        if (hasPhone > 0) {
            return Long.valueOf(-3);
        }
        User user = new User();
        user.setNickName(UUID.randomUUID().toString().substring(0, 7));
        //TODO 默认头像
        user.setAvatar("");
        userDao.addUser(user);
        UserAuth userAuth = new UserAuth();
        userAuth.setUserId(user.getId());
        userAuth.setPhone(phone);
        userAuth.setPassword(password);
        return userAuthDao.addUserAuth(userAuth);
    }

    public List<UserAuth> getAllUserAuth() {
        return userAuthDao.selectAllUserAuth();
    }

    public String login(String phone, String password) {

        UserAuth userAuth = userAuthDao.selectOne(phone, password);
        if (userAuth == null) {
            return null;
        } else {
            String token = MD5Util.generateValue(userAuth.getId().toString());
            int expire = 86400;
            //TODO 把token存进redis
            String key = "token" + phone;
            sharedJedisUtils.set(key, token, expire);
            return token;
        }
    }

    public Long resetPwd(String phone, String password) {

        int hasPhone = userAuthDao.hasPhone(phone);
        if (hasPhone <= 0) {
            return Long.valueOf(-3);
        }
        return userAuthDao.modifyPwd(phone, password);
    }

    public boolean genVerCode(String phone) {

        try {
            String rand = (int)(Math.random()*9+1)*100000 + "";
            String key = PREFIX_VERIFYCODE + phone;
            int expire = 60;
            //TODO 调用阿里短信接口
            Sms.sendSms(phone, rand);
            sharedJedisUtils.set(key, rand, expire);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
