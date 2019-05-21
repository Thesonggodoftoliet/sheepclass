package com.sheepclass.dao;

import com.sheepclass.entity.Users;

import java.util.List;


public interface UserDao {
    Users getUserById(int userid);
    Users getUserByphone(String phone);
    Users getUserByEmail(String email);
    List<Users> getUsersByIdentity(int identity);
    int addUser(Users user);
    int setUser(Users user);
    int updateTotTime(Users user);
    int deleteUserById(int userid);
}
