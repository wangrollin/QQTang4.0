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

    private int wallMapType;

    public int getWallMapType() {
        return wallMapType;
    }

    private Wall[][] wallMap;
    private Explosion[][] explosionMap;
    private Ball[][] ballMap;
    private Item[][] itemMap;

    public Maps() {
        wallMap = new Wall[GameConstants.HENG][GameConstants.SHU];
        ballMap = new Ball[GameConstants.HENG][GameConstants.SHU];
        explosionMap = new Explosion[GameConstants.HENG][GameConstants.SHU];
        itemMap = new Item[GameConstants.HENG][GameConstants.SHU];
    }

    //背景图片
    public ImageIcon getGroundIcon() {
        return groundIcon;
    }

    public ImageIcon getBiwumenIcon() {
        return biwumenIcon;
    }

    /**
     * check
     */
    public boolean isWall(int heng, int shu) {
        return !(wallMap[heng][shu] == null);
    }

    public boolean isItem(int heng, int shu) {
        return !(itemMap[heng][shu] == null);
    }

    public boolean isBall(int heng, int shu) {
        return !(ballMap[heng][shu] == null);
    }

    public boolean isExplosion(int heng, int shu) {
        return !(explosionMap[heng][shu] == null);
    }

    /**
     * get
     */
    public Ball getBall(int heng, int shu) {
        return ballMap[heng][shu];
    }

    public Wall getWall(int heng, int shu) {
        return wallMap[heng][shu];
    }

    public Item getItem(int heng, int shu) {
        return itemMap[heng][shu];
    }

    public Explosion getExplosion(int heng, int shu) {
        return explosionMap[heng][shu];
    }

    /**
     * remove
     */
    public void removeWall(int heng, int shu) {
        this.wallMap[heng][shu] = null;
    }

    public void removeExplosion(int heng, int shu) {
        this.explosionMap[heng][shu] = null;
    }

    public void removeBall(int heng, int shu) {
        this.ballMap[heng][shu] = null;
    }

    public void removeItem(int heng, int shu) {
        this.itemMap[heng][shu] = null;
    }

    /**
     * set
     */
    public void setWall(int heng, int shu, Wall wall) {
        this.wallMap[heng][shu] = wall;
    }

    public void setExplosion(Explosion explosion) {
        this.explosionMap[explosion.getHeng()][explosion.getShu()] = explosion;
    }

    public void setBall(Ball ball) {
        this.ballMap[ball.getHeng()][ball.getShu()] = ball;
    }

    public void setItem(int heng, int shu, Item item) {
        this.itemMap[heng][shu] = item;
    }




    public void clearItemIfInExplosion(int heng, int shu) {
        if (this.explosionMap[heng][shu] != null)
            this.itemMap[heng][shu] = null;
    }

    /*public Wall[][] getWallMap() {
        return wallMap;
    }*/

    /*public Explosion[][] getExplosionMap() {
        return explosionMap;
    }

    public Ball[][] getBallMap() {
        return ballMap;
    }

    public Item[][] getItemMap() {
        return itemMap;
    }*/

    public void setWallMap(Wall[][] wallMap, int wallMapType) {
        this.wallMap = wallMap;
        this.wallMapType = wallMapType;
    }

    public void setGroundIconByType(int groundType) {
        this.groundType = groundType;
        if (this.groundType == GameConstants.BIWU_GROUND) {
            this.groundIcon = new ImageIcon("比武地面.png");
        } else if (this.groundType == GameConstants.SHUIMIAN_GROUND) {
            this.groundIcon = new ImageIcon("水面地面.png");
        } else if (this.groundType == GameConstants.KUANGDONG_GROUND) {
            this.groundIcon = new ImageIcon("宝藏地面.png");
        }
    }

}
