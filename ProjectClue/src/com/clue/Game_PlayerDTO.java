package com.clue;

public class Game_PlayerDTO {

	String id;
	int crrX,crrY;
	int numCanGo;
	int charIndex;
	int[] cards=new int[5];
	
	

	public Game_PlayerDTO(String avaname, int[] cards){
		
		this.id = avaname;
		
		for(int i=0;i<6;i++){
			if(Game_RefData.nameChar[i].equals(avaname)){
				charIndex=i;
				break;
			}
		}
		crrX = 6;
		crrY = 6;
		numCanGo=0;
		this.cards=cards.clone();//카드인덱스 복제.
	}
	
public Game_PlayerDTO(int avaname, int[] cards){
		
		this.id = Game_RefData.nameChar[avaname-1];
		charIndex=avaname;
		
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


	public int getCharIndex() {
		return charIndex;
	}

	
	
}
