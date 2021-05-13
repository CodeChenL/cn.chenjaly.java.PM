package org.gec.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.gec.bean.User;
import org.gec.dao.UserDao;
import org.gec.dao.impl.UserDaoImpl;
import org.gec.service.UserService;
import org.gec.service.impl.UserServiceImpl;
import org.gec.util.PageModel;

/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns = {"/userlist.action", "/userdel.action", "/useradd.action", "/useraddsave.action", "/viewUser.action", "/updateUser.action"})
public class UserController extends HttpServlet {
    UserService service = new UserServiceImpl();
    private static final long serialVersionUID = 1L;
    Integer id = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        //截取
        String action = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
        //判断

        if (action.equals("userlist.action")) {
            String loginnames = request.getParameter("loginname");
            String usernames = request.getParameter("username");
            String status = request.getParameter("status");
            //转化 status != null && !status.equals(""):不为空
            Integer s = status != null && !status.equals("") ? Integer.parseInt(status) : null;
            String loginname = loginnames != null && !loginnames.equals("") ? loginnames.trim() : null;
            String username = usernames != null && !usernames.equals("") ? usernames.trim() : null;
            //构建一个用户查询对象

            User user = new User(loginname, username, s);

            String pageIndex = request.getParameter("pageIndex");
            Integer index = pageIndex != null && !pageIndex.equals("") ? Integer.parseInt(pageIndex) : 1;

            PageModel model = new PageModel();
            model.setPageIndex(index);
            int totalRecordSum = service.getTotalCountByUser(user);
            model.setTotalRecordSum(totalRecordSum);


            //查询
            List<User> userlist = service.findUsersByPage(user, model);
            //userlist.forEach(u->System.out.println(u));

            //存储
            request.setAttribute("pageModel", model);
            //查询对象
            request.setAttribute("user", user);
            //用户list
            request.setAttribute("userlist", userlist);

            //跳转到
            request.getRequestDispatcher("/WEB-INF/jsp/user/userlist.jsp").forward(request, response);

        }
        if (action.equals("userdel.action")) {
            delete(request, response);
        }
        if (action.equals("useradd.action")) {
            showpage(request, response);
        }
        if (action.equals("useraddsave.action")) {
            adduser(request, response);
        }
        if (action.equals("viewUser.action")) {
            showupdate(request, response);
        }
        if (action.equals("updateUser.action")) {
            update(request, response);
        }
        System.out.println(action);
    }

    //添加
    protected void adduser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginname = request.getParameter("loginname");
        String password = request.getParameter("password");
        Integer status = Integer.parseInt(request.getParameter("status"));
        String username = request.getParameter("username");
        User user = new User(loginname, password, status, username);
//		User users =request.getAttribute("useraddsave.action");
        service.addUsers(user);

        //跳转到主页
        request.getRequestDispatcher("userlist.action").forward(request, response);
    }

    //展示
    protected void showpage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/user/useradd.jsp").forward(request, response);
    }

    protected void showupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//	    String loginname = request.getParameter("loginname");
//		String password = request.getParameter("password");
//		Integer sta=Integer.parseInt(request.getParameter("status"));
//		String username = request.getParameter("username");
//		User user = new User(id,loginname,password,username,sta);
        id = Integer.parseInt(request.getParameter("id"));
        User user = service.findUsers(id);
        //查询对象
        request.setAttribute("user1", user);
        request.getRequestDispatcher("/WEB-INF/jsp/user/useredit.jsp").forward(request, response);
    }

    //删除
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] ids = request.getParameterValues("userIds");
        service.deleteUsers(ids);
        //跳转到
        response.sendRedirect("userlist.action");
    }

    //修改
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String loginname = request.getParameter("loginname");
        String password = request.getParameter("password");
        Integer status = Integer.parseInt(request.getParameter("status"));
        String username = request.getParameter("username");
        User user = new User(id, loginname, password, username, status);
//		id=Integer.parseInt(request.getParameter("id"));
//		User user=service.findUsers(id);
        service.update(user);

        //跳转到主页
        request.getRequestDispatcher("userlist.action").forward(request, response);
    }
}
