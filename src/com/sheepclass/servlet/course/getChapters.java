package com.sheepclass.servlet.course;

import com.sheepclass.dao.ChapterDao;
import com.sheepclass.dao.implement.ChapterDaoImpl;
import com.sheepclass.entity.Chapter;
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
import javax.swing.*;

@WebServlet("course/getChapters")
public class getChapters extends HttpServlet {

    public getChapters() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("getChapters");
        JSONObject object = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        PrintWriter out = response.getWriter();
        String token = null;
        int userid = 0;
        try{
            token = object.getString("token");
        }catch (JSONException e){
            e.printStackTrace();
        }

        if (JwtUtils.verifyToken(token) == 0){
            try {
                msg.put("tag", 0);
                msg.put("token"," ");
                msg.put("chapters"," ");
                msg.put("courseid"," ");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            userid = JwtUtils.decodeToken(token);
            token = JwtUtils.createToken(userid);
            int courseid = 0;
            try{
                courseid = object.getInt("courseid");
            }catch (JSONException e){
                e.printStackTrace();
            }
            Learn learn = new Learn();
            List<Chapter> chapters = learn.getChapterByCourse(courseid);
            JSONArray chapterlist = new JSONArray();
            try{
                for(int i = 0;i<chapters.size();i++){
                    JSONObject chapter = new JSONObject();
                    chapter.put("serialnum",chapters.get(i).getSerialnum());
                    chapter.put("chaptername",chapters.get(i).getChaptername());
                    chapterlist.put(chapter);
                }
                msg.put("tag",1);
                msg.put("token",token);
                msg.put("chapters",chapterlist);
                msg.put("courseid",courseid);
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