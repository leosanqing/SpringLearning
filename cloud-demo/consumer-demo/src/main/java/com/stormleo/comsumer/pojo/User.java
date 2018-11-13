package com.stormleo.comsumer.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private Integer age;
    private Integer sex;
    private Date birthday;
    private Date created;

    private Date updated;


}
