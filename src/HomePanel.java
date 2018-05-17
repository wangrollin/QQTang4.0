import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HomePanel extends JPanel {
    private JButton startGameBtn, helpBtn, exitBtn;
    private ImageIcon bgIcon;

    HomePanel() {
        setLayout(null);

        bgIcon = new ImageIcon("1.png");

        startGameBtn = new JButton(new ImageIcon("开始.png"));
        startGameBtn.setBounds(281, 52, 310, 66);
        add(startGameBtn);

        helpBtn = new JButton(new ImageIcon("帮助.png"));
        helpBtn.setBounds(280, 156, 301, 70);
        add(helpBtn);

        exitBtn = new JButton(new ImageIcon("退出.png"));
        exitBtn.setBounds(575, 253, 75, 83);
        add(exitBtn);

        setFocusable(true);
        setPreferredSize(new Dimension(600, 600));
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        bgIcon.paintIcon(this, page, 0, 0);
    }

    public JButton getStartGameBtn() {
        return startGameBtn;
    }

    public JButton getHelpBtn() {
        return helpBtn;
    }

    public JButton getExitBtn() {
        return exitBtn;
    }
}
