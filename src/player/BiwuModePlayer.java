package player;

import constants.GameConstants;
import element.Maps;
import element.MusicTool;

import java.util.Random;

import javax.swing.ImageIcon;


public class BiwuModePlayer extends Player {
    private Random random;
    public int deathFrequency = 0, rebornTime = 0, rebornMaxTime = 500;

    public BiwuModePlayer(int playerNumber, Maps maps) {
        super(playerNumber, maps);
        random = new Random();

        if (getPlayerNumber() == GameConstants.PLAYER1) {
            setJudgeXPosition(125);
            setJudgeYPosition(325);
            originGoDownIcon = new ImageIcon("战士下.gif");
            originGoUpIcon = new ImageIcon("战士上.gif");
            originGoLeftIcon = new ImageIcon("战士左.gif");
            orginGoRightIcon = new ImageIcon("战士右.gif");

            originGoDownFlashIcon = new ImageIcon("s战士下.gif");
            originGoUpFlashIcon = new ImageIcon("s战士上.gif");
            originGoLeftFlashIcon = new ImageIcon("s战士左.gif");
            orginGoRightFlashIcon = new ImageIcon("s战士右.gif");

            stuckIcon = new ImageIcon("炸弹火.gif");
            loseIcon = new ImageIcon("P1die.gif");
            winningIcon = new ImageIcon("P1win.gif");
            ballIcon = new ImageIcon("糖泡红.gif");
        } else {
            setJudgeXPosition(125);
            setJudgeYPosition(75);
            originGoDownIcon = new ImageIcon("包子下.gif");
            originGoUpIcon = new ImageIcon("包子上.gif");
            originGoLeftIcon = new ImageIcon("包子左.gif");
            orginGoRightIcon = new ImageIcon("包子右.gif");

            originGoDownFlashIcon = new ImageIcon("s包子下.gif");
            originGoUpFlashIcon = new ImageIcon("s包子上.gif");
            originGoLeftFlashIcon = new ImageIcon("s包子左.gif");
            orginGoRightFlashIcon = new ImageIcon("s包子右.gif");

            stuckIcon = new ImageIcon("炸弹水.gif");
            loseIcon = new ImageIcon("P2die.gif");
            winningIcon = new ImageIcon("P2win.jpg");
            ballIcon = new ImageIcon("糖泡蓝.gif");
        }

        currentPlayerIcon = originGoDownIcon;
    }
    public void die() {
        deathFrequency += 1;
        outlooking = OUTLOOKING_LOSER;
        setIconsByOutlooking();
        currentPlayerIcon = loseIcon;
        MusicTool.PLAYER_EXPLODE.play();
    }

    public void keepDoing() {
        move();
        countTimeToLoseIfPossible();
        pickupItem();
        beBombed();
        transformToOriginIfPossible();
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

    public void countTimeToLoseIfPossible() {
        if (flashTime > 0) flashTime += 1;
        if (flashTime == Player.FLASH_MAX_TIME) flashTime = 0;
        if (outlooking == OUTLOOKING_STUCK) {
            incStuckTime();
            if (getStuckTime() == Player.BEFORE_LOSE_TIME) {
                die();
            } else if (getAnotherPlayer().outlooking != OUTLOOKING_STUCK
                    && getHeng() == getAnotherPlayer().getHeng()
                    && getShu() == getAnotherPlayer().getShu()) {
                die();
                isRightPressed = false;
                isLeftPressed = false;
                isUpPressed = false;
                isDownPressed = false;
            }
        }
    }


}