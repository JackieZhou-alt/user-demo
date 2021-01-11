package com.baizhi.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object login = session.getAttribute("login");
        if(login != null){
            return true;
        }
        //响应未登录的json串数据
        PrintWriter pw = response.getWriter();
        pw.print("{\"status\":\"not-login\"}");
        pw.flush();
        return false;

    }
}
