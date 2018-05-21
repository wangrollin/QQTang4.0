package player;

import constants.GameConstants;
import element.Ball;
import element.Daoju;
import element.Maps;
import element.MusicTool;
import panel.battle.BattleAIPanel;

import java.util.Random;

import javax.swing.ImageIcon;

public class AI extends Player {
    protected int amount = MAXamount, power = 5, speed = MAXspeed + 2;
    //protected int myheng, myshu, hengcount, shucount, findheng, findshu, ws, ad, way;
    private Random random;
    public AI(Maps maps) {
        super(GameConstants.PLAYER_AI, maps);

        ps = new ImageIcon("包子下.gif");
        pw = new ImageIcon("包子上.gif");
        pa = new ImageIcon("包子左.gif");
        pd = new ImageIcon("包子右.gif");

        sps = new ImageIcon("s包子下.gif");
        spw = new ImageIcon("s包子上.gif");
        spa = new ImageIcon("s包子左.gif");
        spd = new ImageIcon("s包子右.gif");

        pkunzhu = new ImageIcon("炸弹水.gif");
        pdie = new ImageIcon("P2die.gif");
        pwin = new ImageIcon("P2win.jpg");
        ballIcon = new ImageIcon("糖泡蓝.gif");
        now = ps;
        random = new Random();
    }


    public void doing() {
        eatDaoju();
        beexp();
    }

    //技能--斜泡    1继续执行 -1中断
    public int xiepao1(int i) {
        if (i >= 83) return -1;
        if (i % 21 == 0) {
            if (jall(getHeng(), getShu()) == true && !isbianjie(getHeng(), getShu())) setBall();
            else return -1;
        } else if (i % 21 >= 1 && i % 21 <= 10) {
            if (canGoRight()) goD();
            else return -1;
        } else if (i % 21 >= 11 && i % 21 <= 20) {
            if (canGoUp()) goW();
            else return -1;
        }
        return 1;
    }

    public int xiepao2(int i) {
        if (i >= 83) return -1;
        if (i % 21 == 0) {
            if (jall(getHeng(), getShu()) == true && !isbianjie(getHeng(), getShu())) setBall();
            else return -1;
        } else if (i % 21 >= 1 && i % 21 <= 10) {
            if (cangGoLeft()) goA();
            else return -1;
        } else if (i % 21 >= 11 && i % 21 <= 20) {
            if (canGoUp()) goW();
            else return -1;
        }
        return 1;
    }

    public int xiepao3(int i) {
        if (i >= 83) return -1;
        if (i % 21 == 0) {
            if (jall(getHeng(), getShu()) == true && !isbianjie(getHeng(), getShu())) setBall();
            else return -1;
        } else if (i % 21 >= 1 && i % 21 <= 10) {
            if (cangGoLeft()) goA();
            else return -1;
        } else if (i % 21 >= 11 && i % 21 <= 20) {
            if (canGoDown()) goS();
            else return -1;
        }
        return 1;
    }

    public int xiepao4(int i) {
        if (i >= 83) return -1;
        if (i % 21 == 0) {
            if (jall(getHeng(), getShu()) == true && !isbianjie(getHeng(), getShu())) setBall();
            else return -1;
        } else if (i % 21 >= 1 && i % 21 <= 10) {
            if (canGoRight()) goD();
            else return -1;
        } else if (i % 21 >= 11 && i % 21 <= 20) {
            if (canGoDown()) goS();
            else return -1;
        }
        return 1;
    }


    //技能--长龙    1继续执行 -1中断
    public int changlong1(int i) {
        if (i >= 55) return -1;
        else if (canGoUp() == false) return -1;
        else if (getShu() - 1 < 0) return -1;
        else if (changlongsafe(1) == false) return -1;
        if (i % 11 == 0) setBall();
        else goW();
        return 1;
    }

    public int changlong2(int i) {
        if (i >= 55) return -1;
        else if (cangGoLeft() == false) return -1;
        else if (getHeng() - 1 < 0) return -1;
        else if (changlongsafe(2) == false) return -1;
        if (i % 11 == 0) setBall();
        else goA();
        return 1;
    }

    public int changlong3(int i) {
        if (i >= 55) return -1;
        else if (canGoDown() == false) return -1;
        else if (getShu() + 1 > 7) return -1;
        else if (changlongsafe(3) == false) return -1;
        if (i % 11 == 0) setBall();
        else goS();
        return 1;
    }

    public int changlong4(int i) {
        if (i >= 55) return -1;
        else if (canGoRight() == false) return -1;
        else if (getHeng() + 1 > 12) return -1;
        else if (changlongsafe(4) == false) return -1;
        else if (i % 11 == 0) setBall();
        else goD();
        return 1;
    }

    //长龙检查
    public boolean changlongsafe(int n) {

        if (n == 1) {
            int i = getHeng();
            int j = getShu() - 1;
            if (maps.isExp(i, j) == true || maps.isBoom(i, j) == true) return false;
            for (int k = 0; k < 8; ++k) {
                if (jw(i, j - k - 1, k + 1) && ja(i - k - 1, j, k + 1) && jd(i + k + 1, j, k + 1)) ;
                else return false;
            }
            return true;
        } else if (n == 2) {
            int i = getHeng() - 1;
            int j = getShu();
            if (maps.isExp(i, j) == true || maps.isBoom(i, j) == true) return false;
            for (int k = 0; k < 8; ++k) {
                if (jw(i, j - k - 1, k + 1) && ja(i - k - 1, j, k + 1) && js(i, j + k + 1, k + 1)) ;
                else return false;
            }
            return true;
        } else if (n == 3) {
            int i = getHeng();
            int j = getShu() + 1;
            if (maps.isExp(i, j) == true || maps.isBoom(i, j) == true) return false;
            for (int k = 0; k < 8; ++k) {
                if (ja(i - k - 1, j, k + 1) && js(i, j + k + 1, k + 1) && jd(i + k + 1, j, k + 1)) ;
                else return false;
            }
            return true;
        } else {
            int i = getHeng() + 1;
            int j = getShu();
            if (maps.isExp(i, j) == true || maps.isBoom(i, j) == true) return false;
            for (int k = 0; k < 8; ++k) {
                if (jw(i, j - k - 1, k + 1) && js(i, j + k + 1, k + 1) && jd(i + k + 1, j, k + 1)) ;
                else return false;
            }
            return true;
        }
    }

    //技能--V字弹花 此技能需要先检查战地在进行  进行时不会进行检查
    public int danhua(int i) {
        if (i == 0) setBall();
        else if (i >= 1 && i <= 10) goD();
        else if (i >= 11 && i <= 30) goS();
        else if (i == 31) setBall();
        else if (i >= 32 && i <= 41) goD();
        else if (i >= 42 && i <= 51) goW();
        else if (i == 52) setBall();

        if (i >= 0 && i <= 52) return 1;
        else return -1;
    }

    //技能释放前的安全检查
    public boolean danhuaSafe() {
        for (int i = getHeng(); i < getHeng() + 4; ++i)
            for (int j = getShu(); j < getShu() + 4; ++j)
                if (j <= 7 && j >= 0 && i <= 12 && i >= 0) {
                    if (maps.getBallMap()[i][j] != null || maps.getExplosionMap()[i][j] != null
                            || maps.getWallMap()[i][j] != null || jall(i, j) == false)
                        return false;
                } else return false;

        return true;
    }

    //放慢慢胶
    public void sajiao() {

        for (int k = 0; k < 15; ++k) {
            int i = random.nextInt(13);
            int j = random.nextInt(8);
            if (i <= 12 && i >= 0 && j <= 7 && j >= 0 && maps.getDaojuMap()[i][j] == null
                    && maps.getBallMap()[i][j] == null && maps.getExplosionMap()[i][j] == null
                    && maps.getWallMap()[i][j] == null)
                new Daoju(11);
        }
        MusicTool.music[12].stop();
        MusicTool.music[12].play();
    }

    //以自我为中心的方框内是否安全
    public boolean nearSafe() {
        for (int i = getHeng() - 1; i < getHeng() + 2; ++i)
            for (int j = getShu() - 1; j < getShu() + 2; ++j)
                if (j <= 7 && j >= 0 && i <= 12 && i >= 0)
                    if (jall(i, j) == false) return false;
        return true;
    }

    public boolean isbianjie(int i, int j) {
        if (i <= 0 || j <= 0 || i >= 12 || j >= 7) return true;
        else return false;
    }

    //象限的名义查找玩家的位置
    public int where() {
        if (BattleAIPanel.p1.getJudgeXPosition() > getJudgeXPosition() && BattleAIPanel.p1.getJudgeYPosition() > getJudgeYPosition()) return 4;
        if (BattleAIPanel.p1.getJudgeXPosition() < getJudgeXPosition() && BattleAIPanel.p1.getJudgeYPosition() > getJudgeYPosition()) return 3;
        if (BattleAIPanel.p1.getJudgeXPosition() > getJudgeXPosition() && BattleAIPanel.p1.getJudgeYPosition() < getJudgeYPosition()) return 1;
        if (BattleAIPanel.p1.getJudgeXPosition() < getJudgeXPosition() && BattleAIPanel.p1.getJudgeYPosition() < getJudgeYPosition()) return 2;
        return 0;
    }

    //-2必死无疑   0表示不用动  1表示向上走  2表示左走 3表示下走 4表示右走
    public int findway() {
        if (jall(getHeng(), getShu())) return 0;
        if (maps.isBoom(getHeng(), getShu()) == true) {
            if (gw(getHeng(), getShu()) == true && myfindway(getHeng(), getShu() - 1) == true) return 1;
            if (ga(getHeng(), getShu()) == true && myfindway(getHeng() - 1, getShu()) == true) return 2;
            if (gs(getHeng(), getShu()) == true && myfindway(getHeng(), getShu() + 1) == true) return 3;
            if (gd(getHeng(), getShu()) == true && myfindway(getHeng() + 1, getShu()) == true) return 4;
            return -2;
        }
        for (int i = 0; ; ++i) {
            if (gw(getHeng(), getShu() - i) == false) break;
            if (jall(getHeng(), getShu() - i - 1) == true) return 1;
        }
        for (int i = 0; ; ++i) {
            if (ga(getHeng() - i, getShu()) == false) break;
            if (jall(getHeng() - i - 1, getShu()) == true) return 2;
        }
        for (int i = 0; ; ++i) {
            if (gs(getHeng(), getShu() + i) == false) break;
            if (jall(getHeng(), getShu() + i + 1) == true) return 3;
        }
        for (int i = 0; ; ++i) {
            if (gd(getHeng() + i, getShu()) == false) break;
            if (jall(getHeng() + i + 1, getShu()) == true) return 4;
        }

        if (gw(getHeng(), getShu())) return 1;
        else if (ga(getHeng(), getShu())) return 2;
        else if (gs(getHeng(), getShu())) return 3;
        else if (gd(getHeng(), getShu())) return 4;
        return -2;
    }

    //0表示不动
    public int getaway() {
        if (BattleAIPanel.p1.getHeng() - getHeng() > 0 && ga(getHeng(), getShu())
                && jall(getHeng() - 1, getShu()))
            return 2;
        if (BattleAIPanel.p1.getHeng() - getHeng() < 0 && gd(getHeng(), getShu())
                && jall(getHeng() + 1, getShu()))
            return 4;
        if (BattleAIPanel.p1.getShu() - getShu() > 0 && gw(getHeng(), getShu())
                && jall(getHeng(), getShu() - 1))
            return 1;
        if (BattleAIPanel.p1.getShu() - getShu() < 0 && gs(getHeng(), getShu())
                && jall(getHeng(), getShu() + 1))
            return 3;

        if (ga(getHeng(), getShu()) && jall(getHeng() - 1, getShu()))
            return 2;
        if (gd(getHeng(), getShu()) && jall(getHeng() + 1, getShu()))
            return 4;
        if (gw(getHeng(), getShu()) && jall(getHeng(), getShu() - 1))
            return 1;
        if (gs(getHeng(), getShu()) && jall(getHeng(), getShu() + 1))
            return 3;

        return 0;
    }

    public boolean weixian(int i, int j) {
        if (Math.abs(BattleAIPanel.p1.getHeng() - getHeng()) <= 2
                && Math.abs(BattleAIPanel.p1.getShu() - getShu()) <= 2)
            return true;
        else return false;
    }


    //-1表示现在应该跑  0现在安全了去追击玩家吧   1现在还不太安全  别乱动 再看看应该干什么
    public int safecheck() {
        if (jall(getHeng(), getShu()) == false) return -1;
        else if (nearSafe() == true) return 0;
        else return 1;
    }


    //帮助函数///////////////////////////////////////////////////////////////////////////////////////////
    //让虎克行走
    public void goS() {
        UP = false;
        DOWN = true;
        LEFT = false;
        RIGHT = false;
        move();
    }

    public void goW() {
        UP = true;
        DOWN = false;
        LEFT = false;
        RIGHT = false;
        move();
    }

    public void goA() {
        UP = false;
        DOWN = false;
        LEFT = true;
        RIGHT = false;
        move();
    }

    public void goD() {
        UP = false;
        DOWN = false;
        LEFT = false;
        RIGHT = true;
        move();
    }

    void stop() {
        UP = false;
        DOWN = false;
        LEFT = false;
        RIGHT = false;
    }

    //判断此位置是否安全  0表示位置  1表示安全 2表示死亡   判断了 边界，墙壁，炸弹射程
    public boolean jw(int i, int j, int l) {
        if (j < 0) return true;
        else if (maps.isWall(i, j) == true) return true;
            //else if(element.Maps.isExp(i,j)==true) return false;
        else if (maps.isBoom(i, j) == false) return true;
        else if (maps.isBoom(i, j) == true && maps.getBallMap()[i][j].getPower() < l) return true;
        else return false;

    }

    public boolean ja(int i, int j, int l) {
        if (i < 0) return true;
        else if (maps.isWall(i, j) == true) return true;
            //else if(element.Maps.isExp(i,j)==true) return false;
        else if (maps.isBoom(i, j) == false) return true;
        else if (maps.isBoom(i, j) == true && maps.getBallMap()[i][j].getPower() < l) return true;
        else return false;
    }

    public boolean js(int i, int j, int l) {
        if (j > 7) return true;
        else if (maps.isWall(i, j) == true) return true;
            //else if(element.Maps.isExp(i,j)==true) return false;
        else if (maps.isBoom(i, j) == false) return true;
        else if (maps.isBoom(i, j) == true && maps.getBallMap()[i][j].getPower() < l) return true;
        else return false;
    }

    public boolean jd(int i, int j, int l) {
        if (i > 12) return true;
        else if (maps.isWall(i, j) == true) return true;
            //else if(element.Maps.isExp(i,j)==true) return false;
        else if (maps.isBoom(i, j) == false) return true;
        else if (maps.isBoom(i, j) == true && maps.getBallMap()[i][j].getPower() < l) return true;
        else return false;
    }

    //判断如果人在 i，j 点是否横竖安全
    public boolean jall(int i, int j) {
        if (maps.isExp(i, j) == true || maps.isBoom(i, j) == true) return false;
        for (int k = 0; k < 8; ++k) {
            if (jw(i, j - k - 1, k + 1) && ja(i - k - 1, j, k + 1) && js(i, j + k + 1, k + 1) && jd(i + k + 1, j, k + 1))
                ;
            else return false;
        }
        return true;
    }


    //能否移动到另一个格子
    public boolean gw(int i, int j) {
        boolean wall = false;
        boolean ball = false;
        boolean bianjie = false;
        if (j > 0)
            bianjie = true;
        if ((j - 1 >= 0 && maps.isWall(i, j - 1) == false)
                || (j - 1 >= 0 && maps.isWall(i, j - 1) == true && getJudgeYPosition() > ((j - 1) * 50 + 65))
                || (j - 1 < 0 && maps.isWall(i, j) == false))
            wall = true;
        if ((j - 1 >= 0 && maps.isBoom(i, j - 1) == false)
                || (j - 1 >= 0 && maps.isBoom(i, j - 1) == true && getJudgeYPosition() > ((j - 1) * 50 + 65))
                || (j - 1 < 0 && maps.isBoom(i, j) == false))
            ball = true;

        return (wall && ball && bianjie);

    }

    public boolean ga(int i, int j) {

        boolean wall = false;
        boolean ball = false;
        boolean bianjie = false;
        if (i > 0)
            bianjie = true;
        if ((i - 1 >= 0 && maps.isBoom(i - 1, j) == false)
                || (i - 1 >= 0 && maps.isBoom(i - 1, j) == true && getJudgeXPosition() > (i - 1) * 50 + 65)
                || (i - 1 < 0 && maps.isBoom(i, j) == false))
            ball = true;
        if ((i - 1 >= 0 && maps.isWall(i - 1, j) == false)
                || (i - 1 >= 0 && maps.isWall(i - 1, j) == true && getJudgeXPosition() > (i - 1) * 50 + 65)
                || (i - 1 < 0 && maps.isWall(i, j) == false))
            wall = true;
        return (wall && ball && bianjie);

    }


    public boolean gs(int i, int j) {

        boolean wall = false;
        boolean ball = false;
        boolean bianjie = false;
        if (j < 7)
            bianjie = true;
        if ((j + 1 <= 7 && maps.isBoom(i, j + 1) == false)
                || (j + 1 <= 7 && maps.isBoom(i, j + 1) == true && getJudgeYPosition() < ((j + 1) * 50 - 15))
                || (j + 1 > 7 && maps.isBoom(i, j) == false))
            ball = true;
        if ((j + 1 <= 7 && maps.isWall(i, j + 1) == false)
                || (j + 1 <= 7 && maps.isWall(i, j + 1) == true && getJudgeYPosition() < ((j + 1) * 50 - 15))
                || (j + 1 > 7 && maps.isWall(i, j) == false))
            wall = true;
        return (wall && ball && bianjie);
    }

    public boolean gd(int i, int j) {

        boolean wall = false;
        boolean ball = false;
        boolean bianjie = false;
        if (i < 12)
            bianjie = true;
        if ((i + 1 <= 12 && maps.isBoom(i + 1, j) == false)
                || (i + 1 <= 12 && maps.isBoom(i + 1, j) == true && getJudgeXPosition() < (i + 1) * 50 - 15)
                || (i + 1 > 12 && maps.isBoom(i, j) == false))
            ball = true;
        if ((i + 1 <= 12 && maps.isWall(i + 1, j) == false)
                || (i + 1 <= 12 && maps.isWall(i + 1, j) == true && getJudgeXPosition() < (i + 1) * 50 - 15)
                || (i + 1 > 12 && maps.isWall(i, j) == false))
            wall = true;
        return (wall && ball && bianjie);
    }

    public boolean stillin(int i, int j) {
        if (i <= 12 && i >= 0 && j <= 7 && j >= 0) return true;
        else return false;
    }

    public void move() {
        if (RIGHT == true) {
            now = pd;
            if (canGoRight()) setJudgeXPosition(getJudgeXPosition() + speed);
            stop();
            return;
        } else if (LEFT == true) {
            now = pa;
            if (cangGoLeft()) setJudgeXPosition(getJudgeXPosition() - speed);
            stop();
            return;
        } else if (UP == true) {
            now = pw;
            if (canGoUp()) setJudgeYPosition(getJudgeYPosition() - speed);
            stop();
            return;
        } else if (DOWN == true) {
            now = ps;
            if (canGoDown()) setJudgeYPosition(getJudgeYPosition() + speed);
            stop();
            return;
        }

    }

    public void eatDaoju() {
        if (maps.isDaoju(getHeng(), getShu())) {
            switch (maps.getDaojuMap()[getHeng()][getShu()].getType()) {
                case 1:
                case 3:
                case 2:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    MusicTool.music[4].stop();
                    MusicTool.music[4].play();
                    maps.getDaojuMap()[getHeng()][getShu()] = null;
                    break;
            }
        }
    }

    public void setBall() {
        Ball ball = new Ball(this, getHeng(), getShu(), 6, getBallIcon(), maps);
        MusicTool.music[5].play();
    }

    public boolean beexp() {
        if (maps.isExp(getHeng(), getShu())) return true;
        return false;
    }

    public boolean myfindway(int i, int j) {
        if (jall(i, j)) return true;

        for (int k = 0; ; ++k) {
            if (gw(i, j - k) == false) break;
            if (jall(i, j - k - 1) == true) return true;
        }
        for (int k = 0; ; ++k) {
            if (ga(i - k, j) == false) break;
            if (jall(i - k - 1, j) == true) return true;
        }
        for (int k = 0; ; ++k) {
            if (gs(i, j + k) == false) break;
            if (jall(i, j + k + 1) == true) return true;
        }
        for (int k = 0; ; ++k) {
            if (gd(i + k, j) == false) break;
            if (jall(i + k + 1, j) == true) return true;
        }
        return false;
    }

}