package com.datingapp.db;

import java.util.List;
import java.util.Map;

public interface JdbcHandler {
	
	public boolean save(Object e);
	
    void getConnection();
	
    void disconnected();
	
}
