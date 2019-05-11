package com.sheepclass.servlet.course;

import com.sheepclass.entity.Schedule;
import com.sheepclass.service.Infocollect;
import com.sheepclass.utils.JwtUtils;
import com.sheepclass.utils.ReciveUtils;
import net.sf.json.JSON;
import org.bytedeco.javacpp.tools.Info;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/course/updateBreakTime")
public class updateBreakTime extends HttpServlet {

    public updateBreakTime() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("updateBreakTime");
        JSONObject object = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        PrintWriter out = response.getWriter();
        String token = null;
        try{
            token=object.getString("token");
        }catch (JSONException e){
            e.printStackTrace();
        }

        if (JwtUtils.verifyToken(token) == 0) {
            try {
                msg.put("tag", 0);
                msg.put("token", " ");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            int userid;
            userid = JwtUtils.decodeToken(token);
            token = JwtUtils.createToken(userid);
            Infocollect infocollect = new Infocollect();
            float breaktime=0;
            int finish=0;
            float serialnum=0;
            int courseid=0;
            try{
                breaktime = object.getInt("breaktime");
                finish = object.getInt("isFinish");
                courseid = object.getInt("courseid");
                serialnum =(float) object.getDouble("serialnum");
            }catch (JSONException e){
                e.printStackTrace();
            }
            Schedule schedule = new Schedule();
            schedule.setBreaktime(breaktime);
            schedule.setCourseid(courseid);
            schedule.setFinish(finish);
            schedule.setUserid(userid);
            schedule.setSerialnum(serialnum);
            if (infocollect.setSchedule(schedule)==0){
                try{
                    msg.put("tag",2);
                    msg.put("token",token);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }else {
                try{
                    msg.put("tag",1);
                    msg.put("token",token);
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