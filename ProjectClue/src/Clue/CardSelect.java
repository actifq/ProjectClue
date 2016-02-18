package Clue; 
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
 
public class CardSelect extends JPanel{ 
   Image back; 
   JTextField wp;//���� 
   JTextField sp;//������ 
   JTextField r;//�� 
   JLabel k;//���� 
   JButton[] p= new JButton[9]; 
   JButton[] q=new JButton[9];  
   JButton[] j=new JButton[9]; 
   JButton st;//�߸� ���� 

public CardSelect()
   {
	
   back=Toolkit.getDefaultToolkit().getImage("image/back/gwrback1.jpg");
   
   JPanel p1=new JPanel();
   p1.setLayout(new GridLayout(1,9,5,5));
   for(int i=0;i<p.length;i++){
	   p[i]=new JButton();
	   p1.add(p[i]);
   }
   
   p1.setOpaque(false);//��� �����ϰ�
   
   JPanel p2=new JPanel();
   p2.setLayout(new GridLayout(1,9,5,5));//�� �� ���� ����
   for(int i=0;i<q.length;i++){
	   q[i]=new JButton();
	   p2.add(q[i]);
   }
   p2.setOpaque(false);//��� �����ϰ�
   
   JPanel p3=new JPanel();
   p3.setLayout(new GridLayout(1,9,5,5));//�� �� ���� ����
   for(int i=0;i<j.length;i++){
	   j[i]=new JButton();
	   p3.add(j[i]);
   }
   p3.setOpaque(false);//��� �����ϰ�
  
   
   r=new JTextField("��𿡼�");
   sp=new JTextField("����");
   wp=new JTextField("��������");
   k=new JLabel("�׿���!!",JLabel.RIGHT);
   st=new JButton("�߸� ����");
   
  
   
   
   JPanel p4=new JPanel();
   p4.setLayout(new GridLayout(1,4,5,5));
   p4.add(r);
   p4.add(sp);
   p4.add(wp);
   p4.add(k);
   p4.setOpaque(false);
   
   
   JPanel p5=new JPanel();
   p5.setLayout(new GridLayout(1,1,100,100));
   p5.add(st);
   
   setLayout(null);
   p1.setBounds(10,15,900,120);//������ 10(����),15(����)
   p2.setBounds(10,150,900,120);//������ 10(����),250(����)
   p3.setBounds(10,285,900,120);
   p4.setBounds(10,450,600,150);
   p5.setBounds(710,500,200,100);
   
   add(p1);
   add(p2);
   add(p3);
   add(p4);
   add(p5);
   
   
    }
public void setCardImg(){
	   
	   
	   for(int i=0;i<p.length;i++)
	   {
		   
		   p[i].setIcon(new ImageIcon(setImage("image/player/char"+i+".jpg",p[0].getWidth(),p[0].getHeight())));
		   q[i].setIcon(new ImageIcon(setImage("image/weapon/wp"+i+".jpg",q[0].getWidth(),q[0].getHeight())));
		   j[i].setIcon(new ImageIcon(setImage("image/room/room"+i+".jpg",j[0].getWidth(),j[0].getHeight())));
			   
		  
	   }
}


	
	public Image setImage(String filename, int width, int height){
		ImageIcon ii = new ImageIcon(filename);
		Image image=ii.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
		return image;
	}
}