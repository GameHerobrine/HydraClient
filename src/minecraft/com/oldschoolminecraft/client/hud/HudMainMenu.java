package com.oldschoolminecraft.client.hud;

import java.awt.Color;

import com.oldschoolminecraft.client.util.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.src.ScaledResolution;

public class HudMainMenu {
	public static void draw() {
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		
		mc.fontRenderer.drawStringWithShadow("Hello little one!", 2, res.getScaledHeight() - 9, Utils.rainbow());
	}
}
