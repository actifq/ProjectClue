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
	JPanel jp ;
	JPanel[] hint=new JPanel[3];
	JLabel GameMsg;

	int[][] pXY={{6,6},
				{6,6},
				{6,6},
				{6,6}};
	
	

	public GameArea(){


		backImg=Toolkit.getDefaultToolkit().getImage("image/back/boardBack.png");

		for(int i=0;i<4;i++){
		horse[i]=Toolkit.getDefaultToolkit().getImage("image/player/horse"+(i+1)+".png");
		}
		
		
			   
		GameMsg=new JLabel("고현정(1P)님이 위 카드중 하나를 보여줬습니다.");
	
		
		GameMsg.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		GameMsg.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		GameMsg.setForeground(Color.CYAN);
		GameMsg.setOpaque(false);
		GameMsg.setBounds(160, 230, 600, 40);
		
		jp = new JPanel();
		jp.setLayout(null);
		
		jp.setBackground(Color.BLACK);
		
		jp.add(GameMsg);
		setLayout(null);
		jp.setBounds(0, 130, 850, 300);
		add(jp);
		jp.setVisible(false);
		
		//힌트 화면
		for(int i=0;i<3;i++){
			   hint[i]=new JPanel();
			   hint[i].setBounds(180+(i*180), 10, 140, 190);
			   hint[i].setLayout(new BorderLayout());
			   hint[i].add(new JLabel(new ImageIcon(setImage("image/back/qcard.png", hint[i].getWidth(), hint[i].getHeight()))));
			   jp.add(hint[i]);
		}

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



	public void setMsgText(String who,int pp, String r, String p, String w) {
		// TODO Auto-generated method stub
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
		
		hint[0].validate();//panel재배치
		hint[1].validate();//panel재배치
		hint[2].validate();//panel재배치
		
		
		GameMsg.setText(who+"("+p+"P)님이 위 카드중 하나를 보여줬습니다.");
		jp.setVisible(true);
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
			hint[1].validate();//panel재배치
			
		}else if(key==1){
		
			for(int i=0; i<RefData.nameChar.length; i++){
				if(RefData.nameChar[i].equals(card.trim())){
					index=i;
					break;
				}
			}
			
			hint[1].removeAll();
			hint[1].add(new JLabel(new ImageIcon(setImage("image/player/char"+index+".jpg", 140,190))));
			hint[1].validate();//panel재배치
		}else if(key==2){
		
			for(int i=0; i<RefData.nameWp.length; i++){
				if(RefData.nameWp[i].equals(card.trim())){
					index=i;
					break;
				}
			}
			
			hint[1].removeAll();
			hint[1].add(new JLabel(new ImageIcon(setImage("image/weapon/wp"+index+".jpg", 140,190))));
			hint[1].validate();//panel재배치
		}
		
		
	
		
		
		GameMsg.setText(who+"("+pp+"P)님이 "+card+" 를 줬습니다.");
		jp.setVisible(true);
	}
	
	public void deleteMsg(){
			jp.setVisible(false);
			
		hint[0].setVisible(true);
		hint[2].setVisible(true);
		GameMsg.setText("");
		
	}
	
	public Image setImage(String filename, int width, int height){
		ImageIcon ii = new ImageIcon(filename);
		Image image=ii.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
		return image;
	}

}


