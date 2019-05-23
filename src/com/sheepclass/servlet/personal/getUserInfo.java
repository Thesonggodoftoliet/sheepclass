package com.sheepclass.servlet.personal;

import com.sheepclass.entity.Users;
import com.sheepclass.service.Auth;
import com.sheepclass.utils.JwtUtils;
import com.sheepclass.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sound.midi.Receiver;

@WebServlet("/personal/getUserInfo")
public class getUserInfo extends HttpServlet {

    public getUserInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("getUserInfo");
        JSONObject object = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        PrintWriter out = response.getWriter();
        String token = null;
        try{
            token = object.getString("token");
        }catch (JSONException e){
            e.printStackTrace();
        }

        if (JwtUtils.verifyToken(token) == 0){
            try{
                msg.put("token"," ");
                msg.put("tag",0);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            int userid = 0;
            int choice = 0;
            userid = JwtUtils.decodeToken(token);
            try{
                choice = object.getInt("choice");
            }catch (JSONException e){
                e.printStackTrace();
            }
            token = JwtUtils.createToken(userid);
            Users users = new Users();
            users.setUserid(userid);
            Auth auth = new Auth(users);
            users = auth.getUserinfo();
            if (choice == 2){
                users.setUserid(users.getParentid());
                auth = new Auth(users);
                users = auth.getUserinfo();
                try {
                    msg.put("username", users.getNickname());
                    msg.put("birthday", users.getBirthday());
                    msg.put("totaltime", users.getTot_time());
                    msg.put("registtime", users.getRegist_Time());
                    System.out.println(users.getRegist_Time());
                    msg.put("sex", users.getSex());
                    msg.put("identity", users.getIdentity());
                    msg.put("email", users.getEmail());
                    msg.put("phone", users.getPhone());
                    msg.put("parentid", users.getParentid());
                    msg.put("token", token);
                    if (users == null)
                        msg.put("tag", 2);
                    else
                        msg.put("tag",3);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    msg.put("username", users.getNickname());
                    msg.put("birthday", users.getBirthday());
                    msg.put("totaltime", users.getTot_time());
                    msg.put("registtime", users.getRegist_Time());
                    System.out.println(users.getRegist_Time());
                    msg.put("sex", users.getSex());
                    msg.put("identity", users.getIdentity());
                    msg.put("email", users.getEmail());
                    msg.put("phone", users.getPhone());
                    msg.put("parentid", users.getParentid());
                    msg.put("token", token);
                    msg.put("tag", 1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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