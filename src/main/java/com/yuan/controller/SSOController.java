package com.yuan.controller;

import com.yuan.utils.SSOCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangy on 2018/11/20.
 * <p>
 * 同域SSO控制类
 */
@Controller
@RequestMapping("/sso")
public class SSOController {

    @Autowired
    private SSOCheck ssoCheck;

    @GetMapping("/index")
    public String index() {
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "username", required = false) String username,
                          @RequestParam(value = "password", required = false) String password) {
        boolean ok = ssoCheck.checkLogin(username, password);
        if (ok) {
            String gotoURL = request.getParameter("gotoURL");
            System.out.println("gotoURL========" + gotoURL);
            Cookie cookie = new Cookie("ssoCookie", "sso");
            cookie.setPath("/");//将Cookie路径设置到顶层
            response.addCookie(cookie);
            return "redirect:" + gotoURL;
        }
        return null;
    }

}
