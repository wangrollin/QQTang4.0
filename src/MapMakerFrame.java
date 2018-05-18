import constants.GameConstants;
import map_maker.MapMakerPanel;
import map_maker.WallDisplayPanel;
import map_maker.WallSetPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class MapMakerFrame extends JFrame implements ActionListener {
    private MapMakerPanel mapMakerPanel = new MapMakerPanel();
    private Play play;

    public MapMakerFrame(Play play) {
        this.play = play;
        setTitle("地图编辑器");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(mapMakerPanel);
        setLocation(350, 50);
        pack();
        setVisible(false);
        setLocation(157, 164);
        setResizable(false);

        mapMakerPanel.getSaveBtn().addActionListener(this);
        mapMakerPanel.getAbandonBtn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Music.music[1].play();
        if (e.getSource() == mapMakerPanel.getSaveBtn()) {
            setVisible(false);
            play.setVisible(true);
            for (int j = 0; j < GameConstants.SHU; j++)
                for (int i = 0; i < GameConstants.HENG; i++) {
                    Map.wallmap[i][j] = null;
                }
            MyPanelCard.which = 4;
            Map.dimian = Map.dimian1;
            WallMap.maker(WallSetPanel.mapshuzu);
        } else if (e.getSource() == mapMakerPanel.getAbandonBtn()) {
            setVisible(false);
            play.setVisible(true);
        }
    }


}
