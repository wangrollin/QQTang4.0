import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NEWONE extends JPanel {	   
	protected static JButton newgame,shezhi,bangzhu,tuichu;
	protected static  ImageIcon inewgame,ishezhi,ibangzhu,ituichu,beijing;
	public NEWONE()
	{
		setLayout(null);
		
		beijing=new ImageIcon("1.png");
		inewgame=new ImageIcon("开始.png");
		newgame=new JButton(inewgame);
		newgame.setBounds(281, 52, 310, 66);

		ibangzhu=new ImageIcon("帮助.png");
		bangzhu=new JButton(ibangzhu);
		bangzhu.setBounds(280, 156, 301, 70);
		
		ituichu=new ImageIcon("退出.png");
		tuichu=new JButton(ituichu);
		tuichu.setBounds(575, 253, 75, 83);
		
		add(newgame);
		add(bangzhu);
		add(tuichu);
		setFocusable(true);
		setPreferredSize(new Dimension(600,600));		
	}	
	public void paintComponent (  Graphics page) 
	{
		super.paintComponent(page);	
		beijing.paintIcon(this,page,0,0);
	}
}
