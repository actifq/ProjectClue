package Clue;
import java.awt.*;
import javax.swing.*;

public class ShowMyCard extends JPanel{
   JButton[] jb=new JButton[5];
   
   public ShowMyCard()
   {
      for(int i=0;i<5;i++)
         jb[i]=new JButton(new ImageIcon(""));
      
      
      JPanel jp=new JPanel();
         jp.setLayout(new GridLayout(1,5,5,5));//행 열 간격 간격
         for(int i=0;i<5;i++){
            jp.add(jb[i]);
         }
         
      setLayout(null);
      jp.setBounds(0,0,575,185);
      add(jp);   
         
   }
   
   
}