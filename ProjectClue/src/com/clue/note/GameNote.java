package com.clue.note;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.html.HTML;

public class GameNote extends JPanel{
	
	JPanel jpNote1,jpNote2,jpNote3;
	JTextField nt1,nt2,nt3,nt4,nt5,nt6,
				nt7,nt8,nt9,nt10,nt11,nt12;
	JCheckBox sus1,sus2,sus3,sus4,sus5,sus6;
	
	
	public GameNote() {
			
			JLabel lb1 = new JLabel(" ������");
			JLabel lb2 = new JLabel(" ");
			lb1.setFont(new Font("", Font.BOLD, 13));
			sus1=new JCheckBox("���¹�");
			sus2=new JCheckBox("�Źξ�");
			sus3=new JCheckBox("���޼�");
			sus4=new JCheckBox("������");
			sus5=new JCheckBox("���ر�");
			sus6=new JCheckBox("������");
			nt1=new JTextField();
			nt2=new JTextField();
			nt3=new JTextField();
			nt4=new JTextField();
			nt5=new JTextField();
			nt6=new JTextField();
			
			
			setLayout(new GridLayout(8,0,0,2));
			
			add(lb1); add(lb2);
			add(sus1); add(nt1);
			add(sus2); add(nt2);
			add(sus3); add(nt3);
			add(sus4); add(nt4);
			add(sus5); add(nt5);
			add(sus6); add(nt6);
			
	}


}