package Clue;

import java.awt.*;
import javax.swing.*;


public class GameNote extends JPanel{
	JPanel jpNote1;
	JTextField[] nt=new JTextField[23];
	//JTextField nt1,nt2;
	JCheckBox[] cb=new JCheckBox[23];
	
	public GameNote() {
		
			JLabel lb1 = new JLabel("������");
			JLabel lb2 = new JLabel("����");
			JLabel lb3 = new JLabel("���");
			//lb1.setFont(new Font("���� ���", Font.BOLD, 11));
			cb[0]=new JCheckBox("������");
			cb[1]=new JCheckBox("���¹�");
			cb[2]=new JCheckBox("���޼�");
			cb[3]=new JCheckBox("�Źξ�");
			cb[4]=new JCheckBox("���ر�");
			cb[5]=new JCheckBox("������");
			cb[6]=new JCheckBox("Ȱ  ");
			cb[7]=new JCheckBox("��  ");
			cb[8]=new JCheckBox("��� ");
			cb[9]=new JCheckBox("���嵵");
			cb[10]=new JCheckBox("���� ");
			cb[11]=new JCheckBox("��������");
			cb[12]=new JCheckBox("��������");
			cb[13]=new JCheckBox("���");
			cb[14]=new JCheckBox("�ް� ");
			cb[15]=new JCheckBox("�갣 ");
			cb[16]=new JCheckBox("��ä ");
			cb[17]=new JCheckBox("�Թ� ");
			cb[18]=new JCheckBox("�򸶷� ");
			cb[19]=new JCheckBox("�ξ� ");
			cb[20]=new JCheckBox("���� ");
			cb[21]=new JCheckBox("���� ");
			cb[22]=new JCheckBox("����� ");
			
			//JScrollPane js1=new JScrollPane(jpNote1);
			//js1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			//js1.setSize(new Dimension(320,570));
			
			//��ġ
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
