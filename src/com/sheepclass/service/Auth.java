package com.sheepclass.service;

import com.sheepclass.dao.LearninginfoDao;
import com.sheepclass.dao.UserDao;
import com.sheepclass.dao.implement.LearninginfoDaoimple;
import com.sheepclass.dao.implement.UserDaoIm;
import com.sheepclass.entity.Learninginfo;
import com.sheepclass.entity.Users;

import java.util.*;

public class Auth {
    private UserDao userDao= new UserDaoIm();
    private Users sqluser;
    private LearninginfoDao learninginfoDao = new LearninginfoDaoimple();
    private static Calendar calendar = Calendar.getInstance();

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
            logintime(sqluser.getUserid());//设置登录时间
            return sqluser.getUserid();//验证成功
        }
        return 0;//验证失败
    }

    public int addNewuser(Users user){
        int tag = userDao.addUser(user);
        sqluser = userDao.getUserByEmail(user.getEmail());
        logintime(sqluser.getUserid());
        return tag;
    }

    public Users getUserinfo(){
        return sqluser;
    }

    public int logintime(int userid){
        List<Learninginfo> learninginfos = learninginfoDao.getLearningtime(userid,calendar.getTimeInMillis());
        Collections.sort(learninginfos);
        Learninginfo learninginfo = learninginfos.get(0);
        Date date = new Date(learninginfo.getLogintime());
        int tag;
        if (date.getMonth() == calendar.getTime().getMonth() && date.getDate() == calendar.getTime().getDate()){//当天多次登录
            learninginfo.setLogintime(calendar.getTimeInMillis());
            tag = learninginfoDao.updateInfo(learninginfo);
        }else {
            learninginfo.setLogintime(calendar.getTimeInMillis());
            learninginfo.setLogouttime(0);
            tag = learninginfoDao.addInfo(learninginfo);
        }
        return tag;
    }

    public int verifyIdentity(int userid){
        UserDao userDao = new UserDaoIm();
        return userDao.getUserById(userid).getIdentity();
    }
}
