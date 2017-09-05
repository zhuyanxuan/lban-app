package com.app.lban.controller;

import com.app.lban.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;

/*    @RequestMapping(value = "/showUser", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<User> showUser(HttpServletRequest request){

        try {
            log.info("查询所有用户信息");
            List<User> userList = userService.getAllUser();
            return userList;
        }catch(Exception e){
            log.error(e);
        }
        return null;
    }

    @RequestMapping(value = "/addUser", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Long register(HttpServletRequest request) {

        String nickName = request.getParameter("nickName");
        String avatar = request.getParameter("avatar");
        try {
            return userService.addUser(nickName, avatar);
        } catch (Exception e) {
            log.error(e);
        }
        return 0;
    }*/
}
