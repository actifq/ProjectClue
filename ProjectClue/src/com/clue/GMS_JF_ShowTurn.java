package com.clue;



import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import java.awt.*;


public class GMS_JF_ShowTurn extends JFrame {

	PanelST img;
	
	public JButton b1;
	public GMS_JF_ShowTurn(){
		
		setSize(300,300);
		img = new PanelST();
		b1= new JButton("주사위 돌리기");
		setLayout(null);
	
	
		
		img.setBounds(0,0,300,300);
	
		b1.setBounds(40,150,120,40);
		
	
		add(img);
		img.add(b1);	
	
		
	
		
		
	}
	
		
	public static void main(String[] args){
		new GMS_JF_ShowTurn().setVisible(true);
	}/*
public void setLabel(String id, int dice){
		label.setText(id+"턴! ");
		label2.setText("이동횟수: "+dice);
		
	}*/
	
	class PanelST extends JPanel{

		Image turn;
		//JLabel label ;
		
		public JButton b1;
		public PanelST(){
			
			//ShowTurn
			//label = new JLabel("MY TURN ");
			//b1= new JButton("주사위 돌리기");
			turn=Toolkit.getDefaultToolkit().getImage("image/back/blcat.jpg");
			
			setLayout(null);
			//label.setBounds(150,50,80,20);
		//	b1.setBounds(40,150,120,40);
			
			//add(label);
			//add(b1);
			
		
		}
		@Override
		protected void paintComponent(Graphics g) {  
			
			g.drawImage(turn, 0, 0, getWidth(),getHeight(),this);
		}
	}
	
}
