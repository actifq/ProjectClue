package Clue;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import Clue.GameMainScreen;
import Clue.Dice;

import java.awt.event.*;


public class ClueMain extends JFrame implements ActionListener{
	CardLayout card;
	GameWaitingRoom gwr=new GameWaitingRoom();
	Login login=new Login();
	GameMainScreen mainScreen=new GameMainScreen();
	LoadingTest loading= new LoadingTest(this); //160204 정선 추가
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
		add("LD",loading); //160204정선추가

		add("MS",mainScreen);
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
		String path = ClueMain.class.getResource("").getPath();
		System.out.println(path);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==login.b1)
		{
			repaint();
			card.show(getContentPane(),"WR");
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

