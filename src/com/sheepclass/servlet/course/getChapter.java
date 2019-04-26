package com.sheepclass.servlet.course;

import com.sheepclass.dao.ChapterDao;
import com.sheepclass.dao.ScheduleDao;
import com.sheepclass.dao.implement.ChapterDaoImpl;
import com.sheepclass.dao.implement.ScheduleDaoimpl;
import com.sheepclass.entity.Chapter;
import com.sheepclass.entity.Schedule;
import com.sheepclass.utils.JwtUtils;
import com.sheepclass.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("course/getChapter")
public class getChapter extends HttpServlet {

    public getChapter() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("getChapter");
        JSONObject object = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        String token = null;
        int courseid = 0;
        float serialnum = 0;
        PrintWriter out = response.getWriter();

        try{
            token = object.getString("token");
        }catch (JSONException e){
            e.printStackTrace();
        }
        int tag = JwtUtils.verifyToken(token);
        if (tag == 0){
            try{
                msg.put("tag",0);
                msg.put("token",token);
                msg.put("chapter"," ");
                msg.put("breakpoint"," ");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            int userid = JwtUtils.decodeToken(token);
            token = JwtUtils.createToken(userid);
            try {
                courseid = object.getInt("courseid");
                serialnum = (float) object.getDouble("serialnum");
            }catch (JSONException e){
                e.printStackTrace();;
            }
            //接受失败
            if (courseid == 0 || serialnum == 0){
                try {
                    msg.put("tag", -1);
                    msg.put("token", token);
                    msg.put("chapter", " ");
                    msg.put("breakpoint", " ");
                    msg.put("isFinish"," ");
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }else {
                Schedule schedule;
                ScheduleDao scheduleDao = new ScheduleDaoimpl();
                schedule = scheduleDao.getScheduleBycourseid(userid,courseid);
                Chapter chapter;
                ChapterDao chapterDao = new ChapterDaoImpl();
                chapter = chapterDao.getChapterByChapterId(serialnum,courseid);
                try{
                    JSONObject chap = new JSONObject();
                    chap.put("chaptername",chapter.getChaptername());
                    chap.put("video",chapter.getVedio());
                    msg.put("tag",1);
                    msg.put("token",token);
                    msg.put("chapter",chap);
                    msg.put("breakpoint",schedule.getBreaktime());
                    msg.put("isFinish",schedule.getFinish());
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