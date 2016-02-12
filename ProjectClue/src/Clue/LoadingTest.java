package Clue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoadingTest extends JPanel implements Runnable{
	JProgressBar prg;
	Image back;
	int i=0,num=0;  
	ClueMain cm;
	public LoadingTest(ClueMain cm) {
		this.cm=cm;
		back=Toolkit.getDefaultToolkit().getImage("image/loadingBack.gif");
		
		prg=new JProgressBar(0,100);
		prg.setValue(0);  
		prg.setStringPainted(true);  
	      
		setLayout(null);
		prg.setBounds(300,500,500,30);
		add(prg);  
		new Thread(this).start();
		
	}
	

	public void iterate(){  
		while(i<=100){  
		  prg.setValue(i);  
		  i=i+1; 
		  if(i==100)
		  {
			  cm.card.show(cm.getContentPane(), "MS");
		  }
		  try{Thread.sleep(40);}catch(Exception e){}  
		}  
		}  
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(back, 0, 0, getWidth(),getHeight(), this);
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		iterate();
	}
}
