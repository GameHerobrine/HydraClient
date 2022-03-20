package reifnsk.minimap;

import java.util.ArrayList;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;

public class GuiOptionScreen extends GuiScreen implements GuiScreenInterface {
    public static final int minimapMenu = 0;
    public static final int optionMinimap = 1;
    public static final int optionSurfaceMap = 2;
    public static final int optionEntitiesRadar = 3;
    public static final int aboutMinimap = 4;
    private static final String[] TITLE_STRING;
    private int page;
    private ArrayList<GuiOptionButton> buttonList = new ArrayList();
    private GuiSimpleButton exitMenu;
    private GuiSimpleButton waypoint;
    private GuiSimpleButton keyconfig;
    private int top;
    private int left;
    private int right;
    private int bottom;
    private int centerX;
    private int centerY;

    public GuiOptionScreen() {
    }

    GuiOptionScreen(int page) {
        this.page = page;
    }

    public void initGui() {
        this.centerX = this.width / 2;
        this.centerY = this.height / 2;
        this.controlList.clear();
        this.buttonList.clear();
        EnumOption[] arr$ = EnumOption.values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            EnumOption eo = arr$[i$];
            if (eo.getPage() == this.page && (!this.mc.theWorld.multiplayerWorld || (eo != EnumOption.RENDER_TYPE || ReiMinimap.instance.getAllowCavemap()) && (eo != EnumOption.ENTITIES_RADAR_OPTION || ReiMinimap.instance.getAllowEntitiesRadar()))) {
                GuiOptionButton button = new GuiOptionButton(this.mc.fontRenderer, eo);
                button.setValue(ReiMinimap.instance.getOption(eo));
                this.controlList.add(button);
                this.buttonList.add(button);
            }
        }

        this.left = this.width - GuiOptionButton.getWidth() >> 1;
        this.top = this.height - this.buttonList.size() * 10 >> 1;
        this.right = this.width + GuiOptionButton.getWidth() >> 1;
        this.bottom = this.height + this.buttonList.size() * 10 >> 1;

        for(int i = 0; i < this.buttonList.size(); ++i) {
            GuiOptionButton button = (GuiOptionButton)this.buttonList.get(i);
            button.xPosition = this.left;
            button.yPosition = this.top + i * 10;
        }

        if (this.page == 0) {
            this.exitMenu = new GuiSimpleButton(0, this.centerX - 95, this.bottom + 7, 60, 14, "Exit Menu");
            this.controlList.add(this.exitMenu);
            this.waypoint = new GuiSimpleButton(1, this.centerX - 30, this.bottom + 7, 60, 14, "Waypoints");
            this.controlList.add(this.waypoint);
            this.keyconfig = new GuiSimpleButton(2, this.centerX + 35, this.bottom + 7, 60, 14, "Keyconfig");
            this.controlList.add(this.keyconfig);
        } else {
            this.exitMenu = new GuiSimpleButton(0, this.centerX - 30, this.bottom + 7, 60, 14, "Back");
            this.controlList.add(this.exitMenu);
        }

    }

    public void drawScreen(int i, int j, float f) {
        String title = TITLE_STRING[this.page];
        int titleWidth = this.fontRenderer.getStringWidth(title);
        int optionLeft = this.width - titleWidth >> 1;
        int optionRight = this.width + titleWidth >> 1;
        this.drawRect(optionLeft - 2, this.top - 22, optionRight + 2, this.top - 8, -1610612736);
        this.drawCenteredString(this.fontRenderer, title, this.centerX, this.top - 19, -1);
        this.drawRect(this.left - 2, this.top - 2, this.right + 2, this.bottom + 1, -1610612736);
        super.drawScreen(i, j, f);
    }

    protected void actionPerformed(GuiButton guibutton) {
        if (guibutton instanceof GuiOptionButton) {
            GuiOptionButton gob = (GuiOptionButton)guibutton;
            ReiMinimap.instance.setOption(gob.getOption(), gob.getValue());
            ReiMinimap.instance.saveOptions();
        }

        if (guibutton instanceof GuiSimpleButton) {
            if (guibutton == this.exitMenu) {
                this.mc.displayGuiScreen(this.page == 0 ? null : new GuiOptionScreen(0));
            }

            if (guibutton == this.waypoint) {
                this.mc.displayGuiScreen(new GuiWaypointScreen());
            }

            if (guibutton == this.keyconfig) {
                this.mc.displayGuiScreen(new GuiKeyConfigScreen());
            }
        }

    }

    static {
        TITLE_STRING = new String[]{"Rei's Minimap " + ReiMinimap.version, "Minimap Options", "SurfaceMap Options", "Entities Radar Options", "About Rei's Minimap"};
    }
}
