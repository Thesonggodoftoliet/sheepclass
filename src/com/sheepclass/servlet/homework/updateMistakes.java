package com.sheepclass.servlet.homework;

import com.sheepclass.entity.Mistakes;
import com.sheepclass.service.Infocollect;
import com.sheepclass.utils.JwtUtils;
import com.sheepclass.utils.ReciveUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/homework/updateMistakes")
public class updateMistakes extends HttpServlet {

    public updateMistakes() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("updateMistakes");
        JSONObject object = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        String token = null;
        try{
            token = object.getString("token");
        }catch (JSONException e){
            e.printStackTrace();
        }
        if (JwtUtils.verifyToken(token)==0){
            try {
                msg.put("token",token);
                msg.put("tag",0);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            int userid = JwtUtils.decodeToken(token);
            token = JwtUtils.createToken(userid);
            Infocollect infocollect = new Infocollect();
            try{
                JSONArray mistakelist = object.getJSONArray("mistakes");
                for (int i =0;i<mistakelist.length();i++){
                    Mistakes mistake = new Mistakes();
                    JSONObject jsonObject = mistakelist.getJSONObject(i);
                    mistake.setUserid(userid);
                    mistake.setHomeworkid(jsonObject.getInt("homeworkid"));
                    mistake.setCourseid(jsonObject.getInt("courseid"));
                    mistake.setReviewtimes(jsonObject.getInt("reviewtimes"));
                    mistake.setWrongtimes(jsonObject.getInt("wrongtimes"));
                    infocollect.addMistakes(mistake);
                    msg.put("token",token);
                    msg.put("tag",1);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        PrintWriter out = response.getWriter();
        out.print(msg);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}