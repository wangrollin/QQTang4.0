package player;

import constants.GameConstants;
import element.Ball;
import element.Maps;
import element.MusicTool;

import java.util.Random;

import javax.swing.ImageIcon;


public class BiwuModePlayer extends Player {
    //private int playerNumber;
/*    //现在放下去的糖泡的数量
    protected static int count = 0;*/
    Random aa;
    public int siwangcishu = 0, fuhuoshijian = 0, MAX = 500;

    public BiwuModePlayer(int playerNumber, Maps maps) {
        super(playerNumber, maps);
        //this.playerNumber = playerNumber;
        aa = new Random();

        if (getPlayerNumber() == GameConstants.PLAYER1) {
            setX(125);
            setY(325);
            ps = new ImageIcon("战士下.gif");
            pw = new ImageIcon("战士上.gif");
            pa = new ImageIcon("战士左.gif");
            pd = new ImageIcon("战士右.gif");

            sps = new ImageIcon("s战士下.gif");
            spw = new ImageIcon("s战士上.gif");
            spa = new ImageIcon("s战士左.gif");
            spd = new ImageIcon("s战士右.gif");

            pkunzhu = new ImageIcon("炸弹火.gif");
            pdie = new ImageIcon("P1die.gif");
            pwin = new ImageIcon("P1win.gif");
            ballIcon = new ImageIcon("糖泡红.gif");
        } else {
            setX(125);
            setY(75);
            ps = new ImageIcon("包子下.gif");
            pw = new ImageIcon("包子上.gif");
            pa = new ImageIcon("包子左.gif");
            pd = new ImageIcon("包子右.gif");

            sps = new ImageIcon("s包子下.gif");
            spw = new ImageIcon("s包子上.gif");
            spa = new ImageIcon("s包子左.gif");
            spd = new ImageIcon("s包子右.gif");

            pkunzhu = new ImageIcon("炸弹水.gif");
            pdie = new ImageIcon("P2die.gif");
            pwin = new ImageIcon("P2win.jpg");
            ballIcon = new ImageIcon("糖泡蓝.gif");
        }

        now = ps;
    }
    public void Die() {
        siwangcishu += 1;
        outlooking = 6;
        setNow();
        now = pdie;
        MusicTool.music[7].play();
    }

   /*
    public void setBall() {
            Ball ball = new Ball(this, getHeng(), getShu(), power, getBallIcon());
            element.MusicTool.music[5].play();
    }*/

    public void keepDoing(Player anotherPlayer) {
        move();
        toDie(anotherPlayer);
        eatdaoju();
        beExp();
        beBack();
        chusheng();
    }

    ///////////改变的地方
    //死后复活
    public void chusheng() {
        if (outlooking == 6) {
            fuhuoshijian += 1;
            //System.out.println(fuhuoshijian);
            if (fuhuoshijian == MAX) {
                outlooking = 0;
                setX(aa.nextInt(500) + 50);
                setY(aa.nextInt(300) + 50);
                while (getMaps().isWall(getHeng(), getShu())) {
                    setX(aa.nextInt(500) + 50);
                    setY(aa.nextInt(300) + 50);
                }
                fuhuoshijian = 0;
                wuditime = 1;
            }
        }
    }

    public void toDie(Player anotherPlayer) {
        if (wuditime > 0) wuditime += 1;
        if (wuditime == Player.FLASH_TIME) wuditime = 0;
        if (outlooking == 5) {
            dietime += 1;
            /*if (dietime == BEFORE_DIE_TIME && p == panel.battle.BattleBiwuPanel.p1) {
                Die();
            }
            if (dietime == BEFORE_DIE_TIME && p == panel.battle.BattleBiwuPanel.p2) {
                Die();
            }*/
            if (dietime == Player.BEFORE_DIE_TIME) {
                Die();
            } else if (anotherPlayer.outlooking != 5
                    && getHeng() == anotherPlayer.getHeng()
                    && getShu() == anotherPlayer.getShu()) {
                Die();
                RIGHT = false;
                LEFT = false;
                UP = false;
                DOWN = false;
            }
        }
    }


}


    /*public void beZhu(player.Player p) {

        if (p == panel.battle.BattleBiwuPanel.p1 && (panel.battle.BattleBiwuPanel.p2.outlooking == 0 || panel.battle.BattleBiwuPanel.p2.outlooking == 1 ||
                panel.battle.BattleBiwuPanel.p2.outlooking == 2 || panel.battle.BattleBiwuPanel.p2.outlooking == 3 || panel.battle.BattleBiwuPanel.p2.outlooking == 4)) {
            panel.battle.BattleBiwuPanel.p2.outlooking = 4;
            if (panel.battle.BattleBiwuPanel.p2.now == panel.battle.BattleBiwuPanel.p2.s) {
                panel.battle.BattleBiwuPanel.p2.setNow();
                panel.battle.BattleBiwuPanel.p2.now = panel.battle.BattleBiwuPanel.p2.s;
            }
            if (panel.battle.BattleBiwuPanel.p2.now == panel.battle.BattleBiwuPanel.p2.w) {
                panel.battle.BattleBiwuPanel.p2.setNow();
                panel.battle.BattleBiwuPanel.p2.now = panel.battle.BattleBiwuPanel.p2.w;
            }
            if (panel.battle.BattleBiwuPanel.p2.now == panel.battle.BattleBiwuPanel.p2.a) {
                panel.battle.BattleBiwuPanel.p2.setNow();
                panel.battle.BattleBiwuPanel.p2.now = panel.battle.BattleBiwuPanel.p2.a;
            }
            if (panel.battle.BattleBiwuPanel.p2.now == panel.battle.BattleBiwuPanel.p2.d) {
                panel.battle.BattleBiwuPanel.p2.setNow();
                panel.battle.BattleBiwuPanel.p2.now = panel.battle.BattleBiwuPanel.p2.d;
            }
            panel.battle.BattleBiwuPanel.p2.wuditime = 1;
            panel.battle.BattleBiwuPanel.p2.bianshentime = 0;
        }

        if (p == panel.battle.BattleBiwuPanel.p2 && (panel.battle.BattleBiwuPanel.p1.outlooking == 0 || panel.battle.BattleBiwuPanel.p1.outlooking == 1 ||
                panel.battle.BattleBiwuPanel.p1.outlooking == 2 || panel.battle.BattleBiwuPanel.p1.outlooking == 3 || panel.battle.BattleBiwuPanel.p1.outlooking == 4)) {
            panel.battle.BattleBiwuPanel.p1.outlooking = 4;
            if (panel.battle.BattleBiwuPanel.p1.now == panel.battle.BattleBiwuPanel.p1.s) {
                panel.battle.BattleBiwuPanel.p1.setNow();
                panel.battle.BattleBiwuPanel.p1.now = panel.battle.BattleBiwuPanel.p1.s;
            }
            if (panel.battle.BattleBiwuPanel.p1.now == panel.battle.BattleBiwuPanel.p1.w) {
                panel.battle.BattleBiwuPanel.p1.setNow();
                panel.battle.BattleBiwuPanel.p1.now = panel.battle.BattleBiwuPanel.p1.w;
            }
            if (panel.battle.BattleBiwuPanel.p1.now == panel.battle.BattleBiwuPanel.p1.a) {
                panel.battle.BattleBiwuPanel.p1.setNow();
                panel.battle.BattleBiwuPanel.p1.now = panel.battle.BattleBiwuPanel.p1.a;
            }
            if (panel.battle.BattleBiwuPanel.p1.now == panel.battle.BattleBiwuPanel.p1.d) {
                panel.battle.BattleBiwuPanel.p1.setNow();
                panel.battle.BattleBiwuPanel.p1.now = panel.battle.BattleBiwuPanel.p1.d;
            }
            panel.battle.BattleBiwuPanel.p1.wuditime = 1;
            panel.battle.BattleBiwuPanel.p1.bianshentime = 0;
        }
    }*/