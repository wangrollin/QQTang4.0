package panel.battle;

import constants.GameConstants;
import element.Maps;
import element.MusicTool;
import element.Wall;
import panel.MyPanelCard;
import player.AI;
import player.AIModePlayer;
import player.Player;

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
    private final int DELAY = 15;
    //时间老人
    Timer timer;
    //地图
    //private element.Maps maps;
    private Maps maps = new Maps();

    public void setWallMap(Wall[][] wallMap) {
        this.maps.setWallMap(wallMap);
    }
    public void setGroundType(int groundType) {
        this.maps.setGroundType(groundType);
    }
    //人物登场
    public static AIModePlayer p1;
    public static AI p6;
    public Player getPlayer1() {
        return p1;
    }
    private Random aa;
    protected int safe = -1, timecount = 0, jineng = 0, suiji, suiji2, wushi = 0, weizhi;

    public void initPlayerPosition() {
        p1.setX(75);
        p1.setY(25);
        Random random = new Random();
        p6.setX(random.nextInt(500) + 50);
        p6.setY(random.nextInt(300) + 50);
        while (maps.isWall(p6.getHeng(), p6.getShu()) ||
                (p6.getHeng() == p1.getHeng() &&
                        p6.getShu() == p1.getShu())) {
            p6.setX(random.nextInt(500) + 50);
            p6.setY(random.nextInt(300) + 50);
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
        MusicTool.Musicload();

        //maps = new element.Maps();
        p1 = new AIModePlayer(maps);
        p6 = new AI(maps);
        timer = new Timer(DELAY, new Mytime());
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        timer.start();
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);

        if (p6.beexp() == true) {
            MusicTool.stop();
            MusicTool.music[8].loop();
            //panel.MyPanelCard.cardLayout.show(panel.Play.panel, "fighterWinPanel");
            cardLayout.show(myPanelCard, "fighterWinPanel");
        }


        maps.getBiwumenIcon().paintIcon(this, page, 0, 0);
        maps.getGroundIcon().paintIcon(this, page, 0, 200);
        //不断刷新的
        p1.move();
        if (p1.toDie()) {
            //panel.MyPanelCard.cardLayout.show(panel.Play.panel, "fighterLosePanel");
            cardLayout.show(myPanelCard, "fighterLosePanel");
        }
        p1.eatdaoju();
        p1.beExp();
        p1.beBack();

        p6.doing();

        if (safe == 0) {
            //攻击
            //未释放
            if (wushi == 0) {
                suiji = aa.nextInt(100);
                suiji2 = aa.nextInt(100);
                weizhi = p6.where();
                wushi = 1;

                if (weizhi == 0) {
                    if (p6.danhuaSafe()) jineng = 3;
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
                        if (p6.xiepao1(timecount) == -1) {
                            safe = p6.safecheck();
                            timecount = 0;
                            weizhi = -1;
                            wushi = 0;
                            jineng = 0;
                        } else ++timecount;
                    } else if (weizhi == 2) {
                        if (p6.xiepao2(timecount) == -1) {
                            safe = p6.safecheck();
                            timecount = 0;
                            weizhi = -1;
                            wushi = 0;
                            jineng = 0;
                        } else ++timecount;
                    } else if (weizhi == 3) {
                        if (p6.xiepao3(timecount) == -1) {
                            safe = p6.safecheck();
                            timecount = 0;
                            weizhi = -1;
                            wushi = 0;
                            jineng = 0;
                        } else ++timecount;
                    } else if (weizhi == 4) {
                        if (p6.xiepao4(timecount) == -1) {
                            safe = p6.safecheck();
                            timecount = 0;
                            weizhi = -1;
                            wushi = 0;
                            jineng = 0;
                        } else ++timecount;
                    }
                } else if (jineng == 2) {
                    if (weizhi == 0 || weizhi == 1) {
                        if (suiji2 > 50) {
                            if (p6.changlong1(timecount) == -1) {
                                safe = p6.safecheck();
                                timecount = 0;
                                weizhi = -1;
                                wushi = 0;
                                jineng = 0;
                            } else ++timecount;
                        } else {
                            if (p6.changlong4(timecount) == -1) {
                                safe = p6.safecheck();
                                timecount = 0;
                                weizhi = -1;
                                wushi = 0;
                                jineng = 0;
                            } else ++timecount;
                        }
                    } else if (weizhi == 2) {
                        if (suiji2 > 50) {
                            if (p6.changlong1(timecount) == -1) {
                                safe = p6.safecheck();
                                timecount = 0;
                                weizhi = -1;
                                wushi = 0;
                                jineng = 0;
                            } else ++timecount;
                        } else {
                            if (p6.changlong2(timecount) == -1) {
                                safe = p6.safecheck();
                                timecount = 0;
                                weizhi = -1;
                                wushi = 0;
                                jineng = 0;
                            } else ++timecount;
                        }
                    } else if (weizhi == 3) {
                        if (suiji2 > 50) {
                            if (p6.changlong2(timecount) == -1) {
                                safe = p6.safecheck();
                                timecount = 0;
                                weizhi = -1;
                                wushi = 0;
                                jineng = 0;
                            } else ++timecount;
                        } else {
                            if (p6.changlong3(timecount) == -1) {
                                safe = p6.safecheck();
                                timecount = 0;
                                weizhi = -1;
                                wushi = 0;
                                jineng = 0;
                            } else ++timecount;
                        }
                    } else if (weizhi == 4) {
                        if (suiji2 > 50) {
                            if (p6.changlong3(timecount) == -1) {
                                safe = p6.safecheck();
                                timecount = 0;
                                weizhi = -1;
                                wushi = 0;
                                jineng = 0;
                            } else ++timecount;
                        } else {
                            if (p6.changlong4(timecount) == -1) {
                                safe = p6.safecheck();
                                timecount = 0;
                                weizhi = -1;
                                wushi = 0;
                                jineng = 0;
                            } else ++timecount;
                        }
                    }
                } else if (jineng == 3) {
                    if (p6.danhua(timecount) == -1) {
                        safe = p6.safecheck();
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
            if (suiji > 990) p6.sajiao();
            if (p6.findway() == 1) p6.goW();
            else if (p6.findway() == 2) p6.goA();
            else if (p6.findway() == 3) p6.goS();
            else if (p6.findway() == 4) p6.goD();

            else if (p6.findway() == 0) {
                safe = p6.safecheck();
            } else if (p6.findway() == -2) {
            }

        } else if (safe == 1) {
            //safe==1   啥都不做
            safe = p6.safecheck();
            if (p6.weixian(p6.getHeng(), p6.getShu())) safe = 5;
        } else {
            //safe==5 躲开人喽
            suiji = aa.nextInt(10000);
            if (suiji > 9990) p6.sajiao();
            if (p6.getaway() == 0) {
            } else if (p6.getaway() == 1) {
                p6.goW();
            } else if (p6.getaway() == 2) {
                p6.goA();
            } else if (p6.getaway() == 3) {
                p6.goS();
            } else if (p6.getaway() == 4) {
                p6.goD();
            }
            safe = p6.safecheck();
        }
        ////////////////////////////////////////////////////////////////



        //绘图采用一行一行扫的形式              墙   人  糖浆   糖泡 道具
        for (int j = 0; j < GameConstants.SHU; j++)
            for (int i = 0; i < GameConstants.HENG; i++) {

                if (maps.getWallMap()[i][j] != null && (!maps.getWallMap()[i][j].isRuin()))
                    maps.getWallMap()[i][j].getWallIcon().paintIcon(this, page, i * 50, j * 50 - 12 + 200);
                if (maps.getDaojuMap()[i][j] != null) {
                    maps.getDaojuMap()[i][j].getNow().paintIcon(this, page, i * 50, j * 50 + 200);
                    maps.getDaojuMap()[i][j].beExo();
                }
                if (maps.getBallMap()[i][j] != null) {
                    maps.getBallMap()[i][j].getBallIcon().paintIcon(this, page, i * 50, j * 50 + 200);
                    maps.getBallMap()[i][j].addTime();
                }
                if (maps.getExplosionMap()[i][j] != null) {
                    maps.getExplosionMap()[i][j].getImage().paintIcon(this, page, i * 50, j * 50 + 200);
                    maps.getExplosionMap()[i][j].addTime();
                }

                if (p1.getHeng() == i && p1.getShu() == j)
                    p1.now.paintIcon(this, page, p1.getx(), p1.gety() + 200);

                if (p6.getHeng() == i && p6.getShu() == j)
                    p6.now.paintIcon(this, page, p6.getx(), p6.gety() + 200);
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
