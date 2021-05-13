package org.gec.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gec.bean.Dept;
import org.gec.bean.User;
import org.gec.service.DeptService;
import org.gec.service.impl.DeptServiceImpl;
import org.gec.util.PageModel;

/**
 * Servlet implementation class DeptController
 */
@WebServlet(urlPatterns = {"/deptlist.action", "/addDept.action", "/deptaddsave.action", "/deptdel.action", "/updateDept.action", "/viewDept.action"})
public class DeptController extends HttpServlet {
    DeptService service = new DeptServiceImpl();
    private static final long serialVersionUID = 1L;
    Integer id = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeptController() {
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
        System.out.println(uri);
        if (action.equals("deptlist.action")) {
            String names = request.getParameter("name");
            String remarks = request.getParameter("remark");

            String name = names != null && !names.equals("") ? names.trim() : null;
            String remark = remarks != null && !remarks.equals("") ? remarks.trim() : null;
            //构建一个用户查询对象
            Dept dept = new Dept(name, remark);
            String pageIndex = request.getParameter("pageIndex");
            Integer index = pageIndex != null && !pageIndex.equals("") ? Integer.parseInt(pageIndex) : 1;

            PageModel model = new PageModel();
            model.setPageIndex(index);
            int totalRecordSum = service.getTotalCountByDept(dept);
            model.setTotalRecordSum(totalRecordSum);


            //查询
            List<Dept> deptlist = service.findDept(dept, model);
            //userlist.forEach(u->System.out.println(u));

            //存储
            request.setAttribute("pageModel", model);
            //查询对象
            request.setAttribute("dept", dept);
            //用户list
            request.setAttribute("deptlist", deptlist);
            //跳转到
            request.getRequestDispatcher("/WEB-INF/jsp/dept/deptlist.jsp").forward(request, response);
        }
        if (action.equals("addDept.action")) {
            showaddDept(request, response);
        }
        if (action.equals("deptaddsave.action")) {
            addDept(request, response);
        }
        if (action.equals("deptdel.action")) {
            delete(request, response);
        }
        if (action.equals("viewDept.action")) {
            showupdate(request, response);
        }
        if (action.equals("updateDept.action")) {
            update(request, response);
        }

    }

    //添加
    protected void addDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String remark = request.getParameter("remark");
        Dept dept = new Dept(name, remark);
        service.addDept(dept);

        response.sendRedirect("deptlist.action");
    }

    //页面
    protected void showaddDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/dept/deptadd.jsp").forward(request, response);
    }

    protected void showupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        id = Integer.parseInt(request.getParameter("id"));
        Dept dept = service.findDept(id);
        //查询对象
        request.setAttribute("dept1", dept);

        request.getRequestDispatcher("/WEB-INF/jsp/dept/deptdit.jsp").forward(request, response);
    }


    //修改
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String remark = request.getParameter("remark");
        Dept dept = new Dept(id, name, remark);
//			id=Integer.parseInt(request.getParameter("id"));
//			User user=service.findUsers(id);
        service.updateDept(dept);

        //跳转到主页
        request.getRequestDispatcher("deptlist.action").forward(request, response);
    }

    //删除
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] ids = request.getParameterValues("deptIds");
        service.deleteDept(ids);
        ;
        //跳转到
        response.sendRedirect("deptlist.action");

    }
}

