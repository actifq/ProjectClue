package Clue;



import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ReachRoom extends JFrame {
	JLabel la1;
	JButton b1,b2;
	PUImg2 jp;
	public ReachRoom(){
		
		jp = new PUImg2();
		la1 = new JLabel("  ����濡 �����߽��ϴ�");
		setLayout(null);
		
		la1.setBounds(30,60,230,40);
		la1.setForeground(Color.pink);
		la1.setBackground(Color.DARK_GRAY);
		la1.setOpaque(true);
		la1.setFont(new Font("���� ���", Font.ITALIC,20 ));
		b1=new JButton("�߰��߸�");
		b2=new JButton("�����߸�");
		
		
		
		jp.setBounds(0, 0, 300, 300);
		b1.setBounds(40,200,100,40);
		b2.setBounds(150,200,100,40);
		add(jp);
		
		jp.add(b1);
		jp.add(b2);
		jp.add(la1);
		
		
		
		setSize(300,300);
	}
	

	

}
