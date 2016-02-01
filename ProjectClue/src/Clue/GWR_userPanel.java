package Clue;
import java.awt.*;
import javax.swing.*;
public class GWR_userPanel extends JPanel{

	Image panelImg;
	
	public GWR_userPanel() {
		panelImg=Toolkit.getDefaultToolkit().getImage("image/cardback1.jpg");
		
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(panelImg, 0,0, getWidth(), getHeight(), this);
	}

	
}
	

