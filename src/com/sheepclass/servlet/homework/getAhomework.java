package com.sheepclass.servlet.homework;

import com.sheepclass.entity.Homework;
import com.sheepclass.entity.Knowledge;
import com.sheepclass.service.Learn;
import com.sheepclass.utils.JwtUtils;
import com.sheepclass.utils.ReciveUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/homework/getAhomework")
public class getAhomework extends HttpServlet {

    public getAhomework() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("getAhomework");
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
                msg.put("tag",0);
                msg.put("token"," ");
                msg.put("homework"," ");
                msg.put("konwledgepoint"," ");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            int userid = JwtUtils.decodeToken(token);
            token = JwtUtils.createToken(userid);
            int homeworkid = 0;
            try{
                homeworkid = object.getInt("homeworkid");
            }catch (JSONException e){
                e.printStackTrace();
            }
            Learn learn = new Learn();
            Homework homework = learn.getHomework(homeworkid);
            JSONObject home  = new JSONObject();
            try{
                home.put("content",homework.getContent());
                home.put("A",homework.getA());
                home.put("B",homework.getB());
                home.put("C",homework.getC());
                home.put("D",homework.getD());
                home.put("correct",homework.getCorrect());
            }catch (JSONException e){
                e.printStackTrace();
            }
            List<Knowledge> knowledges = learn.getKnoledgesById(homework.getSets());
            JSONArray jsonArray = new JSONArray();
            for (int i= 0;i<knowledges.size();i++){
                JSONObject jsonObject = new JSONObject();
                try{
                    jsonObject.put("content",knowledges.get(i).getContent());
                    jsonArray.put(jsonArray);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            try{
                msg.put("tag",1);
                msg.put("token",token);
                msg.put("homework",home);
                msg.put("knowledgepoint",jsonArray);
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