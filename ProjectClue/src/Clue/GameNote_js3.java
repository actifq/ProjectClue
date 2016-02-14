package Clue;

import java.awt.*;
import javax.swing.*;

public class GameNote_js3 extends JPanel{
	JPanel jpNote1,jpNote2,jpNote3;
	
	public GameNote_js3(){
		
		
		jpNote3=new GameNote1();

		JScrollPane js3=new JScrollPane(jpNote3, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js3.setPreferredSize(new Dimension(320, 186));
		add(js3);
		
		
	}
}

