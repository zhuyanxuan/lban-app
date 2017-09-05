package com.app.lban.controller;

import com.alibaba.fastjson.JSONObject;
import com.app.lban.service.UserAuthService;
import com.app.lban.utils.MD5Util;
import com.app.lban.utils.ResultJSON;
import com.app.lban.utils.SharedJedisUtils;
import com.app.lban.utils.Validator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/userAuth")
public class UserAuthController {

    private Logger log = Logger.getLogger(UserAuthController.class);
    @Resource
    private UserAuthService userAuthService;
    @Resource
    private SharedJedisUtils sharedJedisUtils;

    private static final String PREFIX_VERIFYCODE = "verificationCode";

   /* @RequestMapping("/showUserAuth")
    public String showUserAuth(HttpServletRequest request, Model model){
        log.info("查询所有用户权限信息");
        List<UserAuth> userAuthList = userAuthService.getAllUserAuth();
        model.addAttribute("userAuthList",userAuthList.toString());
        return "showUserAuth";
    }*/

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject register(HttpServletRequest request) {

        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String verificationCode = request.getParameter("verificationCode");
        if (Validator.isBlank(phone) || Validator.isBlank(password) || Validator.isBlank(verificationCode) || !Validator.isMobileNO(phone)) {
            return new ResultJSON(-2, "参数错误");
        }

        String redisCode = sharedJedisUtils.get(PREFIX_VERIFYCODE + phone);
        if (Validator.isBlank(redisCode)) {
            return new ResultJSON(-5, "验证码超时");
        } else if (!redisCode.equals(verificationCode)) {
            return new ResultJSON(-6,"验证码错误");
        }

        try {
            password = MD5Util.EncoderByMd5(password);
        } catch (Exception e) {
            log.error("UserAuthController.register error:" + e);
            return new ResultJSON(-4, "服务器错误");
        }
        Long id = userAuthService.register(phone, password);
        if (id == -3) {
            return new ResultJSON(-3, "该手机号已注册");
        } else if (id > 0) {
            return new ResultJSON(0, "注册成功");
        } else {
            return new ResultJSON(-1, "注册失败");
        }
    }

    @RequestMapping(value = "/loginByPhone", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject loginByPhone(HttpServletRequest request) {

        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        if (Validator.isBlank(phone) || Validator.isBlank(password) || !Validator.isMobileNO(phone)) {
            return new ResultJSON(-2, "参数错误");
        }
        try {
            password = MD5Util.EncoderByMd5(password);
        } catch (Exception e) {
            log.error("UserAuthController.loginByPhone error:" + e);
            return new ResultJSON(-4, "服务器错误");
        }
        String token = userAuthService.login(phone, password);
        if (token == null) {
            return new ResultJSON(-1, "登录失败");
        }
        ResultJSON result = new ResultJSON(0, "登录成功");
        result.put("token", token);
        return result;
    }

    @RequestMapping(value = "resetPwd", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject resetPwd(HttpServletRequest request) {

        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String verificationCode = request.getParameter("verificationCode");
        if (Validator.isBlank(phone) || Validator.isBlank(password) || Validator.isBlank(verificationCode) || !Validator.isMobileNO(phone)) {
            return new ResultJSON(-2, "参数错误");
        }

        String redisCode = sharedJedisUtils.get(PREFIX_VERIFYCODE + phone);
        if (Validator.isBlank(redisCode)) {
            return new ResultJSON(-5, "验证码超时");
        } else if (!redisCode.equals(verificationCode)) {
            return new ResultJSON(-6,"验证码错误");
        }

        try {
            password = MD5Util.EncoderByMd5(password);
        } catch (Exception e) {
            log.error("UserAuthController.register error:" + e);
            return new ResultJSON(-4, "服务器错误");
        }
        Long id = userAuthService.resetPwd(phone, password);
        if (id == -3) {
            return new ResultJSON(-3, "该手机号未注册");
        } else if (id > 0) {
            return new ResultJSON(0, "修改成功");
        } else {
            return new ResultJSON(-1, "修改失败");
        }
    }

    @RequestMapping("/getVerificationCode")
    @ResponseBody
    public JSONObject getVerificationCode (HttpServletRequest request) {
        String phone = request.getParameter("phone");
        if (Validator.isBlank(phone) || !Validator.isMobileNO(phone)) {
            return new ResultJSON(-2, "参数错误");
        }
        boolean status = userAuthService.genVerCode(phone);
        if (status) {
            return new ResultJSON(0, "请求成功");
        } else {
            return new ResultJSON(-1, "请求失败");
        }
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public void test(HttpServletRequest request) {
        sharedJedisUtils.set("aloha", "kongyue", 0);
    }

    @RequestMapping(value = "/test2")
    @ResponseBody
    public JSONObject  test2(HttpServletRequest request) {

        String key = sharedJedisUtils.get("aloha");
        return new ResultJSON(0, key);
    }
}
