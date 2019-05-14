package com.sheepclass.servlet.personal;

import com.sheepclass.entity.Users;
import com.sheepclass.service.Auth;
import com.sheepclass.service.Infocollect;
import com.sheepclass.utils.JwtUtils;
import com.sheepclass.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/personal/AlterPersonalInfo")
public class AlterPersonalInfo extends HttpServlet {

    public AlterPersonalInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AlterPersonalInfo");
        JSONObject object = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        PrintWriter out = response.getWriter();
        String token=null;
        try{
            token = object.getString("token");
        }catch (JSONException e){
            e.printStackTrace();
        }
        if (JwtUtils.verifyToken(token) == 0){
            try{
                msg.put("tag",0);
                msg.put("token"," ");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            Users users = new Users();
            users.setUserid(JwtUtils.decodeToken(token));
            token = JwtUtils.createToken(users.getUserid());
            try{
                users.setEmail(object.getString("email"));
                users.setPhone(object.getString("phone"));
                users.setUserpwd(object.getString("userpwd"));
                users.setBirthday(object.getLong("birthday"));
                users.setNickname(object.getString("username"));
                users.setSex(object.getInt("sex"));
            }catch (JSONException e){
                e.printStackTrace();
            }
            Infocollect infocollect = new Infocollect();
            infocollect.updateUsers(users);
            try{
                msg.put("tag",1);
                msg.put("token",token);
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