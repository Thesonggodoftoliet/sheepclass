package com.sheepclass.servlet.homework;

import com.sheepclass.entity.Chapter;
import com.sheepclass.entity.Homework;
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

@WebServlet("homework/getHomework")
public class getHomework extends HttpServlet {

    public getHomework() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("getHomework");
        JSONObject object = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        String token = null;
        int userid = 0;
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
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            int courseid  = 0;
            float serialnum = 0;
            try{
                courseid = object.getInt("courseid");
                serialnum = (float) object.getDouble("serialnum");
            }catch (JSONException e){
                e.printStackTrace();
            }
            userid = JwtUtils.decodeToken(token);
            token = JwtUtils.createToken(userid);
            Learn learn = new Learn();
            Chapter chapter = learn.getChapter(serialnum,courseid);
            List<Homework> homeworks = learn.generateHomework(chapter.getSets());
            JSONArray jsonArray = new JSONArray();
            try{
                for (int i = 0;i<homeworks.size();i++){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("homeworkid",homeworks.get(i).getHomeworkid());
                    jsonObject.put("A",homeworks.get(i).getA());
                    jsonObject.put("B",homeworks.get(i).getB());
                    jsonObject.put("C",homeworks.get(i).getC());
                    jsonObject.put("D",homeworks.get(i).getD());
                    jsonObject.put("content",homeworks.get(i).getContent());
                    jsonObject.put("correct",homeworks.get(i).getCorrect());
                    jsonArray.put(jsonObject);
                }
                msg.put("tag",1);
                msg.put("token",token);
                msg.put("homework",jsonArray);
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