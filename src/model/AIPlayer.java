package model;

public class AIPlayer extends Player{
	Personality personality;
	public AIPlayer(String s, Personality personality) throws InstantiationException,
			IllegalAccessException {
		super(s);
		this.personality = personality;
	}
	public void playRound(GameState state) {
		numActions = 1;
		totalWorth = 0;
		totalBuys = 1;
		Card selectedCard;
		while(hand.totalOfType(CardType.Action) != 0){
			selectedCard = selectCard(hand, state);
			ActionHandler.handleActionCard(state, state.currentPlayer, (ActionCard)selectedCard);
		}
		//This worth value is wrong
		//I think this is fixed
		totalWorth += hand.totalMoney();
		while(totalBuys > 0){
			selectBuy(state);
		}
	}

	@Override
	public Card selectBuy(GameState state) {
		float bestValue = 0;
		Card bestCard = new BlankCard();
		for (Deck deck : state.buyOptions) {
			AttributeVector vec = personality.getVector(deck.getCardAt(0));
			if (AttributeVectorEvaluator.EvaluateVector(vec, state) > bestValue) {
				bestCard = deck.getCardAt(0);
				bestValue = AttributeVectorEvaluator.EvaluateVector(vec, state);
			}
		}
		return bestCard;
	}
	public Card selectCard(Deck d, GameState state) {
		float bestValue = 0;
		Card bestCard = new BlankCard();
		Card currentCard = new BlankCard();
		while (!d.isEmpty()) {
			currentCard = d.draw();
			AttributeVector vec = personality.getVector(currentCard);
			if (AttributeVectorEvaluator.EvaluateVector(vec, state) > bestValue) {
				bestCard = currentCard;
				bestValue = AttributeVectorEvaluator.EvaluateVector(vec, state);
			}
		}
		return bestCard;
	}
	@Override
	public Card selectCard(Deck d, CardType t, GameState state) {
		// TODO Auto-generated method stub
		return null;
	}
}