package com.sheepclass.servlet.course;

import com.sheepclass.entity.Course;
import com.sheepclass.service.InfoGet;
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

@WebServlet("/course/getCourse")
public class getCourse extends HttpServlet {

    public getCourse() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("getCourse");
        PrintWriter out = response.getWriter();
        JSONObject msg = new JSONObject();
        JSONObject object = ReciveUtils.getObject(request);
        String token = null;
        String subject = null;
        Learn learn = new Learn();
        List<Course> courseList = null;
        JSONArray jsonArray;
        try{
            token = object.getString("token");
        }catch (JSONException e){
            e.printStackTrace();
        }
        int tag = JwtUtils.verifyToken(token);
        int userid = JwtUtils.decodeToken(token);
        if (tag == 1){
            try{
                subject = object.getString("subject");
            }catch (NullPointerException e){
                e.printStackTrace();
            }catch (JSONException e){
                e.printStackTrace();
            }
            if (subject.isEmpty()){
                courseList = learn.getAllcourse();
                jsonArray = new JSONArray(courseList);
                token = JwtUtils.createToken(userid);
            }else {
                courseList = learn.getCoursesBysub(subject);
                jsonArray = new JSONArray();
                for (int i=0;i<courseList.size();i++){
                    try{
                        JSONObject course =new JSONObject();
                        course.put("courseid",courseList.get(i).getCourseid());
                        course.put("coursename",courseList.get(i).getCoursename());
                        course.put("subject",courseList.get(i).getSubject());
                        course.put("info",courseList.get(i).getInfo());
                        course.put("img",courseList.get(i).getImg());
                        jsonArray.put(course);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                token = JwtUtils.createToken(userid);
            }
            try{
                msg.put("tag",1);
                msg.put("course",jsonArray);
                msg.put("token",token);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            try {
                msg.put("tag", 0);
                msg.put("course", " ");
                msg.put("token", " ");
            }catch (JSONException  e){
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