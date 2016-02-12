package Clue;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class GameMainScreen extends JPanel {
	Image back;
	JPanel jpLogo,jpTurn,jpGameBoard,jpDice,jpNote1;
	JTextArea ta;
	JTextField ChatInput;
	JButton b;
	
	public GameMainScreen() {
		back=Toolkit.getDefaultToolkit().getImage(" ");
		jpLogo=new JPanel();	// ���
		jpTurn=new JPanel();	//��ȭ��
		jpGameBoard=new GameArea();		//����ȭ��
		jpDice=new JPanel();	//�ֻ���
		jpNote1=new GameNote();
		jpNote1.setLayout(new FlowLayout());
		/*JScrollPane js1=new JScrollPane(jpNote1);
		js1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);*/
		//jpNote1.setBackground(Color.gray);		//�߸���Ʈ
		
		//ä��
		ta=new JTextArea();
		JScrollPane jsChatArea=new JScrollPane(ta);		//ä��â
		ChatInput=new JTextField();		//ä���Է�â
		b=new JButton("�Է�");	//ä��â
		
		
		setLayout(null);
		jpLogo.setBounds(5, 10, 850, 90);
		jpTurn.setBounds(865, 10, 320, 90);
		jpGameBoard.setBounds(5, 105, 850, 570);
		jsChatArea.setBounds(5, 680, 850, 160);
		jpDice.setBounds(865, 680, 320, 185);
		ChatInput.setBounds(5, 840, 790, 25);
		b.setBounds(795, 840, 60, 25);
		jpNote1.setBounds(865, 105, 320, 570);
		/*jpNote2.setBounds(865, 295, 320, 185);
		jpNote3.setBounds(865, 485, 320, 185);*/
		
		
		add(jpLogo);
		add(jpTurn);
		add(jpGameBoard);
		add(jpDice);
		add(jsChatArea);
		add(ChatInput);
		add(b);
		add(jpNote1);
		/*add(jpNote2);
		add(jpNote3);*/
		//add(js1);
		
	}
	@Override
	protected void paintComponent(Graphics g) {  
		g.drawImage(back, 0, 0, getWidth(),getHeight(),this);

	}
}
