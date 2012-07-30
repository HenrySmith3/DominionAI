package model;

import java.util.HashMap;
import java.util.Map;

public class DefaultPersonality extends Personality {
	
	public void initializeVectors() {
		//just one example. I'll do more of these later.
		vectors = new HashMap<String, AttributeVector>();
		AttributeVector temp = new AttributeVector();
		Card card = new ActionCard(ActionCardTypes.Adventurer.toString());
		temp.expMoneyInHand = .2f;
		temp.moneyDistribution = .8f;
		vectors.put(card.toString(), temp);
		
		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Cellar.toString());
		temp.percentVictoryCards = .7f;
		temp.invMoneyDistribution = .3f;
		vectors.put(card.toString(), temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Chancellor.toString());
		temp.invMoneyDistribution = .9f;
		temp.percentVictoryCards = .1f;		
		vectors.put(card.toString(), temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Chapel.toString());
		temp.invMoneyDistribution = .2f;
		temp.perEstate = .05f;
		temp.invPercentageLeft = .3f;
		temp.expValueEnemyDecks = .05f;
		temp.victoryEfficiency = .4f;
		vectors.put(card.toString(), temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.CouncilRoom.toString());
		temp.expValueNextCard = .7f;
		temp.invExpValueEnemyDecks = .1f;	
		temp.invPercentVictoryCards = .2f;
		vectors.put(card.toString(), temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Feast.toString());
		temp.cardsCosting5 = 1f;		
		vectors.put(card.toString(), temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Festival.toString());
		temp.perActionCards = .7f;
		temp.expMoneyInHand = .3f;
		vectors.put(card.toString(), temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Labratory.toString());
		temp.invPercentVictoryCards = .7f;
		temp.expValueNextCard = .3f;		
		vectors.put(card.toString(), temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Market.toString());
		temp.invExpMoneyInHand = .5f;
		temp.percentVictoryCards = .5f;		
		vectors.put(card.toString(), temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Mine.toString());
		temp.invMoneyDistribution = .6f;
		temp.invPercentageLeft = .2f;
		temp.percentMoney = .2f;
		vectors.put(card.toString(), temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Moneylender.toString());
		temp.perCopper = .9f;
		temp.invPerActionCards = .1f;		
		vectors.put(card.toString(), temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Smithy.toString());
		temp.percentMoney = .7f;
		temp.moneyDistribution = .3f;
		vectors.put(card.toString(), temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Village.toString());
		temp.perActionCards = 1f;	
		vectors.put(card.toString(), temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.WoodCutter.toString());
		temp.invPerActionCards = .5f;
		temp.expMoneyInHand = .5f;		
		vectors.put(card.toString(), temp);

		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Workshop.toString());
		temp.cardsCosting4 = .6f;
		temp.invPercentageLeft = .2f;
		temp.invExpMoneyInHand = .2f;
		vectors.put(card.toString(), temp);
		
		temp = new AttributeVector();
		card = new ActionCard(ActionCardTypes.Witch.toString());
		temp.cursesRemaining = .9f;
		temp.invPercentageLeft = .1f;		
		vectors.put(card.toString(), temp);

		temp = new AttributeVector();
		card = new Copper();
		temp.invPercentageLeft = .3f;	
		temp.expMoneyInHand = .2f;		
		vectors.put(card.toString(), temp);
		
		temp = new AttributeVector();
		card = new Silver();
		temp.invExpMoneyInHand = .6f;
		temp.invMoneyDistribution = .4f;		
		vectors.put(card.toString(), temp);
		
		temp = new AttributeVector();
		card = new Gold();
		temp.invMoneyDistribution = 1.2f;
		vectors.put(card.toString(), temp);

		temp = new AttributeVector();
		card = new Estate();
		temp.minTurnsRequiredToEnd = .5f;
		temp.invPercentageLeft = .1f;
		vectors.put(card.toString(), temp);
		
		temp = new AttributeVector();
		card = new Duchy();
		temp.invPercentageLeft = .7f;
		temp.currentlyWinning = .3f;		
		vectors.put(card.toString(), temp);

		temp = new AttributeVector();
		card = new Province();
		temp.alwaysOne = 1.5f;
		vectors.put(card.toString(), temp);

	}
}
