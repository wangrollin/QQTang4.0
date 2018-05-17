import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Mypanel extends JPanel implements ActionListener {

    static int which = 1;
    static BattleCanvas fight;
    static Battlemaoxian fight2;
    static Battlebiwu fight3;
    static NEWONE newone;
    static HELP help;

    static CHOOSE choose;

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

        fight3 = new Battlebiwu();
        fight2 = new Battlemaoxian();
        fight = new BattleCanvas();
        newone = new NEWONE();
        help = new HELP();

        choose = new CHOOSE();

        setLayout(lay);
        setFocusable(true);

        this.add(fight, "fight");
        this.add(fight2, "fight2");
        this.add(fight3, "fight3");

        this.add(newone, "newone");
        this.add(help, "help");

        this.add(choose, "choose");

        this.add(zhanshu, "zhanshu");
        this.add(baowin, "baowin");
        this.add(zhanwin, "zhanwin");
        this.add(ping, "ping");

        lay.show(this, "newone");

        newone.newgame.addActionListener(this);

        newone.bangzhu.addActionListener(this);

        help.caidan.addActionListener(this);

        choose.caidan.addActionListener(this);
        choose.youxi.addActionListener(this);
        choose.biwu.addActionListener(this);
        choose.shuimian.addActionListener(this);
        choose.kuangdong.addActionListener(this);
        choose.make.addActionListener(this);
        choose.biwu1.addActionListener(this);
        choose.jingji.addActionListener(this);
        choose.maoxian.addActionListener(this);

        a = new Random();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Music.music[1].play();
        if (e.getSource() == newone.newgame) {
            lay.show(this, "choose");
        }
        if (e.getSource() == newone.bangzhu) {
            lay.show(this, "help");
        }
        if (e.getSource() == help.caidan) {
            lay.show(this, "newone");
        }
        if (e.getSource() == choose.caidan) {
            lay.show(this, "newone");
        }
        if (e.getSource() == choose.make) {
            Maker make = new Maker();
            Maker.frame.setVisible(true);
            Play.frame.setVisible(false);
        }

        if (e.getSource() == CHOOSE.youxi) {
            Music.music[9].stop();
            Music.music[6].play();

            if (Play.moshi == 1) {
                lay.show(this, "fight2");
                Play.zanting = 1;
                ;
                Music.music[11].loop();
                addKeyListener(Battlemaoxian.p1);
            }
            if (Play.moshi == 3) {
                lay.show(this, "fight3");
                Music.music[3].loop();

                addKeyListener(Battlebiwu.p1);
                addKeyListener(Battlebiwu.p2);
            }
            if (Play.moshi == 2) {
                if (which == 1) Music.music[3].loop();
                if (which == 2) Music.music[10].loop();
                if (which == 3) Music.music[11].loop();
                if (which == 4) Music.music[14].loop();
                lay.show(this, "fight");
                addKeyListener(BattleCanvas.p1);
                addKeyListener(BattleCanvas.p2);
            }
        }

        if (e.getSource() == choose.biwu) {
            for (int j = 0; j < BattleCanvas.shu; j++)
                for (int i = 0; i < BattleCanvas.heng; i++) {
                    Map.wallmap[i][j] = null;
                }
            which = 1;
            Wallmap.biwu1();
            Map.dimian = Map.dimian1;
        }

        if (e.getSource() == choose.shuimian) {
            for (int j = 0; j < BattleCanvas.shu; j++)
                for (int i = 0; i < BattleCanvas.heng; i++) {
                    Map.wallmap[i][j] = null;
                }
            which = 2;
            Wallmap.putong();
            Map.dimian = Map.dimian2;
        }
        if (e.getSource() == CHOOSE.kuangdong) {

            for (int j = 0; j < BattleCanvas.shu; j++)
                for (int i = 0; i < BattleCanvas.heng; i++)
                    Map.wallmap[i][j] = null;
            which = 3;
            Wallmap.maoxian();
            Map.dimian = Map.dimian3;
        }
        if (e.getSource() == CHOOSE.biwu1) {
            Play.moshi = 3;
            Battlebiwu.p1.X = a.nextInt(500) + 50;
            Battlebiwu.p1.Y = a.nextInt(300) + 50;
            while (Map.isWall(Battlebiwu.p1.getHeng(), Battlebiwu.p1.getShu())) {
                Battlebiwu.p1.X = a.nextInt(500) + 50;
                Battlebiwu.p1.Y = a.nextInt(300) + 50;
            }

            Battlebiwu.p2.X = a.nextInt(500) + 50;
            Battlebiwu.p2.Y = a.nextInt(300) + 50;
            while (Map.isWall(Battlebiwu.p2.getHeng(), Battlebiwu.p2.getShu())) {
                Battlebiwu.p2.X = a.nextInt(500) + 50;
                Battlebiwu.p2.Y = a.nextInt(300) + 50;
            }
        }
        if (e.getSource() == CHOOSE.maoxian) {
            Play.moshi = 1;
            Battlemaoxian.p1.X = 75;
            Battlemaoxian.p1.Y = 25;

            Battlemaoxian.p6.X = a.nextInt(500) + 50;
            Battlemaoxian.p6.Y = a.nextInt(300) + 50;
            while (Map.isWall(Battlemaoxian.p6.getHeng(), Battlemaoxian.p6.getShu()) ||
                    (Battlemaoxian.p6.getHeng() == Battlemaoxian.p1.getHeng() &&
                            Battlemaoxian.p6.getShu() == Battlemaoxian.p1.getShu())) {
                Battlemaoxian.p6.X = a.nextInt(500) + 50;
                Battlemaoxian.p6.Y = a.nextInt(300) + 50;
            }

        }

        if (e.getSource() == CHOOSE.jingji) {
            Play.moshi = 2;
            BattleCanvas.p1.X = a.nextInt(500) + 50;
            BattleCanvas.p1.Y = a.nextInt(300) + 50;
            while (Map.isWall(BattleCanvas.p1.getHeng(), BattleCanvas.p1.getShu())) {
                BattleCanvas.p1.X = a.nextInt(500) + 50;
                BattleCanvas.p1.Y = a.nextInt(300) + 50;
            }
            BattleCanvas.p2.X = a.nextInt(500) + 50;
            BattleCanvas.p2.Y = a.nextInt(300) + 50;
            while (Map.isWall(BattleCanvas.p2.getHeng(), BattleCanvas.p2.getShu())) {
                BattleCanvas.p2.X = a.nextInt(500) + 50;
                BattleCanvas.p2.Y = a.nextInt(300) + 50;
            }
        }
    }
}
