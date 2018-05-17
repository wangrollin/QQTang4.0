import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class Maker extends JFrame implements ActionListener{
	static mapmaker make=new mapmaker();
	static JFrame frame;
	public Maker()
	{		 
			 frame = new JFrame("地图编辑器");
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.getContentPane().add(make);

		    frame.setLocation(350, 50);
		    frame.pack();	        
		    frame.setVisible(true);
		    frame.setLocation(157, 164);
		    setResizable(false);
		    chooseelement.start.addActionListener(this);
		    chooseelement.back.addActionListener(this);		   
	}	
	public static void main(String[] args){
		Maker make=new Maker(); 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Music.music[1].play();
		if (e.getSource()==chooseelement.start)
		{
			frame.setVisible(false);
			Play.frame.setVisible(true);
			for(int j=0;j<BattleCanvas.shu;j++)
				   for(int i=0;i<BattleCanvas.heng;i++)	
				   {
					   Map.wallmap[i][j]=null;
				   }
			Mypanel.which=4;
			Map.dimian=Map.dimian1;
			 Wallmap.maker(makercanvas.mapshuzu);
			Mypanel.lay.show(Play.panel,"choose");           
		}
		if (e.getSource()==chooseelement.back)
		{

			frame.setVisible(false);
			Play.frame.setVisible(true);
		}				
	}
}
