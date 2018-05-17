import javax.swing.ImageIcon;


class Player {
    //各种道具的最大量
    protected static final int MAXamount = 10, MAXpower = 8, MAXspeed = 3, MAXfork = 3;


    //各种道具的持有量  收集用这个
    int namount = 2, npower = 1, nspeed = 1;
    //protected int namount=10,npower=5,nspeed=4;
    //各种道具的持有量 最终显示效果用这个改变这个
    int amount = 2, power = 1, speed = 1, fork = 0;
    //各个时期的玩家图片
    ImageIcon now, pa, ps, pd, pw, pkunzhu, pdie, pwin, w, a, s, d;
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
    ImageIcon ball;
    //xy表示贴图坐标  XY表示判定位置
    protected int x, y, X, Y;
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
    int outlooking = 0;
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

    Player() {
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
    void setNow() {
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

    boolean canspace() {
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
        Music.music[1].play();
    }

    public void toDie(Player p) {
        if (wuditime > 0) wuditime += 1;
        if (wuditime == FLASH_TIME) wuditime = 0;
        if (outlooking == 5) {
            dietime += 1;
            if (dietime == BEFORE_DIE_TIME && p == BattleJingji.p1) {
                Die();
                BattleJingji.p2.Win();
            }
            if (dietime == BEFORE_DIE_TIME && p == BattleJingji.p2) {
                Die();
                BattleJingji.p1.Win();
            }
            if (BattleJingji.p1.outlooking != 5 && p == BattleJingji.p2 && BattleJingji.p1.getHeng() == BattleJingji.p2.getHeng() && BattleJingji.p1.getShu() == BattleJingji.p2.getShu()) {
                BattleJingji.p2.Die();
                BattleJingji.p1.Win();
            }
            if (BattleJingji.p2.outlooking != 5 && p == BattleJingji.p1 && BattleJingji.p1.getHeng() == BattleJingji.p2.getHeng() && BattleJingji.p1.getShu() == BattleJingji.p2.getShu()) {
                BattleJingji.p1.Die();
                BattleJingji.p2.Win();
            }
        }
    }

    public void Die() {
        outlooking = 6;
        setNow();
        now = pdie;
        Music.music[7].play();
        Music.music[8].play();
    }

    public void Win() {
        outlooking = 7;
        setNow();
        now = pwin;
        Music.music[3].stop();
    }

    void beBack() {
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

    public void beZhu(Player p) {

        if (p == BattleJingji.p1 && (BattleJingji.p2.outlooking == 0 || BattleJingji.p2.outlooking == 1 ||
                BattleJingji.p2.outlooking == 2 || BattleJingji.p2.outlooking == 3 || BattleJingji.p2.outlooking == 4)) {
            BattleJingji.p2.outlooking = 4;
            if (BattleJingji.p2.now == BattleJingji.p2.s) {
                BattleJingji.p2.setNow();
                BattleJingji.p2.now = BattleJingji.p2.s;
            }
            if (BattleJingji.p2.now == BattleJingji.p2.w) {
                BattleJingji.p2.setNow();
                BattleJingji.p2.now = BattleJingji.p2.w;
            }
            if (BattleJingji.p2.now == BattleJingji.p2.a) {
                BattleJingji.p2.setNow();
                BattleJingji.p2.now = BattleJingji.p2.a;
            }
            if (BattleJingji.p2.now == BattleJingji.p2.d) {
                BattleJingji.p2.setNow();
                BattleJingji.p2.now = BattleJingji.p2.d;
            }
            BattleJingji.p2.wuditime = 1;
            BattleJingji.p2.bianshentime = 0;
        } else if (p == BattleJingji.p2 && (BattleJingji.p1.outlooking == 0 || BattleJingji.p1.outlooking == 1 ||
                BattleJingji.p1.outlooking == 2 || BattleJingji.p1.outlooking == 3 || BattleJingji.p1.outlooking == 4)) {
            BattleJingji.p1.outlooking = 4;
            if (BattleJingji.p1.now == BattleJingji.p1.s) {
                BattleJingji.p1.setNow();
                BattleJingji.p1.now = BattleJingji.p1.s;
            }
            if (BattleJingji.p1.now == BattleJingji.p1.w) {
                BattleJingji.p1.setNow();
                BattleJingji.p1.now = BattleJingji.p1.w;
            }
            if (BattleJingji.p1.now == BattleJingji.p1.a) {
                BattleJingji.p1.setNow();
                BattleJingji.p1.now = BattleJingji.p1.a;
            }
            if (BattleJingji.p1.now == BattleJingji.p1.d) {
                BattleJingji.p1.setNow();
                BattleJingji.p1.now = BattleJingji.p1.d;
            }
            BattleJingji.p1.wuditime = 1;
            BattleJingji.p1.bianshentime = 0;
        } else if (p == BattleAI.p1 && (BattleAI.p1.outlooking == 0 || BattleAI.p1.outlooking == 1 ||
                BattleAI.p1.outlooking == 2 || BattleAI.p1.outlooking == 3 || BattleAI.p1.outlooking == 4)) {
            BattleAI.p1.outlooking = 4;
            if (BattleAI.p1.now == BattleAI.p1.s) {
                BattleAI.p1.setNow();
                BattleAI.p1.now = BattleAI.p1.s;
            }
            if (BattleAI.p1.now == BattleAI.p1.w) {
                BattleAI.p1.setNow();
                BattleAI.p1.now = BattleAI.p1.w;
            }
            if (BattleAI.p1.now == BattleAI.p1.a) {
                BattleAI.p1.setNow();
                BattleAI.p1.now = BattleAI.p1.a;
            }
            if (BattleAI.p1.now == BattleAI.p1.d) {
                BattleAI.p1.setNow();
                BattleAI.p1.now = BattleAI.p1.d;
            }
            BattleAI.p1.bianshentime = 0;
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

    void beExp() {
        if (Map.isExp(getHeng(), getShu()) && outlooking == 0 && wuditime == 0) kunzhu();
        if (Map.isExp(getHeng(), getShu()) && (outlooking == 1 || outlooking == 2 || outlooking == 3 || outlooking == 4) && wuditime == 0) {
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

    public ImageIcon getBall() {
        return ball;
    }

    public int getHeng() {
        return X / 50;
    }

    public int getShu() {
        return Y / 50;
    }

    int getx() {
        if (outlooking == 1) return X - 25;
        else if (outlooking == 2) return X - 30;
        else if (outlooking == 3) return X - 25;
        else if (outlooking == 4) return X - 55;
        else return X - 43 + BattleJingji.jiangeheng;
    }

    int gety() {
        if (outlooking == 1) return Y - 75;
        else if (outlooking == 2) return Y - 48;
        else if (outlooking == 3) return Y - 55;
        else if (outlooking == 4) return Y - 80;
        else return Y - 73 + BattleJingji.jiangeshu;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    boolean cangow() {
        if (outlooking <= 4) {
            boolean wall = false;
            boolean ball = false;
            boolean bianjie = false;
            if (Y >= 6)
                bianjie = true;
            if ((getShu() - 1 >= 0 && !Map.isWall(getHeng(), getShu() - 1))
                    || (getShu() - 1 >= 0 && Map.isWall(getHeng(), getShu() - 1) && Y > ((getShu() - 1) * 50 + 65))
                    || (getShu() - 1 < 0 && !Map.isWall(getHeng(), getShu())))
                wall = true;
            if ((getShu() - 1 >= 0 && !Map.isBoom(getHeng(), getShu() - 1))
                    || (getShu() - 1 >= 0 && Map.isBoom(getHeng(), getShu() - 1) && Y > ((getShu() - 1) * 50 + 65))
                    || (getShu() - 1 < 0 && !Map.isBoom(getHeng(), getShu())))
                ball = true;

            return (wall && ball && bianjie);

        } else return false;
    }

    boolean cangoa() {
        if (outlooking <= 4) {
            boolean wall = false;
            boolean ball = false;
            boolean bianjie = false;
            if (X >= 6)
                bianjie = true;
            if ((getHeng() - 1 >= 0 && !Map.isBoom(getHeng() - 1, getShu()))
                    || (getHeng() - 1 >= 0 && Map.isBoom(getHeng() - 1, getShu()) && X > (getHeng() - 1) * 50 + 65)
                    || (getHeng() - 1 < 0 && !Map.isBoom(getHeng(), getShu())))
                ball = true;
            if ((getHeng() - 1 >= 0 && !Map.isWall(getHeng() - 1, getShu()))
                    || (getHeng() - 1 >= 0 && Map.isWall(getHeng() - 1, getShu()) && X > (getHeng() - 1) * 50 + 65)
                    || (getHeng() - 1 < 0 && !Map.isWall(getHeng(), getShu())))
                wall = true;
            return (wall && ball && bianjie);
        } else return false;
    }

    boolean cangos() {
        if (outlooking <= 4) {
            boolean wall = false;
            boolean ball = false;
            boolean bianjie = false;
            if (Y <= 394)
                bianjie = true;
            if ((getShu() + 1 <= 7 && !Map.isBoom(getHeng(), getShu() + 1))
                    || (getShu() + 1 <= 7 && Map.isBoom(getHeng(), getShu() + 1) && Y < ((getShu() + 1) * 50 - 15))
                    || (getShu() + 1 > 7 && !Map.isBoom(getHeng(), getShu())))
                ball = true;
            if ((getShu() + 1 <= 7 && !Map.isWall(getHeng(), getShu() + 1))
                    || (getShu() + 1 <= 7 && Map.isWall(getHeng(), getShu() + 1) && Y < ((getShu() + 1) * 50 - 15))
                    || (getShu() + 1 > 7 && !Map.isWall(getHeng(), getShu())))
                wall = true;
            return (wall && ball && bianjie);
        } else return false;
    }

    boolean cangod() {
        if (outlooking <= 4) {
            boolean wall = false;
            boolean ball = false;
            boolean bianjie = false;
            if (X <= 644)
                bianjie = true;
            if ((getHeng() + 1 <= 12 && !Map.isBoom(getHeng() + 1, getShu()))
                    || (getHeng() + 1 <= 12 && Map.isBoom(getHeng() + 1, getShu()) && X < (getHeng() + 1) * 50 - 15)
                    || (getHeng() + 1 > 12 && !Map.isBoom(getHeng(), getShu())))
                ball = true;
            if ((getHeng() + 1 <= 12 && !Map.isWall(getHeng() + 1, getShu()))
                    || (getHeng() + 1 <= 12 && Map.isWall(getHeng() + 1, getShu()) && X < (getHeng() + 1) * 50 - 15)
                    || (getHeng() + 1 > 12 && !Map.isWall(getHeng(), getShu())))
                wall = true;
            return (wall && ball && bianjie);
        } else return false;
    }
}
