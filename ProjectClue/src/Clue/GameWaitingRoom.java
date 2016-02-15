package Clue;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.*;
import javax.swing.table.*;
public class GameWaitingRoom extends JPanel implements ActionListener{
	Image back3;
	JPanel title,user1,user2,user3,user4,chr1,chr2,qcard;
	JTextArea chat;
	JTextField chatInput;

	JButton btnReady,btnExit,insert;//160211 ���� ���� (JComboBox����->JButton insert�߰�/����box�����׸� ����)
	AudioClip clip;
	
	WaitRoom wait=new WaitRoom();
	
	public GameWaitingRoom(){
		back3=Toolkit.getDefaultToolkit().getImage("image/gwrback.jpg");
		//���� ����
		title=new JPanel();
		title.setBackground(Color.darkGray);
		user1=new GWR_userPanel();
		user2=new GWR_userPanel();
		//user2.setBackground(Color.gray);
		user3=new GWR_userPanel();
		//user3.setBackground(Color.gray);
		user4=new GWR_userPanel();
		//user4.setBackground(Color.gray);
		
		
		//ĳ���� ����
		chr1=new JPanel();
		chr1.setBackground(Color.black);
		chr2=new JPanel();
		chr2.setBackground(Color.lightGray);
		
		//ä��â
		chat=new JTextArea();
		chat.setEditable(false);
		JScrollPane js1=new JScrollPane(chat);
		chatInput=new JTextField();
		insert=new JButton("�Է�");
		
		
		//����,������
		btnReady=new JButton("READY");
		btnExit=new JButton("EXIT");
		
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(2,1,5,5));
		p.add(btnReady);
		p.add(btnExit);
		p.setOpaque(false);
		
		setLayout(null);
		title.setBounds(5, 5, 1185, 35);
		user1.setBounds(5, 45, 450, 300);
		user2.setBounds(457, 45, 449, 300);
		user3.setBounds(5, 347, 450, 300);
		user4.setBounds(457, 347, 449, 300);
		js1.setBounds(5, 652, 902, 180);
		chatInput.setBounds(5, 837, 795, 30);
		insert.setBounds(805,837,102,30);
		chr1.setBounds(912, 75, 278, 572);
		chr2.setBounds(912, 45, 278, 30);
		p.setBounds(912, 652, 278, 215);
		
		
		add(title);
		add(user1);
		add(user2);
		add(user3);
		add(user4);
		add(js1);
		add(chatInput);
		add(insert);
		add(chr1);
		add(chr2);
		add(p);
		
		try {
            File file = new File("wav/GameWaitingRoom_bgm.wav");
            clip = Applet.newAudioClip(file.toURL());
            clip.stop();
            
           
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

		
	}
	@Override
	//paint, paintComponent => �ڵ�ȣ��
	protected void paintComponent(Graphics g) {
		g.drawImage(back3, 0, 0, getWidth(),getHeight(),this);
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==wait.b2)
		{
			clip.play();
		}
	}
}
