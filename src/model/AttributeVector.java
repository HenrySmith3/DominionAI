package model;

public class AttributeVector {
	//values can be between 0 and 1, all attributes add to 1
	public float percentageLeft = 0;
	public float minTurnsRequiredToEnd = 0;
	public float expMoneyInHand = 0;//NOT the current amount of money. Expectation of money in next hand.
	public float invExpMoneyInHand = 0;
	public float percentVictoryCards = 0;//in your current deck.
	//public float actionCardPlayability = 0;//higher means you can play all the action cards in your hand.
	public float expNumBuys = 0;
	public float perEstate = 0;//percentage of Estate in your deck.
	public float perDuchy = 0;
	public float perProvince = 0;
	public float expValueNextCard = 0;//measures accross all decks, not just draw.
	//^^^Fix later, this will probably cause an infinite loop.
	public float expValueDraw = 0;//Expected value of decks (discard, draw, hand)
	public float expValueDiscard = 0;
	public float expValueHand = 0;
	public float expValueEnemyDecks = 0;//Expected value of enemy deck.
	public float expValueCosting5 = 0;// Highest expected value of a card costing x (for feast and remodel). 
	//^^^Could cause an infinite loop.
	//add another here if you need a different x for previous one.
	public float cardsCosting5 = 0;//Total number of cards coting 5 left from community (for feast)
	public float expValue3AddGold = 0;//Increased expected value of buy with 3 additional gold (Moneylender).
	//^^^skipped for now. I think we might want to take out MoneyLender, this is really hard to implement.
	public float cursesRemaining = 0;//Number of curses remaining to give.
	public float expEnemiesDrawingWitches = 0;//Relative number of witches other players are expected to draw.
	public float currentlyWinning = 0;//How am I doing relative to other player in terms of vicoty points (one for loser, one for winner)
	public float moneyDistribution = 0;
	public float invMoneyDistribution = 0;
	public float invPercentageLeft = 0;
	public float victoryEfficiency = 0;
	public float invExpValueEnemyDecks = 0;
	public float invPercentVictoryCards = 0;
	public float perActionCards = 0;
	public float perCopper = 0;
	public float invPerActionCards = 0;
	public float percentMoney = 0;
	public float cardsCosting4 = 0;
	public float alwaysOne = 0;
}