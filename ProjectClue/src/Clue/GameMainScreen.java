package Clue;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import com.clue.note.*;

import java.awt.event.*;
import java.io.*;

public class GameMainScreen extends JPanel{
	
	Image back;
	Image dice1,dice2,jpTurn; //����
	Image jpLogo; //����
	JPanel jpCount,jpDice,jpNote1,jpNote2,jpNote3;

	ShowMyCard jpMyCard;
	GameArea jpGameBoard;
	JTextArea ta;
	JTextField ChatInput;
	JButton b;
	Game game;
	JLabel jlshowCnt;
	JFrame jfTurn;
	
	public GameMainScreen() {

		//back=Toolkit.getDefaultToolkit().getImage(" ");
		//jpLogo=new JPanel();// ���
		//jpTurn=new JPanel();//��ȭ��
		jpGameBoard=new GameArea();//����ȭ��
		jpDice=new JPanel();//�ֻ���
		
		//�߸���Ʈ
		jpNote1=new GameNote_js();
		jpNote2=new GameNote_js2();
		jpNote3=new GameNote_js3();

		dice1=Toolkit.getDefaultToolkit().getImage("image/dice/d1.png");
		dice2=Toolkit.getDefaultToolkit().getImage("image/dice/d1.png");	
		
		jpLogo=Toolkit.getDefaultToolkit().getImage("image/back/jplogo2.png");
		back=Toolkit.getDefaultToolkit().getImage("image/back/gwrback.jpg");

		jpTurn=Toolkit.getDefaultToolkit().getImage("image/player/schar.jpg");//���̹���ȭ��
		
		jlshowCnt = new JLabel("0");
		jlshowCnt.setFont(new Font("�������",Font.BOLD,50));
		jpCount = new JPanel();
		jpCount.add("CENTER",jlshowCnt);
			//�̹��� �ҷ��ͼ� �ǳڿ� ä���
		
		jpGameBoard=new GameArea();//����ȭ��
		
		jpMyCard=new ShowMyCard();//ī�庸���ֱ�
		ta=new JTextArea();
		JScrollPane jsChatArea=new JScrollPane(ta);		//ä��â
		ChatInput=new JTextField();		//ä���Է�â
		b=new JButton("�Է�");	//ä��â
		
		jfTurn= new JFrame("�ֻ���");
		//�߸���Ʈ
		 		
		jpNote1=new GameNote_js();
 		jpNote2=new GameNote_js2();
 		jpNote3=new GameNote_js3();

		setLayout(null);
		
		//jpTurn.setBounds(865, 10, 90, 90);
		jpCount.setBounds(995, 10, 140, 90);
		jpGameBoard.setBounds(5, 105, 850, 570);//����ȭ��
		
		jsChatArea.setBounds(5, 680, 600, 160);
		jpMyCard.setBounds(610, 680, 575, 185);
		ChatInput.setBounds(5, 840, 540, 25);
		b.setBounds(545, 840, 60, 25);
		jpNote1.setBounds(865, 105, 320, 186);
		jpNote2.setBounds(865, 295, 320, 186);
		jpNote3.setBounds(865, 485, 320, 186);
		
		//add(jpTurn);
		add(jpCount);
		add(jpGameBoard);

		add(jpMyCard);
		add(jsChatArea);
		add(ChatInput);
		add(b);
		
		add(jpNote1);
		add(jpNote2);
		add(jpNote3);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {  
		
		g.drawImage(back, 0, 0, getWidth(),getHeight(),this);
		g.drawImage(dice1, 1140, 10, this);//�����߰� 160207
		g.drawImage(dice2, 1140, 55, this);//�����߰� 160207
		g.drawImage(jpLogo, 5, 10, this);//�����߰� 160211
		g.drawImage(jpTurn,865, 10,this);
	}
	//�������� �޾ƿ;� �ϴ� ��->�÷��̾� ��ȣ, ĳ����ī�� �ε��� ,id
	
	public void gameStart(){
		game= new Game(jpGameBoard,jfTurn);
		/*try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jpMyCard.setCardImg(game.pCard[0]);//0���÷��̾��
		showCount();
		setImage();*/
		

	}
	public void setImage(){
		jpTurn=Toolkit.getDefaultToolkit().getImage("image/player/schar"+game.getpMain().getCharIndex()+".jpg");
		dice1=Toolkit.getDefaultToolkit().getImage("image/dice/d" + Game.dice1+ ".png");
		dice2=Toolkit.getDefaultToolkit().getImage("image/dice/d" + Game.dice2+ ".png");
	}
	
	public void showCount(){
		jlshowCnt.setText(String.valueOf(game.getCount()));
	}
	
}