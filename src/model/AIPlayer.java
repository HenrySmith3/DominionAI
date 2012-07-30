package model;

public class AIPlayer extends Player{
	
	//TODO fix so that it handles chapels and cellars
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
			totalBuys--;
			buy(selectBuy(state), state);
		}
	}

	@Override
	public Card selectBuy(GameState state) {
		//TODO fix so that it buys the most expensive card possible.
		float bestValue = 0;
		Card bestCard = new BlankCard();
		int costAtLeast = totalWorth;
		Deck canBuy;
		while (costAtLeast >= 2) {
			canBuy = new Deck();
			for (Deck deck : state.buyOptions) {
				if (deck.getCardAt(0).cost > costAtLeast) {
					canBuy.add(deck.getCardAt(0));
				}
			}
			for (int i = 0; i < canBuy.size(); i++) {
				Card card = canBuy.getCardAt(i);
				AttributeVector vec = personality.getVector(card);
				if (AttributeVectorEvaluator.EvaluateVector(vec, state) > bestValue) {
					bestCard = card;
					bestValue = AttributeVectorEvaluator.EvaluateVector(vec, state);
				}
			}
			costAtLeast--;
		}
		System.out.println(this + " just bought a " + bestCard);
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
		System.out.println(this + " plays a " + bestCard);
		return bestCard;
	}
	@Override
	public Card selectCard(Deck d, CardType t, GameState state) {
		// TODO Auto-generated method stub
		return null;
	}
}