package panel.result;

import javax.swing.*;
import java.awt.*;


public class DagfallPanel extends JPanel {
    private JButton gobackBtn, exitBtn;
    private ImageIcon bgIcon;

    public DagfallPanel() {
        gobackBtn = new JButton(new ImageIcon("replay for ƽ.png"));
        gobackBtn.setBounds(73, 484, 146, 59);
        add(gobackBtn);

        exitBtn = new JButton(new ImageIcon("exit for ƽ.png"));
        exitBtn.setBounds(254, 481, 153, 62);
        add(exitBtn);

        bgIcon = new ImageIcon("ƽ.png");
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
