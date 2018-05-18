package map_maker;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class WallDisplayPanel extends JPanel {
    public static final int chooseelement_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 400;
    private JButton saveBtn, abandonBtn;

    private ImageIcon brick1 = new ImageIcon("地图编辑帮助.png");

    WallDisplayPanel() {
        setLayout(null);

        saveBtn = new JButton(new ImageIcon("保存.png"));
        saveBtn.setBounds(300, 355, 40, 40);
        add(saveBtn);

        abandonBtn = new JButton(new ImageIcon("退小.png"));
        abandonBtn.setBounds(350, 355, 40, 40);
        add(abandonBtn);

        setPreferredSize(new Dimension(chooseelement_WIDTH, DEFAULT_HEIGHT));
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        brick1.paintIcon(this, page, 0, 0);
    }

    public JButton getSaveBtn() {
        return saveBtn;
    }

    public JButton getAbandonBtn() {
        return abandonBtn;
    }
}
