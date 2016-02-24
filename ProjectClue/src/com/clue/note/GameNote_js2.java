package com.clue.note;

import java.awt.*;
import javax.swing.*;

public class GameNote_js2 extends JPanel{
	JPanel jpNote1,jpNote2,jpNote3;
	
	public GameNote_js2(){
		
		jpNote2=new GameNote2();
		
		JScrollPane js2=new JScrollPane(jpNote2, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js2.setPreferredSize(new Dimension(320, 186));
		add(js2);
		
	}
}

