package panel;

import constants.GameConstants;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class ModeSelectPanel extends JPanel {
    private JButton gobackBtn, startGameBtn;
    private ImageIcon bgIcon;

    private JButton biwuMapbtn, shuimianMapBtn, kuangdongMapBtn, diyMapBtn;

    private JButton biwuModeBtn, jingjiModeBtn, aiModebtn;

    private ImageIcon arrow;

    private MyPanelCard myPanelCard;
    public ModeSelectPanel(MyPanelCard myPanelCard) {
        bgIcon = new ImageIcon("111.png");
        arrow = new ImageIcon("guang.png");
        this.myPanelCard = myPanelCard;
        /**
         * game mode button
         */
        biwuModeBtn = new JButton(new ImageIcon("比武.png"));
        biwuModeBtn.setBounds(379, 63, 91, 95);
        add(biwuModeBtn);

        jingjiModeBtn = new JButton(new ImageIcon("普通.png"));
        jingjiModeBtn.setBounds(220, 61, 109, 106);
        add(jingjiModeBtn);

        aiModebtn = new JButton(new ImageIcon("怪物模式.png"));
        aiModebtn.setBounds(50, 59, 117, 108);
        add(aiModebtn);

        /**
         * maps button
         */
        biwuMapbtn = new JButton(new ImageIcon("展示1.png"));
        biwuMapbtn.setBounds(248, 389, 153, 96);
        add(biwuMapbtn);

        shuimianMapBtn = new JButton(new ImageIcon("展示2.png"));
        shuimianMapBtn.setBounds(475, 269, 150, 94);
        add(shuimianMapBtn);

        kuangdongMapBtn = new JButton(new ImageIcon("展示3.png"));
        kuangdongMapBtn.setBounds(249, 267, 150, 96);
        add(kuangdongMapBtn);

        diyMapBtn = new JButton(new ImageIcon("diy.png"));
        diyMapBtn.setBounds(469, 389, 159, 101);
        add(diyMapBtn);

        /**
         * functional button
         */
        gobackBtn = new JButton(new ImageIcon("退.png"));
        gobackBtn.setBounds(583, 534, 50, 50);
        add(gobackBtn);

        startGameBtn = new JButton(new ImageIcon("开.png"));
        startGameBtn.setBounds(41, 381, 95, 91);
        add(startGameBtn);

        /**
         * normal settings
         */
        setFocusable(true);
        setLayout(null);
        setPreferredSize(new Dimension(650, 600));
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        bgIcon.paintIcon(this, page, 0, 0);
        if (myPanelCard.getGameMode() == GameConstants.AI_MODE) arrow.paintIcon(this, page, 114, 180);
        if (myPanelCard.getGameMode() == GameConstants.JINGJI_MODE) arrow.paintIcon(this, page, 267, 180);
        if (myPanelCard.getGameMode() == GameConstants.BIWU_MODE) arrow.paintIcon(this, page, 413, 180);
        if (myPanelCard.getWallMapType() == GameConstants.BIWU_MAP) arrow.paintIcon(this, page, 407, 437);
        if (myPanelCard.getWallMapType() == GameConstants.SHUIMIAN_MAP) arrow.paintIcon(this, page, 630, 302);
        if (myPanelCard.getWallMapType() == GameConstants.KUANGDONG_MAP) arrow.paintIcon(this, page, 407, 314);
        if (myPanelCard.getWallMapType() == GameConstants.DIY_MAP) arrow.paintIcon(this, page, 541, 494);
        repaint();
    }

    public JButton getAiModebtn() {
        return aiModebtn;
    }

    public JButton getJingjiModeBtn() {
        return jingjiModeBtn;
    }

    public JButton getBiwuModeBtn() {
        return biwuModeBtn;
    }

    public JButton getBiwuMapbtn() {
        return biwuMapbtn;
    }

    public JButton getShuimianMapBtn() {
        return shuimianMapBtn;
    }

    public JButton getKuangdongMapBtn() {
        return kuangdongMapBtn;
    }

    public JButton getDiyMapBtn() {
        return diyMapBtn;
    }

    public JButton getGobackBtn() {
        return gobackBtn;
    }

    public JButton getStartGameBtn() {
        return startGameBtn;
    }
}
