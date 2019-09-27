-package com.facebookweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.facebookweb.entity.FacebookEmployee;

public class FacebookDAO implements FacebookDAOInterface{
	private FacebookDAO(){}

	public static FacebookDAOInterface createDaoObject(String string) {
		// TODO Auto-generated method stub
		return new FacebookDAO();
	}

	@Override
	public int createProfileDao(FacebookEmployee fe) {
		int i=0;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Zensar123");
			PreparedStatement ps=con.prepareStatement("insert into tt values(?,?,?,?)");
			ps.setString(1, fe.getName());
			ps.setString(2, fe.getPass());
			ps.setString(3, fe.getEmail());
			ps.setString(4, fe.getAddress());
			i=ps.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}

}
