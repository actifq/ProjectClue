package Clue;

import java.awt.*;
import javax.swing.*;

public class GameNote_js extends JPanel{
	JPanel jpNote1,jpNote2,jpNote3;
	
	public GameNote_js(){
		
		jpNote1=new GameNote();
		
		JScrollPane js1=new JScrollPane(jpNote1, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js1.setPreferredSize(new Dimension(320, 186));
		add(js1);
		
	}
}
