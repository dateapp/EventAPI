package com.datingapp.db;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.google.gson.Gson;

public class EventDAOImpl extends RdsJdbcHandler{

	public EventDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean save(Object e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void disconnected() {
		try {
			this.dbconnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public String getDataObject(Map<String, Object> map) {
		if (map.isEmpty())
			return null;
		StringBuilder sb = new StringBuilder();
		String listOfColumns = "id, source, display_name, url, address1, address2, city, state, zip_code, country, latitude, longitude, start_time, end_time, price, deal_id, image_url, category_name";
		sb.append("Select " + listOfColumns
				+ " from event where ");
		for (String column : map.keySet()) {
			sb.append(column + " = ");
			sb.append("'" + map.get(column) + "'");
			sb.append(" and ");
		}
		sb.delete(sb.length() - 5, sb.length());
		sb.append(";");
        List<Map<String, Object>> listOfMaps = null;
		try {
			QueryRunner queryRunner = new QueryRunner();
            listOfMaps = queryRunner.query(this.dbconnection, sb.toString(), new MapListHandler());
			if(listOfMaps!=null) {
				return new Gson().toJson(listOfMaps);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// results.get
		return null;
	}

}
