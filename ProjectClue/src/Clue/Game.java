package Clue;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
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
	PlayerDTO pMain;
	PlayerDTO[] p;
	static int crrPlayer; 
	JFrame frTurn;
	private Random random;
	
	private int myNum, myAva;
	
	
/*	public Game(){
		answerCard=selectAnswerCard();	//����ī��
		distributeCard(answerCard, pCard); //�÷��̾�ī��
		p=new PlayerDTO[4];
		p[0]= new PlayerDTO("�Źξ�",pCard[0]);
		p[1]= new PlayerDTO("���޼�",pCard[1]);
		p[2]= new PlayerDTO("���¹�",pCard[2]);
		p[3]= new PlayerDTO("������",pCard[3]);
		
		crrPlayer=0;
	}
	*/
	
/*�� ������
 		public Game(GameArea gv, JFrame fr){
		p=new PlayerDTO[4];
		
		this.gv=gv;
		frTurn=fr;
		answerCard=selectAnswerCard();	//����ī��
		distributeCard(answerCard, pCard); //�÷��̾�ī��
		
		
		//�÷��̾� �ʱ�ȭ �����ʿ�=> ���ǿ��� ������ �����;���.
		p[0]= new PlayerDTO(0,pCard[0]);
		p[1]= new PlayerDTO(1,pCard[1]);
		p[2]= new PlayerDTO(2,pCard[2]);
		p[3]= new PlayerDTO(3,pCard[3]);
		
		pMain=new PlayerDTO(p[0].getCharIndex(),pCard[0]);
		
		crrPlayer=0;
		
		setGamePlayer(crrPlayer,runDice());
		
	}*/

	public Game(GameArea gv, JFrame fr){
		p=new PlayerDTO[4];
		
		this.gv=gv;
		frTurn=fr;
		/*answerCard=selectAnswerCard();	//����ī��
		distributeCard(answerCard, pCard); //�÷��̾�ī��
	
		
		
		//�÷��̾� �ʱ�ȭ �����ʿ�=> ���ǿ��� ������ �����;���.
		p[0]= new PlayerDTO(0,pCard[0]);
		p[1]= new PlayerDTO(1,pCard[1]);
		p[2]= new PlayerDTO(2,pCard[2]);
		p[3]= new PlayerDTO(3,pCard[3]);
		
		pMain=new PlayerDTO(p[0].getCharIndex(),pCard[0]);
		
		crrPlayer=0;
		
		setGamePlayer(crrPlayer,runDice());
	*/		
		pMain=p[0];
		
		crrPlayer=0;
		
	}
	
	public void setPlayer(int pnum, String avata){
		p[pnum-1]= new PlayerDTO(avata,pCard[pnum-1]);
		
	}
	
	public int runDice() {
		// TODO Auto-generated method stub
		
		random= new Random(System.currentTimeMillis());
		
		dice1=random.nextInt(6)+1;
		dice2=random.nextInt(6)+1;
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
		//frTurn=new ShowTurn(p[crrPlayer].getId(), (dice1+dice2),gv);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frTurn=new ShowTurn(p[crrPlayer].getId(), (dice1+dice2),gv);
		frTurn.setVisible(true);
		
		return dice1+dice2;
	}


	
	

	public int getMyNum() {
		return myNum;
	}

	public void setMyNum(int myNum) {
		this.myNum = myNum;
	}

	public int getMyAva() {
		return myAva;
	}

	public void setMyAva(int myAva) {
		this.myAva = myAva;
	}

	public PlayerDTO getpMain() {
		return pMain;
	}



	public int getDice1() {
		return dice1;
	}



	public int getDice2() {
		return dice2;
	}



	public void move(){
		gp.move(gv);		
		
	}

	
	public int process()  {
			move();
			//isReached();
			int roomNum=gp.isReached();
			if(roomNum!=0){
				return roomNum;
			}
			if(gp.getCount()==0){
				
				
				savePlayerStatus();
				setGamePlayer(crrPlayer,runDice());
				
				
			}
			//isTurn();
			return 0;
		
	}

	public void isTurn() {
		// TODO Auto-generated method stub
		if(gp.getCount()==0){
			
			
			savePlayerStatus();
			setGamePlayer(crrPlayer,runDice());
			
			
		}
	}
	
	
	
	public void setAnswerCard(int[] answerCard) {
		this.answerCard = answerCard;
	}

	public void setpCard(int[][] pCard) {
		this.pCard = pCard;
	}


	public void savePlayerStatus(){
		p[crrPlayer].setCrrX(gp.getCrrX());
		p[crrPlayer].setCrrY(gp.getCrrY());
		p[crrPlayer].setNumCanGo(0);
		
		crrPlayer++;
		if(crrPlayer==4)
			crrPlayer=0;
		
	}

	public void setGamePlayer(int pNum, int dice){
		pMain=p[pNum];
		pMain.setNumCanGo(dice);
		gp.setCrrX(pMain.crrX);
		gp.setCrrY(pMain.crrY);
		gp.setCount(dice);
		
	}
	
}
