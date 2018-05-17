import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;


public class Mypanel extends JPanel implements ActionListener {

    static int which = 1;
    static BattleJingji fight;
    static BattleAI fight2;
    static BattleBiwu fight3;
    static HomePanel homePanel;
    static Help help;

    static ModeSelectPanel modeSelectPanel;

    static zhanshiwin zhanwin;
    static baoziwin baowin;
    static zhanshishu zhanshu;
    static pingju ping;
    private Random a;
    static CardLayout lay = new CardLayout();

    public Mypanel() {
        zhanwin = new zhanshiwin();
        baowin = new baoziwin();
        zhanshu = new zhanshishu();
        ping = new pingju();

        fight3 = new BattleBiwu();
        fight2 = new BattleAI();
        fight = new BattleJingji();
        homePanel = new HomePanel();
        help = new Help();

        modeSelectPanel = new ModeSelectPanel();

        setLayout(lay);
        setFocusable(true);

        this.add(fight, "fight");
        this.add(fight2, "fight2");
        this.add(fight3, "fight3");

        this.add(homePanel, "homePanel");
        this.add(help, "help");

        this.add(modeSelectPanel, "modeSelectPanel");

        this.add(zhanshu, "zhanshu");
        this.add(baowin, "baowin");
        this.add(zhanwin, "zhanwin");
        this.add(ping, "ping");

        lay.show(this, "homePanel");

        HomePanel.newgame.addActionListener(this);

        HomePanel.bangzhu.addActionListener(this);

        Help.menuBtn.addActionListener(this);

        modeSelectPanel.menuBtn.addActionListener(this);
        modeSelectPanel.youxi.addActionListener(this);
        modeSelectPanel.biwu.addActionListener(this);
        modeSelectPanel.shuimian.addActionListener(this);
        modeSelectPanel.kuangdong.addActionListener(this);
        modeSelectPanel.make.addActionListener(this);
        modeSelectPanel.biwuModeBtn.addActionListener(this);
        modeSelectPanel.jingjiModeBtn.addActionListener(this);
        modeSelectPanel.aiModebtn.addActionListener(this);

        a = new Random();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Music.music[1].play();
        if (e.getSource() == homePanel.newgame) {
            lay.show(this, "modeSelectPanel");
        }
        if (e.getSource() == homePanel.bangzhu) {
            lay.show(this, "help");
        }
        if (e.getSource() == help.menuBtn) {
            lay.show(this, "homePanel");
        }
        if (e.getSource() == modeSelectPanel.menuBtn) {
            lay.show(this, "homePanel");
        }
        if (e.getSource() == modeSelectPanel.make) {
            MapMakerFrame make = new MapMakerFrame();
            MapMakerFrame.frame.setVisible(true);
            Play.frame.setVisible(false);
        }

        if (e.getSource() == ModeSelectPanel.youxi) {
            Music.music[9].stop();
            Music.music[6].play();

            if (Play.moshi == 1) {
                lay.show(this, "fight2");
                Play.zanting = 1;
                ;
                Music.music[11].loop();
                addKeyListener(BattleAI.p1);
            }
            if (Play.moshi == 3) {
                lay.show(this, "fight3");
                Music.music[3].loop();

                addKeyListener(BattleBiwu.p1);
                addKeyListener(BattleBiwu.p2);
            }
            if (Play.moshi == 2) {
                if (which == 1) Music.music[3].loop();
                if (which == 2) Music.music[10].loop();
                if (which == 3) Music.music[11].loop();
                if (which == 4) Music.music[14].loop();
                lay.show(this, "fight");
                addKeyListener(BattleJingji.p1);
                addKeyListener(BattleJingji.p2);
            }
        }

        if (e.getSource() == modeSelectPanel.biwu) {
            for (int j = 0; j < BattleJingji.shu; j++)
                for (int i = 0; i < BattleJingji.heng; i++) {
                    Map.wallmap[i][j] = null;
                }
            which = 1;
            WallMap.biwu1();
            Map.dimian = Map.dimian1;
        }

        if (e.getSource() == modeSelectPanel.shuimian) {
            for (int j = 0; j < BattleJingji.shu; j++)
                for (int i = 0; i < BattleJingji.heng; i++) {
                    Map.wallmap[i][j] = null;
                }
            which = 2;
            WallMap.putong();
            Map.dimian = Map.dimian2;
        }
        if (e.getSource() == ModeSelectPanel.kuangdong) {

            for (int j = 0; j < BattleJingji.shu; j++)
                for (int i = 0; i < BattleJingji.heng; i++)
                    Map.wallmap[i][j] = null;
            which = 3;
            WallMap.maoxian();
            Map.dimian = Map.dimian3;
        }
        if (e.getSource() == ModeSelectPanel.biwuModeBtn) {
            Play.moshi = 3;
            BattleBiwu.p1.X = a.nextInt(500) + 50;
            BattleBiwu.p1.Y = a.nextInt(300) + 50;
            while (Map.isWall(BattleBiwu.p1.getHeng(), BattleBiwu.p1.getShu())) {
                BattleBiwu.p1.X = a.nextInt(500) + 50;
                BattleBiwu.p1.Y = a.nextInt(300) + 50;
            }

            BattleBiwu.p2.X = a.nextInt(500) + 50;
            BattleBiwu.p2.Y = a.nextInt(300) + 50;
            while (Map.isWall(BattleBiwu.p2.getHeng(), BattleBiwu.p2.getShu())) {
                BattleBiwu.p2.X = a.nextInt(500) + 50;
                BattleBiwu.p2.Y = a.nextInt(300) + 50;
            }
        }
        if (e.getSource() == ModeSelectPanel.aiModebtn) {
            Play.moshi = 1;
            BattleAI.p1.X = 75;
            BattleAI.p1.Y = 25;

            BattleAI.p6.X = a.nextInt(500) + 50;
            BattleAI.p6.Y = a.nextInt(300) + 50;
            while (Map.isWall(BattleAI.p6.getHeng(), BattleAI.p6.getShu()) ||
                    (BattleAI.p6.getHeng() == BattleAI.p1.getHeng() &&
                            BattleAI.p6.getShu() == BattleAI.p1.getShu())) {
                BattleAI.p6.X = a.nextInt(500) + 50;
                BattleAI.p6.Y = a.nextInt(300) + 50;
            }

        }

        if (e.getSource() == ModeSelectPanel.jingjiModeBtn) {
            Play.moshi = 2;
            BattleJingji.p1.X = a.nextInt(500) + 50;
            BattleJingji.p1.Y = a.nextInt(300) + 50;
            while (Map.isWall(BattleJingji.p1.getHeng(), BattleJingji.p1.getShu())) {
                BattleJingji.p1.X = a.nextInt(500) + 50;
                BattleJingji.p1.Y = a.nextInt(300) + 50;
            }
            BattleJingji.p2.X = a.nextInt(500) + 50;
            BattleJingji.p2.Y = a.nextInt(300) + 50;
            while (Map.isWall(BattleJingji.p2.getHeng(), BattleJingji.p2.getShu())) {
                BattleJingji.p2.X = a.nextInt(500) + 50;
                BattleJingji.p2.Y = a.nextInt(300) + 50;
            }
        }
    }
}
