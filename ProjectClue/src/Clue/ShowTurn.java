package Clue;



import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;



public class ShowTurn extends JFrame {
	JLabel la1;
	JLabel label ;
	JLabel label2 ;
	GameArea gs;
	public ShowTurn(String id, int dice, GameArea gs){
		this.gs=gs;
		setSize(300,300);
		
		label = new JLabel(id+"��! ");
		label2 = new JLabel("�̵�Ƚ��: "+dice);
		
		setLayout(null);
		
		label.setBounds(150,50,80,20);
		label2.setBounds(80,150,100,40);
		
		add(label);
		add(label2);	
	
		
	
		
		
	}
	
	/*public void setLabel(String id, int dice){
		label.setText(id+"��! ");
		label2.setText("�̵�Ƚ��: "+dice);
		
	}*/
	
	
}
