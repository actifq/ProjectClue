package Clue;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

import com.sist.common.Function;

import java.awt.event.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClueMain extends JFrame implements ActionListener, KeyListener, Runnable {

	CardLayout card;
	GameWaitingRoom gwr = new GameWaitingRoom();
	Login login = new Login();
	GameMainScreen mainScreen = new GameMainScreen();
	CardSelect cs = new CardSelect();
	LoadingTest loading= new LoadingTest(this); //160204 정선 추가
	ReachRoom reachRoom =new ReachRoom();
	WaitRoom wait=new WaitRoom(); //160211 정선추가
	//private Dice dice;//160206 정선 추가
	Join_Login join=new Join_Login();//160211 정선 추가
	WR_MakeRoom mkr=new WR_MakeRoom(); //160211 정선 추가
	FinalCardSelect fcs=new FinalCardSelect(); //160216 정선 추가

	// 소켓 연결시도

	Socket s;
	BufferedReader in; // 서버에서 값을 읽는다
	OutputStream out; // 서버로 요청값을 보낸다.

	public ClueMain() {

		card = new CardLayout();
		setLayout(card);

		add("LOG",login);
		add("WR",wait);
		add("GWR",gwr);
		add("MS",mainScreen);
		add("LD",loading); //160204정선추가
		add("CS",cs);
		add("FCS",fcs);
		
		setSize(1200,900);
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
		gwr.chatInput.addActionListener(this);
		gwr.btnReady.addActionListener(this);
		gwr.btnExit.addActionListener(this);
		gwr.chr1.addActionListener(this);
		gwr.chr2.addActionListener(this);
		gwr.chr3.addActionListener(this);
		gwr.chr4.addActionListener(this);
		gwr.chr5.addActionListener(this);
		gwr.chr6.addActionListener(this);

		mainScreen.b.addActionListener(this);
		cs.st.addActionListener(this);
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
			
		} // 160211 정선 추가

		// ################## WaitRoom
		else if (e.getSource() == wait.b1) {
			mkr.setVisible(true);
		} // 160211 정선추가
		else if (e.getSource() == wait.b2) {
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
		} // 160211 정선추가
		else if (e.getSource() == wait.b6) {
			System.exit(0);
		}
		else if (e.getSource() == mkr.b1) {
			repaint();
			card.show(getContentPane(), "GWR");
			mkr.setVisible(false);
		} // 160211 정선추가

		// ################## GameWaitngRoom   // gwr 160217 찬재추가

		else if (e.getSource() == gwr.chatInput) {
			String data = gwr.chatInput.getText();
			gwr.chat.append(data + "\n");
			gwr.chatInput.setText("");
		} else if (e.getSource() == gwr.btnReady) {
			repaint();

			card.show(getContentPane(), "LD"); // 160204 정선추가
			new Thread(loading).start(); // 160204 정선추가

		} else if (e.getSource() == gwr.btnExit) {
			repaint();
			card.previous(getContentPane());
		}

		else if (e.getSource() == gwr.chatInput) {
			String data = gwr.chatInput.getText();
			gwr.chat.append(data + "\n");
			gwr.chatInput.setText("");
		} else if (e.getSource() == gwr.chr1) {
			// gwr.aa1.getBackground();
			gwr.aa1.getGraphics().drawImage(gwr.p1, 24, 5, 171, 250, this);
		} else if (e.getSource() == gwr.chr2) {
			gwr.aa1.getGraphics().drawImage(gwr.p2, 24, 5, 171, 250, this);
		} else if (e.getSource() == gwr.chr3) {
			// gwr.aa1.repaint();
			gwr.aa1.getGraphics().drawImage(gwr.p3, 24, 5, 171, 250, this);
		} else if (e.getSource() == gwr.chr4) {
			// gwr.aa1.repaint();
			gwr.aa1.getGraphics().drawImage(gwr.p4, 24, 5, 171, 250, this);
		} else if (e.getSource() == gwr.chr5) {
			// gwr.aa1.repaint();
			gwr.aa1.getGraphics().drawImage(gwr.p5, 24, 5, 171, 250, this);
		} else if (e.getSource() == gwr.chr6) {
			// gwr.aa1.repaint();
			gwr.aa1.getGraphics().drawImage(gwr.p6, 24, 5, 171, 250, this);

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
		else if(e.getSource()==reachRoom.b2)
		{
			repaint();
			card.show(getContentPane(), "FCS");
			fcs.setCardImg();
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
		int n = mainScreen.game.process();
		if (n != 0) {
			reachRoom.setBounds(500, 250, 230, 240);
			reachRoom.la1.setText(n + "번방에 도달했습니다.");
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
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
