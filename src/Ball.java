import javax.swing.*;


public class Ball {
    //哪个格子
    private int heng, shu;
    //威力大小
    private int power;
    //炸弹计时
    int time = 0;
    //不爆炸的时间    爆炸持续时间
    protected final static int MAXtime = 300, Booming = 20;
    //动态图
    private ImageIcon ball;
    //谁放的
    private int from;
    //等待中 爆炸中  存在吗
    //private boolean wait=true,die=false,booing=false;
    //事先预警
    Explosion just;

    public Ball(int f, int heng, int shu, int power, ImageIcon image) {
        from = f;
        if (from == 1) P1.count++;
        if (from == 2) P2.count++;
        if (from == 3) P3.count++;
        if (from == 4) P4.count++;
        if (from == 5) P5.count++;

        ball = image;
        this.heng = heng;
        this.shu = shu;
        this.power = power;
        Map.boommap[heng][shu] = this;
        just = Map.expmap[heng][shu];
    }

    public int getweili() {
        return power;
    }

    public ImageIcon getBall() {
        return ball;
    }

    public void addTime() {
        time += 1;
        if (time == MAXtime || (Map.isExp(heng, shu) == true && just != Map.expmap[heng][shu])) {
            booit();
            remove();
        }
    }

    public void booit() {
        Music.music[2].stop();
        Music.music[2].play();
        Map.expmap[heng][shu] = new Explosion(heng, shu, "oo");
        //0能炸过去     1遇到了墙能炸碎     2遇到了便边界 或者炸不碎的墙
        //s
        flag:
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
                break flag;
            }
            if (cangos(i) == 2) {
                if (i != 1) Map.expmap[heng][shu + i - 1] = new Explosion(heng, shu + i - 1, "zs");
                break flag;
            }
        }
        //w
        flag1:
        for (int i = 1; i <= power; i++) {
            if (cangow(i) == 0) {
                if (i == power) Map.expmap[heng][shu - i] = new Explosion(heng, shu - i, "w");
                if (i != power) Map.expmap[heng][shu - i] = new Explosion(heng, shu - i, "zw");
            }
            if (cangow(i) == 1) {
                Map.wallmap[heng][shu - i].Ruin();
                Map.expmap[heng][shu - i] = new Explosion(heng, shu - i, "sw");
                break flag1;
            }
            if (cangow(i) == 2) {
                if (i != 1) Map.expmap[heng][shu - i + 1] = new Explosion(heng, shu - i + 1, "zw");
                break flag1;
            }
        }
        //a
        flag2:
        for (int i = 1; i <= power; i++) {
            if (cangoa(i) == 0) {
                if (i == power) Map.expmap[heng - i][shu] = new Explosion(heng - i, shu, "a");
                if (i != power) Map.expmap[heng - i][shu] = new Explosion(heng - i, shu, "za");
            }
            if (cangoa(i) == 1) {
                Map.wallmap[heng - i][shu].Ruin();
                Map.expmap[heng - i][shu] = new Explosion(heng - i, shu, "sa");
                break flag2;
            }
            if (cangoa(i) == 2) {
                if (i != 1) Map.expmap[heng - i + 1][shu] = new Explosion(heng - i + 1, shu, "za");
                break flag2;
            }
        }
        //d
        flag3:
        for (int i = 1; i <= power; i++) {
            if (cangod(i) == 0) {
                if (i == power) Map.expmap[heng + i][shu] = new Explosion(heng + i, shu, "d");
                if (i != power) Map.expmap[heng + i][shu] = new Explosion(heng + i, shu, "zd");
            }
            if (cangod(i) == 1) {
                Map.wallmap[heng + i][shu].Ruin();
                Map.expmap[heng + i][shu] = new Explosion(heng + i, shu, "sd");
                break flag3;
            }
            if (cangod(i) == 2) {
                if (i != 1) Map.expmap[heng + i - 1][shu] = new Explosion(heng + i - 1, shu, "zd");
                break flag3;
            }
        }
    }

    public void remove() {
        Map.boommap[heng][shu] = null;
        if (from == 1) BattleCanvas.p1.count--;

        if (from == 2) BattleCanvas.p2.count--;
        if (from == 3) Battlemaoxian.p1.count--;
        if (from == 4) Battlebiwu.p1.count--;
        if (from == 5) Battlebiwu.p2.count--;


    }

    //返回 int  0能炸过去     1遇到了墙能炸碎     2遇到了便边界 或者炸不碎的墙
    public int cangow(int i) {
        if (shu - i >= 0 && (!Map.isWall(heng, shu - i)))
            return 0;
        else if (shu - i >= 0 && Map.isWall(heng, shu - i) && Map.wallmap[heng][shu - i].getType() == 1)
            return 1;
        else
            return 2;
    }

    public int cangos(int i) {        //墙 和边界
        if (shu + i <= 7 && (!Map.isWall(heng, shu + i)))
            return 0;
        else if (shu + i <= 7 && Map.isWall(heng, shu + i) && Map.wallmap[heng][shu + i].getType() == 1)
            return 1;
        else
            return 2;
    }

    public int cangoa(int i) {
        if (heng - i >= 0 && (!Map.isWall(heng - i, shu)))
            return 0;
        else if (heng - i >= 0 && Map.isWall(heng - i, shu) && Map.wallmap[heng - i][shu].getType() == 1)
            return 1;
        else
            return 2;
    }

    public int cangod(int i) {
        if (heng + i <= 12 && (!Map.isWall(heng + i, shu)))
            return 0;
        else if (heng + i <= 12 && Map.isWall(heng + i, shu) && Map.wallmap[heng + i][shu].getType() == 1)
            return 1;
        else
            return 2;
    }
}
