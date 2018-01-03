package com.cg.airspacetelecomm.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.cg.airspacetelecomm.bean.UserBean;
import com.cg.airspacetelecomm.exception.AirSpaceException;
import com.cg.airspacetelecomm.util.DBConnection;



public class CSI {
	

	
	Connection con= null;
			
	
	public ArrayList<UserBean> get() throws AirSpaceException {
		// TODO Auto-generated method stub
	con=DBConnection.getConnection();
	String query = "SELECT * FROM UserDB";
	
	ArrayList <UserBean> uselist=new ArrayList<UserBean>();
      // create the java statement
      Statement st;
	try {
		st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
     		 while (rs.next())
     	      {
     	         UserBean user=new UserBean();
     	        user.setSNo(rs.getInt("SNo"));
     	      	 user.setName(rs.getString("FirstName"));
     	         user.setUserName(rs.getString("Username"));
     	         user.setPwd(rs.getString("Pass"));
     	         user.setMobileNo(rs.getString("Mbno"));
     	        user.setBill(rs.getString("Bill"));
     	         uselist.add(user);
     	         /////////////////
     	         
     	    };
				
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return uselist;
	
}
/////////////////////////////////////////fake get
	public ArrayList<UserBean> fakeget() throws AirSpaceException {
		// TODO Auto-generated method stub
	
	
	ArrayList <UserBean> uselist=new ArrayList<UserBean>();
      // create the java statement
    
	UserBean user=new UserBean();
	user.setSNo(1);
	 user.setName("admin");
	 user.setUserName("admin");
	 user.setPwd("admin");
	 user.setMobileNo("Mbno");
	 UserBean user1=new UserBean();
	 uselist.add(user);
	 user1.setSNo(2);
	 user1.setName("Hola");
	 user1.setUserName("Amigo");
	 user1.setPwd("zapatos");
	 user1.setMobileNo("amor");
	 user1.setBill("456");
	 uselist.add(user1);
	 /////////////////
	return uselist;
	
}
/////////////////////////////////////////////////////////////	
	


}
	
	