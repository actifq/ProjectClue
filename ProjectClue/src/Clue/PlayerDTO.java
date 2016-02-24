package Clue;

public class PlayerDTO {

	String id;
	int crrX,crrY;
	int numCanGo;
	int charIndex;
	int[] cards=new int[5];
	
	

	public PlayerDTO(String avaname, int[] cards){
		
		this.id = avaname;
		
		for(int i=0;i<6;i++){
			if(RefData.nameChar[i].equals(avaname)){
				charIndex=i;
				break;
			}
		}
		crrX = 6;
		crrY = 6;
		numCanGo=0;
		this.cards=cards.clone();//Ä«µåÀÎµ¦½º º¹Á¦.
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
