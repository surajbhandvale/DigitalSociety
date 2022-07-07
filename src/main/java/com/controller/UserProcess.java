package com.controller;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.DbConnection;
import com.modal.Payment_history;

public class UserProcess {
	
	public static List<Payment_history> getAllRecords_accepted_byid(String D_no) throws SQLException{
		List<Payment_history> list=new ArrayList<Payment_history>();
			Connection con = DbConnection.getConnection();
			PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from temp where pay_stage='accepted' and D_no='"+D_no+"'");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Payment_history ph = new Payment_history();
				ph.setMonth_charge(rs.getString(2));
				ph.setExtra_charge(rs.getString(3));
				ph.setFine(rs.getString(4));
				ph.setPaid_date(rs.getString(6));
				list.add(ph);
			}
			// from main table
			PreparedStatement ps2=(PreparedStatement) con.prepareStatement("select * from main where pay_stage='accepted' and D_no='"+D_no+"'");
			ResultSet rs2=ps2.executeQuery();
			while(rs2.next()) {
				Payment_history ph = new Payment_history();
				ph.setMonth_charge(rs2.getString(2));
				ph.setExtra_charge(rs2.getString(3));
				ph.setFine(rs2.getString(4));
				ph.setPaid_date(rs2.getString(6));
				list.add(ph);
			}

		return list;
	}
	
}
