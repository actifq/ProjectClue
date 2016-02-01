package Clue;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class GameWaitingRoom extends JPanel {
	Image back3;
	JPanel title,user1,user2,user3,user4,chr1,chr2;
	JTextArea chat;
	JTextField chatInput;
	JComboBox box;
	JButton btnReady,btnExit;
	
	public GameWaitingRoom(){
		back3=Toolkit.getDefaultToolkit().getImage("/image/boardBack.png");
		//game user
		title=new JPanel();
		title.setBackground(Color.darkGray);
		user1=new GWR_userPanel();
		user1.setSize(450,300);
		user2=new JPanel();
		user2.setBackground(Color.gray);
		user3=new JPanel();
		user3.setBackground(Color.gray);
		user4=new JPanel();
		user4.setBackground(Color.gray);
		
		//choose character
		chr1=new JPanel();
		chr1.setBackground(Color.white);
		chr2=new JPanel();
		chr2.setBackground(Color.lightGray);
		
		//chat
		chat=new JTextArea();
		JScrollPane js1=new JScrollPane(chat);
		chatInput=new JTextField();
		box=new JComboBox();
		box.addItem("black");
		box.addItem("blue");
		box.addItem("pink");
		box.addItem("green");
		box.addItem("magenta");
		
		//exit, ready
		btnReady=new JButton("READY");
		btnExit=new JButton("EXIT");
		
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(2,1,5,5));
		p.add(btnReady);
		p.add(btnExit);
		p.setOpaque(false);
		
		setLayout(null);
		title.setBounds(5, 5, 1185, 35);
		user1.setBounds(5, 45, 450, 300);
		user2.setBounds(457, 45, 449, 300);
		user3.setBounds(5, 347, 450, 300);
		user4.setBounds(457, 347, 449, 300);
		js1.setBounds(5, 652, 902, 180);
		chatInput.setBounds(5, 837, 795, 30);
		box.setBounds(805,837,102,30);
		chr1.setBounds(912, 75, 278, 572);
		chr2.setBounds(912, 45, 278, 30);
		p.setBounds(912, 652, 278, 215);		
		
		add(title);
		add(user1);
		add(user2);
		add(user3);
		add(user4);
		add(js1);
		add(chatInput);
		add(box);
		add(chr1);
		add(chr2);
		add(p);
		
	}
	@Override
	//paint, paintComponent => 자동호출
	protected void paintComponent(Graphics g) {
		g.drawImage(back3, 0, 0, getWidth(),getHeight(),this);
}
}
