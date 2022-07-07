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

@WebServlet("/reject_pay")
public class UserProcessRejectPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			int status=0;
			String D_no = request.getParameter("D_no");
			String query = "update temp set pay_stage='rejected' where D_no='"+D_no+"'";
			
			Connection con = DbConnection.getConnection();
			PreparedStatement st = (PreparedStatement) con.prepareStatement(query);
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
