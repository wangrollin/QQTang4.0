import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;


public class P4 extends Player implements KeyListener {

    //现在放下去的糖泡的数量
    protected static int count = 0;
    Random aa;
    protected static int siwangcishu = 0, fuhuoshijian = 0, MAX = 500;

    public P4() {
        super();
        aa = new Random();
        X = 125;
        Y = 325;
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
        ball = new ImageIcon("糖泡红.gif");
        now = ps;
    }

    public void setBall() {
        Ball ball = new Ball(4, getHeng(), getShu(), power, getBall());
        Music.music[5].play();
    }

    ///////////改变的地方
    //死后复活
    public void chusheng() {
        if (outlooking == 6) {
            fuhuoshijian += 1;
            System.out.println(fuhuoshijian);
            if (fuhuoshijian == MAX) {
                outlooking = 0;
                X = aa.nextInt(500) + 50;
                Y = aa.nextInt(300) + 50;
                while (Map.isWall(getHeng(), getShu())) {
                    X = aa.nextInt(500) + 50;
                    Y = aa.nextInt(300) + 50;
                }
                fuhuoshijian = 0;
                wuditime = 1;
            }
        }
    }

    public void beZhu(Player p) {

        if (p == BattleBiwuPanel.p1 && (BattleBiwuPanel.p2.outlooking == 0 || BattleBiwuPanel.p2.outlooking == 1 ||
                BattleBiwuPanel.p2.outlooking == 2 || BattleBiwuPanel.p2.outlooking == 3 || BattleBiwuPanel.p2.outlooking == 4)) {
            BattleBiwuPanel.p2.outlooking = 4;
            if (BattleBiwuPanel.p2.now == BattleBiwuPanel.p2.s) {
                BattleBiwuPanel.p2.setNow();
                BattleBiwuPanel.p2.now = BattleBiwuPanel.p2.s;
            }
            if (BattleBiwuPanel.p2.now == BattleBiwuPanel.p2.w) {
                BattleBiwuPanel.p2.setNow();
                BattleBiwuPanel.p2.now = BattleBiwuPanel.p2.w;
            }
            if (BattleBiwuPanel.p2.now == BattleBiwuPanel.p2.a) {
                BattleBiwuPanel.p2.setNow();
                BattleBiwuPanel.p2.now = BattleBiwuPanel.p2.a;
            }
            if (BattleBiwuPanel.p2.now == BattleBiwuPanel.p2.d) {
                BattleBiwuPanel.p2.setNow();
                BattleBiwuPanel.p2.now = BattleBiwuPanel.p2.d;
            }
            BattleBiwuPanel.p2.wuditime = 1;
            BattleBiwuPanel.p2.bianshentime = 0;
        }

        if (p == BattleBiwuPanel.p2 && (BattleBiwuPanel.p1.outlooking == 0 || BattleBiwuPanel.p1.outlooking == 1 ||
                BattleBiwuPanel.p1.outlooking == 2 || BattleBiwuPanel.p1.outlooking == 3 || BattleBiwuPanel.p1.outlooking == 4)) {
            BattleBiwuPanel.p1.outlooking = 4;
            if (BattleBiwuPanel.p1.now == BattleBiwuPanel.p1.s) {
                BattleBiwuPanel.p1.setNow();
                BattleBiwuPanel.p1.now = BattleBiwuPanel.p1.s;
            }
            if (BattleBiwuPanel.p1.now == BattleBiwuPanel.p1.w) {
                BattleBiwuPanel.p1.setNow();
                BattleBiwuPanel.p1.now = BattleBiwuPanel.p1.w;
            }
            if (BattleBiwuPanel.p1.now == BattleBiwuPanel.p1.a) {
                BattleBiwuPanel.p1.setNow();
                BattleBiwuPanel.p1.now = BattleBiwuPanel.p1.a;
            }
            if (BattleBiwuPanel.p1.now == BattleBiwuPanel.p1.d) {
                BattleBiwuPanel.p1.setNow();
                BattleBiwuPanel.p1.now = BattleBiwuPanel.p1.d;
            }
            BattleBiwuPanel.p1.wuditime = 1;
            BattleBiwuPanel.p1.bianshentime = 0;
        }
    }

    public void Die() {
        siwangcishu += 1;
        outlooking = 6;
        setNow();
        now = pdie;
        Music.music[7].play();
    }

    public void toDie(Player p) {
        if (wuditime > 0) wuditime += 1;
        if (wuditime == FLASH_TIME) wuditime = 0;
        if (outlooking == 5) {
            dietime += 1;
            if (dietime == BEFORE_DIE_TIME && p == BattleBiwuPanel.p1) {
                Die();
            }
            if (dietime == BEFORE_DIE_TIME && p == BattleBiwuPanel.p2) {
                Die();
            }
            if (BattleBiwuPanel.p1.outlooking != 5 && p == BattleBiwuPanel.p2 && BattleBiwuPanel.p1.getHeng() == BattleBiwuPanel.p2.getHeng() && BattleBiwuPanel.p1.getShu() == BattleBiwuPanel.p2.getShu()) {
                BattleBiwuPanel.p2.Die();
                BattleBiwuPanel.p2.RIGHT = false;
                BattleBiwuPanel.p2.LEFT = false;
                BattleBiwuPanel.p2.UP = false;
                BattleBiwuPanel.p2.DOWN = false;
            }
            if (BattleBiwuPanel.p2.outlooking != 5 && p == BattleBiwuPanel.p1 && BattleBiwuPanel.p1.getHeng() == BattleBiwuPanel.p2.getHeng() && BattleBiwuPanel.p1.getShu() == BattleBiwuPanel.p2.getShu()) {
                BattleBiwuPanel.p1.Die();
                BattleBiwuPanel.p1.RIGHT = false;
                BattleBiwuPanel.p1.LEFT = false;
                BattleBiwuPanel.p1.UP = false;
                BattleBiwuPanel.p1.DOWN = false;
            }
        }
    }

    //完美的行动派监听器！！！！！！！！！********************************************************************
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (outlooking <= 4) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    dUP = 0;
                    rUP = false;
                    if (rRIGHT == false && UP == false) dRIGHT++;
                    if (rLEFT == false && UP == false) dLEFT++;
                    if (rDOWN == false && UP == false) dDOWN++;
                    UP = true;
                    break;
                case KeyEvent.VK_DOWN:
                    dDOWN = 0;
                    rDOWN = false;
                    if (rRIGHT == false && DOWN == false) dRIGHT++;
                    if (rLEFT == false && DOWN == false) dLEFT++;
                    if (rUP == false && DOWN == false) dUP++;
                    DOWN = true;
                    break;
                case KeyEvent.VK_LEFT:
                    dLEFT = 0;
                    rLEFT = false;
                    if (rRIGHT == false && LEFT == false) dRIGHT++;
                    if (rUP == false && LEFT == false) dUP++;
                    if (rDOWN == false && LEFT == false) dDOWN++;
                    LEFT = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    dRIGHT = 0;
                    rRIGHT = false;
                    if (rUP == false && RIGHT == false) dUP++;
                    if (rLEFT == false && RIGHT == false) dLEFT++;
                    if (rDOWN == false && RIGHT == false) dDOWN++;
                    RIGHT = true;
                    break;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

            if (outlooking == 0 || outlooking == 1) amount = namount;
            if (outlooking == 0 || outlooking == 1 || outlooking == 2) power = npower;
            if (canspace() && USEtangpao == false && count < amount && Map.boommap[getHeng()][getShu()] == null)
                setBall();
            USEtangpao = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_M && USEfork == false) {
            if (fork > 0 && outlooking == 5) {
                fork -= 1;
                fuhuo();
            }
            USEfork = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (outlooking <= 4) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    rUP = true;
                    if (dRIGHT > dUP && UP == true) dRIGHT--;
                    if (dLEFT > dUP && UP == true) dLEFT--;
                    if (dDOWN > dUP && UP == true) dDOWN--;
                    dUP = 0;
                    UP = false;
                    break;
                case KeyEvent.VK_DOWN:
                    rDOWN = true;
                    if (dRIGHT > dDOWN && DOWN == true) dRIGHT--;
                    if (dLEFT > dDOWN && DOWN == true) dLEFT--;
                    if (dUP > dDOWN && DOWN == true) dUP--;
                    DOWN = false;
                    dDOWN = 0;
                    break;
                case KeyEvent.VK_LEFT:
                    rLEFT = true;
                    if (dRIGHT > dLEFT && LEFT == true) dRIGHT--;
                    if (dUP > dLEFT && LEFT == true) dUP--;
                    if (dDOWN > dLEFT && LEFT == true) dDOWN--;
                    LEFT = false;
                    dLEFT = 0;
                    break;
                case KeyEvent.VK_RIGHT:
                    rRIGHT = true;
                    if (dUP > dRIGHT && RIGHT == true) dUP--;
                    if (dLEFT > dRIGHT && RIGHT == true) dLEFT--;
                    if (dDOWN > dRIGHT && RIGHT == true) dDOWN--;
                    RIGHT = false;
                    dRIGHT = 0;
                    break;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) USEtangpao = false;
        if (e.getKeyCode() == KeyEvent.VK_M) USEfork = false;

    }
}
