package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DbConnection;

@WebServlet("/edit_user_data")
public class EditUserDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int status = 0;
			Connection con = DbConnection.getConnection();
			PreparedStatement st = (PreparedStatement) con.prepareStatement(
					"update user set Fam_head=?,DOJ=?,noofmem=?,email=?,phone=?,username=?,pass=? where D_no=?");
			st.setString(1, request.getParameter("fam_head"));
			st.setString(2, request.getParameter("DOJ"));
			st.setString(3, request.getParameter("fam_no"));
			st.setString(4, request.getParameter("email"));
			st.setString(5, request.getParameter("phone"));
			st.setString(6, request.getParameter("user_name"));
			st.setString(7, request.getParameter("pass"));
			st.setString(8, request.getParameter("D_no"));
			status = st.executeUpdate();

			PrintWriter out = response.getWriter();
			if(status > 0) {
				 out.write("success");  
			}else {
				 out.write("fail");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
