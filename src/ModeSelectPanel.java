import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class ModeSelectPanel extends JPanel {
    protected static JButton menuBtn, youxi;
    protected static ImageIcon icaidan, iyouxi, beijing;

    protected static ImageIcon biwuMapIcon, shuimianMapIcon, kuangdongMapIcon, diyMapIcon;
    protected static JButton biwu, shuimian, kuangdong, make;

    protected static ImageIcon biwuModeIcon, jingjiModeIcon, aiModeIcon;
    protected static JButton biwuModeBtn, jingjiModeBtn, aiModebtn;
    private ImageIcon arrow;

    public ModeSelectPanel() {
        arrow = new ImageIcon("guang.png");
        biwuModeIcon = new ImageIcon("比武.png");
        biwuModeBtn = new JButton(biwuModeIcon);
        biwuModeBtn.setBounds(379, 63, 91, 95);
        add(biwuModeBtn);
        jingjiModeIcon = new ImageIcon("普通.png");
        jingjiModeBtn = new JButton(jingjiModeIcon);
        jingjiModeBtn.setBounds(220, 61, 109, 106);
        add(jingjiModeBtn);
        aiModeIcon = new ImageIcon("怪物模式.png");
        aiModebtn = new JButton(aiModeIcon);
        aiModebtn.setBounds(50, 59, 117, 108);
        add(aiModebtn);

        biwuMapIcon = new ImageIcon("展示1.png");
        shuimianMapIcon = new ImageIcon("展示2.png");
        kuangdongMapIcon = new ImageIcon("展示3.png");

        biwu = new JButton(biwuMapIcon);
        biwu.setBounds(248, 389, 153, 96);

        shuimian = new JButton(shuimianMapIcon);
        shuimian.setBounds(475, 269, 150, 94);

        kuangdong = new JButton(kuangdongMapIcon);
        kuangdong.setBounds(249, 267, 150, 96);

        diyMapIcon = new ImageIcon("diy.png");
        make = new JButton(diyMapIcon);
        make.setBounds(469, 389, 159, 101);

        add(biwu);
        add(shuimian);
        add(kuangdong);
        add(make);

        beijing = new ImageIcon("111.png");

        icaidan = new ImageIcon("退.png");
        menuBtn = new JButton(icaidan);
        menuBtn.setBounds(583, 534, 50, 50);

        iyouxi = new ImageIcon("开.png");
        youxi = new JButton(iyouxi);
        youxi.setBounds(41, 381, 95, 91);

        add(menuBtn);
        add(youxi);

        setFocusable(true);
        setLayout(null);
        setPreferredSize(new Dimension(650, 600));
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        beijing.paintIcon(this, page, 0, 0);
        if (Play.moshi == 1) arrow.paintIcon(this, page, 114, 180);
        if (Play.moshi == 2) arrow.paintIcon(this, page, 267, 180);
        if (Play.moshi == 3) arrow.paintIcon(this, page, 413, 180);
        if (Mypanel.which == 1) arrow.paintIcon(this, page, 407, 437);
        if (Mypanel.which == 2) arrow.paintIcon(this, page, 630, 302);
        if (Mypanel.which == 3) arrow.paintIcon(this, page, 407, 314);
        if (Mypanel.which == 4) arrow.paintIcon(this, page, 541, 494);
        repaint();
    }
}
