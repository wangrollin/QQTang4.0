package map_maker;

import java.awt.Dimension;


import javax.swing.*;

public class MapMakerPanel extends JPanel {
    WallDisplayPanel wallDisplayPanel;
    WallSetPanel wallSetPanel;

    public MapMakerPanel() {
        wallSetPanel = new WallSetPanel();
        wallDisplayPanel = new WallDisplayPanel();

        setLayout(null);
        add(wallDisplayPanel);
        add(wallSetPanel);

        wallSetPanel.setBounds(0, 0, 650, 400);
        wallDisplayPanel.setBounds(650, 0, 400, 400);
        setPreferredSize(new Dimension(1050, 400));
    }

    public JButton getSaveBtn() {
        return wallDisplayPanel.getSaveBtn();
    }

    public JButton getAbandonBtn() {
        return wallDisplayPanel.getAbandonBtn();
    }
}
