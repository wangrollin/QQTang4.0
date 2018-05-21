package element;

import player.Player;

import javax.swing.*;


public class Ball {
    //哪个格子
    private int heng, shu;
    //威力大小
    private int power;
    //炸弹计时
    private int timeCounter = 0;
    //不爆炸的时间
    private static final int MAX_TIME = 300;
    //动态图
    private ImageIcon ballIcon;
    //谁放的

    private Player player;
    //等待中 爆炸中  存在吗
    //private boolean wait=true,die=false,booing=false;
    //事先预警
    private Explosion just;
    private Maps maps;
    public Ball(Player player, int heng, int shu, int power, ImageIcon icon, Maps maps) {
        this.player = player;
        player.addCount();

        this.heng = heng;
        this.shu = shu;
        this.power = power;
        this.ballIcon = icon;
        this.maps = maps;

        maps.getBallMap()[heng][shu] = this;
        this.just = maps.getExplosionMap()[heng][shu];
    }

    public int getPower() {
        return power;
    }

    public ImageIcon getBallIcon() {
        return ballIcon;
    }

    public void addTime() {
        timeCounter += 1;
        if (timeCounter == MAX_TIME || (maps.isExp(heng, shu) && just != maps.getExplosionMap()[heng][shu])) {
            booit();
            remove();
        }
    }

    private void booit() {
        MusicTool.music[2].stop();
        MusicTool.music[2].play();
        //maps.getExplosionMap()[heng][shu] = new Explosion(heng, shu, "oo", maps);
        new Explosion(heng, shu, "oo", maps);
        //0能炸过去     1遇到了墙能炸碎     2遇到了便边界 或者炸不碎的墙
        //s
        for (int i = 1; i <= power; i++) {
            if (cangos(i) == 0) {
                if (i == power) {
                    //maps.getExplosionMap()[heng][shu + i] = new Explosion(heng, shu + i, "s", maps);
                    new Explosion(heng, shu + i, "s", maps);
                }
                if (i != power) {
                    //maps.getExplosionMap()[heng][shu + i] = new Explosion(heng, shu + i, "zs", maps);
                    new Explosion(heng, shu + i, "zs", maps);
                }
                maps.getDaojuMap()[heng][shu + i] = null;
            }
            if (cangos(i) == 1) {
                maps.getWallMap()[heng][shu + i].beRuined();
                //maps.getExplosionMap()[heng][shu + i] = new Explosion(heng, shu + i, "ss", maps);
                new Explosion(heng, shu + i, "ss", maps);
                break;
            }
            if (cangos(i) == 2) {
                if (i != 1) {
                    //maps.getExplosionMap()[heng][shu + i - 1] = new Explosion(heng, shu + i - 1, "zs", maps);
                    new Explosion(heng, shu + i - 1, "zs", maps);
                }
                break;
            }
        }
        //w
        for (int i = 1; i <= power; i++) {
            if (cangow(i) == 0) {
                if (i == power) {
                    //maps.getExplosionMap()[heng][shu - i] = new Explosion(heng, shu - i, "w", maps);
                    new Explosion(heng, shu - i, "w", maps);
                }
                if (i != power) {
                    //maps.getExplosionMap()[heng][shu - i] = new Explosion(heng, shu - i, "zw", maps);
                    new Explosion(heng, shu - i, "zw", maps);
                }
                maps.getDaojuMap()[heng][shu - i] = null;
            }
            if (cangow(i) == 1) {
                maps.getWallMap()[heng][shu - i].beRuined();
                //maps.getExplosionMap()[heng][shu - i] = new Explosion(heng, shu - i, "sw", maps);
                new Explosion(heng, shu - i, "sw", maps);
                break;
            }
            if (cangow(i) == 2) {
                if (i != 1) {
                    //maps.getExplosionMap()[heng][shu - i + 1] = new Explosion(heng, shu - i + 1, "zw", maps);
                    new Explosion(heng, shu - i + 1, "zw", maps);
                }
                break;
            }
        }
        //a
        for (int i = 1; i <= power; i++) {
            if (cangoa(i) == 0) {
                if (i == power) {
                    //maps.getExplosionMap()[heng - i][shu] = new Explosion(heng - i, shu, "a", maps);
                    new Explosion(heng - i, shu, "a", maps);
                }

                if (i != power) {
                    //maps.getExplosionMap()[heng - i][shu] = new Explosion(heng - i, shu, "za", maps);
                    new Explosion(heng - i, shu, "za", maps);
                }
                maps.getDaojuMap()[heng - i][shu] = null;
            }
            if (cangoa(i) == 1) {
                maps.getWallMap()[heng - i][shu].beRuined();
                //maps.getExplosionMap()[heng - i][shu] = new Explosion(heng - i, shu, "sa", maps);
                new Explosion(heng - i, shu, "sa", maps);
                break;
            }
            if (cangoa(i) == 2) {
                if (i != 1) {
                    //maps.getExplosionMap()[heng - i + 1][shu] = new Explosion(heng - i + 1, shu, "za", maps);
                    new Explosion(heng - i + 1, shu, "za", maps);
                }
                break;
            }
        }
        //d
        for (int i = 1; i <= power; i++) {
            if (cangod(i) == 0) {
                if (i == power) {
                    //maps.getExplosionMap()[heng + i][shu] = new Explosion(heng + i, shu, "d", maps);
                    new Explosion(heng + i, shu, "d", maps);
                }
                if (i != power) {
                    //maps.getExplosionMap()[heng + i][shu] = new Explosion(heng + i, shu, "zd", maps);
                    new Explosion(heng + i, shu, "zd", maps);
                }
                maps.getDaojuMap()[heng + i][shu] = null;
            }
            if (cangod(i) == 1) {
                maps.getWallMap()[heng + i][shu].beRuined();
                //maps.getExplosionMap()[heng + i][shu] = new Explosion(heng + i, shu, "sd", maps);
                new Explosion(heng + i, shu, "sd", maps);
                break;
            }
            if (cangod(i) == 2) {
                if (i != 1) {
                    //maps.getExplosionMap()[heng + i - 1][shu] = new Explosion(heng + i - 1, shu, "zd", maps);
                    new Explosion(heng + i - 1, shu, "zd", maps);
                }
                break;
            }
        }
    }

    private void remove() {
        this.maps.getBallMap()[heng][shu] = null;
        this.player.subCount();
    }

    //返回 int  0能炸过去     1遇到了墙能炸碎     2遇到了便边界 或者炸不碎的墙
    private int cangow(int i) {
        if (shu - i >= 0 && (!maps.isWall(heng, shu - i)))
            return 0;
        else if (shu - i >= 0 && maps.isWall(heng, shu - i) && maps.getWallMap()[heng][shu - i].isBreakable())
            return 1;
        else
            return 2;
    }

    private int cangos(int i) {        //墙 和边界
        if (shu + i <= 7 && (!maps.isWall(heng, shu + i)))
            return 0;
        else if (shu + i <= 7 && maps.isWall(heng, shu + i) && maps.getWallMap()[heng][shu + i].isBreakable())
            return 1;
        else
            return 2;
    }

    private int cangoa(int i) {
        if (heng - i >= 0 && (!maps.isWall(heng - i, shu)))
            return 0;
        else if (heng - i >= 0 && maps.isWall(heng - i, shu) && maps.getWallMap()[heng - i][shu].isBreakable())
            return 1;
        else
            return 2;
    }

    private int cangod(int i) {
        if (heng + i <= 12 && (!maps.isWall(heng + i, shu)))
            return 0;
        else if (heng + i <= 12 && maps.isWall(heng + i, shu) && maps.getWallMap()[heng + i][shu].isBreakable())
            return 1;
        else
            return 2;
    }
}
