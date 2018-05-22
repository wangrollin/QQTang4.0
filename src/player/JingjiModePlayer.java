package player;

import constants.GameConstants;
import element.Maps;

import javax.swing.ImageIcon;

public class JingjiModePlayer extends Player {
    //private int playerNumber;

/*    //现在放下去的糖泡的数量
    protected static int count = 0;*/

    public JingjiModePlayer(int playerNumber, Maps maps) {
        super(playerNumber, maps);

        if (getPlayerNumber() == GameConstants.PLAYER1) {
            judgeXPosition = 125;
            judgeYPosition = 325;
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
        } else {
            judgeXPosition = 125;
            judgeYPosition = 75;
            originGoDownIcon = new ImageIcon("包子下.gif");
            originGoUpIcon = new ImageIcon("包子上.gif");
            originGoLeftIcon = new ImageIcon("包子左.gif");
            orginGoRightIcon = new ImageIcon("包子右.gif");

            originGoDownFlashIcon = new ImageIcon("s包子下.gif");
            originGoUpFlashIcon = new ImageIcon("s包子上.gif");
            originGoLeftFlashIcon = new ImageIcon("s包子左.gif");
            orginGoRightFlashIcon = new ImageIcon("s包子右.gif");

            stuckIcon = new ImageIcon("炸弹水.gif");
            deadIcon = new ImageIcon("P2die.gif");
            winningIcon = new ImageIcon("P2win.jpg");
            ballIcon = new ImageIcon("糖泡蓝.gif");
        }

        currentPlayerIcon = originGoDownIcon;
    }

}
