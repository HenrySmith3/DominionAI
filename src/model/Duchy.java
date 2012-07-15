package model;

public class Duchy extends Card{
	public Duchy(){
		cost = 5;
		value = 0;
		victory = 3;
		type = CardType.Victory;
	}
	public Card clone() {
		return new Duchy();
	}
}
