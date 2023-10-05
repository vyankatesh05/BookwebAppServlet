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

@WebServlet("/deletePage")
public class DeletePageServlet extends HttpServlet {
	private static final String query = "delete from bookdata where id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		//get id of record to be update
		int id = Integer.parseInt(req.getParameter("id"));
		
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
			ps.setInt(1, id);
			int count = ps.executeUpdate();
			if(count==1) {
				pw.println("<h2>Record is Deleted successfully..</h2>");
			}else {
				pw.println("<h2>Record is not Deleted successfully..</h2>");
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
