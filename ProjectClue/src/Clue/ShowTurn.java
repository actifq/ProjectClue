package Clue;



import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;



public class ShowTurn extends JFrame {
	JLabel la1;
	JLabel label ;
	JLabel label2 ;

	public JButton b1;
	public ShowTurn(){
		
		setSize(300,300);
		
		label = new JLabel("MY TURN ");
		b1= new JButton("�ֻ��� ������");
		
		setLayout(null);
		
		label.setBounds(150,50,80,20);
		b1.setBounds(80,150,120,40);
		
		add(label);
		add(b1);	
	
		
	
		
		
	}
	
	/*public void setLabel(String id, int dice){
		label.setText(id+"��! ");
		label2.setText("�̵�Ƚ��: "+dice);
		
	}*/
	
	
}
