package reifnsk.minimap;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiButton;

public class GuiKeyConfigButton extends GuiButton {
    private GuiKeyConfigScreen parrent;
    private KeyInput keyInput;
    private String labelText;
    private String buttonText;
    private int labelWidth;
    private int buttonWidth;

    public GuiKeyConfigButton(GuiKeyConfigScreen parrent, int id, int x, int y, int label, int button, KeyInput key) {
        super(id, x, y, label + 12 + button, 9, "");
        this.parrent = parrent;
        this.keyInput = key;
        this.labelWidth = label;
        this.buttonWidth = button;
        this.labelText = this.keyInput.label();
        this.buttonText = this.keyInput.getKeyName();
    }

    public void drawButton(Minecraft minecraft, int i, int j) {
        if (this.keyInput != null) {
            boolean b = i >= this.xPosition && i < this.xPosition + this.width && j >= this.yPosition && j < this.yPosition + this.height;
            this.drawString(minecraft.fontRenderer, this.labelText, this.xPosition, this.yPosition + 1, b ? -1 : -4144960);
            String text = this.buttonText;
            if (this == this.parrent.getEditKeyConfig()) {
                text = ">" + text + "<";
            }

            b = i >= this.xPosition + this.width - this.buttonWidth && i < this.xPosition + this.width && j >= this.yPosition && j < this.yPosition + this.height;
            int color = b ? 1728053247 : (this.keyInput.getKey() == 0 ? (this.keyInput.isDefault() ? -1610612481 : -1593868288) : (this.keyInput.isDefault() ? -1610547456 : -1593901056));
            this.drawRect(this.xPosition + this.width - this.buttonWidth, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, color);
            this.drawCenteredString(minecraft.fontRenderer, text, this.xPosition + this.width - this.buttonWidth / 2, this.yPosition + 1, -1);
        }
    }

    public boolean mousePressed(Minecraft minecraft, int i, int j) {
        return i >= this.xPosition + this.width - this.buttonWidth && i < this.xPosition + this.width && j >= this.yPosition && j < this.yPosition + this.height;
    }

    void setBounds(int x, int y, int label, int button) {
        this.xPosition = x;
        this.yPosition = y;
        this.labelWidth = label;
        this.buttonWidth = button;
        this.width = label + button + 2;
    }

    KeyInput getKeyInput() {
        return this.keyInput;
    }
}
