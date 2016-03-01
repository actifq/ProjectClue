package com.clue;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

import com.clue.common.Function;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ClueMain extends JFrame implements ActionListener,
KeyListener,Runnable,MouseListener,FocusListener{

	CardLayout card;
	JP_GameWaitingRoom gwr = new JP_GameWaitingRoom();
	JP_Login login = new JP_Login();
	JP_GameMainScreen mainScreen = new JP_GameMainScreen();
	JP_CardSelect cs = new JP_CardSelect();
	JP_FinalCardSelect fcs = new JP_FinalCardSelect();

	JP_Loading loading= new JP_Loading(this); //160204 정선 추가
	GMS_JF_ReachRoom reachRoom =new GMS_JF_ReachRoom();
	JP_WaitRoom wait=new JP_WaitRoom(); //160211 정선추가
	
	
	WR_JF_MakeRoom mkr=new WR_JF_MakeRoom(); //160211 정선 추가
	GMS_JF_ShowTurn  jfTurn=new GMS_JF_ShowTurn();
	int[] removedP={-1,-1};
	
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
		add("FCS",fcs);		
		

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
			fcs.p[i].addActionListener(this);
		}
		for(int i=0; i<cs.q.length; i++){
			cs.q[i].addActionListener(this);
			fcs.q[i].addActionListener(this);
		}
		for(int i=0; i<fcs.j.length; i++){
			cs.j[i].addActionListener(this);
			fcs.j[i].addActionListener(this);
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
		fcs.st.addActionListener(this);
		mainScreen.jpGameBoard.addMouseListener(this);
		mainScreen.jpGameBoard.b1.addActionListener(this);
		
		
		//addFocusListener(this);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				//ClueMain.this.mainScreen.requestDefaultFocus();
				ClueMain.this.setFocusable(true);
			}
			
		});
	}

	// 소켓
	public void connection(String id, String name, String sex) {
		try {
			s = new Socket("localhost", 7777);
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
			//join.setVisible(true);

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
				 out.write((Function.CHOOSECHAR+"|"+myRoom+"|"+avata+"\n").getBytes());
			}catch(Exception ex){}
			
			
			
		} else if (e.getSource() == gwr.chr[2]) {
			int avata=2;
			try
			{
				 out.write((Function.CHOOSECHAR+"|"+myRoom+"|"+avata+"\n").getBytes());
			}catch(Exception ex){}
			
		} else if (e.getSource() == gwr.chr[3]) {
			int avata=3;
			try
			{
				 out.write((Function.CHOOSECHAR+"|"+myRoom+"|"+avata+"\n").getBytes());
			}catch(Exception ex){}
		} else if (e.getSource() == gwr.chr[4]) {
			int avata=4;
			try
			{
				 out.write((Function.CHOOSECHAR+"|"+myRoom+"|"+avata+"\n").getBytes());
			}catch(Exception ex){}
			
		} else if (e.getSource() == gwr.chr[5]) {
			int avata=5;
			try
			{
				out.write((Function.CHOOSECHAR+"|"+myRoom+"|"+avata+"\n").getBytes());
			}catch(Exception ex){}
			} else if (e.getSource() == gwr.chr[6]) {
				int avata=6;
				try
				{
					 out.write((Function.CHOOSECHAR+"|"+myRoom+"|"+avata+"\n").getBytes());
				}catch(Exception ex){}

		} else if (e.getSource() == cs.st) {
			String sr=cs.tfGuess[0].getText();
			String sp=cs.tfGuess[1].getText();
			String sw=cs.tfGuess[2].getText();
		
			
			// ####### 중간추리
			
		
			int pGiveHint=Game.crrPlayer+1;
			
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
				System.out.println("MIDDLERESULT");
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
		} else if (e.getSource() == fcs.st) {
			String sr=fcs.tfGuess[0].getText();
			String sp=fcs.tfGuess[1].getText();
			String sw=fcs.tfGuess[2].getText();
			
			
			// ####### 최종추리
			
				boolean result =mainScreen.game.finalGusee(sr, sp, sw);
				try
				{
					System.out.println("FINALRESULT");
					 out.write((Function.FINALRESULT+"|"+myRoom+"|"+result+"|"+mainScreen.game.p[myNum%4].id+"|"+sr+"|"+sp+"|"+sw+"\n").getBytes());
				}catch(Exception ex){
					System.out.println("guessing"+ex);
				}
				
				
			
			
			
		} else if (e.getSource() == reachRoom.b1) {
			try
			{
				 out.write((Function.GUESS+"|"+myRoom+"|"+n+"\n").getBytes());
			}catch(Exception ex){}
			
			
			reachRoom.setVisible(false);
		
		} else if (e.getSource() == reachRoom.b2) {
			try
			{
				 out.write((Function.FINALGUESS+"|"+myRoom+"\n").getBytes());
			}catch(Exception ex){}
			
			
			reachRoom.setVisible(false);
		}
		else if(e.getSource()==mainScreen.ChatInput)
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
		
		
		//########## 경은/ CS 버튼
		
		else if(e.getSource()==cs.p[0])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+0+"\n").getBytes());}catch(Exception ex){}
		}else if(e.getSource()==cs.p[1])
		{
			
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+1+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.p[2])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+2+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.p[3])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+3+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.p[4])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+4+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.p[5])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+5+"\n").getBytes());}catch(Exception ex){}
		}
	
		
		//############## cs/ 무기
		else if(e.getSource()==cs.q[0])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+6+"\n").getBytes());}catch(Exception ex){}
			
		}else if(e.getSource()==cs.q[1])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+7+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.q[2])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+8+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.q[3])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+9+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.q[4])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+10+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.q[5])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+11+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.q[6])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+12+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.q[7])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+13+"\n").getBytes());}catch(Exception ex){}
		}
		
		//########### 방
		else if(e.getSource()==cs.j[0])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+14+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.j[1])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+15+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.j[2])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+16+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.j[3])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+17+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.j[4])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+18+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.j[5])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+19+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.j[6])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+20+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.j[7])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+21+"\n").getBytes());}catch(Exception ex){}
		}
		else if(e.getSource()==cs.j[8])
		{
			try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"cs"+"|"+22+"\n").getBytes());}catch(Exception ex){}
		}
		
		
		//########## 경은/ CS 버튼
		
				else if(e.getSource()==fcs.p[0])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+0+"\n").getBytes());}catch(Exception ex){}
				}else if(e.getSource()==fcs.p[1])
				{
					
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+1+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==fcs.p[2])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+2+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==fcs.p[3])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+3+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==fcs.p[4])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+4+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==fcs.p[5])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+5+"\n").getBytes());}catch(Exception ex){}
				}
			
				
				//############## fcs/ 무기
				else if(e.getSource()==fcs.q[0])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+6+"\n").getBytes());}catch(Exception ex){}
					
				}else if(e.getSource()==fcs.q[1])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+7+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==fcs.q[2])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+8+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==fcs.q[3])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+9+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==fcs.q[4])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+10+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==fcs.q[5])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+11+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==fcs.q[6])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+12+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==fcs.q[7])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+13+"\n").getBytes());}catch(Exception ex){}
				}
				
				//########### 방
				else if(e.getSource()==fcs.j[0])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+14+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==fcs.j[1])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+15+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==fcs.j[2])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+16+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==fcs.j[3])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+17+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==fcs.j[4])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+18+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==fcs.j[5])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+19+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==fcs.j[6])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+20+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==fcs.j[7])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+21+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==fcs.j[8])
				{
					try{ out.write((Function.CHOOSECARD+"|"+myRoom+"|"+"fcs"+"|"+22+"\n").getBytes());}catch(Exception ex){}
				}
				else if(e.getSource()==mainScreen.jpGameBoard.b1)
				{
					
						System.exit(0);
										
					
				}
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
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
				System.out.println(ex.getMessage());
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
					 }
						 gwr.isReady[0].setFont(new Font("맑은 고딕", Font.ITALIC, 20));
						 gwr.isReady[0].setForeground(Color.PINK);
						 gwr.isReady[0].setText("방장");
					 
				
					
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
					gwr.avaName[pNum-1].setText(Game_RefData.nameChar[charNum-1]);//캐릭터 바꾸기
					gwr.chr[charNum].setEnabled(false);
					gwr.chr[prvChar].setEnabled(true);
					
					break;
				case Function.STARTGAME:
				{

					int[] ans= new int[3];
					int[][] pCard= new int[4][5];
					int  avata[]=new int[4];
					
					
					int pnum=Integer.parseInt(st.nextToken());//1부터 시작
					avata[0]=Integer.parseInt(st.nextToken());//1부터 시작
					avata[1]=Integer.parseInt(st.nextToken());//1부터 시작
					avata[2]=Integer.parseInt(st.nextToken());//1부터 시작
					avata[3]=Integer.parseInt(st.nextToken());//1부터 시작
					for(int i=0;i<ans.length;i++){
						ans[i]=Integer.parseInt(st.nextToken());
					}
					for(int i=0;i<pCard.length;i++){
						for(int j=0; j<pCard[i].length; j++){
							pCard[i][j]=Integer.parseInt(st.nextToken());
						}
					}
					System.out.println(ans[0]+" "+ ans[1]+ " "+ans[2]);
					
					repaint();
					card.show(getContentPane(), "LD"); // 160204 정선추가
					
					new Thread(loading).start(); // 160204 정선추가
					
					mainScreen.gameStart(); //game생성자 호출
					mainScreen.game.setMyNum(pnum-1);
					this.myNum=pnum-1;
					mainScreen.game.setMyAva(avata[pnum-1]);
					mainScreen.game.setAnswerCard(ans);
					mainScreen.game.setpCard(pCard);
					
					
					
					
					
					for(int i=1;i<=4;i++)
						mainScreen.game.setPlayer(i, avata[i-1]);
					
					mainScreen.jpMyCard.setCardImg(mainScreen.game.pCard[pnum-1]);
					
					
					System.out.println("STARTGAME>>"+mainScreen.game.p[0].charIndex+" "+
							
							mainScreen.game.p[1].charIndex+" "+
							mainScreen.game.p[2].charIndex+" "+
							mainScreen.game.p[3].charIndex);
					
					mainScreen.jpLogo.setImage(avata[0],avata[1],avata[2],avata[3]);
					
					
				}
				break;
				
				case Function.ALLREADY:
				{
					gwr.btnReady.setEnabled(true);
				}
				break;
				case Function.SELECTCARD:
				{
					int pnum=Integer.parseInt(st.nextToken());
					int avata= Integer.parseInt(st.nextToken());
					int roomNo=Integer.parseInt(st.nextToken());
					//데이터를 cs에 넘겨줘서 처리.. 누가 어디에서 추리중,.
					
					repaint();
					
					cs.guess[0].removeAll();
					cs.guess[0].add(new JLabel(new ImageIcon(setImage("image/room/room"+(roomNo-1)+".jpg", cs.guess[0].getWidth(), cs.guess[0].getHeight()))));
					cs.guess[0].validate();//panel재배치
					
					cs.pl.removeAll();
					cs.pl.add(new JLabel(new ImageIcon(setImage("image/player/char"+ (avata-1) + ".jpg", cs.pl.getWidth(), cs.pl.getHeight()))));
					cs.pl.validate();//panel재배치
					
					cs.nPl.setText(Game_RefData.nameChar[avata-1]+" 추리중");
					cs.tfGuess[0].setText(Game_RefData.nameRoom[roomNo-1]);
					
					if((pnum-1)!=mainScreen.game.getMyNum()){
						cs.st.setVisible(false);
					}else
						cs.st.setVisible(true);
					
					card.show(getContentPane(), "CS");
					
					//cs.setCardImg();
				}
				break;
				case Function.FINALSELECTCARD:
				{
					int pnum=Integer.parseInt(st.nextToken());
					int avata= Integer.parseInt(st.nextToken());
					
					//데이터를 cs에 넘겨줘서 처리.. 누가 어디에서 추리중,.
					
					repaint();
					
					/*cs.guess[0].removeAll();
					cs.guess[0].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", cs.guess[0].getWidth(), cs.guess[0].getHeight()))));
					cs.guess[0].validate();//panel재배치
*/					
					fcs.pl.removeAll();
					fcs.pl.add(new JLabel(new ImageIcon(setImage("image/player/char"+ (avata-1) + ".jpg", cs.pl.getWidth(), cs.pl.getHeight()))));
					fcs.pl.validate();//panel재배치
					
					fcs.nPl.setText(Game_RefData.nameChar[avata-1]+" 추리중");
					fcs.tfGuess[0].setText("");
					
					
					if((pnum-1)!=mainScreen.game.getMyNum()){
						fcs.st.setVisible(false);
					}else
						fcs.st.setVisible(true);
					
					card.show(getContentPane(), "FCS");
					
					//fcs.setCardImg();
				}
				break;
				
				case Function.MOVE:
				{
					String pnum=st.nextToken();
					int key= Integer.parseInt(st.nextToken());
					mainScreen.game.keyPressed(key);
					mainScreen.game.move();
					
					if(myNum==Game.crrPlayer){
						/*if(removedP[0]==myNum || removedP[1]==myNum){
							try{
								out.write((Function.FINISHTURN+"|"+myRoom+"\n").getBytes());
						}
						catch(Exception ex){
							
						}
							
							break;
						}*/
						n=mainScreen.game.isReached();
					
					if(n!=0){
						reachRoom.setBounds(500,250,300,300);
						try{
						reachRoom.la1.setText("  "+Game_RefData.nameRoom[n-1]+"에 도달했습니다.");
						out.write((Function.REACHROOM+"|"+myRoom+"|"+(myNum+1)+"|"+Game_RefData.nameRoom[n-1]+"\n").getBytes());
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
				{	String flag=st.nextToken();
					int cardnum=Integer.parseInt(st.nextToken());
					 System.out.println(cardnum);
					try{
					if(flag.equals("cs")){
						if(cardnum<6){
						cs.guess[1].removeAll();
						cs.guess[1].add(new JLabel(new ImageIcon(setImage("image/player/char"+cardnum+".jpg", cs.guess[0].getWidth(), cs.guess[0].getHeight()))));
						cs.guess[1].validate();//panel재배치
						cs.tfGuess[1].setText(Game_RefData.nameChar[cardnum]);
						
						
						}else if(cardnum>=14){
							cardnum=cardnum-14;
							cs.guess[0].removeAll();
							cs.guess[0].add(new JLabel(new ImageIcon(setImage("image/room/room+"+cardnum+".jpg", cs.guess[0].getWidth(), cs.guess[0].getHeight()))));
							cs.guess[0].validate();//panel재배치
							cs.tfGuess[0].setText(Game_RefData.nameRoom[cardnum]);
							
							
						}else{
							cardnum=cardnum-6;
							cs.guess[2].removeAll();
							cs.guess[2].add(new JLabel(new ImageIcon(setImage("image/weapon/wp"+cardnum+".jpg", cs.guess[0].getWidth(), cs.guess[0].getHeight()))));
							cs.guess[2].validate();//panel재배치
							cs.tfGuess[2].setText(Game_RefData.nameWp[cardnum]);
						
						}
					}else if(flag.equals("fcs")){
						if(cardnum<6){
							fcs.guess[1].removeAll();
							fcs.guess[1].add(new JLabel(new ImageIcon(setImage("image/player/char"+cardnum+".jpg", cs.guess[0].getWidth(), cs.guess[0].getHeight()))));
							fcs.guess[1].validate();//panel재배치
							fcs.tfGuess[1].setText(Game_RefData.nameChar[cardnum]);
							
						}else if(cardnum>=6 && cardnum<14){
							
							
							cardnum=cardnum-6;
							fcs.guess[2].removeAll();
							fcs.guess[2].add(new JLabel(new ImageIcon(setImage("image/weapon/wp"+cardnum+".jpg", cs.guess[0].getWidth(), cs.guess[0].getHeight()))));
							fcs.guess[2].validate();//panel재배치
							fcs.tfGuess[2].setText(Game_RefData.nameWp[cardnum]);
						}else{
							cardnum=cardnum-14;
							fcs.guess[0].removeAll();
							fcs.guess[0].add(new JLabel(new ImageIcon(setImage("image/room/room"+cardnum+".jpg", cs.guess[2].getWidth(), cs.guess[2].getHeight()))));
							fcs.guess[0].validate();//panel재배치
							fcs.tfGuess[0].setText(Game_RefData.nameRoom[cardnum]);
						
						}
							
						/*	}else if(cardnum>=14){
								cardnum=cardnum-14;
								fcs.guess[0].removeAll();
								fcs.guess[0].add(new JLabel(new ImageIcon(setImage("image/room/room+"+cardnum+".jpg", cs.guess[2].getWidth(), cs.guess[2].getHeight()))));
								fcs.guess[0].validate();//panel재배치
								fcs.tfGuess[0].setText(RefData.nameRoom[cardnum]);
								
								
							}else{
								cardnum=cardnum-6;
								fcs.guess[2].removeAll();
								fcs.guess[2].add(new JLabel(new ImageIcon(setImage("image/weapon/wp"+cardnum+".jpg", cs.guess[0].getWidth(), cs.guess[0].getHeight()))));
								fcs.guess[2].validate();//panel재배치
								fcs.tfGuess[2].setText(RefData.nameWp[cardnum]);
							
							}*/
						
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
					if(who==0)
						who=4;
					mainScreen.jpGameBoard.setMsgText(0,avata,who,r,p,w);
					
					
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
					fcs.guess[1].removeAll();
					cs.guess[1].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", cs.guess[0].getWidth(), cs.guess[0].getHeight()))));
					cs.guess[1].validate();//panel재배치
					cs.guess[2].removeAll();
					cs.guess[2].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", cs.guess[0].getWidth(), cs.guess[0].getHeight()))));
					cs.guess[2].validate();//panel재배치
					
					cs.tfGuess[0].setText("");
					cs.tfGuess[1].setText("");
					cs.tfGuess[2].setText("");
					
				}
				break;
				case Function.MYHINT:
				{
					card.show(getContentPane(), "MS");
					String avata=st.nextToken();
					int who = Integer.parseInt(st.nextToken());
					if(who==0)
						who=4;
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
					cs.guess[1].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", cs.guess[0].getWidth(), cs.guess[0].getHeight()))));
					cs.guess[1].validate();//panel재배치
					cs.guess[2].removeAll();
					cs.guess[2].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", cs.guess[0].getWidth(), cs.guess[0].getHeight()))));
					cs.guess[2].validate();//panel재배치
					
					cs.tfGuess[0].setText("");
					cs.tfGuess[1].setText("");
					cs.tfGuess[2].setText("");
					
				}
				break;
				case Function.CORRECTANSWER:
				{
					card.show(getContentPane(), "MS");
					String avata=st.nextToken();
					int who = Integer.parseInt(st.nextToken());
					String r= st.nextToken();
					String p = st.nextToken();
					String w=  st.nextToken();
											
					mainScreen.jpGameBoard.setMsgText(1,avata,who,r,p,w);
					// #### 게임종료후 게임 대기실로 돌아가기
					
				}
				break;
				case Function.WRONGANSWER:
				{
					card.show(getContentPane(), "MS");
					String avata=st.nextToken();//플레이어이름
					int who = Integer.parseInt(st.nextToken());//플레이어번호
					int iAvata=Integer.parseInt(st.nextToken());
					
					mainScreen.jpGameBoard.setWrongMsg(avata, who,iAvata-1);
					if(myNum==Game.crrPlayer){
						try{
									out.write((Function.FINISHTURN+"|"+myRoom+"\n").getBytes());
							}
							catch(Exception ex){
								
							}
					}
					Thread.sleep(4000);
					mainScreen.jpGameBoard.deleteWrongMsg();
					fcs.guess[0].removeAll();
					fcs.guess[0].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", fcs.guess[0].getWidth(), fcs.guess[0].getHeight()))));
					fcs.guess[0].validate();//panel재배치
					fcs.guess[1].removeAll();
					fcs.guess[1].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", fcs.guess[0].getWidth(), fcs.guess[0].getHeight()))));
					fcs.guess[1].validate();//panel재배치
					fcs.guess[2].removeAll();
					fcs.guess[2].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", fcs.guess[0].getWidth(), fcs.guess[0].getHeight()))));
					fcs.guess[2].validate();//panel재배치
					
					fcs.tfGuess[0].setText("");
					fcs.tfGuess[1].setText("");
					fcs.tfGuess[2].setText("");
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
			this.requestFocus(true);
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

	@Override
	public void focusGained(FocusEvent e) {
		/*if(e.getSource()==mainScreen.jpGameBoard){
			setFocusable(true);
		}*/
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		/*if(e.getSource()==mainScreen.jpGameBoard){
			setFocusable(false);
		}*/
	}
	
	
	public void removePlayer(){
		
		
	}
	

}

	










