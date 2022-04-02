package com.oldschoolminecraft.client.gui;

import com.oldschoolminecraft.client.Client;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;

public class GuiSupporterOptions extends GuiScreen {
    private GuiScreen parentScreen;
    public GuiSupporterOptions(GuiScreen ps) {
    	this.parentScreen = ps;
    }

    GuiButton alwaysDay;
    
    @SuppressWarnings("unchecked")
	public void initGui() {
    	GuiButton alwaysDay = new GuiButton(2, this.width / 2 - 100, this.height / 6 + 34 + 12, "Always day: " + Client.getInstance().settingsManager.getBool("always_day", false), true);
        this.alwaysDay = alwaysDay;
    	
        this.controlList.add(alwaysDay);
        this.controlList.add(new GuiButton(3, this.width / 2 - 100, this.height / 6 + 168, "Done", true));
    }
    
    protected void actionPerformed(GuiButton btn) {
        switch (btn.id) {
            case 3:
                this.mc.currentScreen = parentScreen;
                this.mc.setIngameFocus();
                break;

            case 2:
                Client.getInstance().settingsManager.toggleBool("always_day", false);
                alwaysDay.displayString = "Always Day: " + String.valueOf(Client.getInstance().settingsManager.getBool("always_day", false));
                System.out.println("ok, " + Client.getInstance().settingsManager.getBool("always_day", false));
                break;
        }
    }
    
    public void drawScreen(int var1, int var2, float var3) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, "Supporter Options", this.width / 2, 20, 16777215);
        super.drawScreen(var1, var2, var3);
    }
}
