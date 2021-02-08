package com.feng.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = (String) request.getSession().getAttribute("user");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("X-Powered-By", "JavaEE Servlet");
        PrintWriter writer = response.getWriter();
        writer.write("<h1>Welcome, " + (user != null ? user : "Guest") + "</h1>");
        if (user == null) {
            writer.write("<p><a href=\"signin\">Sign In</a></p>");
        } else {
            writer.write("<p><a href=\"signout\">Sign Out</a></p>");
        }
        writer.flush();
    }
}
