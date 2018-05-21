package element;

import java.util.Random;

import javax.swing.ImageIcon;

public class Item {

    public static final int ITEM_BALL = 1;
    public static final int ITEM_POWER = 2;
    public static final int ITEM_SHOES = 3;

    public static final int ITEM_FORK = 4;

    public static final int ITEM_WIND = 5;
    public static final int ITEM_CANDY = 6;
    public static final int ITEM_GHOST = 7;
    public static final int ITEM_FOX = 8;

    private int itemType;
    private ImageIcon itemIcon;

    public static Item createItemFox() {
        Item item = new Item();
        item.itemType = ITEM_FOX;
        item.itemIcon = new ImageIcon("zhutu.jpg");
        return item;
    }

    public Item() {
        Random a = new Random();
        itemType = a.nextInt(8) + 1;
        setItemIcon();
    }

    public int getItemType() {
        return itemType;
    }

    public ImageIcon getItemIcon() {
        return itemIcon;
    }

    private void setItemIcon() {
        switch (itemType) {
            case ITEM_BALL:
                itemIcon = new ImageIcon("糖包.gif");
                break;
            case ITEM_POWER:
                itemIcon = new ImageIcon("威力.gif");
                break;
            case ITEM_SHOES:
                itemIcon = new ImageIcon("飞鞋.gif");
                break;
            case ITEM_FORK:
                itemIcon = new ImageIcon("叉子.gif");
                break;
            case ITEM_WIND:
                itemIcon = new ImageIcon("fengtu.gif");
                break;
            case ITEM_CANDY:
                itemIcon = new ImageIcon("paotu.gif");
                break;
            case ITEM_GHOST:
                itemIcon = new ImageIcon("guitu.gif");
                break;
            case ITEM_FOX:
                itemIcon = new ImageIcon("zhutu.jpg");
                break;
        }
    }
}
