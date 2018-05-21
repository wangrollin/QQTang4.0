package panel.battle;

import constants.GameConstants;
import element.*;
import panel.MyPanelCard;
import player.BiwuModePlayer;
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


public class BattleBiwuPanel extends JPanel {
    private MyPanelCard myPanelCard;
    private CardLayout cardLayout;

    private JButton gobackBtn, exitBtn;
    //闪出来的长度
    //protected final static int jiangeheng = 0, jiangeshu = 0;
    //时间监听毫秒
    private final int DELAY = 15;
    //时间老人
    private Timer timer;
    //地图
    //static element.Maps maps;
    private Maps maps = new Maps();


    public void setGroundIconByType(int groundType) {
        this.maps.setGroundIconByType(groundType);
    }
    public Player getPlayer1() {
        return p1;
    }

    public Player getPlayer2() {
        return p2;
    }
    //人物登场
    private BiwuModePlayer p1;
    private BiwuModePlayer p2;
    //public static abandon.BiwuModePlayer2 p2;
    private ImageIcon num, num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;
    protected static boolean timeover = false;
    TimeCounter timeCounter;

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

    public void setWallMapAndWallMapType(Wall[][] wallMap, int wallMapType) {
        this.maps.setWallMap(wallMap, wallMapType);
    }

    public void closeGameAndJumpAway(String panelName, AudioClip bgmToPlay) {
        MusicTool.stopAllMusic();
        myPanelCard.removeKeyListener(p1);
        myPanelCard.removeKeyListener(p2);
        p1 = null;
        p2 = null;

        cardLayout.show(myPanelCard, panelName);
        bgmToPlay.play();
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
        p1 = new BiwuModePlayer(GameConstants.PLAYER1, maps, p2);
        p2 = new BiwuModePlayer(GameConstants.PLAYER2, maps, p1);
        initPlayerPosition();
        myPanelCard.addKeyListener(p1);
        myPanelCard.addKeyListener(p2);


        timeover = false; // TODO

        cardLayout.show(myPanelCard, "battleBiwuPanel");
        timeCounter = new TimeCounter();
        /**
         * play music, loop bgm
         */
        MusicTool.READ_GO.play();
        MusicTool.BIWU_MODE_BGM.loop();

    }

    //构造出来 初始化****************************************************************************************
    public BattleBiwuPanel(MyPanelCard myPanelCard, CardLayout cardLayout) {
        this.myPanelCard = myPanelCard;
        this.cardLayout = cardLayout;


        p1 = new BiwuModePlayer(GameConstants.PLAYER1, maps, p2);
        p2 = new BiwuModePlayer(GameConstants.PLAYER2, maps, p1);

        gobackBtn = new JButton(new ImageIcon("replay1.png"));
        gobackBtn.setBounds(10, 10, 128, 50);
        add(gobackBtn);

        exitBtn = new JButton(new ImageIcon("exit1.png"));
        exitBtn.setBounds(510, 10, 130, 50);
        add(exitBtn);
        num = new ImageIcon("：.png");
        num1 = new ImageIcon("1111.png");
        num2 = new ImageIcon("2.png");
        num3 = new ImageIcon("3333.png");
        num4 = new ImageIcon("4.png");
        num5 = new ImageIcon("5.png");
        num6 = new ImageIcon("6.png");
        num7 = new ImageIcon("7.png");
        num8 = new ImageIcon("8.png");
        num9 = new ImageIcon("9.png");
        num0 = new ImageIcon("0.png");
        //MusicTool.Musicload();
        //maps = new element.Maps();
        timeCounter = new TimeCounter();
        //p1 = new player.BiwuModePlayer();
        //p2 = new abandon.BiwuModePlayer2();
        timer = new Timer(DELAY, new Mytime());

        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        timer.start();
    }

    //画图啦**********************************************************************************************
    public void paintComponent(Graphics page) {
        if (timeCounter.over) {
            if (p1.deathFrequency < p2.deathFrequency) {
                closeGameAndJumpAway("fighterWinPanel", MusicTool.WINNING_BGM);
            } else if (p1.deathFrequency > p2.deathFrequency) {
                closeGameAndJumpAway("baoziWinPanel", MusicTool.WINNING_BGM);
            } else if (p1.deathFrequency == p2.deathFrequency) {
                closeGameAndJumpAway("dagfallPanel", MusicTool.WINNING_BGM);
            }
        }

        super.paintComponent(page);
        maps.getBiwumenIcon().paintIcon(this, page, 0, 0);
        maps.getGroundIcon().paintIcon(this, page, 0, 200);
        //不断刷新的
        p1.keepDoing();
        p2.keepDoing();

        paintTime(page);

        paintScore(page);

        timeCounter.count();



        //绘图采用一行一行扫的形式              墙   人  糖浆   糖泡 道具
        for (int j = 0; j < GameConstants.SHU; j++)
            for (int i = 0; i < GameConstants.HENG; i++) {
                if (maps.isBall(i, j)) {
                    maps.getBall(i, j).getBallIcon().paintIcon(this, page, i * 50, j * 50 + 200);
                    maps.getBall(i, j).addTime();
                }

                if (maps.isWall(i, j) && (!maps.getWall(i, j).isRuined())) {
                    maps.getWall(i, j).getWallIcon().paintIcon(this, page, i * 50, j * 50 - 12 + 200);
                }

                if (maps.isItem(i, j)) {
                    maps.getItem(i, j).getItemIcon().paintIcon(this, page, i * 50, j * 50 + 200);
                }

                if (maps.isExplosion(i, j)) {
                    maps.getExplosion(i, j).getImage().paintIcon(this, page, i * 50, j * 50 + 200);
                    maps.getExplosion(i, j).addTime();
                }

                if (p1.getHeng() == i && p1.getShu() == j && p2.getShu() != j)
                    p1.currentPlayerIcon.paintIcon(this, page, p1.getx(), p1.gety() + 200);
                if (p2.getHeng() == i && p2.getShu() == j && p1.getShu() != j)
                    p2.currentPlayerIcon.paintIcon(this, page, p2.getx(), p2.gety() + 200);
                if (p2.getJudgeXPosition() == i && p2.getJudgeYPosition() == j)
                    p2.currentPlayerIcon.paintIcon(this, page, p2.getx(), p2.gety() + 200);
                if (p1.getShu() == j && p2.getShu() == j && p1.getJudgeYPosition() > p2.getJudgeYPosition()) {
                    p2.currentPlayerIcon.paintIcon(this, page, p2.getx(), p2.gety() + 200);
                    p1.currentPlayerIcon.paintIcon(this, page, p1.getx(), p1.gety() + 200);
                }
                if (p1.getShu() == j && p2.getShu() == j && p1.getJudgeYPosition() <= p2.getJudgeYPosition()) {
                    p1.currentPlayerIcon.paintIcon(this, page, p1.getx(), p1.gety() + 200);
                    p2.currentPlayerIcon.paintIcon(this, page, p2.getx(), p2.gety() + 200);
                }
            }

    }

    private void paintTime(Graphics page) {
        if (timeCounter.minute == 3) num3.paintIcon(this, page, 240, 5);
        if (timeCounter.minute == 2) num2.paintIcon(this, page, 240, 5);
        if (timeCounter.minute == 1) num1.paintIcon(this, page, 240, 5);
        if (timeCounter.minute == 0) num0.paintIcon(this, page, 240, 5);

        num.paintIcon(this, page, 275, 20);

        if (timeCounter.scond / 10 == 0) num0.paintIcon(this, page, 335, 5);
        if (timeCounter.scond / 10 == 1) num1.paintIcon(this, page, 335, 5);
        if (timeCounter.scond / 10 == 2) num2.paintIcon(this, page, 335, 5);
        if (timeCounter.scond / 10 == 3) num3.paintIcon(this, page, 335, 5);
        if (timeCounter.scond / 10 == 4) num4.paintIcon(this, page, 335, 5);
        if (timeCounter.scond / 10 == 5) num5.paintIcon(this, page, 335, 5);
        if (timeCounter.scond / 10 == 6) num6.paintIcon(this, page, 335, 5);
        if (timeCounter.scond / 10 == 7) num7.paintIcon(this, page, 335, 5);
        if (timeCounter.scond / 10 == 8) num8.paintIcon(this, page, 335, 5);
        if (timeCounter.scond / 10 == 9) num9.paintIcon(this, page, 335, 5);

        if (timeCounter.scond % 10 == 0) num0.paintIcon(this, page, 410, 5);
        if (timeCounter.scond % 10 == 1) num1.paintIcon(this, page, 410, 5);
        if (timeCounter.scond % 10 == 2) num2.paintIcon(this, page, 410, 5);
        if (timeCounter.scond % 10 == 3) num3.paintIcon(this, page, 410, 5);
        if (timeCounter.scond % 10 == 4) num4.paintIcon(this, page, 410, 5);
        if (timeCounter.scond % 10 == 5) num5.paintIcon(this, page, 410, 5);
        if (timeCounter.scond % 10 == 6) num6.paintIcon(this, page, 410, 5);
        if (timeCounter.scond % 10 == 7) num7.paintIcon(this, page, 410, 5);
        if (timeCounter.scond % 10 == 8) num8.paintIcon(this, page, 410, 5);
        if (timeCounter.scond % 10 == 9) num9.paintIcon(this, page, 410, 5);
    }

    private void paintScore(Graphics page) {
        if (p1.deathFrequency / 10 == 0) num0.paintIcon(this, page, 505, 100);
        if (p1.deathFrequency / 10 == 1) num1.paintIcon(this, page, 505, 100);
        if (p1.deathFrequency / 10 == 2) num2.paintIcon(this, page, 505, 100);
        if (p1.deathFrequency / 10 == 3) num3.paintIcon(this, page, 505, 100);
        if (p1.deathFrequency / 10 == 4) num4.paintIcon(this, page, 505, 100);
        if (p1.deathFrequency / 10 == 5) num5.paintIcon(this, page, 505, 100);
        if (p1.deathFrequency / 10 == 6) num6.paintIcon(this, page, 505, 100);
        if (p1.deathFrequency / 10 == 7) num7.paintIcon(this, page, 505, 100);
        if (p1.deathFrequency / 10 == 8) num8.paintIcon(this, page, 505, 100);
        if (p1.deathFrequency / 10 == 9) num9.paintIcon(this, page, 505, 100);

        if (p1.deathFrequency % 10 == 0) num0.paintIcon(this, page, 580, 100);
        if (p1.deathFrequency % 10 == 1) num1.paintIcon(this, page, 580, 100);
        if (p1.deathFrequency % 10 == 2) num2.paintIcon(this, page, 580, 100);
        if (p1.deathFrequency % 10 == 3) num3.paintIcon(this, page, 580, 100);
        if (p1.deathFrequency % 10 == 4) num4.paintIcon(this, page, 580, 100);
        if (p1.deathFrequency % 10 == 5) num5.paintIcon(this, page, 580, 100);
        if (p1.deathFrequency % 10 == 6) num6.paintIcon(this, page, 580, 100);
        if (p1.deathFrequency % 10 == 7) num7.paintIcon(this, page, 580, 100);
        if (p1.deathFrequency % 10 == 8) num8.paintIcon(this, page, 580, 100);
        if (p1.deathFrequency % 10 == 9) num9.paintIcon(this, page, 580, 100);

        if (p2.deathFrequency / 10 == 0) num0.paintIcon(this, page, 0, 100);
        if (p2.deathFrequency / 10 == 1) num1.paintIcon(this, page, 0, 100);
        if (p2.deathFrequency / 10 == 2) num2.paintIcon(this, page, 0, 100);
        if (p2.deathFrequency / 10 == 3) num3.paintIcon(this, page, 0, 100);
        if (p2.deathFrequency / 10 == 4) num4.paintIcon(this, page, 0, 100);
        if (p2.deathFrequency / 10 == 5) num5.paintIcon(this, page, 0, 100);
        if (p2.deathFrequency / 10 == 6) num6.paintIcon(this, page, 0, 100);
        if (p2.deathFrequency / 10 == 7) num7.paintIcon(this, page, 0, 100);
        if (p2.deathFrequency / 10 == 8) num8.paintIcon(this, page, 0, 100);
        if (p2.deathFrequency / 10 == 9) num9.paintIcon(this, page, 0, 100);

        if (p2.deathFrequency % 10 == 0) num0.paintIcon(this, page, 75, 100);
        if (p2.deathFrequency % 10 == 1) num1.paintIcon(this, page, 75, 100);
        if (p2.deathFrequency % 10 == 2) num2.paintIcon(this, page, 75, 100);
        if (p2.deathFrequency % 10 == 3) num3.paintIcon(this, page, 75, 100);
        if (p2.deathFrequency % 10 == 4) num4.paintIcon(this, page, 75, 100);
        if (p2.deathFrequency % 10 == 5) num5.paintIcon(this, page, 75, 100);
        if (p2.deathFrequency % 10 == 6) num6.paintIcon(this, page, 75, 100);
        if (p2.deathFrequency % 10 == 7) num7.paintIcon(this, page, 75, 100);
        if (p2.deathFrequency % 10 == 8) num8.paintIcon(this, page, 75, 100);
        if (p2.deathFrequency % 10 == 9) num9.paintIcon(this, page, 75, 100);
    }

    //时间监听*******************************************************************************************
    public class Mytime implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }

    public JButton getGobackBtn() {
        return gobackBtn;
    }

    public JButton getExitBtn() {
        return exitBtn;
    }
}
