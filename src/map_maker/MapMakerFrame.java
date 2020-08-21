package map_maker;

import constants.GameConstants;
import element.MusicTool;
import panel.MyPanelCard;
import panel.Play;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MapMakerFrame extends JFrame implements ActionListener {
    private MapMakerPanel mapMakerPanel = new MapMakerPanel();
    private Play play;
    private MyPanelCard myPanelCard;

    public MapMakerFrame(Play play, MyPanelCard myPanelCard) {
        this.play = play;
        this.myPanelCard = myPanelCard;
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
        MusicTool.PRESS_BUTTON.play();
        if (e.getSource() == mapMakerPanel.getSaveBtn()) {
            setVisible(false);
            play.setVisible(true);

            myPanelCard.setWallMap(mapMakerPanel.getWallMap());
            myPanelCard.setWallMapType(GameConstants.DIY_MAP);
            myPanelCard.setGroundType(GameConstants.BIWU_GROUND);
        } else if (e.getSource() == mapMakerPanel.getAbandonBtn()) {
            setVisible(false);
            play.setVisible(true);
        }
    }


}
