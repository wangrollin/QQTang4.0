import javax.swing.JFrame;

public class Play extends JFrame {
    //static MyPanelCard panel;
    private MyPanelCard panel;
    //static JFrame frame;
    static int moshi = 2, zanting = 0;
    //static Play play;

    public Play() {
        panel = new MyPanelCard(this);
        //new JFrame("666堂");
        //setName();
        setTitle("666堂");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panel);
        setLocation(350, 50);
        pack();
        setVisible(true);

        Music.music[9].loop();
        setResizable(false);

    }

    public static void main(String[] args) {
        new Play();
    }

}
