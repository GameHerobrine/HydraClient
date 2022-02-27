package com.oldschoolminecraft.client.updater;

import com.oldschoolminecraft.client.util.Utils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Updater {
	public void checkUpdates(String version, Utils.FlagDataPipe pipe)
	{
		Utils.get("https://raw.githubusercontent.com/CodeKid0/OSMClientRewrite/main/version.txt", (response) ->
		{
			pipe.pipe(!response.equals(version)); // true for available, false for not available
		});
	}
	
	public void update() throws NotImplementedException
	{
		throw new NotImplementedException();
	}
}
