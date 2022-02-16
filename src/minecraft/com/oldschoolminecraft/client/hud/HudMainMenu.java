package com.oldschoolminecraft.client.hud;

import com.oldschoolminecraft.client.Client;
import com.oldschoolminecraft.client.util.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.src.ScaledResolution;

public class HudMainMenu {
	public static void draw() {
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		
		if(Client.getInstance().isSupporter)
			mc.fontRenderer.drawStringWithShadow("Thanks for supporting us, " + mc.session.username + "!", 2, res.getScaledHeight() - 9, Utils.rainbow());
	}
}
