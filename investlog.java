package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.investregbean;
import Imple.Imple;
import Inter.Inter;

/**
 * Servlet implementation class investlog
 */
@WebServlet("/investlog")
public class investlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public investlog() {
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

		String investemail=request.getParameter("investemail");
		System.out.println("username: "+investemail);
		
		session.setAttribute("investemail", investemail);
		
		String investpassword=request.getParameter("investpassword");
		System.out.println("password: "+investpassword);
				
		
		investregbean il=new investregbean();

		il.setInvestemail(investemail);
		il.setInvestpassword(investpassword);
				
		
		Inter in=new Imple();
		boolean log=in.investlog(il);
		
		if(log==true){
			response.sendRedirect("business home.html");
		}
		else{
			response.sendRedirect("error123.jsp");
		}
		
		
		
	}


	}


