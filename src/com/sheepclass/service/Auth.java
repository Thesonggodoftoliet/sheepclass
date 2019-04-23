package com.sheepclass.service;

import com.sheepclass.dao.UserDao;
import com.sheepclass.dao.implement.UserDaoIm;
import com.sheepclass.entity.Users;

import java.util.Date;

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
        if (sqluser.getUserpwd().equals(user.getUserpwd())) {
            Date lastlogin = new Date(sqluser.getLoginTime());
            Date now = new Date();
            if (lastlogin.getDate() != now.getDate()){//如果不是当天登陆
                if(now.getDay()== 1)//周一清空七天登陆
                    sqluser.setLogin_times(1);
                else
                    sqluser.setLogin_times(sqluser.getLogin_times()+1);
            }
            sqluser.setLoginTime(user.getLoginTime());//更新时间
            userDao.setUser(sqluser);
            return sqluser.getUserid();//验证成功
        }
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
