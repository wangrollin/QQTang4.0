package panel.result;

import javax.swing.*;
import java.awt.*;


public class FighterWinPanel extends JPanel {
    private JButton gobackBtn, exitBtn;
    private ImageIcon bgIcon;

    public FighterWinPanel() {
        gobackBtn = new JButton(new ImageIcon("replay.png"));
        gobackBtn.setBounds(5, 407, 185, 72);
        add(gobackBtn);

        exitBtn = new JButton(new ImageIcon("exit.png"));
        exitBtn.setBounds(3, 501, 190, 73);
        add(exitBtn);

        bgIcon = new ImageIcon("战士win.png");
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
