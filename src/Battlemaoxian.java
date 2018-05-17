import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Battlemaoxian extends JPanel {
    protected static JButton fanhui, jieshu;
    //格子数
    protected final static int heng = 13, shu = 8;
    //长宽
    private final int WIDTH = 650, HEIGHT = 400 + 200;
    //时间监听毫秒
    private final int DELAY = 15;
    //时间老人
    Timer timer;
    //地图
    static Map map;
    //人物登场
    static P3 p1;
    static Enemy2 p6;

    protected Random aa;
    protected int safe = -1, timecount = 0, jineng = 0, suiji, suiji2, wushi = 0, weizhi;

    public Battlemaoxian() {
        timecount = 0;
        aa = new Random();
        fanhui = new JButton(new ImageIcon("replay1.png"));
        fanhui.setBounds(10, 10, 128, 50);
        add(fanhui);

        jieshu = new JButton(new ImageIcon("exit1.png"));
        jieshu.setBounds(510, 10, 130, 50);
        add(jieshu);
        Music.Musicload();

        map = new Map();
        p1 = new P3();
        p6 = new Enemy2();
        timer = new Timer(DELAY, new Mytime());
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        timer.start();
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);

        if (p6.beexp() == true) {
            Music.stop();
            Music.music[8].loop();
            Mypanel.lay.show(Play.panel, "zhanwin");
        }


        map.biwumen().paintIcon(this, page, 0, 0);
        map.getBack().paintIcon(this, page, 0, 200);
        //不断刷新的
        p1.move();
        if (p1.toDie()) {
            Mypanel.lay.show(Play.panel, "zhanshu");
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

        ////////////////////////////////////////////////////////
        /*for(int i=0;i<100000;++i)
		{
			i=i+100-100;
		}*/


        //绘图采用一行一行扫的形式              墙   人  糖浆   糖泡 道具
        for (int j = 0; j < shu; j++)
            for (int i = 0; i < heng; i++) {

                if (map.wallmap[i][j] != null && (!map.wallmap[i][j].isRuin()))
                    map.wallmap[i][j].getImage().paintIcon(this, page, i * 50, j * 50 - 12 + 200);
                if (map.daojumap[i][j] != null) {
                    map.daojumap[i][j].getNow().paintIcon(this, page, i * 50, j * 50 + 200);
                    map.daojumap[i][j].beExo();
                }
                if (map.boommap[i][j] != null) {
                    map.boommap[i][j].getBallIcon().paintIcon(this, page, i * 50, j * 50 + 200);
                    map.boommap[i][j].addTime();
                }
                if (map.expmap[i][j] != null) {
                    map.expmap[i][j].getImage().paintIcon(this, page, i * 50, j * 50 + 200);
                    map.expmap[i][j].addTime();
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

}
