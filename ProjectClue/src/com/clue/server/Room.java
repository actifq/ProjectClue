package com.clue.server;
import java.util.*;

import com.clue.GMS_JF_ShowTurn;

public class Room {
     String roomName,roomState,roomPwd;
     int maxcount;
     int current;
     int noPlayer;
     Vector<Server.ClientThread> userVc=new Vector<Server.ClientThread>();

	 int rdyCnt;
	 
	 	int[] answerCard =new int[3];
		int[][] pCard= new int[4][5];
		int dice1=1,dice2=1;
		int[] removedP={-1,-1};
		
		
     public Room(String rn,String rs,String rp,int max)
     {
    	 roomName=rn;
    	 roomState=rs;
    	 roomPwd=rp;
    	 maxcount=max;
    	 current=1;
    	 rdyCnt=0;

     }
     
     public String getAnsCard(){
    	 String ans="";
    	 for(int i=0; i<answerCard.length; i++){
    		 ans+=String.valueOf(answerCard[i])+"|";
    	 }
    	 return ans;
     }
     
     public String getPCard(){
    	 String ans="";
    	 for(int i=0; i<4; i++){
    		 for(int j=0; j<5; j++)
    		 ans+=String.valueOf(pCard[i][j])+"|";
    	 }
    	 return ans;
     }
     
/*     public static void main(String[] args){
    	 Room r = new Room("","","",0);
    	 r.initGame();
    	 System.out.println(r.getAnsCard());
    	 System.out.println(r.getPCard());
     }*/
     
     public void initGame(){
    	answerCard=selectAnswerCard();	//����ī��
 		distributeCard(answerCard, pCard); //�÷��̾�ī��
     }
     
 	//ī�� �����ϱ�
 	public int[] selectAnswerCard(){
 		//����ī�����
 		int[] answer = new int[3];
 		answer[0]=(int)(Math.random()*9)+14;//���
 		answer[1]=(int)(Math.random()*6);//�÷��̾�
 		answer[2]=(int)(Math.random()*8)+6;//����
 		
 		//System.out.println(answer[0]+" "+answer[1]+" "+answer[2]+"\n");
 		return answer;
 	}
     
	//ī�峪���ֱ� -����ī������ �ߺ����� �����̾� Player�� card[]�� ä���ֱ�
	public void distributeCard(int[] answerCard, int[][] pCard){
		
		int[] com=new int[23];
		for(int i=0; i<3; i++){
			com[i]=answerCard[i];
		}
		//������ �޴� ����
		int su=0;
		//�ߺ�����
		
		boolean bDash=true;
		
		for(int i=3;i<com.length;i++){
			
			bDash=true;	/*
			ó�� while�� �� �� su�� ������ ���� �Ŀ� bDash�� false�� �Ǳ� ������ while ���� for�� �����ʾ� �׻� false�� �ȴ�.
			*/
			while(bDash){	//�ߺ��Ǹ� true ->��ӵ���.
			//�����߻�
			su=(int)(Math.random()*23);
			bDash=false;
			for(int j=0; j<i;j++){
				if(com[j]==su){
					bDash=true;
					break;
				}
			}
			
			}com[i]=su;
			//System.out.print(com[i]+" ");
			
		}
	
		for(int i=0;i<20; i++){
			pCard[i/5][i%5] = com[i+3];
			
		}
		
		//����
		for(int k=0;k<pCard.length;k++){
				for(int i=0; i<pCard[0].length-1; i++){
					for(int j=0; j<pCard[0].length-1-i;j++){
						if(pCard[k][j]>pCard[k][j+1]){
							int temp=pCard[k][j];
							pCard[k][j]=pCard[k][j+1];
							pCard[k][j+1]=temp;
						}
					}
				}
		}
	}
	
	public void runDice() {
		// TODO Auto-generated method stub
		
		Random random= new Random(System.currentTimeMillis());
		
		dice1=random.nextInt(6)+1;
		dice2=random.nextInt(6)+1;

	}
}
