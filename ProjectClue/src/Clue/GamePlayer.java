package Clue;


import java.awt.event.KeyEvent;


public class GamePlayer {
	private int crrX=6,crrY=6;
	private int inputX=0,inputY=0;
	Game game;
	private int count=0;
	
	
	GamePlayer(Game game){
		this.game=game;
		
		
	}
	
	public void keyReleased(KeyEvent e){
		inputX=0;
		inputY=0;
	}
	
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			System.out.println(e);
			inputY=-1;
			break;
		case KeyEvent.VK_DOWN:
			inputY=1;
			break;
		case KeyEvent.VK_LEFT:
			inputX=-1;
			break;
		case KeyEvent.VK_RIGHT:
			inputX=1;
			break;
		default: //다른 키 눌렀을 때
			count++;
		}
		count--;
		
	}
	
	public int getCrrX() {
		return crrX;
	}

	
	public int getCrrY() {
		return crrY;
	}

	

	/*public void paint(Graphics g){
		g.fillRect(crrX*(GameView.SIZE), crrY*(GameView.SIZE), GameView.SIZE, GameView.SIZE);
	}*/
	
	public void move(){
		int isChanged=0;
		if(crrX+inputX>=0 && crrX+inputX<13){
			crrX=crrX+inputX;
			game.pMain.crrX=crrX;
			GameArea.crrX=crrX;
			isChanged++;
		}
		
		if(crrY+inputY>=0 && crrY+inputY<13){
			crrY=crrY+inputY;
			game.pMain.crrY=crrY;
			GameArea.crrY=crrY;
			isChanged++;
		}
		
		if(isChanged<2){
			count++;
		}
		
		inputX=0;
		inputY=0;
	}
	public int isReached(){
		int roomNo=0;
		//방1~3
		if(crrY==0){
			if(crrX==1){
				roomNo=1;
			}else if(crrX==8)
				roomNo=2;
			else if(crrX==12)
				roomNo=3;
		}else if(crrX==12){
			if(crrY==4)
				roomNo=4;
			else if(crrY==10)
				roomNo=5;
		}else if(crrY==12){
			if(crrX==9)
				roomNo=6;
			else if(crrX==3)
				roomNo=7;
			
		}else if(crrX==0){
			if(crrY==9)
				roomNo=8;
			else if(crrY==3)
				roomNo=9;
		}
		return roomNo;
	}
	
	public int getCount(){
		return count;
	}

	public void setCrrX(int crrX) {
		this.crrX = crrX;
	}

	public void setCrrY(int crrY) {
		this.crrY = crrY;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	
	

}
