package cn.chenjaly.java.practicaltrainin.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.chenjaly.java.practicaltrainin.bean.User;
import cn.chenjaly.java.practicaltrainin.service.UserService;
import cn.chenjaly.java.practicaltrainin.service.impl.UserServiceImpl;
import cn.chenjaly.java.practicaltrainin.utils.PageModel;

/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns = {"/userlist.action","/userdel.action","/useradd.action","/useraddsave.action","/viewUser.action","/updateUser.action"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService service = new UserServiceImpl();
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
		String action = uri.substring(uri.lastIndexOf("/")+1,uri.length());
		
		if(action.equals("userlist.action")) {
			String loginnames = request.getParameter("loginname");
			String usernames = request.getParameter("username");
			String status = request.getParameter("status");
			
			Integer s = status != null && !status.equals("") ? Integer.parseInt(status):null;
			String loginname = loginnames !=null && !loginnames.equals("") ? loginnames.trim():null;
			String username = usernames !=null && !usernames.equals("") ? usernames.trim():null;
			
			User user = new User(loginname,username,s);
			
			String pageIndex = request.getParameter("pageIndex");
			Integer index = pageIndex !=null&&!pageIndex.equals("") ? Integer.parseInt(pageIndex):1;
			
			PageModel model = new PageModel();
			model.setPageIndex(index);
			int totalRecordSum = service.getTotalCountByUser(user);
			model.setTotalRecordSum(totalRecordSum);


			List<User> userlist = service.findUsersByPage(user, model);

			request.setAttribute("pageModel", model);

			request.setAttribute("user", user);

			request.setAttribute("userlist", userlist);


			request.getRequestDispatcher("/WEB-INF/jsp/user/userlist.jsp").forward(request, response);
		}if(action.equals("userdel.action")) {
			delete(request,response);
		}if(action.equals("useradd.action")) {
			showpage(request,response);
		}if(action.equals("useraddsave.action")) {
			adduser(request,response);
		}if(action.equals("viewUser.action")) {
			showupdate(request,response);
		}if(action.equals("updateUser.action")) {
			update(request,response);
		}
		System.out.println(action);
	}

	protected void adduser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String loginname = request.getParameter("loginname");
		String password = request.getParameter("password");
		Integer status = Integer.parseInt(request.getParameter("status"));
		String username = request.getParameter("username");
		
		User user = new User(loginname,password,status,username);
		service.addUser(user);
		request.getRequestDispatcher("userlist.action").forward(request, response);
	}

	protected void showpage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/user/useradd.jsp").forward(request, response);
	}
	protected void showupdate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		id = Integer.parseInt(request.getParameter("id"));
		User user = service.findUsers(id);
		request.setAttribute("user1", user);
		request.getRequestDispatcher("/WEB-INF/jsp/user/useredit.jsp").forward(request, response);
	}

	protected void delete(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String[] ids = request.getParameterValues("userIds");
		service.deleteUsers(ids);
		response.sendRedirect("userlist.action");
	}
	protected void update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String loginname = request.getParameter("loginname");
		String password = request.getParameter("password");
		Integer status = Integer.parseInt(request.getParameter("status"));
		String username = request.getParameter("username");
		User user = new User(id,loginname,password,username,status);
		service.updateUsers(user);
		request.getRequestDispatcher("userlist.action").forward(request, response);
	}
}
