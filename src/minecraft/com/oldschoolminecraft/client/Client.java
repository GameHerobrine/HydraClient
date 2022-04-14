package com.oldschoolminecraft.client;

import com.oldschoolminecraft.client.event.events.EventUpdate;
import com.oldschoolminecraft.client.packets.OnlinePlayersPacket;
import com.oldschoolminecraft.client.perks.PerkManager;

import com.oldschoolminecraft.client.settings.SettingsManager;
import org.lwjgl.input.Keyboard;

import com.oldschoolminecraft.client.event.EventManager;
import com.oldschoolminecraft.client.event.EventTarget;
import com.oldschoolminecraft.client.event.events.EventKeyboard;
import com.oldschoolminecraft.client.gui.GuiClientOptions;
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
	public boolean isUpdateAvailable = false;
	public PerkManager perkManager;
	public Updater updater;
	public EventManager eventManager;
	public SettingsManager settingsManager;
	public boolean customStyle = !System.getenv().containsKey("DEFAULT_STYLE");
	public boolean slowFly;
	public boolean Fly;

	public void onEnable() {
		instance = this;
		mc = Minecraft.getMinecraft();
		eventManager = new EventManager();
		perkManager = new PerkManager();
		settingsManager = new SettingsManager();
		updater = new Updater();
		perkManager.fetchPerks(mc.session.username);
		updater.checkUpdates(version, (flag) -> isUpdateAvailable = flag);
		eventManager.register(this);
	}

	@EventTarget
	public void onKeyboard(EventKeyboard event) {
		if(event.getKeyCode() == Keyboard.KEY_RSHIFT) {
			mc.displayGuiScreen(new GuiClientOptions(mc.currentScreen));
		}

		if (event.getKeyCode() == Keyboard.KEY_RBRACKET) settingsManager.toggleBool("hud_debug", true, "debug");

		if(event.getKeyCode() == Keyboard.KEY_LBRACKET && this.perkManager.hasPerk("staff")) this.slowFly = !this.slowFly;

		if(event.getKeyCode() == Keyboard.KEY_NUMPAD5 && this.perkManager.hasPerk("staff")) this.Fly = !this.Fly;
	}

	@EventTarget
	public void onUpdate(EventUpdate event) {
		if (this.settingsManager.getBool("always_day", false)) {
			this.mc.theWorld.setWorldTime(1000);
		}
	}
}
