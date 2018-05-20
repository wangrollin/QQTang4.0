package element;

import javax.swing.ImageIcon;

public class Wall {
    //定义各种墙  brick=1,iron=2;
    //private int wallType;
    //public static final int breakableWallType = 1;
    //public static final int unbreakableWallType = 2;


    private boolean isBreakable;

    public boolean isBreakable() {
        return isBreakable;
    }

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

    //细分各种墙  tt
    //private  int tt;
    //位置
    //private int heng, shu;
    //墙的图像 brick,iron,
    private ImageIcon wallIcon;
    //墙碎了
    // private ImageIcon sw,sa,ss,sd;
    //判断有没有墙
    private boolean ruin, alive, die;
    //0没有 12有
    /*private int has;*/

    //public element.Wall(int t, int tt, int heng, int shu) {
    public Wall(int wallType) {
        this.wallType = wallType;
        if (this.wallType <= WALL_TYPE3) {
            isBreakable = true;
        } else {
            isBreakable = false;
        }

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


       /* this.wallType = t;
        if (wallType == breakableWallType) {
            if (tt == 1) wallIcon = new ImageIcon("比.png");
            if (tt == 2) wallIcon = new ImageIcon("武.png");
            if (tt == 3) wallIcon = new ImageIcon("沙墙.png");
            if (tt == 4) wallIcon = new ImageIcon("冰墙.png");
        }
        if (wallType == unbreakableWallType) {
            if (tt == 1) wallIcon = new ImageIcon("桶.png");
            if (tt == 2) wallIcon = new ImageIcon("水1.png");
            if (tt == 3) wallIcon = new ImageIcon("沙.png");
            if (tt == 4) wallIcon = new ImageIcon("水2.png");
            if (tt == 5) wallIcon = new ImageIcon("水3.png");
            if (tt == 6) wallIcon = new ImageIcon("水4.png");
        }*/

        //this.heng = heng;
        //this.shu = shu;
        alive = true;
        ruin = false;
        die = false;
        //element.Maps.wallMap[heng][shu] = this;
    }

    public int getWallType() {
        return wallType;
    }

    public ImageIcon getWallIcon() {
        return wallIcon;
    }

    public void Ruin() {
        ruin = true;
    }

    public boolean isRuin() {
        return ruin;
    }

   /* public void remove(Maps maps) {
        maps.getWallMap()[heng][shu] = null;
    }*/


/*    public int getx() {
        return heng * 50;
    }

    public int gety() {
        return shu * 50 + 12;
    }*/
}
