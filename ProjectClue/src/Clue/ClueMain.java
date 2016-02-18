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
	
	MusicManager mm=new MusicManager();//160214 ���� �߰�
	
	
	
	
	 // ���� ����õ�
	 

	Socket s;

	BufferedReader in;	//�������� ���� �д´�
	OutputStream out;	//������ ��û���� ������.
	
	 
    String myRoom,myId;


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
		gwr.chr1.addActionListener(this);
		gwr.chr2.addActionListener(this);
		gwr.chr3.addActionListener(this);
		gwr.chr4.addActionListener(this);
		gwr.chr5.addActionListener(this);
		gwr.chr6.addActionListener(this);
		
		mainScreen.b.addActionListener(this);	//ä���Է�
		cs.st.addActionListener(this);	//�߸�-ī�弱��
		
		

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2, getWidth(), getHeight());

		addKeyListener(this);
		setFocusable(true);
		reachRoom.b1.addActionListener(this);
		reachRoom.b2.addActionListener(this);

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
			  
			  int inwon=mkr.box.getSelectedIndex()+2;
			  
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
			repaint();

			card.show(getContentPane(), "LD"); // 160204 �����߰�
			new Thread(loading).start(); // 160204 �����߰�

		} else if (e.getSource() == gwr.btnExit) {
			repaint();
			card.previous(getContentPane());
		}

		else if (e.getSource() == gwr.chatInput) {
			String data = gwr.chatInput.getText();
			gwr.chat.append(data + "\n");
			gwr.chatInput.setText("");
		} else if (e.getSource() == gwr.chr1) {
			chAvata(0,0);
			
			//gwr.aa1.getGraphics().drawImage(gwr.p1, 24, 5, 171, 250, this);
		} else if (e.getSource() == gwr.chr2) {
			//gwr.aa1.getGraphics().drawImage(gwr.p2, 24, 5, 171, 250, this);
		} else if (e.getSource() == gwr.chr3) {
			// gwr.aa1.repaint();
			//gwr.aa1.getGraphics().drawImage(gwr.p3, 24, 5, 171, 250, this);
		} else if (e.getSource() == gwr.chr4) {
			// gwr.aa1.repaint();
			//gwr.aa1.getGraphics().drawImage(gwr.p4, 24, 5, 171, 250, this);
		} else if (e.getSource() == gwr.chr5) {
			// gwr.aa1.repaint();
			//gwr.aa1.getGraphics().drawImage(gwr.p5, 24, 5, 171, 250, this);
		} else if (e.getSource() == gwr.chr6) {
			// gwr.aa1.repaint();
			//gwr.aa1.getGraphics().drawImage(gwr.p6, 24, 5, 171, 250, this);

			// ################## CardSelect

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
			reachRoom.la1.setText(RefData.nameRoom[n-1]+"�� �����߽��ϴ�.");}
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
					 String avata=st.nextToken();
					 
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
					String[] temp={id,sex,avata};
					
				}
				break;
				case Function.ROOMIN:
				{
					 String id=st.nextToken();
					 String sex=st.nextToken();
					 String avata=st.nextToken();
					 myRoom=st.nextToken();
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
					 JOptionPane.showMessageDialog(this,"�̹� ���� á���ϴ�");
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
		gwr.imgP[pNum].removeAll();
		  gwr.imgP[pNum].setLayout(new BorderLayout());
		  gwr.imgP[i].add("Center",
				  new JLabel(new ImageIcon("image/player/char"+0+".jpg")));
		  gwr.imgP[i].validate();//panel���ġ
	}


}
