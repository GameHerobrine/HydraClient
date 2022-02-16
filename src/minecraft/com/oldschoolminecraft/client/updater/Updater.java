package com.oldschoolminecraft.client.updater;

import com.oldschoolminecraft.client.Client;
import com.oldschoolminecraft.client.util.Utils;
import com.oldschoolminecraft.client.util.Utils.HttpDataPipe;

public class Updater {
	public void checkUpdates(String version) {
		HttpDataPipe pipe = new Utils.HttpDataPipe() {
			@Override 
			public void pipe(String rawResponse) {
				 if(Client.getInstance().version.equals(rawResponse))
					 Client.getInstance().isUpdateAvailable = false;
				 else
					 Client.getInstance().isUpdateAvailable = true;
			}
		};
		Utils.get("https://raw.githubusercontent.com/CodeKid0/OSMClientRewrite/main/version.txt", pipe);
	}
	
	public void update() {
		
	}
}
