/*
package abandon;

import element.Ball;
import element.Maps;
import player.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;


public class JingjiModePlayer2 extends Player implements KeyListener {
    //现在放下去的糖泡的数量
    protected static int count = 0;

    public JingjiModePlayer2() {
        super();
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
        ballIcon = new ImageIcon("糖泡蓝.gif");
        now = ps;
    }

    public void setBall() {
        Ball ball = new Ball(2, getHeng(), getShu(), power, getBallIcon());
        Music.music[5].play();
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
            if (canspace() && USEtangpao == false && count < amount && Maps.ballMap[getHeng()][getShu()] == null)
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
*/
