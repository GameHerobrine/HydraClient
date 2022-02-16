package com.oldschoolminecraft.client;

import com.oldschoolminecraft.client.event.EventManager;
import com.oldschoolminecraft.client.perks.PerkManager;

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
	public boolean isSupporter = false;
	public boolean isDebugMode = false;
	public boolean isStaff = false;
	public PerkManager perkManager;
	public EventManager eventManager;

	public void onEnable() {
		eventManager = new EventManager();
		perkManager = new PerkManager();
		perkManager.fetchPerks(mc.session.username);
		isSupporter = perkManager.getPerkList().contains("supporter_menu");
		isStaff = perkManager.getPerkList().contains("staff_menu");
		System.out.print(isSupporter);

		if(mc.session.username.contains("Player"))
			isDebugMode = true;
	}
}
