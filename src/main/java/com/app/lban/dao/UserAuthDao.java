package com.app.lban.dao;

import com.app.lban.model.UserAuth;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAuthDao {

    Long addUserAuth(UserAuth userAuth);
    List<UserAuth> selectAllUserAuth();
    UserAuth selectOne(@Param("phone") String phone, @Param("password") String password);
    int hasPhone(@Param("phone") String phone);
    Long modifyPwd(@Param("phone") String phone, @Param("password") String password);
}
