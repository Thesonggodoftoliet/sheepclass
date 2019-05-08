package com.sheepclass.servlet.homework;

import com.sheepclass.entity.Homework;
import com.sheepclass.entity.Knowledge;
import com.sheepclass.service.Infocollect;
import com.sheepclass.service.Learn;
import com.sheepclass.utils.*;
import netscape.javascript.JSException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/homework/QuestionBypic")
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
            String path = null;
            int userid = 0;
            int courseid=0;
            try{
                time = obj.getDouble("time");
                userid = JwtUtils.decodeToken(token);
                token = JwtUtils.createToken(userid);
                courseid = obj.getInt("courseid");
                path = obj.getString("path");
                String photopath=getPhoto.grabberVideoFramer(time,path);
                String content = pickWord.pickWordString(photopath);
                Infocollect infocollect =new Infocollect();
                infocollect.addMistakes(courseid,content,userid);
                msg.put("token",token);
                msg.put("tag",1);

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