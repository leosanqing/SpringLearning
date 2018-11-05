package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private final static String MATCH_COUNT_SQL ="SELECT COUNT(*) FROM t_user " +
            "where user_name=? and password=? ";
    private final static String UPDATE_LOGIN_INFO_SQL="update t_user" +
            " set last_visit =?,last_ip=?,credits=? where user_id=?";


    public User findUserByUserName(final String userName){
        final User user =new User();
        String sql="SELECT * FROM t_user" +
                " where user_name=?";
        jdbcTemplate.query(sql, new Object[]{userName},
                new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet resultSet) throws SQLException {
                        user.setUserId(resultSet.getInt(resultSet.getInt("user_id")));
                        user.setUserName(userName);
                        user.setCredits(resultSet.getInt("credits"));
                    }
                });
        return user;
    }

    public void updateLoginInfo(User user){
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL,new Object[]{user.getLastVisit(),user.getLastIp(),user.getCredits(),
                            user.getUserId()});
    }

    public int getMatchCount(String userName ,String password){
        System.out.println("=====进入Dao=====");
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL,new Object[]{userName,password},Integer.class);


    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
