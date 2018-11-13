package com.stormleo.user.web;

import com.stormleo.user.pojo.User;
import com.stormleo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class Controller {
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User hello(@PathVariable("id") Long id){
        return userService.selectUser(id);
    }
}
