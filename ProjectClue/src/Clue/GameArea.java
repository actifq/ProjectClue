package Clue;

import java.awt.*;
import javax.swing.*;

public class GameArea extends JPanel{
	Image backImg;

	JPanel gridBoard;
	
	public GameArea(){
		

		backImg=Toolkit.getDefaultToolkit().getImage("image/boardBack.png");

		gridBoard=new GameArea_InnerBoard();
		gridBoard.setBackground(Color.white);
		
		setLayout(null);
		
		gridBoard.setBounds(249,109,352,352);

		add(gridBoard);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(backImg, 0, 0, getWidth(), getHeight(),this);
	}
}


