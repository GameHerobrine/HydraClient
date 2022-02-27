package com.oldschoolminecraft.client.hud;

import com.oldschoolminecraft.client.Client;
import com.oldschoolminecraft.client.perks.PerkManager;
import com.oldschoolminecraft.client.util.Utils;
import net.minecraft.client.Minecraft;

public class HudInGame {
	public static void draw() {
		Minecraft mc = Minecraft.getMinecraft();
		net.minecraft.src.FontRenderer fr = mc.fontRenderer;
		PerkManager perkChecker = Client.getInstance().perkManager;

		if(Client.getInstance().perkManager.hasPerk("debug") && !Minecraft.isDebugInfoEnabled()) {
			int offset = 2;
			fr.drawStringWithShadow("--- DEBUG MODE --- ", 2, offset, Utils.rainbow());
			offset += 12;
			fr.drawStringWithShadow("--- PERKS: ---", 2, offset, 0xFF14e564);
			offset += 12;
			for(int i = 0; i < perkChecker.getPerkList().size(); i++) {
				fr.drawStringWithShadow(perkChecker.getPerkList().get(i), 2, offset, -1);
				offset += 12;
			}
		}
	}
}
