package com.yuan.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangy on 2018/11/20.
 */
@Component
public class SSOCheck {

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
    public boolean checkCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();//获取所有的cookie
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                //是否能找到我们自定义的cookie
                if (cookie.getName().equals("ssoCookie") && cookie.getValue().equals("sso")) {
                    return true;
                }
            }
        }
        return false;
    }

}
