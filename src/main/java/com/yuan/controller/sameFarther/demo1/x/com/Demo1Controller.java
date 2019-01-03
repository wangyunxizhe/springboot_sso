package com.yuan.controller.sameFarther.demo1.x.com;

import com.yuan.controller.sameFarther.demo1.x.com.utlis.Demo1Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangy on 2018/11/20.
 */
@Controller
@RequestMapping("/sameFarther/demo1")
public class Demo1Controller {

    @Autowired
    private Demo1Tool demo1Tool;

    @RequestMapping("/main")
    public String main(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("ssoCookie")) {
                    String rs = demo1Tool.doGet("http//check.x.com/sameFarther/sso/checkCookie",
                            cookie.getName(), cookie.getValue());
                    if (rs.equals("1")) {
                        return "sameFarther/success2";
                    }
                }
            }
        }
        //这里必须要给完整路径了
        request.setAttribute("gotoURL", "http://demo1.x.com/sameFarther/demo1/main");
        return "sameFarther/login";
    }

}
