import panel.result.BaoziWinPanel;
import panel.result.DagfallPanel;
import panel.result.FighterLosePanel;
import panel.result.FighterWinPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Play extends JFrame implements ActionListener {
    static Mypanel panel;
    static JFrame frame;
    static int moshi = 2, zanting = 0;
    static Play play;

    public Play() {
        panel = new Mypanel();
        frame = new JFrame("666堂");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);

        frame.setLocation(350, 50);
        frame.pack();
        frame.setVisible(true);

        Music.music[9].loop();
        setResizable(false);

    }

    public static void main(String[] args) {
        play = new Play();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*Music.stop();
        Music.music[1].play();

        if (e.getSource() == FighterWinPanel.exitBtn ||
                e.getSource() == BaoziWinPanel.exitBtn || e.getSource() == FighterLosePanel.exitBtn
                || e.getSource() == DagfallPanel.exitBtn || e.getSource() == BattleBiwuPanel.exitBtn
                || e.getSource() == BattleJingjiPanel.exitBtn || e.getSource() == BattleAIPanel.exitBtn) {
            System.exit(0);
        }
        if (e.getSource() == FighterWinPanel.gobackBtn ||
                e.getSource() == BaoziWinPanel.gobackBtn || e.getSource() == FighterLosePanel.gobackBtn
                || e.getSource() == DagfallPanel.gobackBtn || e.getSource() == BattleBiwuPanel.gobackBtn
                || e.getSource() == BattleJingjiPanel.gobackBtn || e.getSource() == BattleAIPanel.gobackBtn) {
            play = new Play();
        }*/
    }
}
