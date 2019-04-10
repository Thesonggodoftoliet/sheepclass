package com.sheepclass.dao;

import com.sheepclass.entity.Users;


public interface UserDao {
    Users getUserById(int userid);
    Users getUserByphone(String phone);
    Users getUserByEmail(String email);
    int addUser(Users user);
    int setUser(Users user);
    int deleteUserById(int userid);
}
