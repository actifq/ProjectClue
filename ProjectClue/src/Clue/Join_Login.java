package Clue;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Join_Login extends JFrame implements ActionListener{
	JLabel la1,la2,la3,la4,la5;
	//JRadioButton rb1,rb2;
	JTextField tf1,tf2,tf3,tf4;
	JPasswordField pf;
	JComboBox box;
	JButton b1,b2;
	
	
	public Join_Login() 
	{
		
		la1=new JLabel("���̵�");
		la2=new JLabel("��й�ȣ");
		la3=new JLabel("�̸�");
		la4=new JLabel("�ڵ�����ȣ");
		la5=new JLabel("�̸���");
		
		
		tf1=new JTextField();
		tf2=new JTextField();
		tf3=new JTextField();
		tf4=new JTextField();
		
		pf=new JPasswordField();
		/*rb1=new JRadioButton("����");
		rb2=new JRadioButton("����");*/
		
		b1=new JButton("Ȯ��");
		b2=new JButton("���");
		
		//��ġ
		setLayout(null);
		la1.setBounds(10,15,40,30);
		tf1.setBounds(90, 15, 140, 30);
		
		la2.setBounds(10,60,70,30);
		pf.setBounds(90,60,140,30);
		//rb1.setBounds(55, 50, 70, 30);
		//rb2.setBounds(130, 50, 70, 30);
		
		la3.setBounds(10,105,70,30);
		tf2.setBounds(90, 105, 140, 30);
		
		la4.setBounds(10,150,70,30);
		tf3.setBounds(90, 150, 140, 30);
		
		la5.setBounds(10,195,70,30);
		tf4.setBounds(90, 195, 140, 30);
	
		
		JPanel p=new JPanel();
		p.add(b1);
		p.add(b2);
		
		p.setBounds(35,250,195,50);
		
		//�߰�
		add(la1);add(tf1);
		add(la2);add(pf);
		add(la3);add(tf2);
		add(la4);add(tf3);
		add(la5);add(tf4);
		add(p);
		
		setBounds(470,310,260,320);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1)
		{
			setVisible(false);
		}
		else if(e.getSource()==b2)
		{
			setVisible(false);
		}		
	}
}
