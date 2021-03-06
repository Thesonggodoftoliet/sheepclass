package com.sheepclass.servlet.homework;

import com.sheepclass.entity.Homework;
import com.sheepclass.entity.Mistakes;
import com.sheepclass.service.Learn;
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

@WebServlet("/homework/getMistakes")
public class getMistakes extends HttpServlet {

    public getMistakes() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("getMistakes");
        JSONObject jsonObject = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        String token = null;
        int userid = 0;
        try{
            token = jsonObject.getString("token");
        }catch (JSONException e){
            e.printStackTrace();
        }

        if (JwtUtils.verifyToken(token) == 0){
            try{
                msg.put("tag",0);
                msg.put("token"," ");
                msg.put("mistakes"," ");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            int courseid = 0;
            try{
                courseid = jsonObject.getInt("courseid");
            }catch (JSONException e){
                e.printStackTrace();
            }
            userid = JwtUtils.decodeToken(token);
            token = JwtUtils.createToken(userid);
            Learn learn = new Learn();
            List<Mistakes> mistakesList = learn.getMistakes(userid,courseid);
            List<Homework> mistakes = new ArrayList<>();
            List<String> coursename = new ArrayList<>();
            for (int i=0;i<mistakesList.size();i++){
                Homework homework = learn.getHomework(mistakesList.get(i).getHomeworkid());
                //System.out.println(homework.getHomeworkid());
                mistakes.add(homework);
                coursename.add(learn.getCourseById(mistakesList.get(i).getCourseid()).getCoursename());
            }
            JSONArray jsonArray = new JSONArray();
            try {
                for (int i = 0; i < mistakes.size(); i++) {
                    JSONObject mistake = new JSONObject();
                    mistake.put("homeworkid",mistakes.get(i).getHomeworkid());
                    mistake.put("content",mistakes.get(i).getContent());
                    mistake.put("correct",mistakes.get(i).getCorrect());
                    mistake.put("A",mistakes.get(i).getA());
                    mistake.put("B",mistakes.get(i).getB());
                    mistake.put("C",mistakes.get(i).getC());
                    mistake.put("D",mistakes.get(i).getD());
                    mistake.put("coursename",coursename.get(i));
                    mistake.put("times",mistakesList.get(i).getWrongtimes());
                    jsonArray.put(mistake);
                }
                //System.out.println("print"+jsonArray);
                msg.put("tag",1);
                msg.put("token",token);
                msg.put("mistakes",jsonArray);
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