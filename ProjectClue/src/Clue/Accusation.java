package Clue;

import javax.swing.JFrame;

public class Accusation {
	GameArea gv;
	JFrame fr;
	
	public Accusation() {
		Game game = new Game(gv, fr);
		selectAnswerCard();
		
		
	}
	public int[] selectAnswerCard(){
		//����ī�����
		int[] answer = new int[3];
		answer[0]=(int)(Math.random()*6);//�÷��̾�
		answer[1]=(int)(Math.random()*8)+6;//����
		answer[2]=(int)(Math.random()*9)+14;//���
		//System.out.println(answer[0]+" "+answer[1]+" "+answer[2]+"\n");
		return answer;
	}
}
