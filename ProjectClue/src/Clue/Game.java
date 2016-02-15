package Clue;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game{
	
	
	GamePlayer gp=new GamePlayer(this);
	GameArea gv;
	int[] answerCard =new int[3];
	int[][] pCard= new int[4][5];
	static int dice1=1,dice2=1;
	static PlayerDTO[] p;
	static int crrPlayer; 
	JFrame frTurn;
	private Random random;
/*	public Game(){
		answerCard=selectAnswerCard();	//����ī��
		distributeCard(answerCard, pCard); //�÷��̾�ī��
		p=new PlayerDTO[4];
		p[0]= new PlayerDTO("�Źξ�",pCard[0]);
		p[1]= new PlayerDTO("���޼�",pCard[1]);
		p[2]= new PlayerDTO("���¹�",pCard[2]);
		p[3]= new PlayerDTO("������",pCard[3]);
		
		crrPlayer=0;
	}*/
	
	public Game(GameArea gv, JFrame fr){
		p=new PlayerDTO[4];
		this.gv=gv;
		frTurn=fr;
		answerCard=selectAnswerCard();	//����ī��
		distributeCard(answerCard, pCard); //�÷��̾�ī��
		
		
		//�÷��̾� �ʱ�ȭ �����ʿ�=> ���ǿ��� ������ �����;���.
		p[0]= new PlayerDTO("�Źξ�",pCard[0]);
		p[1]= new PlayerDTO("���޼�",pCard[1]);
		p[2]= new PlayerDTO("���¹�",pCard[2]);
		p[3]= new PlayerDTO("������",pCard[3]);
		
		crrPlayer=0;
		
		setGamePlayer(crrPlayer,runDice());
		
		
		
	}

	
	
	public int runDice() {
		// TODO Auto-generated method stub
		
		random= new Random(System.currentTimeMillis());
		
		dice1=random.nextInt(5)+1;
		dice2=random.nextInt(5)+1;
		/*frTurn = new JFrame("�ֻ���");
		frTurn.setSize(300, 300);
		Container contentPane = frTurn.getContentPane();
		JLabel label = new JLabel(p[crrPlayer].getId()+"��! ",JLabel.CENTER);
		JLabel label2 = new JLabel("�̵�Ƚ��: "+(dice1+dice2),JLabel.CENTER);
		contentPane.add(label, BorderLayout.CENTER);
		contentPane.add(label2, BorderLayout.SOUTH);		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frTurn.setBounds((screenSize.width - frTurn.getWidth())/2,(screenSize.height - frTurn.getHeight())/2,frTurn.getWidth(),frTurn.getHeight());
		*/
		frTurn=new ShowTurn(p[crrPlayer].getId(), (dice1+dice2),gv);
		frTurn.setVisible(true);
		
		return dice1+dice2;
	}


	
	

	public int getDice1() {
		return dice1;
	}



	public int getDice2() {
		return dice2;
	}



	public void move(){
		gp.move();		
		
	}


	/*�׽�Ʈ��
	 * public static void main(String args[]){
		Game g  = new Game();
		for(int i=0;i<4; i++){
			for(int j=0;j<5;j++){
				System.out.print(g.pCard[i][j]+" ");
			}
			System.out.println();
		}
		
	}*/

	
	public int process()  {
			move();
			//isReached();
			int roomNum=gp.isReached();
			if(roomNum!=0){
				return roomNum;
			}
			isTurn();
			return 0;
		
	}

	public void isTurn() {
		// TODO Auto-generated method stub
		if(gp.getCount()==0){
			//JOptionPane.showMessageDialog(this, "���̻� �̵��� �� �����ϴ�.","���̻� �̵��� �� �����ϴ�.",JOptionPane.YES_NO_OPTION);
			System.out.println("���̻� �̵��� �� �����ϴ�.");
			
			savePlayerStatus();
			setGamePlayer(crrPlayer,runDice());
			//System.exit(0);
			
		}
	}
	
	//ī�� �����ϱ�
	public int[] selectAnswerCard(){
		//����ī�����
		int[] answer = new int[3];
		answer[0]=(int)(Math.random()*6);//�÷��̾�
		answer[1]=(int)(Math.random()*8)+6;//����
		answer[2]=(int)(Math.random()*9)+14;//���
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
	public void savePlayerStatus(){
		p[crrPlayer].setCrrX(gp.getCrrX());
		p[crrPlayer].setCrrY(gp.getCrrY());
		p[crrPlayer].setNumCanGo(0);
		
		crrPlayer++;
		if(crrPlayer==4)
			crrPlayer=0;
		
	}
	/*//�浵�������� �׼�
	public void setGamePlayer(int pNum){
		//p[pNum].setNumCanGo(dice);
		gp.setCrrX(p[pNum].crrX);
		gp.setCrrY(p[pNum].crrY);
		//gp.setCount(dice);
		
	}*/
	
	//�����÷����� ��ǥ���� count���� �迭���� �ҷ��� ����
	public void setGamePlayer(int pNum, int dice){
		p[pNum].setNumCanGo(dice);
		gp.setCrrX(p[pNum].crrX);
		gp.setCrrY(p[pNum].crrY);
		gp.setCount(dice);
		
	}
}
