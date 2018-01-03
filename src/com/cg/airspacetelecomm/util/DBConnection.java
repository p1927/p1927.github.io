package com.cg.airspacetelecomm.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



import com.cg.airspacetelecomm.exception.AirSpaceException;

public class DBConnection {
	
	public static Connection getConnection() throws AirSpaceException{
		
		Connection con = null;
		
		
		try {
			
			
			InitialContext context = new InitialContext();
			
			DataSource source = (DataSource) context.lookup("java:/jdbc/MyDs");
			con = source.getConnection();
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AirSpaceException("SQL Exception :" + e.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {}
		return con;
	}

}


