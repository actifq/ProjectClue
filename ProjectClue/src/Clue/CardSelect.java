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
   back=Toolkit.getDefaultToolkit().getImage("image/gwrback1.jpg");
   
   
   for(int i=0;i<6;i++)
   {
      p[i]=new JButton(new ImageIcon("image/player/char"+i+".jpg"));
      
   }
   for(int i=6;i<9;i++)
   {
      p[i]=new JButton(new ImageIcon("image/player/qcard1.png"));
   }
   
   
   for(int i=0;i<8;i++)
   {
      q[i]=new JButton(new ImageIcon("image/weapon/wp"+i+".jpg"));
      
   }
   for(int i=8;i<9;i++)
   {
      q[i]=new JButton(new ImageIcon("image/player/qcard1.png"));
   }
   
   
   for(int i=0;i<9;i++)
   {
      j[i]=new JButton(new ImageIcon("image/player/qcard1.png"));
   }
   
   
   r=new JTextField("��𿡼�");
   sp=new JTextField("����");
   wp=new JTextField("��������");
   k=new JLabel("�׿���!!",JLabel.RIGHT);
   st=new JButton("�߸� ����");
   
   
   JPanel p1=new JPanel();
   p1.setLayout(new GridLayout(1,9,5,5));
   for(int i=0;i<9;i++){
      p1.add(p[i]);
   }
   p1.setOpaque(false);//��� �����ϰ�
   
   JPanel p2=new JPanel();
   p2.setLayout(new GridLayout(1,9,5,5));//�� �� ���� ����
   for(int i=0;i<9;i++){
      p2.add(q[i]);
   }
   p2.setOpaque(false);//��� �����ϰ�
   
   JPanel p3=new JPanel();
   p3.setLayout(new GridLayout(1,9,5,5));//�� �� ���� ����
   /*p2.add(j[0]);
   p2.add(j[1]);
   p2.add(j[2]);
   p2.add(j[3]);
   p2.add(j[4]);
   p2.add(j[5]);
   p2.add(j[6]);
   p2.add(j[7]);
   p2.add(j[8]);*/
   for(int i=0;i<9;i++){
      p3.add(j[i]);
   }
   p3.setOpaque(false);//��� �����ϰ�
   
   
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





}