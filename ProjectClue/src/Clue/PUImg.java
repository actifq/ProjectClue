package Clue;

import java.awt.*;
import javax.swing.*;

public class PUImg extends JPanel{
	
	Image turn;
	//JLabel label ;
	
	public JButton b1;
	public PUImg(){
		
		//ShowTurn
		//label = new JLabel("MY TURN ");
		b1= new JButton("�ֻ��� ������");
		turn=Toolkit.getDefaultToolkit().getImage("image/back/blcat.jpg");
		
		setLayout(null);
		//label.setBounds(150,50,80,20);
		b1.setBounds(40,150,120,40);
		
		//add(label);
		add(b1);
		
	
	}
	@Override
	protected void paintComponent(Graphics g) {  
		
		g.drawImage(turn, 0, 0, getWidth(),getHeight(),this);
	}
	
}
