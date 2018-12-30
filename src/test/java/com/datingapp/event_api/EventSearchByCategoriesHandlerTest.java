package com.datingapp.event_api;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.datingapp.db.EventDAOImpl;

public class EventSearchByCategoriesHandlerTest {
	EventDAOImpl eventDao = new EventDAOImpl();
	
	
	// Tests ignored as database credentials are passed at the API gateway.
	@Ignore
	@Test
	public void testCategories() {
		String categories = eventDao.getEventCategories("BARS","60606");
		Assert.assertNotNull(categories);
		System.out.println("Categories" +categories);
	}

}
