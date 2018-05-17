import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JApplet;


public class Music {
	public static boolean jiemian=false,biwu=false,shuimian=false,kuangdong=false,yingle=false;
	public static  boolean sound=true,zsound=true;
	public static AudioClip[] music;
	static URL 按钮自救 = null,炸弹爆炸= null,比武背景= null,吃道具= null ,放置 = null,readygo= null,
			包住爆炸= null,赢了= null,游戏界面开始= null,
			水面音乐=null,矿洞音乐=null,被吃=null,失败=null,diy=null;
	public Music()
	{
	}
	public static void Musicload() 
	{
		try {
				按钮自救=new URL ("file","localhost","按钮自救.wav");//1
				炸弹爆炸=new URL ("file","localhost","爆炸.wav");//2
				比武背景=new URL ("file","localhost","比武背景.wav");//3
				吃道具=new URL ("file","localhost","吃道具.wav");//4
				放置=new URL ("file","localhost","放泡2.wav");//5
				readygo=new URL ("file","localhost","开始.wav");//6
				包住爆炸=new URL ("file","localhost","死亡.wav");//7
				赢了=new URL ("file","localhost","胜利.wav");//8
				游戏界面开始=new URL ("file","localhost","DIY.wav");//9
				//游戏界面开始=getClass().getResource("DIY.wav");//9
				//getClass().getResource(
				水面音乐=new URL ("file","localhost","水面音乐.wav");//10
				矿洞音乐=new URL ("file","localhost","比武背景.wav");//11
				被吃=new URL ("file","localhost","自救.wav");//12
				失败=new URL ("file","localhost","loss.wav");
				diy=new URL ("file","localhost","diy1.wav");		
		} catch (MalformedURLException e) {			
			e.printStackTrace();
		}
		music=new AudioClip[15];
		music[0]=null;
		music[1]=JApplet.newAudioClip(按钮自救);
		music[2]=JApplet.newAudioClip(炸弹爆炸);
		music[3]=JApplet.newAudioClip(比武背景);//、、、、、、、、、、、、
		music[4]=JApplet.newAudioClip(吃道具);
		music[5]=JApplet.newAudioClip(放置);
		music[6]=JApplet.newAudioClip(readygo);
		music[7]=JApplet.newAudioClip(包住爆炸);
		music[8]=JApplet.newAudioClip(赢了);//、、、、、、、、、、、
		music[9]=JApplet.newAudioClip(游戏界面开始);//、、、、、、
		music[10]=JApplet.newAudioClip(水面音乐);//、、、、、、、、、、、、、
		music[11]=JApplet.newAudioClip(矿洞音乐);//、、、、、、、、、、
		music[12]=JApplet.newAudioClip(被吃);
		music[13]=JApplet.newAudioClip(失败);
		music[14]=JApplet.newAudioClip(diy);

	}
	//此方法只能被执行一次
	public static void stop()
	{
		music[1].stop();
		music[2].stop();
		music[3].stop();
		music[4].stop();
		music[5].stop();
		music[6].stop();
		music[7].stop();
		music[8].stop();
		music[9].stop();
		music[10].stop();
		music[11].stop();
		music[12].stop();
		music[13].stop();
		music[14].stop();		
	}	
}
