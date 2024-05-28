package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.followerregbean;
import Imple.Imple;
import Inter.Inter;

/**
 * Servlet implementation class follolog
 */
@WebServlet("/follolog")
public class follolog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public follolog() {
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
		
		String followemail=request.getParameter("folloemail");
		System.out.println("username: "+followemail);
		
		session.setAttribute("followemail", followemail);
		
		String followpassword=request.getParameter("follopass");
		System.out.println("password: "+followpassword);
		
		
		
		
		followerregbean fl=new followerregbean();

		fl.setFolloemail(followemail);
		fl.setFollopass(followpassword);
		
		
		
		
		
		Inter in=new Imple();
		boolean log=in.followlog(fl);
		
		if(log==true){
			response.sendRedirect("peoplehome.jsp");
		}
		else{
			response.sendRedirect("error123.jsp");
		}
		
		
		
	}

		
	


	}


