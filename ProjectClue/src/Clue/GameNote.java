package Clue;

import java.awt.*;
import javax.swing.*;


public class GameNote extends JPanel{
	JPanel jpNote1;
	JTextField[] nt=new JTextField[23];
	//JTextField nt1,nt2;
	JCheckBox[] cb=new JCheckBox[23];
	
	public GameNote() {
		
			JLabel lb1 = new JLabel("용의자");
			JLabel lb2 = new JLabel("무기");
			JLabel lb3 = new JLabel("장소");
			//lb1.setFont(new Font("맑은 고딕", Font.BOLD, 11));
			cb[0]=new JCheckBox("고현정");
			cb[1]=new JCheckBox("길태미");
			cb[2]=new JCheckBox("오달수");
			cb[3]=new JCheckBox("신민아");
			cb[4]=new JCheckBox("이준기");
			cb[5]=new JCheckBox("김혜수");
			cb[6]=new JCheckBox("활  ");
			cb[7]=new JCheckBox("삽  ");
			cb[8]=new JCheckBox("비소 ");
			cb[9]=new JCheckBox("은장도");
			cb[10]=new JCheckBox("밧줄 ");
			cb[11]=new JCheckBox("부지깽이");
			cb[12]=new JCheckBox("절구공이");
			cb[13]=new JCheckBox("곡괭이");
			cb[14]=new JCheckBox("뒷간 ");
			cb[15]=new JCheckBox("헛간 ");
			cb[16]=new JCheckBox("안채 ");
			cb[17]=new JCheckBox("텃밭 ");
			cb[18]=new JCheckBox("툇마루 ");
			cb[19]=new JCheckBox("부엌 ");
			cb[20]=new JCheckBox("곳간 ");
			cb[21]=new JCheckBox("서재 ");
			cb[22]=new JCheckBox("사랑방 ");
			
			//JScrollPane js1=new JScrollPane(jpNote1);
			//js1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			//js1.setSize(new Dimension(320,570));
			
			//배치
			//lb1.setBounds(870, 115, 50, 30);
			//js1.setBounds(865, 105, 320, 570);
			/*js2.setBounds(865, 295, 320, 185);
			js3.setBounds(865, 485, 320, 185);*/
			
			//add(js1);
			/*add(js2);
			add(js3);*/
			
			setLayout(null);
			for(int i=0;i<cb.length;i++){
				if(i<6){
					lb1.setBounds(870, 105, 30, 10);
					add(lb1);
					//cb[i].setBounds(870, 115+(i*10) , 30, 10);
					add(cb[i]);
					nt[i]=new JTextField(20);
					//nt[i].setBounds(870, 115+(10*i), 50, 10);
					add(nt[i]);
				}else if(i<14){
					lb2.setBounds(870, 105, 30, 10);
					add(lb2);
					//cb[i].setBounds(870, 115, 50, 30);
					add(cb[i]);
					nt[i]=new JTextField(20);
					//nt[i].setBounds(870, 115+(10*i), 50, 10);
					add(nt[i]);
				}else{
					lb3.setBounds(870, 105, 30, 10);
					add(lb3);
					//cb[i].setBounds(870, 115, 50, 30);
					add(cb[i]);
					nt[i]=new JTextField(20);
					//nt[i].setBounds(870, 115+(10*i), 50, 10);
					add(nt[i]);
				}
			}
			
	}
}
