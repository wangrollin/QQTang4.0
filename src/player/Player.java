package player;

import constants.GameConstants;
import element.Maps;
import element.Ball;
import element.MusicTool;
import panel.battle.BattleJingjiPanel;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Player implements KeyListener {
    public Maps maps = new Maps();

    public Maps getMaps() {
        return maps;
    }

    //现在放下去的糖泡的数量
    private int count = 0;

    //各种道具的最大量
    protected static final int MAXamount = 10, MAXpower = 8, MAXspeed = 3, MAXfork = 3;


    //各种道具的持有量  收集用这个
    int namount = 2, npower = 1, nspeed = 1;
    //protected int namount=10,npower=5,nspeed=4;
    //各种道具的持有量 最终显示效果用这个改变这个
    int amount = 2, power = 1, speed = 1, fork = 0;
    //各个时期的玩家图片
    public ImageIcon now, pa, ps, pd, pw, pkunzhu, pdie, pwin, w, a, s, d;
    //特效之后的样子
    private ImageIcon fengw, fenga, fengs, fengd;
    private ImageIcon paow, paoa, paos, paod;
    private ImageIcon guiw, guia, guis, guid;
    private ImageIcon zhuw, zhua, zhus, zhud;
    //闪图
    private ImageIcon sfengw, sfenga, sfengs, sfengd;
    private ImageIcon spaow, spaoa, spaos, spaod;
    private ImageIcon sguiw, sguia, sguis, sguid;
    private ImageIcon szhuw, szhua, szhus, szhud;
    ImageIcon spa, sps, spd, spw;
    //玩家专属糖泡
    ImageIcon ballIcon;
    //xy表示贴图坐标  XY表示判定位置
    public int x, y, X, Y;

    //方向按键的值
    boolean UP = false, DOWN = false, LEFT = false, RIGHT = false;
    //被打断的次数
    int dUP = 0, dDOWN = 0, dLEFT = 0, dRIGHT = 0;
    //按键的松开值
    boolean rUP = true, rDOWN = true, rLEFT = true, rRIGHT = true;

    //判断是否按下一个键（为流畅的走位） 是否在使用叉子   判断是否被困住  是否变身
    boolean USEtangpao = false, USEfork = false;
    //困住 死亡
    protected boolean kunzhu = false, die = false;
    //变身总时间
    private static final int MAXbianshen = 1000;
    //计算变身时间
    int bianshentime = 0;
    //变成什么啦？     0原来的样子 1风 2泡 3鬼 4猪   5困住了  6死掉啦  7胜利啦
    public int outlooking = 0;
    static final int ORIGIN = 0;
    static final int WIND = 1;
    static final int CANDY = 2;
    static final int GHOST = 3;
    static final int FOX = 4;
    static final int STUCK = 5;
    static final int LOSER = 6;
    static final int WINNER = 7;
    //判断死神的秒表
    int dietime;
    static final int BEFORE_DIE_TIME = 600;
    //无敌时间
    int wuditime = 0;
    static final int FLASH_TIME = 300;

    private int playerNumber;

    public int getPlayerNumber() {
        return playerNumber;
    }

    public Player(int playNumber, Maps maps) {
        this.playerNumber = playNumber;
        this.maps = maps;
        //三个特效人物
        fengw = new ImageIcon("fengw.gif");
        fengs = new ImageIcon("fengs.gif");
        fenga = new ImageIcon("fenga.gif");
        fengd = new ImageIcon("fengd.gif");

        paow = new ImageIcon("paow.gif");
        paoa = new ImageIcon("paoa.gif");
        paos = new ImageIcon("paos.gif");
        paod = new ImageIcon("paod.gif");

        zhuw = new ImageIcon("zhuw.gif");
        zhua = new ImageIcon("zhua.gif");
        zhus = new ImageIcon("zhus.gif");
        zhud = new ImageIcon("zhud.gif");

        guiw = new ImageIcon("guiw.gif");
        guia = new ImageIcon("guia.gif");
        guis = new ImageIcon("guis.gif");
        guid = new ImageIcon("guid.gif");
        ////////////
        sfengw = new ImageIcon("sfengw.gif");
        sfengs = new ImageIcon("sfengs.gif");
        sfenga = new ImageIcon("sfenga.gif");
        sfengd = new ImageIcon("sfengd.gif");

        spaow = new ImageIcon("spaow.gif");
        spaoa = new ImageIcon("spaoa.gif");
        spaos = new ImageIcon("spaos.gif");
        spaod = new ImageIcon("spaod.gif");

        szhuw = new ImageIcon("szhuw.gif");
        szhua = new ImageIcon("szhua.gif");
        szhus = new ImageIcon("szhus.gif");
        szhud = new ImageIcon("szhud.gif");

        sguiw = new ImageIcon("sguiw.gif");
        sguia = new ImageIcon("sguia.gif");
        sguis = new ImageIcon("sguis.gif");
        sguid = new ImageIcon("sguid.gif");
    }

    //决定现在的一套wasd
    public void setNow() {
        switch (outlooking) {
            case 0:
                if (wuditime == 0) {
                    s = ps;
                    w = pw;
                    a = pa;
                    d = pd;
                    break;
                } else {
                    s = sps;
                    w = spw;
                    a = spa;
                    d = spd;
                    break;
                }
            case 1:
                if (wuditime == 0) {
                    s = fengs;
                    w = fengw;
                    a = fenga;
                    d = fengd;
                    break;
                } else {
                    s = sfengs;
                    w = sfengw;
                    a = sfenga;
                    d = sfengd;
                    break;
                }
            case 2:
                if (wuditime == 0) {
                    s = paos;
                    w = paow;
                    a = paoa;
                    d = paod;
                    break;
                } else {
                    s = spaos;
                    w = spaow;
                    a = spaoa;
                    d = spaod;
                    break;
                }
            case 3:
                if (wuditime == 0) {
                    s = guis;
                    w = guiw;
                    a = guia;
                    d = guid;
                    break;
                } else {
                    s = sguis;
                    w = sguiw;
                    a = sguia;
                    d = sguid;
                    break;
                }
            case 4:
                if (wuditime == 0) {
                    s = zhus;
                    w = zhuw;
                    a = zhua;
                    d = zhud;
                    break;
                } else {
                    s = szhus;
                    w = szhuw;
                    a = szhua;
                    d = szhud;
                    break;
                }
            case 5:
                s = pkunzhu;
                w = pkunzhu;
                a = pkunzhu;
                d = pkunzhu;
                break;
            case 6:
                s = pdie;
                w = pdie;
                a = pdie;
                d = pdie;
                break;

            case 7:
                s = pwin;
                w = pwin;
                a = pwin;
                d = pwin;
                break;
        }
    }

    public void move() {
        if ((outlooking == 0 || outlooking == 1 || outlooking == 2 || outlooking == 3 || outlooking == 4) && !RIGHT && !LEFT && !DOWN && !UP) {
            if (now == s) {
                setNow();
                now = s;
            }
            if (now == w) {
                setNow();
                now = w;
            }
            if (now == a) {
                setNow();
                now = a;
            }
            if (now == d) {
                setNow();
                now = d;
            }
        }
        setNow();
        if (outlooking == 0 || outlooking == 1 || outlooking == 2 || outlooking == 4) {
            if (outlooking == 0 || outlooking == 2 || outlooking == 4) speed = nspeed;
            if (RIGHT && dRIGHT == 0) {
                now = d;
                if (cangod()) X += speed;
                return;
            }
            if (LEFT && dLEFT == 0) {
                now = a;
                if (cangoa()) X -= speed;
                return;
            }
            if (DOWN && dDOWN == 0) {
                now = s;
                if (cangos()) Y += speed;
                return;
            }
            if (UP && dUP == 0) {
                now = w;
                if (cangow()) Y -= speed;
                return;
            }
        }
        if (outlooking == 3) {
            if (LEFT && dRIGHT == 0) {
                if (cangod()) X += speed;
                now = d;
                return;
            }
            if (RIGHT && dLEFT == 0) {
                if (cangoa()) X -= speed;
                now = a;
                return;
            }
            if (UP && dDOWN == 0) {
                if (cangos()) Y += speed;
                now = s;
                return;
            }
            if (DOWN && dUP == 0) {
                if (cangow()) Y -= speed;
                now = w;
            }
        }
    }

    public boolean canspace() {
        return (outlooking <= 3);
    }

    public void kunzhu() {
        outlooking = 5;
        setNow();
        now = pkunzhu;
    }

    void fuhuo() {

        dietime = 0;
        outlooking = 0;
        setNow();
        now = s;
        MusicTool.music[1].play();
    }

    /*public void toDie(player.Player p) {
        if (wuditime > 0) wuditime += 1;
        if (wuditime == FLASH_TIME) wuditime = 0;
        if (outlooking == 5) {
            dietime += 1;
            if (dietime == BEFORE_DIE_TIME && p == panel.battle.BattleJingjiPanel.p1) {
                Die();
                panel.battle.BattleJingjiPanel.p2.Win();
            }
            if (dietime == BEFORE_DIE_TIME && p == panel.battle.BattleJingjiPanel.p2) {
                Die();
                panel.battle.BattleJingjiPanel.p1.Win();
            }
            if (panel.battle.BattleJingjiPanel.p1.outlooking != 5 && p == panel.battle.BattleJingjiPanel.p2 && panel.battle.BattleJingjiPanel.p1.getHeng() == panel.battle.BattleJingjiPanel.p2.getHeng() && panel.battle.BattleJingjiPanel.p1.getShu() == panel.battle.BattleJingjiPanel.p2.getShu()) {
                panel.battle.BattleJingjiPanel.p2.Die();
                panel.battle.BattleJingjiPanel.p1.Win();
            }
            if (panel.battle.BattleJingjiPanel.p2.outlooking != 5 && p == panel.battle.BattleJingjiPanel.p1 && panel.battle.BattleJingjiPanel.p1.getHeng() == panel.battle.BattleJingjiPanel.p2.getHeng() && panel.battle.BattleJingjiPanel.p1.getShu() == panel.battle.BattleJingjiPanel.p2.getShu()) {
                panel.battle.BattleJingjiPanel.p1.Die();
                panel.battle.BattleJingjiPanel.p2.Win();
            }
        }
    }*/

    public void keepDoing(Player anotherPlayer) {
        move();
        toDie(anotherPlayer);
        eatdaoju();
        beExp();
        beBack();
    }

    public void toDie(Player anotherPlayer) {
        if (wuditime > 0) wuditime += 1;
        if (wuditime == FLASH_TIME) wuditime = 0;
        if (outlooking == 5) {
            dietime += 1;
            if (dietime == BEFORE_DIE_TIME) {
                Die();
                anotherPlayer.Win();
            } else if (anotherPlayer.outlooking != 5
                    && getHeng() == anotherPlayer.getHeng()
                    && getShu() == anotherPlayer.getShu()) {
                Die();
                anotherPlayer.Win();
            }
        }
    }

    public void Die() {
        outlooking = 6;
        setNow();
        now = pdie;
        MusicTool.music[7].play();
        MusicTool.music[8].play();
    }

    public void Win() {
        outlooking = 7;
        setNow();
        now = pwin;
        MusicTool.music[3].stop();
    }

    public void beBack() {
        if (bianshentime == MAXbianshen) {
            bianshentime = 0;
            outlooking = 0;
            if (now == s) {
                setNow();
                now = s;
            }
            if (now == w) {
                setNow();
                now = w;
            }
            if (now == a) {
                setNow();
                now = a;
            }
            if (now == d) {
                setNow();
                now = d;
            }
            amount = namount;
            power = npower;
            speed = nspeed;
        }
        if (bianshentime == MAXbianshen + 1) {
            wuditime += 1;
            bianshentime = 0;
            outlooking = 0;
            if (now == s) {
                setNow();
                now = s;
            }
            if (now == w) {
                setNow();
                now = w;
            }
            if (now == a) {
                setNow();
                now = a;
            }
            if (now == d) {
                setNow();
                now = d;
            }
            amount = namount;
            power = npower;
            speed = nspeed;
        }
        if (outlooking == 1 || outlooking == 2 || outlooking == 3 || outlooking == 4) {
            bianshentime += 1;
        }
    }

    private void beFeng() {
        wuditime = 1;
        bianshentime = 0;
        outlooking = 1;
        if (now == s) {
            setNow();
            now = s;
        }
        if (now == w) {
            setNow();
            now = w;
        }
        if (now == a) {
            setNow();
            now = a;
        }
        if (now == d) {
            setNow();
            now = d;
        }
        speed = MAXspeed;
    }

    private void bePao() {
        bianshentime = 0;
        wuditime = 1;
        outlooking = 2;
        if (now == s) {
            setNow();
            now = s;
        }
        if (now == w) {
            setNow();
            now = w;
        }
        if (now == a) {
            setNow();
            now = a;
        }
        if (now == d) {
            setNow();
            now = d;
        }
        amount = MAXamount;
    }

    private void beGui() {
        bianshentime = 0;
        wuditime = 1;
        outlooking = 3;
        if (now == s) {
            setNow();
            now = s;
        }
        if (now == w) {
            setNow();
            now = w;
        }
        if (now == a) {
            setNow();
            now = a;
        }
        if (now == d) {
            setNow();
            now = d;
        }
        amount = MAXamount;
        power = MAXpower;
        speed = MAXspeed;
    }

    public void beZhu() {
        if (outlooking == 0 || outlooking == 1 || outlooking == 2
                || outlooking == 3 || outlooking == 4) {
            outlooking = 4;
            if (now == s) {
                setNow();
                now = s;
            } else if (now == w) {
                setNow();
                now = w;
            } else if (now == a) {
                setNow();
                now = a;
            } else if (now == d) {
                setNow();
                now = d;
            }
            bianshentime = 0;
        }
    }

    public void eatdaoju() {
        if (maps.isDaoju(getHeng(), getShu())) {
            switch (maps.getDaojuMap()[getHeng()][getShu()].getType()) {
                case 1: {
                    addAmount();
                    break;
                }
                case 2: {
                    addAmount();
                    break;
                }
                case 3: {
                    addPower();
                    break;
                }
                case 4: {
                    addPower();
                    break;
                }
                case 5: {
                    addSpeed();
                    break;
                }
                case 6: {
                    addSpeed();
                    break;
                }
                case 7: {
                    addFork();
                    break;
                }
                case 8: {
                    beFeng();
                    break;
                }
                case 9: {
                    bePao();
                    break;
                }
                case 10: {
                    beGui();
                    break;
                }
                case 11: {
                    beZhu();
                    break;
                }
            }
            maps.getDaojuMap()[getHeng()][getShu()] = null;
            MusicTool.music[4].stop();
            MusicTool.music[4].play();
        }
    }

    public void beExp() {
        if (maps.isExp(getHeng(), getShu()) && outlooking == 0 && wuditime == 0) kunzhu();
        if (maps.isExp(getHeng(), getShu()) && (outlooking == 1 || outlooking == 2 || outlooking == 3 || outlooking == 4) && wuditime == 0) {
            bianshentime = MAXbianshen + 1;
            beBack();
        }
    }

    private void addAmount() {
        if (namount < MAXamount)
            namount += 1;
    }

    private void addPower() {
        if (npower < MAXpower)
            npower += 1;
    }

    private void addSpeed() {
        if (nspeed < MAXspeed)
            nspeed += 1;
    }

    private void addFork() {
        if (fork < MAXfork)
            fork += 1;
    }

    /*public void setYangzi(int outlooking) {
        this.outlooking = outlooking;
    }*/

    public ImageIcon getBallIcon() {
        return ballIcon;
    }

    public int getHeng() {
        return X / 50;
    }

    public int getShu() {
        return Y / 50;
    }

    public int getx() {
        if (outlooking == 1) return X - 25;
        else if (outlooking == 2) return X - 30;
        else if (outlooking == 3) return X - 25;
        else if (outlooking == 4) return X - 55;
        else return X - 43 + BattleJingjiPanel.jiangeheng;
    }

    public int gety() {
        if (outlooking == 1) return Y - 75;
        else if (outlooking == 2) return Y - 48;
        else if (outlooking == 3) return Y - 55;
        else if (outlooking == 4) return Y - 80;
        else return Y - 73 + BattleJingjiPanel.jiangeshu;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }

    public boolean cangow() {
        if (outlooking <= 4) {
            boolean wall = false;
            boolean ball = false;
            boolean isBorder = false;
            if (Y >= 6)
                isBorder = true;
            if ((getShu() - 1 >= 0 && !maps.isWall(getHeng(), getShu() - 1))
                    || (getShu() - 1 >= 0 && maps.isWall(getHeng(), getShu() - 1)
                        && Y > ((getShu() - 1) * 50 + 65))
                    || (getShu() - 1 < 0 && !maps.isWall(getHeng(), getShu())))
                wall = true;
            if ((getShu() - 1 >= 0 && !maps.isBoom(getHeng(), getShu() - 1))
                    || (getShu() - 1 >= 0 && maps.isBoom(getHeng(), getShu() - 1) && Y > ((getShu() - 1) * 50 + 65))
                    || (getShu() - 1 < 0 && !maps.isBoom(getHeng(), getShu())))
                ball = true;

            return (wall && ball && isBorder);

        } else return false;
    }

    public boolean cangoa() {
        if (outlooking <= 4) {
            boolean wall = false;
            boolean ball = false;
            boolean isBorder = false;
            if (X >= 6)
                isBorder = true;
            if ((getHeng() - 1 >= 0 && !maps.isBoom(getHeng() - 1, getShu()))
                    || (getHeng() - 1 >= 0 && maps.isBoom(getHeng() - 1, getShu())
                        && X > (getHeng() - 1) * 50 + 65)
                    || (getHeng() - 1 < 0 && !maps.isBoom(getHeng(), getShu())))
                ball = true;
            if ((getHeng() - 1 >= 0 && !maps.isWall(getHeng() - 1, getShu()))
                    || (getHeng() - 1 >= 0 && maps.isWall(getHeng() - 1, getShu())
                        && X > (getHeng() - 1) * 50 + 65)
                    || (getHeng() - 1 < 0 && !maps.isWall(getHeng(), getShu())))
                wall = true;
            return (wall && ball && isBorder);
        } else return false;
    }

    public boolean cangos() {
        if (outlooking <= 4) {
            boolean wall = false;
            boolean ball = false;
            boolean isBorder = false;
            if (Y <= 394)
                isBorder = true;
            if ((getShu() + 1 <= 7 && !maps.isBoom(getHeng(), getShu() + 1))
                    || (getShu() + 1 <= 7 && maps.isBoom(getHeng(), getShu() + 1) && Y < ((getShu() + 1) * 50 - 15))
                    || (getShu() + 1 > 7 && !maps.isBoom(getHeng(), getShu())))
                ball = true;
            if ((getShu() + 1 <= 7 && !maps.isWall(getHeng(), getShu() + 1))
                    || (getShu() + 1 <= 7 && maps.isWall(getHeng(), getShu() + 1) && Y < ((getShu() + 1) * 50 - 15))
                    || (getShu() + 1 > 7 && !maps.isWall(getHeng(), getShu())))
                wall = true;
            return (wall && ball && isBorder);
        } else return false;
    }

    public boolean cangod() {
        if (outlooking <= 4) {
            boolean wall = false;
            boolean ball = false;
            boolean isBorder = false;
            if (X <= 644)
                isBorder = true;
            if ((getHeng() + 1 <= 12 && !maps.isBoom(getHeng() + 1, getShu()))
                    || (getHeng() + 1 <= 12 && maps.isBoom(getHeng() + 1, getShu()) && X < (getHeng() + 1) * 50 - 15)
                    || (getHeng() + 1 > 12 && !maps.isBoom(getHeng(), getShu())))
                ball = true;
            if ((getHeng() + 1 <= 12 && !maps.isWall(getHeng() + 1, getShu()))
                    || (getHeng() + 1 <= 12 && maps.isWall(getHeng() + 1, getShu()) && X < (getHeng() + 1) * 50 - 15)
                    || (getHeng() + 1 > 12 && !maps.isWall(getHeng(), getShu())))
                wall = true;
            return (wall && ball && isBorder);
        } else return false;
    }

    public void subCount() {
        count--;
    }

    public void addCount() {
        count++;
    }

    public void setBall() {
        new Ball(this, getHeng(), getShu(), power, getBallIcon(), maps);
        MusicTool.music[5].play();
    }
    //完美的行动派监听器！！！！！！！！！********************************************************************
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.playerNumber == GameConstants.PLAYER1) {
            if (outlooking <= 4) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        dUP = 0;
                        rUP = false;
                        if (rRIGHT == false && UP == false) dRIGHT++;
                        if (rLEFT == false && UP == false) dLEFT++;
                        if (rDOWN == false && UP == false) dDOWN++;
                        UP = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        dDOWN = 0;
                        rDOWN = false;
                        if (rRIGHT == false && DOWN == false) dRIGHT++;
                        if (rLEFT == false && DOWN == false) dLEFT++;
                        if (rUP == false && DOWN == false) dUP++;
                        DOWN = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        dLEFT = 0;
                        rLEFT = false;
                        if (rRIGHT == false && LEFT == false) dRIGHT++;
                        if (rUP == false && LEFT == false) dUP++;
                        if (rDOWN == false && LEFT == false) dDOWN++;
                        LEFT = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        dRIGHT = 0;
                        rRIGHT = false;
                        if (rUP == false && RIGHT == false) dUP++;
                        if (rLEFT == false && RIGHT == false) dLEFT++;
                        if (rDOWN == false && RIGHT == false) dDOWN++;
                        RIGHT = true;
                        break;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {

                if (outlooking == 0 || outlooking == 1) amount = namount;
                if (outlooking == 0 || outlooking == 1 || outlooking == 2) power = npower;
                if (canspace() && USEtangpao == false && count < amount && maps.getBallMap()[getHeng()][getShu()] == null)
                    setBall();
                USEtangpao = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_M && USEfork == false) {
                if (fork > 0 && outlooking == 5) {
                    fork -= 1;
                    fuhuo();
                }
                USEfork = true;
            }
        } else {
            if (outlooking <= 4) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_R:
                        dUP = 0;
                        rUP = false;
                        if (rRIGHT == false && UP == false) dRIGHT++;
                        if (rLEFT == false && UP == false) dLEFT++;
                        if (rDOWN == false && UP == false) dDOWN++;
                        UP = true;
                        break;
                    case KeyEvent.VK_F:
                        dDOWN = 0;
                        rDOWN = false;
                        if (rRIGHT == false && DOWN == false) dRIGHT++;
                        if (rLEFT == false && DOWN == false) dLEFT++;
                        if (rUP == false && DOWN == false) dUP++;
                        DOWN = true;
                        break;
                    case KeyEvent.VK_D:
                        dLEFT = 0;
                        rLEFT = false;
                        if (rRIGHT == false && LEFT == false) dRIGHT++;
                        if (rUP == false && LEFT == false) dUP++;
                        if (rDOWN == false && LEFT == false) dDOWN++;
                        LEFT = true;
                        break;
                    case KeyEvent.VK_G:
                        dRIGHT = 0;
                        rRIGHT = false;
                        if (rUP == false && RIGHT == false) dUP++;
                        if (rLEFT == false && RIGHT == false) dLEFT++;
                        if (rDOWN == false && RIGHT == false) dDOWN++;
                        RIGHT = true;
                        break;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_Q) {
                if (outlooking == 0 || outlooking == 1) amount = namount;
                if (outlooking == 0 || outlooking == 1 || outlooking == 2) power = npower;
                if (canspace() && USEtangpao == false && count < amount && maps.getBallMap()[getHeng()][getShu()] == null)
                    setBall();
                USEtangpao = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_W && USEfork == false) {
                if (fork > 0 && outlooking == 5) {
                    fork -= 1;
                    fuhuo();
                }
                USEfork = true;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (this.playerNumber == GameConstants.PLAYER1) {
            if (outlooking <= 4) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        rUP = true;
                        if (dRIGHT > dUP && UP == true) dRIGHT--;
                        if (dLEFT > dUP && UP == true) dLEFT--;
                        if (dDOWN > dUP && UP == true) dDOWN--;
                        dUP = 0;
                        UP = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        rDOWN = true;
                        if (dRIGHT > dDOWN && DOWN == true) dRIGHT--;
                        if (dLEFT > dDOWN && DOWN == true) dLEFT--;
                        if (dUP > dDOWN && DOWN == true) dUP--;
                        DOWN = false;
                        dDOWN = 0;
                        break;
                    case KeyEvent.VK_LEFT:
                        rLEFT = true;
                        if (dRIGHT > dLEFT && LEFT == true) dRIGHT--;
                        if (dUP > dLEFT && LEFT == true) dUP--;
                        if (dDOWN > dLEFT && LEFT == true) dDOWN--;
                        LEFT = false;
                        dLEFT = 0;
                        break;
                    case KeyEvent.VK_RIGHT:
                        rRIGHT = true;
                        if (dUP > dRIGHT && RIGHT == true) dUP--;
                        if (dLEFT > dRIGHT && RIGHT == true) dLEFT--;
                        if (dDOWN > dRIGHT && RIGHT == true) dDOWN--;
                        RIGHT = false;
                        dRIGHT = 0;
                        break;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) USEtangpao = false;
            if (e.getKeyCode() == KeyEvent.VK_M) USEfork = false;
        } else {
            if (outlooking <= 4) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_R:
                        rUP = true;
                        if (dRIGHT > dUP && UP == true) dRIGHT--;
                        if (dLEFT > dUP && UP == true) dLEFT--;
                        if (dDOWN > dUP && UP == true) dDOWN--;
                        dUP = 0;
                        UP = false;
                        break;
                    case KeyEvent.VK_F:
                        rDOWN = true;
                        if (dRIGHT > dDOWN && DOWN == true) dRIGHT--;
                        if (dLEFT > dDOWN && DOWN == true) dLEFT--;
                        if (dUP > dDOWN && DOWN == true) dUP--;
                        DOWN = false;
                        dDOWN = 0;
                        break;
                    case KeyEvent.VK_D:
                        rLEFT = true;
                        if (dRIGHT > dLEFT && LEFT == true) dRIGHT--;
                        if (dUP > dLEFT && LEFT == true) dUP--;
                        if (dDOWN > dLEFT && LEFT == true) dDOWN--;
                        LEFT = false;
                        dLEFT = 0;
                        break;
                    case KeyEvent.VK_G:
                        rRIGHT = true;
                        if (dUP > dRIGHT && RIGHT == true) dUP--;
                        if (dLEFT > dRIGHT && RIGHT == true) dLEFT--;
                        if (dDOWN > dRIGHT && RIGHT == true) dDOWN--;
                        RIGHT = false;
                        dRIGHT = 0;
                        break;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_Q) USEtangpao = false;
            if (e.getKeyCode() == KeyEvent.VK_W) USEfork = false;
        }

    }
}
