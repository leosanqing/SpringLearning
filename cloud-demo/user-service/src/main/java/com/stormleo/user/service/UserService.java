package com.stormleo.user.service;

import com.stormleo.user.mapper.UserMapper;
import com.stormleo.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User selectUser(Long id){
        return userMapper.selectByPrimaryKey(id);

    }
}
