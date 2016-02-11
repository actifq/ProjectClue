package Clue;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
public class GameMainScreen extends JPanel {
	Image back;
	JPanel jpLogo,jpTurn,jpGameBoard,jpDice;
	JTable table;
	JTextArea ta;
	JTextField ChatInput;
	JButton b;
	
	public GameMainScreen() {
		back=Toolkit.getDefaultToolkit().getImage("image/gwrback.jpg");
		jpLogo=new JPanel();// 배너
		jpTurn=new JPanel();//턴화면
		jpGameBoard=new GameArea();//게임화면
		JScrollPane jsMemo=new JScrollPane(table);//메모카드
		jpDice=new JPanel();//주사위
		
		//채팅
		ta=new JTextArea();
		JScrollPane jsChatArea=new JScrollPane(ta);//채팅창
		ChatInput=new JTextField();	//채팅입력창
		b=new JButton("입력");	//채팅창
		
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
