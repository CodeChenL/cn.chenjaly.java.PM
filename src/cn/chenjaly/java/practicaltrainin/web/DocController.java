package cn.chenjaly.java.practicaltrainin.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/documentlist.action","/documentadd.action","/documentaddsave.action"})
public class DocController extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        String action = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

        if (action.equals("documentlist.action")) {
            request.getRequestDispatcher("/WEB-INF/jsp/document/documentlist.jsp").forward(request, response);
        }else if (action.equals("documentadd.action")) {
            request.getRequestDispatcher("/WEB-INF/jsp/document/documentadd.jsp").forward(request, response);
        }
    }
}
