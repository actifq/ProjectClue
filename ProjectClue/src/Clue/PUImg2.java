package Clue;

import java.awt.*;
import javax.swing.*;

public class PUImg2 extends JPanel{
	
	Image reach;
	JLabel la1 ;
	
	public JButton b2,b3;
	public PUImg2(){
		
		//ReachRoom
		JPanel p = new JPanel();
		la1 = new JLabel("���̸�");
		b2=new JButton("�߰��߸�");
		b3=new JButton("�����߸�");
		reach=Toolkit.getDefaultToolkit().getImage("image/back/corr.jpg");
		/*b2.setOpaque(true);
		b3.setOpaque(true);
		b2.setBorderPainted(false);
		b3.setBorderPainted(false);*/
		
		setLayout(null);
		la1.setBounds(50,15,200,30);
		
		add(p);
		add(la1);
		p.add(b2);
		p.add(b3);
		p.setBounds(10,155,195,35);
	
	}
	@Override
	protected void paintComponent(Graphics g) {  
		
		g.drawImage(reach, 0, 0, getWidth(),getHeight(),this);
	}
	
}
