package sujin;

import java.awt.*; //Layout
import java.awt.event.*;
import javax.swing.*; //window
public class GameMainForm extends JFrame{
	CardLayout card=new CardLayout();
	//Login login=new Login();
	Board n=new Board();
	public GameMainForm(){
		setLayout(card); //BorderLayout
		add("N",n);
		//add("LOG",login);
		//크기
		setSize(1200, 900);
		//윈도우를 보여라
		setVisible(true);
		setResizable(false);   //창 크기조절 불가하도록 설정
		
		/*login.b1.addActionListener(this);
		n.tf.addActionListener(this);*/
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 생성자
		try{
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		}catch(Exception ex){}
		
		GameMainForm gm=new GameMainForm();
	}
	/*public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==login.b1){
			card.show(getContentPane(), "N");
		}
		else if(e.getSource()==n.tf){
			String data=n.tf.getText();
			n.ta.append(data+"\n");   //한 줄씩 넘어가며 문자 붙임
			n.tf.setText("");
		}
	}*/
}
