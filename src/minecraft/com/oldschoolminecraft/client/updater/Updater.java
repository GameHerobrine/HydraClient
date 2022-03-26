package com.oldschoolminecraft.client.updater;

import com.oldschoolminecraft.client.Client;
import com.oldschoolminecraft.client.util.Utils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Updater {
	public void checkUpdates(String version, Utils.FlagDataPipe pipe)
	{
		Utils.get("https://raw.githubusercontent.com/CodeKid0/OSMClientRewrite/main/version.txt", (response) ->
		{
			pipe.pipe(!response.equals(version)); // true for available, false for not available
		});
	}
	
	public void update() {
		BufferedInputStream in = null;
		FileOutputStream out = null;

		try {
			URL url = new URL(""); //get latest jar here
			URLConnection conn = url.openConnection();
			int size = conn.getContentLength();

			if (size < 0) {
				System.out.println("Could not get the file size");
			} else {
				System.out.println("File size: " + size);
			}

			in = new BufferedInputStream(url.openStream());
			out = new FileOutputStream("newest.jar");
			byte data[] = new byte[1024];
			int count;
			double sumCount = 0.0;

			while ((count = in.read(data, 0, 1024)) != -1) {
				out.write(data, 0, count);

				sumCount += count;
				if (size > 0) {
					System.out.println("Percentage: " + (sumCount / size * 100.0) + "%");
				}
			}
			in.close();
			out.close();
		} catch(IOException e) {

		}
	}
}
