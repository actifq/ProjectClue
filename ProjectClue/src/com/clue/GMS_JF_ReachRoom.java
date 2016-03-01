package com.clue;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GMS_JF_ReachRoom extends JFrame {
	JLabel la1;
	JButton b1,b2;
	PanelRR jp;
	public GMS_JF_ReachRoom(){
		
		jp = new PanelRR();
		la1 = new JLabel("  사랑방에 도달했습니다");
		setLayout(null);
		
		la1.setBounds(30,60,230,40);
		la1.setForeground(Color.pink);
		la1.setBackground(Color.DARK_GRAY);
		la1.setOpaque(true);
		la1.setFont(new Font("맑은 고딕", Font.ITALIC,20 ));
		b1=new JButton("중간추리");
		b2=new JButton("최종추리");
		
		
		
		jp.setBounds(0, 0, 300, 300);
		b1.setBounds(40,200,100,40);
		b2.setBounds(150,200,100,40);
		add(jp);
		
		jp.add(b1);
		jp.add(b2);
		jp.add(la1);
		
		
		
		setSize(300,300);
	}
	
	
	
	class PanelRR extends JPanel{
		Image reach;
		JLabel la1 ;
		
		public JButton b2,b3;
		public PanelRR(){
			
			
			
			reach=Toolkit.getDefaultToolkit().getImage("image/back/corr.jpg");
			setLayout(null);
			
		
		}
		@Override
		protected void paintComponent(Graphics g) {  
			
			g.drawImage(reach, 0, 0, getWidth(),getHeight(),this);
		}
	}
	

}
