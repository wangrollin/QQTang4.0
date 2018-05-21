package player;

import constants.GameConstants;
import element.Maps;
import element.MusicTool;

import javax.swing.ImageIcon;


public class AIModePlayer extends Player {

    protected int nspeed = 4;
    protected int speed = 4;

    public AIModePlayer(Maps maps) {
        super(GameConstants.PLAYER1, maps);
        judgeXPosition = 125;
        judgeYPosition = 75;

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
        if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT
                && RIGHT != true && LEFT != true && DOWN != true && UP != true) {
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
        if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT) {
            if (outlooking != OUTLOOKING_GHOST) speed = nspeed;
            if (RIGHT == true && RightInterruptCount == 0) {
                now = d;
                if (canGoRight()) judgeXPosition += speed;
                return;
            }
            if (LEFT == true && LeftInterruptCount == 0) {
                now = a;
                if (cangGoLeft()) judgeXPosition -= speed;
                return;
            }
            if (DOWN == true && DownInterruptCount == 0) {
                now = s;
                if (canGoDown()) judgeYPosition += speed;
                return;
            }
            if (UP == true && UpInterruptCount == 0) {
                now = w;
                if (canGoUp()) judgeYPosition -= speed;
                return;
            }
        }
        if (outlooking == OUTLOOKING_GHOST) {
            if (LEFT == true && RightInterruptCount == 0) {
                if (canGoRight()) judgeXPosition += speed;
                now = d;
                return;
            }
            if (RIGHT == true && LeftInterruptCount == 0) {
                if (cangGoLeft()) judgeXPosition -= speed;
                now = a;
                return;
            }
            if (UP == true && DownInterruptCount == 0) {
                if (canGoDown()) judgeYPosition += speed;
                now = s;
                return;
            }
            if (DOWN == true && UpInterruptCount == 0) {
                if (canGoUp()) judgeYPosition -= speed;
                now = w;
                return;
            }
        }
    }

    public void die() {
        outlooking = OUTLOOKING_LOSER;
        setNow();
        now = pdie;
        MusicTool.stop();
        MusicTool.music[7].play();
        MusicTool.music[13].loop();
    }

    public boolean toDie() {
        if (flashTime > 0) flashTime += 1;
        if (flashTime == Player.FLASH_TIME) flashTime = 0;
        if (outlooking == OUTLOOKING_STUCK) {
            stuckTime += 1;
            if (stuckTime == Player.BEFORE_DIE_TIME) {
                die();
                return true;
            }
        }
        return false;
    }




}
