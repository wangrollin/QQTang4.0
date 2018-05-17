import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class chooseelement extends JPanel {
    public static final int chooseelement_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 400;
    static JButton start, back;
    JPanel ch;
    ImageIcon background = new ImageIcon("背景黄.png");
    ImageIcon brick1 = new ImageIcon("地图编辑帮助.png");

    public chooseelement() {
        setLayout(null);
        ch = new JPanel();

        start = new JButton(new ImageIcon("保存.png"));
        back = new JButton(new ImageIcon("退小.png"));

        start.setBounds(300, 355, 40, 40);
        back.setBounds(350, 355, 40, 40);

        add(start);
        add(back);

        setPreferredSize(new Dimension(chooseelement_WIDTH, DEFAULT_HEIGHT));

    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        brick1.paintIcon(this, page, 0, 0);
    }
}
