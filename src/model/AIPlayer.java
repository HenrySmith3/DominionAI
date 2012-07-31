package model;

public class AIPlayer extends Player{
	public static int i=0;
	//TODO fix so that it handles chapels and cellars
	Personality personality;
	public AIPlayer(String s, Personality personality) throws InstantiationException,
			IllegalAccessException {
		super(s);
		this.personality = personality;
	}
	public void playRound(GameState state) {
		i=0;
		numActions = 1;
		totalWorth = 0;
		totalBuys = 1;
		Card selectedCard;
		while(hand.totalOfType(CardType.Action) != 0 && numActions > 0){
			selectedCard = selectCard(hand,CardType.Action, state);
			if(selectedCard instanceof BlankCard)
				numActions = 0;
			else{
				state.inPlay.add(hand.remove(selectedCard));
				ActionHandler.handleActionCard(state, state.currentPlayer, (ActionCard)selectedCard);
				numActions--;
			}
		}
		//This worth value is wrong
		//I think this is fixed
		totalWorth += hand.totalMoney();
		while(totalBuys > 0){
			buyCard(selectBuy(state), state);
		}
	}

	@Override
	public Card selectBuy(GameState state) {
		i++;
		System.out.println(name+" is deciding what to buy....");
		float bestValue = 0;
		Card bestCard = null;
		int costAtLeast = totalWorth;
		Deck canBuy;
		while (costAtLeast >= 0 && bestCard == null) {
			canBuy = new Deck();
			for (Deck deck : state.buyOptions) {
				if (deck.getCardAt(0).cost == costAtLeast) {
					canBuy.add(deck.getCardAt(0));
				}
			}
			for (int i = 0; i < canBuy.size(); i++) {
				Card card = canBuy.getCardAt(i);
				AttributeVector vec = personality.getVector(card);
				float f = AttributeVectorEvaluator.EvaluateVector(vec, state, this, card); 
				if (f > bestValue) {
					bestCard = card;
					bestValue = f;
				}
			}
			costAtLeast--;
		}
		if(bestCard instanceof Copper)
			return new BlankCard();
		System.out.println(this + " just bought a " + bestCard);
		return bestCard;
	}
	public Card selectCard(Deck d, GameState state) {
		System.out.println(name+" is deciding what action card to play....");
		float bestValue = 0;
		Deck freeActionCards = d.getFreeActions();
		if(!freeActionCards.isEmpty()){
			System.out.println(this + " plays a " + freeActionCards.getCardAt(0));
			return freeActionCards.getCardAt(0);
		}
		Card bestCard = new BlankCard();
		for(int i=0;i<d.size();i++){
			Card currentCard = d.getCardAt(i);
			AttributeVector vec = personality.getVector(currentCard);
			float f = AttributeVectorEvaluator.EvaluateVector(vec, state, this, currentCard);
			if (f > bestValue) {
				bestCard = currentCard;
				bestValue = f;
			}
		}
		System.out.println(this + " plays a " + bestCard);
		return bestCard;
	}
	@Override
	public Card selectCard(Deck d, CardType t, GameState state) {
		if(t == null)
			return selectCard(d,state);
		Deck newD = d.makeSubDeck(t);
		return selectCard(newD,state);
	}
	public Card selectCard(Deck d, CardType t, GameState state,String message) {
		if(message.equalsIgnoreCase("Celler")){
			Deck victories = d.makeSubDeck(CardType.Victory);
			if(!victories.isEmpty()){
				return victories.getCardAt(0);
			}
			return new BlankCard();
		}
		else if(message.equalsIgnoreCase("Chapel")){
			Card temp = d.findCard(Curse.class);
			if(temp != null)
				return temp;
			temp = d.findCard(Estate.class);
			if(temp != null && AttributeVectorEvaluator.invPercentageLeft(state)< 0.2)
				return temp;
			temp = d.findCard(Copper.class);
			if(temp != null && AttributeVectorEvaluator.moneyDistribution(state, this) > 0.3)
				return temp;
			return new BlankCard();
		}
		else if(message.equalsIgnoreCase("Mine")){
			Deck monies = d.makeSubDeck(CardType.Money);
			Card copperHoldOn = null;
			for(int i=0;i<monies.size();i++){
				Card c = monies.getCardAt(i);
				if(c instanceof Silver)
					return c;
				else if(c instanceof Copper)
					copperHoldOn = c;
			}
			if(copperHoldOn == null)
				return new BlankCard();
			else
				return copperHoldOn;
		}
		return selectCard(d,state);
	}
}