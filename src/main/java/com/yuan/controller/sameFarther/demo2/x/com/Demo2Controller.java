package com.yuan.controller.sameFarther.demo2.x.com;

import com.yuan.controller.sameFarther.demo2.x.com.utlis.Demo2Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangy on 2018/11/20.
 */
@Controller("sameFartherDemo2")
@RequestMapping("/sameFarther/demo2")
public class Demo2Controller {

    @Autowired
    private Demo2Tool demo2Tool;

    @RequestMapping("/main")
    public String main(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("ssoCookie")) {
                    String rs = demo2Tool.doGet("http//check.x.com/sameFarther/sso/checkCookie",
                            cookie.getName(), cookie.getValue());
                    if (rs.equals("1")) {
                        return "sameFarther/success2";
                    }
                }
            }
        }
        //这里必须要给完整路径了
        request.setAttribute("gotoURL", "http://demo2.x.com/sameFarther/demo2/main");
        return "sameFarther/login";
    }

}
