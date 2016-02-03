package Clue;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CardSelect extends JPanel implements ActionListener{
	Image back;
	JTextField wp;//무기
	JTextField sp;//용의자
	JTextField r;//방
	JLabel k;//죽임
	JButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
	JButton a1,a2,a3,a4,a5,a6,a7,a8,a9;
	JButton st;//추리 제안

public CardSelect()
   {
	back=Toolkit.getDefaultToolkit().getImage("C:\\Users\\sist\\Documents\\프로젝트이미지\\폐가.jpg");
	
	b1=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\프로젝트이미지\\강동원.jpg"));
	b2=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\프로젝트이미지\\오달수.jpg"));
	b3=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\프로젝트이미지\\송중기.jpg"));
	b4=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\프로젝트이미지\\김혜수.jpg"));
	b5=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\프로젝트이미지\\신민아.jpg"));
	b6=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\프로젝트이미지\\고현정.jpg"));
	b7=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\프로젝트이미지\\김혜수.jpg"));
	b8=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\프로젝트이미지\\신민아.jpg"));
	b9=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\프로젝트이미지\\고현정.jpg"));
	
	a1=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\프로젝트이미지\\곡갱이.jpg"));
	a2=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\프로젝트이미지\\손도끼.jpg"));
	a3=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\프로젝트이미지\\독.jpg"));
	a4=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\프로젝트이미지\\활.jpg"));
	a5=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\프로젝트이미지\\부지깽이.jpg"));
	a6=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\프로젝트이미지\\몽둥이.jpg"));
	a7=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\프로젝트이미지\\은장도.jpg"));
	a8=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\프로젝트이미지\\절구공이.jpg"));
	a9=new JButton(new ImageIcon("C:\\Users\\sist\\Documents\\프로젝트이미지\\절구공이.jpg"));
	
	r=new JTextField("어디에서");
	sp=new JTextField("누가");
	wp=new JTextField("무엇으로");
	k=new JLabel("죽였다!!",JLabel.RIGHT);
	st=new JButton("추리 제안");
	
	
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
	p.setOpaque(false);//배경 투명하게
	
	JPanel p1=new JPanel();
	p1.setLayout(new GridLayout(1,9,5,5));//행 열 간격 간격
	p1.add(a1);
	p1.add(a2);
	p1.add(a3);
	p1.add(a4);
	p1.add(a5);
	p1.add(a6);
	p1.add(a7);
	p1.add(a8);
	p1.add(a9);
	p1.setOpaque(false);//배경 투명하게
	
	
	
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
	p.setBounds(10, 15, 900, 150);//시작점 10(가로),15(세로)
	p1.setBounds(10, 200, 900, 150);//시작점 10(가로),250(세로)
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
