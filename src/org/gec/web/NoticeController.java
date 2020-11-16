package org.gec.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.gec.bean.Dept;
import org.gec.bean.Employee;
import org.gec.bean.Job;
import org.gec.bean.Notice;
import org.gec.bean.Type;
import org.gec.bean.User;
import org.gec.service.NoticeService;
import org.gec.service.impl.NoticeServiceImpl;
import org.gec.util.PageModel;

/**
 * Servlet implementation class NoticeController
 */
@WebServlet(urlPatterns= {"/addNotice.action","/noticeaddsave.action","/noticelist.action","/noticedel.action","/updateNotice.action","/viewNotice.action","/displayNotice.action"})
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       NoticeService service=new NoticeServiceImpl();
       Integer id=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeController() {
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
		String action = uri.substring(uri.lastIndexOf("/")+1, uri.length());
		if(action.equals("noticelist.action")) {
			select(request, response);
		}
		if(action.equals("addNotice.action")) {
			List<Type> type= service.findType();
			request.setAttribute("typeList", type);
			request.getRequestDispatcher("/WEB-INF/jsp/notice/noticeadd.jsp").forward(request, response);
		}if(action.equals("noticeaddsave.action")) {
			add(request, response);
		}if(action.equals("noticedel.action")) {
			detele(request, response);
		}if(action.equals("viewNotice.action")) {
			id=Integer.parseInt(request.getParameter("id"));
			Notice notice= service.findNotice(id);
			List<Type> type=service.findType();
			List<User> user=service.findUser();
			request.setAttribute("notice", notice);
			request.setAttribute("typeList", type);
			request.setAttribute("userList", user);
			request.getRequestDispatcher("/WEB-INF/jsp/notice/noticeupdate.jsp").forward(request, response);
		}if(action.equals("updateNotice.action")) {
			update(request, response);
		}if(action.equals("displayNotice.action")) {
			show(request, response);
		}
	}
	
	//查找
	protected void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titles=request.getParameter("title");
		String typeIds=request.getParameter("type_id");
		Integer typeId = typeIds != null && !typeIds.equals("") ? Integer.parseInt(typeIds):null;
		String title=  titles!=null&&!titles.equals("") ? titles.trim():null;
//		String title=  titles!=null&&!titles.equals("") ? titles.trim():null;
		Notice notice=new Notice(title,typeId);
		
		String pageIndex = request.getParameter("pageIndex");
		Integer index = pageIndex != null && !pageIndex.equals("") ? Integer.parseInt(pageIndex):1;
		PageModel model = new PageModel();
		model.setPageIndex(index);
		int totalRecordSum = service.getTotalCountByNotice(notice);
		model.setTotalRecordSum(totalRecordSum);
		
		List<Type> type=service.findType();
		List<User> user=service.findUser();
		List<Notice> notices=service.findNotice(notice, model);
		request.setAttribute("typeList", type);
		request.setAttribute("pageModel", model);
		request.setAttribute("userList", user);
		request.setAttribute("noticelist", notices);
		request.setAttribute("notices", notice);
		
     request.getRequestDispatcher("/WEB-INF/jsp/notice/noticelist.jsp").forward(request, response);
	}
	
	//添加
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user_session");
		Integer userId=user.getId();
		String title=request.getParameter("title");
		Integer typeId=Integer.parseInt(request.getParameter("type_id"));
		String remark=request.getParameter("mytxtIntro");
		Notice notice=new Notice(title,typeId,userId,remark);
		service.addNotice(notice);
		response.sendRedirect("noticelist.action");
	}
	
	//修改
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title=request.getParameter("title");
		String remark=request.getParameter("remark");
		Integer typeId=Integer.parseInt(request.getParameter("type_id"));
		Notice not=new Notice(title,typeId,remark);
		service.update(not);
		response.sendRedirect("noticelist.action");
	}
	//删除
	protected void detele(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] ids = request.getParameterValues("noticeIds");
		service.deleteNotice(ids);
		response.sendRedirect("noticelist.action");
	}
	protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 id = Integer.parseInt(request.getParameter("id"));
		List<Type> typelist=service.findType();
		Notice notice=service.showNotice(id);
		request.setAttribute("typeList", typelist);
		request.setAttribute("notice", notice);
	 request.getRequestDispatcher("WEB-INF/jsp/notice/noticedisplay.jsp").forward(request, response);
	}
}
