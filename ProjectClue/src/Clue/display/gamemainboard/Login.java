package Clue.display.gamemainboard;

import javax.swing.*;
import java.awt.*;
public class Login extends JPanel{
	Image back;
	JLabel la1,la2;
	JTextField tf;
	JPasswordField pf;
	JButton b1,b2;
	// ���� (�ʱ�ȭ)
	
	/* JPanel => �ε�
	 *             1) ������ ȣ��
	 *             2) update() => clearRect() ==> ȭ���� ����(â ũ�� �����, ����� �����ٰ� �ٽ� �θ��� ���ؼ�)
	 *                paint(),paintComponent() ==> �ڵ� ȣ��
	 *             ==> ȭ�� ����
	 */
	 
	public Login() 
	{
		back=Toolkit.getDefaultToolkit().getImage("c:\\image\\back.jpg");
		la1=new JLabel("ID");
		la2=new JLabel("PW");
		tf=new JTextField();
		pf=new JPasswordField();
		b1=new JButton("�α���");
		b2=new JButton("���");
		
		JPanel p=new JPanel(); // FlowLayout
		p.add(b1);
		p.add(b2);
		p.setBounds(10, 85, 185, 35);
		p.setOpaque(false);  //��� �����ϰ�(���׸��� ���̵���)
		// ��ġ
		//setLayout(new GridLayout(3, 2 , 5 , 5)); //���� 5(�¿찣��),5(���Ʒ�����)
		setLayout(null); //����� ����
		la1.setBounds(10, 15, 30, 30);
		tf.setBounds(45, 15, 150, 30);
		la2.setBounds(10, 50, 30, 30);
		pf.setBounds(45, 50, 150, 30);
		// �߰�
		add(la1); add(tf);
		add(la2); add(pf);
		add(b1); add(b2);
		add(p);
	}
	@Override
	//paint, paintComponent => �ڵ�ȣ��
	protected void paintComponent(Graphics g) {
		g.drawImage(back, 0, 0, getWidth(),getHeight(),this);
	}
	
}
