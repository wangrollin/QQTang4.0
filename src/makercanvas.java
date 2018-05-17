import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class makercanvas extends JPanel {
    public static final int makercanvas_WIDTH = 650;
    public static final int DEFAULT_HEIGHT = 400;
    public static final int now_WIDTH = 50;
    public static final int now_HEIGHT = 50;

    public static int[][] mapshuzu = new int[13][8];//表示某位置地形的数字
    private static JLabel[][] ditukuai = new JLabel[13][8];//地图块，存放某位置的地形图片

    ImageIcon background1 = new ImageIcon("比武地面.png");
    ImageIcon background2 = new ImageIcon(".png");
    ImageIcon background3 = new ImageIcon(".png");
    ImageIcon now = new ImageIcon("小怪.gif");
    ImageIcon pic1 = new ImageIcon("冰墙.png");
    ImageIcon pic2 = new ImageIcon("沙墙.png");
    ImageIcon pic3 = new ImageIcon("比.png");
    ImageIcon pic4 = new ImageIcon("武.png");
    ImageIcon pic11 = new ImageIcon("沙.png");
    ImageIcon pic12 = new ImageIcon("水1.png");
    ImageIcon pic13 = new ImageIcon("水2.png");
    ImageIcon pic14 = new ImageIcon("水3.png");
    ImageIcon pic15 = new ImageIcon("水4.png");
    ImageIcon pic16 = new ImageIcon("桶.png");

    JLabel bg1;
    JLabel bg2;
    JLabel bg3;
    JLabel n;
    static int x = 0;//now的当前指示坐标
    static int y = 0;
    static int z = 0;//指示有按键，要paint一次

    public makercanvas() {

        setLayout(null);

        bg1 = new JLabel(background1);
        bg1.setBounds(0, 0, makercanvas_WIDTH, DEFAULT_HEIGHT);
        bg1.setVisible(true);
        n = new JLabel(now);
        n.setBounds(50 * x, 50 * y, now_WIDTH, now_HEIGHT);
        n.setVisible(true);
        add(n);
        add(bg1);
        repaint();
        this.addKeyListener(new eve());
        this.requestFocus(true);

        System.out.print("1成功");
    }

    public void paintComponent(Graphics page) {
        this.requestFocus();
        if (ditukuai[x][y] != null)
            remove(ditukuai[x][y]);
        remove(bg1);
        n.setBounds(50 * x, 50 * y, now_WIDTH, now_HEIGHT);
        add(n);
        if (mapshuzu != null)
            paintwall(x, y);
        add(bg1);
    }

    public void paintwall(int a, int b) {
        if (mapshuzu[a][b] == 1) {
            ditukuai[a][b] = new JLabel(pic1);
            ditukuai[a][b].setBounds(50 * x, 50 * y, now_WIDTH, now_HEIGHT);
            System.out.print("a");
            add(ditukuai[a][b]);
        }
        if (mapshuzu[a][b] == 2) {
            ditukuai[a][b] = new JLabel(pic2);
            ditukuai[a][b].setBounds(50 * x, 50 * y, now_WIDTH, now_HEIGHT);
            System.out.print("a");
            add(ditukuai[a][b]);
        }
        if (mapshuzu[a][b] == 3) {
            ditukuai[a][b] = new JLabel(pic3);
            ditukuai[a][b].setBounds(50 * x, 50 * y, now_WIDTH, now_HEIGHT);
            System.out.print("a");
            add(ditukuai[a][b]);
        }
        if (mapshuzu[a][b] == 4) {
            ditukuai[a][b] = new JLabel(pic4);
            ditukuai[a][b].setBounds(50 * x, 50 * y, now_WIDTH, now_HEIGHT);
            System.out.print("a");
            add(ditukuai[a][b]);
        }
        if (mapshuzu[a][b] == 11) {
            ditukuai[a][b] = new JLabel(pic11);
            ditukuai[a][b].setBounds(50 * x, 50 * y, now_WIDTH, now_HEIGHT);
            System.out.print("a");
            add(ditukuai[a][b]);
        }
        if (mapshuzu[a][b] == 12) {
            ditukuai[a][b] = new JLabel(pic12);
            ditukuai[a][b].setBounds(50 * x, 50 * y, now_WIDTH, now_HEIGHT);
            System.out.print("a");
            add(ditukuai[a][b]);
        }
        if (mapshuzu[a][b] == 13) {
            ditukuai[a][b] = new JLabel(pic13);
            ditukuai[a][b].setBounds(50 * x, 50 * y, now_WIDTH, now_HEIGHT);
            System.out.print("a");
            add(ditukuai[a][b]);
        }
        if (mapshuzu[a][b] == 14) {
            ditukuai[a][b] = new JLabel(pic14);
            ditukuai[a][b].setBounds(50 * x, 50 * y, now_WIDTH, now_HEIGHT);
            System.out.print("a");
            add(ditukuai[a][b]);
        }
        if (mapshuzu[a][b] == 15) {
            ditukuai[a][b] = new JLabel(pic15);
            ditukuai[a][b].setBounds(50 * x, 50 * y, now_WIDTH, now_HEIGHT);
            System.out.print("a");
            add(ditukuai[a][b]);
        }
        if (mapshuzu[a][b] == 16) {
            ditukuai[a][b] = new JLabel(pic16);
            ditukuai[a][b].setBounds(50 * x, 50 * y, now_WIDTH, now_HEIGHT);
            System.out.print("a");
            add(ditukuai[a][b]);
        }


    }

    class eve implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub
            switch (e.getKeyCode()) {
                case KeyEvent.VK_SPACE:
                    mapshuzu[x][y] = 0;
                    remove(ditukuai[x][y]);
                    repaint();
                    break;
                case KeyEvent.VK_UP:
                    if (y >= 1) {
                        y--;
                        repaint();
                        System.out.print(y);
                        break;
                    } else
                        break;
                case KeyEvent.VK_DOWN:
                    if (y <= 6) {
                        y++;
                        repaint();
                        System.out.print(y);
                        break;
                    } else
                        break;
                case KeyEvent.VK_LEFT:
                    if (x >= 1) {
                        x--;
                        repaint();
                        System.out.print(x);
                        break;
                    } else
                        break;
                case KeyEvent.VK_RIGHT:
                    if (x <= 11) {
                        x++;
                        repaint();
                        System.out.print(x);
                        break;
                    } else
                        break;
                case KeyEvent.VK_1:
                    mapshuzu[x][y] = 1;
                    repaint();
                    break;
                case KeyEvent.VK_2:
                    mapshuzu[x][y] = 2;
                    repaint();
                    break;
                case KeyEvent.VK_3:
                    mapshuzu[x][y] = 3;
                    repaint();
                    break;
                case KeyEvent.VK_4:
                    mapshuzu[x][y] = 4;
                    repaint();
                    break;
                case KeyEvent.VK_Q:
                    mapshuzu[x][y] = 11;
                    repaint();
                    break;
                case KeyEvent.VK_W:
                    mapshuzu[x][y] = 12;
                    repaint();
                    break;
                case KeyEvent.VK_E:
                    mapshuzu[x][y] = 13;
                    repaint();
                    break;
                case KeyEvent.VK_A:
                    mapshuzu[x][y] = 14;
                    repaint();
                    break;
                case KeyEvent.VK_S:
                    mapshuzu[x][y] = 15;
                    repaint();
                    break;
                case KeyEvent.VK_D:
                    mapshuzu[x][y] = 16;
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
    }
}
