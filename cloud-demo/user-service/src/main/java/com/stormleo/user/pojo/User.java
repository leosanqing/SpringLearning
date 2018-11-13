package com.stormleo.user.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Table(name="t_user")
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String username;
    private String password;
    private Integer age;
    private Integer sex;
    private Date birthday;
    private Date created;

    @Transient
    private Date updated;


}
