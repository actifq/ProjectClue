package Clue;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Join_Login extends JFrame{
	JLabel la1,la2,la3,la4,la5,la6;
	JRadioButton rb1,rb2;
	JTextField tf;
	JPasswordField pf;
	JComboBox box;
	JButton b1,b2;
	
	public Join_Login() 
	{
		la1=new JLabel("���̵�");
		la2=new JLabel("��й�ȣ");
		la3=new JLabel("�̸�");
		la4=new JLabel("�ּ�");
		la5=new JLabel("�̸���");
		la6=new JLabel("�ڵ�����ȣ");
		
		
		tf=new JTextField();
		pf=new JPasswordField();
		rb1=new JRadioButton("����");
		rb2=new JRadioButton("����");
		
		b1=new JButton("Ȯ��");
		b2=new JButton("���");
		
		//��ġ
		setLayout(null);
		la1.setBounds(10,15,40,30);
		tf.setBounds(55, 15, 150, 30);
		
		la2.setBounds(10,50,40,30);
		pf.setBounds(55,50,70,30);
		//rb1.setBounds(55, 50, 70, 30);
		//rb2.setBounds(130, 50, 70, 30);
		
		la3.setBounds(25,85,60,30);
		pf.setBounds(90, 85, 100, 30);
		
		la4.setBounds(10,120,40,30);
		box.setBounds(90, 120, 100, 30);
		
		JPanel p=new JPanel();
		p.add(b1);
		p.add(b2);
		
		p.setBounds(10,155,195,35);
		
		//�߰�
		add(la1);add(tf);
		add(la2);add(rb1);add(rb2);
		add(la3);add(pf);
		add(la4);add(box);
		add(p);
		
		
		
		
	}
}
