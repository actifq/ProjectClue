package com.sist.note;

import java.awt.*;
import javax.swing.*;

public class GameNote2 extends JPanel{
	JPanel jpNote1,jpNote2,jpNote3;
	JTextField nt1,nt2,nt3,nt4,nt5,nt6,
				nt7,nt8,nt9,nt10,nt11,nt12,nt13,nt14,nt15,nt16,nt17,nt18;
	JCheckBox wp1,wp2,wp3,wp4,wp5,wp6,wp7,wp8;
	
	public GameNote2() {
		
		JLabel lb3 = new JLabel(" 무기");
		JLabel lb4 = new JLabel(" ");
		lb3.setFont(new Font("", Font.BOLD, 13));
		wp1=new JCheckBox("활");
		wp2=new JCheckBox("삽");
		wp3=new JCheckBox("비소");
		wp4=new JCheckBox("은장도");
		wp5=new JCheckBox("활");
		wp6=new JCheckBox("삽");
		wp7=new JCheckBox("비소");
		wp8=new JCheckBox("은장도");
		nt7=new JTextField();
		nt8=new JTextField();
		nt9=new JTextField();
		nt10=new JTextField();
		nt11=new JTextField();
		nt12=new JTextField();
		nt13=new JTextField();
		nt14=new JTextField();
		
		setLayout(new GridLayout(10,0,0,2));
		
		add(lb3); add(lb4);
		add(wp1);add(nt7);
		add(wp2);add(nt8);
		add(wp3); add(nt9);
		add(wp4); add(nt10);
		add(wp5); add(nt11);
		add(wp6); add(nt12);
		add(wp7); add(nt13);
		add(wp8); add(nt14);
		
	}
}
