package com.jc.campusemploydemo.config;

import com.jc.campusemploydemo.utils.Const;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute(Const.NAME);
        if (null == user){
            response.sendRedirect(request.getContextPath()+"/user/login");
            return false;
        }
       return true;
    }
}
