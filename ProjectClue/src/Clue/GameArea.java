package Clue;

import java.awt.*;
import javax.swing.*;

public class GameArea extends JPanel{
	Image backImg;
	
	
	public GameArea(){
		
		backImg=Toolkit.getDefaultToolkit().getImage("image/boardBack.png");
		
		
		setLayout(null);
		
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(backImg, 0, 0, getWidth(), getHeight(),this);
		
		}
}
