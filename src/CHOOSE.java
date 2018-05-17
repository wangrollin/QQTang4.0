import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class CHOOSE extends JPanel {
    protected static JButton caidan, youxi;
    protected static ImageIcon icaidan, iyouxi, beijing;

    protected static ImageIcon ibiwu, ishuimian, ikuangdong, imake;
    protected static JButton biwu, shuimian, kuangdong, make;

    protected static ImageIcon ibiwu1, ijingji, imaoxian;
    protected static JButton biwu1, jingji, maoxian;
    private ImageIcon guang;

    public CHOOSE() {
        guang = new ImageIcon("guang.png");
        ibiwu1 = new ImageIcon("比武.png");
        biwu1 = new JButton(ibiwu1);
        biwu1.setBounds(379, 63, 91, 95);
        add(biwu1);
        ijingji = new ImageIcon("普通.png");
        jingji = new JButton(ijingji);
        jingji.setBounds(220, 61, 109, 106);
        add(jingji);
        imaoxian = new ImageIcon("怪物模式.png");
        maoxian = new JButton(imaoxian);
        maoxian.setBounds(50, 59, 117, 108);
        add(maoxian);

        ibiwu = new ImageIcon("展示1.png");
        ishuimian = new ImageIcon("展示2.png");
        ikuangdong = new ImageIcon("展示3.png");

        biwu = new JButton(ibiwu);
        biwu.setBounds(248, 389, 153, 96);

        shuimian = new JButton(ishuimian);
        shuimian.setBounds(475, 269, 150, 94);

        kuangdong = new JButton(ikuangdong);
        kuangdong.setBounds(249, 267, 150, 96);

        imake = new ImageIcon("diy.png");
        make = new JButton(imake);
        make.setBounds(469, 389, 159, 101);

        add(biwu);
        add(shuimian);
        add(kuangdong);
        add(make);

        beijing = new ImageIcon("111.png");

        icaidan = new ImageIcon("退.png");
        caidan = new JButton(icaidan);
        caidan.setBounds(583, 534, 50, 50);

        iyouxi = new ImageIcon("开.png");
        youxi = new JButton(iyouxi);
        youxi.setBounds(41, 381, 95, 91);

        add(caidan);
        add(youxi);

        setFocusable(true);
        setLayout(null);
        setPreferredSize(new Dimension(650, 600));
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        beijing.paintIcon(this, page, 0, 0);
        if (Play.moshi == 1) guang.paintIcon(this, page, 114, 180);
        if (Play.moshi == 2) guang.paintIcon(this, page, 267, 180);
        if (Play.moshi == 3) guang.paintIcon(this, page, 413, 180);
        if (Mypanel.which == 1) guang.paintIcon(this, page, 407, 437);
        if (Mypanel.which == 2) guang.paintIcon(this, page, 630, 302);
        if (Mypanel.which == 3) guang.paintIcon(this, page, 407, 314);
        if (Mypanel.which == 4) guang.paintIcon(this, page, 541, 494);
        repaint();
    }
}
