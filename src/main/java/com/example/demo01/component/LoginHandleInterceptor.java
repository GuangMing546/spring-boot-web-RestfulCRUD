package com.example.demo01.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandleInterceptor implements HandlerInterceptor{
    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //通过session来获取Attribute的值
        Object user = request.getSession().getAttribute("loginUser");
        if(user==null){ //如果session里面没有loginUser这个值
            //未登陆，返回登陆页面
            request.setAttribute("msg","没有权限请先登陆"); //给request域中放上错误消息
            request.getRequestDispatcher("/index.html").forward(request,response); //返回登陆页面并显示信息
            return false;
        }else {
            //已登陆，放行请求
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
