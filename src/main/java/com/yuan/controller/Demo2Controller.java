package com.yuan.controller;

import com.yuan.utils.SSOCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangy on 2018/11/20.
 */
@Controller
@RequestMapping("/demo2")
public class Demo2Controller {

    @Autowired
    private SSOCheck ssoCheck;

    @RequestMapping("/main")
    public String main(HttpServletRequest request) {
        if (ssoCheck.checkCookie(request)) {
            return "success2";
        }
        request.setAttribute("gotoURL", "/demo2/main");
        return "login";
    }

}
