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

@WebServlet("/delete_user_meth")
public class DeleteUserDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int status = 0;
			String D_no = request.getParameter("D_no");
			String query = "delete from user where D_no='" + D_no + "'";
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			status = ps.executeUpdate();
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
