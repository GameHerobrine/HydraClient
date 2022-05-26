package net.minecraft.src;

import com.oldschoolminecraft.client.Client;
import com.oldschoolminecraft.client.util.RenderUtils;
import net.minecraft.client.Minecraft;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

public class GuiButton extends Gui {
    protected int width;
    protected int height;
    public int xPosition;
    public int yPosition;
    public String displayString;
    public int id;
    public boolean enabled;
    public boolean enabled2;

    public GuiButton(int id, int xPosition, int yPosition, String displayString) {
        this(id, xPosition, yPosition, 200, 20, displayString);
    }
    
    public GuiButton(int id, int xPosition, int yPosition, int width, int height, String displayString) {
        this.enabled = true;
        this.enabled2 = true;
        this.id = id;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.displayString = displayString;
    }

    protected int getHoverState(boolean var1) {
        byte var2 = 1;
        if (!this.enabled) {
            var2 = 0;
        } else if (var1) {
            var2 = 2;
        }

        return var2;
    }

    public void drawButton(Minecraft var1, int var2, int var3) {
        if (this.enabled2) {
        	if(Client.getInstance().customStyle) {
        		FontRenderer var4 = var1.fontRenderer;
                GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, var1.renderEngine.getTexture("/gui/gui.png"));
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                boolean var5 = var2 >= this.xPosition && var3 >= this.yPosition && var2 < this.xPosition + this.width && var3 < this.yPosition + this.height;
                int var6 = this.getHoverState(var5);
                this.mouseDragged(var1, var2, var3);
                RenderUtils.drawRoundedRect(this.xPosition, this.yPosition, width, height, 3.0f, this.enabled ? (var5 ? new Color(0, 0, 0, 100).getRGB() : new Color(30, 30, 30, 100).getRGB()) : new Color(70, 70, 70, 50).getRGB());
                RenderUtils.drawRoundedOutline(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 3.0f, 2.0f, this.enabled ? (var5 ? Client.getMainColor(255) : Client.getMainColor(150)) : Client.getMainColor(100));
                //this.drawRect(this.xPosition, this.yPosition, this.xPosition + width, this.yPosition + height, 0x60000000);
                if (!this.enabled) {
                    this.drawCenteredString(var4, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, -6250336);
                } else if (var5) {
                    this.drawCenteredString(var4, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, 16777120);
                } else {
                    this.drawCenteredString(var4, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, 14737632);
                }
        	} else {
                FontRenderer var4 = var1.fontRenderer;
                GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, var1.renderEngine.getTexture("/gui/gui.png"));
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                boolean var5 = var2 >= this.xPosition && var3 >= this.yPosition && var2 < this.xPosition + this.width && var3 < this.yPosition + this.height;
                int var6 = this.getHoverState(var5);
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + var6 * 20, this.width / 2, this.height);
                this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + var6 * 20, this.width / 2, this.height);
                this.mouseDragged(var1, var2, var3);
                if (!this.enabled) {
                    this.drawCenteredString(var4, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, -6250336);
                } else if (var5) {
                    this.drawCenteredString(var4, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, 16777120);
                } else {
                    this.drawCenteredString(var4, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, 14737632);
                }
        	}

        }
    }

    protected void mouseDragged(Minecraft var1, int var2, int var3) {
    }

    public void mouseReleased(int var1, int var2) {
    }

    public boolean mousePressed(Minecraft var1, int var2, int var3) {
        return this.enabled && var2 >= this.xPosition && var3 >= this.yPosition && var2 < this.xPosition + this.width && var3 < this.yPosition + this.height;
    }
}
