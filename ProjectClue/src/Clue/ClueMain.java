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

	LoadingTest loading= new LoadingTest(this); //160204 ���� �߰�
	ReachRoom reachRoom =new ReachRoom();
	WaitRoom wait=new WaitRoom(); //160211 �����߰�
	
	Join_Login join=new Join_Login();//160211 ���� �߰�
	WR_MakeRoom mkr=new WR_MakeRoom(); //160211 ���� �߰�
	ShowTurn  jfTurn=new ShowTurn();
	
	
	 // ���� ����õ�
	 

	Socket s;

	BufferedReader in;	//�������� ���� �д´�
	OutputStream out;	//������ ��û���� ������.
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
		add("LD", loading); // 160204�����߰�
		add("CS", cs);

		setSize(1200, 900);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		login.b1.addActionListener(this);
		login.b2.addActionListener(this);// 160211 �����߰�
		wait.b1.addActionListener(this);// 160211 �����߰�
		wait.b2.addActionListener(this);// 160211 �����߰�
		wait.b6.addActionListener(this);// 160217 �����߰�
		wait.tf.addActionListener(this);// 160211 �����߰�
		mkr.b1.addActionListener(this);// 160211 �����߰�


    	mkr.b2.addActionListener(this);
    	wait.table1.addMouseListener(this);
    	wait.table2.addMouseListener(this);
    	//�濡��
		gwr.chatInput.addActionListener(this);	//ä���Է�
		gwr.btnReady.addActionListener(this);	//�غ�
		gwr.btnExit.addActionListener(this);	//������
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
		
		mainScreen.b.addActionListener(this);	//ä���Է�
		cs.st.addActionListener(this);	//�߸�-ī�弱��
		
		

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

	// ����
	public void connection(String id, String name, String sex) {
		try {
			s = new Socket("localhost", 3355);
			// s=server
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));// ����Ʈ��
																				// ĳ���ͷ�

			// s�� Ŭ���̾�Ʈ
			out = s.getOutputStream(); // ������ �����ڴ�.
			out.write((Function.LOGIN + "|" + id + "|" + name + "|" + sex + "\n").getBytes()); // ��������

		} catch (Exception e) {
			// TODO: handle exception
		}

		// �����κ��� ���䰪�� �޾Ƽ� ó��

		new Thread(this).start();// run ����.
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
				JOptionPane.showMessageDialog(this, "ID�� �Է��ϼ���");
				login.tf.requestFocus();
				return;
			}

			String name = login.tf2.getText().trim();
			if (id.length() < 1) {
				JOptionPane.showMessageDialog(this, "��ȭ���� �Է��ϼ���");
				login.tf2.requestFocus();
				return;
			}
			String sex = "��";

			/*
			 * if(login.man.isSelected()){ sex="����"; }else sex="����";
			 */
			connection(id, name, sex);
			// repaint();
			// card.show(getContentPane(),"WR");
		} else if (e.getSource() == login.b2) {
			join.setVisible(true);

		} 
		// �游��� ��ư
		else if(e.getSource()==wait.b1)
		{	mkr.tf.setText("");
			mkr.rb1.setSelected(true);
			mkr.la3.setVisible(false);
			mkr.pf.setText("");
			mkr.pf.setVisible(false);
			mkr.box.setSelectedIndex(0);

			mkr.setVisible(true);

		}//160211 �����߰� 
		else if(e.getSource()==wait.b2)
		{

			repaint();
			card.show(getContentPane(), "GWR");
		} // 160211 ���� �߰�
		else if (e.getSource() == wait.tf) {
			String msg = wait.tf.getText().trim();
			if (msg.length() < 1)
				return;
			String color = wait.box.getSelectedItem().toString();
			initStyle();
			append(msg, color);
			wait.tf.setText("");

		}//160211 �����߰�
		//�游��� â
		else if(e.getSource()==mkr.b1)
		{
			/*repaint();
			card.show(getContentPane(),"GWR");
			mkr.setVisible(false);*/
			String rname=mkr.tf.getText().trim();
			  if(rname.length()<1)
			  {
				  JOptionPane.showMessageDialog(this,"���̸��� �Է��ϼ���");
				  mkr.tf.requestFocus();
				  return;
			  }
			  //�ߺ�üũ 
			  String str="";
			  for(int i=0;i<wait.model1.getRowCount();i++)
			  {
				   str=wait.model1.getValueAt(i, 0).toString();
				   if(rname.equals(str))
				   {
					   JOptionPane.showMessageDialog(this, "�̹� �����ϴ� ���Դϴ�");
					   mkr.tf.setText("");
					   mkr.tf.requestFocus();
					   return;
				   }
			  }
			  String state="",pwd="";
			  if(mkr.rb1.isSelected())
			  {
				  state="����";
				  pwd=" ";
			  }
			  else
			  {
				  state="�����";
				  pwd=String.valueOf(mkr.pf.getPassword());//char[] ==> ���ڿ��� ��ȯ
				  //mr.pf.getText();
			  }
			  
			  int inwon=4;
			  
			  //������ ���� 
			  try
			  {
				  out.write((Function.MAKEROOM+"|"+rname+"|"
			                    +state+"|"+pwd+"|"+inwon+"\n").getBytes());
			  }catch(Exception ex){}
			  
			  mkr.setVisible(false);
			
		}//160211 �����߰�
		else if(e.getSource()==mkr.b2)
		{
			mkr.setVisible(false);
		
		
		
		} // 160211 �����߰�
		else if (e.getSource() == wait.b6) {
			System.exit(0);

		}

		

		// ################## GameWaitngRoom   // gwr 160217 �����߰�

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
			String r=cs.tfGuess[0].getText();
			String p=cs.tfGuess[1].getText();
			String w=cs.tfGuess[2].getText();
			
			int nextP=Game.crrPlayer;
			
			
			int hint=-1;
			while(hint>=0){
				hint=mainScreen.game.getHint(nextP,r,p,w);
				nextP++;
			}
			nextP--;
			String avata =mainScreen.game.p[nextP].id;
			//0 -> ��. 1 -> ���� 2->���� -1 ->����
			try
			{
				 out.write((Function.HINT+"|"+myRoom+"|"+avata+(nextP%4)+1+"|"+r+"|"+p+"|"+w+"\n").getBytes());
			}catch(Exception ex){}
			
			
			for(int i=0; i<3;i++){
				if(hint==i){
					//JOptionPane.showMessageDialog(getContentPane(), (Game.crrPlayer%4)+1+"P�� "+cs.tfGuess[i].getText()+"�� ������ �ֽ��ϴ�.");
					
					mainScreen.jpGameBoard.setMsgText(avata,nextP,i,cs.tfGuess[i].getText());
					mainScreen.ta.append("[�����Ը� �˸�]"+(Game.crrPlayer%4)+1+"P�� "+cs.tfGuess[i].getText()+"�� ������ �ֽ��ϴ�."+"\n");
					break;
				}
			}
			nextP=0;
			//ä��â
			
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
			 //������ ���� 
			 /*
			  *   ��� ã�´� ==> id (waitVc)
			  *   �濡 �ִ� ��� ==> roomName(userVc)
			  */
			 try
			 {
				 out.write((Function.ROOMCHAT+"|"+myRoom+"|"+msg+"\n").getBytes());
				 // Server ==> in.readLine() (Thread==> run())
				 /*
				  *   1. �̺�Ʈ �߻� (Button,Mouse)   
				  *      ==> ������ ��û�� ����
				  *      ******** ���������� Ŭ�� , �ּ� ���� 
				  *                 login.jsp?id=aaa&pwd=1234
				  *   2. Server (����� ����ϴ� �����忡�� ó��)
				  *      class Client
				  *      {
				  *          public void run(){}
				  *      }
				  *      ==> ��û�� ������� Ŭ���̾�Ʈ�� ���� 
				  *      ==> ������ ==> ó�� ����� Ŭ���̾�Ʈ �������� ����
				  *   3. run() : �������� ������ ������ �޾Ƽ� 
				  *       �����쿡 ��� (������ ���)
				  *       
				  *   Client ==> Server ==> Client
				  *   ����Ŭ 
				  *     ��û ==> ����� ==> ������ ����(���)
				  *     SQL  
				  *   ������
				  *     ��û (������) ==> ���Ͽ�û ==> 
				  *     ���� ã�� ==> ���ϳ��� ������ ����
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
		
		
		//########## ����/ CS ��ư
		
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
	
		
		//############## cs/ ����
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
		
		//########### ��
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
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(myNum==Game.crrPlayer){//���� ���� �÷��̾��϶�.
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
				
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void initStyle() // 160211 �����߰�
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

	public void append(String msg, String color) // 160211 �����߰�
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
					 if(sex.equals("����")) 
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
					 if(sex.equals("����")) 
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
						 gwr.isReady[0].setFont(new Font("���� ���", Font.ITALIC, 20));
						 gwr.isReady[0].setForeground(Color.PINK);
						 gwr.isReady[0].setText("����");
					 
				
					
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
					int pNum=Integer.parseInt(st.nextToken());//�÷��̾� �ѹ�
					boolean ready=Boolean.parseBoolean(st.nextToken());
					if(ready)
					gwr.isReady[pNum-1].setText("�غ�Ϸ�");//ĳ���� �ٲٱ�
				}
					break;
				
				case Function.AVATA:
					int pNum=Integer.parseInt(st.nextToken());//�÷��̾� �ѹ�
					int charNum=Integer.parseInt(st.nextToken());//ĳ���� �ѹ�
					int prvChar=Integer.parseInt(st.nextToken()); //����ĳ��
					
					chAvata(pNum-1,charNum);	//�����ٲٱ�
					gwr.avaName[pNum-1].setText(RefData.nameChar[charNum-1]);//ĳ���� �ٲٱ�
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
					card.show(getContentPane(), "LD"); // 160204 �����߰�
					new Thread(loading).start(); // 160204 �����߰�
					mainScreen.gameStart(); //game������ ȣ��
					mainScreen.game.setMyNum(pnum-1);
					this.myNum=pnum-1;
					mainScreen.game.setMyAva(ava);
					mainScreen.game.setAnswerCard(ans);
					mainScreen.game.setpCard(pCard);
					
					for(int i=1;i<=4;i++)
						mainScreen.game.setPlayer(i, gwr.avaName[i-1].getText().trim());
					
					mainScreen.jpMyCard.setCardImg(mainScreen.game.pCard[pnum-1]);//0���÷��̾��
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
					//�����͸� cs�� �Ѱ��༭ ó��.. ���� ��𿡼� �߸���,.
					for(int i=0;i<9;i++){
						cs.j[i].setEnabled(false);
					}
					repaint();
					
					cs.guess[0].removeAll();
					cs.guess[0].add(new JLabel(new ImageIcon(setImage("image/room/room"+(roomNo-1)+".jpg", cs.guess[0].getWidth(), cs.guess[0].getHeight()))));
					cs.guess[0].validate();//panel���ġ
					
					cs.pl.removeAll();
					cs.pl.add(new JLabel(new ImageIcon(setImage("image/player/char"+ (avata-1) + ".jpg", cs.pl.getWidth(), cs.pl.getHeight()))));
					cs.pl.validate();//panel���ġ
					
					cs.nPl.setText(RefData.nameChar[avata-1]+" �߸���");
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
						reachRoom.la1.setText(RefData.nameRoom[n-1]+"�� �����߽��ϴ�.");
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
						cs.guess[1].validate();//panel���ġ
						cs.tfGuess[1].setText(RefData.nameChar[cardnum]);
					}else if(cardnum>13){
						cardnum=cardnum-13;
						cs.guess[0].removeAll();
						cs.guess[0].add(new JLabel(new ImageIcon(setImage("image/room/room+"+cardnum+".jpg", cs.guess[2].getWidth(), cs.guess[2].getHeight()))));
						cs.guess[0].validate();//panel���ġ
						cs.tfGuess[0].setText(RefData.nameRoom[cardnum]);
					}else{
						cardnum=cardnum-6;
						cs.guess[2].removeAll();
						cs.guess[2].add(new JLabel(new ImageIcon(setImage("image/weapon/wp"+cardnum+".jpg", cs.guess[0].getWidth(), cs.guess[0].getHeight()))));
						cs.guess[2].validate();//panel���ġ
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
					int avata=Integer.parseInt(st.nextToken());
					int who = Integer.parseInt(st.nextToken());
					String r= st.nextToken();
					String p = st.nextToken();
					String w=  st.nextToken();
					
					mainScreen.jpGameBoard.setMsgText("�Źξ�",who,r,p,w);
					
					
					if(myNum==Game.crrPlayer){
						try{
									out.write((Function.FINISHTURN+"|"+myRoom+"\n").getBytes());
							}
							catch(Exception ex){
								
							}
					}
					Thread.sleep(4000);
					mainScreen.jpGameBoard.deleteMsg();
					
					
					
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
					 JOptionPane.showMessageDialog(this,"�̹� ���� á���ϴ�");
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
		gwr.aa[pNum].validate();//panel���ġ
	
		  
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

	










