package com.clue;

import java.awt.*;


import javax.swing.*;


public class GMS_JP_LogoSet extends JPanel{
   Image jpLogo;
   JPanel[] playerImg=new JPanel[4];
   JLabel[] playerNo=new JLabel[4];
   
   
   public GMS_JP_LogoSet()
   {
      setLayout(null);
               
         for(int i=0;i<4;i++){
            playerImg[i]=new JPanel();
           playerImg[i].setBounds(80+(i*210), 0, 130, 90);
           playerImg[i].setBackground(Color.pink);
           playerNo[i]=new JLabel((i+1)+"P");
           playerNo[i].setBounds(0+(i*210), 0, 80, 90);
           add(playerImg[i]);
           add(playerNo[i]);
           
         }
        // playerImg[0].add(new JLabel(new ImageIcon(setImage("image/player/schar0.jpg", 130, 90))));
         
   }
   public void setImage(int p1,int p2, int p3, int p4){
      for(int i=0;i<4;i++)
      playerImg[i].removeAll();
      
      playerImg[0].add(new JLabel(new ImageIcon(setImage("image/player/schar"+(p1-1)+".jpg", 130, 90))));
      playerImg[1].add(new JLabel(new ImageIcon(setImage("image/player/schar"+(p2-1)+".jpg", 130, 90))));
      playerImg[2].add(new JLabel(new ImageIcon(setImage("image/player/schar"+(p3-1)+".jpg", 130, 90))));
      playerImg[3].add(new JLabel(new ImageIcon(setImage("image/player/schar"+(p4-1)+".jpg", 130, 90))));
      for(int i=0;i<4;i++)
      playerImg[i].validate();//panelÀç¹èÄ¡
   }

  /* 
   public static void  main(String[] args){
      JFrame fr = new JFrame();
      LogoSet sp=new LogoSet();
      fr.setSize(900, 150);
      
      fr.add(sp);
      fr.setVisible(true);
      sp.setImage(1,2,3,4);
      
      
   }*/
   //gwr.aa[pNum].add(new JLabel(new ImageIcon(setImage("image/player/char"+(i-1)+".jpg", 171, 250))));
   
   private Image setImage(String filename, int width, int height) {
      // TODO Auto-generated method stub
      ImageIcon ii = new ImageIcon(filename);
      Image image = ii.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
      return image;
      //return null;
   }
   
   

}