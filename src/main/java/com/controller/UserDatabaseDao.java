package com.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.dao.DbConnection;
import com.modal.Delay_Payment;
import com.modal.Payment_Range;
import com.modal.User;

public class UserDatabaseDao {

	public static List<User> getAllRecords() throws SQLException {
		List<User> list = new ArrayList<User>();
		Connection con = DbConnection.getConnection();
		PreparedStatement ps = (PreparedStatement) con.prepareStatement("select * from user order by D_no");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			User u = new User();
			u.setDoor_num(rs.getString(1));
			u.setFam_head(rs.getString(2));
			u.setDOJ(rs.getString(3));
			u.setFam_num(rs.getString(4));
			u.setEmail(rs.getString(5));
			u.setPhone(rs.getString(6));
			u.setUser_name(rs.getString(7));
			u.setPass(rs.getString(8));
			list.add(u);
		}
		return list;
	}

	public static User getRecordById(String D_no) throws SQLException {
		User u = null;
		Connection con = DbConnection.getConnection();
		PreparedStatement ps = (PreparedStatement) con.prepareStatement("select * from user where D_no='" + D_no + "'");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			u = new User();
			u.setDoor_num(rs.getString(1));
			u.setFam_head(rs.getString(2));
			u.setDOJ(rs.getString(3));
			u.setFam_num(rs.getString(4));
			u.setEmail(rs.getString(5));
			u.setPhone(rs.getString(6));
			u.setUser_name(rs.getString(7));
			u.setPass(rs.getString(8));
		}
		return u;
	}

	public static List<Payment_Range> get_payment_range(String start, String end) throws SQLException {
		List<Payment_Range> list = new ArrayList<Payment_Range>();
		Connection con = DbConnection.getConnection();
		PreparedStatement st = (PreparedStatement) con.prepareStatement("select * from temp where paid_date between '"
				+ start + "' and '" + end + "' and pay_stage='accepted' order by paid_date");
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			Payment_Range pr = new Payment_Range();
			pr.setD_no(rs.getString(1));
			pr.setMonth_charge(rs.getString(2));
			pr.setExtra_charge(rs.getString(3));
			pr.setFine(rs.getString(4));
			pr.setPaid_date(rs.getString(6));

			list.add(pr);
		}
		return list;
	}

	public static List<Delay_Payment> get_delayed_pay() throws SQLException {
		List<Delay_Payment> list = new ArrayList<Delay_Payment>();
		Connection con = DbConnection.getConnection();
		PreparedStatement st = (PreparedStatement) con.prepareStatement("select * from temp where not fine='0'");
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			Delay_Payment dp = new Delay_Payment();
			dp.setD_no(rs.getString(1));
			dp.setMonth_charge(rs.getString(2));
			dp.setExtra_charge(rs.getString(3));
			dp.setFine(rs.getString(4));
			// dp.setPay_stage(rs.getString(5));
			dp.setPaid_date(rs.getString(6));
			PreparedStatement st2 = (PreparedStatement) con
					.prepareStatement("select * from temp where D_no='" + rs.getString(1) + "'");
			ResultSet rs2 = st2.executeQuery();
			rs2.next();
			dp.setMail(rs2.getString(5));
			dp.setPhone(rs2.getString(6));

			list.add(dp);
		}

		return list;
	}

}
