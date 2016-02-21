package Clue;

import java.awt.*;
import javax.swing.*;

public class GameArea extends JPanel{

	Image backImg;
	Image[] horse= new Image[4];
	final int DFT_X=248;
	final int DFT_Y=108;
	final int BLOCK_NUM=13;
	final int BLOCK_WIDTH=27;
	
	int[][] pXY={{6,6},
				{6,6},
				{6,6},
				{6,6}};
	
	

	public GameArea(){


		backImg=Toolkit.getDefaultToolkit().getImage("image/back/boardBack.png");
		for(int i=0;i<horse.length;i++){
			
		
		horse[i]=Toolkit.getDefaultToolkit().getImage("image/player/horse"+(i+1)+".png");
		
		}
		

	}
	
	

	public void setpXY(int x,int y) {
		pXY[Game.crrPlayer][0]=x;
		pXY[Game.crrPlayer][1]=y;
	}



	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(backImg, 0, 0, getWidth(), getHeight(),this);
		for(int i=0; i<pXY.length; i++){
			
				//g.drawImage(horse[i], DFT_X+(BLOCK_WIDTH*Game.p[i].crrX), DFT_Y+(BLOCK_WIDTH*Game.p[i].crrY), this);
			g.drawImage(horse[i], DFT_X+(BLOCK_WIDTH*pXY[i][0]), DFT_Y+(BLOCK_WIDTH*pXY[i][1]), this);
			
			
		}
		
	}

}
