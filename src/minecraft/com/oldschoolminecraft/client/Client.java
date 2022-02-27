package com.oldschoolminecraft.client;

import com.oldschoolminecraft.client.event.EventManager;
import com.oldschoolminecraft.client.perks.PerkManager;

import com.oldschoolminecraft.client.updater.Updater;
import net.minecraft.client.Minecraft;

public class Client {
	public static Minecraft mc = Minecraft.getMinecraft();
	private static Client instance;

	public static Client getInstance() { return instance; }

	//API
	public static String apiURL = "https://os-mc.net/api/";

	//Client
	public String version = "v0.1";
	public String name = "OSM Client";
	public PerkManager perkManager;
	public EventManager eventManager;
	public Updater updater;

	public void onEnable()
	{
		eventManager = new EventManager();
		perkManager = new PerkManager();
		perkManager.fetchPerks(mc.session.username);

		if (perkManager.hasPerk("supporter_menu")) System.out.println("Found supporter perk");
		updater.checkUpdates(version, (isUpdateAvailable) -> updater.update());
	}
}
