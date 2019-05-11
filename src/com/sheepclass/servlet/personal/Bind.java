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
import javax.swing.*;

@WebServlet("/personal/Bind")
public class Bind extends HttpServlet {

    public Bind() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Bind");
        JSONObject object = ReciveUtils.getObject(request);
        JSONObject msg =new JSONObject();
        PrintWriter out = response.getWriter();
        String token=null;
        try{
            token=object.getString("token");
        }catch (JSONException e){
            e.printStackTrace();
        }
        if (JwtUtils.verifyToken(token) == 0){
            try{
                msg.put("tag",0);
                msg.put("token",token);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else{
            int useridfromtoken = 0;
            int userid = 0;
            try{
                useridfromtoken = JwtUtils.decodeToken(token);
                userid = object.getInt("userid");
            }catch (JSONException e){
                e.printStackTrace();
            }
            Users temp = new Users();
            temp.setUserid(userid);
            Auth auth = new Auth(temp);
            if (auth.verifyIdentity(useridfromtoken) == auth.verifyIdentity(userid)){
                try{
                    msg.put("tag",2);//身份相同
                    msg.put("token",token);
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }else if (auth.verifyIdentity(userid) == 2){
                Users user = auth.getUserinfo();
                if (user.getParentid()!=0){
                    try{
                        msg.put("tag",3);//已被绑定
                        msg.put("token",token);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }else {
                    user.setParentid(userid);
                    Infocollect infocollect = new Infocollect();
                    infocollect.updateUsers(user);
                    try{
                        msg.put("tag",1);
                        msg.put("token",token);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
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