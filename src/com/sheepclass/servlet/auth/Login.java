package com.sheepclass.servlet.auth;

import com.sheepclass.entity.Users;
import com.sheepclass.service.Auth;
import com.sheepclass.utils.JwtUtils;
import com.sheepclass.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/auth/Login")
public class Login extends HttpServlet {

    public Login() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Login");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        Users user = new Users();
        try {
            String account = jsonObject.getString("account");
            if (account.indexOf('@') == -1)//不是邮箱
                user.setPhone(account);
            else
                user.setEmail(account);

            user.setUserpwd(jsonObject.getString("userpwd"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("user:"+user.getEmail()+"----"+user.getPhone());
        Auth auth = new Auth(user);
        int userid = auth.checkPassword(user);
        String token;
        if (userid != 0) {
            token = JwtUtils.createToken(userid);
            try {
                msg.put("token", token);
                msg.put("tag", 1);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        else {
            token = null;
            try {
                msg.put("token", token);
                msg.put("tag", 0);
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