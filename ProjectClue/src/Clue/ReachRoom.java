package Clue;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ReachRoom extends JFrame {
	JLabel la1;
	JButton b1,b2;
	
	public ReachRoom(){
		la1 = new JLabel("방이름");
		setLayout(null);
		la1.setBounds(50,15,200,30);
		b1=new JButton("중간추리");
		b2=new JButton("최종추리");
		JPanel p = new JPanel();
		p.add(b1);
		p.add(b2);
		p.setBounds(10,155,195,35);
		
		add(la1);
		add(p);
		
		setSize(230,235);
	}
	
	public static void main(String[] args){
		new ReachRoom();
	}
	

}
