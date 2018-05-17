import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class MapMakerFrame extends JFrame implements ActionListener {
    static MapMakerPanel mapMakerPanel = new MapMakerPanel();
    static JFrame frame;

    public MapMakerFrame() {
        frame = new JFrame("地图编辑器");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mapMakerPanel);

        frame.setLocation(350, 50);
        frame.pack();
        frame.setVisible(true);
        frame.setLocation(157, 164);
        setResizable(false);
        WallSelectPanel.start.addActionListener(this);
        WallSelectPanel.back.addActionListener(this);
    }

    public static void main(String[] args) {
        new MapMakerFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Music.music[1].play();
        if (e.getSource() == WallSelectPanel.start) {
            frame.setVisible(false);
            Play.frame.setVisible(true);
            for (int j = 0; j < BattleJingjiPanel.shu; j++)
                for (int i = 0; i < BattleJingjiPanel.heng; i++) {
                    Map.wallmap[i][j] = null;
                }
            Mypanel.which = 4;
            Map.dimian = Map.dimian1;
            WallMap.maker(makercanvas.mapshuzu);
            Mypanel.lay.show(Play.panel, "modeSelectPanel");
        }
        if (e.getSource() == WallSelectPanel.back) {

            frame.setVisible(false);
            Play.frame.setVisible(true);
        }
    }
}
