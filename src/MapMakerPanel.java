import java.awt.Dimension;


import javax.swing.JPanel;

public class MapMakerPanel extends JPanel {
    WallSelectPanel ch = new WallSelectPanel();
    makercanvas mc = new makercanvas();

    public MapMakerPanel() {
        setLayout(null);
        this.add(ch);
        this.add(mc);
        mc.setBounds(0, 0, 650, 400);
        ch.setBounds(650, 0, 400, 400);
        setPreferredSize(new Dimension(1050, 400));
    }
}
