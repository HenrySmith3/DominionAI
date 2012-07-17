package model;

public class AttributeVector {
	//values can be positive or negative, but the absolute value sums to 1.
	public float percentageLeft;
	public float minTurnsRequiredToEnd;//high value means game is about to end
	public float expMoneyInHand;//NOT the current amount of money. Expectation of money in next hand.
	public float percentVictoryCards;//in your current deck.
	public float actionCardPlayability;//higher means you can play all the action cards in your hand.
	public float expNumBuys;
	public float perEstate;//percentage of Estate in your deck.
	public float perDuchy;
	public float perProvince;
	public float expValueNext3Cards;//add another if you need a different x. Expected value of next x cards.
	public float expValueDraw;//Expected value of decks (discard, draw, hand)
	public float expValueDiscard;
	public float expValueHand;
	public float expValueEnemyDecks;//Expected value of enemy deck.
	public float expValueCosting5;// Highest expected value of a card costing x (for feast and remodel). 
	//add another here if you need a different x for previous one.
	public float cardsCosting5;//Total number of cards coting 5 left from community (for feast)
	public float expValue3AddGold;//Increased expected value of buy with 3 additional gold (Moneylender).
	public float cursesRemaining;//Number of curses remaining to give.
	public float expEnemiesDrawingWitches;//Relative number of witches other players are expected to draw.
	public float currentlyWinning;//How am I doing relative to other player in terms of vicoty points (one for loser, one for winner)
}
