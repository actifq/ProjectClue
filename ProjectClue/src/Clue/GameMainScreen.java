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
	Image dice1,dice2; //정선
	Image jpLogo; //정선
	JPanel jpTurn,jpCount,jpDice;
	GameArea jpGameBoard;
	JTable table;
	JTextArea ta;
	JTextField ChatInput;
	JButton b;
	Game game;
	JLabel showCnt;
	JFrame jfTurn;
	Dice dice;//정선추가 160207
	
	

	public GameMainScreen() {
		dice=new Dice();//정선 추가 160207
		
		dice1=Toolkit.getDefaultToolkit().getImage(dice.getDiceImage1());//정선추가 160207
		dice2=Toolkit.getDefaultToolkit().getImage(dice.getDiceImage2());//정선추가 160207
		jpLogo=Toolkit.getDefaultToolkit().getImage("image/back/jplogo2.png");
		back=Toolkit.getDefaultToolkit().getImage("image/back/gwrback.jpg");
		//jpLogo=new JPanel();// 배너
		jpTurn=new JPanel();//턴이미지화면
		
		showCnt = new JLabel("0");
		showCnt.setFont(new Font("맑은고딕",Font.BOLD,50));
		jpCount = new JPanel();
		jpCount.add("CENTER",showCnt);
			//이미지 불러와서 판넬에 채우기
		
		jpGameBoard=new GameArea();//게임화면
		
		JScrollPane jsMemo=new JScrollPane(table);//메모카드
		jpDice=new ShowMyCard();//카드보여주기
		ta=new JTextArea();
		JScrollPane jsChatArea=new JScrollPane(ta);//채팅창
		ChatInput=new JTextField();	//채팅입력창
		b=new JButton("입력");	//채팅창
		
		jfTurn= new JFrame("주사위");
		

		setLayout(null);
		
		

		//jpLogo.setBounds(5, 10, 850, 90);
		jpTurn.setBounds(865, 10, 90, 90);
		
		jpCount.setBounds(955, 10, 180, 90);
		jpGameBoard.setBounds(5, 105, 850, 570);//게임화면

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
		g.drawImage(dice1, 1140, 10, this);//정선추가 160207
		g.drawImage(dice2, 1140, 55, this);//정선추가 160207
		g.drawImage(jpLogo, 5, 10, this);//정선추가 160211

	}
	//서버에서 받아와야 하는 것->플레이어 번호, 캐릭터카드 인덱스 ,id
	
	public void gameStart(){
		game= new Game(jpGameBoard,jfTurn);
	}
	
	public void showCount(){
		showCnt.setText(String.valueOf(game.gp.getCount()));
	}
	

}
