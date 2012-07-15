package model;

public class Silver extends Card{
	public Silver(){
		cost = 3;
		value = 2;
		victory = 0;
		type = CardType.Money;
	}
	public Card clone() {
		return new Silver();
	}
}
