package com.package1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registerbook")
public class RegisterServlet extends HttpServlet{
	private static final String query = "insert into bookdata(bookname, bookedition, bookprice) values(?,?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		String bookname = req.getParameter("bookName");
		String bookedition = req.getParameter("bookEdition");
		float bookprice = Float.parseFloat(req.getParameter("bookPrice"));
//		pw.println("<h2>load driver step</h2>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			pw.println("<h2>"+e+"</h2>");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practiceservlet","root","Venkiboss");
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, bookname);
			ps.setString(2, bookedition);
			ps.setFloat(3, bookprice);
			
			int count = ps.executeUpdate();
			if(count==1) {
				pw.println("<h2>Record is registered successfully..</h2>");
			}else {
				pw.println("<h2>Record is not registered successfully..</h2>");
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			pw.println("<h2>"+e+"</h2>");
			e.printStackTrace();
		}
		
		pw.println("<a href='home.html' >Home</a>");
		pw.println("<br>");
		pw.println("<a href='booklist'>Book List</a>");
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}
}
