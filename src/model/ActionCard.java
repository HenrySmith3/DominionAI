package model;

public class ActionCard extends Card{
	ActionCardTypes identity;
	public ActionCard(String s){
		victory = 0;
		type = CardType.Action;
		identity = ActionCardTypes.valueOf(s);
		switch (identity){
			case Adventurer:
				cost = 6;
				break;
			case Bureaucrat: case CouncilRoom: case Festival: case Labratory:
			case Market: case Mine: case Witch:
				cost = 5;
				break;
			case Feast: case Militia: case Moneylender: case Remodel:
			case Smithy: case Spy: case Thief: case ThroneRoom: 
				cost = 4;
				break;
			case Village: case WoodCutter: case Workshop:
				cost = 3;
				break;
			case Cellar: case Chapel: case Moat:
				cost = 2;
				break;
		}
	}
	public Card clone() {
		return new ActionCard(identity.toString());
	}
	public String toString() {
		return identity.toString();
	}
}