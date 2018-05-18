import constants.GameConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;


public class BattleBiwuPanel extends JPanel {
    private MyPanelCard myPanelCard;
    private CardLayout cardLayout;

    private JButton gobackBtn, exitBtn;
    //闪出来的长度
    protected final static int jiangeheng = 0, jiangeshu = 0;
    //时间监听毫秒
    private final int DELAY = 15;
    //时间老人
    private Timer timer;
    //地图
    static Map map;
    //人物登场
    static P4 p1;
    static P5 p2;
    private ImageIcon num, num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;
    protected static boolean timeover = false;
    TimeCounter timeCounter;

    //构造出来 初始化****************************************************************************************
    public BattleBiwuPanel(MyPanelCard myPanelCard, CardLayout cardLayout) {
        this.myPanelCard = myPanelCard;
        this.cardLayout = cardLayout;

        gobackBtn = new JButton(new ImageIcon("replay1.png"));
        gobackBtn.setBounds(10, 10, 128, 50);
        add(gobackBtn);

        exitBtn = new JButton(new ImageIcon("exit1.png"));
        exitBtn.setBounds(510, 10, 130, 50);
        add(exitBtn);
        num = new ImageIcon("：.png");
        num1 = new ImageIcon("1111.png");
        num2 = new ImageIcon("2.png");
        num3 = new ImageIcon("3333.png");
        num4 = new ImageIcon("4.png");
        num5 = new ImageIcon("5.png");
        num6 = new ImageIcon("6.png");
        num7 = new ImageIcon("7.png");
        num8 = new ImageIcon("8.png");
        num9 = new ImageIcon("9.png");
        num0 = new ImageIcon("0.png");
        Music.Musicload();
        map = new Map();
        timeCounter = new TimeCounter();
        p1 = new P4();
        p2 = new P5();
        timer = new Timer(DELAY, new Mytime());

        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        timer.start();
    }

    //画图啦**********************************************************************************************
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        map.biwumen().paintIcon(this, page, 0, 0);
        map.getBack().paintIcon(this, page, 0, 200);
        //不断刷新的
        p1.move();
        p2.move();
        p1.toDie(p1);
        p2.toDie(p2);
        p1.eatdaoju();
        p2.eatdaoju();
        p1.beExp();
        p2.beExp();
        p1.beBack();
        p2.beBack();
        p1.chusheng();
        p2.chusheng();

        if (TimeCounter.minute == 3) num3.paintIcon(this, page, 240, 5);
        if (TimeCounter.minute == 2) num2.paintIcon(this, page, 240, 5);
        if (TimeCounter.minute == 1) num1.paintIcon(this, page, 240, 5);
        if (TimeCounter.minute == 0) num0.paintIcon(this, page, 240, 5);

        num.paintIcon(this, page, 275, 20);

        if (TimeCounter.scond / 10 == 0) num0.paintIcon(this, page, 335, 5);
        if (TimeCounter.scond / 10 == 1) num1.paintIcon(this, page, 335, 5);
        if (TimeCounter.scond / 10 == 2) num2.paintIcon(this, page, 335, 5);
        if (TimeCounter.scond / 10 == 3) num3.paintIcon(this, page, 335, 5);
        if (TimeCounter.scond / 10 == 4) num4.paintIcon(this, page, 335, 5);
        if (TimeCounter.scond / 10 == 5) num5.paintIcon(this, page, 335, 5);
        if (TimeCounter.scond / 10 == 6) num6.paintIcon(this, page, 335, 5);
        if (TimeCounter.scond / 10 == 7) num7.paintIcon(this, page, 335, 5);
        if (TimeCounter.scond / 10 == 8) num8.paintIcon(this, page, 335, 5);
        if (TimeCounter.scond / 10 == 9) num9.paintIcon(this, page, 335, 5);

        if (TimeCounter.scond % 10 == 0) num0.paintIcon(this, page, 410, 5);
        if (TimeCounter.scond % 10 == 1) num1.paintIcon(this, page, 410, 5);
        if (TimeCounter.scond % 10 == 2) num2.paintIcon(this, page, 410, 5);
        if (TimeCounter.scond % 10 == 3) num3.paintIcon(this, page, 410, 5);
        if (TimeCounter.scond % 10 == 4) num4.paintIcon(this, page, 410, 5);
        if (TimeCounter.scond % 10 == 5) num5.paintIcon(this, page, 410, 5);
        if (TimeCounter.scond % 10 == 6) num6.paintIcon(this, page, 410, 5);
        if (TimeCounter.scond % 10 == 7) num7.paintIcon(this, page, 410, 5);
        if (TimeCounter.scond % 10 == 8) num8.paintIcon(this, page, 410, 5);
        if (TimeCounter.scond % 10 == 9) num9.paintIcon(this, page, 410, 5);

        if (p1.siwangcishu / 10 == 0) num0.paintIcon(this, page, 505, 100);
        if (p1.siwangcishu / 10 == 1) num1.paintIcon(this, page, 505, 100);
        if (p1.siwangcishu / 10 == 2) num2.paintIcon(this, page, 505, 100);
        if (p1.siwangcishu / 10 == 3) num3.paintIcon(this, page, 505, 100);
        if (p1.siwangcishu / 10 == 4) num4.paintIcon(this, page, 505, 100);
        if (p1.siwangcishu / 10 == 5) num5.paintIcon(this, page, 505, 100);
        if (p1.siwangcishu / 10 == 6) num6.paintIcon(this, page, 505, 100);
        if (p1.siwangcishu / 10 == 7) num7.paintIcon(this, page, 505, 100);
        if (p1.siwangcishu / 10 == 8) num8.paintIcon(this, page, 505, 100);
        if (p1.siwangcishu / 10 == 9) num9.paintIcon(this, page, 505, 100);

        if (p1.siwangcishu % 10 == 0) num0.paintIcon(this, page, 580, 100);
        if (p1.siwangcishu % 10 == 1) num1.paintIcon(this, page, 580, 100);
        if (p1.siwangcishu % 10 == 2) num2.paintIcon(this, page, 580, 100);
        if (p1.siwangcishu % 10 == 3) num3.paintIcon(this, page, 580, 100);
        if (p1.siwangcishu % 10 == 4) num4.paintIcon(this, page, 580, 100);
        if (p1.siwangcishu % 10 == 5) num5.paintIcon(this, page, 580, 100);
        if (p1.siwangcishu % 10 == 6) num6.paintIcon(this, page, 580, 100);
        if (p1.siwangcishu % 10 == 7) num7.paintIcon(this, page, 580, 100);
        if (p1.siwangcishu % 10 == 8) num8.paintIcon(this, page, 580, 100);
        if (p1.siwangcishu % 10 == 9) num9.paintIcon(this, page, 580, 100);

        if (p2.siwangcishu / 10 == 0) num0.paintIcon(this, page, 0, 100);
        if (p2.siwangcishu / 10 == 1) num1.paintIcon(this, page, 0, 100);
        if (p2.siwangcishu / 10 == 2) num2.paintIcon(this, page, 0, 100);
        if (p2.siwangcishu / 10 == 3) num3.paintIcon(this, page, 0, 100);
        if (p2.siwangcishu / 10 == 4) num4.paintIcon(this, page, 0, 100);
        if (p2.siwangcishu / 10 == 5) num5.paintIcon(this, page, 0, 100);
        if (p2.siwangcishu / 10 == 6) num6.paintIcon(this, page, 0, 100);
        if (p2.siwangcishu / 10 == 7) num7.paintIcon(this, page, 0, 100);
        if (p2.siwangcishu / 10 == 8) num8.paintIcon(this, page, 0, 100);
        if (p2.siwangcishu / 10 == 9) num9.paintIcon(this, page, 0, 100);

        if (p2.siwangcishu % 10 == 0) num0.paintIcon(this, page, 75, 100);
        if (p2.siwangcishu % 10 == 1) num1.paintIcon(this, page, 75, 100);
        if (p2.siwangcishu % 10 == 2) num2.paintIcon(this, page, 75, 100);
        if (p2.siwangcishu % 10 == 3) num3.paintIcon(this, page, 75, 100);
        if (p2.siwangcishu % 10 == 4) num4.paintIcon(this, page, 75, 100);
        if (p2.siwangcishu % 10 == 5) num5.paintIcon(this, page, 75, 100);
        if (p2.siwangcishu % 10 == 6) num6.paintIcon(this, page, 75, 100);
        if (p2.siwangcishu % 10 == 7) num7.paintIcon(this, page, 75, 100);
        if (p2.siwangcishu % 10 == 8) num8.paintIcon(this, page, 75, 100);
        if (p2.siwangcishu % 10 == 9) num9.paintIcon(this, page, 75, 100);

        timeCounter.count();

        if (TimeCounter.over == true) {

            Music.stop();
            Music.music[8].loop();
            if (p1.siwangcishu < p2.siwangcishu) cardLayout.show(myPanelCard, "fighterWinPanel");
            if (p1.siwangcishu > p2.siwangcishu) cardLayout.show(myPanelCard, "baoziWinPanel");
            if (p1.siwangcishu == p2.siwangcishu) cardLayout.show(myPanelCard, "dagfallPanel");
        }

        //绘图采用一行一行扫的形式              墙   人  糖浆   糖泡 道具
        for (int j = 0; j < GameConstants.SHU; j++)
            for (int i = 0; i < GameConstants.HENG; i++) {
                if (map.boommap[i][j] != null) {
                    map.boommap[i][j].getBallIcon().paintIcon(this, page, i * 50, j * 50 + 200);
                    map.boommap[i][j].addTime();
                }
                if (map.wallmap[i][j] != null && (!map.wallmap[i][j].isRuin()))
                    map.wallmap[i][j].getImage().paintIcon(this, page, i * 50, j * 50 - 12 + 200);
                if (map.daojumap[i][j] != null) {
                    map.daojumap[i][j].getNow().paintIcon(this, page, i * 50, j * 50 + 200);
                    map.daojumap[i][j].beExo();
                }
                if (map.expmap[i][j] != null) {
                    map.expmap[i][j].getImage().paintIcon(this, page, i * 50, j * 50 + 200);
                    map.expmap[i][j].addTime();
                }
                if (p1.getHeng() == i && p1.getShu() == j && p2.getShu() != j)
                    p1.now.paintIcon(this, page, p1.getx(), p1.gety() + 200);
                if (p2.getHeng() == i && p2.getShu() == j && p1.getShu() != j)
                    p2.now.paintIcon(this, page, p2.getx(), p2.gety() + 200);
                if (p2.X == i && p2.Y == j)
                    p2.now.paintIcon(this, page, p2.getx(), p2.gety() + 200);
                if (p1.getShu() == j && p2.getShu() == j && p1.Y > p2.Y) {
                    p2.now.paintIcon(this, page, p2.getx(), p2.gety() + 200);
                    p1.now.paintIcon(this, page, p1.getx(), p1.gety() + 200);
                }
                if (p1.getShu() == j && p2.getShu() == j && p1.Y <= p2.Y) {
                    p1.now.paintIcon(this, page, p1.getx(), p1.gety() + 200);
                    p2.now.paintIcon(this, page, p2.getx(), p2.gety() + 200);
                }
            }

    }

    //时间监听*******************************************************************************************
    public class Mytime implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }

    public JButton getGobackBtn() {
        return gobackBtn;
    }

    public JButton getExitBtn() {
        return exitBtn;
    }
}
