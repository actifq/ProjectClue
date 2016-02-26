package Clue;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.*;
import javax.swing.table.*;

public class GameWaitingRoom extends JPanel {

	/*
	 * class userInfo{ JPanel uPan; JPanel aa; JPanel bb; JTextField idtf;
	 * JTextField avaName; }
	 */
	Image back3;
	Image p1, p2, p3, p4, p5, p6, qc;

	JPanel title;
	JPanel uPan1, uPan2, uPan3, uPan4, bb1, bb2, bb3, bb4;

	JPanel CntDwn;

	JPanel[] aa = new JPanel[4];
	int pnum; // ÇÃ·¹ÀÌ¾î ³Ñ¹ö

	JTextArea chat;
	JTextField chatInput;

	JButton btnReady, btnExit, insert;// 160211 Á¤¼± ¼öÁ¤ (JComboBox»èÁ¦->JButton
										// insertÃß°¡/ÇÏÀ§box°ü·ÃÇ×¸ñ ¼öÁ¤)

	JTextField t1 = new JTextField();
	JTextField t2 = new JTextField();
	JTextField t3 = new JTextField();
	JTextField t4 = new JTextField();

	JTextField[] avaName = new JTextField[4];
	JTextField[] isReady = new JTextField[4];
	JTextField[] idtf = { t1, t2, t3, t4 };
	boolean[] sw = new boolean[4];

	// JButton chr, chr2, chr3, chr4, chr5, chr6, chrN;
	JButton[] chr = new JButton[7];
	
	AudioClip clip;

	public GameWaitingRoom() {
		back3 = Toolkit.getDefaultToolkit().getImage("image/back/gwrback.jpg");

		p1 = Toolkit.getDefaultToolkit().getImage("image/player/char0.jpg");
		p2 = Toolkit.getDefaultToolkit().getImage("image/player/char1.jpg");
		p3 = Toolkit.getDefaultToolkit().getImage("image/player/char2.jpg");
		p4 = Toolkit.getDefaultToolkit().getImage("image/player/char3.jpg");
		p5 = Toolkit.getDefaultToolkit().getImage("image/player/char4.jpg");
		p6 = Toolkit.getDefaultToolkit().getImage("image/player/char5.jpg");

		// °ÔÀÓ À¯Àú
		title = new JPanel();

		// title.setBackground(Color.darkGray);
		title.add(new JLabel(new ImageIcon(setImage("image/back/jplogo2.png", 1185, 35))));

		// À¯Àú´ë±âÈ­¸é
		uPan1 = new JPanel();
		uPan1.setBackground(Color.BLACK);
		setLayout(null);
		uPan1.setBounds(5, 45, 450, 300);
		uPan1.setLayout(new BorderLayout());
		uPan1.add("Center",
				new JLabel(new ImageIcon(setImage("image/back/cardback.jpg", uPan1.getWidth(), uPan1.getHeight()))));

		aa[0] = new JPanel();
		aa[0].setOpaque(false);
		aa[0].setBounds(5, 60, 220, 295);
		aa[0].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		add(aa[0]);

		bb1 = new JPanel();
		bb1.setOpaque(false);
		bb1.setBounds(230, 60, 220, 295);
		bb1.add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		// add(bb1);

		uPan2 = new JPanel();
		uPan2.setBackground(Color.BLACK);
		uPan2.setBounds(457, 45, 449, 300);
		uPan2.setLayout(new BorderLayout());
		uPan2.add("Center",
				new JLabel(new ImageIcon(setImage("image/back/cardback.jpg", uPan2.getWidth(), uPan2.getHeight()))));

		aa[1] = new JPanel();
		aa[1].setOpaque(false);
		aa[1].setBounds(457, 60, 220, 295);
		aa[1].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		add(aa[1]);

		bb2 = new JPanel();
		bb2.setOpaque(false);
		bb2.setBounds(682, 60, 220, 250);
		bb2.add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		// add(bb2);

		uPan3 = new JPanel();
		uPan3.setBackground(Color.BLACK);
		setLayout(null);
		uPan3.setBounds(5, 347, 450, 300);
		uPan3.setLayout(new BorderLayout());
		uPan3.add("Center",
				new JLabel(new ImageIcon(setImage("image/back/cardback.jpg", uPan3.getWidth(), uPan3.getHeight()))));

		aa[2] = new JPanel();
		aa[2].setOpaque(false);
		aa[2].setBounds(5, 362, 220, 295);
		aa[2].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		add(aa[2]);

		bb3 = new JPanel();
		bb3.setOpaque(false);
		bb3.setBounds(230, 362, 220, 295);
		bb3.add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		// add(bb3);

		uPan4 = new JPanel();
		uPan4.setBackground(Color.BLACK);
		setLayout(null);
		uPan4.setBounds(457, 347, 449, 300);
		uPan4.setLayout(new BorderLayout());
		uPan4.add("Center",
				new JLabel(new ImageIcon(setImage("image/back/cardback.jpg", uPan4.getWidth(), uPan4.getHeight()))));

		aa[3] = new JPanel();
		aa[3].setOpaque(false);
		aa[3].setBounds(457, 362, 220, 295);
		aa[3].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		add(aa[3]);

		bb4 = new JPanel();
		bb4.setOpaque(false);
		bb4.setBounds(682, 362, 220, 295);
		bb4.add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", 171, 250))));
		// add(bb4);

		// Ã¤ÆÃÃ¢
		chat = new JTextArea();
		chat.setEditable(false);
		JScrollPane js1 = new JScrollPane(chat);
		chatInput = new JTextField();
		insert = new JButton("ÀÔ·Â");

		// ·¹µð,³ª°¡±â
		btnReady = new JButton("READY");
		btnExit = new JButton("EXIT");

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1, 5, 5));
		p.add(btnReady);
		p.add(btnExit);
		p.setOpaque(false);

		// ÄÉ¸¯ÅÍ ¼±ÅÃÃ¢
		/*
		 * chrN = new JButton("Ä³¸¯ÅÍ ¼±ÅÃÃ¢"); chrN.setBounds(912, 45, 278, 30);
		 * chrN.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		 */

		chr[0] = new JButton("Ä³¸¯ÅÍ ¼±ÅÃÃ¢");
		chr[0].setBounds(912, 45, 278, 30);
		chr[0].setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));

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

		avaName[0] = new JTextField("");
		avaName[1] = new JTextField("");
		avaName[2] = new JTextField("");
		avaName[3] = new JTextField("");

		isReady[0] = new JTextField("");
		isReady[1] = new JTextField("");
		isReady[2] = new JTextField("");
		isReady[3] = new JTextField("");

		JLabel ii1 = new JLabel("ID");
		ii1.setBounds(235, 90, 50, 40);
		ii1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		ii1.setForeground(Color.white);

		JLabel ii2 = new JLabel("ID");
		ii2.setBounds(690, 90, 50, 40);
		ii2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		ii2.setForeground(Color.white);

		JLabel ii3 = new JLabel("ID");
		ii3.setBounds(235, 395, 50, 40);
		ii3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		ii3.setForeground(Color.white);

		JLabel ii4 = new JLabel("ID");
		ii4.setBounds(690, 395, 50, 40);
		ii4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		ii4.setForeground(Color.white);

		add(ii1);
		add(ii2);
		add(ii3);
		add(ii4);

		idtf[0].setBounds(310, 90, 100, 40);
		idtf[0].setOpaque(false);
		idtf[0].setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		idtf[0].setForeground(Color.white);
		idtf[0].setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);

		idtf[1].setBounds(765, 90, 100, 40);
		idtf[1].setOpaque(false);
		idtf[1].setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		idtf[1].setForeground(Color.white);
		idtf[1].setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);

		idtf[2].setBounds(310, 395, 100, 40);
		idtf[2].setOpaque(false);
		idtf[2].setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		idtf[2].setForeground(Color.white);
		idtf[2].setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);

		idtf[3].setBounds(765, 395, 100, 40);
		idtf[3].setOpaque(false);
		idtf[3].setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		idtf[3].setForeground(Color.white);
		idtf[3].setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);

		add(idtf[0]);
		add(idtf[1]);
		add(idtf[2]);
		add(idtf[3]);

		avaName[0].setBounds(310, 150, 100, 40);
		avaName[0].setOpaque(false);
		avaName[0].setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		avaName[0].setForeground(Color.white);
		avaName[0].setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);

		avaName[1].setBounds(765, 150, 100, 40);
		avaName[1].setOpaque(false);
		avaName[1].setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		avaName[1].setForeground(Color.white);
		avaName[1].setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);

		avaName[2].setBounds(310, 455, 100, 40);
		avaName[2].setOpaque(false);
		avaName[2].setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		avaName[2].setForeground(Color.white);
		avaName[2].setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);

		avaName[3].setBounds(765, 455, 100, 40);
		avaName[3].setOpaque(false);
		avaName[3].setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		avaName[3].setForeground(Color.white);
		avaName[3].setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);

		add(avaName[0]);
		add(avaName[1]);
		add(avaName[2]);
		add(avaName[3]);

		JLabel jj1 = new JLabel("Ä³¸¯ÅÍ");
		jj1.setBounds(235, 150, 100, 40);
		jj1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		jj1.setForeground(Color.white);
		add(jj1);

		JLabel jj2 = new JLabel("Ä³¸¯ÅÍ");
		jj2.setBounds(690, 150, 100, 40);
		jj2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		jj2.setForeground(Color.white);
		add(jj2);

		JLabel jj3 = new JLabel("Ä³¸¯ÅÍ");
		jj3.setBounds(235, 455, 100, 40);
		jj3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		jj3.setForeground(Color.white);
		add(jj3);

		JLabel jj4 = new JLabel("Ä³¸¯ÅÍ");
		jj4.setBounds(690, 455, 100, 40);
		jj4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		jj4.setForeground(Color.white);
		add(jj4);

/*		for(int i=0;i<4;i++){
			isReady[i].setBounds(235, 230, 180, 40);
		}*/
		
		js1.setBounds(5, 652, 902, 180);
		chatInput.setBounds(5, 837, 795, 30);
		insert.setBounds(805, 837, 102, 30);
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

		/*
		 * Ä³¸¯ÅÍ ÀÌ¹ÌÁö ¹Ù²Ü¶§ gwr.pan[i].removeAll(); gwr.pan[i].setLayout(new
		 * BorderLayout()); gwr.pan[i].add("Center", new JLabel(new
		 * ImageIcon("c:\\image\\"+s+avata+".gif")));
		 * gwr.pan[i].validate();//panelÀç¹èÄ¡
		 */
		// setCntDwn();
		
		try {
            File file = new File("wav/GameWaitingRoom_bgm.wav");
            clip = Applet.newAudioClip(file.toURL());
            clip.stop();
            
           
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
	}

	public void setCntDwn() {
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
		// return null;
	}

	@Override
	// paint, paintComponent => ÀÚµ¿È£Ãâ
	protected void paintComponent(Graphics g) {
		g.drawImage(back3, 0, 0, getWidth(), getHeight(), this);
	}
}