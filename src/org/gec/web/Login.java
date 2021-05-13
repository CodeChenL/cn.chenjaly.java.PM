package org.gec.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.gec.bean.User;
import org.gec.service.UserService;
import org.gec.service.impl.UserServiceImpl;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns = {"/loginForm.action", "/login.action"})
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //http://localhost:8080/hrm/loginForm.action
        String uri = request.getRequestURI();//hrm/loginForm.action
        //截取处理  获取到xxx.action
        String action = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
        System.out.println("action:" + action);

        //是登录还是登录页面
        if (action.equals("login.action")) {
            //获取用户名和密码
            String loginname = request.getParameter("loginname");
            String password = request.getParameter("password");

            UserService service = new UserServiceImpl();
            User user = service.login(loginname, password);
            if (user != null) {
                //加入session
                HttpSession session = request.getSession();
                session.setAttribute("user_session", user);
                //跳转到主页面
                request.getRequestDispatcher("/main.action").forward(request, response);
            } else {
                request.setAttribute("message", "用户名或密码错误");
                //跳转到登录页
                request.getRequestDispatcher("/loginForm.action").forward(request, response);
            }
            //service.login
        } else {
            //登录页面
            request.getRequestDispatcher("/WEB-INF/jsp/loginForm.jsp").forward(request, response);
        }
    }

}
