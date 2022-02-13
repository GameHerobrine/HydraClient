package com.oldschoolminecraft.client.hud;

import net.minecraft.client.Minecraft;

public class HudInGame {
	public static void draw() {
		Minecraft mc = Minecraft.getMinecraft();
		
		mc.fontRenderer.drawString("well hello", 0, 0, 0);
		
	}
}
