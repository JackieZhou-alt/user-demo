package com.baizhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class loginController {
    @RequestMapping("login")
    @ResponseBody
    public Map login(String username, String password, HttpSession session){
        Map map = new HashMap();
        if("zhoujy".equals(username) && "123456".equals(password)){
            session.setAttribute("login",true);
            map.put("status","success");
        }else{
            map.put("status","failed");
        }
        return map;
    }


}
