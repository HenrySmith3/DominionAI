package model;

public class ActionCard extends Card{
	ActionCardTypes identity;
	public ActionCard(ActionCardTypes t){
		victory = 0;
		type = CardType.Action;
		identity = t;
		switch (identity){
			case Adventurer:
				cost = 6;
			case Bureaucrat: case CouncilRoom: case Festival: case Labratory:
			case Library: case Market: case Mine: case Witch:
				cost = 5;
			case Feast: case Militia: case Moneylender: case Remodel:
			case Smithy: case Spy: case Thief: case ThroneRoom: 
				cost = 4;
			case Village: case WoodCutter: case Workshop:
				cost = 3;
			case Cellar: case Chapel: case Moat:
				cost = 2;
		}
	}
}