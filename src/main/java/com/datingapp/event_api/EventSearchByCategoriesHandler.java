package com.datingapp.event_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

//import org.json.JsonObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.datingapp.db.EventDAOImpl;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class EventSearchByCategoriesHandler {

	static EventDAOImpl eventdao = new EventDAOImpl();
	static JsonParser parser = new JsonParser();

	public static String handleRequest(Object input, Context context) {
		context.getLogger().log("Input: " + input);
		JsonObject contentObj = new JsonParser().parse(input.toString()).getAsJsonObject();
		eventdao.getConnection();
		Map<String, Object> map = new HashMap<String, Object>();
		String categories = contentObj.get("categories").getAsJsonObject().get("categories").getAsString();
		String zipcode = contentObj.get("zipcode").getAsJsonObject().get("zipcode").getAsString();
		String result = eventdao.getEventCategories(categories,zipcode);
		context.getLogger().log("result: " + result);
		eventdao.disconnected();
		return result;
	}
	

	// @Override
	// public String handleRequest(Object input, Context context) {
	// context.getLogger().log("Input: " + input);
	// JsonObject contentObj = new
	// JsonParser().parse(input.toString()).getAsJsonObject();
	// eventdao.getConnection();
	// Map<String,Object> map = new HashMap<String,Object>();
	// Object id =
	// contentObj.get("queryStringParameters").getAsJsonObject().get("eventId").getAsString();
	// Object source =
	// contentObj.get("queryStringParameters").getAsJsonObject().get("eventSource").getAsString();
	//
	// map.put("id", id);
	// map.put("source", source);
	// String result = eventdao.getDataObject(map);
	// context.getLogger().log("result: " + result);
	//
	// eventdao.disconnected();
	// return result;
	// }

	// public void handleRequest(InputStream input, OutputStream output, Context
	// context) throws IOException {
	// // TODO Auto-generated method stub
	// LambdaLogger logger = context.getLogger();
	// logger.log("Loading Java Lambda handler of ProxyWithStream");
	//
	// BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	// JsonObject responseJson = new JsonObject();
	// eventdao.getConnection();
	// Map<String, Object> map = new HashMap<String, Object>();
	//
	// try {
	// JsonElement event = parser.parse(reader);
	// JsonObject queryParameter =
	// event.getAsJsonObject().get("queryStringParameters").getAsJsonObject();
	// String id = queryParameter.get("eventId").getAsString();
	// String source = queryParameter.get("eventSource").getAsString();
	// logger.log("id:" + id);
	// logger.log("source:" + source);
	//
	// map.put("id", id);
	// map.put("source", source);
	//
	// JsonObject responseBody = new JsonObject();
	//
	// JsonObject headerJson = new JsonObject();
	// // headerJson.put("x-custom-header", "my custom header value");
	// // responseJson.put("isBase64Encoded", false);
	// // responseJson.put("statusCode", 200);
	// // responseJson.put("headers", headerJson)
	// //
	// // String result = eventdao.getDataObject(map);
	// // responseBody.put("result", result);
	// // responseJson.put("body", responseBody.toString());
	//
	// OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
	// logger.log(responseJson.toString());
	// writer.write(responseJson.toString());
	// writer.close();
	// } catch (Exception e) {
	//
	// }
	// eventdao.disconnected();
	// }

}
