package model;

public class DefaultPersonality extends Personality {
	/*Adventurer,Cellar,Chancellor,Chapel,CouncilRoom,Feast,
	Festival,Labratory,Market,Mine,Moneylender,
	Remodel,Smithy,Village,WoodCutter,Workshop,
	Bureaucrat,Moat,Militia,Thief,Spy,Witch 
	Copper, Curse, Duchy, Estate, Gold, Silver, Province*/
	
	public void initializeVectors() {
		//just one example. I'll do more of these later.
		AttributeVector temp = new AttributeVector();
		Card card = new ActionCard(ActionCardTypes.Adventurer.toString());
		temp.expMoneyInHand = .2f;
		//temp.moneyDistribution = .8f;
		vectors.put(card, temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Cellar.toString());
		temp.percentVictoryCards = .7f;
		//temp.invMoneyDistribution = .3f;		
		vectors.put(card, temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Chancellor.toString());
		//temp.invMoneyDistribution = .9f;
		temp.percentVictoryCards = .1f;		
		vectors.put(card, temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Chapel.toString());
		//temp.invMoneyDistribution = .2f;
		temp.perEstate = .05f;
		//temp.invPercentageLeft = .3f;
		temp.expValueEnemyDecks = .05f;
		//temp.victoryEfficiency = .4f;
		vectors.put(card, temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.CouncilRoom.toString());
		temp.expValueNextCard = .7f;
		//temp.invExpValueEnemyDecks = .1f;	
		//temp.invPercentVictoryCards = .2f;
		vectors.put(card, temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Feast.toString());
		temp.cardsCosting5 = 1f;		
		vectors.put(card, temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Festival.toString());
		//temp.perActionCards = .7f;
		temp.expMoneyInHand = .3f;
		vectors.put(card, temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Labratory.toString());
		//temp.invpercentVictoryCards = .7f;
		temp.expValueNextCard = .3f;		
		vectors.put(card, temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Market.toString());
		temp.invExpMoneyInHand = .5f;
		temp.percentVictoryCards = .5f;		
		vectors.put(card, temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Mine.toString());
		//temp.invMoneyDistribution = .6f;
		//temp.invPercentageLeft = .2f;
		//temp.percentMoneyCards = .2f;
		vectors.put(card, temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Moneylender.toString());
		//temp.perCopper = .9f;
		//temp.invPercentAction = .1f;		
		vectors.put(card, temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Smithy.toString());
		//temp.pecentMoney = .7f;
		//temp.moneyDistribution = .3f;
		vectors.put(card, temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Village.toString());
		//temp.perActionCard = 1f;	
		vectors.put(card, temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.WoodCutter.toString());
		//temp.invPercentAction = .5f;
		temp.expMoneyInHand = .5f;		
		vectors.put(card, temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Workshop.toString());
		//temp.cardsCosting4 = .6f;
		//temp.invPercentDone = .2f;
		temp.invExpMoneyInHand = .2f;
		vectors.put(card, temp);
		
		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Witch.toString());
		temp.cursesRemaining = .9f;
		//temp.invPercentLeft = .1f;		
		vectors.put(card, temp);

		temp = new AttributeVector();
		card = new Copper();
		//temp.invPercentageLeft = .3f;	
		temp.expMoneyInHand = .2f;		
		vectors.put(card, temp);
		
		temp = new AttributeVector();
		card = new Silver();
		temp.invExpMoneyInHand = .6f;
		//temp.invMoneyDistribution = .4f;		
		vectors.put(card, temp);
		
		temp = new AttributeVector();
		card = new Gold();
		//temp.invMoneyDisribution = 1.2f;
		vectors.put(card, temp);

		temp = new AttributeVector();
		card = new Estate();
		temp.minTurnsRequiredToEnd = .5f;
		//temp.invPercentageLeft = .1f;
		vectors.put(card, temp);
		
		temp = new AttributeVector();
		card = new Duchy();
		//temp.invPercentageLeft = .7f;
		temp.currentlyWinning = .3f;		
		vectors.put(card, temp);

		temp = new AttributeVector();
		card = new Province();
		//temp.alwaysOne = 1.5f;
		vectors.put(card, temp);

	}
}
