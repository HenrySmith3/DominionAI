package model;

public class Gold extends Card{
	public Gold(){
		cost = 6;
		value = 3;
		victory = 0;
		type = CardType.Money;
	}
	public Card clone() {
		return new Gold();
	}
}