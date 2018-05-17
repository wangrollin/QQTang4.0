import javax.swing.*;


public class Ball {
    //哪个格子
    private int heng, shu;
    //威力大小
    private int power;
    //炸弹计时
    private int timeCounter = 0;
    //不爆炸的时间
    private static final int MAX_TIME = 300;
    //动态图
    private ImageIcon ballIcon;
    //谁放的
    private int owner;
    //等待中 爆炸中  存在吗
    //private boolean wait=true,die=false,booing=false;
    //事先预警
    private Explosion just;

    public Ball(int f, int heng, int shu, int power, ImageIcon image) {
        owner = f;
        if (owner == 1) P1.count++;
        if (owner == 2) P2.count++;
        if (owner == 3) P3.count++;
        if (owner == 4) P4.count++;
        if (owner == 5) P5.count++;

        ballIcon = image;
        this.heng = heng;
        this.shu = shu;
        this.power = power;
        Map.boommap[heng][shu] = this;
        just = Map.expmap[heng][shu];
    }

    public int getweili() {
        return power;
    }

    ImageIcon getBallIcon() {
        return ballIcon;
    }

    public void addTime() {
        timeCounter += 1;
        if (timeCounter == MAX_TIME || (Map.isExp(heng, shu) && just != Map.expmap[heng][shu])) {
            booit();
            remove();
        }
    }

    private void booit() {
        Music.music[2].stop();
        Music.music[2].play();
        Map.expmap[heng][shu] = new Explosion(heng, shu, "oo");
        //0能炸过去     1遇到了墙能炸碎     2遇到了便边界 或者炸不碎的墙
        //s
        for (int i = 1; i <= power; i++) {
            if (cangos(i) == 0) {
                if (i == power)
                    Map.expmap[heng][shu + i] = new Explosion(heng, shu + i, "s");
                if (i != power)
                    Map.expmap[heng][shu + i] = new Explosion(heng, shu + i, "zs");
            }
            if (cangos(i) == 1) {
                Map.wallmap[heng][shu + i].Ruin();
                Map.expmap[heng][shu + i] = new Explosion(heng, shu + i, "ss");
                break;
            }
            if (cangos(i) == 2) {
                if (i != 1) Map.expmap[heng][shu + i - 1] = new Explosion(heng, shu + i - 1, "zs");
                break;
            }
        }
        //w
        for (int i = 1; i <= power; i++) {
            if (cangow(i) == 0) {
                if (i == power) Map.expmap[heng][shu - i] = new Explosion(heng, shu - i, "w");
                if (i != power) Map.expmap[heng][shu - i] = new Explosion(heng, shu - i, "zw");
            }
            if (cangow(i) == 1) {
                Map.wallmap[heng][shu - i].Ruin();
                Map.expmap[heng][shu - i] = new Explosion(heng, shu - i, "sw");
                break;
            }
            if (cangow(i) == 2) {
                if (i != 1) Map.expmap[heng][shu - i + 1] = new Explosion(heng, shu - i + 1, "zw");
                break;
            }
        }
        //a
        for (int i = 1; i <= power; i++) {
            if (cangoa(i) == 0) {
                if (i == power) Map.expmap[heng - i][shu] = new Explosion(heng - i, shu, "a");
                if (i != power) Map.expmap[heng - i][shu] = new Explosion(heng - i, shu, "za");
            }
            if (cangoa(i) == 1) {
                Map.wallmap[heng - i][shu].Ruin();
                Map.expmap[heng - i][shu] = new Explosion(heng - i, shu, "sa");
                break;
            }
            if (cangoa(i) == 2) {
                if (i != 1) Map.expmap[heng - i + 1][shu] = new Explosion(heng - i + 1, shu, "za");
                break;
            }
        }
        //d
        for (int i = 1; i <= power; i++) {
            if (cangod(i) == 0) {
                if (i == power) Map.expmap[heng + i][shu] = new Explosion(heng + i, shu, "d");
                if (i != power) Map.expmap[heng + i][shu] = new Explosion(heng + i, shu, "zd");
            }
            if (cangod(i) == 1) {
                Map.wallmap[heng + i][shu].Ruin();
                Map.expmap[heng + i][shu] = new Explosion(heng + i, shu, "sd");
                break;
            }
            if (cangod(i) == 2) {
                if (i != 1) Map.expmap[heng + i - 1][shu] = new Explosion(heng + i - 1, shu, "zd");
                break;
            }
        }
    }

    private void remove() {
        Map.boommap[heng][shu] = null;
        if (owner == 1) BattleJingjiPanel.p1.count--;

        if (owner == 2) BattleJingjiPanel.p2.count--;
        if (owner == 3) BattleAIPanel.p1.count--;
        if (owner == 4) BattleBiwuPanel.p1.count--;
        if (owner == 5) BattleBiwuPanel.p2.count--;


    }

    //返回 int  0能炸过去     1遇到了墙能炸碎     2遇到了便边界 或者炸不碎的墙
    private int cangow(int i) {
        if (shu - i >= 0 && (!Map.isWall(heng, shu - i)))
            return 0;
        else if (shu - i >= 0 && Map.isWall(heng, shu - i) && Map.wallmap[heng][shu - i].getType() == 1)
            return 1;
        else
            return 2;
    }

    private int cangos(int i) {        //墙 和边界
        if (shu + i <= 7 && (!Map.isWall(heng, shu + i)))
            return 0;
        else if (shu + i <= 7 && Map.isWall(heng, shu + i) && Map.wallmap[heng][shu + i].getType() == 1)
            return 1;
        else
            return 2;
    }

    private int cangoa(int i) {
        if (heng - i >= 0 && (!Map.isWall(heng - i, shu)))
            return 0;
        else if (heng - i >= 0 && Map.isWall(heng - i, shu) && Map.wallmap[heng - i][shu].getType() == 1)
            return 1;
        else
            return 2;
    }

    private int cangod(int i) {
        if (heng + i <= 12 && (!Map.isWall(heng + i, shu)))
            return 0;
        else if (heng + i <= 12 && Map.isWall(heng + i, shu) && Map.wallmap[heng + i][shu].getType() == 1)
            return 1;
        else
            return 2;
    }
}
