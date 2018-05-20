package element;

import java.util.Random;

import javax.swing.ImageIcon;


public class Explosion {
    private int time = 0, Booming = 30;
    private int heng, shu;
    public String direction;
    //爆炸中的图  四个方向 中心   w,s,a,d,zw,za,zs,zd,oo,sw,sa,ss,sd
    private ImageIcon now;

    private Maps maps;
    public Explosion(int h, int s, String _direction, Maps maps) {
        heng = h;
        shu = s;
        direction = _direction;
        this.maps = maps;
        switch (direction) {
            case "w": {
                now = new ImageIcon("上头.jpg");
                break;
            }
            case "s": {
                now = new ImageIcon("下头.jpg");
                break;
            }
            case "a": {
                now = new ImageIcon("左头.jpg");
                break;
            }
            case "d": {
                now = new ImageIcon("右头.jpg");
                break;
            }
            case "zw": {
                now = new ImageIcon("上中.jpg");
                break;
            }
            case "za": {
                now = new ImageIcon("左中.jpg");
                break;
            }
            case "zs": {
                now = new ImageIcon("下中.jpg");
                break;
            }
            case "zd": {
                now = new ImageIcon("右中.jpg");
                break;
            }
            case "oo": {
                now = new ImageIcon("中心.jpg");
                break;
            }
            case "sw": {
                now = new ImageIcon("碎.jpg");
                break;
            }
            case "sa": {
                now = new ImageIcon("碎.jpg");
                break;
            }
            case "ss": {
                now = new ImageIcon("碎.jpg");
                break;
            }
            case "sd": {
                now = new ImageIcon("碎.jpg");
                break;
            }
        }
        maps.getExplosionMap()[heng][shu] = this;
    }

    public void addTime() {
        time += 1;
        if (time == Booming) {
            if (direction.equals("sw") || direction.equals("sd") || direction.equals("ss")
                    || direction.equals("sa")) {
                getDaoju();
                //maps.getWallMap()[heng][shu].remove(maps);
                maps.getWallMap()[heng][shu] = null;
                //maps.removeWall(heng, shu);
            }
            remove();
        }
    }

    public void getDaoju() {
        Random a = new Random();
        int has = a.nextInt(3);
        if (has == 0 || has == 1) maps.getDaojuMap()[heng][shu] = new Daoju(heng, shu, maps);
    }

    public ImageIcon getImage() {
        return now;
    }

    public void remove() {
        maps.getExplosionMap()[heng][shu] = null;
    }
}
