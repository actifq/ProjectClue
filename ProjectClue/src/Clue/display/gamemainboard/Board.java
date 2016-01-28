package Clue.display.gamemainboard;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
public class Board extends JPanel {
	Image back;
	JPanel jpLogo,jpTurn,jpGameBoard,jpDice;
	JTable table;
	JTextArea ta;
	JTextField ChatInput;
	JButton b;
	
	public Board() {
		back=Toolkit.getDefaultToolkit().getImage("c:\\image\\back.jpg");
		jpLogo=new JPanel();// ���
		jpTurn=new JPanel();//��ȭ��
		jpGameBoard=new GameBoard();//����ȭ��
		JScrollPane jsMemo=new JScrollPane(table);//�޸�ī��
		jpDice=new JPanel();//�ֻ���
		
		//ä��
		ta=new JTextArea();
		JScrollPane jsChatArea=new JScrollPane(ta);//ä��â
		ChatInput=new JTextField();	//ä���Է�â
		b=new JButton("�Է�");	//ä��â
		
		setLayout(null);
		jpLogo.setBounds(5, 10, 850, 90);
		jpTurn.setBounds(865, 10, 320, 90);
		jpGameBoard.setBounds(5, 105, 850, 570);
		jsMemo.setBounds(865, 105, 320, 570);
		jsChatArea.setBounds(5, 680, 850, 160);
		jpDice.setBounds(865, 680, 320, 185);
		ChatInput.setBounds(5, 840, 790, 25);
		b.setBounds(795, 840, 60, 25);
		
		add(jpLogo);
		add(jpTurn);
		add(jpGameBoard);
		add(jsMemo);
		add(jpDice);
		add(jsChatArea);
		add(ChatInput);
		add(b);
	}
	@Override
	protected void paintComponent(Graphics g) {  
		g.drawImage(back, 0, 0, getWidth(),getHeight(),this);

	}
}
