package com.datingapp.db;

public interface JdbcHandler {
	
	public boolean save(Object e);
	
	Object getDataObject(Object... objects); 
	
    void getConnection();
	
    void disconnected();
	
}
