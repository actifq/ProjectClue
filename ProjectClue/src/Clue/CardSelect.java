package Clue;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CardSelect extends JPanel {
	Image back, back3;

	JLabel[] tfGuess = new JLabel[3];// ����
	JLabel[] info = new JLabel[3];// ����

	JLabel k, nPl;// ����
	JButton[] p = new JButton[6];
	JButton[] q = new JButton[8];
	JButton[] j = new JButton[9];
	JPanel pl;
	JPanel[] guess = new JPanel[3];
	JButton st;// �߸� ����

	public CardSelect() {

		back = Toolkit.getDefaultToolkit().getImage("image/back/cardback.jpg");
		back3 = Toolkit.getDefaultToolkit().getImage("image/back/cardback.jpg");

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 6, 5, 5));
		for (int i = 0; i < p.length; i++) {
			p[i] = new JButton();
			p1.add(p[i]);
		}

		p1.setOpaque(false);// ��� �����ϰ�

		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(1, 8, 5, 5));// �� �� ���� ����
		for (int i = 0; i < q.length; i++) {
			q[i] = new JButton();
			p2.add(q[i]);
		}
		p2.setOpaque(false);// ��� �����ϰ�

		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(1, 9, 5, 5));// �� �� ���� ����
		for (int i = 0; i < j.length; i++) {
			j[i] = new JButton();
			p3.add(j[i]);
		}
		p3.setOpaque(false);// ��� �����ϰ�

		tfGuess[0] = new JLabel("��𿡼�", JLabel.CENTER);
		tfGuess[1] = new JLabel("����", JLabel.CENTER);
		tfGuess[2] = new JLabel("��������", JLabel.CENTER);

		info[0] = new JLabel("", JLabel.CENTER);
		info[1] = new JLabel("", JLabel.CENTER);
		info[2] = new JLabel("", JLabel.CENTER);

		k = new JLabel("�׿���!!", JLabel.RIGHT);
		st = new JButton("�߸� ����");
		st.setFont(new Font("���� ���", Font.BOLD, 30));

		for (int i = 0; i < 3; i++) {
			guess[i] = new JPanel();
			guess[i].setBounds(50 + (i * 250), 500, 180, 250);
			guess[i].setLayout(new BorderLayout());
			guess[i].add(new JLabel(
					new ImageIcon(setImage("image/back/qcard.png", guess[i].getWidth(), guess[i].getHeight()))));
			info[i].setBounds(50 + (i * 250), 600, 180, 30);
			info[i].setBackground(Color.BLACK);
			info[i].setForeground(Color.GREEN);

			tfGuess[i].setBounds(50 + (i * 250), 600, 180, 30);
			tfGuess[i].setBackground(Color.YELLOW);
			tfGuess[i].setForeground(Color.BLACK);
			tfGuess[i].setFont(new Font("���� ���", Font.BOLD, 13));
		}

		JLabel l3 = new JLabel("����", JLabel.CENTER);
		l3.setFont(new Font("���� ���", Font.BOLD, 30));
		l3.setForeground(Color.white);
		l3.setBounds(225, 700, 80,50);
		add(l3);
		
		JLabel l4 = new JLabel("��", JLabel.CENTER);
		l4.setFont(new Font("���� ���", Font.BOLD, 30));
		l4.setForeground(Color.white);
		l4.setBounds(475, 700, 80,50);
		add(l4);
		
		JLabel l5 = new JLabel("�� �̿��ؼ� �����ڸ� �׿���.", JLabel.CENTER);
		l5.setFont(new Font("���� ���", Font.BOLD, 30));
		l5.setForeground(Color.white);
		l5.setBounds(740, 700, 440,50);
		add(l5);
		
		// �ɸ��� ī��
		pl = new JPanel();
		pl.setBounds(950, 90, 200, 280);
		pl.setLayout(new BorderLayout());
		pl.add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", pl.getWidth(), pl.getHeight()))));

		nPl = new JLabel("...�߸���", JLabel.CENTER);
		nPl.setFont(new Font("���� ���", Font.BOLD, 25));
		nPl.setForeground(Color.white);
		nPl.setBounds(950, 400, 200, 30);

		JPanel p4 = new JPanel();
		p4.setLayout(new GridLayout(1, 4, 5, 5));
		p4.add(k);
		p4.setOpaque(false);

		JPanel p5 = new JPanel();
		p5.setLayout(new GridLayout(1, 1, 100, 100));
		p5.add(st);

		setLayout(null);

		JLabel l1 = new JLabel("������ ����", JLabel.CENTER);
		l1.setFont(new Font("���� ���", Font.BOLD, 30));
		l1.setForeground(Color.white);
		l1.setBounds(20, 30, 200, 50);
		add(l1);

		JLabel l2 = new JLabel("���� ���� ����", JLabel.CENTER);
		l2.setFont(new Font("���� ���", Font.BOLD, 30));
		l2.setForeground(Color.white);
		l2.setBounds(20, 255, 250, 40);
		add(l2);
		
		

		p1.setBounds(20, 80, 650, 150);// ������ 10(����),15(����)
		p2.setBounds(20, 305, 870, 150);// ������ 10(����),250(����)
		p3.setBounds(20, 335, 900, 120);
		p4.setBounds(20, 600, 600, 150);

		p5.setBounds(950, 550, 200, 100);

		add(p1);
		add(p2);
		add(pl);
		//add(p3);
		// add(p4);
		add(guess[0]);
		add(guess[1]);
		add(guess[2]);
		add(tfGuess[0]);
		add(tfGuess[1]);
		add(tfGuess[2]);
		add(p5);
		add(nPl);

	}

	public void setCardImg() {

		for (int i = 0; i < p.length; i++) {
			p[i].setIcon(new ImageIcon(setImage("image/player/char" + i + ".jpg", p[0].getWidth(), p[0].getHeight())));
		}
		for (int i = 0; i < q.length; i++) {
			q[i].setIcon(new ImageIcon(setImage("image/weapon/wp" + i + ".jpg", q[0].getWidth(), q[0].getHeight())));

		}
		for (int i = 0; i < j.length; i++) {
			j[i].setIcon(new ImageIcon(setImage("image/room/room" + i + ".jpg", j[0].getWidth(), j[0].getHeight())));

		}

	}

	public Image setImage(String filename, int width, int height) {
		ImageIcon ii = new ImageIcon(filename);
		Image image = ii.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

		return image;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(back3, 0, 0, getWidth(), getHeight(), this);
	}

}
