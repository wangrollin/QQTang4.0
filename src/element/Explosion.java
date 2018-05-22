package element;

import java.util.Random;

import javax.swing.ImageIcon;


public class Explosion {
    private int time = 0, Booming = 40;
    private int heng, shu;
    public String position;

    public static final String UP_RUINED_EXPLOSION = "sw";
    public static final String LEFT_RUINED_EXPLOSION = "sa";
    public static final String RIGHT_RUINED_EXPLOSION = "sd";
    public static final String DOWN_RUINED_EXPLOSION = "ss";
    public static final String UP_HEAD_EXPLOSION = "w";
    public static final String LEFT_HEAD_EXPLOSION = "a";
    public static final String RIGHT_HEAD_EXPLOSION = "d";
    public static final String DOWN_HEAD_EXPLOSION = "s";
    public static final String UP_CENTER_EXPLOSION = "zw";
    public static final String LEFT_CENTER_EXPLOSION = "za";
    public static final String RIGHT_CENTER_EXPLOSION = "zd";
    public static final String DOWN_CENTER_EXPLOSION = "zs";
    public static final String CENTER_EXPLOSION = "oo";

    private ImageIcon explosionIcon;

    private Maps maps;
    public Explosion(int h, int s, String position, Maps maps) {
        heng = h;
        shu = s;
        this.position = position;
        this.maps = maps;

        setExplosionIcon();
        //maps.setExplosion(this);
    }

    private void setExplosionIcon() {
        switch (this.position) {
            case UP_HEAD_EXPLOSION: {
                explosionIcon = new ImageIcon("上头.jpg");
                break;
            }
            case DOWN_HEAD_EXPLOSION: {
                explosionIcon = new ImageIcon("下头.jpg");
                break;
            }
            case LEFT_HEAD_EXPLOSION: {
                explosionIcon = new ImageIcon("左头.jpg");
                break;
            }
            case RIGHT_HEAD_EXPLOSION: {
                explosionIcon = new ImageIcon("右头.jpg");
                break;
            }
            case UP_CENTER_EXPLOSION: {
                explosionIcon = new ImageIcon("上中.jpg");
                break;
            }
            case LEFT_CENTER_EXPLOSION: {
                explosionIcon = new ImageIcon("左中.jpg");
                break;
            }
            case DOWN_CENTER_EXPLOSION: {
                explosionIcon = new ImageIcon("下中.jpg");
                break;
            }
            case RIGHT_CENTER_EXPLOSION: {
                explosionIcon = new ImageIcon("右中.jpg");
                break;
            }
            case CENTER_EXPLOSION: {
                explosionIcon = new ImageIcon("中心.jpg");
                break;
            }
            case UP_RUINED_EXPLOSION: {
                explosionIcon = new ImageIcon("碎.jpg");
                break;
            }
            case LEFT_RUINED_EXPLOSION: {
                explosionIcon = new ImageIcon("碎.jpg");
                break;
            }
            case DOWN_RUINED_EXPLOSION: {
                explosionIcon = new ImageIcon("碎.jpg");
                break;
            }
            case RIGHT_RUINED_EXPLOSION: {
                explosionIcon = new ImageIcon("碎.jpg");
                break;
            }
        }
    }

    public void addTime() {
        time += 1;
        if (time == Booming) {
            if (position.equals("sw") || position.equals("sd") || position.equals("ss")
                    || position.equals("sa")) {
                creatDaoju();
                maps.removeWall(heng, shu);
            }
            remove();
        }
    }

    public void creatDaoju() {
        Random random = new Random();
        int has = random.nextInt(3);
        if (has == 0 || has == 1) {
            maps.setItem(heng, shu, new Item());
        }
    }

    public ImageIcon getImage() {
        return explosionIcon;
    }

    public void remove() {
        maps.removeExplosion(heng, shu);
    }

    public int getHeng() {
        return heng;
    }

    public int getShu() {
        return shu;
    }
}
