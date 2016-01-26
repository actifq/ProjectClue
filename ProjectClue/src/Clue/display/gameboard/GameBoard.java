package Clue.display.gameboard;

import java.awt.*;
import javax.swing.*;

public class GameBoard extends JPanel{
	Image backImg;
	JPanel gridBoard;
	
	public GameBoard(){
		backImg=Toolkit.getDefaultToolkit().getImage("/������Ʈ��/res/boardBack.png");
		
		gridBoard=new JPanel();
		gridBoard.setBackground(Color.blue);
		
		setLayout(null);
		/*gridBoard.setBounds(280,130,390,390);
		roomImg[0].setBounds(10,15,300,115);
		roomImg[1].setBounds(315,15,300,115);
		roomImg[2].setBounds(620,15,280,115);
		roomImg[3].setBounds(670,130,280,240);
		roomImg[4].setBounds(670,375,280,275);
		roomImg[5].setBounds(315,520,350,130);
		roomImg[6].setBounds(10,520,300,130);
		roomImg[7].setBounds(10,340,270,175);
		roomImg[8].setBounds(10,135,270,200);*/
		
		
		add(gridBoard);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(backImg, 0, 0, getWidth(), getHeight(),this);
		}
	
}
