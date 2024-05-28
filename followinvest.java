package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Bean.followinvestbean;
import Bean.investmentbean;
import Dbcon.Dbconn;
import Imple.Imple;
import Inter.Inter;

 import java.sql.ResultSet;
 import java.sql.PreparedStatement;
 import java.sql.*;
 import java.util.*;
 import java.io.FileInputStream;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.sql.ResultSet;
 import java.sql.PreparedStatement;
 import java.sql.*;
 import java.util.*;
 import javax.servlet.ServletException;
 import javax.servlet.annotation.WebServlet;
 import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class followinvest
 */
@WebServlet("/followinvest")
public class followinvest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public followinvest() {
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
						

		String companyid=request.getParameter("companyid");
		System.out.println("companyid: "+companyid);
		
		String companyname=request.getParameter("companyname");
		System.out.println("companyname: "+companyname);
		
		String foundemail=request.getParameter("foundemail");
		System.out.println("foundemail: "+foundemail);
		
		String funding=request.getParameter("funding");
		System.out.println("funding: "+funding);
		
		String fieldtype=request.getParameter("fieldtype");
		System.out.println("fieldtype: "+fieldtype);
		
		String totalshares=request.getParameter("totalshares");
		System.out.println("totalshares: "+totalshares);
		
		String nooffounder=request.getParameter("nooffounder");
		System.out.println("nooffounder: "+nooffounder);
		
		String investoremail=request.getParameter("investoremail");
		System.out.println("investoremail: "+investoremail);
		
		String followname=request.getParameter("followname");
		System.out.println("followname: "+followname);
		
		String followemail=request.getParameter("followemail");
		System.out.println("followemail: "+followemail);


		
		followinvestbean fin=new followinvestbean();

		fin.setCompanyid(companyid);
		fin.setCompanyname(companyname);
		fin.setFoundemail(foundemail);
		fin.setFunding(funding);
		fin.setFieldtype(fieldtype);
		fin.setTotalshares(totalshares);
		fin.setNooffounder(nooffounder);
		fin.setInvestoremail(investoremail);
		fin.setFollowname(followname);
		fin.setFollowemail(followemail);
	
		

		
  
		
		

		
		Inter im = new Imple();
		int i = im.followinvest(fin);
		if (i == 1) {
			 
			
		
			
			
		
			
			
			
			response.sendRedirect("followerpayment.jsp");
		} else {
			response.sendRedirect("error.jsp");
		}
			
	}
		
	
		
	
}
