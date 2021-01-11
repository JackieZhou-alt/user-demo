package com.baizhi.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessControlAllowFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin","http://localhost:8081");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        httpServletResponse.setHeader("Access-Control-Allow-Headers","Content-Type,Origin,Accept");
        httpServletResponse.setHeader("Access-Control-Allow-Methods","POST,GET,PUT,DELETE,TRACE,OPTIONS");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {    }
}
