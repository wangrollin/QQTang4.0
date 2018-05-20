package player;

import constants.GameConstants;
import element.Ball;
import element.Maps;

import javax.swing.ImageIcon;

public class JingjiModePlayer extends Player {
    //private int playerNumber;

/*    //现在放下去的糖泡的数量
    protected static int count = 0;*/

    public JingjiModePlayer(int playerNumber, Maps maps) {
        super(playerNumber, maps);
        //this.playerNumber = playerNumber;
        if (getPlayerNumber() == GameConstants.PLAYER1) {
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
            ballIcon = new ImageIcon("糖泡红.gif");
        } else {
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
        }

        now = ps;
    }

  /*  public void setBall() {
            Ball ball = new Ball(1, getHeng(), getShu(), power, getBallIcon());
            element.MusicTool.music[5].play();
    }*/

}
