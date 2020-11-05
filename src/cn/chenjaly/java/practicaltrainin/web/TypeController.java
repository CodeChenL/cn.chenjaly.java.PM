package cn.chenjaly.java.practicaltrainin.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/typelist.action","/noticelist.action","/addNotice.action"})
public class TypeController extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        String action = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
        if (request.equals("typelist.action")) {
            request.getRequestDispatcher("/WEB-INF/jsp/type/typelist.jsp").forward(request, response);
            request.setAttribute("flag", "1");
        }else if (request.equals("noticelist.action")) {
            request.getRequestDispatcher("/WEB-INF/jsp/notice/noticelist.jsp").forward(request, response);
        }else if (request.equals("addNotice.action")) {
            request.getRequestDispatcher("/WEB-INF/jsp/notice/noticeadd.jsp").forward(request, response);
        }
    }
}