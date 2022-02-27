package com.oldschoolminecraft.client.gui;

import com.oldschoolminecraft.client.Client;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;

public class GuiClientOptions extends GuiScreen {
    private GuiScreen parentScreen;
    public GuiClientOptions(GuiScreen ps) {
    	this.parentScreen = ps;
    }
    
    @SuppressWarnings("unchecked")
	public void initGui() {
    	GuiButton supporterOptionsBtn = new GuiButton(1, this.width / 2 - 100, this.height / 6 + 34 + 12, "Supporter Options");
    	supporterOptionsBtn.enabled = Client.getInstance().perkManager.hasPerk("supporter_menu");
    	
    	GuiButton staffOptionsBtn = new GuiButton(2, this.width / 2 - 100, this.height / 6 + 58 + 12, "Staff Options");
    	staffOptionsBtn.enabled = Client.getInstance().perkManager.hasPerk("staff_menu");
    	
        this.controlList.add(supporterOptionsBtn);
        this.controlList.add(staffOptionsBtn);
        this.controlList.add(new GuiButton(3, this.width / 2 - 100, this.height / 6 + 168, "Done"));
    }
    
    protected void actionPerformed(GuiButton btn) {
    	if(btn.id == 3) {
    		this.mc.currentScreen = parentScreen;
    	}
    }
    
    public void drawScreen(int var1, int var2, float var3) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, "OSM Client Settings", this.width / 2, 20, 16777215);
        super.drawScreen(var1, var2, var3);
    }
}
