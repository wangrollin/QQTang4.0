import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;

public class P5 extends Player implements KeyListener {
    //现在放下去的糖泡的数量
    protected static int count = 0;
    Random aa;
    protected static int siwangcishu = 0, fuhuoshijian = 0, MAX = 500;

    public P5() {
        super();
        aa = new Random();
        X = 125;
        Y = 75;
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
        ball = new ImageIcon("糖泡蓝.gif");
        now = ps;
    }

    public void Die() {
        siwangcishu += 1;
        outlooking = 6;
        setNow();
        now = pdie;
        Music.music[7].play();
    }

    public void setBall() {
        Ball ball = new Ball(5, getHeng(), getShu(), power, getBall());
        Music.music[5].play();
    }

    ///////////改变的地方
    //死后复活
    public void chusheng() {
        if (outlooking == 6) {
            fuhuoshijian += 1;
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

        if (p == BattleBiwu.p1 && (BattleBiwu.p2.outlooking == 0 || BattleBiwu.p2.outlooking == 1 ||
                BattleBiwu.p2.outlooking == 2 || BattleBiwu.p2.outlooking == 3 || BattleBiwu.p2.outlooking == 4)) {
            BattleBiwu.p2.outlooking = 4;
            if (BattleBiwu.p2.now == BattleBiwu.p2.s) {
                BattleBiwu.p2.setNow();
                BattleBiwu.p2.now = BattleBiwu.p2.s;
            }
            if (BattleBiwu.p2.now == BattleBiwu.p2.w) {
                BattleBiwu.p2.setNow();
                BattleBiwu.p2.now = BattleBiwu.p2.w;
            }
            if (BattleBiwu.p2.now == BattleBiwu.p2.a) {
                BattleBiwu.p2.setNow();
                BattleBiwu.p2.now = BattleBiwu.p2.a;
            }
            if (BattleBiwu.p2.now == BattleBiwu.p2.d) {
                BattleBiwu.p2.setNow();
                BattleBiwu.p2.now = BattleBiwu.p2.d;
            }
            BattleBiwu.p2.wuditime = 1;
            BattleBiwu.p2.bianshentime = 0;
        }

        if (p == BattleBiwu.p2 && (BattleBiwu.p1.outlooking == 0 || BattleBiwu.p1.outlooking == 1 ||
                BattleBiwu.p1.outlooking == 2 || BattleBiwu.p1.outlooking == 3 || BattleBiwu.p1.outlooking == 4)) {
            BattleBiwu.p1.outlooking = 4;
            if (BattleBiwu.p1.now == BattleBiwu.p1.s) {
                BattleBiwu.p1.setNow();
                BattleBiwu.p1.now = BattleBiwu.p1.s;
            }
            if (BattleBiwu.p1.now == BattleBiwu.p1.w) {
                BattleBiwu.p1.setNow();
                BattleBiwu.p1.now = BattleBiwu.p1.w;
            }
            if (BattleBiwu.p1.now == BattleBiwu.p1.a) {
                BattleBiwu.p1.setNow();
                BattleBiwu.p1.now = BattleBiwu.p1.a;
            }
            if (BattleBiwu.p1.now == BattleBiwu.p1.d) {
                BattleBiwu.p1.setNow();
                BattleBiwu.p1.now = BattleBiwu.p1.d;
            }
            BattleBiwu.p1.wuditime = 1;
            BattleBiwu.p1.bianshentime = 0;
        }
    }

    public void toDie(Player p) {
        if (wuditime > 0) wuditime += 1;
        if (wuditime == FLASH_TIME) wuditime = 0;
        if (outlooking == 5) {
            dietime += 1;
            if (dietime == BEFORE_DIE_TIME && p == BattleBiwu.p1) {
                Die();
            }
            if (dietime == BEFORE_DIE_TIME && p == BattleBiwu.p2) {
                Die();
            }
            if (BattleBiwu.p1.outlooking != 5 && p == BattleBiwu.p2 && BattleBiwu.p1.getHeng() == BattleBiwu.p2.getHeng() && BattleBiwu.p1.getShu() == BattleBiwu.p2.getShu()) {
                BattleBiwu.p2.Die();
                BattleBiwu.p2.RIGHT = false;
                BattleBiwu.p2.LEFT = false;
                BattleBiwu.p2.UP = false;
                BattleBiwu.p2.DOWN = false;
            }
            if (BattleBiwu.p2.outlooking != 5 && p == BattleBiwu.p1 && BattleBiwu.p1.getHeng() == BattleBiwu.p2.getHeng() && BattleBiwu.p1.getShu() == BattleBiwu.p2.getShu()) {
                BattleBiwu.p1.Die();
                BattleBiwu.p1.RIGHT = false;
                BattleBiwu.p1.LEFT = false;
                BattleBiwu.p1.UP = false;
                BattleBiwu.p1.DOWN = false;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (outlooking <= 4) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_R:
                    dUP = 0;
                    rUP = false;
                    if (rRIGHT == false && UP == false) dRIGHT++;
                    if (rLEFT == false && UP == false) dLEFT++;
                    if (rDOWN == false && UP == false) dDOWN++;
                    UP = true;
                    break;
                case KeyEvent.VK_F:
                    dDOWN = 0;
                    rDOWN = false;
                    if (rRIGHT == false && DOWN == false) dRIGHT++;
                    if (rLEFT == false && DOWN == false) dLEFT++;
                    if (rUP == false && DOWN == false) dUP++;
                    DOWN = true;
                    break;
                case KeyEvent.VK_D:
                    dLEFT = 0;
                    rLEFT = false;
                    if (rRIGHT == false && LEFT == false) dRIGHT++;
                    if (rUP == false && LEFT == false) dUP++;
                    if (rDOWN == false && LEFT == false) dDOWN++;
                    LEFT = true;
                    break;
                case KeyEvent.VK_G:
                    dRIGHT = 0;
                    rRIGHT = false;
                    if (rUP == false && RIGHT == false) dUP++;
                    if (rLEFT == false && RIGHT == false) dLEFT++;
                    if (rDOWN == false && RIGHT == false) dDOWN++;
                    RIGHT = true;
                    break;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            if (outlooking == 0 || outlooking == 1) amount = namount;
            if (outlooking == 0 || outlooking == 1 || outlooking == 2) power = npower;
            if (canspace() && USEtangpao == false && count < amount && Map.boommap[getHeng()][getShu()] == null)
                setBall();
            USEtangpao = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_W && USEfork == false) {
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
                case KeyEvent.VK_R:
                    rUP = true;
                    if (dRIGHT > dUP && UP == true) dRIGHT--;
                    if (dLEFT > dUP && UP == true) dLEFT--;
                    if (dDOWN > dUP && UP == true) dDOWN--;
                    dUP = 0;
                    UP = false;
                    break;
                case KeyEvent.VK_F:
                    rDOWN = true;
                    if (dRIGHT > dDOWN && DOWN == true) dRIGHT--;
                    if (dLEFT > dDOWN && DOWN == true) dLEFT--;
                    if (dUP > dDOWN && DOWN == true) dUP--;
                    DOWN = false;
                    dDOWN = 0;
                    break;
                case KeyEvent.VK_D:
                    rLEFT = true;
                    if (dRIGHT > dLEFT && LEFT == true) dRIGHT--;
                    if (dUP > dLEFT && LEFT == true) dUP--;
                    if (dDOWN > dLEFT && LEFT == true) dDOWN--;
                    LEFT = false;
                    dLEFT = 0;
                    break;
                case KeyEvent.VK_G:
                    rRIGHT = true;
                    if (dUP > dRIGHT && RIGHT == true) dUP--;
                    if (dLEFT > dRIGHT && RIGHT == true) dLEFT--;
                    if (dDOWN > dRIGHT && RIGHT == true) dDOWN--;
                    RIGHT = false;
                    dRIGHT = 0;
                    break;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) USEtangpao = false;
        if (e.getKeyCode() == KeyEvent.VK_W) USEfork = false;
    }
}
