package Clue.display.gameboard;


import javax.swing.*;
import java.awt.*;


public class GameView extends JPanel{
	static final int SIZE=30;
	static final int ARR_SIZE=13; //�迭ũ��
	static int[][] arrBoard;
	
	

	
	public GameView(){
		
		
		//���� �ʱ�ȭ
				//������ �׵θ�=>1
				//�Ա� =>-1
				//�� =>1;
		arrBoard= new int[ARR_SIZE][ARR_SIZE];
		for(int i=0;i<ARR_SIZE;i++){
			for(int j=0;j<ARR_SIZE;j++){
				/*if(i==0 || i==ARR_SIZE-1 || j==0 || j==ARR_SIZE-1)
					arrBoard[i][j]=1;*/
				if(isReached(i,j))
					arrBoard[i][j]=-1;					
				}
			}
	}
	
	
	
	


	public void paint(Graphics g){
		super.paint(g);
		Graphics g2 = g;
		//�⺻ ������
		for(int i=0;i<ARR_SIZE;i++){
			for(int j=0;j<ARR_SIZE;j++){
				g2.setColor(Color.BLACK);
				if(arrBoard[i][j]==0){
					g2.drawRect(i*SIZE, j*SIZE, SIZE, SIZE);
				}else{
								
				if(arrBoard[i][j]==-1)
					g2.setColor(Color.pink);
				
					//g2.fillRect(i*SIZE, j*SIZE, SIZE, SIZE);
					
							
				}
				
			}
		}
		//�������
				
		g.fillRect(6*(SIZE), 6*(SIZE), SIZE, SIZE);
		
		
	}
	
	
	
	public boolean isReached(int i, int j){
		boolean room=false;
		int x=i-1;
		int y=j-1;
		//��1~3
		if(y==0){
			if(x==1){
				room=true;
			}else if(x==8)
				room=true;
			else if(x==12)
				room=true;
		}else if(x==12){
			if(y==4)
				room=true;
			else if(y==10)
				room=true;
		}else if(y==12){
			if(x==9)
				room=true;
			else if(x==3)
				room=true;
			
		}else if(x==0){
			if(y==9)
				room=true;
			else if(y==3)
				room=true;
		}
		
		return room;
	}
}
