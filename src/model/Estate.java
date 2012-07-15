package model;

public class Estate extends Card{
	public Estate(){
		cost = 2;
		value = 0;
		victory = 1;
		type = CardType.Victory;
	}
	public Card clone() {
		return new Estate();
	}
}
