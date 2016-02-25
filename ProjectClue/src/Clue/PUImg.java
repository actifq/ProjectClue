package Clue;

import java.awt.*;
import javax.swing.*;

public class PUImg extends JPanel{
	
	Image turn;
	JLabel label ;
	
	public JButton b1;
	public PUImg(){
		
		//ShowTurn
		label = new JLabel("MY TURN ");
		b1= new JButton("주사위 돌리기");
		turn=Toolkit.getDefaultToolkit().getImage("c:\\image2\\cork.jpg");
		
		setLayout(null);
		label.setBounds(150,50,80,20);
		b1.setBounds(80,150,120,40);
		
		add(label);
		add(b1);
		
	
	}
	@Override
	protected void paintComponent(Graphics g) {  
		
		g.drawImage(turn, 0, 0, getWidth(),getHeight(),this);
	}
	
}
