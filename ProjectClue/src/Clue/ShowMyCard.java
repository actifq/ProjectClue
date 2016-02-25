package Clue;
import java.awt.*;
import javax.swing.*;

public class ShowMyCard extends JPanel{
   JButton[] jb=new JButton[5];
   
   public ShowMyCard()
   {
      for(int i=0;i<5;i++)
         jb[i]=new JButton();
      
      
      JPanel jp=new JPanel();
         jp.setLayout(new GridLayout(1,5,5,5));//행 열 간격 간격
         for(int i=0;i<5;i++){
            jp.add(jb[i]);
         }
         
      setLayout(null);
      jp.setBounds(0,0,575,185);
      add(jp);   
      //jb[0]=new JButton(new ImageIcon("image/player/char0.jpg"));
     // jb[1].setIcon(new ImageIcon("image/player/char0.jpg"));
      
   }
   
   public void setCardImg(int[] arr){
	   
	   
	   for(int i=0;i<arr.length;i++)
	   {
		   if(arr[i]<6) //캐릭터
			   jb[i].setIcon(new ImageIcon(setImage("image/player/char"+arr[i]+".jpg",jb[0].getWidth(),jb[0].getHeight())));
		   else if(arr[i]>=6 && arr[i]<14)	//무기
			   jb[i].setIcon(new ImageIcon(setImage("image/weapon/wp"+(arr[i]-6)+".jpg",jb[0].getWidth(),jb[0].getHeight())));
		   else	//장소
			   jb[i].setIcon(new ImageIcon(setImage("image/room/room"+(arr[i]-14)+".jpg",jb[0].getWidth(),jb[0].getHeight())));
			  // jb[i].setIcon(new ImageIcon("image/room/room"+(arr[i]-14)+".jpg"));
	      
	   }
   }
   
   public Image setImage(String filename, int width, int height){
		ImageIcon ii = new ImageIcon(filename);
		Image image=ii.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
		return image;
	}
   
   
}