package org.gec.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.gec.bean.Type;
import org.gec.service.TypeService;
import org.gec.service.impl.TypeServiceImpl;
import org.gec.util.PageModel;

/**
 * Servlet implementation class TypeControler
 */
@WebServlet(urlPatterns = {"/type/addType", "/typeaddsave.action", "/typelist.action", "/viewtype.action", "/typeupdate.action", "/typedel.action"})
public class TypeControler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypeControler() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TypeService ts = new TypeServiceImpl();
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        //截取
        String action = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
        if (action.equals("addType")) {
            request.getRequestDispatcher("/WEB-INF/jsp/type/typeadd.jsp").forward(request, response);
        }
        if (action.equals("typeaddsave.action")) {
            String names = request.getParameter("name");
            String name = names != null && !names.equals("") ? names.trim() : null;
            Type type = new Type(name);
            ts.saveType(type);

            response.sendRedirect("typelist.action");
        }
        if (action.equals("typelist.action")) {
            String name = request.getParameter("name");

            String pageIndex = request.getParameter("pageIndex");
            Integer index = pageIndex != null && !pageIndex.equals("") ? Integer.parseInt(pageIndex) : 1;

            PageModel model = new PageModel();
            model.setPageIndex(index);
            int totalRecordSum = ts.getTotalCountByType(name);
            model.setTotalRecordSum(totalRecordSum);

            List<Type> types = ts.findTypesByPage(name, model);
            request.setAttribute("typelist", types);
            request.setAttribute("pageModel", model);
            request.getRequestDispatcher("/WEB-INF/jsp/type/typelist.jsp").forward(request, response);

        }
        if (action.equals("viewtype.action")) {
            HttpSession session = request.getSession();
            int id = Integer.parseInt(request.getParameter("id"));
            Type type = ts.findTypeById(id);
            request.setAttribute("type", type);
            session.setAttribute("type_session", type);
            request.getRequestDispatcher("/WEB-INF/jsp/type/typeedit.jsp").forward(request, response);
        }
        if (action.equals("typeupdate.action")) {
            HttpSession session = request.getSession();
            Type type = (Type) session.getAttribute("type_session");
            String name = request.getParameter("name").trim();
            type.setName(name);
            ts.updateType(type);
            response.sendRedirect("typelist.action");
        }
        if (action.equals("typedel.action")) {
            String[] ids = request.getParameterValues("typeIds");
            ts.deleteType(ids);
            response.sendRedirect("typelist.action");
        }
    }

}
