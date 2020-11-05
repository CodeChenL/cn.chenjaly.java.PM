package cn.chenjaly.java.practicaltrainin.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.chenjaly.java.practicaltrainin.bean.Job;
import cn.chenjaly.java.practicaltrainin.service.JobService;
import cn.chenjaly.java.practicaltrainin.service.impl.JobServiceImpl;
import cn.chenjaly.java.practicaltrainin.utils.PageModel;

/**
 * Servlet implementation class JobController
 */
@WebServlet(urlPatterns= {"/joblist.action","/jobadd.action","/jobaddsave.action","/jobdel.action","/updateJob.action","/viewJob.action"})
public class JobController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JobService service = new JobServiceImpl();
	Integer id = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobController() {
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
		System.out.println(uri);
		if(action.equals("joblist.action")) {
			String names = request.getParameter("name");
			String remarks = request.getParameter("remark");
			String name = names !=null&&!names.equals("")?names.trim():null;
			String remark = remarks !=null&&!remarks.equals("")?remarks.trim():null;
			
			Job job = new Job(name,remark);
			String pageIndex = request.getParameter("pageIndex");
			Integer index = pageIndex !=null &&!pageIndex.equals("")?Integer.parseInt(pageIndex):1;
			
			PageModel model = new PageModel(); 
			model.setPageIndex(index);
			int totalRecordSum = service.getTotalCountByJob(job);
			model.setTotalRecordSum(totalRecordSum);
			
			List<Job> joblist = service.findJob(job, model);
			request.setAttribute("pageModel", model);
			request.setAttribute("dept", job);
			request.setAttribute("joblist", joblist);
			request.getRequestDispatcher("/WEB-INF/jsp/job/joblist.jsp").forward(request, response);
		}if(action.equals("jobadd.action")) {
			showaddJob(request,response);
		}if(action.equals("jobaddsave.action")) {
			addJob(request,response);
		}if(action.equals("jobdel.action")) {
			delete(request,response);
		}if(action.equals("viewJob.action")) {
			showupdate(request,response);
		}if(action.equals("updateJob.action")) {
			update(request,response);
		}
	}
	protected void addJob(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String remark  = request.getParameter("remark");
		Job job = new Job(name,remark);
		service.addJob(job);
		response.sendRedirect("joblist.action");
	}
	protected void showaddJob(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/job/jobadd.jsp").forward(request, response);
	}
	protected void update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String remark  = request.getParameter("remark");
		Job job = new Job(id,name,remark);
		service.updateJob(job);
		request.getRequestDispatcher("joblist.action").forward(request, response);
	}
	protected void showupdate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		id = Integer.parseInt(request.getParameter("id"));
		Job job = service.findJob(id);
		request.setAttribute("job", job);
		request.getRequestDispatcher("WEB-INF/jsp/job/jobedit.jsp").forward(request, response);
	}
	protected void delete(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String[] ids = request.getParameterValues("jobIds"); 
		service.deleteJob(ids);
		response.sendRedirect("joblist.action");
	}
	}