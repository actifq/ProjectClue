package Clue;


import javax.swing.*;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.net.MalformedURLException;

/*import javax.sound.sampled.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;*/

public class Login extends JPanel implements ActionListener{
	Image back;
	JLabel la1,la2;
	JTextField tf;
	//JPasswordField pf;
	JTextField tf2;
	JButton b1,b2;
	AudioClip clip;
	
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
		la2=new JLabel("닉네임");
		la2.setForeground(Color.WHITE);
		la2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		tf=new JTextField();
		//pf=new JPasswordField();
		tf2=new JTextField();
		b1=new JButton("로그인");
		b2=new JButton("회원가입");
		
		JPanel p=new JPanel(); // FlowLayout
		p.add(b1);
		p.add(b2);
		p.setBounds(530, 555, 150, 35);
		p.setOpaque(false);
		// 배치
		//setLayout(new GridLayout(3, 2 , 5 , 5)); //뒤쪽 5(좌우간격),5(위아래간격)
		setLayout(null); //사용자 지정

		la1.setBounds(460, 480, 60, 30);
		tf.setBounds(520, 480, 150, 30);
		la2.setBounds(464, 480, 100,100);
		//pf.setBounds(520, 515, 150, 30);
		tf2.setBounds(520, 515, 150, 30);

		// 추가
		add(la1); add(tf);
		add(la2); add(tf2);
		add(p);
		
		b1.addActionListener(this);
		
		
		 try {
	            File file = new File("wav/login_bgm.wav");
	            clip = Applet.newAudioClip(file.toURL());
	            clip.play();
	            
	           
	        } catch (MalformedURLException e){
	            e.printStackTrace();
	        }
	}
	
	

	
	@Override
	//paint, paintComponent => 자동호출
	protected void paintComponent(Graphics g) {
		g.drawImage(back, 0, 0, getWidth(),getHeight(),this);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
