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

@WebServlet("/pay")
public class UserProcessPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			int status=0;
			int mon_charge=0;
			int extra_charge=0;
			int fine=0;
			String D_no = request.getParameter("D_no");
			Connection con = DbConnection.getConnection();
			Statement st = (Statement) con.createStatement();
			Statement st2 = (Statement) con.createStatement();
			
			ResultSet rs = st.executeQuery("select * from temp where D_no = '"+D_no+"'");
			ResultSet rs2 = st2.executeQuery("select * from main where D_no = '"+D_no+"'");
			
			while(rs.next()) {
				if(rs.getString(5).equals("requested")) {
					mon_charge += Integer.parseInt(rs.getString(2));
					extra_charge += Integer.parseInt(rs.getString(3));
					String f = rs.getString(4);
					if(f!=null)
						fine += Integer.parseInt(f);
					status++;
				}
			}
			while(rs2.next()) {
				if(rs2.getString(5).equals("requested")) {
					mon_charge += Integer.parseInt(rs2.getString(2));
					extra_charge += Integer.parseInt(rs2.getString(3));
					fine += Integer.parseInt(rs2.getString(4));
					status++;
				}
			}
			if(status>0) {
				HttpSession session = request.getSession();
				session.setAttribute("D_no",D_no);
				session.setAttribute("mon_charge",Integer.toString(mon_charge));
				session.setAttribute("extra_charge",Integer.toString(extra_charge));
				session.setAttribute("fine",Integer.toString(fine));
				RequestDispatcher rd=request.getRequestDispatcher("/checker.jsp");  
				rd.include(request, response);  
			}
			else {
				RequestDispatcher rd=request.getRequestDispatcher("/nodue.jsp");  
				rd.include(request, response);  
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
