package model;

public abstract class Card {
	protected int cost;
	protected int value;
	protected int victory;
	protected CardType type;
	protected AttributeVector vector;
	public String toString(){
		return this.getClass().toString().substring(12);
	}
	public abstract Card clone();
	public boolean equals(Card other){
		if (other.getClass().equals(this.getClass())){
			if (this.type == CardType.Action){
				if (((ActionCard)this).identity == ((ActionCard)other).identity){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return true;
			}
		}
		return false;
	}
}