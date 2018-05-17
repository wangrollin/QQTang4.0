import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;

public class P5 extends player implements  KeyListener{
	//现在放下去的糖泡的数量
		protected static int count=0;
		Random aa;
		protected static int siwangcishu =0,fuhuoshijian=0,MAX=500;
		public P5()
		{
			super();
			aa=new Random();
			X=125;
			Y=75;			
			ps=new ImageIcon("包子下.gif");
			pw=new ImageIcon("包子上.gif");
			pa=new ImageIcon("包子左.gif");
			pd=new ImageIcon("包子右.gif");
			
			sps=new ImageIcon("s包子下.gif");
			spw=new ImageIcon("s包子上.gif");
			spa=new ImageIcon("s包子左.gif");
			spd=new ImageIcon("s包子右.gif");
			
			pkunzhu=new ImageIcon("炸弹水.gif");
			pdie=new ImageIcon("P2die.gif");
			pwin=new ImageIcon("P2win.jpg");
			ball=new ImageIcon("糖泡蓝.gif");
			now=ps;
		}
		public void Die()
		{
			siwangcishu+=1;
			yangzi=6;
			setNow();
			now=pdie;
			Music.music[7].play();
		}
		public void setBall ()
		{
			Ball ball=new Ball(5,getHeng(),getShu(),power,getBall());
			Music.music[5].play();
		}
		///////////改变的地方
		//死后复活
		public void chusheng()
		{
			if(yangzi==6)
			{
				fuhuoshijian+=1;
				if(fuhuoshijian==MAX)
				{
					yangzi=0;
					X=aa.nextInt(500)+50;
					Y=aa.nextInt(300)+50;
					while(Map.isWall(getHeng(),getShu()))
					{
						X=aa.nextInt(500)+50;
						Y=aa.nextInt(300)+50;
					}
					fuhuoshijian=0;
					wuditime=1;
				}
			}
		}
		public void beZhu(player p)
		{
			
			if(p==Battlebiwu.p1&&(Battlebiwu.p2.yangzi==0||Battlebiwu.p2.yangzi==1||
					Battlebiwu.p2.yangzi==2||Battlebiwu.p2.yangzi==3||Battlebiwu.p2.yangzi==4)) 
			{
				Battlebiwu.p2.yangzi=4;
				if(Battlebiwu.p2.now==Battlebiwu.p2.s) {Battlebiwu.p2.setNow();Battlebiwu.p2.now=Battlebiwu.p2.s;}
				if(Battlebiwu.p2.now==Battlebiwu.p2.w) {Battlebiwu.p2.setNow();Battlebiwu.p2.now=Battlebiwu.p2.w;}
				if(Battlebiwu.p2.now==Battlebiwu.p2.a) {Battlebiwu.p2.setNow();Battlebiwu.p2.now=Battlebiwu.p2.a;}
				if(Battlebiwu.p2.now==Battlebiwu.p2.d) {Battlebiwu.p2.setNow();Battlebiwu.p2.now=Battlebiwu.p2.d;}
				Battlebiwu.p2.wuditime=1;
				Battlebiwu.p2.bianshentime=0;
			}
			
			if(p==Battlebiwu.p2&&(Battlebiwu.p1.yangzi==0||Battlebiwu.p1.yangzi==1||
					Battlebiwu.p1.yangzi==2||Battlebiwu.p1.yangzi==3||Battlebiwu.p1.yangzi==4)) 
			{
				Battlebiwu.p1.yangzi=4;
				if(Battlebiwu.p1.now==Battlebiwu.p1.s) {Battlebiwu.p1.setNow();Battlebiwu.p1.now=Battlebiwu.p1.s;}
				if(Battlebiwu.p1.now==Battlebiwu.p1.w) {Battlebiwu.p1.setNow();Battlebiwu.p1.now=Battlebiwu.p1.w;}
				if(Battlebiwu.p1.now==Battlebiwu.p1.a) {Battlebiwu.p1.setNow();Battlebiwu.p1.now=Battlebiwu.p1.a;}
				if(Battlebiwu.p1.now==Battlebiwu.p1.d) {Battlebiwu.p1.setNow();Battlebiwu.p1.now=Battlebiwu.p1.d;}
				Battlebiwu.p1.wuditime=1;
				Battlebiwu.p1.bianshentime=0;
			}
		}
		public void toDie(player p)
		{
			if(wuditime>0) wuditime+=1;
			if(wuditime==wudiTime) wuditime=0;
			if(yangzi==5)
			{
				dietime+=1;
				if(dietime==Dietime&&p==Battlebiwu.p1) 
					{Die();}
				if(dietime==Dietime&&p==Battlebiwu.p2) 
				    {Die();}
				if(Battlebiwu.p1.yangzi!=5&&p==Battlebiwu.p2&&Battlebiwu.p1.getHeng()==Battlebiwu.p2.getHeng()&&Battlebiwu.p1.getShu()==Battlebiwu.p2.getShu()) 
					{Battlebiwu.p2.Die();
					Battlebiwu.p2.RIGHT=false;
					Battlebiwu.p2.LEFT=false;
					Battlebiwu.p2.UP=false;
					Battlebiwu.p2.DOWN=false;}
				if(Battlebiwu.p2.yangzi!=5&&p==Battlebiwu.p1&&Battlebiwu.p1.getHeng()==Battlebiwu.p2.getHeng()&&Battlebiwu.p1.getShu()==Battlebiwu.p2.getShu()) 
					{Battlebiwu.p1.Die();
					Battlebiwu.p1.RIGHT=false;
					Battlebiwu.p1.LEFT=false;
					Battlebiwu.p1.UP=false;
					Battlebiwu.p1.DOWN=false;} 
			}
		}		
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyPressed(KeyEvent e) {
			if(yangzi<=4)
		    {
			   switch(e.getKeyCode())
			   {
			    case KeyEvent.VK_R:		    	
			    	dUP=0;
			    	rUP=false;		    	
			    	if(rRIGHT==false&&UP==false) dRIGHT++;
				    if(rLEFT==false&&UP==false) dLEFT++;
				    if(rDOWN==false&&UP==false) dDOWN++;
				    UP=true;		    				     
			    	break;
			    case KeyEvent.VK_F:		
			    	dDOWN=0;
			    	rDOWN=false;
			    	if(rRIGHT==false&&DOWN==false) dRIGHT++;
			    	if(rLEFT==false&&DOWN==false) dLEFT++;
			        if(rUP==false&&DOWN==false) dUP++;	
			        DOWN=true;		       
			    	break;
			    case KeyEvent.VK_D:		    	
			    	dLEFT=0;
			    	rLEFT=false;
			    	if(rRIGHT==false&&LEFT==false) dRIGHT++;
			    	if(rUP==false&&LEFT==false) dUP++;
			        if(rDOWN==false&&LEFT==false) dDOWN++;	
			        LEFT=true;
			    	break;
			    case KeyEvent.VK_G:		    	
			    	dRIGHT=0;
			    	rRIGHT=false;
			    	if(rUP==false&&RIGHT==false) dUP++;
			    	if(rLEFT==false&&RIGHT==false) dLEFT++;
			        if(rDOWN==false&&RIGHT==false) dDOWN++;
			        RIGHT=true;
			    	break;		    			    	
			   }
		    }					
			if(e.getKeyCode()==KeyEvent.VK_Q) 
			{
				if(yangzi==0||yangzi==1)  amount=namount;
				if(yangzi==0||yangzi==1||yangzi==2) power=npower;
				if(canspace()&&USEtangpao==false&&count<amount&&Map.boommap[getHeng()][getShu()]==null) setBall();
				USEtangpao=true;
			}
		
			if(e.getKeyCode()==KeyEvent.VK_W&&USEfork==false) 
			{
				if(fork>0&&yangzi==5) {fork-=1;fuhuo();}
				USEfork=true;
			}			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			if(yangzi<=4)
			{
				switch(e.getKeyCode())
				{
				  case KeyEvent.VK_R:				  				  
				      rUP=true;
				      if(dRIGHT>dUP&&UP==true) dRIGHT--;
				      if(dLEFT>dUP&&UP==true) dLEFT--;
				      if(dDOWN>dUP&&UP==true) dDOWN--;
				      dUP=0;
				      UP=false;
				      break;	
				  case KeyEvent.VK_F:				 
				      rDOWN=true;
				      if(dRIGHT>dDOWN&&DOWN==true) dRIGHT--;
				      if(dLEFT>dDOWN&&DOWN==true) dLEFT--;
				      if(dUP>dDOWN&&DOWN==true) dUP--;
				      DOWN=false;
					  dDOWN=0;
				      break;	
				  case KeyEvent.VK_D:				 
				      rLEFT=true;
				      if(dRIGHT>dLEFT&&LEFT==true) dRIGHT--;
				      if(dUP>dLEFT&&LEFT==true) dUP--;
				      if(dDOWN>dLEFT&&LEFT==true) dDOWN--;
				      LEFT=false;
					  dLEFT=0;
				      break;
				  case KeyEvent.VK_G:				  
				      rRIGHT=true;
				      if(dUP>dRIGHT&&RIGHT==true) dUP--;
				      if(dLEFT>dRIGHT&&RIGHT==true) dLEFT--;
				      if(dDOWN>dRIGHT&&RIGHT==true) dDOWN--;
				      RIGHT=false;
					  dRIGHT=0;
				      break;	
				}
			}		
			if(e.getKeyCode()==KeyEvent.VK_Q)  USEtangpao=false;
			if(e.getKeyCode()==KeyEvent.VK_W) USEfork=false;			
		}	
}
