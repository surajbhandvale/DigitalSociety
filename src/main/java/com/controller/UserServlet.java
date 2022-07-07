package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DbConnection;

@WebServlet("/checkuser")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String name = request.getParameter("user_name");
			String passf = request.getParameter("user_pass");
			
			Connection con = DbConnection.getConnection();
			Statement st = (Statement) con.createStatement();
			
			if(name.equals("Admin")) {
				ResultSet rs1 = st.executeQuery("select * from Admin");
				rs1.next();
					if(name.equals(rs1.getString(1)) && passf.equals(rs1.getString(2))) {
						HttpSession session = request.getSession();
						session.setAttribute("name",name);
						session.setAttribute("username", name);
						
						RequestDispatcher rd=request.getRequestDispatcher("/admin.jsp");  
				        rd.include(request, response);  
					}
			}
			
			ResultSet rs = st.executeQuery("select * from user");
			while(rs.next()) {
				String named = rs.getString(7);
				String passd = rs.getString(8);
				if(named.equals(name) && passd.equals(passf)) {
					HttpSession session = request.getSession();
					session.setAttribute("name",name);
					session.setAttribute("Dno",rs.getString(1));
					session.setAttribute("username", name);
					
					RequestDispatcher rd=request.getRequestDispatcher("/user.jsp");  
			        rd.include(request, response);  
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("/wrong.jsp");  
        rd.include(request, response);  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
