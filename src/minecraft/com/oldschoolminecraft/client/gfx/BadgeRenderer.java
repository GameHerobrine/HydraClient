package com.oldschoolminecraft.client.gfx;

import com.oldschoolminecraft.client.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.src.RenderEngine;

public class BadgeRenderer
{
    public final String badgeURL;

    public BadgeRenderer(String badgeURL)
    {
        this.badgeURL = badgeURL;
    }

    public final void render()
    {
        if (!Client.getInstance().perkManager.hasPerk("supporter_menu")) return;
        if (badgeURL == null || !badgeURL.startsWith("http")) return;

        RenderEngine renderEngine = Minecraft.getMinecraft().renderEngine;
    }
}
