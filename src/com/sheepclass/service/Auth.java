package com.sheepclass.service;

import com.sheepclass.dao.UserDao;
import com.sheepclass.dao.implement.UserDaoIm;
import com.sheepclass.entity.Users;

public class Auth {
    private UserDao userDao= new UserDaoIm();
    private Users sqluser;

    public  Auth(){

    }
    public Auth(Users user){
        if (user.getUserid() != 0)
            sqluser = userDao.getUserById(user.getUserid());
        else if (user.getEmail().isEmpty())
            sqluser = userDao.getUserByphone(user.getPhone());
        else
            sqluser = userDao.getUserByEmail(user.getEmail());
    }
    public int checkPassword(Users user){
        if (sqluser.getUserpwd().equals(user.getUserpwd()))
            return sqluser.getUserid();//验证成功
        return 0;//验证失败
    }

    public int addNewuser(Users user){
        int tag = userDao.addUser(user);
        sqluser = userDao.getUserByEmail(user.getEmail());
        return tag;
    }

    public Users getUserinfo(){
        return sqluser;
    }
}
