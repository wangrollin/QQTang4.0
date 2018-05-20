package map_maker;

import element.Wall;
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
        addKeyListener(wallSetPanel);//
        wallSetPanel.setBounds(0, 0, 650, 400);
        wallDisplayPanel.setBounds(650, 0, 400, 400);
        setPreferredSize(new Dimension(1050, 400));
        setFocusable(true);
    }

    public JButton getSaveBtn() {
        return wallDisplayPanel.getSaveBtn();
    }

    public JButton getAbandonBtn() {
        return wallDisplayPanel.getAbandonBtn();
    }

    public Wall[][] getWallMap() {
        return wallSetPanel.getWallMap();
    }
}
