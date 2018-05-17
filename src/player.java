import java.util.Random;

import javax.swing.ImageIcon;


public class player {
    //各种道具的最大量
    protected static final int MAXamount = 10, MAXpower = 8, MAXspeed = 3, MAXfork = 3;
    //各种道具的持有量  收集用这个
    protected int namount = 2, npower = 1, nspeed = 1;
    //protected int namount=10,npower=5,nspeed=4;
    //各种道具的持有量 最终显示效果用这个改变这个
    protected int amount = 2, power = 1, speed = 1, fork = 0;
    //各个时期的玩家图片
    protected ImageIcon now, pa, ps, pd, pw, pkunzhu, pdie, pwin, w, a, s, d;
    //特效之后的样子
    protected ImageIcon fengw, fenga, fengs, fengd;
    protected ImageIcon paow, paoa, paos, paod;
    protected ImageIcon guiw, guia, guis, guid;
    protected ImageIcon zhuw, zhua, zhus, zhud;
    //闪图
    protected ImageIcon sfengw, sfenga, sfengs, sfengd;
    protected ImageIcon spaow, spaoa, spaos, spaod;
    protected ImageIcon sguiw, sguia, sguis, sguid;
    protected ImageIcon szhuw, szhua, szhus, szhud;
    protected ImageIcon spa, sps, spd, spw;
    //玩家专属糖泡
    protected ImageIcon ball;
    //xy表示贴图坐标  XY表示判定位置
    protected int x, y, X, Y;
    //方向按键的值
    protected boolean UP = false, DOWN = false, LEFT = false, RIGHT = false;
    //被打断的次数
    protected int dUP = 0, dDOWN = 0, dLEFT = 0, dRIGHT = 0;
    //按键的松开值
    protected boolean rUP = true, rDOWN = true, rLEFT = true, rRIGHT = true;

    //判断是否按下一个键（为流畅的走位） 是否在使用叉子   判断是否被困住  是否变身
    protected boolean USEtangpao = false, USEfork = false;
    //困住 死亡
    protected boolean kunzhu = false, die = false;
    //变身总时间
    protected static final int MAXbianshen = 1000;
    //计算变身时间
    protected int bianshentime = 0;
    //变成什么啦？     0原来的样子 1风 2泡 3鬼 4猪   5困住了  6死掉啦  7胜利啦
    protected int yangzi = 0;
    //判断死神的秒表
    protected int dietime, Dietime = 600;
    //无敌时间
    protected int wuditime = 0, wudiTime = 300;

    public player() {
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
        switch (yangzi) {
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
        if ((yangzi == 0 || yangzi == 1 || yangzi == 2 || yangzi == 3 || yangzi == 4) && RIGHT != true && LEFT != true && DOWN != true && UP != true) {
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
        if (yangzi == 0 || yangzi == 1 || yangzi == 2 || yangzi == 4) {
            if (yangzi == 0 || yangzi == 2 || yangzi == 4) speed = nspeed;
            if (RIGHT == true && dRIGHT == 0) {
                now = d;
                if (cangod()) X += speed;
                return;
            }
            if (LEFT == true && dLEFT == 0) {
                now = a;
                if (cangoa()) X -= speed;
                return;
            }
            if (DOWN == true && dDOWN == 0) {
                now = s;
                if (cangos()) Y += speed;
                return;
            }
            if (UP == true && dUP == 0) {
                now = w;
                if (cangow()) Y -= speed;
                return;
            }
        }
        if (yangzi == 3) {
            if (LEFT == true && dRIGHT == 0) {
                if (cangod()) X += speed;
                now = d;
                return;
            }
            if (RIGHT == true && dLEFT == 0) {
                if (cangoa()) X -= speed;
                now = a;
                return;
            }
            if (UP == true && dDOWN == 0) {
                if (cangos()) Y += speed;
                now = s;
                return;
            }
            if (DOWN == true && dUP == 0) {
                if (cangow()) Y -= speed;
                now = w;
                return;
            }
        }
    }

    public boolean canspace() {
        return (yangzi <= 3);
    }

    public void kunzhu() {
        yangzi = 5;
        setNow();
        now = pkunzhu;
    }

    public void fuhuo() {

        dietime = 0;
        yangzi = 0;
        setNow();
        now = s;
        Music.music[1].play();
    }

    public void toDie(player p) {
        if (wuditime > 0) wuditime += 1;
        if (wuditime == wudiTime) wuditime = 0;
        if (yangzi == 5) {
            dietime += 1;
            if (dietime == Dietime && p == BattleCanvas.p1) {
                Die();
                BattleCanvas.p2.Win();
            }
            if (dietime == Dietime && p == BattleCanvas.p2) {
                Die();
                BattleCanvas.p1.Win();
            }
            if (BattleCanvas.p1.yangzi != 5 && p == BattleCanvas.p2 && BattleCanvas.p1.getHeng() == BattleCanvas.p2.getHeng() && BattleCanvas.p1.getShu() == BattleCanvas.p2.getShu()) {
                BattleCanvas.p2.Die();
                BattleCanvas.p1.Win();
            }
            if (BattleCanvas.p2.yangzi != 5 && p == BattleCanvas.p1 && BattleCanvas.p1.getHeng() == BattleCanvas.p2.getHeng() && BattleCanvas.p1.getShu() == BattleCanvas.p2.getShu()) {
                BattleCanvas.p1.Die();
                BattleCanvas.p2.Win();
            }
        }
    }

    public void Die() {
        yangzi = 6;
        setNow();
        now = pdie;
        Music.music[7].play();
        Music.music[8].play();
    }

    public void Win() {
        yangzi = 7;
        setNow();
        now = pwin;
        Music.music[3].stop();
    }

    public void beBack() {
        if (bianshentime == MAXbianshen) {
            bianshentime = 0;
            yangzi = 0;
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
            yangzi = 0;
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
        if (yangzi == 1 || yangzi == 2 || yangzi == 3 || yangzi == 4) {
            bianshentime += 1;
        }
    }

    public void beFeng() {
        wuditime = 1;
        bianshentime = 0;
        yangzi = 1;
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

    public void bePao() {
        bianshentime = 0;
        wuditime = 1;
        yangzi = 2;
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

    public void beGui() {
        bianshentime = 0;
        wuditime = 1;
        yangzi = 3;
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

    public void beZhu(player p) {

        if (p == BattleCanvas.p1 && (BattleCanvas.p2.yangzi == 0 || BattleCanvas.p2.yangzi == 1 ||
                BattleCanvas.p2.yangzi == 2 || BattleCanvas.p2.yangzi == 3 || BattleCanvas.p2.yangzi == 4)) {
            BattleCanvas.p2.yangzi = 4;
            if (BattleCanvas.p2.now == BattleCanvas.p2.s) {
                BattleCanvas.p2.setNow();
                BattleCanvas.p2.now = BattleCanvas.p2.s;
            }
            if (BattleCanvas.p2.now == BattleCanvas.p2.w) {
                BattleCanvas.p2.setNow();
                BattleCanvas.p2.now = BattleCanvas.p2.w;
            }
            if (BattleCanvas.p2.now == BattleCanvas.p2.a) {
                BattleCanvas.p2.setNow();
                BattleCanvas.p2.now = BattleCanvas.p2.a;
            }
            if (BattleCanvas.p2.now == BattleCanvas.p2.d) {
                BattleCanvas.p2.setNow();
                BattleCanvas.p2.now = BattleCanvas.p2.d;
            }
            BattleCanvas.p2.wuditime = 1;
            BattleCanvas.p2.bianshentime = 0;
        } else if (p == BattleCanvas.p2 && (BattleCanvas.p1.yangzi == 0 || BattleCanvas.p1.yangzi == 1 ||
                BattleCanvas.p1.yangzi == 2 || BattleCanvas.p1.yangzi == 3 || BattleCanvas.p1.yangzi == 4)) {
            BattleCanvas.p1.yangzi = 4;
            if (BattleCanvas.p1.now == BattleCanvas.p1.s) {
                BattleCanvas.p1.setNow();
                BattleCanvas.p1.now = BattleCanvas.p1.s;
            }
            if (BattleCanvas.p1.now == BattleCanvas.p1.w) {
                BattleCanvas.p1.setNow();
                BattleCanvas.p1.now = BattleCanvas.p1.w;
            }
            if (BattleCanvas.p1.now == BattleCanvas.p1.a) {
                BattleCanvas.p1.setNow();
                BattleCanvas.p1.now = BattleCanvas.p1.a;
            }
            if (BattleCanvas.p1.now == BattleCanvas.p1.d) {
                BattleCanvas.p1.setNow();
                BattleCanvas.p1.now = BattleCanvas.p1.d;
            }
            BattleCanvas.p1.wuditime = 1;
            BattleCanvas.p1.bianshentime = 0;
        } else if (p == Battlemaoxian.p1 && (Battlemaoxian.p1.yangzi == 0 || Battlemaoxian.p1.yangzi == 1 ||
                Battlemaoxian.p1.yangzi == 2 || Battlemaoxian.p1.yangzi == 3 || Battlemaoxian.p1.yangzi == 4)) {
            Battlemaoxian.p1.yangzi = 4;
            if (Battlemaoxian.p1.now == Battlemaoxian.p1.s) {
                Battlemaoxian.p1.setNow();
                Battlemaoxian.p1.now = Battlemaoxian.p1.s;
            }
            if (Battlemaoxian.p1.now == Battlemaoxian.p1.w) {
                Battlemaoxian.p1.setNow();
                Battlemaoxian.p1.now = Battlemaoxian.p1.w;
            }
            if (Battlemaoxian.p1.now == Battlemaoxian.p1.a) {
                Battlemaoxian.p1.setNow();
                Battlemaoxian.p1.now = Battlemaoxian.p1.a;
            }
            if (Battlemaoxian.p1.now == Battlemaoxian.p1.d) {
                Battlemaoxian.p1.setNow();
                Battlemaoxian.p1.now = Battlemaoxian.p1.d;
            }
            Battlemaoxian.p1.bianshentime = 0;
        }
    }

    public void eatdaoju() {
        if (Map.isDaoju(getHeng(), getShu())) {
            switch (Map.daojumap[getHeng()][getShu()].getType()) {
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
                    beZhu(this);
                    break;
                }
            }
            Map.daojumap[getHeng()][getShu()] = null;
            Music.music[4].stop();
            Music.music[4].play();
        }
    }

    public void beExp() {
        if (Map.isExp(getHeng(), getShu()) && yangzi == 0 && wuditime == 0) kunzhu();
        if (Map.isExp(getHeng(), getShu()) && (yangzi == 1 || yangzi == 2 || yangzi == 3 || yangzi == 4) && wuditime == 0) {
            bianshentime = MAXbianshen + 1;
            beBack();
        }
    }

    public void addAmount() {
        if (namount < MAXamount)
            namount += 1;
    }

    public void addPower() {
        if (npower < MAXpower)
            npower += 1;
    }

    public void addSpeed() {
        if (nspeed < MAXspeed)
            nspeed += 1;
    }

    public void addFork() {
        if (fork < MAXfork)
            fork += 1;
    }

    public void setYangzi(int yangzi) {
        this.yangzi = yangzi;
    }

    public ImageIcon getBall() {
        return ball;
    }

    public int getHeng() {
        return X / 50;
    }

    public int getShu() {
        return Y / 50;
    }

    public int getx() {
        if (yangzi == 1) return X - 25;
        else if (yangzi == 2) return X - 30;
        else if (yangzi == 3) return X - 25;
        else if (yangzi == 4) return X - 55;
        else return X - 43 + BattleCanvas.jiangeheng;
    }

    public int gety() {
        if (yangzi == 1) return Y - 75;
        else if (yangzi == 2) return Y - 48;
        else if (yangzi == 3) return Y - 55;
        else if (yangzi == 4) return Y - 80;
        else return Y - 73 + BattleCanvas.jiangeshu;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public boolean cangow() {
        if (yangzi <= 4) {
            boolean wall = false;
            boolean ball = false;
            boolean bianjie = false;
            if (Y >= 6)
                bianjie = true;
            if ((getShu() - 1 >= 0 && Map.isWall(getHeng(), getShu() - 1) == false)
                    || (getShu() - 1 >= 0 && Map.isWall(getHeng(), getShu() - 1) == true && Y > ((getShu() - 1) * 50 + 65))
                    || (getShu() - 1 < 0 && Map.isWall(getHeng(), getShu()) == false))
                wall = true;
            if ((getShu() - 1 >= 0 && Map.isBoom(getHeng(), getShu() - 1) == false)
                    || (getShu() - 1 >= 0 && Map.isBoom(getHeng(), getShu() - 1) == true && Y > ((getShu() - 1) * 50 + 65))
                    || (getShu() - 1 < 0 && Map.isBoom(getHeng(), getShu()) == false))
                ball = true;

            return (wall && ball && bianjie);

        } else return false;
    }

    public boolean cangoa() {
        if (yangzi <= 4) {
            boolean wall = false;
            boolean ball = false;
            boolean bianjie = false;
            if (X >= 6)
                bianjie = true;
            if ((getHeng() - 1 >= 0 && Map.isBoom(getHeng() - 1, getShu()) == false)
                    || (getHeng() - 1 >= 0 && Map.isBoom(getHeng() - 1, getShu()) == true && X > (getHeng() - 1) * 50 + 65)
                    || (getHeng() - 1 < 0 && Map.isBoom(getHeng(), getShu()) == false))
                ball = true;
            if ((getHeng() - 1 >= 0 && Map.isWall(getHeng() - 1, getShu()) == false)
                    || (getHeng() - 1 >= 0 && Map.isWall(getHeng() - 1, getShu()) == true && X > (getHeng() - 1) * 50 + 65)
                    || (getHeng() - 1 < 0 && Map.isWall(getHeng(), getShu()) == false))
                wall = true;
            return (wall && ball && bianjie);
        } else return false;
    }

    public boolean cangos() {
        if (yangzi <= 4) {
            boolean wall = false;
            boolean ball = false;
            boolean bianjie = false;
            if (Y <= 394)
                bianjie = true;
            if ((getShu() + 1 <= 7 && Map.isBoom(getHeng(), getShu() + 1) == false)
                    || (getShu() + 1 <= 7 && Map.isBoom(getHeng(), getShu() + 1) == true && Y < ((getShu() + 1) * 50 - 15))
                    || (getShu() + 1 > 7 && Map.isBoom(getHeng(), getShu()) == false))
                ball = true;
            if ((getShu() + 1 <= 7 && Map.isWall(getHeng(), getShu() + 1) == false)
                    || (getShu() + 1 <= 7 && Map.isWall(getHeng(), getShu() + 1) == true && Y < ((getShu() + 1) * 50 - 15))
                    || (getShu() + 1 > 7 && Map.isWall(getHeng(), getShu()) == false))
                wall = true;
            return (wall && ball && bianjie);
        } else return false;
    }

    public boolean cangod() {
        if (yangzi <= 4) {
            boolean wall = false;
            boolean ball = false;
            boolean bianjie = false;
            if (X <= 644)
                bianjie = true;
            if ((getHeng() + 1 <= 12 && Map.isBoom(getHeng() + 1, getShu()) == false)
                    || (getHeng() + 1 <= 12 && Map.isBoom(getHeng() + 1, getShu()) == true && X < (getHeng() + 1) * 50 - 15)
                    || (getHeng() + 1 > 12 && Map.isBoom(getHeng(), getShu()) == false))
                ball = true;
            if ((getHeng() + 1 <= 12 && Map.isWall(getHeng() + 1, getShu()) == false)
                    || (getHeng() + 1 <= 12 && Map.isWall(getHeng() + 1, getShu()) == true && X < (getHeng() + 1) * 50 - 15)
                    || (getHeng() + 1 > 12 && Map.isWall(getHeng(), getShu()) == false))
                wall = true;
            return (wall && ball && bianjie);
        } else return false;
    }
}
