package com.clue;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class JP_GameArea extends JPanel{

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
	
	

	public JP_GameArea(){


		backImg=Toolkit.getDefaultToolkit().getImage("image/back/boardBack.png");
		for(int i=0;i<4;i++){
			
		
		horse[i]=Toolkit.getDefaultToolkit().getImage("image/player/horse"+(i+1)+".png");
		
		}
		// #### 힌트 MSG 문구	   
		GameMsg=new JLabel("고현정(1P)님이 위 카드중 하나를 보여줬습니다.");
		GameMsg.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		GameMsg.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		GameMsg.setForeground(Color.CYAN);
		GameMsg.setOpaque(false);
		GameMsg.setBounds(160, 300, 600,50);
		
		//###### 최종추리 실패 MSG 문구
		wrongMsg=new JLabel("고현정(1P)님 최종추리 실패");
		wrongMsg.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		wrongMsg.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		wrongMsg.setForeground(Color.RED);
		wrongMsg.setOpaque(false);
		wrongMsg.setBounds(240, 270, 600, 50);
		
	
		setLayout(null);
		
		// #####  힌트 MSG 배경판넬 
		jpHint = new JPanel();
		jpHint.setLayout(null);
		jpHint.setBackground(Color.BLACK);
		jpHint.add(GameMsg);
		jpHint.setBounds(0, 130, 850, 400);
		add(jpHint);
		jpHint.setVisible(false);
		
		// #####  최종추리 실패 MSG 배경판넬 
				jpWrong = new JPanel();
				jpWrong.setLayout(null);
				jpWrong.setBackground(Color.BLACK);
				jpWrong.add(wrongMsg);
				jpWrong.setBounds(0, 130, 850, 350);
				add(jpWrong);
				jpWrong.setVisible(false);
		
		b1=new JButton("게임종료");
		b1.setBounds(720, 270, 100, 80);
		jpHint.add(b1);
		b1.setVisible(false);
		
		
		// ##### 힌트 화면 이미지 추가하기
		for(int i=0;i<3;i++){
			   hint[i]=new JPanel();
			   hint[i].setBounds(180+(i*180),50, 140, 190);
			   hint[i].setLayout(new BorderLayout());
			   hint[i].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", hint[i].getWidth(), hint[i].getHeight()))));
			   jpHint.add(hint[i]);
		}
		
		// ##### 힌트 화면 이미지 추가하기
		
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
		
		for(int i=0; i<Game_RefData.nameRoom.length; i++){
			if(Game_RefData.nameRoom[i].equals(r.trim())){
				ir=i;
				break;
			}
		}
		
		for(int i=0; i<Game_RefData.nameChar.length; i++){
			if(Game_RefData.nameChar[i].equals(p.trim())){
				ip=i;
			}
		}
		
		for(int i=0; i<Game_RefData.nameWp.length; i++){
			if(Game_RefData.nameWp[i].equals(w.trim())){
				iw=i;
			}
		}
		hint[0].removeAll();
		hint[1].removeAll();
		hint[2].removeAll();
		
		hint[0].add(new JLabel(new ImageIcon(setImage("image/room/room"+ir+".jpg", 140,190))));
		hint[1].add(new JLabel(new ImageIcon(setImage("image/player/char"+ip+".jpg", 140,190))));
		hint[2].add(new JLabel(new ImageIcon(setImage("image/weapon/wp"+iw+".jpg", 140,190))));
		
		hint[0].validate();//panel재배치
		hint[1].validate();//panel재배치
		hint[2].validate();//panel재배치
		
		if(flag==0){
			b1.setText("");
			b1.setVisible(false);
			GameMsg.setText(who+"("+pp+"P)님이 위 카드중 하나를 보여줬습니다.");
			GameMsg.setVisible(true);
		}
		
		else if(flag==1){
			
			GameMsg.setText(who+"("+pp+"P)님이 정답을 맞췄습니다!!");
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
			for(int i=0; i<Game_RefData.nameRoom.length; i++){
				if(Game_RefData.nameRoom[i].equals(card.trim())){
					index=i;
					break;
				}
				
			}
			hint[1].removeAll();
			hint[1].add(new JLabel(new ImageIcon(setImage("image/room/room"+index+".jpg", 140,190))));
			hint[1].validate();//panel재배치
			
		}else if(key==1){
		
			for(int i=0; i<Game_RefData.nameChar.length; i++){
				if(Game_RefData.nameChar[i].equals(card.trim())){
					index=i;
					break;
				}
			}
			
			hint[1].removeAll();
			hint[1].add(new JLabel(new ImageIcon(setImage("image/player/char"+index+".jpg", 140,190))));
			hint[1].validate();//panel재배치
		}else if(key==2){
		
			for(int i=0; i<Game_RefData.nameWp.length; i++){
				if(Game_RefData.nameWp[i].equals(card.trim())){
					index=i;
					break;
				}
			}
			
			hint[1].removeAll();
			hint[1].add(new JLabel(new ImageIcon(setImage("image/weapon/wp"+index+".jpg", 140,190))));
			hint[1].validate();//panel재배치
		}
		
		
	
		
		
		GameMsg.setText(who+"("+pp+"P)님이 "+card+" 를 보여줬습니다.");
		GameMsg.setVisible(true);
		jpHint.setVisible(true);
	}
	
	public void deleteMsg(){
			jpHint.setVisible(false);
			
		hint[0].setVisible(true);
		hint[2].setVisible(true);
		GameMsg.setText("00");
		
	}
	
	// #########최종추리 - 잘못 골랐을 때
	
	public void setWrongMsg(String who,int pp, int iAvata) {
		// TODO Auto-generated method stub

		
			wrong.removeAll();
			wrong.add(new JLabel(new ImageIcon(setImage("image/player/char"+iAvata+".jpg", 140,190))));
			wrong.validate();//panel재배치
			
		wrongMsg.setText(who+"("+pp+"P)의 최종추리 실패");
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

















