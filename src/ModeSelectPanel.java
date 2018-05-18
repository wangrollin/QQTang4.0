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

    public ModeSelectPanel() {
        bgIcon = new ImageIcon("111.png");
        arrow = new ImageIcon("guang.png");

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
         * map button
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
        if (Play.moshi == 1) arrow.paintIcon(this, page, 114, 180);
        if (Play.moshi == 2) arrow.paintIcon(this, page, 267, 180);
        if (Play.moshi == 3) arrow.paintIcon(this, page, 413, 180);
        if (MyPanelCard.which == 1) arrow.paintIcon(this, page, 407, 437);
        if (MyPanelCard.which == 2) arrow.paintIcon(this, page, 630, 302);
        if (MyPanelCard.which == 3) arrow.paintIcon(this, page, 407, 314);
        if (MyPanelCard.which == 4) arrow.paintIcon(this, page, 541, 494);
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
