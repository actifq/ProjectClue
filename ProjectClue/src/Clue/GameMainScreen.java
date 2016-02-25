package Clue;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import com.clue.note.*;

import java.awt.event.*;
import java.io.*;

public class GameMainScreen extends JPanel{
	
	Image back;
	Image dice1,dice2,jpTurn; //정선
	Image jpLogo; //정선
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
		//jpLogo=new JPanel();// 배너
		//jpTurn=new JPanel();//턴화면
		jpGameBoard=new GameArea();//게임화면
		jpDice=new JPanel();//주사위
		
		//추리노트
		jpNote1=new GameNote_js();
		jpNote2=new GameNote_js2();
		jpNote3=new GameNote_js3();

		dice1=Toolkit.getDefaultToolkit().getImage("image/dice/d1.png");
		dice2=Toolkit.getDefaultToolkit().getImage("image/dice/d1.png");	
		
		jpLogo=Toolkit.getDefaultToolkit().getImage("image/back/jplogo2.png");
		back=Toolkit.getDefaultToolkit().getImage("image/back/gwrback.jpg");

		jpTurn=Toolkit.getDefaultToolkit().getImage("image/player/schar.jpg");//턴이미지화면
		
		jlshowCnt = new JLabel("0");
		jlshowCnt.setFont(new Font("맑은고딕",Font.BOLD,50));
		jpCount = new JPanel();
		jpCount.add("CENTER",jlshowCnt);
			//이미지 불러와서 판넬에 채우기
		
		jpGameBoard=new GameArea();//게임화면
		
		jpMyCard=new ShowMyCard();//카드보여주기
		ta=new JTextArea();
		JScrollPane jsChatArea=new JScrollPane(ta);		//채팅창
		ChatInput=new JTextField();		//채팅입력창
		b=new JButton("입력");	//채팅창
		
		jfTurn= new JFrame("주사위");
		//추리노트
		 		
		jpNote1=new GameNote_js();
 		jpNote2=new GameNote_js2();
 		jpNote3=new GameNote_js3();

		setLayout(null);
		
		//jpTurn.setBounds(865, 10, 90, 90);
		jpCount.setBounds(995, 10, 140, 90);
		jpGameBoard.setBounds(5, 105, 850, 570);//게임화면
		
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
		g.drawImage(dice1, 1140, 10, this);//정선추가 160207
		g.drawImage(dice2, 1140, 55, this);//정선추가 160207
		g.drawImage(jpLogo, 5, 10, this);//정선추가 160211
		g.drawImage(jpTurn,865, 10,this);
	}
	//서버에서 받아와야 하는 것->플레이어 번호, 캐릭터카드 인덱스 ,id
	
	public void gameStart(){
		game= new Game(jpGameBoard,jfTurn);
		/*try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jpMyCard.setCardImg(game.pCard[0]);//0번플레이어로
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