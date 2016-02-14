package Clue;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.*;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Login extends JPanel {
	Image back;
	JLabel la1,la2;
	JTextField tf;
	JPasswordField pf;
	JButton b1,b2;
	// 저장 (초기화)
	/*
	 *   JPanel => 로딩
	 *             1) 생성자 호출
	 *             2) update() => clearRect()
	 *                paint(),paintComponent() ==> 자동 호출
	 *             ==> 화면 변경
	 */
	public Login() 
	{

		back=Toolkit.getDefaultToolkit().getImage("image/back/background.gif");
		
		la1=new JLabel("ID");
		la1.setForeground(Color.WHITE);
		la1.setFont(new Font("Arial", Font.BOLD, 14));
		la2=new JLabel("PW");
		la2.setForeground(Color.WHITE);
		la2.setFont(new Font("Arial", Font.BOLD, 14));
		tf=new JTextField();
		pf=new JPasswordField();
		b1=new JButton("로그인");
		b2=new JButton("회원가입");
		
		JPanel p=new JPanel(); // FlowLayout
		p.add(b1);
		p.add(b2);
		p.setBounds(520, 555, 150, 35);
		p.setOpaque(false);
		// 배치
		//setLayout(new GridLayout(3, 2 , 5 , 5)); //뒤쪽 5(좌우간격),5(위아래간격)
		setLayout(null); //사용자 지정
		la1.setBounds(490, 480, 30, 30);
		tf.setBounds(520, 480, 150, 30);
		la2.setBounds(490, 515, 30, 30);
		pf.setBounds(520, 515, 150, 30);
		// 추가
		add(la1); add(tf);
		add(la2); add(pf);
		add(p);
		
		File loginBGM=new File("wav/login_bgm.wav");
	}
	
	public static void music(File Sound)
	{
		
			try{
				Clip clip=AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(Sound));
				clip.start();
				
				Thread.sleep(clip.getMicrosecondLength()/1000);
			}catch(Exception ex) {}
	}
	
	@Override
	//paint, paintComponent => 자동호출
	protected void paintComponent(Graphics g) {
		g.drawImage(back, 0, 0, getWidth(),getHeight(),this);
	}

	
}
