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
	static int crrX=6,crrY=6;
	
	

	public GameArea(){


		backImg=Toolkit.getDefaultToolkit().getImage("image/back/boardBack.png");
		for(int i=0;i<horse.length;i++){
			
		
		horse[i]=Toolkit.getDefaultToolkit().getImage("image/player/horse"+(i+1)+".png");
		
		}
		

	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(backImg, 0, 0, getWidth(), getHeight(),this);
		for(int i=0; i<Game.p.length; i++){
			
				//g.drawImage(horse[i], DFT_X+(BLOCK_WIDTH*crrX), DFT_Y+(BLOCK_WIDTH*crrY), this);
			
				g.drawImage(horse[i], DFT_X+(BLOCK_WIDTH*Game.p[i].crrX), DFT_Y+(BLOCK_WIDTH*Game.p[i].crrY), this);
			
			
		}
		/*g.drawImage(horse1, DFT_X+(BLOCK_WIDTH*crrX), DFT_Y+(BLOCK_WIDTH*crrY), this);//6=> gettCrrX
		g.drawImage(horse2, DFT_X+(BLOCK_WIDTH*crrX), DFT_Y+(BLOCK_WIDTH*crrY), this);//6=> gettCrrX
		g.drawImage(horse3, DFT_X+(BLOCK_WIDTH*crrX), DFT_Y+(BLOCK_WIDTH*crrY), this);//6=> gettCrrX
		g.drawImage(horse4, DFT_X+(BLOCK_WIDTH*crrX), DFT_Y+(BLOCK_WIDTH*crrY), this);//6=> gettCrrX

		
		*/
	
	
	
	}

}
