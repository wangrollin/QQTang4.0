import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class HELP extends JPanel {
    protected static JButton caidan;
    protected static ImageIcon icaidan, beijing;

    public HELP() {
        setLayout(null);

        beijing = new ImageIcon("3.png");

        icaidan = new ImageIcon("退2.png");
        caidan = new JButton(icaidan);
        caidan.setBounds(470, 50, 50, 50);

        add(caidan);

        setFocusable(true);
        setPreferredSize(new Dimension(600, 600));
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        beijing.paintIcon(this, page, 0, 0);
    }
}
