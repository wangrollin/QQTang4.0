package panel.battle;

import constants.GameConstants;
import element.Maps;
import element.MusicTool;
import element.Wall;
import element.WallMapTool;
import panel.MyPanelCard;
import player.AI;
import player.AIModePlayer;
import player.JingjiModePlayer;
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



public class BattleAIPanel extends JPanel {
    private MyPanelCard myPanelCard;
    private CardLayout cardLayout;

    private JButton gobackBtn, exitBtn;
    //时间监听毫秒
    private static final int DELAY = 10;
    //时间老人
    private Timer timer;

    private Maps maps = new Maps();

    private static final int DELAY_TO_JUMP_MAX_TIME = 200;
    private int delayToJumpTime = 0;

    public void setGroundIconByType(int groundType) {
        this.maps.setGroundIconByType(groundType);
    }
    //人物登场
    private AIModePlayer p1;
    private AI p2;
    //public Player getPlayer1() {
    //    return p1;
    //}
    private Random aa;
    protected int safe = -1, timecount = 0, jineng = 0, suiji, suiji2, wushi = 0, weizhi;

    public void initPlayerPosition() {
        p1.setJudgeXPosition(75);
        p1.setJudgeYPosition(25);
        Random random = new Random();
        p2.setJudgeXPosition(random.nextInt(500) + 50);
        p2.setJudgeYPosition(random.nextInt(300) + 50);
        while (maps.isWall(p2.getHeng(), p2.getShu()) ||
                (p2.getHeng() == p1.getHeng() &&
                        p2.getShu() == p1.getShu())) {
            p2.setJudgeXPosition(random.nextInt(500) + 50);
            p2.setJudgeYPosition(random.nextInt(300) + 50);
        }
    }

    public BattleAIPanel(MyPanelCard myPanelCard, CardLayout cardLayout) {
        this.myPanelCard = myPanelCard;
        this.cardLayout = cardLayout;

        timecount = 0;
        aa = new Random();
        gobackBtn = new JButton(new ImageIcon("replay1.png"));
        gobackBtn.setBounds(10, 10, 128, 50);
        add(gobackBtn);

        exitBtn = new JButton(new ImageIcon("exit1.png"));
        exitBtn.setBounds(510, 10, 130, 50);
        add(exitBtn);

        p1 = new AIModePlayer(maps);
        p2 = new AI(maps);
        p1.setAnotherPlayer(p2);
        p2.setAnotherPlayer(p1);

        timer = new Timer(DELAY, new Mytime());
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
    }
    public void setWallMapAndWallMapType(Wall[][] wallMap, int wallMapType) {
        this.maps.setWallMap(wallMap, wallMapType);
    }
    public void closeGameAndJumpAway(String panelName, AudioClip bgmToPlay) {
        MusicTool.stopAllMusic();
        myPanelCard.removeKeyListener(p1);
        p1 = null;
        p2 = null;

        cardLayout.show(myPanelCard, panelName);
        bgmToPlay.loop();
        timer.stop();
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
        p1 = new AIModePlayer(maps);
        p2 = new AI(maps);
        p1.setAnotherPlayer(p2);
        p2.setAnotherPlayer(p1);
        initPlayerPosition();
        myPanelCard.addKeyListener(p1);


        safe = -1;
        timecount = 0;
        jineng = 0;
        wushi = 0;
        weizhi = p2.getPlayerQuadrant();
        delayToJumpTime = 0;

        timer.start();
        cardLayout.show(myPanelCard, "battleAIPanel");

        /**
         * play music, loop bgm
         */
        MusicTool.READ_GO.play();
        MusicTool.AI_MODE_BGM.loop();

    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        maps.getBiwumenIcon().paintIcon(this, page, 0, 0);
        maps.getGroundIcon().paintIcon(this, page, 0, 200);

        //绘图采用一行一行扫的形式              墙   人  糖浆   糖泡 道具
        for (int j = 0; j < GameConstants.SHU; j++)
            for (int i = 0; i < GameConstants.HENG; i++) {
                if (maps.isWall(i, j) && !maps.getWall(i, j).isRuined()) {
                    maps.getWall(i, j).getWallIcon().paintIcon(this, page, i * 50, j * 50 - 12 + 200);
                }

                if (maps.isItem(i, j)) {
                    maps.getItem(i, j).getItemIcon().paintIcon(this, page, i * 50, j * 50 + 200);
                }

                if (maps.isBall(i, j)) {
                    maps.getBall(i, j).getBallIcon().paintIcon(this, page, i * 50, j * 50 + 200);
                    maps.getBall(i, j).addTime();
                }

                if (maps.isExplosion(i, j)) {
                    maps.getExplosion(i, j).getImage().paintIcon(this, page, i * 50, j * 50 + 200);
                    maps.getExplosion(i, j).addTime();
                }

                if (p1.getHeng() == i && p1.getShu() == j)
                    p1.currentPlayerIcon.paintIcon(this, page, p1.getx(), p1.gety() + 200);

                if (p2.getHeng() == i && p2.getShu() == j)
                    p2.currentPlayerIcon.paintIcon(this, page, p2.getx(), p2.gety() + 200);
            }


    }

    private void aiDoing() {
        if (safe == 0) {
            //攻击
            //未释放
            if (wushi == 0) {
                suiji = aa.nextInt(100);
                suiji2 = aa.nextInt(100);
                weizhi = p2.getPlayerQuadrant();
                wushi = 1;

                if (weizhi == 0) {
                    if (p2.danhuaSafe()) jineng = 3;
                    else if (suiji > 50) jineng = 1;
                    else jineng = 2;
                }
                if (weizhi == 1) {
                    if (suiji > 50) jineng = 1;
                    else jineng = 2;
                }
                if (weizhi == 2) {
                    if (suiji > 50) jineng = 1;
                    else jineng = 2;
                }
                if (weizhi == 3) {
                    if (suiji > 50) jineng = 1;
                    else jineng = 2;
                }
                if (weizhi == 4) {
                    if (suiji > 50) jineng = 1;
                    else jineng = 2;
                }

            }
            //正在释放技能中
            else {
                if (jineng == 1) {
                    if (weizhi == 0 || weizhi == 1) {
                        if (p2.attackUpRight(timecount) == -1) {
                            safe = p2.safecheck();
                            timecount = 0;
                            weizhi = -1;
                            wushi = 0;
                            jineng = 0;
                        } else ++timecount;
                    } else if (weizhi == 2) {
                        if (p2.attackUpLeft(timecount) == -1) {
                            safe = p2.safecheck();
                            timecount = 0;
                            weizhi = -1;
                            wushi = 0;
                            jineng = 0;
                        } else ++timecount;
                    } else if (weizhi == 3) {
                        if (p2.attackDownLeft(timecount) == -1) {
                            safe = p2.safecheck();
                            timecount = 0;
                            weizhi = -1;
                            wushi = 0;
                            jineng = 0;
                        } else ++timecount;
                    } else if (weizhi == 4) {
                        if (p2.attackDownRight(timecount) == -1) {
                            safe = p2.safecheck();
                            timecount = 0;
                            weizhi = -1;
                            wushi = 0;
                            jineng = 0;
                        } else ++timecount;
                    }
                } else if (jineng == 2) {
                    if (weizhi == 0 || weizhi == 1) {
                        if (suiji2 > 50) {
                            if (p2.attackUp(timecount) == -1) {
                                safe = p2.safecheck();
                                timecount = 0;
                                weizhi = -1;
                                wushi = 0;
                                jineng = 0;
                            } else ++timecount;
                        } else {
                            if (p2.attackRight(timecount) == -1) {
                                safe = p2.safecheck();
                                timecount = 0;
                                weizhi = -1;
                                wushi = 0;
                                jineng = 0;
                            } else ++timecount;
                        }
                    } else if (weizhi == 2) {
                        if (suiji2 > 50) {
                            if (p2.attackUp(timecount) == -1) {
                                safe = p2.safecheck();
                                timecount = 0;
                                weizhi = -1;
                                wushi = 0;
                                jineng = 0;
                            } else ++timecount;
                        } else {
                            if (p2.attackLeft(timecount) == -1) {
                                safe = p2.safecheck();
                                timecount = 0;
                                weizhi = -1;
                                wushi = 0;
                                jineng = 0;
                            } else ++timecount;
                        }
                    } else if (weizhi == 3) {
                        if (suiji2 > 50) {
                            if (p2.attackLeft(timecount) == -1) {
                                safe = p2.safecheck();
                                timecount = 0;
                                weizhi = -1;
                                wushi = 0;
                                jineng = 0;
                            } else ++timecount;
                        } else {
                            if (p2.attackDown(timecount) == -1) {
                                safe = p2.safecheck();
                                timecount = 0;
                                weizhi = -1;
                                wushi = 0;
                                jineng = 0;
                            } else ++timecount;
                        }
                    } else if (weizhi == 4) {
                        if (suiji2 > 50) {
                            if (p2.attackDown(timecount) == -1) {
                                safe = p2.safecheck();
                                timecount = 0;
                                weizhi = -1;
                                wushi = 0;
                                jineng = 0;
                            } else ++timecount;
                        } else {
                            if (p2.attackRight(timecount) == -1) {
                                safe = p2.safecheck();
                                timecount = 0;
                                weizhi = -1;
                                wushi = 0;
                                jineng = 0;
                            } else ++timecount;
                        }
                    }
                } else if (jineng == 3) {
                    if (p2.danhua(timecount) == -1) {
                        safe = p2.safecheck();
                        timecount = 0;
                        weizhi = -1;
                        wushi = 0;
                        jineng = 0;
                    } else ++timecount;
                }

            }
        } else if (safe == -1) {
            //逃跑
            suiji = aa.nextInt(1000);
            if (suiji > 990) p2.scatterItemFox();
            if (p2.findway() == 1) p2.goUp();
            else if (p2.findway() == 2) p2.goLeft();
            else if (p2.findway() == 3) p2.goDown();
            else if (p2.findway() == 4) p2.goRight();

            else if (p2.findway() == 0) {
                safe = p2.safecheck();
            } else if (p2.findway() == -2) {
            }

        } else if (safe == 1) {
            //safe==1   啥都不做
            safe = p2.safecheck();
            if (p2.isNearWithHumanPlayer()) safe = 5;
        } else {
            //safe==5 躲开人喽
            suiji = aa.nextInt(10000);
            if (suiji > 9990) p2.scatterItemFox();
            if (p2.getaway() == 0) {
            } else if (p2.getaway() == 1) {
                p2.goUp();
            } else if (p2.getaway() == 2) {
                p2.goLeft();
            } else if (p2.getaway() == 3) {
                p2.goDown();
            } else if (p2.getaway() == 4) {
                p2.goRight();
            }
            safe = p2.safecheck();
        }
    }

    private void playerDoing() {
        //不断刷新的
        p1.move();

        p1.pickupItem();
        p1.beBombed();
        p1.transformToOrigin();

        p2.doing();
    }

    private void jumpAwayIfPossible() {
        // TODO ai一旦被炸到,就dieIcon, human变成ideIcon才是, 谁先谁输
        if (p2.isBeBombed()) {
            if(delayToJumpTime == 0) {
                MusicTool.stopAllMusic();
                MusicTool.WINNING_BGM.loop();
            }
            if (delayToJumpTime < DELAY_TO_JUMP_MAX_TIME) {
                ++delayToJumpTime;
            } else {
                closeGameAndJumpAway("fighterWinPanel", MusicTool.WINNING_BGM);
            }
        } else if (p1.toDie()) {
            if(delayToJumpTime == 0) {
                MusicTool.stopAllMusic();
                MusicTool.WINNING_BGM.loop();
            }
            if (delayToJumpTime < DELAY_TO_JUMP_MAX_TIME) {
                ++delayToJumpTime;
            } else {
                closeGameAndJumpAway("fighterLosePanel", MusicTool.LOSING_BGM);
            }
        }
    }

    //时间监听*******************************************************************************************
    public class Mytime implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            repaint();
            jumpAwayIfPossible();
            playerDoing();
            aiDoing();
        }
    }

    public JButton getGobackBtn() {
        return gobackBtn;
    }

    public JButton getExitBtn() {
        return exitBtn;
    }
}
