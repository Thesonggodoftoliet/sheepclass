package com.sheepclass.servlet.course;

import com.sheepclass.entity.Chapter;
import com.sheepclass.entity.Schedule;
import com.sheepclass.service.Learn;
import com.sheepclass.utils.JwtUtils;
import com.sheepclass.utils.ReciveUtils;
import com.sheepclass.utils.getPhoto;
import com.sheepclass.utils.pickWord;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.lang.Double.valueOf;

@WebServlet(name = "doScreenshot")
public class doScreenshot extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("getScreenshot");
        //获取接收到的JSON
        JSONObject object = ReciveUtils.getObject(request);
        //要传过去的JSON
        JSONObject msg = new JSONObject();

        //获取TOKEN
        String token = null;
        //get video route
        String videoFileName=null;
        //获取暂停的时间
        String curTime=null;
        Double curTimeD=null;

        PrintWriter out = response.getWriter();

        try{
            token = object.getString("token");
        }catch (JSONException e){
            e.printStackTrace();
        }
        int tag = JwtUtils.verifyToken(token);
        //TOKEN不对
        if (tag == 0){
            try{
                msg.put("tag",0);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else { //TOKEN对的
            int userid = 0;
            userid = JwtUtils.decodeToken(token);
            token = JwtUtils.createToken(userid);
            try {
                videoFileName = object.getString("videoFileName");
                curTime = object.getString("curTime");
                curTimeD = valueOf(curTime);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //获取照片
            String photoName = getPhoto.grabberVideoFramer(curTimeD, videoFileName);

            //提取文字,已经转换成了JSON数组
            /****这里我不知道要把这个数据传给谁耶，这个获取到了文字之后呢,应该拿去给结巴分词，然后传给数据库；前端没必要知道这段文字***/
            String wordlist = pickWord.pickWordString(photoName);

            System.out.println("成功获得文字 "+wordlist);

            try{
                msg.put("tag",1);
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
        out.print(msg);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
