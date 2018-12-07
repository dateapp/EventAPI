package com.datingapp.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.datingapp.util.ResultSetAdapter;
import com.datingapp.yelp.model.YelpEvent;

public class YelpEventDAOImpl extends RdsJdbcHandler {

	public YelpEventDAOImpl() {
		super();
	}

	@Override
	public boolean save(Object e) {
		// TODO Auto-generated method stub
		return true;
	}

	public String getYelpEventByCategory(String category) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("c.category_name", category);
		return getDataObject(queryMap);
	}

	public String getYelpEventById(String id) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("ye.yelp_id", id);
		return getDataObject(queryMap);
	}

	@Override
	public String getDataObject(Map<String, Object> map) {
		// TODO Auto-generated method stub
		if (map.isEmpty())
			return null;
		StringBuilder sb = new StringBuilder();
		String listOfColumns = "ye.yelp_id,ye.name,ye.image_url,ye.yelp_url,ye.rating,ye.latitude,ye.longitude,ye.price,ye.address1,ye.address2,ye.address3,ye.city,ye.zip_code,ye.country,ye.state,ye.display_address,ye.phone,ye.display_phone,ye.review_count,c.category_name";
		sb.append("Select " + listOfColumns
				+ " from yelp_event ye left join yelp_category c on ye.yelp_id = c.yelp_id where ");
		for (String column : map.keySet()) {
			sb.append(column + " = ");
			sb.append("'" + map.get(column) + "'");
			sb.append(" and ");
		}
		sb.delete(sb.length() - 5, sb.length());
		sb.append(";");
		Statement statement;

		try {
			statement = this.dbconnection.createStatement();
			ResultSet results = statement.executeQuery(sb.toString());
			if(results!=null) {
				JSONArray array = ResultSetAdapter.convertToJSON(results);
				return array.toString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// results.get
			
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
