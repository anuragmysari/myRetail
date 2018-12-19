package com.myRetail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

	public static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static String readNameFromExternalAPI(String url) throws IOException, JSONException {
		URL api = new URL(url);
		String name = "";
		HttpURLConnection con = (HttpURLConnection) api.openConnection();
		con.setRequestMethod("GET");
		if (con.getResponseCode() == 200) {
			InputStream is = con.getInputStream();
			if (is != null) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
				String jsonText = readAll(rd);
				JSONObject json = new JSONObject(jsonText);
				name = json.getJSONObject("product").getJSONObject("item").getJSONObject("product_description")
						.get("title").toString();
			}
		}

		return name;

	}
}