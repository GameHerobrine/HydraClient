package reifnsk.minimap;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GuiWaypointScreen extends GuiScreen implements GuiScreenInterface {
    static final int MIN_STRING_WIDTH = 64;
    static final int MAX_STRING_WIDTH = 160;
    static final int LIST_SIZE = 9;
    private ReiMinimap rmm;
    private ArrayList<Waypoint> wayPts;
    private GuiWaypoint[] guiWaypoints;
    private GuiScrollbar scrollbar;
    private GuiSimpleButton backButton;
    private GuiSimpleButton addButton;
    private GuiSimpleButton removeButton;
    private GuiSimpleButton cancelButton;
    private int scroll;
    private boolean removeMode;
    private int maxStringWidth;

    public GuiWaypointScreen() {
        this.rmm = ReiMinimap.instance;
        this.wayPts = ReiMinimap.instance.getWaypoints();
        this.guiWaypoints = new GuiWaypoint[9];
        this.scrollbar = new GuiScrollbar(0, 0, 0, 12, 90);

        for(int i = 0; i < 9; ++i) {
            this.guiWaypoints[i] = new GuiWaypoint(i, this);
        }

    }

    public void initGui() {
        this.controlList.clear();
        Keyboard.enableRepeatEvents(true);
        GuiWaypoint[] arr$ = this.guiWaypoints;
        int bottom = arr$.length;

        for(int i$ = 0; i$ < bottom; ++i$) {
            GuiWaypoint gpt = arr$[i$];
            this.controlList.add(gpt);
        }

        this.controlList.add(this.scrollbar);
        this.updateWaypoints();
        int centerX = this.width / 2;
        bottom = this.height + 90 >> 1;
        this.backButton = new GuiSimpleButton(0, centerX - 65, bottom + 7, 40, 14, "Back");
        this.controlList.add(this.backButton);
        this.addButton = new GuiSimpleButton(0, centerX - 20, bottom + 7, 40, 14, "Add");
        this.controlList.add(this.addButton);
        this.removeButton = new GuiSimpleButton(0, centerX + 25, bottom + 7, 40, 14, "Remove");
        this.controlList.add(this.removeButton);
        this.cancelButton = new GuiSimpleButton(0, centerX - 20, bottom + 7, 40, 14, "Cancel");
        this.controlList.add(this.cancelButton);
        this.setRemoveMode(this.removeMode);
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    public void drawScreen(int mouseX, int mouseY, float f) {
        int gwpWidth = Math.min(160, this.maxStringWidth) + 16;
        int top = this.height - 90 >> 1;
        int bottom = this.height + 90 >> 1;
        int left = this.width - gwpWidth - 45 - 10 >> 1;
        int right = this.width + gwpWidth + 45 + 10 >> 1;
        this.drawRect(left - 2, top - 2, right + 2, bottom + 2, -1610612736);
        super.drawScreen(mouseX, mouseY, f);
        String title = "Waypoints";
        int titleWidth = this.fontRenderer.getStringWidth(title);
        int titleLeft = this.width - titleWidth >> 1;
        int titleRight = this.width + titleWidth >> 1;
        this.drawRect(titleLeft - 2, top - 22, titleRight + 2, top - 8, -1610612736);
        this.drawCenteredString(this.fontRenderer, title, this.width / 2, top - 19, -1);
    }

    public void updateScreen() {
        int temp = (int)this.scrollbar.getValue();
        if (this.scroll != temp) {
            this.scroll = temp;
            this.setWaypoints();
        }

    }

    protected void keyTyped(char c, int i) {
        super.keyTyped(c, i);
        switch(i) {
        case 199:
            this.scrollbar.setValue(this.scrollbar.getMinimum());
            break;
        case 200:
            this.scrollbar.unitDecrement();
            break;
        case 201:
            this.scrollbar.blockDecrement();
        case 202:
        case 203:
        case 204:
        case 205:
        case 206:
        default:
            break;
        case 207:
            this.scrollbar.setValue(this.scrollbar.getMaximum());
            break;
        case 208:
            this.scrollbar.unitIncrement();
            break;
        case 209:
            this.scrollbar.blockIncrement();
        }

    }

    public void handleMouseInput() {
        super.handleMouseInput();
        int i = Mouse.getDWheel();
        if (i != 0) {
            i = i < 0 ? 3 : -3;
            this.scrollbar.setValue(this.scrollbar.getValue() + (float)i);
        }

    }

    protected void actionPerformed(GuiButton guibutton) {
        if (guibutton == this.backButton) {
            this.mc.displayGuiScreen(new GuiOptionScreen());
        }

        if (guibutton == this.removeButton) {
            this.setRemoveMode(true);
        }

        if (guibutton == this.cancelButton) {
            this.setRemoveMode(false);
        }

        if (guibutton == this.addButton) {
            this.mc.displayGuiScreen(new GuiWaypointEditorScreen(this, (Waypoint)null));
        }

    }

    void setRemoveMode(boolean b) {
        this.backButton.enabled = this.backButton.enabled2 = !b;
        this.addButton.enabled = this.addButton.enabled2 = !b;
        this.removeButton.enabled = this.removeButton.enabled2 = !b;
        this.cancelButton.enabled = this.cancelButton.enabled2 = b;
        if (this.removeMode != b) {
            this.removeMode = b;
        }

    }

    boolean getRemoveMode() {
        return this.removeMode;
    }

    void addWaypoint(Waypoint wp) {
        if (!this.wayPts.contains(wp)) {
            this.wayPts.add(wp);
            this.rmm.saveWaypoints();
            this.updateWaypoints();
            this.scrollbar.setValue(this.scrollbar.getMaximum());
        }

    }

    void removeWaypoint(Waypoint wp) {
        if (this.wayPts.remove(wp)) {
            this.rmm.saveWaypoints();
            this.updateWaypoints();
        }

        this.setRemoveMode(false);
    }

    void updateWaypoint(Waypoint wp) {
        if (this.wayPts.contains(wp)) {
            this.rmm.saveWaypoints();
            this.updateWaypoints();
        }

    }

    private void updateWaypoints() {
        this.maxStringWidth = 64;
        int i = 0;

        for(int j = this.wayPts.size(); i < j; ++i) {
            Waypoint pt = (Waypoint)this.wayPts.get(i);
            this.maxStringWidth = Math.max(this.maxStringWidth, this.fontRenderer.getStringWidth(i + 1 + ") " + pt.name));
        }

        this.scrollbar.setMinimum(0.0F);
        this.scrollbar.setMaximum((float)this.wayPts.size());
        this.scrollbar.setVisibleAmount((float)Math.min(9, this.wayPts.size()));
        this.scroll = (int)this.scrollbar.getValue();
        this.updateGui();
        this.setWaypoints();
    }

    private void updateGui() {
        int gwpWidth = Math.min(160, this.maxStringWidth) + 16;
        int top = this.height - 90 - 4 >> 1;
        int left = this.width - gwpWidth - 45 - 12 >> 1;
        int right = this.width + gwpWidth + 45 + 12 >> 1;

        for(int i = 0; i < 9; ++i) {
            this.guiWaypoints[i].bounds(left + 2, top + 2 + 10 * i, gwpWidth + 45, 9);
        }

        this.scrollbar.xPosition = right - 12;
        this.scrollbar.yPosition = top + 2;
    }

    private void setWaypoints() {
        for(int i = 0; i < 9; ++i) {
            int num = i + this.scroll;
            this.guiWaypoints[i].setWaypoint(num + 1, num < this.wayPts.size() ? (Waypoint)this.wayPts.get(num) : null);
        }

    }

    Minecraft getMinecraft() {
        return this.mc;
    }
}
