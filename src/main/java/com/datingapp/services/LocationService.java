package com.datingapp.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocationService {

	private final Logger logger = LoggerFactory.getLogger(LocationService.class);
	
	private static String URL = "https://www.zipcodeapi.com/rest/wwErz0Xg5kq7q29kQMkZ64pU0sJnX5lAs4jQGJOE5e0UpFTOv3azqhsfW1ZOJS43/radius.json/%s/20/mile?minimal";

	/**
	 * Function to get list of nearby zip codes.
	 * https://www.zipcodeapi.com/API#radius if the API key expires, need to
	 * register for new one. Add Minimal for just Zip codes.
	 * https://www.zipcodeapi.com/rest/pJq9ptc9a6lH5ROncJn46bpvPpKu7SlWoPO5fcpvgp5ByxVkA1sIzF65nlFTDik7/radius.json/60606/10/mile?minimal
	 * 
	 * @param args
	 * @throws IOException
	 */

	public  List<String> nearbyZipcodesTo(String zipcode) {
		List<String> zipcodesList = new ArrayList<>();
		JSONObject json = null;
		InputStream is = null;
		try {
			is = new URL(String.format(URL, zipcode)).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			json = new JSONObject(jsonText);
			JSONArray zipcodeArr = json.getJSONArray("zip_codes");
			if (zipcodeArr != null) {
				for (int i = 0; i < zipcodeArr.length(); i++) {
					zipcodesList.add(zipcodeArr.getString(i));
				}
			}
			logger.info("Total nearby zipcodes list" + zipcodesList);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return zipcodesList;
	}

	/**
	 * Function to read the data over the stream.
	 * @param rd
	 * @return
	 * @throws IOException
	 */
	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

}
