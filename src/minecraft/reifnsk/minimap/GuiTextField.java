package reifnsk.minimap;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiButton;
import org.lwjgl.input.Keyboard;

public class GuiTextField extends GuiButton {
    private static GuiTextField active;
    private int inputType;
    private GuiTextField prev;
    private GuiTextField next;

    public GuiTextField(String s) {
        super(0, 0, 0, 0, 0, s);
    }

    public GuiTextField() {
        super(0, 0, 0, 0, 0, "");
    }

    public void drawButton(Minecraft mc, int mx, int my) {
        int color = active == this ? -2134851392 : -2141167520;
        this.drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, color);
        if (this.inputType == 0) {
            this.drawCenteredString(mc.fontRenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + 1, -1);
        } else {
            int w = mc.fontRenderer.getStringWidth(this.displayString);
            this.drawString(mc.fontRenderer, this.displayString, this.xPosition + this.width - w - 1, this.yPosition + 1, -1);
        }

    }

    public boolean mousePressed(Minecraft mc, int mx, int my) {
        if (mx >= this.xPosition && mx < this.xPosition + this.width && my >= this.yPosition && my < this.yPosition + this.height) {
            this.active();
        }

        return false;
    }

    public void active() {
        if (active != null) {
            active.norm();
        }

        active = this;
    }

    static void keyType(Minecraft mc, char c, int i) {
        if (active != null) {
            active.kt(mc, c, i);
        }

    }

    private void kt(Minecraft mc, char c, int i) {
        if (i != 14 && i != 211) {
            if (i == 15) {
                if (!Keyboard.isKeyDown(42) && !Keyboard.isKeyDown(54)) {
                    next();
                } else {
                    prev();
                }
            }

            if (i == 28) {
                next();
            }

            if (this.checkInput(c)) {
                String newString = this.displayString + c;
                if (mc.fontRenderer.getStringWidth(newString) < this.width - 2) {
                    try {
                        int temp;
                        if (this.inputType == 1) {
                            temp = Integer.parseInt(newString);
                            newString = temp < -32000000 ? "-32000000" : (temp >= 32000000 ? "31999999" : Integer.toString(temp));
                        }

                        if (this.inputType == 2) {
                            temp = Integer.parseInt(newString);
                            newString = temp < 0 ? "0" : (temp > 129 ? "129" : Integer.toString(temp));
                        }
                    } catch (NumberFormatException var6) {
                    }

                    this.displayString = newString;
                }
            }

        } else {
            if (!this.displayString.isEmpty()) {
                this.displayString = this.displayString.substring(0, this.displayString.length() - 1);
            }

        }
    }

    boolean checkInput(char c) {
        switch(this.inputType) {
        case 0:
            return " !\"#$%&'()*+,-./0123456789;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_'abcdefghijklmnopqrstuvwxyz{|}~\u2302\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb".indexOf(c) != -1;
        case 1:
            return (this.displayString.isEmpty() ? "-0123456789" : "0123456789").indexOf(c) != -1;
        case 2:
            return "0123456789".indexOf(c) != -1;
        default:
            return false;
        }
    }

    void norm() {
        String newString = this.displayString;

        try {
            int temp;
            if (this.inputType == 1) {
                temp = Integer.parseInt(newString);
                newString = temp < -32000000 ? "-32000000" : (temp >= 32000000 ? "31999999" : Integer.toString(temp));
            }

            if (this.inputType == 2) {
                temp = Integer.parseInt(newString);
                newString = temp < 0 ? "0" : (temp > 129 ? "129" : Integer.toString(temp));
            }
        } catch (NumberFormatException var3) {
            newString = "0";
        }

        this.displayString = newString;
    }

    void setInputType(int i) {
        this.inputType = i;
    }

    void setPosition(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
    }

    void setSize(int w, int h) {
        this.width = w;
        this.height = h;
    }

    void setBounds(int x, int y, int w, int h) {
        this.xPosition = x;
        this.yPosition = y;
        this.width = w;
        this.height = h;
    }

    void setNext(GuiTextField next) {
        this.next = next;
    }

    void setPrev(GuiTextField prev) {
        this.prev = prev;
    }

    static void next() {
        if (active != null) {
            active.norm();
            active = active.next;
        }

    }

    static void prev() {
        if (active != null) {
            active.norm();
            active = active.prev;
        }

    }

    static GuiTextField getActive() {
        return active;
    }
}
