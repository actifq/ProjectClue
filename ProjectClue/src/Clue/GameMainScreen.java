package Clue;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
public class GameMainScreen extends JPanel{
	Image back;
	Image dice1,dice2; //����
	Image jpLogo; //����
	JPanel jpTurn,jpCount,jpDice;
	GameArea jpGameBoard;
	JTable table;
	JTextArea ta;
	JTextField ChatInput;
	JButton b;
	Game game;
	JLabel showCnt;
	JFrame jfTurn;
	Dice dice;//�����߰� 160207
	
	

	public GameMainScreen() {
		dice=new Dice();//���� �߰� 160207
		
		dice1=Toolkit.getDefaultToolkit().getImage(dice.getDiceImage1());//�����߰� 160207
		dice2=Toolkit.getDefaultToolkit().getImage(dice.getDiceImage2());//�����߰� 160207
		jpLogo=Toolkit.getDefaultToolkit().getImage("image/back/jplogo2.png");
		back=Toolkit.getDefaultToolkit().getImage("image/back/gwrback.jpg");
		//jpLogo=new JPanel();// ���
		jpTurn=new JPanel();//���̹���ȭ��
		
		showCnt = new JLabel("0");
		showCnt.setFont(new Font("�������",Font.BOLD,50));
		jpCount = new JPanel();
		jpCount.add("CENTER",showCnt);
			//�̹��� �ҷ��ͼ� �ǳڿ� ä���
		
		jpGameBoard=new GameArea();//����ȭ��
		
		JScrollPane jsMemo=new JScrollPane(table);//�޸�ī��
		jpDice=new ShowMyCard();//ī�庸���ֱ�
		ta=new JTextArea();
		JScrollPane jsChatArea=new JScrollPane(ta);//ä��â
		ChatInput=new JTextField();	//ä���Է�â
		b=new JButton("�Է�");	//ä��â
		
		jfTurn= new JFrame("�ֻ���");
		

		setLayout(null);
		
		

		//jpLogo.setBounds(5, 10, 850, 90);
		jpTurn.setBounds(865, 10, 90, 90);
		
		jpCount.setBounds(955, 10, 180, 90);
		jpGameBoard.setBounds(5, 105, 850, 570);//����ȭ��

		jsMemo.setBounds(865, 105, 320, 570);
		jsChatArea.setBounds(5, 680, 600, 160);
		jpDice.setBounds(610, 680, 575, 185);
		ChatInput.setBounds(5, 840, 540, 25);
		b.setBounds(545, 840, 60, 25);
		
		//add(jpLogo);
		add(jpTurn);
		add(jpCount);
		add(jpGameBoard);

		add(jsMemo);
		add(jpDice);
		add(jsChatArea);
		add(ChatInput);
		add(b);
		add(dice);
		
	}
	@Override
	protected void paintComponent(Graphics g) {  
		
		g.drawImage(back, 0, 0, getWidth(),getHeight(),this);
		g.drawImage(dice1, 1140, 10, this);//�����߰� 160207
		g.drawImage(dice2, 1140, 55, this);//�����߰� 160207
		g.drawImage(jpLogo, 5, 10, this);//�����߰� 160211

	}
	//�������� �޾ƿ;� �ϴ� ��->�÷��̾� ��ȣ, ĳ����ī�� �ε��� ,id
	
	public void gameStart(){
		game= new Game(jpGameBoard,jfTurn);
	}
	
	public void showCount(){
		showCnt.setText(String.valueOf(game.gp.getCount()));
	}
	

}
