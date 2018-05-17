import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;

public class P1 extends player implements KeyListener {
    //现在放下去的糖泡的数量
    protected static int count = 0;

    public P1() {
        super();
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
        Ball ball = new Ball(1, getHeng(), getShu(), power, getBall());
        Music.music[5].play();
    }

    //完美的行动派监听器！！！！！！！！！********************************************************************
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (yangzi <= 4) {
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

            if (yangzi == 0 || yangzi == 1) amount = namount;
            if (yangzi == 0 || yangzi == 1 || yangzi == 2) power = npower;
            if (canspace() && USEtangpao == false && count < amount && Map.boommap[getHeng()][getShu()] == null)
                setBall();
            USEtangpao = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_M && USEfork == false) {
            if (fork > 0 && yangzi == 5) {
                fork -= 1;
                fuhuo();
            }
            USEfork = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (yangzi <= 4) {
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
