package com.yuan.controller.cross.www.a.com;

import com.yuan.controller.cross.www.a.com.utils.Demo1Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangy on 2018/11/20.
 */
@Controller
@RequestMapping("/crossdemo1")
public class Demo1Controller {

    @Autowired
    private Demo1Tool demo1Tool;

    @RequestMapping("/doLogin")
    public String doLogin(HttpServletRequest request,
                          @RequestParam(value = "username", required = false) String username,
                          @RequestParam(value = "password", required = false) String password) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        //后台统一去http://www.x.com/cross/sso/doLogin下请求登录，获取登录结果
        String rs = demo1Tool.doGet("http://www.x.com/cross/sso/doLogin", map);
        if (rs.equals("1")) {
            //登录成功，将写入cookie的url通过list传给前台iframe，自动触发写入cookie的url。
            //如有再多需要sso的url，在list中追加
            List<String> hiddenURL = new ArrayList<>();
            hiddenURL.add("http://www.a.com/crossdemo1/addCookie");
            hiddenURL.add("http://www.b.com/crossdemo2/addCookie");
            request.setAttribute("hiddenURL", hiddenURL);
            return "cross/success1";
        }
        return "cross/login";
    }

    @RequestMapping("/main")
    public String main(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("ssoCookie")) {
                    Map<String, String> map = new HashMap<>();
                    map.put("cookieName", cookie.getName());
                    map.put("cookieValue", cookie.getValue());
                    String rs = demo1Tool.doGet("http//www.x.com/cross/sso/checkCookie", map);
                    if (rs.equals("1")) {
                        return "cross/success1";
                    }
                }
            }
        }
        //给form表单中的path变量传值
        request.setAttribute("path", "crossdemo1");
        //这里必须要给完整路径了
        request.setAttribute("gotoURL", "http://www.a.com/crossdemo1/main");
        return "cross/login";
    }

    /**
     * 跨域时设置cookie的方法
     */
    @RequestMapping("/addCookie")
    public void addCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("ssoCookie", "sso");
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
