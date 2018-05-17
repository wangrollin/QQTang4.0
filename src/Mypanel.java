import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;


public class Mypanel extends JPanel implements ActionListener {
    /**
     * all panels
     */
    static HomePanel homePanel;
    static HelpPanel helpPanel;
    static ModeSelectPanel modeSelectPanel;

    static BattleJingjiPanel battleJingjiPanel;
    static BattleAIPanel battleAIPanel;
    static BattleBiwuPanel battleBiwuPanel;

    static FighterWinPanel fighterWinPanel;
    static BaoziWinPanel baoziWinPanel;
    static FighterLosePanel fighterLosePanel;
    static DagfallPanel dagfallPanel;

    static int which = 1;
    private Random a;
    static CardLayout lay = new CardLayout();

    Mypanel() {
        fighterWinPanel = new FighterWinPanel();
        baoziWinPanel = new BaoziWinPanel();
        fighterLosePanel = new FighterLosePanel();
        dagfallPanel = new DagfallPanel();

        battleBiwuPanel = new BattleBiwuPanel();
        battleAIPanel = new BattleAIPanel();
        battleJingjiPanel = new BattleJingjiPanel();
        homePanel = new HomePanel();
        helpPanel = new HelpPanel();

        modeSelectPanel = new ModeSelectPanel();

        setLayout(lay);
        setFocusable(true);

        add(homePanel, "homePanel");
        add(helpPanel, "helpPanel");
        add(modeSelectPanel, "modeSelectPanel");
        add(battleJingjiPanel, "battleJingjiPanel");
        add(battleAIPanel, "battleAIPanel");
        add(battleBiwuPanel, "battleBiwuPanel");
        add(fighterLosePanel, "fighterLosePanel");
        add(baoziWinPanel, "baoziWinPanel");
        add(fighterWinPanel, "fighterWinPanel");
        add(dagfallPanel, "dagfallPanel");

        lay.show(this, "homePanel");

        homePanel.getStartGameBtn().addActionListener(this);
        homePanel.getHelpBtn().addActionListener(this);
        homePanel.getExitBtn().addActionListener(this);

        helpPanel.getGobackBtn().addActionListener(this);

        modeSelectPanel.getGobackBtn().addActionListener(this);
        modeSelectPanel.getStartGameBtn().addActionListener(this);
        modeSelectPanel.getBiwuMapbtn().addActionListener(this);
        modeSelectPanel.getShuimianMapBtn().addActionListener(this);
        modeSelectPanel.getKuangdongMapBtn().addActionListener(this);
        modeSelectPanel.getDiyMapBtn().addActionListener(this);
        modeSelectPanel.getBiwuModeBtn().addActionListener(this);
        modeSelectPanel.getJingjiModeBtn().addActionListener(this);
        modeSelectPanel.getAiModebtn().addActionListener(this);

        a = new Random();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Music.music[1].play();
        // TODO
        if (e.getSource() == homePanel.getExitBtn() || e.getSource() == FighterWinPanel.jieshu ||
                e.getSource() == BaoziWinPanel.jieshu || e.getSource() == FighterLosePanel.jieshu
                || e.getSource() == DagfallPanel.jieshu || e.getSource() == BattleBiwuPanel.jieshu
                || e.getSource() == BattleJingjiPanel.jieshu || e.getSource() == BattleAIPanel.jieshu) {
            System.exit(0);
        } else if (e.getSource() == homePanel.getStartGameBtn()) {
            lay.show(this, "modeSelectPanel");
        } else if (e.getSource() == homePanel.getHelpBtn()) {
            lay.show(this, "helpPanel");
        } else if (e.getSource() == helpPanel.getGobackBtn()) {
            lay.show(this, "homePanel");
        } else if (e.getSource() == modeSelectPanel.getGobackBtn()) {
            lay.show(this, "homePanel");
        } else if (e.getSource() == modeSelectPanel.getDiyMapBtn()) {
            MapMakerFrame make = new MapMakerFrame();
            MapMakerFrame.frame.setVisible(true);
            Play.frame.setVisible(false);
        } else if (e.getSource() == modeSelectPanel.getStartGameBtn()) {
            Music.music[9].stop();
            Music.music[6].play();

            if (Play.moshi == 1) {
                lay.show(this, "battleAIPanel");
                Play.zanting = 1;
                ;
                Music.music[11].loop();
                addKeyListener(BattleAIPanel.p1);
            }
            if (Play.moshi == 3) {
                lay.show(this, "battleBiwuPanel");
                Music.music[3].loop();

                addKeyListener(BattleBiwuPanel.p1);
                addKeyListener(BattleBiwuPanel.p2);
            }
            if (Play.moshi == 2) {
                if (which == 1) Music.music[3].loop();
                if (which == 2) Music.music[10].loop();
                if (which == 3) Music.music[11].loop();
                if (which == 4) Music.music[14].loop();
                lay.show(this, "battleJingjiPanel");
                addKeyListener(BattleJingjiPanel.p1);
                addKeyListener(BattleJingjiPanel.p2);
            }
        } else if (e.getSource() == modeSelectPanel.getBiwuMapbtn()) {
            for (int j = 0; j < BattleJingjiPanel.shu; j++)
                for (int i = 0; i < BattleJingjiPanel.heng; i++) {
                    Map.wallmap[i][j] = null;
                }
            which = 1;
            WallMap.biwu1();
            Map.dimian = Map.dimian1;
        } else if (e.getSource() == modeSelectPanel.getShuimianMapBtn()) {
            for (int j = 0; j < BattleJingjiPanel.shu; j++)
                for (int i = 0; i < BattleJingjiPanel.heng; i++) {
                    Map.wallmap[i][j] = null;
                }
            which = 2;
            WallMap.putong();
            Map.dimian = Map.dimian2;
        } else if (e.getSource() == modeSelectPanel.getKuangdongMapBtn()) {

            for (int j = 0; j < BattleJingjiPanel.shu; j++)
                for (int i = 0; i < BattleJingjiPanel.heng; i++)
                    Map.wallmap[i][j] = null;
            which = 3;
            WallMap.maoxian();
            Map.dimian = Map.dimian3;
        } else if (e.getSource() == modeSelectPanel.getBiwuModeBtn()) {
            Play.moshi = 3;
            BattleBiwuPanel.p1.X = a.nextInt(500) + 50;
            BattleBiwuPanel.p1.Y = a.nextInt(300) + 50;
            while (Map.isWall(BattleBiwuPanel.p1.getHeng(), BattleBiwuPanel.p1.getShu())) {
                BattleBiwuPanel.p1.X = a.nextInt(500) + 50;
                BattleBiwuPanel.p1.Y = a.nextInt(300) + 50;
            }

            BattleBiwuPanel.p2.X = a.nextInt(500) + 50;
            BattleBiwuPanel.p2.Y = a.nextInt(300) + 50;
            while (Map.isWall(BattleBiwuPanel.p2.getHeng(), BattleBiwuPanel.p2.getShu())) {
                BattleBiwuPanel.p2.X = a.nextInt(500) + 50;
                BattleBiwuPanel.p2.Y = a.nextInt(300) + 50;
            }
        } else if (e.getSource() == modeSelectPanel.getAiModebtn()) {
            Play.moshi = 1;
            BattleAIPanel.p1.X = 75;
            BattleAIPanel.p1.Y = 25;

            BattleAIPanel.p6.X = a.nextInt(500) + 50;
            BattleAIPanel.p6.Y = a.nextInt(300) + 50;
            while (Map.isWall(BattleAIPanel.p6.getHeng(), BattleAIPanel.p6.getShu()) ||
                    (BattleAIPanel.p6.getHeng() == BattleAIPanel.p1.getHeng() &&
                            BattleAIPanel.p6.getShu() == BattleAIPanel.p1.getShu())) {
                BattleAIPanel.p6.X = a.nextInt(500) + 50;
                BattleAIPanel.p6.Y = a.nextInt(300) + 50;
            }

        } else if (e.getSource() == modeSelectPanel.getJingjiModeBtn()) {
            Play.moshi = 2;
            BattleJingjiPanel.p1.X = a.nextInt(500) + 50;
            BattleJingjiPanel.p1.Y = a.nextInt(300) + 50;
            while (Map.isWall(BattleJingjiPanel.p1.getHeng(), BattleJingjiPanel.p1.getShu())) {
                BattleJingjiPanel.p1.X = a.nextInt(500) + 50;
                BattleJingjiPanel.p1.Y = a.nextInt(300) + 50;
            }
            BattleJingjiPanel.p2.X = a.nextInt(500) + 50;
            BattleJingjiPanel.p2.Y = a.nextInt(300) + 50;
            while (Map.isWall(BattleJingjiPanel.p2.getHeng(), BattleJingjiPanel.p2.getShu())) {
                BattleJingjiPanel.p2.X = a.nextInt(500) + 50;
                BattleJingjiPanel.p2.Y = a.nextInt(300) + 50;
            }
        }
    }
}
