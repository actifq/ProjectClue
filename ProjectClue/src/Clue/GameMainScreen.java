package Clue;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
public class GameMainScreen extends JPanel implements ActionListener{
	
	Image back;
	Image dice1,dice2,jpTurn; //정선
	Image jpLogo; //정선
	JPanel jpCount;
	ShowMyCard jpMyCard;
	GameArea jpGameBoard;

	JTable table;
	JTextArea ta;
	JTextField ChatInput;
	JButton b;
	Game game;
	JLabel jlshowCnt;
	JFrame jfTurn;
	AudioClip clip;
	
	GameWaitingRoom gwr= new GameWaitingRoom();
	

	public GameMainScreen() {

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
		
		JScrollPane jsMemo=new JScrollPane(table);//메모카드
		jpMyCard=new ShowMyCard();//카드보여주기
		ta=new JTextArea();
		JScrollPane jsChatArea=new JScrollPane(ta);//채팅창
		ChatInput=new JTextField();	//채팅입력창
		b=new JButton("입력");	//채팅창
		
		jfTurn= new JFrame("주사위");
		

		setLayout(null);
		
		//jpTurn.setBounds(865, 10, 90, 90);
		jpCount.setBounds(995, 10, 140, 90);
		jpGameBoard.setBounds(5, 105, 850, 570);//게임화면
		jsMemo.setBounds(865, 105, 320, 570);
		jsChatArea.setBounds(5, 680, 600, 160);
		jpMyCard.setBounds(610, 680, 575, 185);
		ChatInput.setBounds(5, 840, 540, 25);
		b.setBounds(545, 840, 60, 25);

		//add(jpTurn);
		add(jpCount);
		add(jpGameBoard);

		add(jsMemo);
		add(jpMyCard);
		add(jsChatArea);
		add(ChatInput);
		add(b);

		try {
            File file = new File("wav/Game_bgm_low.wav");
            clip = Applet.newAudioClip(file.toURL());
            clip.stop();
            
           
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
		
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
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jpMyCard.setCardImg(game.pCard[0]);//0번플레이어로
		showCount();
		setImage();
		

	}
	public void setImage(){
		jpTurn=Toolkit.getDefaultToolkit().getImage("image/player/schar"+game.getpMain().getCharIndex()+".jpg");
		dice1=Toolkit.getDefaultToolkit().getImage("image/dice/d" + Game.dice1+ ".png");
		dice2=Toolkit.getDefaultToolkit().getImage("image/dice/d" + Game.dice2+ ".png");
	}
	
	public void showCount(){
		jlshowCnt.setText(String.valueOf(game.gp.getCount()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==gwr.btnReady)
		{
			clip.play();
		}
	}
	
}