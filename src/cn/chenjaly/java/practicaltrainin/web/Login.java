package cn.chenjaly.java.practicaltrainin.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.chenjaly.java.practicaltrainin.bean.User;
import cn.chenjaly.java.practicaltrainin.service.UserService;
import cn.chenjaly.java.practicaltrainin.service.impl.UserServiceImpl;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns= {"/loginForm.action","/login.action"})
//@WebServlet(urlPatterns= {"/loginForm.action","/login.action"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String uri = request.getRequestURI();
		
		String action =  uri.substring(uri.lastIndexOf("/")+1,uri.length());
		
		if(action.equals("login.action")) {
			String loginname = request.getParameter("loginname");
			String password = request.getParameter("password");
			System.out.println(loginname+password+"1");
			UserService service = new UserServiceImpl();
			User user = service.login(loginname, password);
			if(user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user_session", user);
				
				request.getRequestDispatcher("/main.action").forward(request, response);
			}else {
				request.setAttribute("message", "用户名或密码错误");
				request.getRequestDispatcher("/loginForm.action").forward(request, response);
			}
		}else {
			request.getRequestDispatcher("/WEB-INF/jsp/loginForm.jsp").forward(request, response);
		}
	}

}
