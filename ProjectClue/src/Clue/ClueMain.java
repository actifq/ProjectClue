package Clue;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class ClueMain extends JFrame implements ActionListener,KeyListener{
	
	CardLayout card;
	GameWaitingRoom gwr=new GameWaitingRoom();
	Login login=new Login();
	GameMainScreen mainScreen=new GameMainScreen();
	CardSelect cs = new CardSelect();
	LoadingTest loading= new LoadingTest(this); //160204 정선 추가
	ReachRoom reachRoom =new ReachRoom();
	WaitRoom wait=new WaitRoom(); //160211 정선추가
	private Dice dice;//160206 정선 추가
	Join_Login join=new Join_Login();//160211 정선 추가
	WR_MakeRoom mkr=new WR_MakeRoom(); //160211 정선 추가
	


	
	

	
	public ClueMain()
	{	
		dice=new Dice();//정선 추가 150207
		card=new CardLayout();
		setLayout(card);

		add("LOG",login);
		add("WR",wait);
		add("GWR",gwr);
		add("MS",mainScreen);
		add("LD",loading); //160204정선추가
		add("CS",cs);
		
		setSize(1200,900);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		login.b1.addActionListener(this);
		login.b2.addActionListener(this);//160211 정선추가
		wait.b1.addActionListener(this);//160211 정선추가
		wait.b2.addActionListener(this);//160211 정선추가
		wait.tf.addActionListener(this);//160211 정선추가
		mkr.b1.addActionListener(this);// 160211 정선추가
		gwr.chatInput.addActionListener(this);
		gwr.btnReady.addActionListener(this);
		gwr.btnExit.addActionListener(this);
		mainScreen.b.addActionListener(this);
		cs.st.addActionListener(this);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - getWidth())/2,(screenSize.height - getHeight())/2,getWidth(),getHeight());
		
		addKeyListener(this);
		setFocusable(true);
		reachRoom.b1.addActionListener(this);
		reachRoom.b2.addActionListener(this);
		
		

	}
	
	public Dice dice() //정선 추가 160206
	{
		return dice;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		}catch(Exception ex){}
		ClueMain mn=new ClueMain();

	}
	
	@Override

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==login.b1)
		{
			repaint();
			card.show(getContentPane(),"WR");
			wait.tf.requestFocus();
		}
		else if(e.getSource()==login.b2)
		{
			join.setVisible(true);
			
		} // 160211 정선 추가
		else if(e.getSource()==wait.b1)
		{
			mkr.setVisible(true);
		}//160211 정선추가
		else if(e.getSource()==wait.b2)
		{
			repaint();
			card.show(getContentPane(),"GWR");
		} // 160211 정선 추가
		else if (e.getSource()==wait.tf)
		{
			String msg=wait.tf.getText().trim();
			if(msg.length()<1) return;
			String color=wait.box.getSelectedItem().toString();
			initStyle();
			append(msg,color);
			wait.tf.setText("");
		}//160211 정선추가
		
		else if(e.getSource()==mkr.b1)
		{
			repaint();
			card.show(getContentPane(),"GWR");
			mkr.setVisible(false);
		}//160211 정선추가
		else if(e.getSource()==gwr.chatInput)
		{
			String data= gwr.chatInput.getText();
			gwr.chat.append(data+"\n");
			gwr.chatInput.setText("");
		}
		else if(e.getSource()==gwr.btnReady)
		{	
			repaint();
			card.show(getContentPane(), "LD"); //160204 정선추가
			new Thread(loading).start(); //160204 정선추가
			
		}else if(e.getSource()==gwr.btnExit){
			repaint();
			card.previous(getContentPane());
		}
		
		else if(e.getSource()==gwr.chatInput)
		{
			String data= gwr.chatInput.getText();
			gwr.chat.append(data+"\n");
			gwr.chatInput.setText("");
		
				
		}else if(e.getSource()==cs.st){
			repaint();
			card.previous(getContentPane());
			card.show(getContentPane(), "MS");
			
			//수정필요
			mainScreen.game.savePlayerStatus();
			mainScreen.game.setGamePlayer(Game.crrPlayer,mainScreen.game.runDice());
			
			mainScreen.showCount();
			mainScreen.jpGameBoard.repaint();
			//여기까지
			//mainScreen.mc.show(getParent(), "GB");
		}else if(e.getSource()==reachRoom.b1){
			repaint();
			card.show(getContentPane(), "CS");
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
			reachRoom.la1.setText(n+"번방에 도달했습니다.");
			reachRoom.setVisible(true);
		}
		mainScreen.showCount();
		mainScreen.jpGameBoard.repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void initStyle() // 160211 정선추가
	{
		Style def=StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		Style blue=wait.ta.addStyle("blue", def);
		StyleConstants.setForeground(blue, Color.blue);
		
		
		Style pink=wait.ta.addStyle("pink", def);
		StyleConstants.setForeground(pink, Color.pink);
		
		Style green=wait.ta.addStyle("green", def);
		StyleConstants.setForeground(green, Color.green);
		
		Style cyan=wait.ta.addStyle("cyan", def);
		StyleConstants.setForeground(cyan, Color.cyan);
		
		
	}
	public void append(String msg,String color) // 160211 정선추가
	{
		try
		{
			Document doc=wait.ta.getDocument();
			doc.insertString(doc.getLength(), msg+"\n", wait.ta.getStyle(color));
		}catch(Exception e){}
	}
	

}

