package com.sheepclass.servlet.personal;

import com.sheepclass.entity.Course;
import com.sheepclass.entity.Knowledge;
import com.sheepclass.service.Auth;
import com.sheepclass.service.InfoGet;
import com.sheepclass.service.Infocollect;
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

@WebServlet("/personal/getStuInfo")
public class getStuInfo extends HttpServlet {

    public getStuInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("getStuInfo");
        JSONObject object  = ReciveUtils.getObject(request);
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
                msg.put("token"," ");
                msg.put("tag",0);
                msg.put("times",0);
                msg.put("tot_time",0);
                msg.put("knowledges"," ");
                msg.put("courses"," ");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            int userid = JwtUtils.decodeToken(token);
            token = JwtUtils.createToken(userid);
            Auth auth = new Auth();
            if (auth.verifyIdentity(userid) == 3)
                userid = auth.getChild(userid);
            InfoGet infoGet = new InfoGet();
            List<Course> courses = infoGet.getMosterrorCourse(userid);
            List<Knowledge> knowledges = infoGet.getMostwrongKnowledges(userid);
            try{
                JSONArray courselist = new JSONArray();
                JSONArray klist = new JSONArray();
                for (int i =0;i<courses.size();i++){
                    JSONObject jo = new JSONObject();
                    jo.put("coursename",courses.get(i).getCoursename());
                    jo.put("img",courses.get(i).getImg());
                    jo.put("subject",courses.get(i).getSubject());
                    jo.put("courseid",courses.get(i).getCourseid());
                    courselist.put(jo);
                }
                for (int j =0;j<knowledges.size();j++){
                    JSONObject jo = new JSONObject();
                    jo.put("kid",knowledges.get(j).getKnowledgeid());
                    jo.put("content",knowledges.get(j).getContent());
                    jo.put("level",knowledges.get(j).getLevel());
                    klist.put(jo);
                }
                msg.put("token",token);
                msg.put("tag",1);
                msg.put("times",infoGet.getWeeklogintimes(userid));
                msg.put("tot_time",infoGet.getWeekTottime(userid));
                msg.put("knowledges",klist);
                msg.put("courses",courselist);
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