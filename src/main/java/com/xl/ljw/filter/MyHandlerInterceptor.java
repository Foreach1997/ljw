package com.xl.ljw.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyHandlerInterceptor implements HandlerInterceptor {




    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("----拦截启动----");
        boolean flag = false;
       Object userId = request.getSession().getAttribute("userId");
        System.out.println(userId);
        if(userId==null ||  "".equals(userId)) {
            Cookie A = new Cookie("IsFlag", "false");
            A.setPath("/");
            response.addCookie(A);
            return false;
        }
            Cookie cookie = new Cookie("IsFlag", "true");
            cookie.setPath("/");
            response.addCookie(cookie);
            return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
