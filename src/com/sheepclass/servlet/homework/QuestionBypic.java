package com.sheepclass.servlet.homework;

import com.sheepclass.utils.JwtUtils;
import com.sheepclass.utils.ReciveUtils;
import netscape.javascript.JSException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/QuestionBypic")
public class QuestionBypic extends HttpServlet {

    public QuestionBypic() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("QuestionBypic");
        JSONObject obj = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        PrintWriter out = response.getWriter();
        String token = null;
        try{
            token=obj.getString("token");
        }catch (JSONException e){
            e.printStackTrace();
        }

        if (JwtUtils.verifyToken(token) == 0){
            try{
                msg.put("token",token);
                msg.put("tag",0);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            double time = 0;
            int userid = 0;
            try{
                time = obj.getDouble("time");
                userid = JwtUtils.decodeToken(token);
                token = JwtUtils.createToken(userid);
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}