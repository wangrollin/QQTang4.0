package panel.result;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class FighterLosePanel extends JPanel {
    private JButton gobackBtn, exitBtn;
    private ImageIcon bgIcon;

    public FighterLosePanel() {
        gobackBtn = new JButton(new ImageIcon("replay.png"));
        gobackBtn.setBounds(5, 407, 185, 72);
        add(gobackBtn);

        exitBtn = new JButton(new ImageIcon("exit.png"));
        exitBtn.setBounds(3, 501, 190, 73);
        add(exitBtn);

        bgIcon = new ImageIcon("gameover.png");
        setFocusable(true);
        setLayout(null);
        setPreferredSize(new Dimension(650, 600));
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        bgIcon.paintIcon(this, page, 0, 0);
    }

    public JButton getGobackBtn() {
        return gobackBtn;
    }

    public JButton getExitBtn() {
        return exitBtn;
    }
}
