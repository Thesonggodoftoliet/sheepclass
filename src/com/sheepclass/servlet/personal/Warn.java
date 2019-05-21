package com.sheepclass.servlet.personal;

import com.sheepclass.entity.Users;
import com.sheepclass.service.Warning;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/personal/Warn")
public class Warn extends HttpServlet {

    public Warn() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Warn");
        Warning warning = new Warning();
        warning.sendWarn();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}