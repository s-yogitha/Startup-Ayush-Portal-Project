package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Bean.followerregbean;
import Bean.investmentbean;
import Dbcon.Dbconn;
import Imple.Imple;
import Inter.Inter;

/**
 * Servlet implementation class investment
 */
@WebServlet("/investment")
public class investment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public investment() {
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
		
		
		
		String companyname=request.getParameter("companyname");
		System.out.println("companyname: "+companyname);
		
		String totalshares=request.getParameter("totalshares");
		System.out.println("totalshares: "+totalshares);
		
		String nooffounders=request.getParameter("nooffounders");
		System.out.println("nooffounders: "+nooffounders);
		
		String ED_founder1=request.getParameter("ED_founder1");
		System.out.println("ED_founder1: "+ED_founder1);
		
		String ED_founder2=request.getParameter("ED_founder2");
		System.out.println("ED_founder2: "+ED_founder2);
		
		String ED_founder3=request.getParameter("ED_founder3");
		System.out.println("ED_founder3: "+ED_founder3);
		
		String ED_founder4=request.getParameter("ED_founder4");
		System.out.println("ED_founder4: "+ED_founder4);
		
		String totalinvest=request.getParameter("totalinvest");
		System.out.println("totalinvest: "+totalinvest);
		
		String founder1shares=request.getParameter("founder1shares");
		System.out.println("founder1shares: "+founder1shares);
		
		String founder2shares=request.getParameter("founder2shares");
		System.out.println("founder2shares: "+founder2shares);
		
		String founder3shares=request.getParameter("founder3shares");
		System.out.println("founder3shares: "+founder3shares);
		
		String founder4shares=request.getParameter("founder4shares");
		System.out.println("founder4shares: "+founder4shares);
		
		String investamount=request.getParameter("investamount");
		System.out.println("investamount: "+investamount);
		
		String investED=request.getParameter("investED");
		System.out.println("investED: "+investED);
		
		String fieldtype=request.getParameter("fieldtype");
		System.out.println("fieldtype: "+fieldtype);
		
		String founder1invest=request.getParameter("founder1invest");
		System.out.println("founder1invest: "+founder1invest);
		
		String founder2invest=request.getParameter("founder2invest");
		System.out.println("founder2invest: "+founder2invest);
		
		String founder3invest=request.getParameter("founder3invest");
		System.out.println("founder3invest: "+founder3invest);
		
		String founder4invest=request.getParameter("founder4invest");
		System.out.println("founder4invest: "+founder4invest);
		
		String founder1name=request.getParameter("founder1name");
		System.out.println("founder1name: "+founder1name);
		
		String founder2name=request.getParameter("founder2name");
		System.out.println("founder2name: "+founder2name);
		
		String founder3name=request.getParameter("founder3name");
		System.out.println("founder3name: "+founder3name);
		
		String founder4name=request.getParameter("founder4name");
		System.out.println("founder4name: "+founder4name);
		
		String companyid=request.getParameter("companyid");
		System.out.println("companyid: "+companyid);
		
		String investoremail=request.getParameter("investoremail");
		System.out.println("investoremail: "+investoremail);
		
		String founderemail=request.getParameter("founderemail");
		System.out.println("founderemail: "+founderemail);
		
	 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		investmentbean in=new investmentbean();

		in.setCompanyid(companyid);
		in.setCompanyname(companyname);
		in.setTotalshares(totalshares);
		in.setED_founder1(ED_founder1);
		in.setED_founder2(ED_founder2);
		in.setED_founder3(ED_founder3);
		in.setED_founder4(ED_founder4);
		in.setTotalinvest(totalinvest);
		in.setFounder1shares(founder1shares);
		in.setFounder2shares(founder2shares);
		in.setFounder3shares(founder3shares);
		in.setFounder4shares(founder4shares);
		in.setInvestamount(investamount);
		in.setInvestED(investED);
		in.setFieldtype(fieldtype);
		in.setFounder1invest(founder1invest);
		in.setFounder2invest(founder2invest);
		in.setFounder3invest(founder3invest);
		in.setFounder4invest(founder4invest);
		in.setFounder1name(founder1name);
		in.setFounder2name(founder2name);
		in.setFounder3name(founder3name);
		in.setFounder4name(founder4name);
		in.setNooffounders(nooffounders);
		in.setInvestoremail(investoremail);
		in.setFounderemail(founderemail);
		
				
		int nooffounders1 = Integer.parseInt(nooffounders);
	
		int investorshares = Integer.parseInt(investED);
		System.out.println(investorshares);
		
		
		
		
		
		
		
		
		Inter im = new Imple();
		int i = im.investment(in);
		if (i == 1) {
			
			
			switch (nooffounders1) {

			case 1: {

				double ED_1founder = Double.parseDouble(ED_founder1);

				int firstfoundershares= Integer.parseInt(founder1shares);

				double sharesbeforeinvestor= Double.parseDouble(totalshares);
				
				double intialvaluation=  Double.parseDouble(totalinvest);
				
				int investoramuntinvest= Integer.parseInt(investamount);
 
				
				
				
				
				double totalSharesAfterInvestment =investorshares+firstfoundershares;
				System.out.println(totalSharesAfterInvestment);
				
				double ED_Investors=((investorshares/totalSharesAfterInvestment)*100);
						
				double companyvaluation=((investoramuntinvest/ED_Investors)*100);
						
				 System.out.println(companyvaluation);
				
				
				
				
				  double founderEquities1 = (((1 - ( investorshares / totalSharesAfterInvestment))*(ED_1founder/100))*100);
				 
				  
				  System.out.println(founderEquities1);
		

				
				
				
				try {
					Connection con1 = (Connection) Dbconn.create();
					Statement st = (Statement) con1.createStatement();
					st.executeUpdate("UPDATE investment.investment i SET totalshares='" + totalSharesAfterInvestment+ "' ,totalinvest='"+totalinvest+"' , ED_Founder1='" + founderEquities1 + "' ,Company Valuation='"+companyvaluation+"'  WHERE founderemail='" + founderemail+ "' AND investoremail='" + investoremail+ "' ");

				} catch (Exception e) {
					System.out.println(e);
				}
				

				try {
					Connection con2 = (Connection) Dbconn.create();
					Statement st1 = (Statement) con2.createStatement();
					st1.executeUpdate("UPDATE investment.companyprofile c SET totalshares='" + totalSharesAfterInvestment+ "' , ED_Founder1='" + founderEquities1 + "'  WHERE founderemail='" + founderemail+ "' ");

				} catch (Exception e) {
					System.out.println(e);
				}
				
				try {
					Connection con3 = (Connection) Dbconn.create();
					Statement st3 = (Statement) con3.createStatement();
					st3.executeUpdate("UPDATE investment.investorsinvest i SET Equity='" + ED_Investors+ "'  WHERE compyfoundemail='" + founderemail+ "' AND investorsemail='"+investoremail+"' ");

				} catch (Exception e) {
					System.out.println(e);
				}

			}
				break;

			case 2: {	

				double ED_1founder = Double.parseDouble(ED_founder1);
				System.out.println("ED1:"+ED_1founder);
				double ED_2founder = Double.parseDouble(ED_founder2);

				int firstfoundershares= Integer.parseInt(founder1shares);
				
				int secondfoundershares= Integer.parseInt(founder2shares);

				double sharesbeforeinvestor=Double.parseDouble(totalshares);
				
				double intialvaluation=  Double.parseDouble(totalinvest);
				
				int investoramuntinvest= Integer.parseInt(investamount);
 
				
				
				
				
				double totalSharesAfterInvestment =investorshares+firstfoundershares+secondfoundershares;
				System.out.println(totalSharesAfterInvestment);
			
				
				double ED_Investors=((investorshares/totalSharesAfterInvestment)*100);
					
				
				
				double companyvaluation=((investoramuntinvest/ED_Investors)*100);
						
				 System.out.println(companyvaluation);
				
				
				
				
				  double founderEquities1 = (((1 - ( investorshares / totalSharesAfterInvestment))*(ED_1founder/100))*100);
				 
				  System.out.println(founderEquities1);
				  
				  double founderEquities2 = (((1 - ( investorshares / totalSharesAfterInvestment))*(ED_2founder/100))*100);
				  
				  System.out.println(founderEquities2);
		

					try {
						Connection con1 = (Connection) Dbconn.create();
						Statement st = (Statement) con1.createStatement();
						st.executeUpdate("UPDATE investment.investment i SET totalshares='" + totalSharesAfterInvestment+ "' ,totalinvest='"+totalinvest+"' , ED_Founder1='" + founderEquities1 + "' , ED_Founder2='" + founderEquities2 + "',Company Valuation='"+companyvaluation+"'  WHERE founderemail='" + founderemail+ "' AND investoremail='" + investoremail+ "' ");

					} catch (Exception e) {
						System.out.println(e);
					}
					

					try {
						Connection con2 = (Connection) Dbconn.create();
						Statement st1 = (Statement) con2.createStatement();
						st1.executeUpdate("UPDATE investment.companyprofile c SET totalshares='" + totalSharesAfterInvestment+ "' ,totalinvest='"+totalinvest+"' , ED_Founder1='" + founderEquities1 + "', ED_Founder2='" + founderEquities2 + "'  WHERE founderemail='" + founderemail+ "' ");

					} catch (Exception e) {
						System.out.println(e);
					}
					
					try {
						Connection con3 = (Connection) Dbconn.create();
						Statement st3 = (Statement) con3.createStatement();
						st3.executeUpdate("UPDATE investment.investorsinvest i SET Equity='" + ED_Investors+ "'  WHERE compyfoundemail='" + founderemail+ "' AND investorsemail='"+investoremail+"' ");

					} catch (Exception e) {
						System.out.println(e);
					}

					
			}
				break;

			case 3: {

				double ED_1founder = Double.parseDouble(ED_founder1);
				
				double ED_2founder = Double.parseDouble(ED_founder2);
				
				double ED_3founder = Double.parseDouble(ED_founder3);

				int firstfoundershares= Integer.parseInt(founder1shares);
				
				int secondfoundershares= Integer.parseInt(founder2shares);
				
				int thirdfoundershares= Integer.parseInt(founder3shares);

				double sharesbeforeinvestor= Double.parseDouble(totalshares);
				
				double intialvaluation=  Double.parseDouble(totalinvest);
				
				int investoramuntinvest= Integer.parseInt(investamount);
 
				
				
				
				
				double totalSharesAfterInvestment =investorshares+firstfoundershares+secondfoundershares+thirdfoundershares;
				System.out.println(totalSharesAfterInvestment);
				
				double ED_Investors=((investorshares/totalSharesAfterInvestment)*100);
						
				double companyvaluation=((investoramuntinvest/ED_Investors)*100);
						
				
				
				
				
				
				  double founderEquities1 = (((1 - ( investorshares / totalSharesAfterInvestment))*(ED_1founder/100))*100);
				  System.out.println(founderEquities1);
				  
				  double founderEquities2 = (((1 - ( investorshares / totalSharesAfterInvestment))*(ED_2founder/100))*100);
				  System.out.println(founderEquities2);
				  
				  double founderEquities3 = (((1 - ( investorshares / totalSharesAfterInvestment))*(ED_3founder/100))*100);
				  System.out.println(founderEquities3);
		
				  try {
						Connection con1 = (Connection) Dbconn.create();
						Statement st = (Statement) con1.createStatement();
						st.executeUpdate("UPDATE investment.investment i SET totalshares='" + totalSharesAfterInvestment+ "' ,totalinvest='"+totalinvest+"' , ED_Founder1='" + founderEquities1 + "' , ED_Founder2='" + founderEquities2 + "', ED_Founder=3='" + founderEquities3 + "',Company Valuation='"+companyvaluation+"'  WHERE founderemail='" + founderemail+ "' AND investoremail='" + investoremail+ "' ");

					} catch (Exception e) {
						System.out.println(e);
					}
					

					try {
						Connection con2 = (Connection) Dbconn.create();
						Statement st1 = (Statement) con2.createStatement();
						st1.executeUpdate("UPDATE investment.companyprofile c SET totalshares='" + totalSharesAfterInvestment+ "' ,totalinvest='"+totalinvest+"' , ED_Founder1='" + founderEquities1 + "', ED_Founder2='" + founderEquities2 + "', ED_Founder3='" + founderEquities3 + "'  WHERE founderemail='" + founderemail+ "' ");

					} catch (Exception e) {
						System.out.println(e);
					}
					
					try {
						Connection con3 = (Connection) Dbconn.create();
						Statement st3 = (Statement) con3.createStatement();
						st3.executeUpdate("UPDATE investment.investorsinvest i SET Equity='" + ED_Investors+ "'  WHERE compyfoundemail='" + founderemail+ "' AND investorsemail='"+investoremail+"' ");

					} catch (Exception e) {
						System.out.println(e);
					}


			}
				break;

			case 4: {

				
				double ED_1founder = Double.parseDouble(ED_founder1);
				
				double ED_2founder =Double.parseDouble(ED_founder2);
				
				double ED_3founder = Double.parseDouble(ED_founder3);
				
				double ED_4founder = Double.parseDouble(ED_founder4);

				int firstfoundershares= Integer.parseInt(founder1shares);
				
				int secondfoundershares= Integer.parseInt(founder2shares);
				
				int thirdfoundershares= Integer.parseInt(founder3shares);

				int fourthfoundershares= Integer.parseInt(founder4shares);
				
				double sharesbeforeinvestor= Double.parseDouble(totalshares);
				
				double intialvaluation=  Double.parseDouble(totalinvest);
				
				int investoramuntinvest= Integer.parseInt(investamount);
 
				
				
				
				
				double totalSharesAfterInvestment =investorshares	+firstfoundershares+secondfoundershares+thirdfoundershares+fourthfoundershares;
				System.out.println(totalSharesAfterInvestment);
				
				double ED_Investors=((investorshares/totalSharesAfterInvestment)*100);
						
				double companyvaluation=((investoramuntinvest/ED_Investors)*100);
						
				
				
				
				
				
				  double founderEquities1 = (((1 - ( investorshares / totalSharesAfterInvestment))*(ED_1founder/100))*100);
				 
				  System.out.println(founderEquities1);
				  
				  double founderEquities2 = (((1 - ( investorshares / totalSharesAfterInvestment))*(ED_2founder/100))*100);
					 
				  System.out.println(founderEquities1);
				  
				  
				  double founderEquities3 = (((1 - ( investorshares / totalSharesAfterInvestment))*(ED_3founder/100))*100);
					 
				  System.out.println(founderEquities1);
				  
				  
				  double founderEquities4 = (((1 - ( investorshares / totalSharesAfterInvestment))*(ED_4founder/100))*100);
					 
				  System.out.println(founderEquities1);
		
				  
				  try {
						Connection con1 = (Connection) Dbconn.create();
						Statement st = (Statement) con1.createStatement();
						st.executeUpdate("UPDATE investment.investment i SET totalshares='" + totalSharesAfterInvestment+ "' ,totalinvest='"+totalinvest+"' , ED_Founder1='" + founderEquities1 + "' , ED_Founder2='" + founderEquities2 + "', ED_Founder=3='" + founderEquities3 + "', ED_Founder=4='" + founderEquities3 + "',Company Valuation='"+companyvaluation+"'  WHERE founderemail='" + founderemail+ "' AND investoremail='" + investoremail+ "' ");

					} catch (Exception e) {
						System.out.println(e);
					}
					

					try {
						Connection con2 = (Connection) Dbconn.create();
						Statement st1 = (Statement) con2.createStatement();
						st1.executeUpdate("UPDATE investment.companyprofile c SET totalshares='" + totalSharesAfterInvestment+ "' ,totalinvest='"+totalinvest+"' , ED_Founder1='" + founderEquities1 + "', ED_Founder2='" + founderEquities2 + "', ED_Founder3='" + founderEquities3 + "', ED_Founder4='" + founderEquities4 + "'  WHERE founderemail='" + founderemail+ "' ");

					} catch (Exception e) {
						System.out.println(e);
					}
					
					try {
						Connection con3 = (Connection) Dbconn.create();
						Statement st3 = (Statement) con3.createStatement();
						st3.executeUpdate("UPDATE investment.investorsinvest i SET Equity='" + ED_Investors+ "'  WHERE compyfoundemail='" + founderemail+ "' AND investorsemail='"+investoremail+"' ");

					} catch (Exception e) {
						System.out.println(e);
					}


			}
				break;

			}
			response.sendRedirect("success.jsp");
		} else {
			response.sendRedirect("error.jsp");
		}
			
	}

	}
