package player;

import constants.GameConstants;
import element.Item;
import element.Maps;
import element.Ball;
import element.MusicTool;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// TODO: 18/5/22  player 整理

public class Player implements KeyListener {




    /**
     * myself, anotherPlayer, maps
     */
    private int playerNumber;
    private Player anotherPlayer;
    public Maps maps = new Maps();

    /**
     * player attribute
     */
    public static final int MAX_BALL_CAPACITY = 7, MAX_BALL_POWER = 5, MAX_SPEED = 3, MAX_FORK_NUMBER = 3;
    private int realBallCapacity = 2, realBallPower = 1, realSpeed = 1;
    private int showBallCapacity = 2, showBallPower = 1, showSpeed = 1, forkNumber = 0;
    private int usedBallCount = 0;

    /**
     * player outlooking
     */
    public int outlooking = 0;
    public static final int OUTLOOKING_ORIGIN = 0;

    public static final int OUTLOOKING_FIRST_TRANSFORM = 1;
    public static final int OUTLOOKING_WIND = 1;
    public static final int OUTLOOKING_CANDY = 2;
    public static final int OUTLOOKING_GHOST = 3;
    public static final int OUTLOOKING_CAN_SETBALL_UPPER_LIMIT = 3;

    public static final int OUTLOOKING_FOX = 4;
    public static final int OUTLOOKING_CAN_MOVE_UPPER_LIMIT = 4;
    public static final int OUTLOOKING_LAST_TRANSFORM = 4;

    public static final int OUTLOOKING_STUCK = 5;
    public static final int OUTLOOKING_LOSER = 6;
    public static final int OUTLOOKING_WINNER = 7;

    /**
     * player and ball icon
     */
    public ImageIcon currentPlayerIcon;
    public ImageIcon currentGoUpIcon, currentGoLeftIcon, currentGoDownIcon, currentGoRightIcon;
    public ImageIcon stuckIcon, loseIcon, winningIcon;

    public ImageIcon originGoLeftIcon, originGoDownIcon, orginGoRightIcon, originGoUpIcon;
    public ImageIcon originGoLeftFlashIcon, originGoDownFlashIcon, orginGoRightFlashIcon, originGoUpFlashIcon;

    private ImageIcon windGoUpIcon, windGoLeftIcon, windGoDownIcon, windGoRightIcon;
    private ImageIcon candyGoUpIcon, candyGoLeftIcon, candyGoDownIcon, candyGoRightIcon;
    private ImageIcon ghostGoUpIcon, ghostGoLeftIcon, ghostGoDownIcon, ghostGoRightIcon;
    private ImageIcon foxGoUpIcon, foxGoLeftIcon, foxGoDownIcon, foxGoRightIcon;

    private ImageIcon windGoUpFlashIcon, windGoLeftFlashIcon, windGoDownFlashIcon, windGoRightFlashIcon;
    private ImageIcon candyGoUpFlashIcon, candyGoLeftFlashIcon, candyGoDownFlashIcon, candyGoRightFlashIcon;
    private ImageIcon ghostGoUpFlashIcon, ghostGoLeftFlashIcon, ghostGoDownFlashIcon, ghostGoRightFlashIcon;
    private ImageIcon foxGoUpFlashIcon, foxGoLeftFlashIcon, foxGoDownFlashIcon, foxGoRightFlashIcon;

    public ImageIcon ballIcon;

    /**
     * player transform time
     */
    int flashTime = 0;
    public static final int FLASH_MAX_TIME = 500;

    private int transformTime = 0;
    public static final int TRANSFORM_MAX_TIME = 1000;

    private int stuckTime;
    public static final int BEFORE_LOSE_TIME = 500;



    /**
     * player walk, set ball, use fork
     */
    public boolean isUpPressed = false, isDownPressed = false, isLeftPressed = false, isRightPressed = false;
    public boolean isBallBtnPressed = false, isForkBtnPressed = false;
    public boolean isUpReleased = true, isDownReleased = true, isLeftReleased = true, isRightReleased = true;
    public int UpInterruptCount = 0, DownInterruptCount = 0, LeftInterruptCount = 0, RightInterruptCount = 0;

    /**
     * player position
     */
    private int judgeXPosition, judgeYPosition;



    /**
     * getter
     */
    protected int getRealSpeed() {
        return realSpeed;
    }

    public Maps getMaps() {
        return maps;
    }

    protected int getStuckTime() {
        return stuckTime;
    }

    protected Player getAnotherPlayer() {
        return anotherPlayer;
    }

    protected int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * setter and operator
     */

    /**
     * protected methods
     */


    /**
     * private methods
     */
    private void setMaxAttribute() {
        this.realBallCapacity = MAX_BALL_CAPACITY;
        this.realBallPower = MAX_BALL_POWER;
        this.realSpeed = MAX_SPEED;

        this.showBallCapacity = MAX_BALL_CAPACITY;
        this.showBallPower = MAX_BALL_POWER;
        this.showSpeed = MAX_SPEED;
    }

    public void setAnotherPlayer(Player anotherPlayer) {
        this.anotherPlayer = anotherPlayer;
    }

    protected void incStuckTime() {
        stuckTime++;
    }

    /**
     * public methods
     */
    public Player(int playNumber, Maps maps) {
        this.playerNumber = playNumber;
        this.maps = maps;

        setBasicCharacterIcon();
        loadSuperCharacterIcon();
        setMaxAttributeIfNeeded();
    }


    private void setBasicCharacterIcon() {
        if (this.playerNumber == GameConstants.PLAYER1
                || this.playerNumber == GameConstants.PLAYER_HUMAN) {
            //judgeXPosition = 125;
            //judgeYPosition = 325;
            originGoDownIcon = new ImageIcon("战士下.gif");
            originGoUpIcon = new ImageIcon("战士上.gif");
            originGoLeftIcon = new ImageIcon("战士左.gif");
            orginGoRightIcon = new ImageIcon("战士右.gif");

            originGoDownFlashIcon = new ImageIcon("s战士下.gif");
            originGoUpFlashIcon = new ImageIcon("s战士上.gif");
            originGoLeftFlashIcon = new ImageIcon("s战士左.gif");
            orginGoRightFlashIcon = new ImageIcon("s战士右.gif");

            stuckIcon = new ImageIcon("炸弹火.gif");
            loseIcon = new ImageIcon("P1die.gif");
            winningIcon = new ImageIcon("P1win.gif");
            ballIcon = new ImageIcon("糖泡红.gif");


        } else if (this.playerNumber == GameConstants.PLAYER2
                || this.playerNumber == GameConstants.PLAYER_AI){
            //judgeXPosition = 125;
            //judgeYPosition = 75;
            originGoDownIcon = new ImageIcon("包子下.gif");
            originGoUpIcon = new ImageIcon("包子上.gif");
            originGoLeftIcon = new ImageIcon("包子左.gif");
            orginGoRightIcon = new ImageIcon("包子右.gif");

            originGoDownFlashIcon = new ImageIcon("s包子下.gif");
            originGoUpFlashIcon = new ImageIcon("s包子上.gif");
            originGoLeftFlashIcon = new ImageIcon("s包子左.gif");
            orginGoRightFlashIcon = new ImageIcon("s包子右.gif");

            stuckIcon = new ImageIcon("炸弹水.gif");
            loseIcon = new ImageIcon("P2die.gif");
            winningIcon = new ImageIcon("P2win.jpg");
            ballIcon = new ImageIcon("糖泡蓝.gif");

            setMaxAttributeIfNeeded();
        }
        currentPlayerIcon = originGoDownIcon;
    }


    private void setMaxAttributeIfNeeded() {
        if (this.playerNumber == GameConstants.PLAYER_HUMAN) {
            setMaxAttribute();
        } else if (this.playerNumber == GameConstants.PLAYER_AI) {
            setMaxAttribute();
        }
    }


    private void loadSuperCharacterIcon() {
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
                currentGoDownIcon = loseIcon;
                currentGoUpIcon = loseIcon;
                currentGoLeftIcon = loseIcon;
                currentGoRightIcon = loseIcon;
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
        if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT && !isRightPressed && !isLeftPressed && !isDownPressed && !isUpPressed) {
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
                showSpeed = realSpeed;
            }
            if (isRightPressed && RightInterruptCount == 0) {
                currentPlayerIcon = currentGoRightIcon;
                if (canGoRight()) judgeXPosition += showSpeed;
                return;
            }
            if (isLeftPressed && LeftInterruptCount == 0) {
                currentPlayerIcon = currentGoLeftIcon;
                if (canGoLeft()) judgeXPosition -= showSpeed;
                return;
            }
            if (isDownPressed && DownInterruptCount == 0) {
                currentPlayerIcon = currentGoDownIcon;
                if (canGoDown()) judgeYPosition += showSpeed;
                return;
            }
            if (isUpPressed && UpInterruptCount == 0) {
                currentPlayerIcon = currentGoUpIcon;
                if (canGoUp()) judgeYPosition -= showSpeed;
                return;
            }
        }
        if (outlooking == OUTLOOKING_GHOST) {
            if (isLeftPressed && LeftInterruptCount == 0) {
                if (canGoRight()) judgeXPosition += showSpeed;
                currentPlayerIcon = currentGoRightIcon;
                return;
            }
            if (isRightPressed && RightInterruptCount == 0) {
                if (canGoLeft()) judgeXPosition -= showSpeed;
                currentPlayerIcon = currentGoLeftIcon;
                return;
            }
            if (isUpPressed && UpInterruptCount == 0) {
                if (canGoDown()) judgeYPosition += showSpeed;
                currentPlayerIcon = currentGoDownIcon;
                return;
            }
            if (isDownPressed && DownInterruptCount == 0) {
                if (canGoUp()) judgeYPosition -= showSpeed;
                currentPlayerIcon = currentGoUpIcon;
            }
        }
        if (playerNumber == GameConstants.PLAYER_AI) {
            isUpPressed = false;
            isDownPressed = false;
            isLeftPressed = false;
            isRightPressed = false;
        }
    }

    public boolean canSetBall() {
        return (outlooking <= OUTLOOKING_CAN_SETBALL_UPPER_LIMIT);
    }

    public void beStuck() {
        outlooking = OUTLOOKING_STUCK;
        //setIconsByOutlooking();
        currentPlayerIcon = stuckIcon;
    }

    void escape() {
        stuckTime = 0;
        outlooking = OUTLOOKING_ORIGIN;
        //setIconsByOutlooking();
        currentPlayerIcon = currentGoDownIcon;
        MusicTool.ESCAPE.play();
    }


    public void keepDoing() {
        move();
        countTimeToLoseIfPossible();
        pickupItem();
        beBombed();
        transformToOriginIfPossible();
    }

    public void countTimeToLoseIfPossible() {
        if (flashTime > 0) flashTime += 1;
        if (flashTime == FLASH_MAX_TIME) flashTime = 0;
        if (outlooking == OUTLOOKING_STUCK) {
            stuckTime += 1;
            if (stuckTime == BEFORE_LOSE_TIME) {
                lose();
                anotherPlayer.win();
            } else if (anotherPlayer.outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT
                    && getHeng() == anotherPlayer.getHeng()
                    && getShu() == anotherPlayer.getShu()) {
                lose();
                anotherPlayer.win();
            }
        }
    }

    public void lose() {
        outlooking = OUTLOOKING_LOSER;
        //setIconsByOutlooking();
        currentPlayerIcon = loseIcon;

        MusicTool.PLAYER_EXPLODE.play();
    }

    public void win() {
        outlooking = OUTLOOKING_WINNER;
        //setIconsByOutlooking();
        currentPlayerIcon = winningIcon;
    }

    public void transformToOriginIfPossible() {
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
            showBallCapacity = realBallCapacity;
            showBallPower = realBallPower;
            showSpeed = realSpeed;
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
            showBallCapacity = realBallCapacity;
            showBallPower = realBallPower;
            showSpeed = realSpeed;
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
        showSpeed = MAX_SPEED;
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
        showBallCapacity = MAX_BALL_CAPACITY;
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
        showBallCapacity = MAX_BALL_CAPACITY;
        showBallPower = MAX_BALL_POWER;
        showSpeed = MAX_SPEED;
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
            MusicTool.PICKUP_ITEM.play();
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
            transformToOriginIfPossible();
        }
    }

    private void addBallCapacity() {
        if (realBallCapacity < MAX_BALL_CAPACITY)
            realBallCapacity += 1;
    }

    private void addBallPower() {
        if (realBallPower < MAX_BALL_POWER)
            realBallPower += 1;
    }

    private void addSpeed() {
        if (realSpeed < MAX_SPEED)
            realSpeed += 1;
    }

    private void addFork() {
        if (forkNumber < MAX_FORK_NUMBER)
            forkNumber += 1;
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
        else return judgeXPosition - 43;
    }

    public int gety() {
        if (outlooking == OUTLOOKING_WIND) return judgeYPosition - 75;
        else if (outlooking == OUTLOOKING_CANDY) return judgeYPosition - 48;
        else if (outlooking == OUTLOOKING_GHOST) return judgeYPosition - 55;
        else if (outlooking == OUTLOOKING_FOX) return judgeYPosition - 80;
        else return judgeYPosition - 73;
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

    public void decUsedBallCount() {
        usedBallCount--;
    }

    public void incUsedBallCount() {
        usedBallCount++;
    }

    public void setBall() {
        maps.setBall(new Ball(this, getHeng(), getShu(), showBallPower, getBallIcon(), maps));
        MusicTool.SET_BALL.play();
    }

    public int getShowBallPower() {
        return showBallPower;
    }

    //完美的行动派监听器！！！！！！！！！********************************************************************
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.playerNumber == GameConstants.PLAYER1 || this.playerNumber == GameConstants.PLAYER_HUMAN) {
            if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        UpInterruptCount = 0;
                        isUpReleased = false;
                        if (!isRightReleased && !isUpPressed) RightInterruptCount++;
                        if (!isLeftReleased && !isUpPressed) LeftInterruptCount++;
                        if (!isDownReleased && !isUpPressed) DownInterruptCount++;
                        isUpPressed = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        DownInterruptCount = 0;
                        isDownReleased = false;
                        if (!isRightReleased && !isDownPressed) RightInterruptCount++;
                        if (!isLeftReleased && !isDownPressed) LeftInterruptCount++;
                        if (!isUpReleased && !isDownPressed) UpInterruptCount++;
                        isDownPressed = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        LeftInterruptCount = 0;
                        isLeftReleased = false;
                        if (!isRightReleased && !isLeftPressed) RightInterruptCount++;
                        if (!isUpReleased && !isLeftPressed) UpInterruptCount++;
                        if (!isDownReleased && !isLeftPressed) DownInterruptCount++;
                        isLeftPressed = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        RightInterruptCount = 0;
                        isRightReleased = false;
                        if (isUpReleased == false && isRightPressed == false) UpInterruptCount++;
                        if (isLeftReleased == false && isRightPressed == false) LeftInterruptCount++;
                        if (isDownReleased == false && isRightPressed == false) DownInterruptCount++;
                        isRightPressed = true;
                        break;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {

                if (outlooking == OUTLOOKING_ORIGIN || outlooking == OUTLOOKING_WIND) {
                    showBallCapacity = realBallCapacity;
                }
                if (outlooking == OUTLOOKING_ORIGIN || outlooking == OUTLOOKING_WIND
                        || outlooking == OUTLOOKING_CANDY) {
                    showBallPower = realBallPower;
                }
                if (canSetBall() && !isBallBtnPressed && usedBallCount < showBallCapacity
                        && !maps.isBall(getHeng(), getShu())) {
                    setBall();
                }
                isBallBtnPressed = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_M && !isForkBtnPressed) {
                if (forkNumber > 0 && outlooking == OUTLOOKING_STUCK) {
                    forkNumber -= 1;
                    escape();
                }
                isForkBtnPressed = true;
            }
        } else {
            if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_R:
                        UpInterruptCount = 0;
                        isUpReleased = false;
                        if (!isRightReleased && !isUpPressed) RightInterruptCount++;
                        if (!isLeftReleased && !isUpPressed) LeftInterruptCount++;
                        if (!isDownReleased && !isUpPressed) DownInterruptCount++;
                        isUpPressed = true;
                        break;
                    case KeyEvent.VK_F:
                        DownInterruptCount = 0;
                        isDownReleased = false;
                        if (!isRightReleased && !isDownPressed) RightInterruptCount++;
                        if (!isLeftReleased && !isDownPressed) LeftInterruptCount++;
                        if (!isUpReleased && !isDownPressed) UpInterruptCount++;
                        isDownPressed = true;
                        break;
                    case KeyEvent.VK_D:
                        LeftInterruptCount = 0;
                        isLeftReleased = false;
                        if (!isRightReleased && !isLeftPressed) RightInterruptCount++;
                        if (!isUpReleased && !isLeftPressed) UpInterruptCount++;
                        if (!isDownReleased && !isLeftPressed) DownInterruptCount++;
                        isLeftPressed = true;
                        break;
                    case KeyEvent.VK_G:
                        RightInterruptCount = 0;
                        isRightReleased = false;
                        if (!isUpReleased && !isRightPressed) UpInterruptCount++;
                        if (!isLeftReleased && !isRightPressed) LeftInterruptCount++;
                        if (!isDownReleased && !isRightPressed) DownInterruptCount++;
                        isRightPressed = true;
                        break;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_Q) {
                if (outlooking == OUTLOOKING_ORIGIN || outlooking == OUTLOOKING_WIND) {
                    showBallCapacity = realBallCapacity;
                }
                if (outlooking == OUTLOOKING_ORIGIN || outlooking == OUTLOOKING_WIND
                        || outlooking == OUTLOOKING_CANDY) {
                    showBallPower = realBallPower;
                }
                if (canSetBall() && !isBallBtnPressed && usedBallCount < showBallCapacity
                        && !maps.isBall(getHeng(), getShu())) {
                    setBall();
                }
                isBallBtnPressed = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_W && !isForkBtnPressed) {
                if (forkNumber > 0 && outlooking == OUTLOOKING_STUCK) {
                    forkNumber -= 1;
                    escape();
                }
                isForkBtnPressed = true;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (this.playerNumber == GameConstants.PLAYER1 || this.playerNumber == GameConstants.PLAYER_HUMAN) {
            if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        isUpReleased = true;
                        if (RightInterruptCount > UpInterruptCount && isUpPressed) RightInterruptCount--;
                        if (LeftInterruptCount > UpInterruptCount && isUpPressed) LeftInterruptCount--;
                        if (DownInterruptCount > UpInterruptCount && isUpPressed) DownInterruptCount--;
                        UpInterruptCount = 0;
                        isUpPressed = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        isDownReleased = true;
                        if (RightInterruptCount > DownInterruptCount && isDownPressed) RightInterruptCount--;
                        if (LeftInterruptCount > DownInterruptCount && isDownPressed) LeftInterruptCount--;
                        if (UpInterruptCount > DownInterruptCount && isDownPressed) UpInterruptCount--;
                        isDownPressed = false;
                        DownInterruptCount = 0;
                        break;
                    case KeyEvent.VK_LEFT:
                        isLeftReleased = true;
                        if (RightInterruptCount > LeftInterruptCount && isLeftPressed) RightInterruptCount--;
                        if (UpInterruptCount > LeftInterruptCount && isLeftPressed) UpInterruptCount--;
                        if (DownInterruptCount > LeftInterruptCount && isLeftPressed) DownInterruptCount--;
                        isLeftPressed = false;
                        LeftInterruptCount = 0;
                        break;
                    case KeyEvent.VK_RIGHT:
                        isRightReleased = true;
                        if (UpInterruptCount > RightInterruptCount && isRightPressed) UpInterruptCount--;
                        if (LeftInterruptCount > RightInterruptCount && isRightPressed) LeftInterruptCount--;
                        if (DownInterruptCount > RightInterruptCount && isRightPressed) DownInterruptCount--;
                        isRightPressed = false;
                        RightInterruptCount = 0;
                        break;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) isBallBtnPressed = false;
            if (e.getKeyCode() == KeyEvent.VK_M) isForkBtnPressed = false;
        } else {
            if (outlooking <= OUTLOOKING_CAN_MOVE_UPPER_LIMIT) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_R:
                        isUpReleased = true;
                        if (RightInterruptCount > UpInterruptCount && isUpPressed) RightInterruptCount--;
                        if (LeftInterruptCount > UpInterruptCount && isUpPressed) LeftInterruptCount--;
                        if (DownInterruptCount > UpInterruptCount && isUpPressed) DownInterruptCount--;
                        UpInterruptCount = 0;
                        isUpPressed = false;
                        break;
                    case KeyEvent.VK_F:
                        isDownReleased = true;
                        if (RightInterruptCount > DownInterruptCount && isDownPressed) RightInterruptCount--;
                        if (LeftInterruptCount > DownInterruptCount && isDownPressed) LeftInterruptCount--;
                        if (UpInterruptCount > DownInterruptCount && isDownPressed) UpInterruptCount--;
                        isDownPressed = false;
                        DownInterruptCount = 0;
                        break;
                    case KeyEvent.VK_D:
                        isLeftReleased = true;
                        if (RightInterruptCount > LeftInterruptCount && isLeftPressed) RightInterruptCount--;
                        if (UpInterruptCount > LeftInterruptCount && isLeftPressed) UpInterruptCount--;
                        if (DownInterruptCount > LeftInterruptCount && isLeftPressed) DownInterruptCount--;
                        isLeftPressed = false;
                        LeftInterruptCount = 0;
                        break;
                    case KeyEvent.VK_G:
                        isRightReleased = true;
                        if (UpInterruptCount > RightInterruptCount && isRightPressed) UpInterruptCount--;
                        if (LeftInterruptCount > RightInterruptCount && isRightPressed) LeftInterruptCount--;
                        if (DownInterruptCount > RightInterruptCount && isRightPressed) DownInterruptCount--;
                        isRightPressed = false;
                        RightInterruptCount = 0;
                        break;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_Q) isBallBtnPressed = false;
            if (e.getKeyCode() == KeyEvent.VK_W) isForkBtnPressed = false;
        }

    }
}
