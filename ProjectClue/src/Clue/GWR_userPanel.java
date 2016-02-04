package Clue;
import java.awt.*;
import javax.swing.*;
public class GWR_userPanel extends JPanel{

	Image panelImage;
	Image qcard;
	
	public GWR_userPanel() {
		
		panelImage=Toolkit.getDefaultToolkit().getImage("image/cardback.jpg");
		qcard=Toolkit.getDefaultToolkit().getImage("image/qcard1.png");
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(panelImage, 0,0, getWidth(), getHeight(), this);
		g.drawImage(qcard, 20, 25, 171, 250, this);
	}

	
}
	

