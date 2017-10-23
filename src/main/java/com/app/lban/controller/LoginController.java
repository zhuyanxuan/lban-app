package com.app.lban.controller;

import com.app.lban.utils.SharedJedisUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
@RequestMapping("/login")
public class LoginController {

    private Logger log = Logger.getLogger(LoginController.class);

    @Resource
    private SharedJedisUtils sharedJedisUtils;

    @RequestMapping("login")
    public String login(HttpServletRequest request){
        return "login";
    }

    @RequestMapping("submit")
    public ModelAndView submit(HttpServletRequest request, ModelAndView mv){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //TODO 登录逻辑需要优化
        if("adminlban".equals(username) && "admin123456".equals(password)) {

            sharedJedisUtils.set("commonSessionId", UUID.randomUUID().toString(), 3600);
            mv.setViewName("main");
        }else{
            mv.addObject("msg", "账号或密码错误");
            mv.setViewName("login");
        }
        return mv;
    }
}
