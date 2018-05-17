import javax.swing.ImageIcon;


public class Map {
    //判断是否这个地方在爆炸
    //判断这个地方的道具情况
    //是否人道具被炸掉
    //判断有没有糖泡
    //一个格子的大小
    protected static int Xoff = 50, Yoff = 50;
    //地面 墙
    protected static ImageIcon dimian, dimian1, dimian2, dimian3, biwumen;
    //墙的图像
    private ImageIcon brick, iron, now;

    //墙地图
    protected static Wall[][] wallmap;
    //爆炸地图
    static Explosion[][] expmap;
    //糖泡地图
    protected static Ball[][] boommap;
    //道具地图
    static Daoju[][] daojumap;

    public Map() {

        dimian1 = new ImageIcon("比武地面.png");
        dimian2 = new ImageIcon("水面地面.png");
        dimian3 = new ImageIcon("宝藏地面.png");
        biwumen = new ImageIcon("比武门.png");

        wallmap = new Wall[BattleJingjiPanel.heng][BattleJingjiPanel.shu];
        boommap = new Ball[BattleJingjiPanel.heng][BattleJingjiPanel.shu];
        expmap = new Explosion[BattleJingjiPanel.heng][BattleJingjiPanel.shu];
        daojumap = new Daoju[BattleJingjiPanel.heng][BattleJingjiPanel.shu];

        WallMap.biwu1();
        dimian = dimian1;
    }

    //背景图片
    public ImageIcon getBack() {
        return dimian;
    }

    public ImageIcon biwumen() {
        return biwumen;
    }

    //墙的
    public static boolean isWall(int heng, int shu) {
        if (wallmap[heng][shu] == null) return false;
        else return true;
    }

    //道具的
    public static boolean isDaoju(int heng, int shu) {
        if (daojumap[heng][shu] == null) return false;
        else return true;
    }

    //炸弹的
    public static boolean isBoom(int heng, int shu) {
        if (boommap[heng][shu] == null) return false;
        else return true;
    }

    ////爆炸中
    public static boolean isExp(int heng, int shu) {
        if (expmap[heng][shu] == null) return false;
        else return true;
    }
}
