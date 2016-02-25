package Clue;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class ShowTurn extends JFrame {
	
	//Container ct;
	Image turn;
	JLabel la1;
	JLabel label ;
	JLabel label2 ;
	JPanel tu,re;

	
	public ShowTurn(){
		
		PUImg tu= new PUImg();
		
		setSize(300,300);
		
		add(tu);
		
	}
	
		/*public static void main(String[] args){
		new ShowTurn().setVisible(true);
	}*/
/*public void setLabel(String id, int dice){
		label.setText(id+"ÅÏ! ");
		label2.setText("ÀÌµ¿È½¼ö: "+dice);
		
	}*/
	
	
}
