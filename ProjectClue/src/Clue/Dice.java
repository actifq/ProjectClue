package Clue;
import java.util.Random;
import java.awt.*;
import javax.swing.*;

public class Dice extends JPanel{
	
	private Random random;
	private int dice1;
	private int dice2;
	
	public Dice() 
	{
		/*setSize(900,900);
		setVisible(true);*/
		random= new Random(System.currentTimeMillis());
		dice1=1;
		dice2=1;
		
		//setLayout(null);
		
		dice1=random.nextInt(6)+1;
		dice2=random.nextInt(6)+1;
		
	}
	
	
	
	public int Roll()
	{
		dice1=random.nextInt(5)+1;
		dice2=random.nextInt(5)+1;
		System.out.println(dice1);
		System.out.println(dice2);
		
		return getValue();
	}

	public int getValue() 
	{
		return dice1+dice2;
	}
	
	public String getDiceImage1()
	{
		return "image/dice/d" + dice1+ ".png";
	}
	
	public String getDiceImage2()
	{
		return "image/dice/d" + dice2+ ".png";
	}
	


	/*public static void main(String[] args) {
		Dice dc=new Dice();
		int a= dc.Roll();
		
		String b=dc.getDiceImage1();
		String c=dc.getDiceImage2();
		
	}*/




}
