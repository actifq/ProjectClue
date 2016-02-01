package sujin;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
public class Board extends JPanel {
	Image back;
	JTable table1,table2,table3,table4,table5;
	JTextArea ta;
	JTextField tf;
	JButton b;
	
	public Board() {
		back=Toolkit.getDefaultToolkit().getImage("c:\\image\\back.jpg");
		JScrollPane js1=new JScrollPane(table1);
		JScrollPane js2=new JScrollPane(table2);
		JScrollPane js3=new JScrollPane(table3);
		JScrollPane js4=new JScrollPane(table4);
		JScrollPane js5=new JScrollPane(table5);
		
		//채팅
		ta=new JTextArea();
		JScrollPane js6=new JScrollPane(ta);
		tf=new JTextField();
		b=new JButton("입력");
		
		setLayout(null);
		js1.setBounds(5, 5, 940, 90);
		js2.setBounds(955, 5, 230, 90);
		js3.setBounds(5, 105, 940, 640);
		js4.setBounds(955, 105, 230, 640);
		js5.setBounds(955, 755, 230, 110);
		js6.setBounds(5, 755, 940, 80);
		tf.setBounds(5, 840, 845, 25);
		b.setBounds(860, 840, 85, 25);
		
		add(js1);
		add(js2);
		add(js3);
		add(js4);
		add(js5);
		add(js6);
		add(tf);
		add(b);
	}
	@Override
	protected void paintComponent(Graphics g) {  
		g.drawImage(back, 0, 0, getWidth(),getHeight(),this);
	}
}
