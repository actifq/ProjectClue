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
		//ũ��
		setSize(1200, 900);
		//�����츦 ������
		setVisible(true);
		setResizable(false);   //â ũ������ �Ұ��ϵ��� ����
		
		/*login.b1.addActionListener(this);
		n.tf.addActionListener(this);*/
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ������
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
			n.ta.append(data+"\n");   //�� �پ� �Ѿ�� ���� ����
			n.tf.setText("");
		}
	}*/
}
