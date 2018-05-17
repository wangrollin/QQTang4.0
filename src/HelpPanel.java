import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class HelpPanel extends JPanel {
    private JButton gobackBtn;
    private ImageIcon bgIcon;

    HelpPanel() {
        setLayout(null);

        bgIcon = new ImageIcon("3.png");

        gobackBtn = new JButton(new ImageIcon("退2.png"));
        gobackBtn.setBounds(470, 50, 50, 50);

        add(gobackBtn);

        setFocusable(true);
        setPreferredSize(new Dimension(600, 600));
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        bgIcon.paintIcon(this, page, 0, 0);
    }

    public JButton getGobackBtn() {
        return gobackBtn;
    }
}
