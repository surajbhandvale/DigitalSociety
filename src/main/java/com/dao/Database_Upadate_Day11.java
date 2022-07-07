package com.dao;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class Database_Upadate_Day11 implements Job {

	static Connection con;

	public void startconnection() throws ClassNotFoundException, SQLException {
		con = DbConnection.getConnection();
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		try {
			startconnection();
			Statement st2 = (Statement) con.createStatement();
			ResultSet rs = st2.executeQuery("select * from date where day=11");
			rs.next();
			String str = "no";
			if ((str.equals(rs.getString("state"))) == true) {
				insert();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void insert() throws SQLException {
		String query = "update temp set fine='100' where D_no= '?'";

		PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
		Statement st2 = (Statement) con.createStatement();
		ResultSet rs = st2.executeQuery("select * from temp where pay_stage='requested' or pay_stage='rejected'");

		while (rs.next()) {
			ps.setString(1, rs.getString(1));
			ps.executeUpdate();
		}
		Statement st3 = (Statement) con.createStatement();
		st3.executeUpdate("update date set state='yes' where day=11");
	}
}
