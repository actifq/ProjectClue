package sujin;

import javax.swing.*;
import java.awt.*;
public class Login extends JPanel{
	Image back;
	JLabel la1,la2;
	JTextField tf;
	JPasswordField pf;
	JButton b1,b2;
	// ���� (�ʱ�ȭ)
	 
	public Login() 
	{
		back=Toolkit.getDefaultToolkit().getImage("c:\\image\\back.jpg");
		la1=new JLabel("ID");
		la2=new JLabel("PW");
		tf=new JTextField();
		pf=new JPasswordField();
		b1=new JButton("�α���");
		b2=new JButton("���");
		
		JPanel p=new JPanel(); // FlowLayout
		p.add(b1);
		p.add(b2);
		p.setBounds(530, 430, 185, 35);
		p.setOpaque(false);  
		
		// ��ġ
		setLayout(null); //����� ����
		la1.setBounds(500, 355, 30, 30);
		tf.setBounds(545, 355, 150, 30);
		la2.setBounds(500, 400, 30, 30);
		pf.setBounds(545, 400, 150, 30);
		
		// �߰�
		add(la1); add(tf);
		add(la2); add(pf);
		//add(b1); add(b2);
		add(p);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(back, 0, 0, getWidth(),getHeight(),this);
	}
	
}
