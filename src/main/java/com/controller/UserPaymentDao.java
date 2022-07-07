package com.controller;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.DbConnection;
import com.modal.Payment;
import com.modal.Temp;

public class UserPaymentDao {
	
	public static List<Payment> getAllRecords_rejected() throws SQLException{
		List<Payment> list=new ArrayList<Payment>();
		
			List<Temp> list_temp = new ArrayList<Temp>();
			Connection con = DbConnection.getConnection();
			PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from temp where pay_stage='rejected'");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Temp t = new Temp();
				t.setD_no(rs.getString(1));
				t.setMonthly_charge(rs.getString(2));
				t.setExtra_charge(rs.getString(3));
				t.setFine(rs.getString(4));
				list_temp.add(t);
			}
			for(Temp tl : list_temp) {
				PreparedStatement st = (PreparedStatement) con.prepareStatement("select * from user where D_no='"+tl.getD_no()+"'");
				ResultSet rs2 = st.executeQuery();
				
				while(rs2.next()){	
					Payment u=new Payment();
					u.setD_no(tl.getD_no());
					u.setFam_head(rs2.getString(2));
					u.setEmail(rs2.getString(5));
					u.setPhone(rs2.getString(6));
					u.setMonthly_charge(tl.getMonthly_charge());
					u.setExtra_charge(tl.getExtra_charge());
					u.setFine(tl.getFine());
					
					list.add(u);
				}
			}
		return list;
	}

}
