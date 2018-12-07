/**
 * 
 */
package com.datingapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author Haiwei
 *
 */
public abstract class RdsJdbcHandler implements JdbcHandler {

	protected Connection dbconnection;
	
	private String rdsHostName;
	
	private String rdsPassword;
	
	private String rdsUserName;
	
	/**
	 * 
	 */
	public RdsJdbcHandler() {
		this.rdsUserName = System.getenv("rdsUserName");
		this.rdsPassword = System.getenv("rdsPassword");
		this.rdsHostName = System.getenv("rdsHostName");
	}
	
	@Override
	public void getConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection(rdsHostName, rdsUserName, rdsPassword);
			this.dbconnection = conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public abstract String getDataObject(Map<String,Object> map); 
	
}
