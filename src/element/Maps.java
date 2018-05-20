package element;

import constants.GameConstants;

import javax.swing.ImageIcon;


public class Maps {
    //判断是否这个地方在爆炸
    //判断这个地方的道具情况
    //是否人道具被炸掉
    //判断有没有糖泡
    //一个格子的大小
    //protected static int Xoff = 50, Yoff = 50;
    //地面 墙
    //public ImageIcon dimian1, dimian2, dimian3;

    private ImageIcon biwumenIcon = new ImageIcon("比武门.png");

    private int groundType = GameConstants.BIWU_GROUND;
    private ImageIcon groundIcon = new ImageIcon("比武地面.png");;

    public void setGroundType(int groundType) {
        this.groundType = groundType;
        if (this.groundType == GameConstants.BIWU_GROUND) {
            this.groundIcon = new ImageIcon("比武地面.png");
        } else if (this.groundType == GameConstants.SHUIMIAN_GROUND) {
            this.groundIcon = new ImageIcon("水面地面.png");
        } else if (this.groundType == GameConstants.KUANGDONG_GROUND) {
            this.groundIcon = new ImageIcon("宝藏地面.png");
        }
    }
//墙的图像
    //private ImageIcon brick, iron, now;

    private Wall[][] wallMap;
    private Explosion[][] explosionMap;
    private Ball[][] ballMap;
    private Daoju[][] daojuMap;

    public Maps() {
        wallMap = new Wall[GameConstants.HENG][GameConstants.SHU];
        ballMap = new Ball[GameConstants.HENG][GameConstants.SHU];
        explosionMap = new Explosion[GameConstants.HENG][GameConstants.SHU];
        daojuMap = new Daoju[GameConstants.HENG][GameConstants.SHU];
    }

    //背景图片
    public ImageIcon getGroundIcon() {
        return groundIcon;
    }

    public ImageIcon getBiwumenIcon() {
        return biwumenIcon;
    }

    //墙的
    public boolean isWall(int heng, int shu) {
        if (wallMap[heng][shu] == null) return false;
        else return true;
    }

    //道具的
    public boolean isDaoju(int heng, int shu) {
        if (daojuMap[heng][shu] == null) return false;
        else return true;
    }

    //炸弹的
    public boolean isBoom(int heng, int shu) {
        if (ballMap[heng][shu] == null) return false;
        else return true;
    }

    ////爆炸中
    public boolean isExp(int heng, int shu) {
        if (explosionMap[heng][shu] == null) return false;
        else return true;
    }

    /*public void removeWall(int heng, int shu) {
        wallMap[heng][shu] = null;
    }*/

    public Wall[][] getWallMap() {
        return wallMap;
    }

    public Explosion[][] getExplosionMap() {
        return explosionMap;
    }

    public Ball[][] getBallMap() {
        return ballMap;
    }

    public Daoju[][] getDaojuMap() {
        return daojuMap;
    }

    public void setWallMap(Wall[][] wallMap) {
        this.wallMap = wallMap;
    }
}
