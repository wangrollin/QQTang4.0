import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class mapmaker extends JPanel {
    chooseelement ch = new chooseelement();
    makercanvas mc = new makercanvas();

    public mapmaker() {
        setLayout(null);
        this.add(ch);
        this.add(mc);
        mc.setBounds(0, 0, 650, 400);
        ch.setBounds(650, 0, 400, 400);
        setPreferredSize(new Dimension(1050, 400));
    }
}
