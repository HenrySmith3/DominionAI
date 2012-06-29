package model;

public abstract class Card {
	protected int cost;
	protected int value;
	protected int victory;
	protected CardType type;
	public String toString(){
		return this.getClass().toString();
	}
}