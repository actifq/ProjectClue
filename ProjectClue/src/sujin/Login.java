package sujin;

import javax.swing.*;
import java.awt.*;
public class Login extends JPanel{
	Image back;
	JLabel la1,la2;
	JTextField tf;
	JPasswordField pf;
	JButton b1,b2;
	// 저장 (초기화)
	 
	public Login() 
	{
		back=Toolkit.getDefaultToolkit().getImage("c:\\image\\back.jpg");
		la1=new JLabel("ID");
		la2=new JLabel("PW");
		tf=new JTextField();
		pf=new JPasswordField();
		b1=new JButton("로그인");
		b2=new JButton("취소");
		
		JPanel p=new JPanel(); // FlowLayout
		p.add(b1);
		p.add(b2);
		p.setBounds(530, 430, 185, 35);
		p.setOpaque(false);  
		
		// 배치
		setLayout(null); //사용자 지정
		la1.setBounds(500, 355, 30, 30);
		tf.setBounds(545, 355, 150, 30);
		la2.setBounds(500, 400, 30, 30);
		pf.setBounds(545, 400, 150, 30);
		
		// 추가
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
