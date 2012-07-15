package model;

public class BlankCard extends Card{
	BlankCard(){
		
	}
	public Card clone() {
		return new BlankCard();
	}
}
