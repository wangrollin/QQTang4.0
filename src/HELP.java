import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Help extends JPanel {
    protected static JButton menuBtn;
    protected static ImageIcon menuIcon, bgIcon;

    public Help() {
        setLayout(null);

        bgIcon = new ImageIcon("3.png");

        menuIcon = new ImageIcon("退2.png");
        menuBtn = new JButton(menuIcon);
        menuBtn.setBounds(470, 50, 50, 50);

        add(menuBtn);

        setFocusable(true);
        setPreferredSize(new Dimension(600, 600));
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        bgIcon.paintIcon(this, page, 0, 0);
    }
}
