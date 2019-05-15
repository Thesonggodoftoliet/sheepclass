package com.sheepclass.servlet.auth;

import com.sheepclass.entity.Users;
import com.sheepclass.service.Auth;
import com.sheepclass.service.Infocollect;
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
            user.setRegist_Time(jsonObject.getLong("regist_time"));
//            user.setLoginTime(jsonObject.getLong("login_time"));
        }catch (JSONException e){
            e.printStackTrace();
        }
        Auth auth = new Auth();
        Auth auth1 = new Auth(user);
        if (auth1.getUserinfo() == null) {//如果没有注册过
            if (auth.addNewuser(user) != 0) {
                try {
                    user = auth.getUserinfo();
                    msg.put("token", JwtUtils.createToken(user.getUserid()));
                    msg.put("tag", 1);
                    Infocollect infocollect = new Infocollect();
                    infocollect.createView(user.getUserid());//创建错题视图
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    msg.put("token", "");
                    msg.put("tag", 0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }else {
            try{
                msg.put("token"," ");
                msg.put("tag",2);//有重复邮箱或手机号码
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