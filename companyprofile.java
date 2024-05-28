package servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.lowagie.text.Element;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

import Bean.companyprofilebean;
import Dbcon.Dbconn;
import Imple.Imple;
import Inter.Inter;

/**
 * Servlet implementation class companyprofile
 */
@WebServlet("/companyprofile")
public class companyprofile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public companyprofile() {
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
		MultipartParser mp = new MultipartParser(request, 999999999);

		Part part = null;
		ArrayList paramValues = new ArrayList();

		FilePart filepart = null;
		ParamPart param = null;
		File file1 = null;
		String filepath1 = null;
		String filetype = null;
		String filepath2 = null;
		String filename = null;

		long size = 0;
		String path = getServletContext().getRealPath("");

		System.out.println("path==" + path);

		String editpath = path.substring(0, path.indexOf("."));

		System.out.println("edithpath==" + editpath);

		String fullpath = editpath + "startupinvestment\\WebContent\\forms\\Local\\";

		System.out.println("fullpath==" + fullpath);

		while ((part = mp.readNextPart()) != null) {

			if (part.isFile()) {

				filepart = (FilePart) part;

				filename = filepart.getFileName();
				System.out.println("filename==" + filename);

				fullpath = fullpath + filename;
				System.out.println("fullpath==" + fullpath);

				File file = new File(fullpath);
				size = filepart.writeTo(file);
				System.out.println("size==" + size);

				filetype = filepart.getContentType();
				System.out.println("filetype---" + filetype);

			} else if (part.isParam()) {
				param = (ParamPart) part;
				String tagName = param.getName();
				System.out.println("tagName ============= " + tagName);
				String tagValue = param.getStringValue();
				System.out.println("tagValue ************ " + tagValue);

				paramValues.add(tagValue);
				paramValues.add(tagName);

			}

		}
		// FileInputStrean get bytes from file

		String filecontent = "";
		String encrpt = null;
		String encontent = null;

		if (filename.endsWith(".txt")) {// if open

			// file encrypted and store into filepath1

			FileInputStream fis = new FileInputStream(fullpath);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			String reading = new String(b);
			filecontent = filecontent + reading;
			System.out.println("filecontent=" + filecontent);

			try {// try1 open

				encontent = AES.encrypt99(filecontent);
				System.out.println("encontent====" + encontent);
				filepath1 = editpath + "\\startupinvestment\\WebContent\\Encrypt\\" + filename;
				file1 = new File(filepath1);
				file1.createNewFile();
				if (!file1.exists()) {
					file1.createNewFile();
				} // If file doesn't exists, then create it
				FileWriter fw = new FileWriter(file1.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(encontent);// Write in file
				bw.close();// Close connection
				System.out.println("fileeeeeeeeeeeeeeeee" + filepath1);

				// file decrypted and store into filepath2
				String decontent = AES.decrypt(encontent);
				System.out.println("decontent====" + decontent);
				filepath2 = editpath + "\\startupinvestment\\WebContent\\Decrypt\\" + filename;
				File file2 = new File(filepath2);
				file2.createNewFile();
				if (!file1.exists()) {
					file1.createNewFile();
				} // If file doesn't exists, then create it
				FileWriter fw1 = new FileWriter(file2.getAbsoluteFile());
				BufferedWriter bw1 = new BufferedWriter(fw1);
				bw1.write(decontent);// Write in file
				bw1.close();// Close connection
				System.out.println("fileeeeeeeeeeeeeeeee" + filepath2);

			} catch (Exception e) {

				e.printStackTrace();
			}

		} else if (filename.endsWith(".docx")) {
			WordExtractor extractor = null;

			FileInputStream fis2 = new FileInputStream(fullpath);
			System.out.println("file is" + fis2);
			HWPFDocument document = new HWPFDocument(fis2);
			extractor = new WordExtractor(document);
			String[] fileData = extractor.getParagraphText();
			String mydate = extractor.getTextFromPieces();
			System.out.println("DATASSSSSSSSSSSSSSSSSss " + fileData);
			System.out.println("THE MYYYYYYYYYYY " + mydate);
			String text1 = null;
			System.out.println("filedata len  " + fileData.length);
		}

		else if (filename.endsWith(".pdf")) {
			// System.out.println("padf file name"+file1.getName());

			PdfReader pdfReader = new PdfReader(fullpath);
			String Text7 = null;
			int pages = pdfReader.getNumberOfPages();
			for (int i1 = 1; i1 < pages; i1++) {
				filecontent = PdfTextExtractor.getTextFromPage(pdfReader, i1);
				System.out.println("page:" + i1 + " " + filecontent);
				Text7 = Text7 + filecontent;

			}
			System.out.println("Pdf full content =" + Text7);

			try {
				encrpt = Encryptdata.encrypt(filecontent);
				Document document = new Document(PageSize.A4);
				/*
				 * File file=new File("C:\\Users\\spiro13\\Desktop\\naga\\"+pdffilename);
				 * System.out.println(file.delete());
				 */
				PdfWriter.getInstance(document, new FileOutputStream(new File(filepath2 + filename)));
				// FileOutputStream fileOutputStream=new
				// FileOutputStream("C:\\Users\\spiro13\\Desktop\\naga\\pdffilename1.pdf");
				Chunk chunk = new Chunk(encrpt);
				document.open();
				Font font = new Font();
				font.setStyle(Font.BOLD);
				Paragraph p1 = new Paragraph(chunk);
				p1.setAlignment(Element.ALIGN_LEFT);
				document.add(p1);
				document.close();
				System.out.println("pdf writing is completed");
				// fileOutputStream.write(encrpt.getBytes());
				System.out.println(encrpt);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {

				// try1 open

				encontent = AES.encrypt99(filecontent);
				System.out.println("encontent====" + encontent);
				filepath1 = editpath + "\\startupinvestment\\WebContent\\Encrypt\\" + filename;
				file1 = new File(filepath1);
				file1.createNewFile();
				if (!file1.exists()) {
					file1.createNewFile();
				} // If file doesn't exists, then create it
				FileWriter fw = new FileWriter(file1.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(encontent);// Write in file
				bw.close();// Close connection
				System.out.println("fileeeeeeeeeeeeeeeee" + filepath1);

				// file decrypted and store into filepath2
				String decontent = AES.decrypt(encontent);
				System.out.println("decontent====" + decontent);
				filepath2 = editpath + "\\startupinvestment\\WebContent\\Decrypt\\" + filename;
				File file2 = new File(filepath2);
				file2.createNewFile();
				if (!file1.exists()) {
					file1.createNewFile();
				} // If file doesn't exists, then create it
				FileWriter fw1 = new FileWriter(file2.getAbsoluteFile());
				BufferedWriter bw1 = new BufferedWriter(fw1);
				bw1.write(decontent);// Write in file
				bw1.close();// Close connection
				System.out.println("fileeeeeeeeeeeeeeeee" + filepath2);

			} // try close

			catch (Exception e) {
				System.out.println(e);
			}

		}

		companyprofilebean cp = new companyprofilebean();

		cp.setImplementdate(paramValues.get(0).toString());
		System.out.println("Date: " + paramValues.get(0));

		cp.setTotalamountinvest(paramValues.get(2).toString());
		System.out.println("InvestAmount: " + paramValues.get(2));

		cp.setTotalshares(paramValues.get(4).toString());
		System.out.println("Totalshare: " + paramValues.get(4));

		cp.setNooffounders(paramValues.get(6).toString());
		System.out.println("NoFounder: " + paramValues.get(6));

		cp.setFoundername1(paramValues.get(8).toString());
		System.out.println("Foundername1: " + paramValues.get(8));

		cp.setFoundername2(paramValues.get(10).toString());
		System.out.println("Foundername2: " + paramValues.get(10));

		cp.setFoundername3(paramValues.get(12).toString());
		System.out.println("Foundername3: " + paramValues.get(12));

		cp.setFoundername4(paramValues.get(14).toString());
		System.out.println("Foundername4: " + paramValues.get(14));

		cp.setFounder1invest(paramValues.get(16).toString());
		System.out.println("invest: " + paramValues.get(16));

		cp.setFounder2invest(paramValues.get(18).toString());
		System.out.println("invest: " + paramValues.get(18));

		cp.setFounder3invest(paramValues.get(20).toString());
		System.out.println("invest: " + paramValues.get(20));

		cp.setFounder4invest(paramValues.get(22).toString());
		System.out.println("invest: " + paramValues.get(22));

		cp.setFounder1shares(paramValues.get(24).toString());
		System.out.println("shares: " + paramValues.get(24));

		cp.setFounder2shares(paramValues.get(26).toString());
		System.out.println("shares: " + paramValues.get(26));

		cp.setFounder3shares(paramValues.get(28).toString());
		System.out.println("shares: " + paramValues.get(28));

		cp.setFounder4shares(paramValues.get(30).toString());
		System.out.println("shares: " + paramValues.get(30));

		cp.setFounderemail(paramValues.get(32).toString());
		System.out.println("email: " + paramValues.get(32));
		
		cp.setCompanyid(paramValues.get(34).toString());
		System.out.println("Companyid: " + paramValues.get(34));

		cp.setCompanyname(paramValues.get(36).toString());
		System.out.println("Companyname: " + paramValues.get(36));

		cp.setFieldtype(paramValues.get(38).toString());
		System.out.println("Fieldtype: " + paramValues.get(38));

		cp.setFilename(filename);
		System.out.println("Filename: " + filename);

		String founderemail = paramValues.get(32).toString();

		String numberoffounder = paramValues.get(6).toString();

		String firstfounderinvest = paramValues.get(16).toString();
		String secondfounderinvest = paramValues.get(18).toString();
		String thirdfounderinvest = paramValues.get(20).toString();
		String fourthfounderinvest = paramValues.get(22).toString();
   
		String firstfoundershares = paramValues.get(24).toString();
		String secondfoundershares = paramValues.get(26).toString();
		String thirdfoundershares = paramValues.get(28).toString();
		String fourthfoundershares = paramValues.get(30).toString();

		int numberoffounders = Integer.parseInt(numberoffounder);

		/*
		 * int founder1invest = Integer.parseInt(firstfounderinvest); int founder2invest
		 * = Integer.parseInt(secondfounderinvest); int founder3invest =
		 * Integer.parseInt(thirdfounderinvest); int founder4invest =
		 * Integer.parseInt(fourthfounderinvest);
		 * 
		 * int founder1shares = Integer.parseInt(firstfoundershares); int founder2shares
		 * = Integer.parseInt(secondfoundershares); int founder3shares =
		 * Integer.parseInt(thirdfoundershares); int founder4shares =
		 * Integer.parseInt(fourthfoundershares);
		 */

		/*
		 * int totalshares=founder1shares+founder2shares+founder3shares+founder4shares;
		 */

		java.sql.Connection con;
		try {

			con = Dbconn.create();

			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement("SELECT * FROM investment.companyprofile c");

			ResultSet rs = (ResultSet) ps.executeQuery();
			String founderemail1 = " ";
			while (rs.next()) {
				founderemail1 = rs.getString(17);

			}

			if (founderemail1.equals(paramValues.get(32))) {
				response.sendRedirect("alreadyregistered.jsp");

			} else {

				Inter in = new Imple();
				int i = in.comprofile(cp);
				if (i == 1) {

					switch (numberoffounders) {

					case 1: {

						int founder1invest = Integer.parseInt(firstfounderinvest);

						int founder1shares = Integer.parseInt(firstfoundershares);
						System.out.println(founder1shares);

						double totalshares = founder1shares;
						System.out.println(totalshares);

						double totalinvest = founder1invest;
						System.out.println(totalshares);
						
						
						double equityDilution1 = (founder1shares / totalshares) * 100;
						System.out.println(equityDilution1);

						
						
						
						try {
							Connection con1 = (Connection) Dbconn.create();
							Statement st = (Statement) con1.createStatement();
							st.executeUpdate("UPDATE investment.companyprofile c SET totalshares='" + totalshares+ "' ,totalinvest='"+totalinvest+"' , ED_Founder1='" + equityDilution1 + "'  WHERE founderemail='" + founderemail+ "' ");

						} catch (Exception e) {
							System.out.println(e);
						}

					}
						break;

					case 2: {

						int founder1invest = Integer.parseInt(firstfounderinvest);
						int founder2invest = Integer.parseInt(secondfounderinvest);

						int founder1shares = Integer.parseInt(firstfoundershares);
						System.out.println(founder1shares);
						int founder2shares = Integer.parseInt(secondfoundershares);
						System.out.println(founder2shares);

						double  totalshares = founder1shares + founder2shares;
						System.out.println(totalshares);

						double totalinvest = founder1invest+founder2invest;
						System.out.println(totalshares);
						
						double  equityDilution1 = (founder1shares / totalshares) * 100;
						double  equityDilution2 = (founder2shares / totalshares) * 100;

						System.out.println(equityDilution1);
						System.out.println(equityDilution2);

						try {
							Connection con2 = (Connection) Dbconn.create();
							Statement st = (Statement) con2.createStatement();
							st.executeUpdate("UPDATE investment.companyprofile c SET totalshares='" + totalshares+ "',totalinvest='"+totalinvest+"' , ED_Founder1='" + equityDilution1 + "' , ED_Founder2='" + equityDilution2+ "'  WHERE  founderemail='" + founderemail + "' ");

						} catch (Exception e) {
							System.out.println(e);
						}

					}
						break;

					case 3: {

						int founder1invest = Integer.parseInt(firstfounderinvest);
						int founder2invest = Integer.parseInt(secondfounderinvest);
						int founder3invest = Integer.parseInt(thirdfounderinvest);

						int founder1shares = Integer.parseInt(firstfoundershares);
						System.out.println(founder1shares);
						int founder2shares = Integer.parseInt(secondfoundershares);
						System.out.println(founder2shares);
						int founder3shares = Integer.parseInt(thirdfoundershares);
						System.out.println(founder3shares);
						double totalshares = founder1shares + founder2shares + founder3shares;
						System.out.println(totalshares);

						double totalinvest = founder1invest+founder2invest+founder3invest;
						System.out.println(totalshares);
						
						double equityDilution1 = (founder1shares / totalshares) * 100;
						double equityDilution2 = (founder2shares / totalshares) * 100;
						double equityDilution3 = (founder3shares / totalshares) * 100;
						System.out.println(equityDilution1);
						System.out.println(equityDilution2);
						System.out.println(equityDilution3);

						try {
							Connection con3 = (Connection) Dbconn.create();
							Statement st = (Statement) con3.createStatement();
							st.executeUpdate("UPDATE investment.companyprofile c SET totalshares='" + totalshares+ "' ,totalinvest='"+totalinvest+"' , ED_Founder1='" + equityDilution1 + "' , ED_Founder2='" + equityDilution2+ "', ED_Founder3='" + equityDilution3 + "'  WHERE founderemail='" + founderemail+ "' ");

						} catch (Exception e) {
							System.out.println(e);
						}

					}
						break;

					case 4: {

						int founder1invest = Integer.parseInt(firstfounderinvest);
						int founder2invest = Integer.parseInt(secondfounderinvest);
						int founder3invest = Integer.parseInt(thirdfounderinvest);
						int founder4invest = Integer.parseInt(fourthfounderinvest);

						int founder1shares = Integer.parseInt(firstfoundershares);
						System.out.println(founder1shares);
						int founder2shares = Integer.parseInt(secondfoundershares);
						System.out.println(founder2shares);
						int founder3shares = Integer.parseInt(thirdfoundershares);
						System.out.println(founder3shares);
						int founder4shares = Integer.parseInt(fourthfoundershares);
						System.out.println(founder4shares);

						double totalshares = founder1shares + founder2shares + founder3shares + founder4shares;
						System.out.println(totalshares);

						double totalinvest = founder1invest+founder2invest+founder3invest+founder4invest;
						System.out.println(totalshares);
						
						double equityDilution1 = (founder1shares / totalshares) * 100;
						double equityDilution2 = (founder2shares / totalshares) * 100;
						double equityDilution3 = (founder3shares / totalshares) * 100;
						double equityDilution4 = (founder4shares / totalshares) * 100;

						System.out.println(equityDilution1);
						System.out.println(equityDilution2);
						System.out.println(equityDilution3);
						System.out.println(equityDilution4);

						try {
							Connection con4 = (Connection) Dbconn.create();
							Statement st = (Statement) con4.createStatement();
							st.executeUpdate("UPDATE investment.companyprofile c SET  totalshares='" + totalshares+ "' ,totalinvest='"+totalinvest+"' , ED_Founder1='" + equityDilution1 + "' , ED_Founder2='" + equityDilution2+ "', ED Founder3='" + equityDilution3 + "' , ED_Founder4='" + equityDilution4+ "'  WHERE founderemail='" + founderemail + "' ");

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

		} catch (Exception e) {

		}
	}

}
