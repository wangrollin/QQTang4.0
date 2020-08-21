package panel;

import constants.GameConstants;
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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

    public int getGroundType() {
        return groundType;
    }

    public void setGroundType(int groundType) {
        this.groundType = groundType;
    }


    private Wall[][] wallMap;

    public Wall[][] getWallMap() {
        return wallMap;
    }

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
        MusicTool.Musicload();
        MusicTool.HOME_BGM.loop();
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
        MusicTool.PRESS_BUTTON.play();
        if (e.getSource() == homePanel.getExitBtn() || e.getSource() == fighterWinPanel.getExitBtn() ||
                e.getSource() == baoziWinPanel.getExitbtn() || e.getSource() == fighterLosePanel.getExitBtn()
                || e.getSource() == dagfallPanel.getExitBtn() || e.getSource() == battleBiwuPanel.getExitBtn()
                || e.getSource() == battleJingjiPanel.getExitBtn() || e.getSource() == battleAIPanel.getExitBtn()) {
            System.exit(0);
        } else if (e.getSource() == fighterWinPanel.getGobackBtn()
                || e.getSource() == baoziWinPanel.getGobackBtn()
                || e.getSource() == fighterLosePanel.getGobackBtn()
                || e.getSource() == dagfallPanel.getGobackBtn()) {
            MusicTool.stopAllMusic();
            MusicTool.HOME_BGM.loop();
            cardLayout.show(this, "modeSelectPanel");
        } else if (e.getSource() == battleBiwuPanel.getGobackBtn()) {
            battleBiwuPanel.closeGameAndJumpAway("modeSelectPanel", MusicTool.HOME_BGM);
        } else if (e.getSource() == battleJingjiPanel.getGobackBtn()) {
            battleJingjiPanel.closeGameAndJumpAway("modeSelectPanel", MusicTool.HOME_BGM);
        } else if (e.getSource() == battleAIPanel.getGobackBtn()) {
            battleAIPanel.closeGameAndJumpAway("modeSelectPanel", MusicTool.HOME_BGM);
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
            MusicTool.HOME_BGM.stop();

            if (this.gameMode == GameConstants.AI_MODE) {
                //Play.zanting = 1;
                battleAIPanel.initAndShowAndStartGame();
            } else if (this.gameMode == GameConstants.BIWU_MODE) {
                battleBiwuPanel.initAndShowAndStartGame();
            } else if (this.gameMode == GameConstants.JINGJI_MODE) {
                battleJingjiPanel.initAndShowAndStartGame();
            }
        }

        /**
         * modeSelectPanel select wallmap button
         */
        else if (e.getSource() == modeSelectPanel.getBiwuMapbtn()) {
            this.wallMapType = GameConstants.BIWU_MAP;
            this.wallMap = WallMapTool.createBiwuMap();


            this.groundType = GameConstants.BIWU_GROUND;
        } else if (e.getSource() == modeSelectPanel.getShuimianMapBtn()) {
            this.wallMapType = GameConstants.SHUIMIAN_MAP;
            this.wallMap = WallMapTool.createShuimianMap();
            this.groundType = GameConstants.SHUIMIAN_GROUND;
        } else if (e.getSource() == modeSelectPanel.getKuangdongMapBtn()) {
            this.wallMapType = GameConstants.KUANGDONG_MAP;
            this.wallMap = WallMapTool.createKuangdongMap();
            this.groundType = GameConstants.KUANGDONG_GROUND;
        } else if (e.getSource() == modeSelectPanel.getDiyMapBtn()) {
            MapMakerFrame mapMakerFrame = new MapMakerFrame(this.play, this);
            this.play.setVisible(false);
            mapMakerFrame.setVisible(true);
        }

        /**
         * modeSelectPanel game mode button
         */
        else if (e.getSource() == modeSelectPanel.getBiwuModeBtn()) {
            this.gameMode = GameConstants.BIWU_MODE;
        } else if (e.getSource() == modeSelectPanel.getAiModebtn()) {
            this.gameMode = GameConstants.AI_MODE;
        } else if (e.getSource() == modeSelectPanel.getJingjiModeBtn()) {
            this.gameMode = GameConstants.JINGJI_MODE;
        }
    }
}
