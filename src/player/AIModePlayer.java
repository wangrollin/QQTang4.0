package player;

import constants.GameConstants;
import element.Ball;
import element.Maps;
import element.MusicTool;

import javax.swing.ImageIcon;


public class AIModePlayer extends Player {

/*    //现在放下去的糖泡的数量
    protected static int count = 0;*/
    //protected int namount=10,npower=6,nspeed=4;
    //protected int amount=10,power=6,speed=4;
    protected int namount = 10, npower = 5, nspeed = 4;
    protected int amount = 10, power = 5, speed = 4;

    public AIModePlayer(Maps maps) {
        super(GameConstants.PLAYER1, maps);
        X = 125;
        Y = 75;

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
        now = ps;
    }

    public void move() {
        if ((outlooking == 0 || outlooking == 1 || outlooking == 2 || outlooking == 3 || outlooking == 4) && RIGHT != true && LEFT != true && DOWN != true && UP != true) {
            if (now == s) {
                setNow();
                now = s;
            }
            if (now == w) {
                setNow();
                now = w;
            }
            if (now == a) {
                setNow();
                now = a;
            }
            if (now == d) {
                setNow();
                now = d;
            }
        }
        setNow();
        if (outlooking == 0 || outlooking == 1 || outlooking == 2 || outlooking == 4) {
            if (outlooking == 0 || outlooking == 2 || outlooking == 4) speed = nspeed;
            if (RIGHT == true && dRIGHT == 0) {
                now = d;
                if (cangod()) X += speed;
                return;
            }
            if (LEFT == true && dLEFT == 0) {
                now = a;
                if (cangoa()) X -= speed;
                return;
            }
            if (DOWN == true && dDOWN == 0) {
                now = s;
                if (cangos()) Y += speed;
                return;
            }
            if (UP == true && dUP == 0) {
                now = w;
                if (cangow()) Y -= speed;
                return;
            }
        }
        if (outlooking == 3) {
            if (LEFT == true && dRIGHT == 0) {
                if (cangod()) X += speed;
                now = d;
                return;
            }
            if (RIGHT == true && dLEFT == 0) {
                if (cangoa()) X -= speed;
                now = a;
                return;
            }
            if (UP == true && dDOWN == 0) {
                if (cangos()) Y += speed;
                now = s;
                return;
            }
            if (DOWN == true && dUP == 0) {
                if (cangow()) Y -= speed;
                now = w;
                return;
            }
        }
    }

    public void Die() {
        outlooking = 6;
        setNow();
        now = pdie;
        MusicTool.stop();
        MusicTool.music[7].play();
        MusicTool.music[13].loop();
    }

    public boolean toDie() {
        if (wuditime > 0) wuditime += 1;
        if (wuditime == Player.FLASH_TIME) wuditime = 0;
        if (outlooking == 5) {
            dietime += 1;
            if (dietime == Player.BEFORE_DIE_TIME) {
                Die();
                return true;
            }
        }
        return false;
    }




}
