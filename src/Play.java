import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Play extends JFrame implements ActionListener {
    static Mypanel panel;
    static JFrame frame;
    static int moshi = 2, zanting = 0;
    static Play play, zplay;

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
        HomePanel.tuichu.addActionListener(this);

        zhanshiwin.fanhui.addActionListener(this);
        zhanshiwin.jieshu.addActionListener(this);
        baoziwin.fanhui.addActionListener(this);
        baoziwin.jieshu.addActionListener(this);
        zhanshishu.fanhui.addActionListener(this);
        zhanshishu.jieshu.addActionListener(this);
        pingju.fanhui.addActionListener(this);
        pingju.jieshu.addActionListener(this);

        BattleBiwu.fanhui.addActionListener(this);
        BattleBiwu.jieshu.addActionListener(this);
        BattleJingji.fanhui.addActionListener(this);
        BattleJingji.jieshu.addActionListener(this);
        BattleAI.fanhui.addActionListener(this);
        BattleAI.jieshu.addActionListener(this);
    }

    public static void main(String[] args) {
        play = new Play();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Music.stop();
        Music.music[1].play();
        if (e.getSource() == HomePanel.tuichu || e.getSource() == zhanshiwin.jieshu ||
                e.getSource() == baoziwin.jieshu || e.getSource() == zhanshishu.jieshu
                || e.getSource() == pingju.jieshu || e.getSource() == BattleBiwu.jieshu
                || e.getSource() == BattleJingji.jieshu || e.getSource() == BattleAI.jieshu) {
            System.exit(0);
        }
        if (e.getSource() == zhanshiwin.fanhui ||
                e.getSource() == baoziwin.fanhui || e.getSource() == zhanshishu.fanhui
                || e.getSource() == pingju.fanhui || e.getSource() == BattleBiwu.fanhui
                || e.getSource() == BattleJingji.fanhui || e.getSource() == BattleAI.fanhui) {
            play = new Play();
        }
    }
}
