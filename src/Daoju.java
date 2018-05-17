import java.util.Random;

import javax.swing.ImageIcon;

public class Daoju {
	//道具的图片  糖包 威力 飞鞋  tangbao,weili,feixie,fork,feng,pao,zhu,gui,
   private ImageIcon now;
   // (概率)0表示无 12表示数量 34表示威力56表示飞鞋，7表示叉子,8表示 风 9表示泡 10表示鬼 11表示猪
   //(类型)0无  1数量 2威力 3鞋子 4叉子 5风 6泡 7鬼 8猪
	private int type;
	private int heng,shu;
	public Daoju(int h,int s,int te)
	{
		  heng=h;
		   shu=s;
		type=te;
		 now=new ImageIcon("zhutu.jpg");
		 Map.daojumap[heng][shu]=this;
	}
   public Daoju(int h,int s)
   {
	   Random a =new Random();
	   type=a.nextInt(11)+1;
	   heng=h;
	   shu=s;
	   switch( type)
	   {
		   case 1:
			   now=new ImageIcon("糖包.gif");
			   break;
		   case 2:
			   now=new ImageIcon("糖包.gif");
			   break;
		   case 3:
			   now=new ImageIcon("威力.gif");
			   break;
		   case 4:
			   now=new ImageIcon("威力.gif");
			   break;
		   case 5:
			   now=new ImageIcon("飞鞋.gif");
			   break;
		   case 6:
			   now=new ImageIcon("飞鞋.gif");
			   break;
		   case 7:
			   now=new ImageIcon("叉子.gif");
			   break;
		   case 8:
			   now=new ImageIcon("fengtu.gif");
		       break;
		   case 9:
			   now=new ImageIcon("paotu.gif");
			   break;
		   case 10:
			   now=new ImageIcon("guitu.gif");
			   break;
		   case 11:
			   now=new ImageIcon("zhutu.jpg");
			   break;
	   }
	   Map.daojumap[heng][shu]=this;
   }
   public void beExo()
   {
	   if(Map.expmap[heng][shu]!=null) remove();
   }
   public int getType()
   {
	   return type;
   }
   public ImageIcon getNow()
   {
	   return now;
   }
   public void remove()
   {
	   Map.daojumap[heng][shu]=null;
   }
}
