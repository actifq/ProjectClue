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
	

	 // 소켓 연결시도
	 
	Socket s;

	BufferedReader in;	//서버에서 값을 읽는다
	OutputStream out;	//서버로 요청값을 보낸다.
	
	 
    String myRoom,myId;


	public ClueMain() {

		card = new CardLayout();
		setLayout(card);
		
		add("LOG",login);
		add("WR", wait);
		add("GWR", gwr);
		add("MS", mainScreen);
		add("LD", loading); // 160204정선추가
		add("CS", cs);

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
		
		
		mainScreen.b.addActionListener(this);	//채팅입력
		cs.st.addActionListener(this);	//추리-카드선택
		
		

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2, getWidth(), getHeight());

		addKeyListener(this);
		setFocusable(true);
		reachRoom.b1.addActionListener(this);
		reachRoom.b2.addActionListener(this);

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
			//gwr.chat.append(data + "\n");
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
			
			/*repaint();
			 * card.show(getContentPane(), "LD"); // 160204 정선추가
			new Thread(loading).start(); // 160204 정선추가
*/
		} else if (e.getSource() == gwr.btnExit) {
			/*repaint();
			card.previous(getContentPane());*/
			try
			{
				 out.write((Function.ROOMOUT+"|"+myRoom+"\n").getBytes());
			}catch(Exception ex){}
		}

		else if (e.getSource() == gwr.chatInput) {
			String data = gwr.chatInput.getText();
			//gwr.chat.append(data + "\n");
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
			repaint();
			card.previous(getContentPane());
			card.show(getContentPane(), "MS");

			mainScreen.game.savePlayerStatus();
			mainScreen.game.setGamePlayer(Game.crrPlayer, mainScreen.game.runDice());

			mainScreen.showCount();
			mainScreen.setImage();
			mainScreen.jpGameBoard.repaint();

			// mainScreen.mc.show(getParent(), "GB");
		} else if (e.getSource() == reachRoom.b1) {
			repaint();
			card.show(getContentPane(), "CS");
			cs.setCardImg();
			reachRoom.setVisible(false);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		mainScreen.game.gp.keyPressed(e);

		
		int n=mainScreen.game.process();
		if(n!=0){
			reachRoom.setBounds(500,250,230,240);
			try{
			reachRoom.la1.setText(RefData.nameRoom[n-1]+"에 도달했습니다.");}
			catch(Exception ex){
				
			}

			reachRoom.setVisible(true);
		}
		mainScreen.showCount();
		mainScreen.setImage();
		mainScreen.jpGameBoard.repaint();
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

					 //int pNum=Integer.parseInt(st.nextToken());
					 //int avata=Integer.parseInt(st.nextToken());
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
					 //chAvata(pNum-1,avata);	//사진바꾸기
					 //gwr.avaName[pNum-1].setText("캐릭터:"+RefData.nameChar[avata-1]);//캐릭터 바꾸기
					 
					//String[] temp={id,sex,avata};
					
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
						 gwr.isReady[pnum-1].setFont(new Font("맑은 고딕", Font.ITALIC, 20));
						 gwr.isReady[pnum-1].setForeground(Color.PINK);
						 gwr.isReady[pnum-1].setText("방장");
					 }
					
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
					gwr.avaName[pNum-1].setText("캐릭터:"+RefData.nameChar[charNum-1]);//캐릭터 바꾸기
					gwr.chr[charNum].setEnabled(false);
					gwr.chr[prvChar].setEnabled(true);
					
					break;
				case Function.STARTGAME:
				{
					
				}
				break;
				
				case Function.ALLREADY:
				{
					gwr.btnReady.setEnabled(true);
				}
				break;
				}
			} catch (Exception e) {
				// TODO: handle exception
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

	private Image setImage(String filename, int width, int height) {
		// TODO Auto-generated method stub
		ImageIcon ii = new ImageIcon(filename);
		Image image = ii.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return image;
		//return null;
	}

}
