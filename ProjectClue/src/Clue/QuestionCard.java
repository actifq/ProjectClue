package Clue;
import java.awt.*;
import javax.swing.*;
public class QuestionCard extends JPanel{
	Image qcard;
	
	public QuestionCard() {
		qcard=Toolkit.getDefaultToolkit().getImage("image/qcard1.png");
		
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(qcard, 0, 0, getWidth(),getHeight(),this);
	}
}
