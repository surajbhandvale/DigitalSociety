package com.controller;

import java.io.IOException;
import java.sql.*;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DbConnection;

@WebServlet("/user_info")
public class UserProcessUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String D_no = request.getParameter("D_no");
			Connection con = DbConnection.getConnection();
			Statement st = (Statement) con.createStatement();
			
			ResultSet rs = st.executeQuery("select * from user where D_no='"+D_no+"'");
			while(rs.next()) {
				HttpSession session = request.getSession();
				
				session.setAttribute("D_no",rs.getString(1));
				session.setAttribute("Fam_head",rs.getString(2));
				session.setAttribute("DOJ",rs.getString(3));
				session.setAttribute("noofmem",rs.getString(4));
				session.setAttribute("email",rs.getString(5));
				session.setAttribute("phone",rs.getString(6));
				session.setAttribute("username",rs.getString(7));
				//session.setAttribute("pass",rs.getString(8));
			}
			RequestDispatcher rd=request.getRequestDispatcher("/display_user_info.jsp");  
	        rd.include(request, response);  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
