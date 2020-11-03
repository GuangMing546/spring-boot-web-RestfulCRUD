package com.example.demo01.component;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //通过request来获取请求参数
        String l = request.getParameter("l");
        //先拿一个默认locale(区域信息)
        Locale locale = Locale.getDefault();
        //用thymeleaf引擎模板提供的工具来看看有没有获取到参数，有的话就重构一下locale咯
        if (!StringUtils.isEmpty(l)){
            String[] split = l.split("_");
            locale=new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
