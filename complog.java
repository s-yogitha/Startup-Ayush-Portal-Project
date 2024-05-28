package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.compregbean;

import Imple.Imple;
import Inter.Inter;

/**
 * Servlet implementation class complog
 */
@WebServlet("/complog")
public class complog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public complog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	
	
		HttpSession session=request.getSession();
	
	
	
	
	
	String founderemail=request.getParameter("foundemail");
	System.out.println("username: "+founderemail);
	
	session.setAttribute("founderemail", founderemail);
	
	String password=request.getParameter("password");
	System.out.println("password: "+password);
	
	
	
	
	compregbean cl=new compregbean();

	cl.setFoundemail(founderemail);
	cl.setPassword(password);
	
	
	
	
	
	Inter in=new Imple();
	boolean log=in.companylog(cl);
	
	if(log==true){
		response.sendRedirect("startuphome.html");
	}
	else{
		response.sendRedirect("error123.jsp");
	}
	
	
	
}

	
}
