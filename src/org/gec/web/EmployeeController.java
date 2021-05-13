package org.gec.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

import org.gec.bean.Dept;
import org.gec.bean.Employee;
import org.gec.bean.Job;
import org.gec.service.DeptService;
import org.gec.service.EmployeeService;
import org.gec.service.JobService;
import org.gec.service.impl.DeptServiceImpl;
import org.gec.service.impl.EmployeeServiceImpl;
import org.gec.service.impl.JobServiceImpl;
import org.gec.util.PageModel;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet(urlPatterns = {"/updateEmployee.action", "/employeeadd.action", "/addEmployee.action", "/employeelist.action", "/viewEmployee.action", "/employeedel.action", "/getcardid.action"})
public class EmployeeController extends HttpServlet {
    EmployeeService service = new EmployeeServiceImpl();
    DeptService ser = new DeptServiceImpl();
    JobService s = new JobServiceImpl();
    Integer id = null;
    private static final long serialVersionUID = 1L;

    /**
     * XXX
     *
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeController() {
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
        if (action.equals("employeelist.action")) {
            selectEmployees(request, response);

            request.getRequestDispatcher("/WEB-INF/jsp/employee/employeelist.jsp").forward(request, response);
        }
        if (action.equals("employeeadd.action")) {
            List<Dept> list = new ArrayList<Dept>();
            list = service.findDept();
//			//查询对象 
            List<Job> lists = new ArrayList<Job>();

            lists = service.findJob();
            request.setAttribute("deptList", list);
            request.setAttribute("jobList", lists);
            request.getRequestDispatcher("WEB-INF/jsp/employee/employeeadd.jsp").forward(request, response);
        }
        if (action.equals("addEmployee.action")) {
//			request.getRequestDispatcher("WEB-INF/jsp/employee/employeeadd.jsp").forward(request, response);
            addEmployees(request, response);
        }
        if (action.equals("viewEmployee.action")) {
            showEmployees(request, response);
        }
        if (action.equals("updateEmployee.action")) {
            String cardId = request.getParameter("cardId");
            String cardIds = service.findCardId(cardId);
            PrintWriter out = response.getWriter();
            System.out.println("debug" + cardId);
            System.out.println(cardIds != null);
            out.write("身份证已经存在");
            if (cardIds != null) {
                System.out.println("debug" + cardId);
                request.getRequestDispatcher("false.jsp");
            } else {
                update(request, response);
            }

        }
        if (action.equals("employeedel.action")) {
            delectEmployees(request, response);
        }
        if (action.equals("getcardid.action")) {
            String cardId = request.getParameter("cardId");
            String cardIds = service.findCardId(cardId);
            PrintWriter out = response.getWriter();
            System.out.println("debug" + cardId);
            System.out.println("debug" + cardIds);
            if (cardIds != null) {
                out.write("身份证已经存在");
            } else {
                out.write("");
            }
        }
    }

    //查询
    protected void selectEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jobid = request.getParameter("job_id");
//		System.out.println(jobid);
        String names = request.getParameter("name");
        String cardIds = request.getParameter("cardId");
        String sex = request.getParameter("sex");
        String phones = request.getParameter("phone");
        String deptid = request.getParameter("dept_id");
//		String jobremark=request.getParameter("remark");
        //把job_id、sex、dept_id转换为整型


        String name = names != null && !names.equals("") ? names.trim() : null;
        String cardId = cardIds != null && !cardIds.equals("") ? cardIds.trim() : null;
        String phone = phones != null && !phones.equals("") ? phones.trim() : null;

        Integer jobId = jobid != null && !jobid.equals("") ? Integer.parseInt(jobid) : null;
        Integer sexs = sex != null && !sex.equals("") ? Integer.parseInt(sex) : null;
        Integer deptId = deptid != null && !deptid.equals("") ? Integer.parseInt(deptid) : null;
        //构建一个用户查询对象
        Employee emp = new Employee(deptId, jobId, cardId, name, phone, sexs);

        String pageIndex = request.getParameter("pageIndex");
        Integer index = pageIndex != null && !pageIndex.equals("") ? Integer.parseInt(pageIndex) : 1;
        PageModel model = new PageModel();
        model.setPageIndex(index);
        int totalRecordSum = service.getTotalCountByEmployee(emp);
        model.setTotalRecordSum(totalRecordSum);

        //查询
        List<Job> joblist = service.findJob();
        List<Dept> deptlist = service.findDept();
        List<Employee> employeelist = service.findEmployee(emp, model);

        request.setAttribute("jobList", joblist);
        request.setAttribute("deptList", deptlist);
        request.setAttribute("employeelist", employeelist);
        request.setAttribute("pageModel", model);
        request.setAttribute("employee", emp);
    }

    //添加
    protected void addEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer jobId = Integer.parseInt(request.getParameter("job_id"));
        Integer deptId = Integer.parseInt(request.getParameter("dept_id"));
        String name = request.getParameter("name");
        String cardId = request.getParameter("cardId");
        String address = request.getParameter("address");
        String postcode = request.getParameter("postcode");
        String tel = request.getParameter("tel");
        String phone = request.getParameter("phone");
        String qqnum = request.getParameter("qqnum");
        String email = request.getParameter("email");
        Integer sex = Integer.valueOf(request.getParameter("sex"));
        String party = request.getParameter("party");
        String birthdays = request.getParameter("birthday");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        Date birthday = null;
        try {
            birthday = simpleDateFormat.parse(birthdays);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String race = request.getParameter("race");
        String education = request.getParameter("education");
        String speciality = request.getParameter("speciality");
        String hobby = request.getParameter("hobby");
        String remark = request.getParameter("remark");
        Employee emp = new Employee(deptId, jobId, cardId, name, address, postcode, tel, phone, qqnum, email, sex, party, birthday, race, education, speciality, hobby, remark);
//cardId,


        service.addEmployee(emp);
        response.sendRedirect("employeelist.action");
    }

    //修改
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer jobId = Integer.parseInt(request.getParameter("job_id"));
        Integer deptId = Integer.parseInt(request.getParameter("dept_id"));
        String name = request.getParameter("name");
        String cardId = request.getParameter("cardId");
        String address = request.getParameter("address");
        String postcode = request.getParameter("postcode");
        String tel = request.getParameter("tel");
        String phone = request.getParameter("phone");
        String qqnum = request.getParameter("qqnum");
        String email = request.getParameter("email");
        Integer sex = Integer.parseInt(request.getParameter("sex"));
        String party = request.getParameter("party");
        String birthdays = request.getParameter("birthday");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        Date birthday = null;
        try {
            birthday = simpleDateFormat.parse(birthdays);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String race = request.getParameter("race");
        String education = request.getParameter("education");
        String speciality = request.getParameter("speciality");
        String hobby = request.getParameter("hobby");
        String remark = request.getParameter("remark");
        Employee emp = new Employee(id, deptId, jobId, cardId, name, address, postcode, tel, phone, qqnum, email, sex, party, birthday, race, education, speciality, hobby, remark);

        service.update(emp);
        //跳转到主页
        response.sendRedirect("employeelist.action");
//				request.getRequestDispatcher("employeelist.action").forward(request, response);
    }

    //删除
    protected void delectEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] ids = request.getParameterValues("id");
        service.deleteEmployee(ids);
        //跳转到
        response.sendRedirect("employeelist.action");
    }

    //展示修改页面
    protected void showEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        Employee emp = service.findEmployees(id);
        List<Job> job = service.findJob();
        List<Dept> dept = service.findDept();
        request.setAttribute("employee", emp);
        request.setAttribute("jobs", job);
        request.setAttribute("depts", dept);
        request.getRequestDispatcher("WEB-INF/jsp/employee/employeeedit.jsp").forward(request, response);
    }

}
