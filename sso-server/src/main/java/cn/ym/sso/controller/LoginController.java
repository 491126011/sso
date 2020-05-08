package cn.ym.sso.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("login")
    public String login(Model model,String redirect_url, @CookieValue(required = false,value = "sso_user") String ssoUser, HttpServletResponse response){
        System.out.println("login...");
        //判断之前是否登录过
        if(!StringUtils.isEmpty(ssoUser)){
            try {
                response.sendRedirect(redirect_url + "?sso_user=" + ssoUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else {
            model.addAttribute("redirect_url",redirect_url);
            return "login";
        }
    }

    @PostMapping
    public void doLogin(String userName,String password,String redirect_url,HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        map.put("userName",userName);
        map.put("email",userName + "@qq.com");

        String token = UUID.randomUUID().toString().replace("-","");
        redisTemplate.opsForValue().set(token,JSONObject.toJSONString(map));

        Cookie cookie = new Cookie("sso_user",token);
        response.addCookie(cookie);

        try {
            response.sendRedirect(redirect_url + "?sso_user" + token);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
