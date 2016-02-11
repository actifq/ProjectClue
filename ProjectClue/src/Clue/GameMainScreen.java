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
	Image back,dice1,dice2;//정선추가160207
	JPanel jpLogo,jpTurn,jpGameBoard,jpDice;
	JTable table;
	JTextArea ta;
	JTextField ChatInput;
	JButton b;
	ClueMain cluemain;//이거 제가 추가했나요?ㅋㅋ
	Dice dice;//정선추가 160207
	

	
	public GameMainScreen() {
		dice=new Dice();//정선 추가 160207
		dice1=Toolkit.getDefaultToolkit().getImage(dice.getDiceImage1());//정선추가 160207
		dice2=Toolkit.getDefaultToolkit().getImage(dice.getDiceImage2());//정선추가 160207
		back=Toolkit.getDefaultToolkit().getImage("image/gwrback.jpg");
		jpLogo=new JPanel();// 배너
		//jpTurn=new JPanel();//턴화면
		jpGameBoard=new GameArea();//게임화면
		JScrollPane jsMemo=new JScrollPane(table);//메모카드
		jpDice=new JPanel();//주사위
		
		//채팅
		ta=new JTextArea();
		JScrollPane jsChatArea=new JScrollPane(ta);//채팅창
		ChatInput=new JTextField();	//채팅입력창
		b=new JButton("입력");	//채팅창
		
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
		g.drawImage(dice1, 1140, 10, this);//정선추가 160207
		g.drawImage(dice2, 1140, 55, this);//정선추가 160207
	}
	
	
	
}
