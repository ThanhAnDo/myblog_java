package com.aptech.myblog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//cách dùng annotation để cấu hình routes
//@WebServlet(urlPatterns = "/home") //cấu hình routes

//dùng annotation
//@WebServlet(urlPatterns = {"/home", "*.do"}, initParams = {@WebInitParam(name="productName",value="My Blog")}) //cấu hình nhều route, *.do =>chấp nhận mọi đường dẫn từ trước .do, vd a.do, ///.do... đều vô được
//end dùng annotation

//dùng web.xml
@WebServlet(urlPatterns = {"/home", "*.do"}, name="Main")
//end dùng web.xml
public class MainServlet extends HttpServlet {
    String productName="";
    String connectionStr="";
    @Override
    public void init() throws ServletException{
        productName = getInitParameter("productName");
        connectionStr = getServletContext().getInitParameter("connectString");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //trả về xml
        resp.setContentType("text/xml");
        String name = req.getParameter("name");
        resp.getWriter().write(String.format("<application>" +
                "<msg>Hello %s</msg>" +
                "<productName>%s</productName>" +
                "<connectionStr>%s</connectionStr>" +
                "</application>",name,productName, connectionStr));
        //trả về JSON
       // resp.setContentType("application/xml");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if(name!=null && name!=""){
            resp.getWriter().write("hello"+ " " +name);
        }else{
            resp.sendRedirect("index.html");
        }
    }
}
