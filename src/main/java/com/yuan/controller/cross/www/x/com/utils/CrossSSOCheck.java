package com.yuan.controller.cross.www.x.com.utils;

import org.springframework.stereotype.Component;

/**
 * Created by wangy on 2018/11/20.
 */
@Component("crossSSOCheck")
public class CrossSSOCheck {

    public static final String USERNAME = "user";
    public static final String PASSWORD = "123";

    //检查是否登录：模拟数据库查询出结果
    public boolean checkLogin(String username, String password) {
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            return true;
        }
        return false;
    }

    //检查Cookie中是否有我们自定义的cookie
    public boolean checkCookie(String cookieName, String cookieValue) {
        if (cookieName.equals("ssoCookie") && cookieValue.equals("sso")) {
            return true;
        }
        return false;
    }

}
