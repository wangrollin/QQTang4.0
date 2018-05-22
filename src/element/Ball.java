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
    private static final int MAX_TIME = 400;
    //动态图
    private ImageIcon ballIcon;
    //谁放的

    private Player player;
    //事先预警
    private Explosion just;
    private Maps maps;
    public Ball(Player player, int heng, int shu, int power, ImageIcon icon, Maps maps) {
        this.player = player;
        player.addUsedBallCount();

        this.heng = heng;
        this.shu = shu;
        this.power = power;
        this.ballIcon = icon;
        this.maps = maps;

        //maps.setBall(this);
        this.just = maps.getExplosion(heng, shu);
    }

    public int getPower() {
        return power;
    }

    public ImageIcon getBallIcon() {
        return ballIcon;
    }

    public int getHeng() {
        return heng;
    }

    public int getShu() {
        return shu;
    }

    public void addTime() {
        timeCounter += 1;
        if (timeCounter == MAX_TIME
                || (maps.isExplosion(heng, shu) && just != maps.getExplosion(heng, shu))) {
            booit();
            removeBallSelf();
        }
    }

    private void booit() {
        MusicTool.BALL_EXPLODE.play();
        //maps.getExplosionMap()[heng][shu] = new Explosion(heng, shu, "oo", maps);
        maps.setExplosion(new Explosion(heng, shu, "oo", maps));
        //0能炸过去     1遇到了墙能炸碎     2遇到了便边界 或者炸不碎的墙
        //s
        for (int i = 1; i <= power; i++) {
            if (canExplodeDown(i) == 0) {
                if (i == power) {
                    maps.setExplosion(new Explosion(heng, shu + i, "s", maps));
                }
                if (i != power) {
                    maps.setExplosion(new Explosion(heng, shu + i, "zs", maps));

                }
                maps.removeItem(heng, shu + i);
            }
            if (canExplodeDown(i) == 1) {
                maps.getWall(heng, shu + i).beRuined();
                maps.setExplosion(new Explosion(heng, shu + i, "ss", maps));
                break;
            }
            if (canExplodeDown(i) == 2) {
                if (i != 1) {
                    maps.setExplosion(new Explosion(heng, shu + i - 1, "zs", maps));
                }
                break;
            }
        }
        //w
        for (int i = 1; i <= power; i++) {
            if (canExplodeUp(i) == 0) {
                if (i == power) {
                    maps.setExplosion(new Explosion(heng, shu - i, "w", maps));
                }
                if (i != power) {
                    maps.setExplosion(new Explosion(heng, shu - i, "zw", maps));
                }
                maps.removeItem(heng, shu - i);
            }
            if (canExplodeUp(i) == 1) {
                maps.getWall(heng, shu - i).beRuined();
                maps.setExplosion(new Explosion(heng, shu - i, "sw", maps));
                break;
            }
            if (canExplodeUp(i) == 2) {
                if (i != 1) {
                    maps.setExplosion(new Explosion(heng, shu - i + 1, "zw", maps));
                }
                break;
            }
        }
        //a
        for (int i = 1; i <= power; i++) {
            if (canExplodeLeft(i) == 0) {
                if (i == power) {
                    maps.setExplosion(new Explosion(heng - i, shu, "a", maps));
                }

                if (i != power) {
                    maps.setExplosion(new Explosion(heng - i, shu, "za", maps));
                }
                maps.removeItem(heng - i, shu);
            }
            if (canExplodeLeft(i) == 1) {
                maps.getWall(heng - i, shu).beRuined();
                maps.setExplosion(new Explosion(heng - i, shu, "sa", maps));
                break;
            }
            if (canExplodeLeft(i) == 2) {
                if (i != 1) {
                    maps.setExplosion(new Explosion(heng - i + 1, shu, "za", maps));
                }
                break;
            }
        }
        //d
        for (int i = 1; i <= power; i++) {
            if (canExplodeRight(i) == 0) {
                if (i == power) {
                    maps.setExplosion(new Explosion(heng + i, shu, "d", maps));
                }
                if (i != power) {
                    maps.setExplosion(new Explosion(heng + i, shu, "zd", maps));
                }
                maps.removeItem(heng + i, shu);
            }
            if (canExplodeRight(i) == 1) {
                maps.getWall(heng + i, shu).beRuined();
                maps.setExplosion(new Explosion(heng + i, shu, "sd", maps));
                break;
            }
            if (canExplodeRight(i) == 2) {
                if (i != 1) {
                    maps.setExplosion(new Explosion(heng + i - 1, shu, "zd", maps));

                }
                break;
            }
        }
    }

    private void removeBallSelf() {
        this.maps.removeBall(heng, shu);
        this.player.subUsedBallCount();
    }

    //返回 int  0能炸过去     1遇到了墙能炸碎     2遇到了便边界 或者炸不碎的墙
    private int canExplodeUp(int i) {
        if (shu - i >= 0 && (!maps.isWall(heng, shu - i)))
            return 0;
        else if (shu - i >= 0 && maps.isWall(heng, shu - i) && maps.getWall(heng, shu - i).isBreakable())
            return 1;
        else
            return 2;
    }

    private int canExplodeDown(int i) {        //墙 和边界
        if (shu + i <= 7 && (!maps.isWall(heng, shu + i)))
            return 0;
        else if (shu + i <= 7 && maps.isWall(heng, shu + i) && maps.getWall(heng, shu + i).isBreakable())
            return 1;
        else
            return 2;
    }

    private int canExplodeLeft(int i) {
        if (heng - i >= 0 && (!maps.isWall(heng - i, shu)))
            return 0;
        else if (heng - i >= 0 && maps.isWall(heng - i, shu) && maps.getWall(heng - i, shu).isBreakable())
            return 1;
        else
            return 2;
    }

    private int canExplodeRight(int i) {
        if (heng + i <= 12 && (!maps.isWall(heng + i, shu)))
            return 0;
        else if (heng + i <= 12 && maps.isWall(heng + i, shu) && maps.getWall(heng + i ,shu).isBreakable())
            return 1;
        else
            return 2;
    }
}
