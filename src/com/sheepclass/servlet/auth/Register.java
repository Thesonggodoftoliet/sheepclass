package com.sheepclass.servlet.auth;

import com.sheepclass.entity.Users;
import com.sheepclass.service.Auth;
import com.sheepclass.utils.JwtUtils;
import com.sheepclass.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/auth/Register")
public class Register extends HttpServlet {

    public Register() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Register");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = ReciveUtils.getObject(request);
        JSONObject msg  = new JSONObject();
        Users user  = new Users();
        try{
            user.setNickname(jsonObject.getString("username"));
            user.setUserpwd(jsonObject.getString("userpwd"));
            user.setEmail(jsonObject.getString("email"));
            user.setPhone(jsonObject.getString("phone"));
            user.setSex(jsonObject.getInt("sex"));
            user.setIdentity(jsonObject.getInt("identity"));
            user.setBirthday(jsonObject.getLong("birthday"));
            user.setRegistTime(jsonObject.getLong("regist_time"));
            user.setLoginTime(jsonObject.getLong("login_time"));
        }catch (JSONException e){
            e.printStackTrace();
        }
        Auth auth = new Auth();
        if (auth.addNewuser(user) !=0) {
            try {
                user = auth.getUserinfo();
                msg.put("token", JwtUtils.createToken(user.getUserid()));
                msg.put("tag",1);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            try{
                msg.put("token","");
                msg.put("tag",0);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        out.print(msg);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}