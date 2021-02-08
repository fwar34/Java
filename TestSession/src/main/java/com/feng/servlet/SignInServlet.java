package com.feng.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/signin")
public class SignInServlet extends HttpServlet {
    //模拟数据库
    private Map<String, String> users = new HashMap<>();

    // GET请求时显示登录页:
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (users.isEmpty()) {
            users.put("bob", "bob123");
            users.put("alice", "alice123");
            users.put("tom", "tomcat");
        }

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.write("<h1>Sign In</h1>");
        writer.write("<form action=\"/signin\" method=\"post\">");
        writer.write("<p>Username: <input name=\"username\"></p>");
        writer.write("<p>Password: <input name=\"password\" type=\"password\"></p>");
        writer.write("<p><button type=\"submit\">Sign In</button> <a href=\"/\">Cancel</a></p>");
        writer.write("</form>");
        writer.flush();
    }

    // POST请求时处理用户登录:
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String expectedPassword = users.get(name.toLowerCase());
        if (expectedPassword != null && expectedPassword.equals(password)) {
            request.getSession().setAttribute("user", name);
            response.sendRedirect("/");
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
