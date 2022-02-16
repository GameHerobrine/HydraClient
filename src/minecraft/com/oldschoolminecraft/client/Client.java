package com.oldschoolminecraft.client;

import com.oldschoolminecraft.client.event.EventManager;
import com.oldschoolminecraft.client.perks.PerkChecker;
import com.oldschoolminecraft.client.updater.Updater;

import net.minecraft.client.Minecraft;

public class Client {
	public Minecraft mc;
	private static Client instance;

	public static Client getInstance() { return instance; }

	//API
	public static String apiURL = "https://os-mc.net/api/";

	//Client
	public String version = "b0.1";
	public String name = "OSM Client";
	public boolean isSupporter = false;
	public boolean isDebugMode = false;
	public boolean isStaff = false;
	public boolean isUpdateAvailable = false;
	public PerkChecker perkChecker;
	public Updater updater;
	public EventManager eventManager;

	public void onEnable() {
		instance = this;
		mc = Minecraft.getMinecraft();
		eventManager = new EventManager();
		perkChecker = new PerkChecker();
		updater = new Updater();
		perkChecker.fetchPerks(mc.session.username);
		isSupporter = perkChecker.getPerkList().contains("supporter_menu");
		isStaff = perkChecker.getPerkList().contains("staff_menu");
		isDebugMode = perkChecker.getPerkList().contains("debug");
		
		updater.checkUpdates(version);
		System.out.print(isUpdateAvailable);
	}
}
