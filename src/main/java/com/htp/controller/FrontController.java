package com.htp.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FrontController extends HttpServlet {
    public FrontController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/bye");
        if (dispatcher != null) {
            System.out.println("Forward will be done!");
            dispatcher.forward(req, resp);
        }
//        resp.setContentType("text/html");
//        PrintWriter out = resp.getWriter();
//        out.println("<http><head>");
//        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
//        out.println("<title>Start!</title>");
//        out.println("</head></body>");
//        out.println("<h1> Hello, world! </h1>");
//        out.println("</body></html>");
    }
}
