package Clue;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CardSelect extends JPanel{
   Image back;
   JLabel[] tfGuess=new JLabel[3];//무기
   JLabel[] info=new JLabel[3];//무기

   JLabel k,nPl;//죽임
   JButton[] p= new JButton[6];
   JButton[] q=new JButton[8]; 
   JButton[] j=new JButton[9];
   JPanel pl;
   JPanel[] guess= new JPanel[3];
   JButton st;//추리 제안

public CardSelect()
   {

   back=Toolkit.getDefaultToolkit().getImage("image/back/gwrback1.jpg");
   
   JPanel p1=new JPanel();
   p1.setLayout(new GridLayout(1,6,5,5));
   for(int i=0;i<p.length;i++){
	   p[i]=new JButton();
	   p1.add(p[i]);
   }
   
   p1.setOpaque(false);//배경 투명하게
   
   JPanel p2=new JPanel();
   p2.setLayout(new GridLayout(1,8,5,5));//행 열 간격 간격
   for(int i=0;i<q.length;i++){
	   q[i]=new JButton();
	   p2.add(q[i]);
   }
   p2.setOpaque(false);//배경 투명하게
   
   JPanel p3=new JPanel();
   p3.setLayout(new GridLayout(1,9,5,5));//행 열 간격 간격
   for(int i=0;i<j.length;i++){
	   j[i]=new JButton();
	   p3.add(j[i]);
   }
   p3.setOpaque(false);//배경 투명하게
  
   
   tfGuess[0]=new JLabel("어디에서",JLabel.CENTER);
   tfGuess[1]=new JLabel("누가",JLabel.CENTER);
   tfGuess[2]=new JLabel("무엇으로",JLabel.CENTER);
   
   info[0]=new JLabel("",JLabel.CENTER);
   info[1]=new JLabel("",JLabel.CENTER);
   info[2]=new JLabel("",JLabel.CENTER);
   
   k=new JLabel("죽였다!!",JLabel.RIGHT);
   st=new JButton("추리 제안");
   
   for(int i=0;i<3;i++){
	   guess[i]=new JPanel();
	   guess[i].setBounds(50+(i*210), 550, 180, 250);
	   guess[i].setLayout(new BorderLayout());
	   guess[i].add(new JLabel(new ImageIcon(setImage("image/back/cardback.jpg", guess[i].getWidth(), guess[i].getHeight()))));
	   info[i].setBounds(50+(i*210),520,180,30);
	   info[i].setBackground(Color.BLACK);
	   info[i].setForeground(Color.GREEN);
	   tfGuess[i].setBounds(50+(i*210), 800, 180, 30);
	   tfGuess[i].setBackground(Color.YELLOW);
	   tfGuess[i].setForeground(Color.BLACK);
   }
   pl= new JPanel();
   pl.setBounds(920, 15, 250, 350);
   pl.setLayout(new BorderLayout());
   pl.add(new JLabel(new ImageIcon(setImage("image/back/cardback.jpg", pl.getWidth(), pl.getHeight()))));
   
   nPl=new JLabel("...추리중",JLabel.CENTER);
   nPl.setFont(new Font("맑은 고딕",Font.BOLD,25));
   nPl.setBounds(920,365,250,30);
   
   JPanel p4=new JPanel();
   p4.setLayout(new GridLayout(1,4,5,5));

   p4.add(k);
   p4.setOpaque(false);
   
   
   JPanel p5=new JPanel();
   p5.setLayout(new GridLayout(1,1,100,100));
   p5.add(st);
   
   setLayout(null);
   p1.setBounds(10,15,600,120);//시작점 10(가로),15(세로)
   p2.setBounds(10,150,800,120);//시작점 10(가로),250(세로)
   p3.setBounds(10,285,900,120);
   p4.setBounds(10,550,600,150);
   p5.setBounds(710,600,200,100);
   
   add(p1);
   add(p2);
   add(pl);
 //  add(p3);
   //add(p4);
   add(guess[0]);
   add(guess[1]);
   add(guess[2]);
   add(tfGuess[0]);
   add(tfGuess[1]);
   add(tfGuess[2]);
   add(p5);
   add(nPl);
   
   
   
    }
public void setCardImg(){
	   
	   
	   for(int i=0;i<p.length;i++)
	   {
		   p[i].setIcon(new ImageIcon(setImage("image/player/char"+i+".jpg",p[0].getWidth(),p[0].getHeight())));
	   }
	   for(int i=0;i<q.length;i++)
	   {
		   q[i].setIcon(new ImageIcon(setImage("image/weapon/wp"+i+".jpg",q[0].getWidth(),q[0].getHeight())));
		  
	   }
	   for(int i=0;i<j.length;i++)
	   {   
		   j[i].setIcon(new ImageIcon(setImage("image/room/room"+i+".jpg",j[0].getWidth(),j[0].getHeight())));
			  
	   }
	   
}

	
	public Image setImage(String filename, int width, int height){
		ImageIcon ii = new ImageIcon(filename);
		Image image=ii.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
		return image;
	}

}