package org.gec.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gec.bean.Dept;
import org.gec.bean.Job;
import org.gec.service.JobService;
import org.gec.service.impl.JobServiceImpl;
import org.gec.util.PageModel;

/**
 * Servlet implementation class JobController
 */
@WebServlet(urlPatterns= {"/jobController","/joblist.action","/jobdel.action","/jobaddsave.action","/jobadd.action","/viewJob.action","/updateJob.action"})
public class JobController extends HttpServlet {
	JobService service=new JobServiceImpl();
	private static final long serialVersionUID = 1L;
       Integer id=null;
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
		//截取
		String action = uri.substring(uri.lastIndexOf("/")+1, uri.length());
		System.out.println(uri);
		if(action.equals("joblist.action")) {
			String names=request.getParameter("name");
			String remarks=request.getParameter("remark");
			
			String name=  names!=null&&!names.equals("") ? names.trim():null;
			String remark=  remarks!=null&&!remarks.equals("") ? remarks.trim():null;
			//构建一个用户查询对象
			Job job =new Job(name,remark);	
			String pageIndex = request.getParameter("pageIndex");
			Integer index = pageIndex != null && !pageIndex.equals("") ? Integer.parseInt(pageIndex):1;
			
			PageModel model = new PageModel();
			model.setPageIndex(index);
			int totalRecordSum = service.getTotalCountByJob(job);
			model.setTotalRecordSum(totalRecordSum);
			
			
			//查询
			List<Job> joblist = service.findJob(job, model);
			//userlist.forEach(u->System.out.println(u));
			
			//存储
			request.setAttribute("pageModel", model);
			//查询对象 
			request.setAttribute("job", job);
			//用户list
			request.setAttribute("joblist", joblist);
			//跳转到
			request.getRequestDispatcher("/WEB-INF/jsp/job/joblist.jsp").forward(request, response);
		}
		if(action.equals("jobadd.action")) {
			showaddJob(request, response);
		}if(action.equals("jobaddsave.action")) {
			addJob(request, response);
		}if(action.equals("jobdel.action")) {
			delete(request, response);
		}
		if(action.equals("viewJob.action")) {
			showupdate(request, response);
		}if(action.equals("updateJob.action")) {
			update(request, response);
		}
		
	}
	
	//添加
		protected void addJob(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String name=request.getParameter("name");
			String remark=request.getParameter("remark");
			Job job=new Job(name,remark);
			service.addJob(job);
			
			response.sendRedirect("joblist.action");
		}
		
		//页面
		protected void showaddJob(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("/WEB-INF/jsp/job/jobadd.jsp").forward(request, response);
		}
		protected void showupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			 id=Integer.parseInt(request.getParameter("id"));
			Job job =service.findJob(id);
			//查询对象 
			request.setAttribute("job1", job);
			
			request.getRequestDispatcher("/WEB-INF/jsp/job/jobedit.jsp").forward(request, response);
		}
		
		
		//修改
			protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				String name = request.getParameter("name");
				String remark = request.getParameter("remark");
				Job job=new Job(id,name,remark);
//				id=Integer.parseInt(request.getParameter("id"));
//				User user=service.findUsers(id);
				service.updateJob(job);
				
				//跳转到主页
				request.getRequestDispatcher("joblist.action").forward(request, response);
			}
	//删除
			protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
					String[] ids=request.getParameterValues("jobIds");
					service.deleteJob(ids);
					//跳转到
					response.sendRedirect("joblist.action");

			     }

}
