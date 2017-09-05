package com.app.lban.dao;

import com.app.lban.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    Long addUser(User user);
    List<User> selectAllUser();
}
