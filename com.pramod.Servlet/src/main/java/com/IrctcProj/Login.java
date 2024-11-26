package com.IrctcProj;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/login")
public class Login extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				String user_name1="";
				//String password1="";
				Connection con = null;
				PrintWriter pr=resp.getWriter();
//				pr.print("<h1>"+username+"</h1>");
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/server_1", "root", "root");
					String query = "select username,password from irctc where username=? and password=?";
					PreparedStatement pstm = con.prepareStatement(query);
					pstm.setString(1, username);
					pstm.setString(2, password);
					ResultSet rs=pstm.executeQuery();
					while(rs.next()) {
						user_name1=rs.getString("username");
					}
					if(user_name1.equals(username)) {
						RequestDispatcher rd=req.getRequestDispatcher("Irctc_Clone.html");
						rd.forward(req, resp);
					}
					
					else {
						RequestDispatcher rd=req.getRequestDispatcher("Login.html");
						rd.include(req, resp);
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}

	}
}
