package Clue;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;


import java.awt.Graphics;

import Clue.ClueMain;
import Clue.Dice;
public class GameMainScreen extends JPanel {
	Image back,dice1,dice2;//�����߰�160207
	JPanel jpLogo,jpTurn,jpGameBoard,jpDice;
	JTable table;
	JTextArea ta;
	JTextField ChatInput;
	JButton b;
	ClueMain cluemain;//�̰� ���� �߰��߳���?����
	Dice dice;//�����߰� 160207
	

	
	public GameMainScreen() {
		dice=new Dice();//���� �߰� 160207
		dice1=Toolkit.getDefaultToolkit().getImage(dice.getDiceImage1());//�����߰� 160207
		dice2=Toolkit.getDefaultToolkit().getImage(dice.getDiceImage2());//�����߰� 160207
		back=Toolkit.getDefaultToolkit().getImage("image/gwrback.jpg");
		jpLogo=new JPanel();// ���
		//jpTurn=new JPanel();//��ȭ��
		jpGameBoard=new GameArea();//����ȭ��
		JScrollPane jsMemo=new JScrollPane(table);//�޸�ī��
		jpDice=new JPanel();//�ֻ���
		
		//ä��
		ta=new JTextArea();
		JScrollPane jsChatArea=new JScrollPane(ta);//ä��â
		ChatInput=new JTextField();	//ä���Է�â
		b=new JButton("�Է�");	//ä��â
		
		setLayout(null);
		jpLogo.setBounds(5, 10, 850, 90);
		//jpTurn.setBounds(865, 10, 320, 90);
		jpGameBoard.setBounds(5, 105, 850, 570);
		jsMemo.setBounds(865, 105, 320, 570);
		jsChatArea.setBounds(5, 680, 850, 160);
		jpDice.setBounds(865, 680, 320, 185);
		ChatInput.setBounds(5, 840, 790, 25);
		b.setBounds(795, 840, 60, 25);
		
		
		add(jpLogo);
		//add(jpTurn);
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
	}
	
	
	
}
