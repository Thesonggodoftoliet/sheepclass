package com.sheepclass.dao.implement;

import com.sheepclass.dao.UserDao;
import com.sheepclass.entity.Users;
import com.sheepclass.utils.JdbcUtils;

import java.util.List;

public class UserDaoIm implements UserDao {
    @Override
    public Users getUserById(int userid) {
        String sql = "select * from users where userid = ?";
        return (Users)JdbcUtils.getObject(Users.class,sql,userid);
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
    public List<Users> getUsersByIdentity(int identity) {
        String sql = "select * from users where identity ="+identity;
        return JdbcUtils.getList(Users.class,sql);
    }

    @Override
    public int addUser(Users user) {
        String sql = "insert into users values(?,?,?,?,?,?,?,?,?,?,?)";
        return JdbcUtils.executeSQL(sql,user.getNickname(),null,user.getUserpwd(),user.getEmail(),user.getPhone(),user.getSex(),user.getIdentity(),0,user.getBirthday(),user.getRegist_Time(),0);
    }

    @Override
    public int setUser(Users user) {
        String sql = "update users set nickname = ?,userpwd =?,email=?,phone=?,sex=?,parentid=?,birthday=? where userid = ?";
        return JdbcUtils.executeSQL(sql,user.getNickname(),user.getUserpwd(),user.getEmail(),user.getPhone(),user.getSex(),user.getParentid(),user.getBirthday(),user.getUserid());
    }

    @Override
    public int updateTotTime(Users user) {
        String sql = "update users set tottime = ? where userid = ?";
        return JdbcUtils.executeSQL(sql,user.getTot_time(),user.getUserid());
    }

    @Override
    public int deleteUserById(int userid) {
        String sql = "delete from users where userid = ?";
        return JdbcUtils.executeSQL(sql,userid);
    }
}
