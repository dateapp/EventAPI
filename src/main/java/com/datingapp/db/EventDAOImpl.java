package com.datingapp.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.datingapp.services.LocationService;
import com.google.gson.Gson;

public class EventDAOImpl extends RdsJdbcHandler {

	private LocationService locationService = new LocationService();

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
		addListOfColumns(sb);
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
			if (listOfMaps != null) {
				return new Gson().toJson(listOfMaps);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Function to retreive categories.
	 * 
	 * @return
	 */
	public String getEventCategories(String categoryName, String zipcode) {
		List<String> nearByZipcodes = new ArrayList<String>();
		StringBuilder categorySearchBuilder = new StringBuilder();
		addListOfColumns(categorySearchBuilder);
		categorySearchBuilder.append(" upper(trim(category_name)) like");
		categorySearchBuilder.append(" '%");
		categorySearchBuilder.append(categoryName != null ? categoryName.trim().toUpperCase() : " ");
		categorySearchBuilder.append(" %' ");
		if (zipcode != null) {
			nearByZipcodes = locationService.nearbyZipcodesTo(zipcode);
			categorySearchBuilder.append("   AND zip_code in (  ");
			for (int i = 0; i < nearByZipcodes.size(); i++) {
				if (i == nearByZipcodes.size() - 1) {
					categorySearchBuilder.append("'" + nearByZipcodes.get(i) + "'" + "  ");
				} else {
					categorySearchBuilder.append("'" + nearByZipcodes.get(i) + "'" + ",");
				}

			}
			categorySearchBuilder.append("  ) ");

		}
		categorySearchBuilder.append(";");
		List<Map<String, Object>> listOfMaps = null;
		try {
			QueryRunner queryRunner = new QueryRunner();
			listOfMaps = queryRunner.query(this.dbconnection, categorySearchBuilder.toString(), new MapListHandler());
			if (listOfMaps != null) {
				return new Gson().toJson(listOfMaps);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param sb
	 */
	private void addListOfColumns(StringBuilder sb) {
		String listOfColumns = "id, source, display_name, url, address1, address2, city, state, zip_code, country, latitude, longitude, start_time, end_time, price, deal_id, image_url, category_name";
		sb.append("Select " + listOfColumns + " from event where ");
	}

}
