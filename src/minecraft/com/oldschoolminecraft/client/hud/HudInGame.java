package com.oldschoolminecraft.client.hud;

import com.oldschoolminecraft.client.Client;
import com.oldschoolminecraft.client.hack.Fly;
import com.oldschoolminecraft.client.perks.PerkManager;
import com.oldschoolminecraft.client.settings.SettingsManager;
import com.oldschoolminecraft.client.util.Utils;
import net.minecraft.client.Minecraft;

public class HudInGame {
	public static void draw() {
		Minecraft mc = Minecraft.getMinecraft();
		net.minecraft.src.FontRenderer fr = mc.fontRenderer;
		PerkManager perkChecker = Client.getInstance().perkManager;
		SettingsManager sm = Client.getInstance().settingsManager;

		if(Client.getInstance().perkManager.hasPerk("debug") && !Minecraft.isDebugInfoEnabled() && sm.getBool("hud_debug", false)) {
			int offset = 2;
			fr.drawStringWithShadow("-- Debug Mode --", 2, offset, Utils.rainbowSlow());
			offset += 12;
			fr.drawStringWithShadow("Perks:", 2, offset, 0xFF14e564);
			offset += 12;
			for(int i = 0; i < perkChecker.getPerkList().size(); i++) {
				fr.drawStringWithShadow("- " + perkChecker.getPerkList().get(i), 2, offset, -1);
				offset += 12;
			}
		}

		if(Client.getInstance().slowFly) {
			fr.drawStringWithShadow("§8[§cSlowFly§8]", 4, 4, -1);
			Fly.runFly(Client.getInstance().mc.thePlayer, Client.getInstance().mc, true);
		}

		if(Client.getInstance().Fly) {
			fr.drawStringWithShadow("§8[§cFly§8]", 4, 4, -1);
			Fly.runFly(Client.getInstance().mc.thePlayer, Client.getInstance().mc, false);
		}
	}
}
