package player;

import constants.GameConstants;
import element.Item;
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
    private int usedBallCount = 0;

    //各种道具的最大量
    public static final int MAX_BALL_CAPACITY = 10, MAX_BALL_POWER = 8, MAX_SPEED = 3, MAX_FORK_NUMBER = 3;

    //各种道具的持有量  收集用这个
    int namount = 2, npower = 1, nspeed = 1;
    //各种道具的持有量 最终显示效果用这个改变这个
    int amount = 2, power = 1, speed = 1, fork = 0;

    //各个时期的玩家图片
    public ImageIcon currentPlayerIcon;
    public ImageIcon currentGoUpIcon, currentGoLeftIcon, currentGoDownIcon, currentGoRightIcon;
    public ImageIcon originGoLeftIcon, originGoDownIcon, orginGoRightIcon, originGoUpIcon;
    public ImageIcon originGoLeftFlashIcon, originGoDownFlashIcon, orginGoRightFlashIcon, originGoUpFlashIcon;
    public ImageIcon stuckIcon, deadIcon, winningIcon;

    //特效之后的样子
    private ImageIcon windGoUpIcon, windGoLeftIcon, windGoDownIcon, windGoRightIcon;
    private ImageIcon candyGoUpIcon, candyGoLeftIcon, candyGoDownIcon, candyGoRightIcon;
    private ImageIcon ghostGoUpIcon, ghostGoLeftIcon, ghostGoDownIcon, ghostGoRightIcon;
    private ImageIcon foxGoUpIcon, foxGoLeftIcon, foxGoDownIcon, foxGoRightIcon;
    //闪图
    private ImageIcon windGoUpFlashIcon, windGoLeftFlashIcon, windGoDownFlashIcon, windGoRightFlashIcon;
    private ImageIcon candyGoUpFlashIcon, candyGoLeftFlashIcon, candyGoDownFlashIcon, candyGoRightFlashIcon;
    private ImageIcon ghostGoUpFlashIcon, ghostGoLeftFlashIcon, ghostGoDownFlashIcon, ghostGoRightFlashIcon;
    private ImageIcon foxGoUpFlashIcon, foxGoLeftFlashIcon, foxGoDownFlashIcon, foxGoRightFlashIcon;


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
    int transformTime = 0;
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
    public static final int FLASH_MAX_TIME = 300;

    private int playerNumber;

    public int getPlayerNumber() {
        return playerNumber;
    }

    public Player(int playNumber, Maps maps) {
        this.playerNumber = playNumber;
        this.maps = maps;

        windGoUpIcon = new ImageIcon("fengw.gif");
        windGoDownIcon = new ImageIcon("fengs.gif");
        windGoLeftIcon = new ImageIcon("fenga.gif");
        windGoRightIcon = new ImageIcon("fengd.gif");
        windGoUpFlashIcon = new ImageIcon("sfengw.gif");
        windGoDownFlashIcon = new ImageIcon("sfengs.gif");
        windGoLeftFlashIcon = new ImageIcon("sfenga.gif");
        windGoRightFlashIcon = new ImageIcon("sfengd.gif");

        candyGoUpIcon = new ImageIcon("paow.gif");
        candyGoLeftIcon = new ImageIcon("paoa.gif");
        candyGoDownIcon = new ImageIcon("paos.gif");
        candyGoRightIcon = new ImageIcon("paod.gif");
        candyGoUpFlashIcon = new ImageIcon("spaow.gif");
        candyGoLeftFlashIcon = new ImageIcon("spaoa.gif");
        candyGoDownFlashIcon = new ImageIcon("spaos.gif");
        candyGoRightFlashIcon = new ImageIcon("spaod.gif");

        ghostGoUpIcon = new ImageIcon("guiw.gif");
        ghostGoLeftIcon = new ImageIcon("guia.gif");
        ghostGoDownIcon = new ImageIcon("guis.gif");
        ghostGoRightIcon = new ImageIcon("guid.gif");
        ghostGoUpFlashIcon = new ImageIcon("sguiw.gif");
        ghostGoLeftFlashIcon = new ImageIcon("sguia.gif");
        ghostGoDownFlashIcon = new ImageIcon("sguis.gif");
        ghostGoRightFlashIcon = new ImageIcon("sguid.gif");

        foxGoUpIcon = new ImageIcon("zhuw.gif");
        foxGoLeftIcon = new ImageIcon("zhua.gif");
        foxGoDownIcon = new ImageIcon("zhus.gif");
        foxGoRightIcon = new ImageIcon("zhud.gif");
        foxGoUpFlashIcon = new ImageIcon("szhuw.gif");
        foxGoLeftFlashIcon = new ImageIcon("szhua.gif");
        foxGoDownFlashIcon = new ImageIcon("szhus.gif");
        foxGoRightFlashIcon = new ImageIcon("szhud.gif");
    }

    //决定现在的一套wasd
    public void setIconsByOutlooking() {
        switch (outlooking) {
            case OUTLOOKING_ORIGIN:
                if (flashTime == 0) {
                    currentGoDownIcon = originGoDownIcon;
                    currentGoUpIcon = originGoUpIcon;
                    currentGoLeftIcon = originGoLeftIcon;
                    currentGoRightIcon = orginGoRightIcon;
                    break;
                } else {
                    currentGoDownIcon = originGoDownFlashIcon;
                    currentGoUpIcon = originGoUpFlashIcon;
                    currentGoLeftIcon = originGoLeftFlashIcon;
                    currentGoRightIcon = orginGoRightFlashIcon;
                    break;
                }
            case OUTLOOKING_WIND:
                if (flashTime == 0) {
                    currentGoDownIcon = windGoDownIcon;
                    currentGoUpIcon = windGoUpIcon;
                    currentGoLeftIcon = windGoLeftIcon;
                    currentGoRightIcon = windGoRightIcon;
                    break;
                } else {
                    currentGoDownIcon = windGoDownFlashIcon;
                    currentGoUpIcon = windGoUpFlashIcon;
                    currentGoLeftIcon = windGoLeftFlashIcon;
                    currentGoRightIcon = windGoRightFlashIcon;
                    break;
                }
            case OUTLOOKING_CANDY:
                if (flashTime == 0) {
                    currentGoDownIcon = candyGoDownIcon;
                    currentGoUpIcon = candyGoUpIcon;
                    currentGoLeftIcon = candyGoLeftIcon;
                    currentGoRightIcon = candyGoRightIcon;
                    break;
                } else {
                    currentGoDownIcon = candyGoDownFlashIcon;
                    currentGoUpIcon = candyGoUpFlashIcon;
                    currentGoLeftIcon = candyGoLeftFlashIcon;
                    currentGoRightIcon = candyGoRightFlashIcon;
                    break;
                }
            case OUTLOOKING_GHOST:
                if (flashTime == 0) {
                    currentGoDownIcon = ghostGoDownIcon;
                    currentGoUpIcon = ghostGoUpIcon;
                    currentGoLeftIcon = ghostGoLeftIcon;
                    currentGoRightIcon = ghostGoRightIcon;
                    break;
                } else {
                    currentGoDownIcon = ghostGoDownFlashIcon;
                    currentGoUpIcon = ghostGoUpFlashIcon;
                    currentGoLeftIcon = ghostGoLeftFlashIcon;
                    currentGoRightIcon = ghostGoRightFlashIcon;
                    break;
                }
            case OUTLOOKING_FOX:
                if (flashTime == 0) {
                    currentGoDownIcon = foxGoDownIcon;
                    currentGoUpIcon = foxGoUpIcon;
                    currentGoLeftIcon = foxGoLeftIcon;
                    currentGoRightIcon = foxGoRightIcon;
                    break;
                } else {
                    currentGoDownIcon = foxGoDownFlashIcon;
                    currentGoUpIcon = foxGoUpFlashIcon;
                    currentGoLeftIcon = foxGoLeftFlashIcon;
                    currentGoRightIcon = foxGoRightFlashIcon;
                    break;
                }
            case OUTLOOKING_STUCK:
                currentGoDownIcon = stuckIcon;
                currentGoUpIcon = stuckIcon;
                currentGoLeftIcon = stuckIcon;
                currentGoRightIcon = stuckIcon;
                break;
            case OUTLOOKING_LOSER:
                currentGoDownIcon = deadIcon;
                currentGoUpIcon = deadIcon;
                currentGoLeftIcon = deadIcon;
                currentGoRightIcon = deadIcon;
                break;

            case OUTLOOKING_WINNER:
                currentGoDownIcon = winningIcon;
                currentGoUpIcon = winningIcon;
                currentGoLeftIcon = winningIcon;
                currentGoRightIcon = winningIcon;
                break;
        }
    }

    public void move() {
        if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT && !RIGHT && !LEFT && !DOWN && !UP) {
            if (currentPlayerIcon == currentGoDownIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoDownIcon;
            }
            if (currentPlayerIcon == currentGoUpIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoUpIcon;
            }
            if (currentPlayerIcon == currentGoLeftIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoLeftIcon;
            }
            if (currentPlayerIcon == currentGoRightIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoRightIcon;
            }
        }
        setIconsByOutlooking();
        if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT && outlooking != OUTLOOKING_GHOST) {
            if (outlooking != OUTLOOKING_WIND) {
                speed = nspeed;
            }
            if (RIGHT && RightInterruptCount == 0) {
                currentPlayerIcon = currentGoRightIcon;
                if (canGoRight()) judgeXPosition += speed;
                return;
            }
            if (LEFT && LeftInterruptCount == 0) {
                currentPlayerIcon = currentGoLeftIcon;
                if (canGoLeft()) judgeXPosition -= speed;
                return;
            }
            if (DOWN && DownInterruptCount == 0) {
                currentPlayerIcon = currentGoDownIcon;
                if (canGoDown()) judgeYPosition += speed;
                return;
            }
            if (UP && UpInterruptCount == 0) {
                currentPlayerIcon = currentGoUpIcon;
                if (canGoUp()) judgeYPosition -= speed;
                return;
            }
        }
        if (outlooking == OUTLOOKING_GHOST) {
            if (LEFT && RightInterruptCount == 0) {
                if (canGoRight()) judgeXPosition += speed;
                currentPlayerIcon = currentGoRightIcon;
                return;
            }
            if (RIGHT && LeftInterruptCount == 0) {
                if (canGoLeft()) judgeXPosition -= speed;
                currentPlayerIcon = currentGoLeftIcon;
                return;
            }
            if (UP && DownInterruptCount == 0) {
                if (canGoDown()) judgeYPosition += speed;
                currentPlayerIcon = currentGoDownIcon;
                return;
            }
            if (DOWN && UpInterruptCount == 0) {
                if (canGoUp()) judgeYPosition -= speed;
                currentPlayerIcon = currentGoUpIcon;
            }
        }
    }

    public boolean canSetBall() {
        return (outlooking <= OUTLOOKING_CAN_SETBALL_UPPER_LIMIT);
    }

    public void beStuck() {
        outlooking = OUTLOOKING_STUCK;
        setIconsByOutlooking();
        currentPlayerIcon = stuckIcon;
    }

    void escape() {
        stuckTime = 0;
        outlooking = OUTLOOKING_ORIGIN;
        setIconsByOutlooking();
        currentPlayerIcon = currentGoDownIcon;
        MusicTool.music[1].play();
    }


    public void keepDoing(Player anotherPlayer) {
        move();
        dieIfPossible(anotherPlayer);
        pickupItem();
        beBombed();
        transformToOrigin();
    }

    public void dieIfPossible(Player anotherPlayer) {
        if (flashTime > 0) flashTime += 1;
        if (flashTime == FLASH_MAX_TIME) flashTime = 0;
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

    private void die() {
        outlooking = OUTLOOKING_LOSER;
        setIconsByOutlooking();
        currentPlayerIcon = deadIcon;
        MusicTool.music[7].play();
        MusicTool.music[8].play();
    }

    private void win() {
        outlooking = OUTLOOKING_WINNER;
        setIconsByOutlooking();
        currentPlayerIcon = winningIcon;
        MusicTool.music[3].stop();
    }

    public void transformToOrigin() {
        if (transformTime == TRANSFORM_MAX_TIME) {
            transformTime = 0;
            outlooking = OUTLOOKING_ORIGIN;
            if (currentPlayerIcon == currentGoDownIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoDownIcon;
            }
            if (currentPlayerIcon == currentGoUpIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoUpIcon;
            }
            if (currentPlayerIcon == currentGoLeftIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoLeftIcon;
            }
            if (currentPlayerIcon == currentGoRightIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoRightIcon;
            }
            amount = namount;
            power = npower;
            speed = nspeed;
        }
        if (transformTime == TRANSFORM_MAX_TIME + 1) {
            flashTime += 1;
            transformTime = 0;
            outlooking = OUTLOOKING_ORIGIN;
            if (currentPlayerIcon == currentGoDownIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoDownIcon;
            }
            if (currentPlayerIcon == currentGoUpIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoUpIcon;
            }
            if (currentPlayerIcon == currentGoLeftIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoLeftIcon;
            }
            if (currentPlayerIcon == currentGoRightIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoRightIcon;
            }
            amount = namount;
            power = npower;
            speed = nspeed;
        }
        if (outlooking >= OUTLOOKING_FIRST_TRANSFORM
                && outlooking <= OUTLOOKING_LAST_TRANSFORM) {
            transformTime += 1;
        }
    }

    private void transformToWind() {
        flashTime = 1;
        transformTime = 0;
        outlooking = OUTLOOKING_WIND;
        if (currentPlayerIcon == currentGoDownIcon) {
            setIconsByOutlooking();
            currentPlayerIcon = currentGoDownIcon;
        }
        if (currentPlayerIcon == currentGoUpIcon) {
            setIconsByOutlooking();
            currentPlayerIcon = currentGoUpIcon;
        }
        if (currentPlayerIcon == currentGoLeftIcon) {
            setIconsByOutlooking();
            currentPlayerIcon = currentGoLeftIcon;
        }
        if (currentPlayerIcon == currentGoRightIcon) {
            setIconsByOutlooking();
            currentPlayerIcon = currentGoRightIcon;
        }
        speed = MAX_SPEED;
    }

    private void transformToCandy() {
        transformTime = 0;
        flashTime = 1;
        outlooking = 2;
        if (currentPlayerIcon == currentGoDownIcon) {
            setIconsByOutlooking();
            currentPlayerIcon = currentGoDownIcon;
        }
        if (currentPlayerIcon == currentGoUpIcon) {
            setIconsByOutlooking();
            currentPlayerIcon = currentGoUpIcon;
        }
        if (currentPlayerIcon == currentGoLeftIcon) {
            setIconsByOutlooking();
            currentPlayerIcon = currentGoLeftIcon;
        }
        if (currentPlayerIcon == currentGoRightIcon) {
            setIconsByOutlooking();
            currentPlayerIcon = currentGoRightIcon;
        }
        amount = MAX_BALL_CAPACITY;
    }

    private void transformToGhost() {
        transformTime = 0;
        flashTime = 1;
        outlooking = 3;
        if (currentPlayerIcon == currentGoDownIcon) {
            setIconsByOutlooking();
            currentPlayerIcon = currentGoDownIcon;
        }
        if (currentPlayerIcon == currentGoUpIcon) {
            setIconsByOutlooking();
            currentPlayerIcon = currentGoUpIcon;
        }
        if (currentPlayerIcon == currentGoLeftIcon) {
            setIconsByOutlooking();
            currentPlayerIcon = currentGoLeftIcon;
        }
        if (currentPlayerIcon == currentGoRightIcon) {
            setIconsByOutlooking();
            currentPlayerIcon = currentGoRightIcon;
        }
        amount = MAX_BALL_CAPACITY;
        power = MAX_BALL_POWER;
        speed = MAX_SPEED;
    }

    public void transformToFox() {
        if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT) {
            outlooking = OUTLOOKING_FOX;
            if (currentPlayerIcon == currentGoDownIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoDownIcon;
            } else if (currentPlayerIcon == currentGoUpIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoUpIcon;
            } else if (currentPlayerIcon == currentGoLeftIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoLeftIcon;
            } else if (currentPlayerIcon == currentGoRightIcon) {
                setIconsByOutlooking();
                currentPlayerIcon = currentGoRightIcon;
            }
            transformTime = 0;
        }
    }

    public void pickupItem() {
        if (maps.isItem(getHeng(), getShu())) {
            switch (maps.getItem(getHeng(), getShu()).getItemType()) {
                case Item.ITEM_BALL: {
                    addBallCapacity();
                    break;
                }
                case Item.ITEM_POWER: {
                    addBallPower();
                    break;
                }
                case Item.ITEM_SHOES: {
                    addSpeed();
                    break;
                }
                case Item.ITEM_FORK: {
                    addFork();
                    break;
                }
                case Item.ITEM_WIND: {
                    transformToWind();
                    break;
                }
                case Item.ITEM_CANDY: {
                    transformToCandy();
                    break;
                }
                case Item.ITEM_GHOST: {
                    transformToGhost();
                    break;
                }
                case Item.ITEM_FOX: {
                    transformToFox();
                    break;
                }
            }
            maps.removeItem(getHeng(),getShu());
            MusicTool.music[4].stop();
            MusicTool.music[4].play();
        }
    }

    public void beBombed() {
        if (maps.isExplosion(getHeng(), getShu()) && outlooking == OUTLOOKING_ORIGIN && flashTime == 0) {
            beStuck();
        }
        if (maps.isExplosion(getHeng(), getShu())
                && (outlooking >= OUTLOOKING_FIRST_TRANSFORM
                    && outlooking <= OUTLOOKING_LAST_TRANSFORM)
                && flashTime == 0) {
            transformTime = TRANSFORM_MAX_TIME + 1;
            transformToOrigin();
        }
    }

    private void addBallCapacity() {
        if (namount < MAX_BALL_CAPACITY)
            namount += 1;
    }

    private void addBallPower() {
        if (npower < MAX_BALL_POWER)
            npower += 1;
    }

    private void addSpeed() {
        if (nspeed < MAX_SPEED)
            nspeed += 1;
    }

    private void addFork() {
        if (fork < MAX_FORK_NUMBER)
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
            if ((getShu() - 1 >= 0 && !maps.isBall(getHeng(), getShu() - 1))
                    || (getShu() - 1 >= 0 && maps.isBall(getHeng(), getShu() - 1) && judgeYPosition > ((getShu() - 1) * 50 + 65))
                    || (getShu() - 1 < 0 && !maps.isBall(getHeng(), getShu())))
                ball = true;

            return (wall && ball && isBorder);

        } else return false;
    }

    public boolean canGoLeft() {
        if (outlooking <= 4) {
            boolean wall = false;
            boolean ball = false;
            boolean isBorder = false;
            if (judgeXPosition >= 6)
                isBorder = true;
            if ((getHeng() - 1 >= 0 && !maps.isBall(getHeng() - 1, getShu()))
                    || (getHeng() - 1 >= 0 && maps.isBall(getHeng() - 1, getShu())
                        && judgeXPosition > (getHeng() - 1) * 50 + 65)
                    || (getHeng() - 1 < 0 && !maps.isBall(getHeng(), getShu())))
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
            if ((getShu() + 1 <= 7 && !maps.isBall(getHeng(), getShu() + 1))
                    || (getShu() + 1 <= 7 && maps.isBall(getHeng(), getShu() + 1) && judgeYPosition < ((getShu() + 1) * 50 - 15))
                    || (getShu() + 1 > 7 && !maps.isBall(getHeng(), getShu())))
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
            if ((getHeng() + 1 <= 12 && !maps.isBall(getHeng() + 1, getShu()))
                    || (getHeng() + 1 <= 12 && maps.isBall(getHeng() + 1, getShu()) && judgeXPosition < (getHeng() + 1) * 50 - 15)
                    || (getHeng() + 1 > 12 && !maps.isBall(getHeng(), getShu())))
                ball = true;
            if ((getHeng() + 1 <= 12 && !maps.isWall(getHeng() + 1, getShu()))
                    || (getHeng() + 1 <= 12 && maps.isWall(getHeng() + 1, getShu()) && judgeXPosition < (getHeng() + 1) * 50 - 15)
                    || (getHeng() + 1 > 12 && !maps.isWall(getHeng(), getShu())))
                wall = true;
            return (wall && ball && isBorder);
        } else return false;
    }

    public void subUsedBallCount() {
        --usedBallCount;
    }

    public void addUsedBallCount() {
        ++usedBallCount;
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
                if (canSetBall() && isUsingBallBtn == false && usedBallCount < amount
                        && !maps.isBall(getHeng(), getShu())) {
                    setBall();
                }
                isUsingBallBtn = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_M && isUsingForkBtn == false) {
                if (fork > 0 && outlooking == OUTLOOKING_STUCK) {
                    fork -= 1;
                    escape();
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
                if (canSetBall() && isUsingBallBtn == false && usedBallCount < amount
                        && !maps.isBall(getHeng(), getShu())) {
                    setBall();
                }
                isUsingBallBtn = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_W && isUsingForkBtn == false) {
                if (fork > 0 && outlooking == OUTLOOKING_STUCK) {
                    fork -= 1;
                    escape();
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
