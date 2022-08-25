package com.xxxx.springboot.controller;

import com.xxxx.springboot.AccessToken.PrividerToken;
import com.xxxx.springboot.Privider.AccessPrivider;
import com.xxxx.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccessTokenController {
    @Autowired
    private PrividerToken prividerToken;

    @Autowired
    private AccessPrivider accessPrivider;

    @Autowired
    private User user;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {

        prividerToken.setClient_id("db79b7dee204deb521cde6a973c14aec9b2dab75b1c87d096be7027d407a9399");
        prividerToken.setCode(code);
        prividerToken.setRedirect_uri("http://localhost:8989/callback");
        prividerToken.setState(state);
        prividerToken.setClient_Secret("e0c6105c4e0a8798fd7652b1a428fbf038daa6027f0a1ba67b05c781fe98b566");
        String Token = accessPrivider.getToken(prividerToken);
        //调用接口获取用户信息
        user = accessPrivider.getUser(Token);
        System.out.println(user);

        return "redirect:/hello";
    }


}
