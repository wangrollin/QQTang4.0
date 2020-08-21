package element;

import javax.swing.*;

public class Wall {
    private boolean isBreakable;

    private int wallType;
    public static final int WALL_TYPE0 = 0;
    public static final int WALL_TYPE1 = 1;
    public static final int WALL_TYPE2 = 2;
    public static final int WALL_TYPE3 = 3;
    public static final int WALL_TYPE4 = 4;
    public static final int WALL_TYPE5 = 5;
    public static final int WALL_TYPE6 = 6;
    public static final int WALL_TYPE7 = 7;
    public static final int WALL_TYPE8 = 8;
    public static final int WALL_TYPE9 = 9;

    private ImageIcon wallIcon;

    private boolean isRuined;

    public Wall(int wallType) {
        this.wallType = wallType;
        if (this.wallType <= WALL_TYPE3) {
            isBreakable = true;
        } else {
            isBreakable = false;
        }

        setWallIcon();

        isRuined = false;
    }

    public boolean isBreakable() {
        return isBreakable;
    }

    public ImageIcon getWallIcon() {
        return wallIcon;
    }

    public void beRuined() {
        isRuined = true;
    }

    public boolean isRuined() {
        return isRuined;
    }

    private void setWallIcon() {
        if (this.wallType == WALL_TYPE0) wallIcon = new ImageIcon("比.png");
        if (this.wallType == WALL_TYPE1) wallIcon = new ImageIcon("武.png");
        if (this.wallType == WALL_TYPE2) wallIcon = new ImageIcon("沙墙.png");
        if (this.wallType == WALL_TYPE3) wallIcon = new ImageIcon("冰墙.png");

        if (this.wallType == WALL_TYPE4) wallIcon = new ImageIcon("桶.png");
        if (this.wallType == WALL_TYPE5) wallIcon = new ImageIcon("水1.png");
        if (this.wallType == WALL_TYPE6) wallIcon = new ImageIcon("沙.png");
        if (this.wallType == WALL_TYPE7) wallIcon = new ImageIcon("水2.png");
        if (this.wallType == WALL_TYPE8) wallIcon = new ImageIcon("水3.png");
        if (this.wallType == WALL_TYPE9) wallIcon = new ImageIcon("水4.png");
    }

    public int getWallType() {
        return wallType;
    }
}
