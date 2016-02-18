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
	Image p1,p2,p3,p4,p5,p6,qc;
	
	JPanel title;
	JPanel uPan1,uPan2,uPan3,uPan4,aa1,aa2,aa3,aa4,bb1,bb2,bb3,bb4;
	JPanel[] imgP={aa1,aa2,aa3,aa4};
	
	
	JTextArea chat;
	JTextField chatInput;
	
	JButton btnReady,btnExit,insert;//160211 정선 수정 (JComboBox삭제->JButton insert추가/하위box관련항목 수정)
	AudioClip clip;

	WaitRoom wait=new WaitRoom();

    JTextField t1=new JTextField();
    JTextField t2=new JTextField();
    JTextField t3=new JTextField();
    JTextField t4=new JTextField();

	
	JTextField[] idtf={t1,t2,t3,t4};
	boolean[] sw=new boolean[4];

	JButton chr1, chr2, chr3, chr4, chr5, chr6, chrN;


	
	public GameWaitingRoom(){
		back3=Toolkit.getDefaultToolkit().getImage("image/back/gwrback.jpg");

		p1 = Toolkit.getDefaultToolkit().getImage("image/player/char0.jpg");
		p2 = Toolkit.getDefaultToolkit().getImage("image/player/char1.jpg");
		p3 = Toolkit.getDefaultToolkit().getImage("image/player/char2.jpg");
		p4 = Toolkit.getDefaultToolkit().getImage("image/player/char3.jpg");
		p5 = Toolkit.getDefaultToolkit().getImage("image/player/char4.jpg");
		p6 = Toolkit.getDefaultToolkit().getImage("image/player/char5.jpg");
		
		

		//게임 유저
		title=new JPanel();

		//title.setBackground(Color.darkGray);
		title.add(new JLabel(new ImageIcon(setImage("image/back/jplogo2.png", 1185, 35))));
		
		//유저대기화면
		uPan1 = new JPanel();
		uPan1.setBackground(Color.BLACK);
		setLayout(null);
		uPan1.setBounds(5, 45, 450, 300);
		uPan1.setLayout(new BorderLayout());
		uPan1.add("Center",new JLabel(new ImageIcon(setImage("image/back/cardback.jpg", uPan1.getWidth(), uPan1.getHeight()))));
		
		
		

		aa1=new JPanel();
		aa1.setOpaque(false);
		aa1.setBounds(5, 60, 220, 295);
		aa1.add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		add(aa1);

		
		bb1=new JPanel();
		bb1.setOpaque(false);
		bb1.setBounds(230, 60, 220, 295);
		bb1.add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		//add(bb1);
		
		
		uPan2 = new JPanel();
		uPan2.setBackground(Color.BLACK);
		uPan2.setBounds(457, 45, 449, 300);
		uPan2.setLayout(new BorderLayout());
		uPan2.add("Center",new JLabel(new ImageIcon(setImage("image/back/cardback.jpg", uPan2.getWidth(), uPan2.getHeight()))));
		
		aa2=new JPanel();
		aa2.setOpaque(false);
		aa2.setBounds(457, 60, 220, 295);
		aa2.add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		add(aa2);
			
		bb2=new JPanel();
		bb2.setOpaque(false);
		bb2.setBounds(682, 60, 220, 295);
		bb2.add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		//add(bb2);
	
		uPan3 = new JPanel();
		uPan3.setBackground(Color.BLACK);
		setLayout(null);
		uPan3.setBounds(5, 347, 450, 300);
		uPan3.setLayout(new BorderLayout());
		uPan3.add("Center",new JLabel(new ImageIcon(setImage("image/back/cardback.jpg", uPan3.getWidth(), uPan3.getHeight()))));
		
		aa3=new JPanel();
		aa3.setOpaque(false);
		aa3.setBounds(5, 362, 220, 295);
		aa3.add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		add(aa3);
			
		bb3=new JPanel();
		bb3.setOpaque(false);
		bb3.setBounds(230, 362, 220, 295);
		bb3.add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		//add(bb3);
		
		uPan4 = new JPanel();
		uPan4.setBackground(Color.BLACK);
		setLayout(null);
		uPan4.setBounds(457, 347, 449, 300);
		uPan4.setLayout(new BorderLayout());
		uPan4.add("Center",new JLabel(new ImageIcon(setImage("image/back/cardback.jpg", uPan4.getWidth(), uPan4.getHeight()))));
		
		aa4=new JPanel();
		aa4.setOpaque(false);
		aa4.setBounds(457, 362, 220, 295);
		aa4.add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		add(aa4);
			
		bb4=new JPanel();
		bb4.setOpaque(false);
		bb4.setBounds(682, 362, 220, 295);
		bb4.add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		//add(bb4);
		
		
	
		//채팅창
		chat=new JTextArea();
		chat.setEditable(false);
		JScrollPane js1=new JScrollPane(chat);
		chatInput=new JTextField();
		insert=new JButton("입력");
		
		
		//레디,나가기
		btnReady=new JButton("READY");
		btnExit=new JButton("EXIT");
		
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(2,1,5,5));
		p.add(btnReady);
		p.add(btnExit);
		p.setOpaque(false);
		
		//케릭터 선택창
		chrN = new JButton("캐릭터 선택창");
		chrN.setBounds(912, 45, 278, 30);
		chrN.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		
		ImageIcon img1 = new ImageIcon("image/player/wp0.jpg");
		ImageIcon img2 = new ImageIcon("image/player/wp1.jpg");
		ImageIcon img3 = new ImageIcon("image/player/wp2.jpg");
		ImageIcon img4 = new ImageIcon("image/player/wp3.jpg");
		ImageIcon img5 = new ImageIcon("image/player/wp4.jpg");
		ImageIcon img6 = new ImageIcon("image/player/wp5.jpg");

		chr1 = new JButton(img1);
		chr2 = new JButton(img2);
		chr3 = new JButton(img3);
		chr4 = new JButton(img4);
		chr5 = new JButton(img5);
		chr6 = new JButton(img6);
		
		setLayout(null);
		title.setBounds(5, 5, 1185, 35);

	
		
  		  
  		idtf[0].setBounds(235,45,225, 50);
  		idtf[1].setBounds(690,45,225, 50);
  		idtf[2].setBounds(235,347,225, 50);
  		idtf[3].setBounds(690,347,225, 50);
  		  
  		add(idtf[0]);
  		add(idtf[1]);
  		add(idtf[2]);
  		add(idtf[3]);
  		
  		
		

		
		js1.setBounds(5, 652, 902, 180);
		chatInput.setBounds(5, 837, 795, 30);
		insert.setBounds(805,837,102,30);
		chr1.setBounds(912, 75, 278, 95);
		chr2.setBounds(912, 170, 278, 95);
		chr3.setBounds(912, 265, 278, 95);
		chr4.setBounds(912, 360, 278, 95);
		chr5.setBounds(912, 455, 278, 95);
		chr6.setBounds(912, 550, 278, 95);
		p.setBounds(912, 652, 278, 215);
		
		
		add(title);
		add(chrN);
		add(chr1);
		add(chr2);
		add(chr3);
		add(chr4);
		add(chr5);
		add(chr6);
		add(uPan1);
		add(uPan2);
		add(uPan3);
		add(uPan4);
		add(js1);
		add(chatInput);
		add(insert);
		add(chr1);
		add(chr2);
		add(p);
		
		try {
            File file = new File("wav/GameWaitingRoom_bgm_low.wav");
            clip = Applet.newAudioClip(file.toURL());
            clip.stop();
            
           
        } catch (MalformedURLException e){
            e.printStackTrace();
        }


		
	/*	캐릭터 이미지 바꿀때
	 * gwr.pan[i].removeAll();
		  gwr.pan[i].setLayout(new BorderLayout());
		  gwr.pan[i].add("Center",
				  new JLabel(new ImageIcon("c:\\image\\"+s+avata+".gif")));
		  gwr.pan[i].validate();//panel재배치
*/

		
	}
	private Image setImage(String filename, int width, int height) {
		// TODO Auto-generated method stub
		ImageIcon ii = new ImageIcon(filename);
		Image image = ii.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return image;
		//return null;
	}
	@Override
	//paint, paintComponent => 자동호출
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
