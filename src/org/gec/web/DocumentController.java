package org.gec.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.Severity;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.gec.bean.Dept;
import org.gec.bean.Document;
import org.gec.bean.Employee;
import org.gec.bean.Job;
import org.gec.bean.User;
import org.gec.service.DocumentService;
import org.gec.service.impl.DocumentServiceImpl;

/**
 * Servlet implementation class DocumentController
 */
@WebServlet(urlPatterns= {"/documentlist.action","/documentaddsave.action","/documentadd.action","/removeDocument.action","/updateDocumentsave.action","/updateDocument.action","/documentdownloads.action"})
@MultipartConfig
public class DocumentController extends HttpServlet {
	DocumentService service=new DocumentServiceImpl();
	private static final long serialVersionUID = 1L;
       Integer id=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocumentController() {
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
		//判断
		if(action.equals("documentlist.action")) {
			String titles=request.getParameter("title").trim();
			HttpSession session=request.getSession();
			User users=(User) session.getAttribute("user_session");
			Integer user_id=users.getId();
			
			String title=  titles!=null&&!titles.equals("") ? titles.trim():null;
			
			//新建查询对象
			Document doc=new Document(title);
			
			
			//查询
			List<Document> documentlist =service.findDocumentpage(doc);
			List<User> user=service.findUser();
	
			request.setAttribute("user", user);
			request.setAttribute("documentlist", documentlist);
			request.setAttribute("document", doc);
//			request.setAttribute("pageModel", model);
			
			request.getRequestDispatcher("WEB-INF/jsp/document/documentlist.jsp").forward(request, response);
		}
		if(action.equals("documentadd.action")) {
			showDocument(request, response);
		}if(action.equals("documentaddsave.action")) {
			addDocument(request, response);
		}if(action.equals("removeDocument.action")) {
			delete(request, response);
		}if(action.equals("updateDocumentsave.action")) {
			showupdateDocuments(request, response);
		}if(action.equals("updateDocument.action")) {
			update(request, response);
		}if(action.equals("documentdownloads.action")) {
			downloadDocument(request, response);
		}
	}

	//上传
		protected void addDocument(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			    String title=request.getParameter("title");
				String remark=request.getParameter("remark");
				Part part=request.getPart("file");
				String filename=part.getSubmittedFileName();
				String filetype=part.getContentType();
				HttpSession session=request.getSession() ;
				User user=(User) session.getAttribute("user_session");
				Integer user_id=user.getId();
				byte[] filebytes=null;		
				try(InputStream is=part.getInputStream();
						ByteArrayOutputStream bos=new ByteArrayOutputStream()) {
					int len=0;
					byte [] b=new byte[1024];
					while((len=is.read(b))>0) {
						bos.write(b, 0, len);
					}
					filebytes=bos.toByteArray();
					Document doc=new Document( title, filename, filetype, filebytes, remark,user_id);
				
					service.saveFile(doc);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			response.sendRedirect("documentlist.action");
		}
		
		//修改
				protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					Part part=request.getPart("file");
					String filename=part.getSubmittedFileName();
					String filetype=part.getContentType();
					byte[] filebytes=null;		
					try(InputStream is=part.getInputStream();
							ByteArrayOutputStream bos=new ByteArrayOutputStream()) {
						int len=0;
						byte [] b=new byte[1024];
						while((len=is.read(b))>0) {
							bos.write(b, 0, len);
						}
						filebytes=bos.toByteArray();
					String title=request.getParameter("title");
					String remark =request.getParameter("remark");
					Document doc=new Document(id,title,remark,filebytes,filename,filetype);
					service.updateDocument(doc);}
					response.sendRedirect("documentlist.action");
				}
		
		//删除
				protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					String[] ids=request.getParameterValues("ids");
					service.deleteDocument(ids);
					response.sendRedirect("documentlist.action");
				}
		
		protected void showDocument(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("WEB-INF/jsp/document/documentadd.jsp").forward(request, response);
		}
		protected void showupdateDocuments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			id=Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
			Document doc= service.findDocument(id);
//			List<User> user=service.findUser();
			request.setAttribute("document", doc);
//			request.setAttribute("users", user);
			
			request.getRequestDispatcher("WEB-INF/jsp/document/showUpdateDocument.jsp").forward(request, response);
			
		}
		
		//下载
		protected void downloadDocument(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html,charset=utf-8");
			response.setCharacterEncoding("utf-8");
			id=Integer.parseInt(request.getParameter("id"));
			Document doc=service.findDocument(id);
//			request.setAttribute("document", doc);
			String headStr = "attachment;filename=" + URLEncoder.encode(doc.getFilename(),"UTF-8").replace("+","%20");
			    	response.setHeader("content-disposition", headStr);
			    	 response.setContentType(doc.getFiletype());

			try(ByteArrayInputStream is = new ByteArrayInputStream(doc.getFilebytes());
				    OutputStream os =response.getOutputStream();		
				){
				int len = 0;
				byte[] b = new byte[1024];
				while((len = is.read(b)) != -1) {
					os.write(b, 0, len);
				}
				System.out.println("下载成功");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
}
		

