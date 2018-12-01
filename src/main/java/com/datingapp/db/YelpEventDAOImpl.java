package com.datingapp.db;

import java.sql.SQLException;

public class YelpEventDAOImpl extends RdsJdbcHandler {

	public YelpEventDAOImpl() {
		super();
	}

	@Override
	public boolean save(Object e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getDataObject(Object... objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disconnected() {
		// TODO Auto-generated method stub
		try {
			this.dbconnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
