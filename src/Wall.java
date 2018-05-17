import java.util.Random;

import javax.swing.ImageIcon;

public class Wall {
    //定义各种墙  brick=1,iron=2;
    private int type;
    //细分各种墙  tt
    //private  int tt;
    //位置
    private int heng, shu;
    //墙的图像 brick,iron,
    private ImageIcon now;
    //墙碎了
    // private ImageIcon sw,sa,ss,sd;
    //判断有没有墙
    private boolean alive, ruin, die;
    //0没有 12有
    private int has;

    public Wall(int t, int tt, int heng, int shu) {
        this.type = t;
        if (type == 1) {
            if (tt == 1) now = new ImageIcon("比.png");
            if (tt == 2) now = new ImageIcon("武.png");
            if (tt == 3) now = new ImageIcon("沙墙.png");
            if (tt == 4) now = new ImageIcon("冰墙.png");
        }
        if (type == 2) {
            if (tt == 1) now = new ImageIcon("桶.png");
            if (tt == 2) now = new ImageIcon("水1.png");
            if (tt == 3) now = new ImageIcon("沙.png");
            if (tt == 4) now = new ImageIcon("水2.png");
            if (tt == 5) now = new ImageIcon("水3.png");
            if (tt == 6) now = new ImageIcon("水4.png");
        }
        this.heng = heng;
        this.shu = shu;
        alive = true;
        ruin = false;
        die = false;
        Map.wallmap[heng][shu] = this;
    }

    public int getType() {
        return type;
    }

    public ImageIcon getImage() {
        return now;
    }

    public void Ruin() {
        ruin = true;
    }

    public boolean isRuin() {
        return ruin;
    }

    public void remove() {
        Map.wallmap[heng][shu] = null;
    }

    public int getx() {
        return heng * 50;
    }

    public int gety() {
        return shu * 50 + 12;
    }
}
