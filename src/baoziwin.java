import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class baoziwin extends JPanel {
    protected static JButton fanhui, jieshu;
    protected ImageIcon back;

    public baoziwin() {
        fanhui = new JButton(new ImageIcon("replay.png"));
        fanhui.setBounds(5, 407, 185, 72);
        add(fanhui);

        jieshu = new JButton(new ImageIcon("exit.png"));
        jieshu.setBounds(3, 501, 190, 73);
        add(jieshu);

        back = new ImageIcon("包子win.png");
        setFocusable(true);
        setLayout(null);
        setPreferredSize(new Dimension(650, 600));
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        back.paintIcon(this, page, 0, 0);
    }
}
