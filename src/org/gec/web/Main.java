package org.gec.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Main
 */
@WebServlet(urlPatterns = {"/main.action", "/top.action", "/left.action", "/right.action"})
public class Main extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        //截取
        String action = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

        //判断action
        if (action.equals("main.action")) {
            request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
        } else if (action.equals("left.action")) {
            request.getRequestDispatcher("/WEB-INF/jsp/left.jsp").forward(request, response);
        } else if (action.equals("top.action")) {
            request.getRequestDispatcher("/WEB-INF/jsp/top.jsp").forward(request, response);
        } else if (action.equals("right.action")) {
            request.getRequestDispatcher("/WEB-INF/jsp/right.jsp").forward(request, response);
        }
    }

}
