package Clue;
import java.awt.*;//Layout
import javax.swing.*;//Window
public class MainForm extends JFrame{
	CardLayout card=new CardLayout();
	CardSelect cs=new CardSelect();
	
	public MainForm()
	{
		setLayout(card);
		add("CS",cs);
		setSize(950,650);
		setVisible(true);
		setResizable(false);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainForm mf=new MainForm();
	}

}
