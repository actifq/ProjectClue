package Clue;

public class PlayerDTO {

	String id;
	int crrX,crrY;
	int numCanGo;
	int[] cards=new int[5];
	
	

	public PlayerDTO(String id, int[] cards){
		this.id = id;
		crrX = 6;
		crrY = 6;
		numCanGo=0;
		this.cards=cards.clone();//카드인덱스 복제.
	}
	
	
	public int getCrrX() {
		return crrX;
	}

	public void setCrrX(int crrX) {
		this.crrX = crrX;
	}

	public int getCrrY() {
		return crrY;
	}

	public void setCrrY(int crrY) {
		this.crrY = crrY;
	}

	public int getNumCanGo() {
		return numCanGo;
	}

	public void setNumCanGo(int numCanGo) {
		this.numCanGo = numCanGo;
	}

	public String getId() {
		return id;
	}

	public int[] getCards() {
		return cards;
	}

	
	
}
