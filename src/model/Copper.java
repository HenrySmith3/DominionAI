package model;

public class Copper extends Card{
	public Copper(){
		cost = 0;
		value = 1;
		victory = 0;
		type = CardType.Money;
	}
	public Card clone() {
		return new Copper();
	}
}
