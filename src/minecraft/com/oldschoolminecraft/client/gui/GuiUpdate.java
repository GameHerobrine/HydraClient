package com.oldschoolminecraft.client.gui;

import com.oldschoolminecraft.client.Client;
import com.oldschoolminecraft.client.util.Utils;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiMainMenu;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.ScaledResolution;

public class GuiUpdate extends GuiScreen
{
    public void initGui()
    {
        int var4 = this.height / 4 + this.height / 10;
        this.controlList.add(new GuiButton(1, this.width / 2 - 100, var4 + 64, 98, 20, "Yes"));
        this.controlList.add(new GuiButton(2, this.width / 2 + 2, var4 + 64, 98, 20, "No"));
    }

    protected void actionPerformed(GuiButton btn)
    {
        if (btn.id == 1) Client.getInstance().updater.update();

        if (btn.id == 2)
        {
            //TODO: trigger update procedure
            Client.getInstance().isUpdateAvailable = false;
            this.mc.displayGuiScreen(new GuiMainMenu());
        }
    }

    public void drawScreen(int var1, int var2, float var3)
    {
        this.drawDefaultBackground();
        ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);

        String s1 = "There is a new OSM client update available.";
        String s2 = "Would you like to update?";
        int y = this.height / 4 + this.height / 10;
        this.drawString(this.fontRenderer, s1, res.getScaledWidth() / 2 - this.fontRenderer.getStringWidth(s1) / 2, y, -1);
        this.drawString(this.fontRenderer, s2, res.getScaledWidth() / 2 - this.fontRenderer.getStringWidth(s2) / 2, y + 14, Utils.rainbowSlow());
        super.drawScreen(var1, var2, var3);
    }
}
