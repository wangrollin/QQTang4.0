import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class DagfallPanel extends JPanel {
    protected static JButton fanhui, jieshu;
    protected ImageIcon back;

    public DagfallPanel() {
        fanhui = new JButton(new ImageIcon("replay for ƽ.png"));
        fanhui.setBounds(73, 484, 146, 59);
        add(fanhui);

        jieshu = new JButton(new ImageIcon("exit for ƽ.png"));
        jieshu.setBounds(254, 481, 153, 62);
        add(jieshu);

        back = new ImageIcon("ƽ.png");
        setFocusable(true);
        setLayout(null);
        setPreferredSize(new Dimension(650, 600));
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        back.paintIcon(this, page, 0, 0);
    }
}
