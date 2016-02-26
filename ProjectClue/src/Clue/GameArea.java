package Clue;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class GameArea extends JPanel{

	Image backImg;
	Image[] horse= new Image[4];
	final int DFT_X=248;
	final int DFT_Y=108;
	final int BLOCK_NUM=13;
	final int BLOCK_WIDTH=27;
	JPanel jpHint, jpWrong, jpCorrect ;
	JPanel[] hint=new JPanel[3];
	JPanel wrong;
	
	JLabel GameMsg,wrongMsg,corrMsg;
	JButton b1;
	int[][] pXY={{6,6},
				{6,6},
				{6,6},
				{6,6}};
	
	

	public GameArea(){


		backImg=Toolkit.getDefaultToolkit().getImage("image/back/boardBack.png");
		for(int i=0;i<4;i++){
			
		
		horse[i]=Toolkit.getDefaultToolkit().getImage("image/player/horse"+(i+1)+".png");
		
		}
		// #### ��Ʈ MSG ����	   
		GameMsg=new JLabel("������(1P)���� �� ī���� �ϳ��� ��������ϴ�.");
		GameMsg.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		GameMsg.setFont(new Font("���� ���", Font.BOLD, 25));
		GameMsg.setForeground(Color.CYAN);
		GameMsg.setOpaque(false);
		GameMsg.setBounds(160, 300, 600,50);
		
		//###### �����߸� ���� MSG ����
		wrongMsg=new JLabel("������(1P)�� �����߸� ����");
		wrongMsg.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		wrongMsg.setFont(new Font("���� ���", Font.BOLD, 30));
		wrongMsg.setForeground(Color.RED);
		wrongMsg.setOpaque(false);
		wrongMsg.setBounds(240, 270, 600, 50);
		
	
		setLayout(null);
		
		// #####  ��Ʈ MSG ����ǳ� 
		jpHint = new JPanel();
		jpHint.setLayout(null);
		jpHint.setBackground(Color.BLACK);
		jpHint.add(GameMsg);
		jpHint.setBounds(0, 130, 850, 400);
		add(jpHint);
		jpHint.setVisible(false);
		
		// #####  �����߸� ���� MSG ����ǳ� 
				jpWrong = new JPanel();
				jpWrong.setLayout(null);
				jpWrong.setBackground(Color.BLACK);
				jpWrong.add(wrongMsg);
				jpWrong.setBounds(0, 130, 850, 350);
				add(jpWrong);
				jpWrong.setVisible(false);
		
		b1=new JButton("��������");
		b1.setBounds(720, 270, 100, 80);
		jpHint.add(b1);
		b1.setVisible(false);
		
		
		// ##### ��Ʈ ȭ�� �̹��� �߰��ϱ�
		for(int i=0;i<3;i++){
			   hint[i]=new JPanel();
			   hint[i].setBounds(180+(i*180),50, 140, 190);
			   hint[i].setLayout(new BorderLayout());
			   hint[i].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", hint[i].getWidth(), hint[i].getHeight()))));
			   jpHint.add(hint[i]);
		}
		
		// ##### ��Ʈ ȭ�� �̹��� �߰��ϱ�
		
			   wrong=new JPanel();
			   wrong.setBounds(360, 30, 140, 190);
			   wrong.setLayout(new BorderLayout());
			   wrong.add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", wrong.getWidth(), wrong.getHeight()))));
			   jpWrong.add(wrong);
		

	}
	
	

	public void setpXY(int x,int y) {
		pXY[Game.crrPlayer][0]=x;
		pXY[Game.crrPlayer][1]=y;
	}



	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(backImg, 0, 0, getWidth(), getHeight(),this);
		for(int i=3; i>=0; i--){
			
				//g.drawImage(horse[i], DFT_X+(BLOCK_WIDTH*Game.p[i].crrX), DFT_Y+(BLOCK_WIDTH*Game.p[i].crrY), this);
			g.drawImage(horse[i], DFT_X+(BLOCK_WIDTH*pXY[i][0]), DFT_Y+(BLOCK_WIDTH*pXY[i][1]), this);
			
			
		}
		
	}



	public void setMsgText(int flag,String who,int pp, String r, String p, String w) {
		// TODO Auto-generated method stub
		
		// ### flag 0 => HINT
		// ### flag 1 => Correct Answer
		
		int ir=0,ip=0,iw=0;
		
		for(int i=0; i<RefData.nameRoom.length; i++){
			if(RefData.nameRoom[i].equals(r.trim())){
				ir=i;
				break;
			}
		}
		
		for(int i=0; i<RefData.nameChar.length; i++){
			if(RefData.nameChar[i].equals(p.trim())){
				ip=i;
			}
		}
		
		for(int i=0; i<RefData.nameWp.length; i++){
			if(RefData.nameWp[i].equals(w.trim())){
				iw=i;
			}
		}
		hint[0].removeAll();
		hint[1].removeAll();
		hint[2].removeAll();
		
		hint[0].add(new JLabel(new ImageIcon(setImage("image/room/room"+ir+".jpg", 140,190))));
		hint[1].add(new JLabel(new ImageIcon(setImage("image/player/char"+ip+".jpg", 140,190))));
		hint[2].add(new JLabel(new ImageIcon(setImage("image/weapon/wp"+iw+".jpg", 140,190))));
		
		hint[0].validate();//panel���ġ
		hint[1].validate();//panel���ġ
		hint[2].validate();//panel���ġ
		
		if(flag==0){
			b1.setText("");
			b1.setVisible(false);
			GameMsg.setText(who+"("+pp+"P)���� �� ī���� �ϳ��� ��������ϴ�.");
			GameMsg.setVisible(true);
		}
		
		else if(flag==1){
			
			GameMsg.setText(who+"("+pp+"P)���� ������ ������ϴ�!!");
			b1.setVisible(true);
			GameMsg.setVisible(true);
		}
		
		jpHint.setVisible(true);
	}
	

	public void setMsgText(String who,int pp, String card,int key) {
		// TODO Auto-generated method stub
		hint[0].setVisible(false);
		hint[2].setVisible(false);

		
		int index=0;
		if(key==0){
			for(int i=0; i<RefData.nameRoom.length; i++){
				if(RefData.nameRoom[i].equals(card.trim())){
					index=i;
					break;
				}
				
			}
			hint[1].removeAll();
			hint[1].add(new JLabel(new ImageIcon(setImage("image/room/room"+index+".jpg", 140,190))));
			hint[1].validate();//panel���ġ
			
		}else if(key==1){
		
			for(int i=0; i<RefData.nameChar.length; i++){
				if(RefData.nameChar[i].equals(card.trim())){
					index=i;
					break;
				}
			}
			
			hint[1].removeAll();
			hint[1].add(new JLabel(new ImageIcon(setImage("image/player/char"+index+".jpg", 140,190))));
			hint[1].validate();//panel���ġ
		}else if(key==2){
		
			for(int i=0; i<RefData.nameWp.length; i++){
				if(RefData.nameWp[i].equals(card.trim())){
					index=i;
					break;
				}
			}
			
			hint[1].removeAll();
			hint[1].add(new JLabel(new ImageIcon(setImage("image/weapon/wp"+index+".jpg", 140,190))));
			hint[1].validate();//panel���ġ
		}
		
		
	
		
		
		GameMsg.setText(who+"("+pp+"P)���� "+card+" �� ��������ϴ�.");
		GameMsg.setVisible(true);
		jpHint.setVisible(true);
	}
	
	public void deleteMsg(){
			jpHint.setVisible(false);
			
		hint[0].setVisible(true);
		hint[2].setVisible(true);
		GameMsg.setText("00");
		
	}
	
	// #########�����߸� - �߸� ����� ��
	
	public void setWrongMsg(String who,int pp, int iAvata) {
		// TODO Auto-generated method stub

		
			wrong.removeAll();
			wrong.add(new JLabel(new ImageIcon(setImage("image/player/char"+iAvata+".jpg", 140,190))));
			wrong.validate();//panel���ġ
			
		wrongMsg.setText(who+"("+pp+"P)�� �����߸� ����");
		jpWrong.setVisible(true);
	}
	public void deleteWrongMsg(){
		jpWrong.setVisible(false);
		wrongMsg.setText("");
	
}
	
	
	public Image setImage(String filename, int width, int height){
		ImageIcon ii = new ImageIcon(filename);
		Image image=ii.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
		return image;
	}



}

















