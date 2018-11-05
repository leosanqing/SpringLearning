package com.smart.dao;

import com.smart.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDao {
    private JdbcTemplate jdbcTemplate;
    private  final static String INSERT_LOGIN_LOG_SQL="INSERT INTO " +
            "t_login_log(user_id,ip,login_datetime) values(?,?,?)";
    public void inserLoginLog(LoginLog loginLog){
        Object[] params={loginLog.getUserId(),loginLog.getIp(),loginLog.getLoginDate()

        };
        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL,params);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

}
