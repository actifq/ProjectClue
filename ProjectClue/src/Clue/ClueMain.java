package Clue;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;


public class ClueMain extends JFrame implements ActionListener{
	CardLayout card;
	GameWaitingRoom gwr=new GameWaitingRoom();
	Login login=new Login();
	GameMainScreen mainScreen=new GameMainScreen();

	LoadingTest loading;
	
	
	
	public ClueMain()
	{	
		loading=new LoadingTest(this);
		card=new CardLayout();
		setLayout(card);
		
		add("MS",mainScreen);
		add("LOG",login);
		add("GWR",gwr);
		add("LS",loading);
		
		
		setSize(1200,900);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		login.b1.addActionListener(this);
		gwr.chatInput.addActionListener(this);
		gwr.btnReady.addActionListener(this);
		gwr.btnExit.addActionListener(this);
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
			card.show(getContentPane(),"GWR");
		}
		else if(e.getSource()==gwr.chatInput)
		{
			String data= gwr.chatInput.getText();
			gwr.chat.append(data+"\n");
			gwr.chatInput.setText("");
		}
		else if(e.getSource()==gwr.btnReady)
		{	
			repaint();
			
			card.show(getContentPane(), "LS");
			new Thread(loading).start();
		}else if(e.getSource()==gwr.btnExit){
			repaint();
			card.previous(getContentPane());
		}
	}
	

}

