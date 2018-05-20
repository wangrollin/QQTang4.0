package panel;

import constants.GameConstants;
import element.Maps;
import element.MusicTool;
import element.Wall;
import element.WallMapTool;
import map_maker.MapMakerFrame;
import panel.battle.BattleAIPanel;
import panel.battle.BattleBiwuPanel;
import panel.battle.BattleJingjiPanel;
import panel.result.BaoziWinPanel;
import panel.result.DagfallPanel;
import panel.result.FighterLosePanel;
import panel.result.FighterWinPanel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;


public class MyPanelCard extends JPanel implements ActionListener {

    /**
     * all panels
     */
    private HomePanel homePanel;
    private HelpPanel helpPanel;
    private ModeSelectPanel modeSelectPanel;
    private BattleJingjiPanel battleJingjiPanel;
    private BattleAIPanel battleAIPanel;
    private BattleBiwuPanel battleBiwuPanel;

    private FighterWinPanel fighterWinPanel;
    private BaoziWinPanel baoziWinPanel;
    private FighterLosePanel fighterLosePanel;
    private DagfallPanel dagfallPanel;

    private Play play;

    private int gameMode = GameConstants.AI_MODE;
    public int getGameMode() {
        return gameMode;
    }

    private int wallMapType = GameConstants.KUANGDONG_MAP;
    public int getWallMapType() {
        return wallMapType;
    }
    public void setWallMapType(int wallMapType) {
        this.wallMapType = wallMapType;
    }


    private int groundType = GameConstants.KUANGDONG_GROUND;
    public void setGroundType(int groundType) {
        this.groundType = groundType;
    }

    private Wall[][] wallMap;
    public void setWallMap(Wall[][] wallMap) {
        this.wallMap = wallMap;
    }

    //private Random random;
    private CardLayout cardLayout;

    public MyPanelCard(Play play) {
        this.play = play;

        cardLayout = new CardLayout();
        setLayout(cardLayout);
        setFocusable(true);

        this.wallMap = WallMapTool.createKuangdongMap();
        /**
         * init panels
         */
        homePanel = new HomePanel();
        helpPanel = new HelpPanel();

        modeSelectPanel = new ModeSelectPanel(this);

        battleBiwuPanel = new BattleBiwuPanel(this, cardLayout);
        battleAIPanel = new BattleAIPanel(this, cardLayout);
        battleJingjiPanel = new BattleJingjiPanel(this, cardLayout);

        fighterWinPanel = new FighterWinPanel();
        baoziWinPanel = new BaoziWinPanel();
        fighterLosePanel = new FighterLosePanel();
        dagfallPanel = new DagfallPanel();

        /**
         * add panels
         */
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

        cardLayout.show(this, "homePanel");
        System.out.println(cardLayout.toString());

        /**
         * add button listener
         */
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

        battleBiwuPanel.getGobackBtn().addActionListener(this);
        battleBiwuPanel.getExitBtn().addActionListener(this);
        battleJingjiPanel.getGobackBtn().addActionListener(this);
        battleJingjiPanel.getExitBtn().addActionListener(this);
        battleAIPanel.getGobackBtn().addActionListener(this);
        battleAIPanel.getExitBtn().addActionListener(this);

        fighterWinPanel.getGobackBtn().addActionListener(this);
        fighterWinPanel.getExitBtn().addActionListener(this);
        baoziWinPanel.getGobackBtn().addActionListener(this);
        baoziWinPanel.getExitbtn().addActionListener(this);
        fighterLosePanel.getGobackBtn().addActionListener(this);
        fighterLosePanel.getExitBtn().addActionListener(this);
        dagfallPanel.getGobackBtn().addActionListener(this);
        dagfallPanel.getExitBtn().addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MusicTool.music[1].play();
        if (e.getSource() == homePanel.getExitBtn() || e.getSource() == fighterWinPanel.getExitBtn() ||
                e.getSource() == baoziWinPanel.getExitbtn() || e.getSource() == fighterLosePanel.getExitBtn()
                || e.getSource() == dagfallPanel.getExitBtn() || e.getSource() == battleBiwuPanel.getExitBtn()
                || e.getSource() == battleJingjiPanel.getExitBtn() || e.getSource() == battleAIPanel.getExitBtn()) {
            System.exit(0);
        } else if (e.getSource() == fighterWinPanel.getGobackBtn() ||
                e.getSource() == baoziWinPanel.getGobackBtn() || e.getSource() == fighterLosePanel.getGobackBtn()
                || e.getSource() == dagfallPanel.getGobackBtn() || e.getSource() == battleBiwuPanel.getGobackBtn()
                || e.getSource() == battleJingjiPanel.getGobackBtn() || e.getSource() == battleAIPanel.getGobackBtn()) {
            // TODO goback
        }

        /**
         * home panel
         */
        else if (e.getSource() == homePanel.getStartGameBtn()) {
            cardLayout.show(this, "modeSelectPanel");
        } else if (e.getSource() == homePanel.getHelpBtn()) {
            cardLayout.show(this, "helpPanel");
        } else if (e.getSource() == helpPanel.getGobackBtn()) {
            cardLayout.show(this, "homePanel");
        }

        /**
         * mode Select Panel
         */
        else if (e.getSource() == modeSelectPanel.getGobackBtn()) {
            cardLayout.show(this, "homePanel");
        } else if (e.getSource() == modeSelectPanel.getStartGameBtn()) {
            MusicTool.music[9].stop();
            MusicTool.music[6].play();

            if (this.gameMode == GameConstants.AI_MODE) {
                /*if (this.wallMapType == GameConstants.BIWU_MAP) {

                } else if (this.wallMapType == GameConstants.SHUIMIAN_MAP) {

                } else if (this.wallMapType == GameConstants.KUANGDONG_MAP) {

                }*/
                battleAIPanel.setWallMap(this.wallMap);
                battleAIPanel.setGroundType(this.groundType);
                battleAIPanel.initPlayerPosition();


                cardLayout.show(this, "battleAIPanel");
                Play.zanting = 1;

                MusicTool.music[11].loop();

                addKeyListener(battleAIPanel.getPlayer1());
            } else if (this.gameMode == GameConstants.BIWU_MODE) {
                battleBiwuPanel.setWallMap(this.wallMap);
                battleAIPanel.setGroundType(this.groundType);
                battleBiwuPanel.initPlayerPosition();

                cardLayout.show(this, "battleBiwuPanel");

                MusicTool.music[3].loop();

                addKeyListener(battleBiwuPanel.getPlayer1());
                addKeyListener(battleBiwuPanel.getPlayer2());
            } else if (this.gameMode == GameConstants.JINGJI_MODE) {
                battleJingjiPanel.setWallMap(this.wallMap);
                battleAIPanel.setGroundType(this.groundType);
                battleJingjiPanel.initPlayerPosition();

                cardLayout.show(this, "battleJingjiPanel");

                if (this.wallMapType == GameConstants.BIWU_MAP) MusicTool.music[3].loop();
                if (this.wallMapType == GameConstants.SHUIMIAN_MAP) MusicTool.music[10].loop();
                if (this.wallMapType == GameConstants.KUANGDONG_MAP) MusicTool.music[11].loop();
                if (this.wallMapType == GameConstants.DIY_MAP) MusicTool.music[14].loop();

                addKeyListener(battleJingjiPanel.getPlayer1());
                addKeyListener(battleJingjiPanel.getPlayer2());
            }
        }

        /**
         * modeSelectPanel select wallmap button
         */
        else if (e.getSource() == modeSelectPanel.getBiwuMapbtn()) {
           /* for (int j = 0; j < GameConstants.SHU; j++)
                for (int i = 0; i < GameConstants.HENG; i++) {
                    element.Maps.wallMap[i][j] = null;
                }
            which = 1;*/
            this.wallMapType = GameConstants.BIWU_MAP;
            this.wallMap = WallMapTool.createBiwuMap();
            this.groundType = GameConstants.BIWU_GROUND;
        } else if (e.getSource() == modeSelectPanel.getShuimianMapBtn()) {
            /*for (int j = 0; j < GameConstants.SHU; j++)
                for (int i = 0; i < GameConstants.HENG; i++) {
                    element.Maps.wallMap[i][j] = null;
                }
            which = 2;*/
            this.wallMapType = GameConstants.SHUIMIAN_MAP;
            //element.WallMapTool.putong();
            this.wallMap = WallMapTool.createShuimianMap();
            this.groundType = GameConstants.SHUIMIAN_GROUND;
        } else if (e.getSource() == modeSelectPanel.getKuangdongMapBtn()) {
            /*for (int j = 0; j < GameConstants.SHU; j++)
                for (int i = 0; i < GameConstants.HENG; i++)
                    element.Maps.wallMap[i][j] = null;
            which = 3;*/
            this.wallMapType = GameConstants.KUANGDONG_MAP;
            //element.WallMapTool.maoxian();
            this.wallMap = WallMapTool.createKuangdongMap();
            this.groundType = GameConstants.KUANGDONG_GROUND;
        }  else if (e.getSource() == modeSelectPanel.getDiyMapBtn()) {
            MapMakerFrame mapMakerFrame = new MapMakerFrame(this.play, this);
            this.play.setVisible(false);
            mapMakerFrame.setVisible(true);
        }

        /**
         * modeSelectPanel game mode button
         */
        else if (e.getSource() == modeSelectPanel.getBiwuModeBtn()) {
            //panel.Play.moshi = 3;
            this.gameMode = GameConstants.BIWU_MODE;
        } else if (e.getSource() == modeSelectPanel.getAiModebtn()) {
            //panel.Play.moshi = 1;
            this.gameMode = GameConstants.AI_MODE;
        } else if (e.getSource() == modeSelectPanel.getJingjiModeBtn()) {
            //panel.Play.moshi = 2;
            this.gameMode = GameConstants.JINGJI_MODE;
        }
    }
}
