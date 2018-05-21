package element;

import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JApplet;


public class MusicTool {
    /**
     * before game, loop and play
     */

    public static AudioClip HOME_BGM;
    public static AudioClip PRESS_BUTTON;

    /**
     * in the game, loop
     */
    public static AudioClip SHUIMIAN_MAP_BGM;
    public static AudioClip BIWU_MAP_BGM;
    public static AudioClip KUANGDONG_MAP_BGM;
    public static AudioClip DIY_MAP_BGM;

    public static AudioClip BIWU_MODE_BGM;
    public static AudioClip AI_MODE_BGM;


    /**
     * in the game, play
     */
    public static AudioClip READ_GO;

    public static AudioClip SET_BALL;
    public static AudioClip BALL_EXPLODE;

    public static AudioClip PICKUP_ITEM;
    public static AudioClip SCATTER_ITEM;

    public static AudioClip ESCAPE;
    public static AudioClip PLAYER_EXPLODE;

    /**
     * game result, loop
     */
    public static AudioClip WINNING_BGM;
    public static AudioClip LOSING_BGM;

    public static void Musicload() {
        try {
            /**
             * before game, loop and play
             */
            HOME_BGM = JApplet.newAudioClip(new URL("file", "localhost", "DIY.wav"));
            PRESS_BUTTON = JApplet.newAudioClip(new URL("file", "localhost", "按钮自救.wav"));
            /**
             * mode game, loop
             */
            SHUIMIAN_MAP_BGM = JApplet.newAudioClip(new URL("file", "localhost", "水面音乐.wav"));
            BIWU_MAP_BGM = JApplet.newAudioClip(new URL("file", "localhost", "比武背景.wav"));
            KUANGDONG_MAP_BGM = JApplet.newAudioClip(new URL("file", "localhost", "比武背景.wav"));
            DIY_MAP_BGM = JApplet.newAudioClip(new URL("file", "localhost", "diy1.wav"));
            /**
             * map game, loop
             */
            BIWU_MODE_BGM = JApplet.newAudioClip(new URL("file", "localhost", "比武背景.wav"));
            AI_MODE_BGM = JApplet.newAudioClip(new URL("file", "localhost", "diy1.wav"));
            /**
             * game operation, play
             */
            READ_GO = JApplet.newAudioClip(new URL("file", "localhost", "开始.wav"));
            SET_BALL = JApplet.newAudioClip(new URL("file", "localhost", "放泡2.wav"));
            BALL_EXPLODE = JApplet.newAudioClip(new URL("file", "localhost", "爆炸.wav"));
            PICKUP_ITEM = JApplet.newAudioClip(new URL("file", "localhost", "吃道具.wav"));
            SCATTER_ITEM = JApplet.newAudioClip(new URL("file", "localhost", "自救.wav"));
            ESCAPE = JApplet.newAudioClip(new URL("file", "localhost", "按钮自救.wav"));
            PLAYER_EXPLODE = JApplet.newAudioClip(new URL("file", "localhost", "死亡.wav"));
            /**
             * game result, loop
             */
            WINNING_BGM = JApplet.newAudioClip(new URL("file", "localhost", "胜利.wav"));
            LOSING_BGM = JApplet.newAudioClip(new URL("file", "localhost", "loss.wav"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void stopAllMusic() {
        HOME_BGM.stop();
        PRESS_BUTTON.stop();
        SHUIMIAN_MAP_BGM.stop();
        BIWU_MAP_BGM.stop();
        KUANGDONG_MAP_BGM.stop();
        DIY_MAP_BGM.stop();
        BIWU_MODE_BGM.stop();
        AI_MODE_BGM.stop();
        READ_GO.stop();
        SET_BALL.stop();
        BALL_EXPLODE.stop();
        PICKUP_ITEM.stop();
        SCATTER_ITEM.stop();
        ESCAPE.stop();
        PLAYER_EXPLODE.stop();
        WINNING_BGM.stop();
        LOSING_BGM.stop();
    }
}
