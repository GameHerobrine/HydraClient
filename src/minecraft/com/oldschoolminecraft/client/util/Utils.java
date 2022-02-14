package com.oldschoolminecraft.client.util;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {
	public static int rainbow() {
		return Color.HSBtoRGB((float)(System.currentTimeMillis() % 1000L) / 1000.0f, 0.8f, 0.8f);
	}

	public static void get(String url, HttpDataPipe dataPipe)
	{
		try
		{
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:78.0) Gecko/20100101 Firefox/78.0 Waterfox/78.12.0");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				response.append(inputLine);
			in.close();
			dataPipe.pipe(response.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public interface HttpDataPipe
	{
		void pipe(String rawResponse);
	}
}
