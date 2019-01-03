package com.yuan.controller.sameFarther.check.x.com;

import com.yuan.controller.sameFarther.check.x.com.utlis.SameFartherSSOCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangy on 2018/11/20.
 * <p>
 * 同父域SSO控制类：
 * 因为不能同时启动3个tomcat了实现同父域url，为模拟同父域效果，建了3个包模拟3个工程，所以会产生部分重复代码，
 * 但在真实情况下，这些重复代码都会在不同项目中。
 * 同父域SSO示例（父域相同都是.x.com）：
 * http://demo1.x.com/sameFarther/demo1/main
 * http://demo2.x.com/sameFarther/demo2/main
 * 统一登录校验的接口URL====http://check.x.com/sameFarther/sso/checkCookie
 */
@Controller
@RequestMapping("/sameFarther/sso")
public class SSOController {

    @Autowired
    private SameFartherSSOCheck sameFartherSsoCheck;

    @GetMapping("/index")
    public String index() {
        return "sameFarther/login";
    }

    @PostMapping("/doLogin")
    public String doLogin(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "username", required = false) String username,
                          @RequestParam(value = "password", required = false) String password) {
        boolean ok = sameFartherSsoCheck.checkLogin(username, password);
        if (ok) {
            String gotoURL = request.getParameter("gotoURL");
            System.out.println("gotoURL========" + gotoURL);
            Cookie cookie = new Cookie("ssoCookie", "sso");
            cookie.setDomain(".x.com");//同父域SSO的关键
            cookie.setPath("/");//将Cookie路径设置到父域的顶层
            response.addCookie(cookie);
            return "redirect:" + gotoURL;
        }
        return null;
    }

    //校验cookie是否符合要求，符合返回1
    @RequestMapping("/checkCookie")
    @ResponseBody
    public String checkCookie(@RequestParam(value = "cookieName", required = false) String cookieName,
                              @RequestParam(value = "cookieValue", required = false) String cookieValue) {
        boolean ok = sameFartherSsoCheck.checkCookie(cookieName, cookieValue);
        String rs = "0";
        if (ok) {
            rs = "1";
        }
        return rs;
    }

}
