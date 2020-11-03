package com.example.demo01.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginControl {
//    @DeleteMapping
//    @GetMapping
//    @PutMapping
//    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        if (!StringUtils.isEmpty(username)&&"123".equals(password)){
            //HttpSession用来做登陆检查，登陆好的用户，我们可以用session来记录一下
            session.setAttribute("loginUser",username);
            //登陆成功
            //return "dashboard";
            //为了解决登陆进去后，刷新页面表单重复提交饿问题，我们用重定向来retrun
            return "redirect:/main.html";
        }else {
            //登陆失败
            map.put("msg","用户名密码错误");
            return "login";
        }


    }

}
