package Clue;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class GameWaitingRoom extends JPanel {
	
	/*class userInfo{
		JPanel uPan;
		JPanel aa;
		JPanel bb;
		JTextField idtf;
		JTextField avaName;
	}
	*/
	Image back3;
	Image p1,p2,p3,p4,p5,p6,qc;

	JPanel title;
	JPanel uPan1,uPan2,uPan3,uPan4,bb1,bb2,bb3,bb4;

	JPanel CntDwn;

	JPanel[] aa= new JPanel[4];
	int pnum; //플레이어 넘버
	
	JTextArea chat;
	JTextField chatInput;
	
	JButton btnReady,btnExit,insert;	//160211 정선 수정 (JComboBox삭제->JButton insert추가/하위box관련항목 수정)

    JTextField t1=new JTextField();
    JTextField t2=new JTextField();
    JTextField t3=new JTextField();
    JTextField t4=new JTextField();
    
    JTextField[] avaName= new JTextField[4];

    JTextField[] isReady= new JTextField[4];

	
	JTextField[] idtf={t1,t2,t3,t4};
	boolean[] sw=new boolean[4];

	//JButton chr, chr2, chr3, chr4, chr5, chr6, chrN;
	JButton[] chr=new JButton[7];

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
		
		aa[0]=new JPanel();
		aa[0].setOpaque(false);
		aa[0].setBounds(5, 60, 220, 295);
		aa[0].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		add(aa[0]);

		
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
		
		aa[1]=new JPanel();
		aa[1].setOpaque(false);
		aa[1].setBounds(457, 60, 220, 295);
		aa[1].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		add(aa[1]);
			
		bb2=new JPanel();
		bb2.setOpaque(false);
		bb2.setBounds(682, 60, 220, 250);
		bb2.add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		//add(bb2);
	
		uPan3 = new JPanel();
		uPan3.setBackground(Color.BLACK);
		setLayout(null);
		uPan3.setBounds(5, 347, 450, 300);
		uPan3.setLayout(new BorderLayout());
		uPan3.add("Center",new JLabel(new ImageIcon(setImage("image/back/cardback.jpg", uPan3.getWidth(), uPan3.getHeight()))));
		
		aa[2]=new JPanel();
		aa[2].setOpaque(false);
		aa[2].setBounds(5, 362, 220, 295);
		aa[2].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		add(aa[2]);
			
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
		
		aa[3]=new JPanel();
		aa[3].setOpaque(false);
		aa[3].setBounds(457, 362, 220, 295);
		aa[3].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		add(aa[3]);
			
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
		/*chrN = new JButton("캐릭터 선택창");
		chrN.setBounds(912, 45, 278, 30);
		chrN.setFont(new Font("맑은 고딕", Font.BOLD, 14));*/
		
		chr[0] = new JButton("캐릭터 선택창");
		chr[0].setBounds(912, 45, 278, 30);
		chr[0].setFont(new Font("맑은 고딕", Font.BOLD, 14));
		
		ImageIcon img1 = new ImageIcon("image/player/wp0.jpg");
		ImageIcon img2 = new ImageIcon("image/player/wp1.jpg");
		ImageIcon img3 = new ImageIcon("image/player/wp2.jpg");
		ImageIcon img4 = new ImageIcon("image/player/wp3.jpg");
		ImageIcon img5 = new ImageIcon("image/player/wp4.jpg");
		ImageIcon img6 = new ImageIcon("image/player/wp5.jpg");

		chr[1] = new JButton(img1);
		chr[2] = new JButton(img2);
		chr[3] = new JButton(img3);
		chr[4] = new JButton(img4);
		chr[5] = new JButton(img5);
		chr[6] = new JButton(img6);
		
		setLayout(null);
		title.setBounds(5, 5, 1185, 35);
		
		avaName[0]= new JTextField("?");
		avaName[1]= new JTextField("?");
		avaName[2]= new JTextField("?");
		avaName[3]= new JTextField("?");

		
		isReady[0]= new JTextField("");
		isReady[1]= new JTextField("");
		isReady[2]= new JTextField("");
		isReady[3]= new JTextField("");

	
		
  		  
  		idtf[0].setBounds(235,45,225, 50);
  		idtf[1].setBounds(690,45,225, 50);
  		idtf[2].setBounds(235,347,225, 50);
  		idtf[3].setBounds(690,347,225, 50);
  		  
  		add(idtf[0]);
  		add(idtf[1]);
  		add(idtf[2]);
  		add(idtf[3]);
  		
  		avaName[0].setBounds(235,105,225, 50);
  		avaName[1].setBounds(690,105,225, 50);
  		avaName[2].setBounds(235,407,225, 50);
  		avaName[3].setBounds(690,407,225, 50);
  		  
  		add(avaName[0]);
  		add(avaName[1]);
  		add(avaName[2]);
  		add(avaName[3]);
  		
  		isReady[0].setBounds(235,185,225, 50);
  		isReady[1].setBounds(690,185,225, 50);
  		isReady[2].setBounds(235,487,225, 50);
  		isReady[3].setBounds(690,487,225, 50);
  		  
  		add(isReady[0]);
  		add(isReady[1]);
  		add(isReady[2]);
  		add(isReady[3]);
  		
  		
  		CntDwn= new JPanel();
  		CntDwn.setOpaque(false);
  		CntDwn.setBounds(510, 330, 171, 250);
  		CntDwn.add(new JLabel(new ImageIcon(setImage("image/dice/d3.png", 171, 250))));

		js1.setBounds(5, 652, 902, 180);
		chatInput.setBounds(5, 837, 795, 30);
		insert.setBounds(805,837,102,30);

		chr[1].setBounds(912, 75, 278, 95);
		chr[2].setBounds(912, 170, 278, 95);
		chr[3].setBounds(912, 265, 278, 95);
		chr[4].setBounds(912, 360, 278, 95);
		chr[5].setBounds(912, 455, 278, 95);
		chr[6].setBounds(912, 550, 278, 95);

		p.setBounds(912, 652, 278, 215);
		
		
		add(title);
		add(chr[0]);
		add(chr[1]);
		add(chr[2]);
		add(chr[3]);
		add(chr[4]);
		add(chr[5]);
		add(chr[6]);
		
		add(uPan1);
		add(uPan2);
		add(uPan3);
		add(uPan4);
		add(js1);
		add(chatInput);
		add(insert);
		
		add(p);

	/*	캐릭터 이미지 바꿀때
	 * gwr.pan[i].removeAll();
		  gwr.pan[i].setLayout(new BorderLayout());
		  gwr.pan[i].add("Center",
				  new JLabel(new ImageIcon("c:\\image\\"+s+avata+".gif")));
		  gwr.pan[i].validate();//panel재배치
*/
		//setCntDwn();
	}
	public void setCntDwn(){
		this.add(CntDwn);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
}
