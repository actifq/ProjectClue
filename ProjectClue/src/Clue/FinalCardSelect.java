package Clue; 
import java.awt.*; 
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import java.awt.event.*; 
 
public class FinalCardSelect extends JPanel implements ActionListener{ 
   Image back; 
   JLabel wp;//무기 
   JLabel sp;//용의자 
   JLabel r;//방 
   JLabel k;//죽임 
   JButton[] p= new JButton[9]; 
   JButton[] q=new JButton[9];  
   JButton[] j=new JButton[9]; 
   JButton st;//추리 제안 

	public FinalCardSelect() 
	   { 
	   back=Toolkit.getDefaultToolkit().getImage("image/gwrback1.jpg"); 
	    
	   for(int i=0;i<9;i++)
	   {
		   p[i]=new JButton();
		   if(i>=6 && i<9)
		   {
			   p[i].setBorderPainted(false);
			   p[i].setContentAreaFilled(false);
			   p[i].setFocusPainted(false);
		   }
	   }
		   
	   JPanel p1=new JPanel();
		p1.setLayout(new GridLayout(1,9,5,5));
		for(int i=0;i<p.length;i++)
		{
			p1.add(p[i]);
		}
		   
		p1.setOpaque(false);//배경 투명하게
		
		
		JPanel p2=new JPanel();
		p2.setLayout(new GridLayout(1,9,5,5));
		for(int i=0;i<q.length;i++)
		{
			q[i]=new JButton();
			
			if(i>=8 && i<9)
			{
				q[i].setBorderPainted(false);
				q[i].setContentAreaFilled(false);
				q[i].setFocusPainted(false);
			}
			p2.add(q[i]);
		}
		p2.setOpaque(false);
		
		JPanel p3=new JPanel();
		p3.setLayout(new GridLayout(1,9,5,5));
		for(int i=0;i<j.length;i++)
		{
			j[i]=new JButton();
			p3.add(j[i]);
			
		}
		p3.setOpaque(false);
	    
	   r=new JLabel("어디에서", JLabel.CENTER);
	   r.setBorder(new BevelBorder(WHEN_FOCUSED));
	   sp=new JLabel("누가", JLabel.CENTER); 
	   sp.setBorder(new BevelBorder(WHEN_FOCUSED));
	   wp=new JLabel("무엇으로", JLabel.CENTER); 
	   wp.setBorder(new BevelBorder(WHEN_FOCUSED));
	   
	   
	   k=new JLabel("죽였다!!", JLabel.RIGHT);
	   k.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	   st=new JButton("추리 제안"); 
	    
	   JPanel p4=new JPanel(); 
	   p4.setLayout(new GridLayout(1,4,5,5)); 
	   p4.add(r); 
	   p4.add(sp); 
	   p4.add(wp); 
	   p4.add(k); 
	   p4.setOpaque(false); 
	   //st.addActionListener(this); 
	    
	   JPanel p5=new JPanel(); 
	   p5.setLayout(new GridLayout(1,1,100,100)); 
	   p5.add(st); 
	     
	   setLayout(null); 
	   p1.setBounds(10,15,900,120);//시작점 10(가로),15(세로) 
	   p2.setBounds(10,150,900,120);//시작점 10(가로),250(세로) 
	   p3.setBounds(10,285,900,120); 
	   p4.setBounds(10,450,600,150); 
	   p5.setBounds(710,500,200,100); 
	    
	   add(p1); 
	   add(p2); 
	   add(p3); 
	   add(p4); 
	   add(p5); 
     } 
	
	public void setCardImg()
	{
		for(int i=0;i<p.length;i++)
		{
			p[i].setIcon(new ImageIcon(setImage("image/player/char"+i+".jpg",p[0].getWidth(),p[i].getHeight())));
			q[i].setIcon(new ImageIcon(setImage("image/weapon/wp"+i+".jpg",q[0].getWidth(),q[i].getHeight())));
		    j[i].setIcon(new ImageIcon(setImage("image/room/room"+i+".jpg",j[0].getWidth(),j[i].getHeight())));
		}
		
	}

	public Image setImage(String filename, int width, int height){
			ImageIcon i = new ImageIcon(filename);
			Image image=i.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
			return image;
     }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==j)
		{
			for(int i=0;i<p.length;i++)
			{
				r=setIcon(new ImageIcon(setImage("image/room/room"+i+".jpg",j[0].getWidth(),j[i].getHeight())));
			}
		}
	}
}