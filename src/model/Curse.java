package model;

public class Curse extends Card{
	public Curse(){
		cost = 0;
		value = 0;
		victory = -1;
		type = CardType.Victory;
	}
	public Card clone() {
		return new Curse();
	}
}
