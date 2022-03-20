package reifnsk.minimap;

import net.minecraft.client.Minecraft;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.GuiButton;

public class GuiSimpleButton extends GuiButton {
    public GuiSimpleButton(int i, int j, int k, int l, int i1, String s) {
        super(i, j, k, l, i1, s);
    }

    public void drawButton(Minecraft minecraft, int i, int j) {
        if (this.enabled2) {
            FontRenderer fontrenderer = minecraft.fontRenderer;
            boolean flag = i >= this.xPosition && j >= this.yPosition && i < this.xPosition + this.width && j < this.yPosition + this.height;
            int color = flag ? -932813210 : -1610612736;
            this.drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, color);
            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, -1);
        }
    }
}
