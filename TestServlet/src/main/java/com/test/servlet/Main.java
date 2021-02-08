package com.test.servlet;

import org.apache.commons.lang3.StringUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/")
public class Main extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String name = request.getParameter("name");
        String word = "world";
        if (name != null && !StringUtils.isBlank(name)) {
            word = name;
        }
        writer.write("<h1>hello, " + word + "!</h1>");
        writer.flush();
    }
}


