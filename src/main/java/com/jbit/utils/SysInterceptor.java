package com.jbit.utils;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SysInterceptor implements HandlerInterceptor {

    /*进入请求方法之前*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //目前 session dev(userdev) 和  backend（backuser） 两个
        //获取session(dev)
        Object devuser = request.getSession().getAttribute("devuser");
        Object backuser = request.getSession().getAttribute("backuser");
        if (devuser!=null||backuser!=null){
            return  true;
        }
        response.sendRedirect("/index.jsp");
        /*if(request.getRequestURI().startsWith("/dev")){
            Object devuser = request.getSession().getAttribute("devuser");
            if (devuser!=null){
                return true;
            }
            response.sendRedirect("/jsp/devlogin.jsp");
        }else if (request.getRequestURI().startsWith("/backuser")){
            Object backuser = request.getSession().getAttribute("backuser");
            if (backuser!=null){
                return true;
            }
            response.sendRedirect("/jsp/backendlogin.jsp");
        }else {
            Object devuser = request.getSession().getAttribute("devuser");
            Object backuser = request.getSession().getAttribute("backuser");
            if (devuser!=null||backuser!=null){
                return  true;
            }
        }*/
        return false;
    }
}
