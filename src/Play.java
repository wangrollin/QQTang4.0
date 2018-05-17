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



        FighterWinPanel.fanhui.addActionListener(this);
        FighterWinPanel.jieshu.addActionListener(this);
        BaoziWinPanel.fanhui.addActionListener(this);
        BaoziWinPanel.jieshu.addActionListener(this);
        FighterLosePanel.fanhui.addActionListener(this);
        FighterLosePanel.jieshu.addActionListener(this);
        DagfallPanel.fanhui.addActionListener(this);
        DagfallPanel.jieshu.addActionListener(this);

        BattleBiwuPanel.fanhui.addActionListener(this);
        BattleBiwuPanel.jieshu.addActionListener(this);
        BattleJingjiPanel.fanhui.addActionListener(this);
        BattleJingjiPanel.jieshu.addActionListener(this);
        BattleAIPanel.fanhui.addActionListener(this);
        BattleAIPanel.jieshu.addActionListener(this);
    }

    public static void main(String[] args) {
        play = new Play();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Music.stop();
        Music.music[1].play();
        //TODO
        if (e.getSource() == FighterWinPanel.jieshu ||
                e.getSource() == BaoziWinPanel.jieshu || e.getSource() == FighterLosePanel.jieshu
                || e.getSource() == DagfallPanel.jieshu || e.getSource() == BattleBiwuPanel.jieshu
                || e.getSource() == BattleJingjiPanel.jieshu || e.getSource() == BattleAIPanel.jieshu) {
            System.exit(0);
        }
        if (e.getSource() == FighterWinPanel.fanhui ||
                e.getSource() == BaoziWinPanel.fanhui || e.getSource() == FighterLosePanel.fanhui
                || e.getSource() == DagfallPanel.fanhui || e.getSource() == BattleBiwuPanel.fanhui
                || e.getSource() == BattleJingjiPanel.fanhui || e.getSource() == BattleAIPanel.fanhui) {
            play = new Play();
        }
    }
}
