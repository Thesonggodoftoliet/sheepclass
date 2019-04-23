package com.sheepclass.dao.implement;

import com.sheepclass.dao.UserDao;
import com.sheepclass.entity.Users;
import com.sheepclass.utils.JdbcUtils;

public class UserDaoIm implements UserDao {
    @Override
    public Users getUserById(int userid) {
        String sql = "select * from users where userid = ?";
        return (Users)JdbcUtils.getObjectById(Users.class,sql,userid);
    }

    @Override
    public Users getUserByphone(String phone) {
        String sql = "select * from users where phone = ?";
        return (Users)JdbcUtils.getObjectById(Users.class,sql,phone);
    }

    @Override
    public Users getUserByEmail(String email) {
        String sql = "select * from users where email = ?";
        return (Users)JdbcUtils.getObjectById(Users.class,sql,email);
    }

    @Override
    public int addUser(Users user) {
        String sql = "insert into users values(?,?,?,?,?,?,?,?,?,?)";
        return JdbcUtils.executeSQL(sql,user.getNickname(),null,user.getUserpwd(),user.getEmail(),user.getPhone(),user.getSex(),user.getIdentity(),null,user.getBirthday(),user.getRegistTime());
    }

    @Override
    public int setUser(Users user) {
        String sql = "update users set nickname = ?,userpwd =?,email=?,phone=?,sex=?,parentid=?,birthday=?,login_time=?";
        return JdbcUtils.executeSQL(sql,user.getNickname(),user.getUserpwd(),user.getEmail(),user.getPhone(),user.getSex(),user.getParentid(),user.getBirthday());
    }

    @Override
    public int deleteUserById(int userid) {
        String sql = "delete from users where userid = ?";
        return JdbcUtils.executeSQL(sql,userid);
    }
}
