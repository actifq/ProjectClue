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
<<<<<<< HEAD
		back=Toolkit.getDefaultToolkit().getImage("image/loadingBack.gif");
=======

		back=Toolkit.getDefaultToolkit().getImage("image/back/loadingbr.gif");
>>>>>>> refs/remotes/origin/kyung-eun
		
		prg=new JProgressBar(0,100);
		prg.setValue(0);  
		prg.setStringPainted(true);  
	      
		setLayout(null);
		prg.setBounds(100,800,1000,40);
		add(prg);  
<<<<<<< HEAD
		
		
=======

>>>>>>> refs/remotes/origin/kyung-eun
	}
	
	

	public void iterate(){  
		while(i<=100){  
		  prg.setValue(i);  
		  i=i+1; 
		  if(i==100)
		  {
			  cm.card.show(cm.getContentPane(), "MS");
			  cm.mainScreen.gameStart();
		  }
<<<<<<< HEAD
		  try{Thread.sleep(40);}catch(Exception e){}  
=======
		  try{Thread.sleep(55);}catch(Exception e){}  

>>>>>>> refs/remotes/origin/kyung-eun
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
