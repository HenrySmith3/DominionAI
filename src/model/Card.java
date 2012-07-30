package model;

public abstract class Card {
	protected int cost;
	protected int value;
	protected int victory;
	protected CardType type;
	public String toString(){
		String s = getClass().toString();
		while(s.contains(".")){
			s = s.substring(s.indexOf('.')+1);
		}
		return s;
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
	public int getCost(){
		return cost;
	}
	public CardType getType(){
		return type;
	}
}