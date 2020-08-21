package panel;

import javax.swing.*;

public class Play extends JFrame {
    //static panel.MyPanelCard panel;
    private MyPanelCard panel;
    //static JFrame frame;
    //static int moshi = 2;
    static int zanting = 0;

    //static panel.Play play;
    // TODO: 18/5/22 到底是什么吃资源,消灭掉 风扇猛转
    // TODO: 18/5/22 到底是什么导致的卡顿
    // TODO: 18/5/22 改善文件命名,代码变量名
    // TODO: 18/5/22 加好的注释
    // TODO: 18/5/22 将图片放在resource里,将resource放入jar包中
    // TODO: 18/5/22 将所有输出都删掉 或者加个开关 debugMode = True
    // TODO: 18/5/22 添加联网模式,局域网,不需要服务器的那种
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
