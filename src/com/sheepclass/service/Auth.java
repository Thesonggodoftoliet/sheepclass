package com.sheepclass.service;

import com.sheepclass.dao.UserDao;
import com.sheepclass.dao.implement.UserDaoIm;
import com.sheepclass.entity.Users;

public class Auth {
    private UserDao userDao= new UserDaoIm();
    private Users sqluser;
    public int checkPassword(Users user){
        if (user.getEmail().isEmpty())
            sqluser = userDao.getUserByphone(user.getPhone());
        else
            sqluser = userDao.getUserByEmail(user.getEmail());

        if (sqluser.getUserpwd().equals(user.getUserpwd()))
            return sqluser.getUserid();//验证成功
        return 0;//验证失败
    }
}
