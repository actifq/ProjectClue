package Clue;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CardSelect extends JPanel implements ActionListener{
	Image back;
	JTextField wp;//����
	JTextField sp;//������
	JTextField r;//��
	JLabel k;//����
	JButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
	JButton a1,a2,a3,a4,a5,a6,a7,a8,a9;
	JButton st;//�߸� ����

public CardSelect()
   {
	back=Toolkit.getDefaultToolkit().getImage("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\��.jpg");
	
	b1=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\������.jpg"));
	b2=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\���޼�.jpg"));
	b3=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\���߱�.jpg"));
	b4=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\������.jpg"));
	b5=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\�Źξ�.jpg"));
	b6=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\������.jpg"));
	b7=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\������.jpg"));
	b8=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\�Źξ�.jpg"));
	b9=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\������.jpg"));
	
	a1=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\���.jpg"));
	a2=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\�յ���.jpg"));
	a3=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\��.jpg"));
	a4=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\Ȱ.jpg"));
	a5=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\��������.jpg"));
	a6=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\������.jpg"));
	a7=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\���嵵.jpg"));
	a8=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\��������.jpg"));
	a9=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\������Ʈ�̹���\\��������.jpg"));
	
	r=new JTextField("��𿡼�");
	sp=new JTextField("����");
	wp=new JTextField("��������");
	k=new JLabel("�׿���!!",JLabel.RIGHT);
	st=new JButton("�߸� ����");
	
	
	JPanel p=new JPanel();
	p.setLayout(new GridLayout(1,9,5,5));
	p.add(b1);
	p.add(b2);
	p.add(b3);
	p.add(b4);
	p.add(b5);
	p.add(b6);
	p.add(b7);
	p.add(b8);
	p.add(b9);
	p.setOpaque(false);//��� �����ϰ�
	
	JPanel p1=new JPanel();
	p1.setLayout(new GridLayout(1,9,5,5));//�� �� ���� ����
	p1.add(a1);
	p1.add(a2);
	p1.add(a3);
	p1.add(a4);
	p1.add(a5);
	p1.add(a6);
	p1.add(a7);
	p1.add(a8);
	p1.add(a9);
	p1.setOpaque(false);//��� �����ϰ�
	
	
	
	JPanel p2=new JPanel();
	p2.setLayout(new GridLayout(1,4,5,5));
	p2.add(r);
	p2.add(sp);
	p2.add(wp);
	p2.add(k);
	p2.add(st);
	p2.setOpaque(false);
	//st.addActionListener(this);
	
	setLayout(null);
	p.setBounds(10, 15, 900, 150);//������ 10(����),15(����)
	p1.setBounds(10, 200, 900, 150);//������ 10(����),250(����)
	p2.setBounds(10,450, 600, 150);;
	add(p);
	add(p1);
	add(p2);
	add(st);
    }

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	/*if(e.getSource()==)
	{
		
	}
	*/
}


}
