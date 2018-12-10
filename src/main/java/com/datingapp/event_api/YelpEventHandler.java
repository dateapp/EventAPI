///**
// * 
// */
//package com.datingapp.event_api;
//
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestHandler;
//import com.datingapp.db.YelpEventDAOImpl;
//
///**
// * @author Haiwei
// *
// */
//public class YelpEventHandler  implements RequestHandler<Object, String>{
//
//	
//	public YelpEventDAOImpl yelpDao = new YelpEventDAOImpl();
//	@Override
//	public String handleRequest(Object input, Context context) {
//        context.getLogger().log("Input: " + input);
//        yelpDao.getConnection();
//        String json = yelpDao.getYelpEventById((String)input);
//		return json;
//	}
//
//}
