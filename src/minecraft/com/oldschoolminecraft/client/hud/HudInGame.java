package com.oldschoolminecraft.client.hud;

import com.oldschoolminecraft.client.Client;

import com.oldschoolminecraft.client.util.Utils;
import net.minecraft.client.Minecraft;

public class HudInGame {
	public static void draw() {
		Minecraft mc = Minecraft.getMinecraft();
		net.minecraft.src.FontRenderer fr = mc.fontRenderer;

		if(Client.getInstance().isDebugMode && !Minecraft.isDebugInfoEnabled()) {
			int offset = 2;
			fr.drawStringWithShadow("--- DEBUG MODE --- ", 2, offset, Utils.rainbow());
			offset += 12;
			fr.drawStringWithShadow("isSupporter: " + Client.getInstance().isSupporter, 2, offset, -1);
			offset += 12;
			fr.drawStringWithShadow("isStaff: " + Client.getInstance().isStaff, 2, offset, -1);
			offset += 12;
			fr.drawStringWithShadow("username: " + Client.getInstance().username, 2, offset, -1);
		}
	}
}
