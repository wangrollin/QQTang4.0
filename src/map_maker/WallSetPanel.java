package map_maker;

import constants.GameConstants;
import element.MusicTool;
import element.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WallSetPanel extends JPanel implements KeyListener {
    /*public static final int makercanvas_WIDTH = 650;
    public static final int DEFAULT_HEIGHT = 400;
    public static final int now_WIDTH = 50;
    public static final int now_HEIGHT = 50;*/

    private Wall[][] wallMap;

    public Wall[][] getWallMap() {
        return wallMap;
    }

    private ImageIcon bgIcon = new ImageIcon("比武地面.png");
    private ImageIcon arrowIcon = new ImageIcon("小怪.gif");

    private int x = 0;//now的当前指示坐标
    private int y = 0;

    public WallSetPanel() {
        wallMap = new Wall[GameConstants.HENG][GameConstants.SHU];
        for (int i = 0; i < GameConstants.HENG; i++) {
            for (int j = 0; j < GameConstants.SHU; j++) {
                wallMap[i][j] = null;
            }
        }

        setLayout(null);

        //this.addKeyListener(new eve());
        this.requestFocus(true);
    }

    public void paintComponent(Graphics page) {
        bgIcon.paintIcon(this, page, 0, 0);

        for (int j = 0; j < GameConstants.SHU; j++) {
            for (int i = 0; i < GameConstants.HENG; i++) {
                if (wallMap[i][j] != null) {
                    wallMap[i][j].getWallIcon().paintIcon(this, page, i * 50, j * 50);
                }
            }
        }

        arrowIcon.paintIcon(this, page, x * 50, y * 50);

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                if (wallMap[x][y] != null) {
                    MusicTool.PRESS_BUTTON.play();
                }
                wallMap[x][y] = null;
                repaint();
                break;
            case KeyEvent.VK_UP:
                if (y >= 1) {
                    y--;
                    repaint();
                }
                break;
            case KeyEvent.VK_DOWN:
                if (y <= 6) {
                    y++;
                    repaint();
                }
                break;
            case KeyEvent.VK_LEFT:
                if (x >= 1) {
                    x--;
                    repaint();
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (x <= 11) {
                    x++;
                    repaint();
                }
                break;
            case KeyEvent.VK_1:
                MusicTool.PRESS_BUTTON.play();
                wallMap[x][y] = new Wall(Wall.WALL_TYPE3);
                repaint();
                break;
            case KeyEvent.VK_2:
                MusicTool.PRESS_BUTTON.play();
                wallMap[x][y] = new Wall(Wall.WALL_TYPE2);
                repaint();
                break;
            case KeyEvent.VK_3:
                MusicTool.PRESS_BUTTON.play();
                wallMap[x][y] = new Wall(Wall.WALL_TYPE0);
                repaint();
                break;
            case KeyEvent.VK_4:
                MusicTool.PRESS_BUTTON.play();
                wallMap[x][y] = new Wall(Wall.WALL_TYPE1);
                repaint();
                break;
            case KeyEvent.VK_Q:
                MusicTool.PRESS_BUTTON.play();
                wallMap[x][y] = new Wall(Wall.WALL_TYPE6);
                repaint();
                break;
            case KeyEvent.VK_W:
                MusicTool.PRESS_BUTTON.play();
                wallMap[x][y] = new Wall(Wall.WALL_TYPE5);
                repaint();
                break;
            case KeyEvent.VK_E:
                MusicTool.PRESS_BUTTON.play();
                wallMap[x][y] = new Wall(Wall.WALL_TYPE7);
                repaint();
                break;
            case KeyEvent.VK_A:
                MusicTool.PRESS_BUTTON.play();
                wallMap[x][y] = new Wall(Wall.WALL_TYPE8);
                repaint();
                break;
            case KeyEvent.VK_S:
                MusicTool.PRESS_BUTTON.play();
                wallMap[x][y] = new Wall(Wall.WALL_TYPE9);
                repaint();
                break;
            case KeyEvent.VK_D:
                MusicTool.PRESS_BUTTON.play();
                wallMap[x][y] = new Wall(Wall.WALL_TYPE4);
                repaint();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


  /*  private class eve implements KeyListener {

    }*/
}
