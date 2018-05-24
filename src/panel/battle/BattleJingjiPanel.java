package panel.battle;

import constants.GameConstants;
import element.Maps;
import element.MusicTool;
import element.Wall;
import element.WallMapTool;
import panel.MyPanelCard;
import player.Player;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BattleJingjiPanel extends JPanel {
    private MyPanelCard myPanelCard;
    private CardLayout cardLayout;

    private JButton gobackBtn, exitBtn;

    //时间监听毫秒
    private static final int DELAY = 10;
    //时间老人
    Timer timer;

    private Maps maps = new Maps();

    public void setWallMapAndWallMapType(Wall[][] wallMap, int wallMapType) {
        this.maps.setWallMap(wallMap, wallMapType);
    }
    public void setGroundIconByType(int groundType) {
        this.maps.setGroundIconByType(groundType);
    }

    //人物登场
    private Player p1;
    private Player p2;

    //private static int cishu = 0;
    private static final int DELAY_TO_JUMP_MAX_TIME = 200;
    private int delayToJumpTime = 0;

    public void initPlayerPosition() {
        Random random = new Random();
        p1.setJudgeXPosition(random.nextInt(500) + 50);
        p1.setJudgeYPosition(random.nextInt(300) + 50);
        while (maps.isWall(p1.getHeng(), p1.getShu())) {
            p1.setJudgeXPosition(random.nextInt(500) + 50);
            p1.setJudgeYPosition(random.nextInt(300) + 50);
        }

        p2.setJudgeXPosition(random.nextInt(500) + 50);
        p2.setJudgeYPosition(random.nextInt(300) + 50);
        while (maps.isWall(p2.getHeng(), p2.getShu())) {
            p2.setJudgeXPosition(random.nextInt(500) + 50);
            p2.setJudgeYPosition(random.nextInt(300) + 50);
        }
    }

    public void closeGameAndJumpAway(String panelName, AudioClip bgmToPlay) {
        MusicTool.stopAllMusic();
        myPanelCard.removeKeyListener(p1);
        myPanelCard.removeKeyListener(p2);
        timer.stop();
        p1 = null;
        p2 = null;

        cardLayout.show(myPanelCard, panelName);
        bgmToPlay.loop();

    }

    public void initAndShowAndStartGame() {
        /**
         * init maps
         */
        maps = new Maps();
        setWallMapAndWallMapType(WallMapTool.copyWallMap(myPanelCard.getWallMap()), myPanelCard.getWallMapType());
        setGroundIconByType(myPanelCard.getGroundType());

        /**
         * init players
         */
        p2 = new Player(GameConstants.PLAYER2, maps);
        p1 = new Player(GameConstants.PLAYER1, maps);
        p1.setAnotherPlayer(p2);
        p2.setAnotherPlayer(p1);
        initPlayerPosition();
        myPanelCard.addKeyListener(p1);
        myPanelCard.addKeyListener(p2);


        //cishu = 0;
        delayToJumpTime = 0;
        timer.start();
        cardLayout.show(myPanelCard, "battleJingjiPanel");

        /**
         * play music, loop bgm
         */
        MusicTool.READ_GO.play();
        if (this.maps.getWallMapType() == GameConstants.BIWU_MAP) {
            MusicTool.BIWU_MAP_BGM.loop();
        } else if (this.maps.getWallMapType() == GameConstants.SHUIMIAN_MAP) {
            MusicTool.SHUIMIAN_MAP_BGM.loop();
        } else if (this.maps.getWallMapType() == GameConstants.KUANGDONG_MAP) {
            MusicTool.KUANGDONG_MAP_BGM.loop();
        } else if (this.maps.getWallMapType() == GameConstants.DIY_MAP) {
            MusicTool.DIY_MAP_BGM.loop();
        }

    }

    //构造出来 初始化****************************************************************************************
    public BattleJingjiPanel(MyPanelCard myPanelCard, CardLayout cardLayout) {
        this.myPanelCard = myPanelCard;
        this.cardLayout = cardLayout;


        p2 = new Player(GameConstants.PLAYER2, maps);
        p1 = new Player(GameConstants.PLAYER1, maps);
        p1.setAnotherPlayer(p2);
        p2.setAnotherPlayer(p1);

        gobackBtn = new JButton(new ImageIcon("replay1.png"));
        gobackBtn.setBounds(10, 10, 128, 50);
        add(gobackBtn);

        exitBtn = new JButton(new ImageIcon("exit1.png"));
        exitBtn.setBounds(510, 10, 130, 50);
        add(exitBtn);

        //timer = new Timer(DELAY, new Mytime());
        timer = new Timer(DELAY, actionListener);
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);

    }

    //画图啦**********************************************************************************************
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        maps.getBiwumenIcon().paintIcon(this, page, 0, 0);
        maps.getGroundIcon().paintIcon(this, page, 0, GameConstants.MAP_UPPER_PICTURE_HEIGHT);
        //绘图采用一行一行扫的形式              墙   人  糖浆   糖泡 道具
        for (int j = 0; j < GameConstants.SHU; j++)
            for (int i = 0; i < GameConstants.HENG; i++) {
                if (maps.isBall(i, j)) {
                    maps.getBall(i, j).getBallIcon().paintIcon(this, page, i * GameConstants.GRID_LENGTH, j * GameConstants.GRID_LENGTH + GameConstants.MAP_UPPER_PICTURE_HEIGHT);
                    //maps.getBall(i, j).addTime();
                }

                if (maps.isWall(i, j) && (!maps.getWall(i, j).isRuined())) {
                    maps.getWall(i, j).getWallIcon().paintIcon(this, page, i * GameConstants.GRID_LENGTH, j * GameConstants.GRID_LENGTH - 12 + GameConstants.MAP_UPPER_PICTURE_HEIGHT);
                }

                if (maps.isItem(i, j)) {
                    maps.getItem(i, j).getItemIcon().paintIcon(this, page, i * GameConstants.GRID_LENGTH, j * GameConstants.GRID_LENGTH + GameConstants.MAP_UPPER_PICTURE_HEIGHT);
                }

                if (maps.isExplosion(i, j)) {
                    maps.getExplosion(i, j).getImage().paintIcon(this, page, i * GameConstants.GRID_LENGTH, j * GameConstants.GRID_LENGTH + GameConstants.MAP_UPPER_PICTURE_HEIGHT);
                    //maps.getExplosion(i, j).addTime();
                }

                if (p1.getHeng() == i && p1.getShu() == j && p2.getShu() != j) {
                    p1.currentPlayerIcon.paintIcon(this, page, p1.getx(), p1.gety() + GameConstants.MAP_UPPER_PICTURE_HEIGHT);
                    continue;
                }
                if (p2.getHeng() == i && p2.getShu() == j && p1.getShu() != j) {
                    p2.currentPlayerIcon.paintIcon(this, page, p2.getx(), p2.gety() + GameConstants.MAP_UPPER_PICTURE_HEIGHT);
                    continue;
                }
                if (p1.getHeng() == i && p1.getShu() == j && p1.getJudgeYPosition() > p2.getJudgeYPosition()) {
                    p2.currentPlayerIcon.paintIcon(this, page, p2.getx(), p2.gety() + GameConstants.MAP_UPPER_PICTURE_HEIGHT);
                    p1.currentPlayerIcon.paintIcon(this, page, p1.getx(), p1.gety() + GameConstants.MAP_UPPER_PICTURE_HEIGHT);
                    continue;
                }
                if (p2.getHeng() == i && p2.getShu() == j && p1.getJudgeYPosition() <= p2.getJudgeYPosition()) {
                    p1.currentPlayerIcon.paintIcon(this, page, p1.getx(), p1.gety() + GameConstants.MAP_UPPER_PICTURE_HEIGHT);
                    p2.currentPlayerIcon.paintIcon(this, page, p2.getx(), p2.gety() + GameConstants.MAP_UPPER_PICTURE_HEIGHT);
                    continue;
                }

            }
    }
// todo reset position
    private void jumpAwayIfPossible() {
    /*if (cishu == 0) {
        if (p1.outlooking == Player.OUTLOOKING_WINNER) {
            cishu = 1;
            closeGameAndJumpAway("fighterWinPanel", MusicTool.WINNING_BGM);
        }
        if (p2.outlooking == Player.OUTLOOKING_WINNER) {
            cishu = 1;
            closeGameAndJumpAway("baoziWinPanel", MusicTool.WINNING_BGM);
        }
    }*/
        if (p1.outlooking == Player.OUTLOOKING_WINNER) {
            //cishu = 1;
            if(delayToJumpTime == 0) {
                MusicTool.stopAllMusic();
                MusicTool.WINNING_BGM.loop();
            }
            if (delayToJumpTime < DELAY_TO_JUMP_MAX_TIME) {
                ++delayToJumpTime;
            } else {
                closeGameAndJumpAway("fighterWinPanel", MusicTool.WINNING_BGM);
            }

        }
        if (p2.outlooking == Player.OUTLOOKING_WINNER) {
            //cishu = 1;
            if(delayToJumpTime == 0) {
                MusicTool.stopAllMusic();
                MusicTool.WINNING_BGM.loop();
            }
            if (delayToJumpTime < DELAY_TO_JUMP_MAX_TIME) {
                ++delayToJumpTime;
            } else {
                closeGameAndJumpAway("baoziWinPanel", MusicTool.WINNING_BGM);
            }
        }
    }

    private void playerDoing() {
        p1.keepDoing();
        p2.keepDoing();
    }


    //时间监听*******************************************************************************************
    /*public class Mytime implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            repaint();
            playerDoing();
            jumpAwayIfPossible();

            for (int j = 0; j < GameConstants.SHU; j++)
                for (int i = 0; i < GameConstants.HENG; i++) {
                    if (maps.isBall(i, j)) {
                        maps.getBall(i, j).addTime();
                    }
                    if (maps.isExplosion(i, j)) {
                        maps.getExplosion(i, j).addTime();
                    }
                }
        }
    }*/

    ActionListener actionListener = (ActionEvent e) -> {
        repaint();
        playerDoing();
        jumpAwayIfPossible();

        for (int j = 0; j < GameConstants.SHU; j++)
            for (int i = 0; i < GameConstants.HENG; i++) {
                if (maps.isBall(i, j)) {
                    maps.getBall(i, j).addTime();
                }
                if (maps.isExplosion(i, j)) {
                    maps.getExplosion(i, j).addTime();
                }
            }
    };


    public JButton getGobackBtn() {
        return gobackBtn;
    }

    public JButton getExitBtn() {
        return exitBtn;
    }
}
