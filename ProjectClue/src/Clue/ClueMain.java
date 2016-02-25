package Clue;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

import com.sist.common.Function;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ClueMain extends JFrame implements ActionListener,
KeyListener,Runnable,MouseListener{

	CardLayout card;
	GameWaitingRoom gwr = new GameWaitingRoom();
	Login login = new Login();
	GameMainScreen mainScreen = new GameMainScreen();
	CardSelect cs = new CardSelect();

	LoadingTest loading= new LoadingTest(this); //160204 정선 추가
	ReachRoom reachRoom =new ReachRoom();
	WaitRoom wait=new WaitRoom(); //160211 정선추가
	
	Join_Login join=new Join_Login();//160211 정선 추가
	WR_MakeRoom mkr=new WR_MakeRoom(); //160211 정선 추가
	ShowTurn  jfTurn=new ShowTurn();
	FinalCard fc=new FinalCard();
	
	 // 소켓 연결시도
	 

	Socket s;

	BufferedReader in;	//서버에서 값을 읽는다
	OutputStream out;	//서버로 요청값을 보낸다.
	int n=0;
	 
    String myRoom,myId;
   int myNum;

	public ClueMain() {

		card = new CardLayout();
		setLayout(card);
		
		add("LOG",login);
		add("WR", wait);
		add("GWR", gwr);
		add("MS", mainScreen);
		add("LD", loading); // 160204정선추가
		add("CS", cs);
		add("FC", fc);

		setSize(1200, 900);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		login.b1.addActionListener(this);
		login.b2.addActionListener(this);// 160211 정선추가
		wait.b1.addActionListener(this);// 160211 정선추가
		wait.b2.addActionListener(this);// 160211 정선추가
		wait.b6.addActionListener(this);// 160217 찬재추가
		wait.tf.addActionListener(this);// 160211 정선추가
		mkr.b1.addActionListener(this);// 160211 정선추가
    	mkr.b2.addActionListener(this);
    	wait.table1.addMouseListener(this);
    	wait.table2.addMouseListener(this);
    	//방에서
		gwr.chatInput.addActionListener(this);	//채팅입력
		gwr.btnReady.addActionListener(this);	//준비
		gwr.btnExit.addActionListener(this);	//나가기
		for(int i=1; i<=6; i++){
			gwr.chr[i].addActionListener(this);
		}
		
		for(int i=0; i<cs.p.length; i++){
			cs.p[i].addActionListener(this);
		}
		for(int i=0; i<cs.q.length; i++){
			cs.q[i].addActionListener(this);
		}
		for(int i=1; i<cs.j.length; i++){
			cs.j[i].addActionListener(this);
		}
		
		for(int i=0; i<fc.p.length; i++){
			fc.p[i].addActionListener(this);
		}
		for(int i=0; i<fc.q.length; i++){
			fc.q[i].addActionListener(this);
		}
		for(int i=1; i<fc.j.length; i++){
			fc.j[i].addActionListener(this);
		}
		
		mainScreen.b.addActionListener(this);	//채팅입력
		cs.st.addActionListener(this);	//추리-카드선택
		

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2, getWidth(), getHeight());

		addKeyListener(this);
		setFocusable(true);
		reachRoom.b1.addActionListener(this);
		reachRoom.b2.addActionListener(this);
		mainScreen.ChatInput.addActionListener(this);
		jfTurn.b1.addActionListener(this);
		mainScreen.jpGameBoard.addMouseListener(this);
		
	}

	// 소켓
	public void connection(String id, String name, String sex) {
		try {
			s = new Socket("localhost", 3355);
			// s=server
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));// 바이트를
																				// 캐릭터러

			// s는 클라이언트
			out = s.getOutputStream(); // 서버로 보내겠다.
			out.write((Function.LOGIN + "|" + id + "|" + name + "|" + sex + "\n").getBytes()); // 값보내기

		} catch (Exception e) {
			// TODO: handle exception
		}

		// 서버로부터 응답값을 받아서 처리

		new Thread(this).start();// run 돈다.
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (Exception ex) {
		}
		ClueMain mn = new ClueMain();

	}

	@Override

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == login.b1) {
			String id = login.tf.getText().trim();
			if (id.length() < 1) {
				JOptionPane.showMessageDialog(this, "ID를 입력하세요");
				login.tf.requestFocus();
				return;
			}

			String name = login.tf2.getText().trim();
			if (id.length() < 1) {
				JOptionPane.showMessageDialog(this, "대화명을 입력하세요");
				login.tf2.requestFocus();
				return;
			}
			String sex = "남";

			/*
			 * if(login.man.isSelected()){ sex="남자"; }else sex="여자";
			 */
			connection(id, name, sex);
			// repaint();
			// card.show(getContentPane(),"WR");
		} else if (e.getSource() == login.b2) {
			join.setVisible(true);

		} 
		// 방만들기 버튼
		else if(e.getSource()==wait.b1)
		{	mkr.tf.setText("");
			mkr.rb1.setSelected(true);
			mkr.la3.setVisible(false);
			mkr.pf.setText("");
			mkr.pf.setVisible(false);
			mkr.box.setSelectedIndex(0);

			mkr.setVisible(true);

		}//160211 정선추가 
		else if(e.getSource()==wait.b2)
		{

			repaint();
			card.show(getContentPane(), "GWR");
		} // 160211 정선 추가
		else if (e.getSource() == wait.tf) {
			String msg = wait.tf.getText().trim();
			if (msg.length() < 1)
				return;
			String color = wait.box.getSelectedItem().toString();
			initStyle();
			append(msg, color);
			wait.tf.setText("");

		}//160211 정선추가
		//방만들기 창
		else if(e.getSource()==mkr.b1)
		{
			/*repaint();
			card.show(getContentPane(),"GWR");
			mkr.setVisible(false);*/
			String rname=mkr.tf.getText().trim();
			  if(rname.length()<1)
			  {
				  JOptionPane.showMessageDialog(this,"방이름을 입력하세요");
				  mkr.tf.requestFocus();
				  return;
			  }
			  //중복체크 
			  String str="";
			  for(int i=0;i<wait.model1.getRowCount();i++)
			  {
				   str=wait.model1.getValueAt(i, 0).toString();
				   if(rname.equals(str))
				   {
					   JOptionPane.showMessageDialog(this, "이미 존재하는 방입니다");
					   mkr.tf.setText("");
					   mkr.tf.requestFocus();
					   return;
				   }
			  }
			  String state="",pwd="";
			  if(mkr.rb1.isSelected())
			  {
				  state="공개";
				  pwd=" ";
			  }
			  else
			  {
				  state="비공개";
				  pwd=String.valueOf(mkr.pf.getPassword());//char[] ==> 문자열로 변환
				  //mr.pf.getText();
			  }
			  
			  int inwon=4;
			  
			  //서버로 전송 
			  try
			  {
				  out.write((Function.MAKEROOM+"|"+rname+"|"
			                    +state+"|"+pwd+"|"+inwon+"\n").getBytes());
			  }catch(Exception ex){}
			  
			  mkr.setVisible(false);
			
		}//160211 정선추가
		else if(e.getSource()==mkr.b2)
		{
			mkr.setVisible(false);
		
		
		
		} // 160211 정선추가
		else if (e.getSource() == wait.b6) {
			System.exit(0);

		}

		

		// ################## GameWaitngRoom   // gwr 160217 찬재추가

		else if (e.getSource() == gwr.chatInput) {
			String data = gwr.chatInput.getText();
			gwr.chat.append(data + "\n");
			gwr.chatInput.setText("");
		} else if (e.getSource() == gwr.btnReady) {
			try
			{
				if(gwr.btnReady.getText().equals("START"))
					out.write((Function.STARTGAME+"|"+myRoom+"\n").getBytes());
				else 
				 out.write((Function.READY+"|"+myRoom+"\n").getBytes());
			}catch(Exception ex){}

			gwr.btnReady.setEnabled(false);
			
		} else if (e.getSource() == gwr.btnExit) {

			try
			{
				 out.write((Function.ROOMOUT+"|"+myRoom+"\n").getBytes());
			}catch(Exception ex){}
		}

		else if (e.getSource() == gwr.chatInput) {
			String data = gwr.chatInput.getText();
			gwr.chat.append(data + "\n");
			gwr.chatInput.setText("");
		} 
		else if (e.getSource() == gwr.chr[1]) {
		
			int avata=1;
			try
			{
				 out.write((Function.CHOOSECHAR+"|"+avata+"\n").getBytes());
			}catch(Exception ex){}
			
			
			
		} else if (e.getSource() == gwr.chr[2]) {
			int avata=2;
			try
			{
				 out.write((Function.CHOOSECHAR+"|"+avata+"\n").getBytes());
			}catch(Exception ex){}
			
		} else if (e.getSource() == gwr.chr[3]) {
			int avata=3;
			try
			{
				 out.write((Function.CHOOSECHAR+"|"+avata+"\n").getBytes());
			}catch(Exception ex){}
		} else if (e.getSource() == gwr.chr[4]) {
			int avata=4;
			try
			{
				 out.write((Function.CHOOSECHAR+"|"+avata+"\n").getBytes());
			}catch(Exception ex){}
			
		} else if (e.getSource() == gwr.chr[5]) {
			int avata=5;
			try
			{
				out.write((Function.CHOOSECHAR+"|"+avata+"\n").getBytes());
			}catch(Exception ex){}
			} else if (e.getSource() == gwr.chr[6]) {
				int avata=6;
				try
				{
					 out.write((Function.CHOOSECHAR+"|"+avata+"\n").getBytes());
				}catch(Exception ex){}

		} else if (e.getSource() == cs.st) {
			/*repaint();
			card.previous(getContentPane());
			card.show(getContentPane(), "MS");

			mainScreen.game.savePlayerStatus();
			mainScreen.game.setGamePlayer(Game.crrPlayer, mainScreen.game.runDice());

			mainScreen.showCount();
			mainScreen.setImage();
			mainScreen.jpGameBoard.repaint();*/
			int pGiveHint=Game.crrPlayer+1;
			String sr=cs.tfGuess[0].getText();
			String sp=cs.tfGuess[1].getText();
			String sw=cs.tfGuess[2].getText();
			int hint=-1;
			for(int i=0;i<3;i++){
				hint=mainScreen.game.getHint(pGiveHint,sr,sp,sw);
				if(hint>=0)
					break;
				else pGiveHint++;
			}
			//0 -> 방. 1 -> 범인 2->무기 -1 ->없음
			try
			{
				 out.write((Function.HINT+"|"+myRoom+"|"+mainScreen.game.p[pGiveHint%4].id+"|"+(pGiveHint+1)%4+"|"+sr+"|"+sp+"|"+sw+"|"+hint+"\n").getBytes());
			}catch(Exception ex){
				System.out.println("guessing"+ex);
			}
			
			
			
			for(int i=0; i<3;i++){
				if(hint==i){
					//JOptionPane.showMessageDialog(getContentPane(), (Game.crrPlayer%4)+1+"P가 "+cs.tfGuess[i].getText()+"를 가지고 있습니다.");
					mainScreen.ta.append("[나에게만 알림]"+(pGiveHint+1)+"P가 "+cs.tfGuess[i].getText()+"를 가지고 있습니다."+"\n");
					
					break;
				}
			}
			
			
			
			//채팅창
			
			/*need to decide the action after guessing.
			either show up one dialog or either give a msg on chatarea*/
			
		} else if (e.getSource() == reachRoom.b1) {
			try
			{
				 out.write((Function.GUESS+"|"+myRoom+"|"+n+"\n").getBytes());
			}catch(Exception ex){}
			
			
			reachRoom.setVisible(false);
		}else if(e.getSource()==mainScreen.ChatInput)
		{
			 String msg=mainScreen.ChatInput.getText().trim();
			 if(msg.length()<1)
				 return;
			 //서버로 전송 
			 /*
			  *   사람 찾는다 ==> id (waitVc)
			  *   방에 있는 사람 ==> roomName(userVc)
			  */
			 try
			 {
				 out.write((Function.ROOMCHAT+"|"+myRoom+"|"+msg+"\n").getBytes());
				 // Server ==> in.readLine() (Thread==> run())
				 /*
				  *   1. 이벤트 발생 (Button,Mouse)   
				  *      ==> 서버로 요청값 전송
				  *      ******** 브라우저으로 클릭 , 주소 변경 
				  *                 login.jsp?id=aaa&pwd=1234
				  *   2. Server (통신을 담당하는 쓰레드에서 처리)
				  *      class Client
				  *      {
				  *          public void run(){}
				  *      }
				  *      ==> 요청한 결과값을 클라이언트로 전송 
				  *      ==> 웹서버 ==> 처리 결과를 클라이언트 브라우저로 전송
				  *   3. run() : 서버에서 들어오는 응답을 받아서 
				  *       윈도우에 출력 (브라우저 출력)
				  *       
				  *   Client ==> Server ==> Client
				  *   오라클 
				  *     요청 ==> 결과값 ==> 브라우저 전송(출력)
				  *     SQL  
				  *   웹서버
				  *     요청 (브라우저) ==> 파일요청 ==> 
				  *     파일 찾기 ==> 파일내용 브라우저 전송
				  */
			 }catch(Exception ex){}
			 mainScreen.ChatInput.setText("");
		}else if(e.getSource()==jfTurn.b1){
			try
			{
				 out.write((Function.SETTURN+"|"+myRoom+"\n").getBytes());
			}catch(Exception ex){}
			jfTurn.setVisible(false);
		}
		else if(e.getSource()==reachRoom.b2)
		{
			try
			{
				 out.write((Function.FINALGUESS+"|"+myRoom+"|"+n+"\n").getBytes());
			}catch(Exception ex){}
			
			
			reachRoom.setVisible(false);
		}
		/*else if (e.getSource() == fc.st) {
			
			int pGiveHint=Game.crrPlayer+1;
			String sr=fc.tfGuess[0].getText();
			String sp=fc.tfGuess[1].getText();
			String sw=fc.tfGuess[2].getText();
			int hint=-1;
			for(int i=0;i<3;i++){
				hint=mainScreen.game.getHint(pGiveHint,sr,sp,sw);
				if(hint>=0)
					break;
				else pGiveHint++;
			}
			//0 -> 방. 1 -> 범인 2->무기 -1 ->없음
			try
			{
				 out.write((Function.HINT+"|"+myRoom+"|"+mainScreen.game.p[pGiveHint%4].id+"|"+(pGiveHint+1)%4+"|"+sr+"|"+sp+"|"+sw+"|"+hint+"\n").getBytes());
			}catch(Exception ex){
				System.out.println("guessing"+ex);
			}
			
			
			
			for(int i=0; i<3;i++){
				if(hint==i){
					//JOptionPane.showMessageDialog(getContentPane(), (Game.crrPlayer%4)+1+"P가 "+cs.tfGuess[i].getText()+"를 가지고 있습니다.");
					mainScreen.ta.append("[나에게만 알림]"+(pGiveHint+1)+"P가 "+cs.tfGuess[i].getText()+"를 가지고 있습니다."+"\n");
					
					break;
				}
			}

		}*/
		
		//########## 경은/ CS 버튼
		
		else if(e.getSource()==cs.p[0])
		{
			try{ out.write((Function.CHOOSECARD+"|"+0+"\n").getBytes());}catch(Exception ex){}
		}else if(e.getSource()==cs.p[1])
		{
			
			try{ out.write((Function.CHOOSECARD+"|"+1+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.p[2])
		{
			try{ out.write((Function.CHOOSECARD+"|"+2+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.p[3])
		{
			try{ out.write((Function.CHOOSECARD+"|"+3+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.p[4])
		{
			try{ out.write((Function.CHOOSECARD+"|"+4+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.p[5])
		{
			try{ out.write((Function.CHOOSECARD+"|"+5+"\n").getBytes());}catch(Exception ex){}
		}
	
		
		//############## cs/ 무기
		else if(e.getSource()==cs.q[0])
		{
			try{ out.write((Function.CHOOSECARD+"|"+6+"\n").getBytes());}catch(Exception ex){}
			
		}else if(e.getSource()==cs.q[1])
		{
			try{ out.write((Function.CHOOSECARD+"|"+7+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.q[2])
		{
			try{ out.write((Function.CHOOSECARD+"|"+8+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.q[3])
		{
			try{ out.write((Function.CHOOSECARD+"|"+9+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.q[4])
		{
			try{ out.write((Function.CHOOSECARD+"|"+10+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.q[5])
		{
			try{ out.write((Function.CHOOSECARD+"|"+11+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.q[6])
		{
			try{ out.write((Function.CHOOSECARD+"|"+12+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.q[7])
		{
			try{ out.write((Function.CHOOSECARD+"|"+13+"\n").getBytes());}catch(Exception ex){}
		}
		
		//########### 방
		else if(e.getSource()==cs.j[0])
		{
			try{ out.write((Function.CHOOSECARD+"|"+14+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.j[1])
		{
			try{ out.write((Function.CHOOSECARD+"|"+15+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.j[2])
		{
			try{ out.write((Function.CHOOSECARD+"|"+16+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.j[3])
		{
			try{ out.write((Function.CHOOSECARD+"|"+17+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.j[4])
		{
			try{ out.write((Function.CHOOSECARD+"|"+18+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.j[5])
		{
			try{ out.write((Function.CHOOSECARD+"|"+19+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.j[6])
		{
			try{ out.write((Function.CHOOSECARD+"|"+20+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.j[7])
		{
			try{ out.write((Function.CHOOSECARD+"|"+21+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.j[8])
		{
			try{ out.write((Function.CHOOSECARD+"|"+22+"\n").getBytes());}catch(Exception ex){}
		}
		
		// ########### 최종 선택
		else if(e.getSource()==fc.p[0])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+0+"\n").getBytes());}catch(Exception ex){}
		}else if(e.getSource()==fc.p[1])
		{
			
			try{ out.write((Function.FINALCHOOSECARD+"|"+1+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==fc.p[2])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+2+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==fc.p[3])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+3+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==fc.p[4])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+4+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==fc.p[5])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+5+"\n").getBytes());}catch(Exception ex){}
		}
	
		
		//############## fc/ 무기
		else if(e.getSource()==fc.q[0])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+6+"\n").getBytes());}catch(Exception ex){}
			
		}else if(e.getSource()==fc.q[1])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+7+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==fc.q[2])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+8+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==fc.q[3])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+9+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==fc.q[4])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+10+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==fc.q[5])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+11+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==fc.q[6])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+12+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==fc.q[7])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+13+"\n").getBytes());}catch(Exception ex){}
		}
		
		//########### fc/방
		else if(e.getSource()==fc.j[0])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+14+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==fc.j[1])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+15+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==fc.j[2])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+16+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==fc.j[3])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+17+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==fc.j[4])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+18+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==fc.j[5])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+19+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==fc.j[6])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+20+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==fc.j[7])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+21+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==fc.j[8])
		{
			try{ out.write((Function.FINALCHOOSECARD+"|"+22+"\n").getBytes());}catch(Exception ex){}
		}
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//mainScreen.game.gp.keyPressed(e);
		
		if(myNum==Game.crrPlayer){//내가 현재 플레이어일때.
		int key=-1;
		
		//mainScreen.game.gp.keyPressed(e);
		
		switch(e.getKeyCode()){
		case KeyEvent.VK_RIGHT:
			key=3;
			break;
		case KeyEvent.VK_LEFT:
			key=2;
			break;
		case KeyEvent.VK_UP:
			key=0;
			break;
		case KeyEvent.VK_DOWN:
			key=1;
			break;
		}
		
		try{
			
			out.write((Function.MOVE+"|"+myRoom+"|"+(myNum+1)+"|"+key+"\n").getBytes());
			}catch(Exception ex){
			//reachRoom.setVisible(true);
		}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void initStyle() // 160211 정선추가
	{
		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		Style blue = wait.ta.addStyle("blue", def);
		StyleConstants.setForeground(blue, Color.blue);

		Style pink = wait.ta.addStyle("pink", def);
		StyleConstants.setForeground(pink, Color.pink);

		Style green = wait.ta.addStyle("green", def);
		StyleConstants.setForeground(green, Color.green);

		Style cyan = wait.ta.addStyle("cyan", def);
		StyleConstants.setForeground(cyan, Color.cyan);

	}

	public void append(String msg, String color) // 160211 정선추가
	{
		try {
			Document doc = wait.ta.getDocument();
			doc.insertString(doc.getLength(), msg + "\n", wait.ta.getStyle(color));
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				String msg = in.readLine();
				System.out.println("Server=>" + msg);
				StringTokenizer st = new StringTokenizer(msg, "|");
				int protocol = Integer.parseInt(st.nextToken());
				switch (protocol) {

				case Function.LOGIN: {
					String[] data = { st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken() };
					wait.model2.addRow(data);
				}
					break;

				case Function.MYLOG: {
					String id = st.nextToken();
					setTitle(id);
					repaint();
					card.show(getContentPane(), "WR");
				}
					break;

				case Function.WAITCHAT: {
					// wait.ta.append(st.nextToken()+"\n");
					append(st.nextToken() + "\n", "Color.BLUE");
					wait.bar.setValue(wait.bar.getMaximum());
				}

				break;
				case Function.MAKEROOM:
				{
					 String[] temp={st.nextToken(),st.nextToken(),st.nextToken()};
					 wait.model1.addRow(temp);
				}
				
				
				break;
				case Function.ROOMADD:
				{
					 String id=st.nextToken();
					 String sex=st.nextToken();
					 
					 String s="";
					 if(sex.equals("남자")) 
						 s="m";
					 else 
						 s="w";
					 
					 for(int i=0;i<4;i++)
					 {
						  if(!gwr.sw[i])
						  {
							  gwr.sw[i]=true;
							  gwr.idtf[i].setText(id);
							  
							  break;
						  }
					 }
					 
					
				}
				break;
				case Function.ROOMIN:
				{
					 String id=st.nextToken();
					 String sex=st.nextToken();					 
					 myRoom=st.nextToken();
					 int pnum = Integer.parseInt(st.nextToken());
					 
					 String s="";
					 if(sex.equals("남자")) 
						 s="m";
					 else 
						 s="w";
					 
					 for(int i=0;i<4;i++)
					 {
						  if(!gwr.sw[i])
						  {
							  gwr.sw[i]=true;
							  gwr.idtf[i].setText(id);
							  
							  
							  break;
						  }
					 }
					 String[] temp={id,sex};
					 card.show(getContentPane(), "GWR");
					 
					 if(pnum==1){
						 gwr.btnReady.setText("START");
						 gwr.btnReady.setEnabled(false);
/*<<<<<<< HEAD
						 gwr.isReady[pnum-1].setFont(new Font("맑은 고딕", Font.ITALIC, 20));
						 gwr.isReady[pnum-1].setForeground(Color.PINK);
						 gwr.isReady[pnum-1].setText("방장");
					 }
=======*/
					 }
						 gwr.isReady[0].setFont(new Font("맑은 고딕", Font.ITALIC, 20));
						 gwr.isReady[0].setForeground(Color.PINK);
						 gwr.isReady[0].setText("방장");
					 
				
//>>>>>>> refs/heads/master
					
				}
				break;
				case Function.REFLUSH:
				{
					 String id=st.nextToken();
					 String pos=st.nextToken();
					 String str="";
					 for(int i=0;i<wait.model2.getRowCount();i++)
					 {
						 str=wait.model2.getValueAt(i, 0).toString();
						 if(str.equals(id))
						 {
							 wait.model2.setValueAt(pos, i, 3);
							 break;
						 }
					 }
				}
				break;
				case Function.ROOMOUT:
				{
					String id=st.nextToken();
					for(int i=0;i<4;i++)
					 {
						String mid=gwr.idtf[i].getText();
						if(mid.equals(id))
						{
							 gwr.sw[i]=false;
							 gwr.idtf[i].setText("");
							 gwr.aa[i].removeAll();
							 gwr.aa[i].setLayout(new BorderLayout());
							 gwr.aa[i].add("Center",
									 new JLabel(new ImageIcon("image/back/qcard.png")));
							 gwr.aa[i].validate();
						}
					 }
				}
					break;
				case Function.MYROOMOUT:
				{
					
					for(int i=0;i<4;i++)
						{
					 gwr.sw[i]=false;
					 gwr.idtf[i].setText("");
					 gwr.aa[i].removeAll();
					 gwr.aa[i].setLayout(new BorderLayout());
					 gwr.aa[i].add("Center",
							 new JLabel(new ImageIcon("image/back/qcard.png")));
					 gwr.aa[i].validate();
						}
							
					
					card.show(getContentPane(),"WR");
					repaint();
					
				}
				break;

				case Function.WAITROOMUPDATE:
				{
					 String id=st.nextToken();
					 String pos=st.nextToken();
					 String rname=st.nextToken();
					 String current=st.nextToken();
					 String max=st.nextToken();
					 
					 String temp="";
					 for(int i=0;i<wait.model1.getRowCount();i++)
					 {
						  temp=wait.model1.getValueAt(i, 0).toString();
						  if(temp.equals(rname))
						  {
							  if(Integer.parseInt(current)<1)
							  {
								   wait.model1.removeRow(i);
							  }
							  else
							  {
								  wait.model1.setValueAt(current+"/"+max, i, 2);
							  }
							  break;
						  }
					 }
					 for(int i=0;i<wait.model2.getRowCount();i++)
					 {
						 temp=wait.model2.getValueAt(i, 0).toString();
						 if(temp.equals(id))
						 {
							 wait.model2.setValueAt(pos, i, 3);
							 break;
						 }
					 }
				}
				break;
				
				case Function.GETREADY:
				{
					int pNum=Integer.parseInt(st.nextToken());//플레이어 넘버
					boolean ready=Boolean.parseBoolean(st.nextToken());
					if(ready)
					gwr.isReady[pNum-1].setText("준비완료");//캐릭터 바꾸기
				}
					break;
				
				case Function.AVATA:
					int pNum=Integer.parseInt(st.nextToken());//플레이어 넘버
					int charNum=Integer.parseInt(st.nextToken());//캐릭터 넘버
					int prvChar=Integer.parseInt(st.nextToken()); //이전캐릭
					
					chAvata(pNum-1,charNum);	//사진바꾸기
					gwr.avaName[pNum-1].setText(RefData.nameChar[charNum-1]);//캐릭터 바꾸기
					gwr.chr[charNum].setEnabled(false);
					gwr.chr[prvChar].setEnabled(true);
					
					break;
				case Function.STARTGAME:
				{	int[] ans= new int[3];
					int[][] pCard= new int[4][5];
					
					int pnum=Integer.parseInt(st.nextToken());
					int ava=Integer.parseInt(st.nextToken());
					for(int i=0;i<ans.length;i++){
						ans[i]=Integer.parseInt(st.nextToken());
					}
					for(int i=0;i<pCard.length;i++){
						for(int j=0; j<pCard[i].length; j++){
							pCard[i][j]=Integer.parseInt(st.nextToken());
						}
					}
					
					repaint();
					card.show(getContentPane(), "LD"); // 160204 정선추가
					new Thread(loading).start(); // 160204 정선추가
					mainScreen.gameStart(); //game생성자 호출
					mainScreen.game.setMyNum(pnum-1);
					this.myNum=pnum-1;
					mainScreen.game.setMyAva(ava);
					mainScreen.game.setAnswerCard(ans);
					mainScreen.game.setpCard(pCard);
					
					for(int i=1;i<=4;i++)
						mainScreen.game.setPlayer(i, gwr.avaName[i-1].getText().trim());
					
					mainScreen.jpMyCard.setCardImg(mainScreen.game.pCard[pnum-1]);//0번플레이어로
					mainScreen.showCount();
					mainScreen.setImage();
					
				}
				break;
				
				case Function.ALLREADY:
				{
					gwr.btnReady.setEnabled(true);
				}
				break;
				case Function.SELECTCARD:
				{
					String pnum=st.nextToken();
					int avata= Integer.parseInt(st.nextToken());
					int roomNo=Integer.parseInt(st.nextToken());
					//데이터를 cs에 넘겨줘서 처리.. 누가 어디에서 추리중,.
					for(int i=0;i<9;i++){
						cs.j[i].setEnabled(false);
					}
					repaint();
					
					cs.guess[0].removeAll();
					cs.guess[0].add(new JLabel(new ImageIcon(setImage("image/room/room"+(roomNo-1)+".jpg", cs.guess[0].getWidth(), cs.guess[0].getHeight()))));
					cs.guess[0].validate();//panel재배치
					
					cs.pl.removeAll();
					cs.pl.add(new JLabel(new ImageIcon(setImage("image/player/char"+ (avata-1) + ".jpg", cs.pl.getWidth(), cs.pl.getHeight()))));
					cs.pl.validate();//panel재배치
					
					cs.nPl.setText(RefData.nameChar[avata-1]+" 추리중");
					cs.tfGuess[0].setText(RefData.nameRoom[roomNo-1]);
					card.show(getContentPane(), "CS");
					
					cs.setCardImg();
				}
				break;
				
				case Function.MOVE:
				{
					String pnum=st.nextToken();
					int key= Integer.parseInt(st.nextToken());
					mainScreen.game.keyPressed(key);
					mainScreen.game.move();
					
					if(myNum==Game.crrPlayer){
						n=mainScreen.game.isReached();
					
					if(n!=0){
						reachRoom.setBounds(500,250,230,240);
						try{
						reachRoom.la1.setText(RefData.nameRoom[n-1]+"에 도달했습니다.");
						out.write((Function.REACHROOM+"|"+myRoom+"|"+(myNum+1)+"|"+RefData.nameRoom[n-1]+"\n").getBytes());
						}
						catch(Exception ex){
							
						}
						reachRoom.setVisible(true);
						
					}
					else if(mainScreen.game.getCount()==0){
						try{
									out.write((Function.FINISHTURN+"|"+myRoom+"\n").getBytes());
							}
							catch(Exception ex){
								
							}
						
						
						
					}
					}
					mainScreen.showCount();
					mainScreen.setImage();
					mainScreen.jpGameBoard.repaint();
					repaint();
					
					
					
					
					repaint();
					
				}
				break;
				
				case Function.ROOMCHAT:
				{
					 mainScreen.ta.append(st.nextToken()+"\n");
				}
				break;
				case Function.SETTURN:
				{
					Game.crrPlayer=Integer.parseInt(st.nextToken());
					Game.dice1=Integer.parseInt(st.nextToken());
					Game.dice2=Integer.parseInt(st.nextToken());
					mainScreen.game.setGamePlayer(Game.crrPlayer, (Game.dice1+Game.dice2));
					//new GameThread().start();
					mainScreen.showCount();
					mainScreen.setImage();
					mainScreen.jpGameBoard.repaint();
					repaint();
				}
				break;
				case Function.MYTURN:
				{
					showMyTurn();
					
				}
				break;
				case Function.FINISHTURN:
				{
					mainScreen.game.savePlayerStatus();
					
				}
				break;
				
				case Function.CHOOSECARD:
				{
					int cardnum=Integer.parseInt(st.nextToken());
					 System.out.println(cardnum);
					try{
					if(cardnum<6){
						cs.guess[1].removeAll();
						cs.guess[1].add(new JLabel(new ImageIcon(setImage("image/player/char"+cardnum+".jpg", cs.guess[0].getWidth(), cs.guess[0].getHeight()))));
						cs.guess[1].validate();//panel재배치
						cs.tfGuess[1].setText(RefData.nameChar[cardnum]);
					}else if(cardnum>13){
						cardnum=cardnum-13;
						cs.guess[0].removeAll();
						cs.guess[0].add(new JLabel(new ImageIcon(setImage("image/room/room+"+cardnum+".jpg", cs.guess[2].getWidth(), cs.guess[2].getHeight()))));
						cs.guess[0].validate();//panel재배치
						cs.tfGuess[0].setText(RefData.nameRoom[cardnum]);
					}else{
						cardnum=cardnum-6;
						cs.guess[2].removeAll();
						cs.guess[2].add(new JLabel(new ImageIcon(setImage("image/weapon/wp"+cardnum+".jpg", cs.guess[0].getWidth(), cs.guess[0].getHeight()))));
						cs.guess[2].validate();//panel재배치
						cs.tfGuess[2].setText(RefData.nameWp[cardnum]);
					}
					}catch(ArrayIndexOutOfBoundsException ex){
						System.out.println("ChooseCard: "+ex.getMessage());
					}
					
					
					
				}
				break;
				
				case Function.HINT:
				{
					card.show(getContentPane(), "MS");
					String avata=st.nextToken();
					int who = Integer.parseInt(st.nextToken());
					String r= st.nextToken();
					String p = st.nextToken();
					String w=  st.nextToken();
					
					mainScreen.jpGameBoard.setMsgText(avata,who,r,p,w);
					
					
					if(myNum==Game.crrPlayer){
						try{
									out.write((Function.FINISHTURN+"|"+myRoom+"\n").getBytes());
							}
							catch(Exception ex){
								
							}
					}
					Thread.sleep(4000);
					mainScreen.jpGameBoard.deleteMsg();
					
					cs.guess[0].removeAll();
					cs.guess[1].removeAll();
					cs.guess[2].removeAll();
					cs.tfGuess[0].setText("");
					cs.tfGuess[1].setText("");
					cs.tfGuess[2].setText("");;
					
				}
				break;
				case Function.MYHINT:
				{
					card.show(getContentPane(), "MS");
					String avata=st.nextToken();
					int who = Integer.parseInt(st.nextToken());
					String r= st.nextToken();
					String p = st.nextToken();
					String w=  st.nextToken();
					int hint=Integer.parseInt(st.nextToken());
					if(hint==0){
						mainScreen.jpGameBoard.setMsgText(avata,who,r,hint);
					}else if(hint==1){
						mainScreen.jpGameBoard.setMsgText(avata,who,p,hint);
					}else if(hint==2){
						mainScreen.jpGameBoard.setMsgText(avata,who,w,hint);
					}
					
					try {
						out.write((Function.FINISHTURN+"|"+myRoom+"\n").getBytes());
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					
					
					Thread.sleep(4000);
					
					mainScreen.jpGameBoard.deleteMsg();
					
					cs.guess[0].removeAll();
					cs.guess[1].removeAll();
					cs.guess[2].removeAll();
					cs.tfGuess[0].setText("");
					cs.tfGuess[1].setText("");
					cs.tfGuess[2].setText("");;
					
				}
				break;
				
				case Function.FINALCHOOSECARD:
				{
					int cardnum=Integer.parseInt(st.nextToken());
					 System.out.println(cardnum);
					try{
					if(cardnum<6){
						fc.guess[1].removeAll();
						fc.guess[1].add(new JLabel(new ImageIcon(setImage("image/player/char"+cardnum+".jpg", fc.guess[0].getWidth(), cs.guess[0].getHeight()))));
						fc.guess[1].validate();//panel재배치
						fc.tfGuess[1].setText(RefData.nameChar[cardnum]);
					}else if(cardnum>13){
						cardnum=cardnum-13;
						fc.guess[0].removeAll();
						fc.guess[0].add(new JLabel(new ImageIcon(setImage("image/room/room+"+cardnum+".jpg", fc.guess[2].getWidth(), cs.guess[2].getHeight()))));
						fc.guess[0].validate();//panel재배치
						fc.tfGuess[0].setText(RefData.nameRoom[cardnum]);
					}else{
						cardnum=cardnum-6;
						fc.guess[2].removeAll();
						fc.guess[2].add(new JLabel(new ImageIcon(setImage("image/weapon/wp"+cardnum+".jpg", fc.guess[0].getWidth(), fc.guess[0].getHeight()))));
						fc.guess[2].validate();//panel재배치
						fc.tfGuess[2].setText(RefData.nameWp[cardnum]);
					}
					}catch(ArrayIndexOutOfBoundsException ex){
						System.out.println("ChooseCard: "+ex.getMessage());
					}
				}
				break;
				case Function.FINALSELECTCARD:
				{
					String pnum=st.nextToken();
					int avata= Integer.parseInt(st.nextToken());
					int roomNo=Integer.parseInt(st.nextToken());
					//데이터를 fc에 넘겨줘서 처리. 누가 어디에서 추리중.
					for(int i=0;i<9;i++){
						cs.j[i].setEnabled(false);
					}
					repaint();
					
					fc.guess[0].removeAll();
					fc.guess[0].add(new JLabel(new ImageIcon(setImage("image/room/room"+(roomNo-1)+".jpg", fc.guess[0].getWidth(), fc.guess[0].getHeight()))));
					fc.guess[0].validate();//panel재배치
					
					fc.pl.removeAll();
					fc.pl.add(new JLabel(new ImageIcon(setImage("image/player/char"+ (avata-1) + ".jpg", fc.pl.getWidth(), fc.pl.getHeight()))));
					fc.pl.validate();//panel재배치
					
					fc.nPl.setText(RefData.nameChar[avata-1]+" 추리중");
					fc.tfGuess[0].setText(RefData.nameRoom[roomNo-1]);
					card.show(getContentPane(), "FC");
					
					fc.setCardImg();
				}
				break;
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==wait.table1)
		{
			if(e.getClickCount()==2)
			{
				 int row=wait.table1.getSelectedRow();
				 String rn=wait.model1.getValueAt(row, 0).toString();
				 String inwon=wait.model1.getValueAt(row, 2).toString();
				 StringTokenizer st=new StringTokenizer(inwon,"/");
				 // 6/6
				 String s1=st.nextToken();
				 String s2=st.nextToken();
				 
				 if(s1.equals(s2))
				 {
					 JOptionPane.showMessageDialog(this,"이미 방이 찼습니다");
					 return;
				 }
				 
				 try
				 {
					 out.write((Function.ROOMIN+"|"+rn+"\n").getBytes());
				 }catch(Exception ex){}
			}
		
		
		}else if(e.getSource()==mainScreen.jpGameBoard){
			setFocusable(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void chAvata(int pNum,int i){
		gwr.aa[pNum].removeAll();
		gwr.aa[pNum].add(new JLabel(new ImageIcon(setImage("image/player/char"+(i-1)+".jpg", 171, 250))));
		gwr.aa[pNum].validate();//panel재배치
	
		  
	}
	
	public void showMyTurn(){
				
		jfTurn.setBounds(450,300,300,300);
		jfTurn.setVisible(true);
	}

	private Image setImage(String filename, int width, int height) {
		// TODO Auto-generated method stub
		ImageIcon ii = new ImageIcon(filename);
		Image image = ii.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return image;
		//return null;
	}

}

	










