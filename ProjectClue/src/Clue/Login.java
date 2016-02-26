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
	JLabel la1,la2,la3;
	JTextField tf,tf2;
	JButton b1,b2;
	JRadioButton man,woman;
	AudioClip clip;
	
	// ���� (�ʱ�ȭ)
	/*
	 *   JPanel => �ε�
	 *             1) ������ ȣ��
	 *             2) update() => clearRect()
	 *                paint(),paintComponent() ==> �ڵ� ȣ��
	 *             ==> ȭ�� ����
	 */
	public Login() 
	{

		back=Toolkit.getDefaultToolkit().getImage("image/back/background.gif");
		
		la1=new JLabel("ID");
		la1.setForeground(Color.WHITE);
		la1.setFont(new Font("Arial", Font.BOLD, 14));
		la2=new JLabel("�г���");
		la2.setForeground(Color.WHITE);
		la2.setFont(new Font("���� ���l", Font.BOLD, 14));
		
		la3=new JLabel("����");
		la3.setForeground(Color.WHITE);
		la3.setFont(new Font("���� ���l", Font.BOLD, 14));
		
		man=new JRadioButton("����");
		woman=new JRadioButton("����");
		
		man.setFont(new Font("���� ���",Font.BOLD,14));
		woman.setFont(new Font("���� ���",Font.BOLD,14));
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(man);
		bg.add(woman);
		
		tf=new JTextField();
		tf2=new JTextField();
		b1=new JButton("�α���");
		b2=new JButton("ȸ������");
		
		JPanel p=new JPanel(); // FlowLayout
		p.add(b1);
		//p.add(b2);
		p.setBounds(520, 600, 150, 35);
		p.setOpaque(false);
		// ��ġ
		//setLayout(new GridLayout(3, 2 , 5 , 5)); //���� 5(�¿찣��),5(���Ʒ�����)
		setLayout(null); //����� ����

		la1.setBounds(460, 480, 60, 30);
		tf.setBounds(520, 480, 150, 30);
		la2.setBounds(464, 480, 100,100);
		tf2.setBounds(520, 515, 150, 30);
		
		la3.setBounds(478, 550, 40, 30);
		
		man.setBounds(540, 550, 70, 30);
		woman.setBounds(595, 550, 70, 30);
		
		man.setOpaque(false);
		woman.setOpaque(false);


		// �߰�
		add(la1); add(tf);
		add(la2); add(tf2);
		add(la3);
		add(p);
		add(man); add(woman);
		
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
	//paint, paintComponent => �ڵ�ȣ��
	protected void paintComponent(Graphics g) {
		g.drawImage(back, 0, 0, getWidth(),getHeight(),this);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
