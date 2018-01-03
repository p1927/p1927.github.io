package com.cg.airspacetelecomm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cg.airspacetelecomm.bean.UserBean;
import com.cg.airspacetelecomm.exception.AirSpaceException;
import com.cg.airspacetelecomm.service.CSI;
import com.cg.airspacetelecomm.util.DBConnection;

public class CustomerDAOImpl implements CustomerDAO {
	
	
	
	
	
	public void addUser(UserBean user) throws AirSpaceException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement statement = null;
		con = DBConnection.getConnection();
		String query = "insert into UserDB VALUES(?,?,?,?,?,'750')";
		try {
			statement = con.prepareStatement(query);
			statement.setString(1, Integer.toString(user.getSNo()));
			statement.setString(2, user.getName());
			statement.setString(3, user.getUserName());
			statement.setString(4, user.getPwd());
			statement.setString(5, user.getMobileNo());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AirSpaceException("Details Couldn't be added due to SQL Error : " + e.getMessage());
		}
		finally
		{
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new AirSpaceException("Connection could not be closed" + e.getMessage());
			}
			
		}

	}
	
	

	public void Adjustbill(UserBean user,int Balance) throws AirSpaceException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement statement = null;
		con = DBConnection.getConnection();
		String query = "update UserDB set BILL=? WHERE USERNAME=? AND PASS=?";
		try {
			statement = con.prepareStatement(query);
			statement.setString(1, String.valueOf(Balance));
			statement.setString(2, user.getUserName());
			statement.setString(3, user.getPwd());
			
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AirSpaceException("Details Couldn't be added due to SQL Error : " + e.getMessage());
		}
		finally
		{
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new AirSpaceException("Connection could not be closed" + e.getMessage());
			}
			
		}

	}
	////////////////////////////////////////////////////////////////////
	

	//////////////////////////////
	public int[] check(String un) throws AirSpaceException {
		// TODO Auto-generated method stub
		System.out.println(un);
		CSI dataget = new CSI();
		//////////////////////////////////////////////////////check with fakedata	
		/*ArrayList<UserBean> udata=dataget.fakeget();
		 int []code={0,0};
		 
		for(UserBean currbean : udata)
		{if(currbean.getSNo()>=code[1]){code[1]=currbean.getSNo()+1;};
		if (currbean.getUserName().equals(un))
			code[0]= 1;
		}
		
		
		return code;}*/
	////////////////////////////////////////
		Connection con = null;
		PreparedStatement statement = null;
		
		  con = DBConnection.getConnection();
		  int []code={0,0};
		  String query = "SELECT USERNAME FROM UserDB WHERE USERNAME='"+un+"'";
	try {
		
		statement = con.prepareStatement(query);
		ResultSet rs = statement.executeQuery(query);
		if (!rs.next())
		{code[0]=0;}
		else {code[0]=1;};
			
		query = "SELECT MAX(SNo) FROM UserDB";
		statement = con.prepareStatement(query);
		rs = statement.executeQuery(query);
		rs.next();
		code[1]=rs.getInt("MAX(SNo)")+1;
		
		return code;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		throw new AirSpaceException("Details Couldn't be added due to SQL Error : " + e.getMessage());
	}
	finally
	{
		try {
			statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AirSpaceException("Connection could not be closed" + e.getMessage());
		}
		
	}
	
	
}
	
	



	
	public void editUser(UserBean user) throws AirSpaceException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement statement = null;
		con = DBConnection.getConnection();
		
		try {
			String query = "delete from UserDB where SNo='"+Integer.toString((user.getSNo()))+"'";
			statement = con.prepareStatement(query);
			statement.executeUpdate();
			query = "insert into UserDB VALUES(?,?,?,?,?,?)";
			statement = con.prepareStatement(query);
			statement.setString(1, Integer.toString((user.getSNo())));
			statement.setString(2, user.getName());
			statement.setString(3, user.getUserName());
			statement.setString(4, user.getPwd());
			statement.setString(5, user.getMobileNo());
			statement.setString(6, user.getBill());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AirSpaceException("Details Couldn't be added due to SQL Error : " + e.getMessage());
		}
		finally
		{
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new AirSpaceException("Connection could not be closed" + e.getMessage());
			}
			
		}

	}
	

}
