package com.oldschoolminecraft.client;

import com.oldschoolminecraft.client.perks.PerkManager;
import com.oldschoolminecraft.client.event.EventManager;
import com.oldschoolminecraft.client.updater.Updater;

import net.minecraft.client.Minecraft;

public class Client {
	public Minecraft mc;
	private static Client instance;

	public static Client getInstance() { return instance; }

	//API
	public static String apiURL = "https://os-mc.net/api/";

	//Client
	public String version = "b0.01";
	public String name = "OSM Client";
	public boolean isUpdateAvailable = false;
	public PerkManager perkManager;
	public Updater updater;
	public EventManager eventManager;

	public void onEnable() {
		instance = this;
		mc = Minecraft.getMinecraft();
		eventManager = new EventManager();
		perkManager = new PerkManager();
		updater = new Updater();
		perkManager.fetchPerks(mc.session.username);
		updater.checkUpdates(version, (flag) -> isUpdateAvailable = flag);
	}
}
