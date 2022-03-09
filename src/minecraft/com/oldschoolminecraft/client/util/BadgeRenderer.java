package com.oldschoolminecraft.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.src.RenderEngine;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;

public class BadgeRenderer
{
    public static void renderBadge(int textureID)
    {
        RenderEngine renderEngine = Minecraft.getMinecraft().renderEngine;
        Tessellator tessellator = Tessellator.instance;

        float badgeScale = 5f;
        int offsetX = 0; // -(strWidth + 6);
        float offsetY = -8f;
        renderEngine.bindTexture(textureID);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(1f, 1f, 1f, 1.0F);
        tessellator.addVertexWithUV(-badgeScale + offsetX, badgeScale + offsetY, 0.0D, -0f, 1f);
        tessellator.addVertexWithUV(badgeScale + offsetX, badgeScale + offsetY, 0.0D, 1f, 1f);
        tessellator.addVertexWithUV(badgeScale + offsetX, -badgeScale + offsetY, 0.0D, 1f, -0f);
        tessellator.addVertexWithUV(-badgeScale + offsetX, -badgeScale + offsetY, 0.0D, -0f, -0f);
        tessellator.draw();
    }
}
