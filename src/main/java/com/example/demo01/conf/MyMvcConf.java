package com.example.demo01.conf;

import com.example.demo01.component.LoginHandleInterceptor;
import com.example.demo01.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

@Configuration
//@EnableWebMvc //这个注解是让你全面接管springMVC的配置，springboot就不会再帮你配置
public class MyMvcConf implements WebMvcConfigurer {
    //测试功能
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/haha").setViewName("success");
    }

    /*下面开始是真正的代码实现功能*/
    @Bean //方法返回值会被作为组件注入到容器中
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            //注册视图解析器
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                //这里在踩坑了，忘记了加/,在做拦截器功能的时候访问main.html会出现500的报错
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                //对于静态资源：*.cs  *.js
//                //springboot已经做好静态资源的映射，不用我们管了
//                registry.addInterceptor(new LoginHandleInterceptor()).addPathPatterns("/**")
//                        .excludePathPatterns("/","/index.html","/user/login"); //在做拦截器练习的时候这里在在index.html前面忘记了加/
//            }
        };
        return webMvcConfigurer;
    }

    //国际化功能
    //注意这里的方法名字一定要是localeResolver，不然会失效
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }


}
