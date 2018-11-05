package com.smart.web;

import com.smart.Service.UserService;
import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class LoginController {
    private UserService userService;

    @RequestMapping(value = {"/","/index.html"})
    public ModelAndView loginPage(){
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request,LoginCommand loginCommand){

        System.out.println("进入。。。。。。。。。。");
        System.out.println("===="+loginCommand.getUserName()+loginCommand.getPassword()+"========");
        boolean isValidUser=userService.hasMatchUser(loginCommand.getUserName(),loginCommand.getPassword());
        System.out.println("========"+isValidUser+"============");
        if(!isValidUser){
            return new ModelAndView("login","error","用户名密码错误");

        }else {
            User user=userService.findUserByUserName(loginCommand.getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user",user);
            return new ModelAndView("main");
        }

    }
    @Autowired
    public void setUserService(UserService userService){
        this.userService=userService;
    }
}
