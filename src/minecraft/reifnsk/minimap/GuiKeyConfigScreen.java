package reifnsk.minimap;

import net.minecraft.src.FontRenderer;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;

public class GuiKeyConfigScreen extends GuiScreen implements GuiScreenInterface {
    private int top;
    private int bottom;
    private int left;
    private int right;
    private GuiSimpleButton backButton;
    private GuiSimpleButton saveButton;
    private GuiSimpleButton defaultButton;
    private GuiKeyConfigButton edit;

    public void initGui() {
        int label = this.calcLabelWidth();
        int button = this.calcButtonWidth();
        this.left = (this.width - label - button - 12) / 2;
        this.right = (this.width + label + button + 12) / 2;
        this.top = (this.height - KeyInput.values().length * 10) / 2;
        this.bottom = (this.height + KeyInput.values().length * 10) / 2;
        int y = this.top;
        KeyInput[] arr$ = KeyInput.values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            KeyInput ki = arr$[i$];
            GuiKeyConfigButton gkcb = new GuiKeyConfigButton(this, 0, this.left, y, label, button, ki);
            this.controlList.add(gkcb);
            y += 10;
        }

        int centerX = this.width / 2;
        this.backButton = new GuiSimpleButton(0, centerX - 74, this.bottom + 7, 46, 14, "Back");
        this.controlList.add(this.backButton);
        this.saveButton = new GuiSimpleButton(0, centerX - 23, this.bottom + 7, 46, 14, "Save");
        this.controlList.add(this.saveButton);
        this.defaultButton = new GuiSimpleButton(0, centerX + 28, this.bottom + 7, 46, 14, "Default");
        this.controlList.add(this.defaultButton);
    }

    private int calcLabelWidth() {
        FontRenderer fr = this.mc.fontRenderer;
        int width = -1;
        KeyInput[] arr$ = KeyInput.values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            KeyInput ki = arr$[i$];
            width = Math.max(width, fr.getStringWidth(ki.name()));
        }

        return width;
    }

    private int calcButtonWidth() {
        FontRenderer fr = this.mc.fontRenderer;
        int width = 30;
        KeyInput[] arr$ = KeyInput.values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            KeyInput ki = arr$[i$];
            width = Math.max(width, fr.getStringWidth(">" + ki.getKeyName() + "<"));
        }

        return width + 2;
    }

    public void drawScreen(int i, int j, float f) {
        String title = "Key Config";
        int titleWidth = this.fontRenderer.getStringWidth(title);
        int titleLeft = this.width - titleWidth >> 1;
        int titleRight = this.width + titleWidth >> 1;
        this.drawRect(titleLeft - 2, this.top - 22, titleRight + 2, this.top - 8, -1610612736);
        this.drawCenteredString(this.fontRenderer, title, this.width / 2, this.top - 19, -1);
        this.drawRect(this.left - 2, this.top - 2, this.right + 2, this.bottom + 1, -1610612736);
        super.drawScreen(i, j, f);
    }

    GuiKeyConfigButton getEditKeyConfig() {
        return this.edit;
    }

    protected void actionPerformed(GuiButton guibutton) {
        if (guibutton instanceof GuiKeyConfigButton) {
            this.edit = (GuiKeyConfigButton)guibutton;
        }

        if (guibutton == this.saveButton) {
            if (KeyInput.saveKeyConfig()) {
                this.mc.ingameGUI.addChatMessage("\u00a7E[Rei's Minimap] Keyconfig Saved.");
            } else {
                this.mc.ingameGUI.addChatMessage("\u00a7E[Rei's Minimap] Error Keyconfig Saving.");
            }
        }

        if (guibutton == this.defaultButton) {
            KeyInput[] arr$ = KeyInput.values();
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                KeyInput ki = arr$[i$];
                ki.setDefault();
            }

            this.controlList.clear();
            this.initGui();
        }

        if (guibutton == this.backButton) {
            this.mc.displayGuiScreen(new GuiOptionScreen());
        }

    }

    protected void keyTyped(char c, int i) {
        if (this.edit != null) {
            this.edit.getKeyInput().setKey(i);
            this.edit = null;
            this.controlList.clear();
            this.initGui();
        } else if (i == 1) {
            this.mc.displayGuiScreen((GuiScreen)null);
        }

    }
}
