package player;

import constants.GameConstants;
import element.Maps;
import element.MusicTool;

import java.util.Random;

import javax.swing.ImageIcon;


public class BiwuModePlayer extends Player {
    //private int playerNumber;
/*    //现在放下去的糖泡的数量
    protected static int count = 0;*/
    private Random random;
    public int deathFrequency = 0, rebornTime = 0, rebornMaxTime = 500;

    public BiwuModePlayer(int playerNumber, Maps maps) {
        super(playerNumber, maps);
        random = new Random();

        if (getPlayerNumber() == GameConstants.PLAYER1) {
            setJudgeXPosition(125);
            setJudgeYPosition(325);
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
            setJudgeXPosition(125);
            setJudgeYPosition(75);
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
    public void die() {
        deathFrequency += 1;
        outlooking = OUTLOOKING_LOSER;
        setNow();
        now = pdie;
        MusicTool.music[7].play();
    }

    public void keepDoing(Player anotherPlayer) {
        move();
        dieIfPossible(anotherPlayer);
        eatDaoju();
        beBombed();
        beBack();
        reborn();
    }

    private void reborn() {
        if (outlooking == OUTLOOKING_LOSER) {
            rebornTime += 1;
            //System.out.println(rebornTime);
            if (rebornTime == rebornMaxTime) {
                outlooking = OUTLOOKING_ORIGIN;
                setJudgeXPosition(random.nextInt(500) + 50);
                setJudgeYPosition(random.nextInt(300) + 50);
                while (getMaps().isWall(getHeng(), getShu())) {
                    setJudgeXPosition(random.nextInt(500) + 50);
                    setJudgeYPosition(random.nextInt(300) + 50);
                }
                rebornTime = 0;
                flashTime = 1;
            }
        }
    }

    public void dieIfPossible(Player anotherPlayer) {
        if (flashTime > 0) flashTime += 1;
        if (flashTime == Player.FLASH_TIME) flashTime = 0;
        if (outlooking == OUTLOOKING_STUCK) {
            stuckTime += 1;
            if (stuckTime == Player.BEFORE_DIE_TIME) {
                die();
            } else if (anotherPlayer.outlooking != OUTLOOKING_STUCK
                    && getHeng() == anotherPlayer.getHeng()
                    && getShu() == anotherPlayer.getShu()) {
                die();
                RIGHT = false;
                LEFT = false;
                UP = false;
                DOWN = false;
            }
        }
    }


}