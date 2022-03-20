package reifnsk.minimap;

import net.minecraft.client.Minecraft;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.GuiButton;

public class GuiOptionButton extends GuiButton {
    private static int NAME_WIDTH;
    private static int VALUE_WIDTH;
    private static int WIDTH;
    private EnumOption option;
    private EnumOptionValue value;

    public GuiOptionButton(FontRenderer renderer, EnumOption eo) {
        super(0, 0, 0, 0, 10, "");
        this.option = eo;
        this.value = this.option.getValue(0);

        for(int i = 0; i < eo.getValueNum(); ++i) {
            String valueName = eo.getValue(i).text();
            int stringWidth = renderer.getStringWidth(valueName) + 4;
            VALUE_WIDTH = Math.max(VALUE_WIDTH, stringWidth);
        }

        NAME_WIDTH = Math.max(NAME_WIDTH, renderer.getStringWidth(eo.getText() + ": "));
        WIDTH = VALUE_WIDTH + 8 + NAME_WIDTH;
    }

    public void drawButton(Minecraft minecraft, int i, int j) {
        if (this.enabled2) {
            FontRenderer fontrenderer = minecraft.fontRenderer;
            boolean flag = i >= this.xPosition && j >= this.yPosition && i < this.xPosition + getWidth() && j < this.yPosition + getHeight();
            int textcolor = flag ? -1 : -4144960;
            int bgcolor = flag ? 1728053247 : this.value.color;
            this.drawString(fontrenderer, this.option.getText(), this.xPosition, this.yPosition + 1, textcolor);
            int x1 = this.xPosition + NAME_WIDTH + 8;
            int x2 = x1 + VALUE_WIDTH;
            this.drawRect(x1, this.yPosition, x2, this.yPosition + getHeight() - 1, bgcolor);
            this.drawCenteredString(fontrenderer, this.value.text(), x1 + VALUE_WIDTH / 2, this.yPosition + 1, -1);
        }
    }

    public boolean mousePressed(Minecraft minecraft, int i, int j) {
        if (this.enabled && i >= this.xPosition && j >= this.yPosition && i < this.xPosition + getWidth() && j < this.yPosition + getHeight()) {
            this.nextValue();
            return true;
        } else {
            return false;
        }
    }

    public EnumOption getOption() {
        return this.option;
    }

    public EnumOptionValue getValue() {
        return this.value;
    }

    public void setValue(EnumOptionValue value) {
        if (this.option.getValue(value) != -1) {
            this.value = value;
        }

    }

    public void nextValue() {
        this.value = this.option.getValue((this.option.getValue(this.value) + 1) % this.option.getValueNum());
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return 10;
    }
}
