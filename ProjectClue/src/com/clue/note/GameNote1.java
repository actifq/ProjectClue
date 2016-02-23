package com.clue.note;

import java.awt.*;
import javax.swing.*;

public class GameNote1 extends JPanel{
	JPanel jpNote1,jpNote2,jpNote3;
	JTextField nt1,nt2,nt3,nt4,nt5,nt6,
				nt7,nt8,nt9,nt10,nt11,nt12,nt13,nt14,
				nt15,nt16,nt17,nt18,nt19,nt20,nt21,nt22,nt23;
	JCheckBox pl1,pl2,pl3,pl4,pl5,pl6,pl7,pl8,pl9;
	
	public GameNote1() {
		
		JLabel lb5 = new JLabel(" Àå¼Ò");
		JLabel lb6 = new JLabel(" ");
		lb5.setFont(new Font("", Font.BOLD, 13));
		pl1=new JCheckBox("µÞ°£");
		pl2=new JCheckBox("Çê°£");
		pl3=new JCheckBox("¾ÈÃ¤");
		pl4=new JCheckBox("ÅÔ¹ç");
		pl5=new JCheckBox("ºÎ¾ý");
		pl6=new JCheckBox("°÷°£");
		pl7=new JCheckBox("¼­Àç");
		pl8=new JCheckBox("»ç¶û¹æ");
		pl9=new JCheckBox("Åò¸¶·ç");
		nt15=new JTextField();
		nt16=new JTextField();
		nt17=new JTextField();
		nt18=new JTextField();
		nt19=new JTextField();
		nt20=new JTextField();
		nt21=new JTextField();
		nt22=new JTextField();
		nt23=new JTextField();
		
		setLayout(new GridLayout(11,0,0,2));
		
		add(lb5); add(lb6);
		add(pl1); add(nt15);
		add(pl2); add(nt16);
		add(pl3); add(nt17);
		add(pl4); add(nt18);
		add(pl5); add(nt19);
		add(pl6); add(nt20);
		add(pl7); add(nt21);
		add(pl8); add(nt22);
		add(pl9); add(nt23);
		
	}
}
