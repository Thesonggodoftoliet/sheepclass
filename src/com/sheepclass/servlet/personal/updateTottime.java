package com.sheepclass.servlet.personal;

import com.sheepclass.entity.Users;
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

@WebServlet("/personal/updateTottime")
public class updateTottime extends HttpServlet {

    public updateTottime() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("updateTottime");
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
                msg.put("token","");
                msg.put("tag",0);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            Users users = new Users();
            int userid = 0;
            userid = JwtUtils.decodeToken(token);
            token = JwtUtils.createToken(userid);
            try{
                users.setUserid(userid);
                users.setTot_time(object.getInt("tot_time"));
            }catch (JSONException e){
                e.printStackTrace();
            }
            Infocollect infocollect = new Infocollect();
            if (infocollect.caculateTime(users) == 1){
                try{
                    msg.put("token",token);
                    msg.put("tag",1);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }else {
                try{
                    msg.put("token",token);
                    msg.put("tag",2);
                }catch (JSONException e){
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