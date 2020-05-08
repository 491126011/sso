package cn.ym.sso.controller;

import cn.ym.sso.config.SsoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("index")
public class IndexController {

    @Autowired
    private SsoConfig ssoConfig;


    @GetMapping
    public String index(Model model,
                        @CookieValue(value = "sso_user",required = false) String ssoCookieValue,
                        @RequestParam(value = "sso_user",required = false) String ssoCookieParams,
                      HttpServletRequest request, HttpServletResponse response){
        if(!StringUtils.isEmpty(ssoCookieParams)){
            Cookie cookie = new Cookie("sso_user",ssoCookieParams);
            response.addCookie(cookie);

            return "index";
        }

        StringBuffer requestURL = request.getRequestURL();
        if(StringUtils.isEmpty(ssoCookieValue)){
            try {
                response.sendRedirect(ssoConfig.getUrl() + ssoConfig.getLoginPath() + "?redirect_url=" + requestURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            model.addAttribute("loginUser","张三");
            return "index";
        }
        return null;
    }



}
