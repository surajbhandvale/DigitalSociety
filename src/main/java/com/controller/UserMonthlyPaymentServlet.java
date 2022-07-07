package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DbConnection;

@WebServlet("/insert_monthly_pay")
public class UserMonthlyPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int status=0;
			
			String query = "insert into temp values (?,?,?,?,?,?)";
			
			Connection con = DbConnection.getConnection();
			PreparedStatement st = (PreparedStatement) con.prepareStatement(query);
			Statement st2 = (Statement) con.createStatement();
			ResultSet rs = st2.executeQuery("select * from user");
			while(rs.next()) {
				String d_no = rs.getString(1);
				st.setString(1,d_no);
				st.setString(2,request.getParameter("monthly"));
				st.setString(3,request.getParameter("extra"));
				st.setString(4,"0");
				st.setString(5,"requested");
				st.setString(6,"NOT PAID");
				status+=st.executeUpdate();
			}
			int[] array = new int[]{1,6,7,8,9,10,110};
			for(int i:array) {
			Statement st3 = (Statement) con.createStatement();
			st3.executeUpdate("update date set state='no' where day="+i);
			}
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
