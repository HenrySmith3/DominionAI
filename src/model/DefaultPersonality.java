package model;

public class DefaultPersonality extends Personality {
	/*Adventurer,Cellar,Chancellor,Chapel,CouncilRoom,Feast,
	Festival,Labratory,Market,Mine,Moneylender,
	Remodel,Smithy,ThroneRoom,Village,WoodCutter,Workshop,
	Bureaucrat,Moat,Militia,Thief,Spy,Witch 
	Copper, Curse, Duchy, Estate, Gold, Silver, Province*/
	
	public void initializeVectors() {
		//just one example. I'll do more of these later.
		AttributeVector temp = new AttributeVector();
		Card card = new ActionCard(ActionCardTypes.Adventurer.toString());
		temp.invExpMoneyInHand = .7f;
		temp.percentVictoryCards = .3f;		
		vectors.put(card, temp);
		
		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Bureaucrat.toString());
	}
}
