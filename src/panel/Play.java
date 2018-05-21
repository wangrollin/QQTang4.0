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

    public Play() {
        this.panel = new MyPanelCard(this);

        setTitle("666堂");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panel);
        setLocation(350, 50);
        pack();
        setVisible(true);

        MusicTool.music[9].loop();
        setResizable(false);

    }

    public static void main(String[] args) {
        new Play();
    }

}
