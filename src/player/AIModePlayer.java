package player;

import constants.GameConstants;
import element.Maps;
import element.MusicTool;

import javax.swing.ImageIcon;


public class AIModePlayer extends Player {

    protected int nspeed = 4;
    protected int speed = 4;

    public AIModePlayer(Maps maps, Player anotherPlayer) {
        super(GameConstants.PLAYER1, maps, anotherPlayer);
        judgeXPosition = 125;
        judgeYPosition = 75;

        originGoDownIcon = new ImageIcon("战士下.gif");
        originGoUpIcon = new ImageIcon("战士上.gif");
        originGoLeftIcon = new ImageIcon("战士左.gif");
        orginGoRightIcon = new ImageIcon("战士右.gif");

        originGoDownFlashIcon = new ImageIcon("s战士下.gif");
        originGoUpFlashIcon = new ImageIcon("s战士上.gif");
        originGoLeftFlashIcon = new ImageIcon("s战士左.gif");
        orginGoRightFlashIcon = new ImageIcon("s战士右.gif");

        stuckIcon = new ImageIcon("炸弹火.gif");
        deadIcon = new ImageIcon("P1die.gif");
        winningIcon = new ImageIcon("P1win.gif");
        ballIcon = new ImageIcon("糖泡红.gif");
        currentPlayerIcon = originGoDownIcon;
    }

    public void move() {
        if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT
                && RIGHT != true && LEFT != true && DOWN != true && UP != true) {
            if (currentPlayerIcon == currentGoDownIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoDownIcon;
            }
            if (currentPlayerIcon == currentGoUpIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoUpIcon;
            }
            if (currentPlayerIcon == currentGoLeftIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoLeftIcon;
            }
            if (currentPlayerIcon == currentGoRightIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoRightIcon;
            }
        }
        setIconsByOutlooking();
        if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT) {
            if (outlooking != OUTLOOKING_GHOST) speed = nspeed;
            if (RIGHT == true && RightInterruptCount == 0) {
                currentPlayerIcon = currentGoRightIcon;
                if (canGoRight()) judgeXPosition += speed;
                return;
            }
            if (LEFT == true && LeftInterruptCount == 0) {
                currentPlayerIcon = currentGoLeftIcon;
                if (canGoLeft()) judgeXPosition -= speed;
                return;
            }
            if (DOWN == true && DownInterruptCount == 0) {
                currentPlayerIcon = currentGoDownIcon;
                if (canGoDown()) judgeYPosition += speed;
                return;
            }
            if (UP == true && UpInterruptCount == 0) {
                currentPlayerIcon = currentGoUpIcon;
                if (canGoUp()) judgeYPosition -= speed;
                return;
            }
        }
        if (outlooking == OUTLOOKING_GHOST) {
            if (LEFT == true && RightInterruptCount == 0) {
                if (canGoRight()) judgeXPosition += speed;
                currentPlayerIcon = currentGoRightIcon;
                return;
            }
            if (RIGHT == true && LeftInterruptCount == 0) {
                if (canGoLeft()) judgeXPosition -= speed;
                currentPlayerIcon = currentGoLeftIcon;
                return;
            }
            if (UP == true && DownInterruptCount == 0) {
                if (canGoDown()) judgeYPosition += speed;
                currentPlayerIcon = currentGoDownIcon;
                return;
            }
            if (DOWN == true && UpInterruptCount == 0) {
                if (canGoUp()) judgeYPosition -= speed;
                currentPlayerIcon = currentGoUpIcon;
                return;
            }
        }
    }

    public void die() {
        outlooking = OUTLOOKING_LOSER;
        setIconsByOutlooking();
        currentPlayerIcon = deadIcon;
        MusicTool.PLAYER_EXPLODE.play();
    }

    public boolean toDie() {
        if (flashTime > 0) flashTime += 1;
        if (flashTime == Player.FLASH_MAX_TIME) flashTime = 0;
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
