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
    public int pinXPosition, pinYPosition, judgeXPosition, judgeYPosition;

    //方向按键的值
    boolean UP = false, DOWN = false, LEFT = false, RIGHT = false;
    //被打断的次数
    int UpInterruptCount = 0, DownInterruptCount = 0, LeftInterruptCount = 0, RightInterruptCount = 0;
    //按键的松开值
    boolean isUpReleased = true, isDownReleased = true, isLeftReleased = true, isRightReleased = true;

    //判断是否按下一个键（为流畅的走位） 是否在使用叉子   判断是否被困住  是否变身
    boolean isUsingBallBtn = false, isUsingForkBtn = false;
    //困住 死亡
    protected boolean kunzhu = false, die = false;
    //变身总时间
    private static final int TRANSFORM_MAX_TIME = 1000;
    //计算变身时间
    int bianshentime = 0;
    //变成什么啦？     0原来的样子 1风 2泡 3鬼 4猪   5困住了  6死掉啦  7胜利啦
    public int outlooking = 0;
    public static final int OUTLOOKING_ORIGIN = 0;

    public static final int OUTLOOKING_FIRST_TRANSFORM = 1;
    public static final int OUTLOOKING_FIRST_SUPER = 1;
    public static final int OUTLOOKING_WIND = 1;
    public static final int OUTLOOKING_CANDY = 2;
    public static final int OUTLOOKING_GHOST = 3;
    public static final int OUTLOOKING_LAST_SUPER = 3;
    public static final int OUTLOOKING_CAN_SETBALL_UPPER_LIMIT = 3;

    public static final int OUTLOOKING_FOX = 4;
    public static final int OUTLOOKING_CAN_MOVE_UPPER_LIMIT = 4;
    public static final int OUTLOOKING_LAST_TRANSFORM = 4;

    public static final int OUTLOOKING_STUCK = 5;
    public static final int OUTLOOKING_LOSER = 6;
    public static final int OUTLOOKING_WINNER = 7;

    //判断死神的秒表
    int stuckTime;
    public static final int BEFORE_DIE_TIME = 600;
    //无敌时间
    int flashTime = 0;
    public static final int FLASH_TIME = 300;

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
            case OUTLOOKING_ORIGIN:
                if (flashTime == 0) {
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
            case OUTLOOKING_WIND:
                if (flashTime == 0) {
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
            case OUTLOOKING_CANDY:
                if (flashTime == 0) {
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
            case OUTLOOKING_GHOST:
                if (flashTime == 0) {
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
            case OUTLOOKING_FOX:
                if (flashTime == 0) {
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
            case OUTLOOKING_STUCK:
                s = pkunzhu;
                w = pkunzhu;
                a = pkunzhu;
                d = pkunzhu;
                break;
            case OUTLOOKING_LOSER:
                s = pdie;
                w = pdie;
                a = pdie;
                d = pdie;
                break;

            case OUTLOOKING_WINNER:
                s = pwin;
                w = pwin;
                a = pwin;
                d = pwin;
                break;
        }
    }

    public void move() {
        if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT && !RIGHT && !LEFT && !DOWN && !UP) {
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
        if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT && outlooking != OUTLOOKING_GHOST) {
            if (outlooking != OUTLOOKING_WIND) {
                speed = nspeed;
            }
            if (RIGHT && RightInterruptCount == 0) {
                now = d;
                if (canGoRight()) judgeXPosition += speed;
                return;
            }
            if (LEFT && LeftInterruptCount == 0) {
                now = a;
                if (cangGoLeft()) judgeXPosition -= speed;
                return;
            }
            if (DOWN && DownInterruptCount == 0) {
                now = s;
                if (canGoDown()) judgeYPosition += speed;
                return;
            }
            if (UP && UpInterruptCount == 0) {
                now = w;
                if (canGoUp()) judgeYPosition -= speed;
                return;
            }
        }
        if (outlooking == OUTLOOKING_GHOST) {
            if (LEFT && RightInterruptCount == 0) {
                if (canGoRight()) judgeXPosition += speed;
                now = d;
                return;
            }
            if (RIGHT && LeftInterruptCount == 0) {
                if (cangGoLeft()) judgeXPosition -= speed;
                now = a;
                return;
            }
            if (UP && DownInterruptCount == 0) {
                if (canGoDown()) judgeYPosition += speed;
                now = s;
                return;
            }
            if (DOWN && UpInterruptCount == 0) {
                if (canGoUp()) judgeYPosition -= speed;
                now = w;
            }
        }
    }

    public boolean canspace() {
        return (outlooking <= OUTLOOKING_CAN_SETBALL_UPPER_LIMIT);
    }

    public void beStuck() {
        outlooking = OUTLOOKING_STUCK;
        setNow();
        now = pkunzhu;
    }

    void fuhuo() {

        stuckTime = 0;
        outlooking = OUTLOOKING_ORIGIN;
        setNow();
        now = s;
        MusicTool.music[1].play();
    }


    public void keepDoing(Player anotherPlayer) {
        move();
        dieIfPossible(anotherPlayer);
        eatDaoju();
        beBombed();
        beBack();
    }

    public void dieIfPossible(Player anotherPlayer) {
        if (flashTime > 0) flashTime += 1;
        if (flashTime == FLASH_TIME) flashTime = 0;
        if (outlooking == OUTLOOKING_STUCK) {
            stuckTime += 1;
            if (stuckTime == BEFORE_DIE_TIME) {
                die();
                anotherPlayer.win();
            } else if (anotherPlayer.outlooking != OUTLOOKING_STUCK
                    && getHeng() == anotherPlayer.getHeng()
                    && getShu() == anotherPlayer.getShu()) {
                die();
                anotherPlayer.win();
            }
        }
    }

    public void die() {
        outlooking = OUTLOOKING_LOSER;
        setNow();
        now = pdie;
        MusicTool.music[7].play();
        MusicTool.music[8].play();
    }

    public void win() {
        outlooking = OUTLOOKING_WINNER;
        setNow();
        now = pwin;
        MusicTool.music[3].stop();
    }

    public void beBack() {
        if (bianshentime == TRANSFORM_MAX_TIME) {
            bianshentime = 0;
            outlooking = OUTLOOKING_ORIGIN;
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
        if (bianshentime == TRANSFORM_MAX_TIME + 1) {
            flashTime += 1;
            bianshentime = 0;
            outlooking = OUTLOOKING_ORIGIN;
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
        if (outlooking >= OUTLOOKING_FIRST_TRANSFORM
                && outlooking <= OUTLOOKING_LAST_TRANSFORM) {
            bianshentime += 1;
        }
    }

    private void transformToWind() {
        flashTime = 1;
        bianshentime = 0;
        outlooking = OUTLOOKING_WIND;
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

    private void transformToCandy() {
        bianshentime = 0;
        flashTime = 1;
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

    private void transformToGhost() {
        bianshentime = 0;
        flashTime = 1;
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

    public void transformToFox() {
        if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT) {
            outlooking = OUTLOOKING_FOX;
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

    public void eatDaoju() {
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
                    transformToWind();
                    break;
                }
                case 9: {
                    transformToCandy();
                    break;
                }
                case 10: {
                    transformToGhost();
                    break;
                }
                case 11: {
                    transformToFox();
                    break;
                }
            }
            maps.getDaojuMap()[getHeng()][getShu()] = null;
            MusicTool.music[4].stop();
            MusicTool.music[4].play();
        }
    }

    public void beBombed() {
        if (maps.isExp(getHeng(), getShu()) && outlooking == OUTLOOKING_ORIGIN && flashTime == 0) {
            beStuck();
        }
        if (maps.isExp(getHeng(), getShu())
                && (outlooking >= OUTLOOKING_FIRST_TRANSFORM
                    && outlooking <= OUTLOOKING_LAST_TRANSFORM)
                && flashTime == 0) {
            bianshentime = TRANSFORM_MAX_TIME + 1;
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

    public ImageIcon getBallIcon() {
        return ballIcon;
    }

    public int getHeng() {
        return judgeXPosition / 50;
    }

    public int getShu() {
        return judgeYPosition / 50;
    }

    public int getx() {
        if (outlooking == OUTLOOKING_WIND) return judgeXPosition - 25;
        else if (outlooking == OUTLOOKING_CANDY) return judgeXPosition - 30;
        else if (outlooking == OUTLOOKING_GHOST) return judgeXPosition - 25;
        else if (outlooking == OUTLOOKING_FOX) return judgeXPosition - 55;
        else return judgeXPosition - 43 + BattleJingjiPanel.jiangeheng;
    }

    public int gety() {
        if (outlooking == OUTLOOKING_WIND) return judgeYPosition - 75;
        else if (outlooking == OUTLOOKING_CANDY) return judgeYPosition - 48;
        else if (outlooking == OUTLOOKING_GHOST) return judgeYPosition - 55;
        else if (outlooking == OUTLOOKING_FOX) return judgeYPosition - 80;
        else return judgeYPosition - 73 + BattleJingjiPanel.jiangeshu;
    }

    public int getJudgeXPosition() {
        return judgeXPosition;
    }

    public int getJudgeYPosition() {
        return judgeYPosition;
    }

    public void setJudgeXPosition(int judgeXPosition) {
        this.judgeXPosition = judgeXPosition;
    }

    public void setJudgeYPosition(int judgeYPosition) {
        this.judgeYPosition = judgeYPosition;
    }

    public boolean canGoUp() {
        if (outlooking <= 4) {
            boolean wall = false;
            boolean ball = false;
            boolean isBorder = false;
            if (judgeYPosition >= 6)
                isBorder = true;
            if ((getShu() - 1 >= 0 && !maps.isWall(getHeng(), getShu() - 1))
                    || (getShu() - 1 >= 0 && maps.isWall(getHeng(), getShu() - 1)
                        && judgeYPosition > ((getShu() - 1) * 50 + 65))
                    || (getShu() - 1 < 0 && !maps.isWall(getHeng(), getShu())))
                wall = true;
            if ((getShu() - 1 >= 0 && !maps.isBoom(getHeng(), getShu() - 1))
                    || (getShu() - 1 >= 0 && maps.isBoom(getHeng(), getShu() - 1) && judgeYPosition > ((getShu() - 1) * 50 + 65))
                    || (getShu() - 1 < 0 && !maps.isBoom(getHeng(), getShu())))
                ball = true;

            return (wall && ball && isBorder);

        } else return false;
    }

    public boolean cangGoLeft() {
        if (outlooking <= 4) {
            boolean wall = false;
            boolean ball = false;
            boolean isBorder = false;
            if (judgeXPosition >= 6)
                isBorder = true;
            if ((getHeng() - 1 >= 0 && !maps.isBoom(getHeng() - 1, getShu()))
                    || (getHeng() - 1 >= 0 && maps.isBoom(getHeng() - 1, getShu())
                        && judgeXPosition > (getHeng() - 1) * 50 + 65)
                    || (getHeng() - 1 < 0 && !maps.isBoom(getHeng(), getShu())))
                ball = true;
            if ((getHeng() - 1 >= 0 && !maps.isWall(getHeng() - 1, getShu()))
                    || (getHeng() - 1 >= 0 && maps.isWall(getHeng() - 1, getShu())
                        && judgeXPosition > (getHeng() - 1) * 50 + 65)
                    || (getHeng() - 1 < 0 && !maps.isWall(getHeng(), getShu())))
                wall = true;
            return (wall && ball && isBorder);
        } else return false;
    }

    public boolean canGoDown() {
        if (outlooking <= 4) {
            boolean wall = false;
            boolean ball = false;
            boolean isBorder = false;
            if (judgeYPosition <= 394)
                isBorder = true;
            if ((getShu() + 1 <= 7 && !maps.isBoom(getHeng(), getShu() + 1))
                    || (getShu() + 1 <= 7 && maps.isBoom(getHeng(), getShu() + 1) && judgeYPosition < ((getShu() + 1) * 50 - 15))
                    || (getShu() + 1 > 7 && !maps.isBoom(getHeng(), getShu())))
                ball = true;
            if ((getShu() + 1 <= 7 && !maps.isWall(getHeng(), getShu() + 1))
                    || (getShu() + 1 <= 7 && maps.isWall(getHeng(), getShu() + 1) && judgeYPosition < ((getShu() + 1) * 50 - 15))
                    || (getShu() + 1 > 7 && !maps.isWall(getHeng(), getShu())))
                wall = true;
            return (wall && ball && isBorder);
        } else return false;
    }

    public boolean canGoRight() {
        if (outlooking <= 4) {
            boolean wall = false;
            boolean ball = false;
            boolean isBorder = false;
            if (judgeXPosition <= 644)
                isBorder = true;
            if ((getHeng() + 1 <= 12 && !maps.isBoom(getHeng() + 1, getShu()))
                    || (getHeng() + 1 <= 12 && maps.isBoom(getHeng() + 1, getShu()) && judgeXPosition < (getHeng() + 1) * 50 - 15)
                    || (getHeng() + 1 > 12 && !maps.isBoom(getHeng(), getShu())))
                ball = true;
            if ((getHeng() + 1 <= 12 && !maps.isWall(getHeng() + 1, getShu()))
                    || (getHeng() + 1 <= 12 && maps.isWall(getHeng() + 1, getShu()) && judgeXPosition < (getHeng() + 1) * 50 - 15)
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
            if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        UpInterruptCount = 0;
                        isUpReleased = false;
                        if (isRightReleased == false && UP == false) RightInterruptCount++;
                        if (isLeftReleased == false && UP == false) LeftInterruptCount++;
                        if (isDownReleased == false && UP == false) DownInterruptCount++;
                        UP = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        DownInterruptCount = 0;
                        isDownReleased = false;
                        if (isRightReleased == false && DOWN == false) RightInterruptCount++;
                        if (isLeftReleased == false && DOWN == false) LeftInterruptCount++;
                        if (isUpReleased == false && DOWN == false) UpInterruptCount++;
                        DOWN = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        LeftInterruptCount = 0;
                        isLeftReleased = false;
                        if (isRightReleased == false && LEFT == false) RightInterruptCount++;
                        if (isUpReleased == false && LEFT == false) UpInterruptCount++;
                        if (isDownReleased == false && LEFT == false) DownInterruptCount++;
                        LEFT = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        RightInterruptCount = 0;
                        isRightReleased = false;
                        if (isUpReleased == false && RIGHT == false) UpInterruptCount++;
                        if (isLeftReleased == false && RIGHT == false) LeftInterruptCount++;
                        if (isDownReleased == false && RIGHT == false) DownInterruptCount++;
                        RIGHT = true;
                        break;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {

                if (outlooking == OUTLOOKING_ORIGIN || outlooking == OUTLOOKING_WIND) {
                    amount = namount;
                }
                if (outlooking == OUTLOOKING_ORIGIN || outlooking == OUTLOOKING_WIND
                        || outlooking == OUTLOOKING_CANDY) {
                    power = npower;
                }
                if (canspace() && isUsingBallBtn == false && count < amount
                        && maps.getBallMap()[getHeng()][getShu()] == null)
                    setBall();
                isUsingBallBtn = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_M && isUsingForkBtn == false) {
                if (fork > 0 && outlooking == OUTLOOKING_STUCK) {
                    fork -= 1;
                    fuhuo();
                }
                isUsingForkBtn = true;
            }
        } else {
            if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_R:
                        UpInterruptCount = 0;
                        isUpReleased = false;
                        if (isRightReleased == false && UP == false) RightInterruptCount++;
                        if (isLeftReleased == false && UP == false) LeftInterruptCount++;
                        if (isDownReleased == false && UP == false) DownInterruptCount++;
                        UP = true;
                        break;
                    case KeyEvent.VK_F:
                        DownInterruptCount = 0;
                        isDownReleased = false;
                        if (isRightReleased == false && DOWN == false) RightInterruptCount++;
                        if (isLeftReleased == false && DOWN == false) LeftInterruptCount++;
                        if (isUpReleased == false && DOWN == false) UpInterruptCount++;
                        DOWN = true;
                        break;
                    case KeyEvent.VK_D:
                        LeftInterruptCount = 0;
                        isLeftReleased = false;
                        if (isRightReleased == false && LEFT == false) RightInterruptCount++;
                        if (isUpReleased == false && LEFT == false) UpInterruptCount++;
                        if (isDownReleased == false && LEFT == false) DownInterruptCount++;
                        LEFT = true;
                        break;
                    case KeyEvent.VK_G:
                        RightInterruptCount = 0;
                        isRightReleased = false;
                        if (isUpReleased == false && RIGHT == false) UpInterruptCount++;
                        if (isLeftReleased == false && RIGHT == false) LeftInterruptCount++;
                        if (isDownReleased == false && RIGHT == false) DownInterruptCount++;
                        RIGHT = true;
                        break;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_Q) {
                if (outlooking == OUTLOOKING_ORIGIN || outlooking == OUTLOOKING_WIND) {
                    amount = namount;
                }
                if (outlooking == OUTLOOKING_ORIGIN || outlooking == OUTLOOKING_WIND
                        || outlooking == OUTLOOKING_CANDY) {
                    power = npower;
                }
                if (canspace() && isUsingBallBtn == false && count < amount
                        && maps.getBallMap()[getHeng()][getShu()] == null) {
                    setBall();
                }
                isUsingBallBtn = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_W && isUsingForkBtn == false) {
                if (fork > 0 && outlooking == OUTLOOKING_STUCK) {
                    fork -= 1;
                    fuhuo();
                }
                isUsingForkBtn = true;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (this.playerNumber == GameConstants.PLAYER1) {
            if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        isUpReleased = true;
                        if (RightInterruptCount > UpInterruptCount && UP == true) RightInterruptCount--;
                        if (LeftInterruptCount > UpInterruptCount && UP == true) LeftInterruptCount--;
                        if (DownInterruptCount > UpInterruptCount && UP == true) DownInterruptCount--;
                        UpInterruptCount = 0;
                        UP = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        isDownReleased = true;
                        if (RightInterruptCount > DownInterruptCount && DOWN == true) RightInterruptCount--;
                        if (LeftInterruptCount > DownInterruptCount && DOWN == true) LeftInterruptCount--;
                        if (UpInterruptCount > DownInterruptCount && DOWN == true) UpInterruptCount--;
                        DOWN = false;
                        DownInterruptCount = 0;
                        break;
                    case KeyEvent.VK_LEFT:
                        isLeftReleased = true;
                        if (RightInterruptCount > LeftInterruptCount && LEFT == true) RightInterruptCount--;
                        if (UpInterruptCount > LeftInterruptCount && LEFT == true) UpInterruptCount--;
                        if (DownInterruptCount > LeftInterruptCount && LEFT == true) DownInterruptCount--;
                        LEFT = false;
                        LeftInterruptCount = 0;
                        break;
                    case KeyEvent.VK_RIGHT:
                        isRightReleased = true;
                        if (UpInterruptCount > RightInterruptCount && RIGHT == true) UpInterruptCount--;
                        if (LeftInterruptCount > RightInterruptCount && RIGHT == true) LeftInterruptCount--;
                        if (DownInterruptCount > RightInterruptCount && RIGHT == true) DownInterruptCount--;
                        RIGHT = false;
                        RightInterruptCount = 0;
                        break;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) isUsingBallBtn = false;
            if (e.getKeyCode() == KeyEvent.VK_M) isUsingForkBtn = false;
        } else {
            if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_R:
                        isUpReleased = true;
                        if (RightInterruptCount > UpInterruptCount && UP == true) RightInterruptCount--;
                        if (LeftInterruptCount > UpInterruptCount && UP == true) LeftInterruptCount--;
                        if (DownInterruptCount > UpInterruptCount && UP == true) DownInterruptCount--;
                        UpInterruptCount = 0;
                        UP = false;
                        break;
                    case KeyEvent.VK_F:
                        isDownReleased = true;
                        if (RightInterruptCount > DownInterruptCount && DOWN == true) RightInterruptCount--;
                        if (LeftInterruptCount > DownInterruptCount && DOWN == true) LeftInterruptCount--;
                        if (UpInterruptCount > DownInterruptCount && DOWN == true) UpInterruptCount--;
                        DOWN = false;
                        DownInterruptCount = 0;
                        break;
                    case KeyEvent.VK_D:
                        isLeftReleased = true;
                        if (RightInterruptCount > LeftInterruptCount && LEFT == true) RightInterruptCount--;
                        if (UpInterruptCount > LeftInterruptCount && LEFT == true) UpInterruptCount--;
                        if (DownInterruptCount > LeftInterruptCount && LEFT == true) DownInterruptCount--;
                        LEFT = false;
                        LeftInterruptCount = 0;
                        break;
                    case KeyEvent.VK_G:
                        isRightReleased = true;
                        if (UpInterruptCount > RightInterruptCount && RIGHT == true) UpInterruptCount--;
                        if (LeftInterruptCount > RightInterruptCount && RIGHT == true) LeftInterruptCount--;
                        if (DownInterruptCount > RightInterruptCount && RIGHT == true) DownInterruptCount--;
                        RIGHT = false;
                        RightInterruptCount = 0;
                        break;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_Q) isUsingBallBtn = false;
            if (e.getKeyCode() == KeyEvent.VK_W) isUsingForkBtn = false;
        }

    }
}
