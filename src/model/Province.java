package model;

public class Province extends Card{
	public Province(){
		cost = 8;
		value = 0;
		victory = 6;
		type = CardType.Victory;
	}
	public Card clone() {
		return new Province();
	}
}