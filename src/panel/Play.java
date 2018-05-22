package panel;

import element.MusicTool;

import javax.swing.JFrame;

public class Play extends JFrame {
    //static panel.MyPanelCard panel;
    private MyPanelCard panel;
    //static JFrame frame;
    //static int moshi = 2;
    static int zanting = 0;
    //static panel.Play play;
// TODO: 18/5/22 到底是什么吃资源,消灭掉
    public Play() {
        this.panel = new MyPanelCard(this);

        setTitle("666堂");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panel);
        setLocation(350, 50);
        pack();
        setVisible(true);

        setResizable(false);

    }

    public static void main(String[] args) {
        new Play();
    }

}
