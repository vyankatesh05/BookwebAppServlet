package com.package1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/booklist")
public class BookListServlet extends HttpServlet{
	private static final String query = "select * from bookdata";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
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
			
			ResultSet rs = ps.executeQuery();
			pw.println("<table border='1' align='center'>");
			pw.println("<tr>");
			pw.println("<th>Book ID</th>");
			pw.println("<th>Book Name</th>");
			pw.println("<th>Book Edition</th>");
			pw.println("<th>Book price</th>");
			pw.println("<th>Edit</th>");
			pw.println("<th>Delete</th>");
			pw.println("</tr>");
			
			while(rs.next()) {
				pw.println("<tr>");
				pw.println("<td>"+rs.getObject(1)+"</td>");
				pw.println("<td>"+rs.getObject(2)+"</td>");
				pw.println("<td>"+rs.getObject(3)+"</td>");
				pw.println("<td>"+rs.getObject(4)+"</td>");		
				pw.println("<td><a href='editPage?id="+rs.getObject(1)+"'>Edit</a></td>");		
				pw.println("<td><a href='deletePage?id="+rs.getObject(1)+"'>Delete</a></td>");			
				pw.println("</tr>");
			}
			pw.println("</table>");
			pw.println("<a href='home.html' >Home</a>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			pw.println("<h2>"+e+"</h2>");
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}

}
