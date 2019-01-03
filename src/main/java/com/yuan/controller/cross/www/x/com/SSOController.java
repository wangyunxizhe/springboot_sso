package com.yuan.controller.cross.www.x.com;

import com.yuan.controller.cross.www.x.com.utils.CrossSSOCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangy on 2018/11/20.
 * <p>
 * 跨域SSO控制类
 * 跨域SSO示例：
 * http://www.a.com/xxx/yyy/zzz
 * http://www.b.com/aaa/bbb/ccc
 * 统一登录校验的接口URL====http://www.x.com/cross/sso/checkCookie
 */
@Controller
@RequestMapping("/cross/sso")
public class SSOController {

    @Autowired
    private CrossSSOCheck ssoCheck;

    @GetMapping("/index")
    public String index() {
        return "cross/login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public String doLogin(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "username", required = false) String username,
                          @RequestParam(value = "password", required = false) String password) {
        boolean ok = ssoCheck.checkLogin(username, password);
        String rs = "0";
        if (ok) {
            rs = "1";
        }
        return rs;
    }

    //校验cookie是否符合要求，符合返回1
    @RequestMapping("/checkCookie")
    @ResponseBody
    public String checkCookie(@RequestParam(value = "cookieName", required = false) String cookieName,
                              @RequestParam(value = "cookieValue", required = false) String cookieValue) {
        boolean ok = ssoCheck.checkCookie(cookieName, cookieValue);
        String rs = "0";
        if (ok) {
            rs = "1";
        }
        return rs;
    }

}
