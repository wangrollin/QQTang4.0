package panel.battle;

import constants.GameConstants;
import element.Maps;
import element.MusicTool;
import element.Wall;
import panel.MyPanelCard;
import player.JingjiModePlayer;
import player.Player;

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
    //闪出来的长度
    public final static int jiangeheng = 0, jiangeshu = 0;
    //时间监听毫秒
    private final int DELAY = 15;
    //时间老人
    Timer timer;
    //地图
    //static element.Maps maps;
    private Maps maps = new Maps();

    public void setWallMap(Wall[][] wallMap) {
        this.maps.setWallMap(wallMap);
    }
    //人物登场
    private JingjiModePlayer p1 = new JingjiModePlayer(GameConstants.PLAYER1, maps);
    private JingjiModePlayer p2 = new JingjiModePlayer(GameConstants.PLAYER2, maps);
    //public static abandon.JingjiModePlayer2 p2;

    protected static int cishu = 0;

    public Player getPlayer1() {
        return p1;
    }

    public Player getPlayer2() {
        return p2;
    }
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

    //构造出来 初始化****************************************************************************************
    public BattleJingjiPanel(MyPanelCard myPanelCard, CardLayout cardLayout) {
        this.myPanelCard = myPanelCard;
        this.cardLayout = cardLayout;

        gobackBtn = new JButton(new ImageIcon("replay1.png"));
        gobackBtn.setBounds(10, 10, 128, 50);
        add(gobackBtn);

        exitBtn = new JButton(new ImageIcon("exit1.png"));
        exitBtn.setBounds(510, 10, 130, 50);
        add(exitBtn);
        //音乐准备
        MusicTool.Musicload();

        //maps = new element.Maps();
        //p1 = new player.JingjiModePlayer();
        //p2 = new abandon.JingjiModePlayer2();
        timer = new Timer(DELAY, new Mytime());
        //addKeyListener(Listener);
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        timer.start();

    }

    //画图啦**********************************************************************************************
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        maps.getBiwumenIcon().paintIcon(this, page, 0, 0);
        maps.getGroundIcon().paintIcon(this, page, 0, 200);

        p1.keepDoing(p2);
        p2.keepDoing(p1);

        if (cishu == 0) {
            if (p1.outlooking == 7) {
                MusicTool.stop();
                MusicTool.music[8].loop();
                cishu = 1;
                cardLayout.show(myPanelCard, "fighterWinPanel");
            }
            if (p2.outlooking == 7) {
                MusicTool.stop();
                cishu = 1;
                MusicTool.music[8].loop();
                cardLayout.show(myPanelCard, "baoziWinPanel");
            }
        }
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
                if (p1.getShu() == j && p2.getShu() == j && p1.getJudgeYPosition() < p2.getJudgeYPosition()) {
                    p1.currentPlayerIcon.paintIcon(this, page, p1.getx(), p1.gety() + 200);
                    p2.currentPlayerIcon.paintIcon(this, page, p2.getx(), p2.gety() + 200);
                }
            }
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
