package com.oldschoolminecraft.client.hud;

import net.minecraft.client.Minecraft;

public class HudInGame {
	public static void draw() {
		Minecraft mc = Minecraft.getMinecraft();
		FontRenderer fr = mc.fontRenderer;

		if(Client.isDebugMode && !mc.isDebugInfoEnabled()) {
			int offset = 2;
			fr.drawStringWithShadow("--- DEBUG MODE --- ", 2, offset, Utils.rainbow());
			offset += 12;
			fr.drawStringWithShadow("isSupporter: " + Client.isSupporter, 2, offset, -1);
			offset += 12;
			fr.drawStringWithShadow("isStaff: " + Client.isStaff, 2, offset, -1);
			offset += 12;
			fr.drawStringWithShadow("username: " + Client.username, 2, offset, -1);
		}
	}
}
