package model;

public abstract class Player {
	public String name;
	public Deck drawPile;
	public Deck hand;
	public Deck discard;
	public int victoryPoints;
	public int totalBuys;
	public int totalWorth;
	public int numActions;
	public static Card selectedCardGUI = null;
	public Player(String s) throws InstantiationException, IllegalAccessException{
		name = s;
		victoryPoints = 3;
		Card temp = new Copper();
		drawPile = new Deck(temp, 7);
		for(int i=0;i<3;i++)
			drawPile.add(new Estate());
		drawPile.shuffle();
		hand = new Deck();
		discard = new Deck();
	}
	public void draw(){
		hand.add(drawCheck());
	}
	public Card drawCheck(){
		if(drawPile.isEmpty())
			drawPile.mergeShuffle(discard);
		if(drawPile.isEmpty())
			return null;
		return drawPile.removeCardAt(0);
	}
	public void draw(int n){
		for(int i=0;i<n;i++){
			draw();
		}
	}
	public void discard(){
		discard.merge(hand);
	}
	public void shuffle(){
		drawPile.mergeShuffle(discard);
	}
	public void newHand(){
		discard();
		if(drawPile.size()<5)
			drawPile.mergeShuffle(discard);
		int i = 0;
		while(i<5 && drawPile.size() != 0){
			draw();
			i++;
		}
	}
	public Deck allCards(){
		return new Deck(drawPile,hand,discard);
	}
	
	public Card buyCard(Card c,GameState state){
		if(c.cost > totalWorth)
			return null;
		if(c instanceof BlankCard){
			totalBuys = 0;
			return null;
		}
		discard.add(c);
		totalWorth -= c.cost;
		totalBuys--;
		state.bought(c);
		return c;
	}
	
	public abstract void playRound(GameState state);
	public abstract Card selectBuy(GameState state);
	
	//Wrapper function to encorporate existing architecture
	public Card selectBuy(int total,GameState state){
		totalWorth = total;
		Card c = selectBuy(state);
		totalWorth = 0;
		return c;
	}
	//Handle an action from your hand
	public void handleAction(ActionCard c,GameState g){
		if(c != null){
			hand.remove(c);
			g.inPlay.add(c);
			ActionHandler.handleActionCard(g, this, c);
			numActions--;
		}
	}
	public abstract Card selectCard(Deck d,CardType t, GameState state);
	public abstract Card selectCard(Deck d,CardType t,GameState state,String message);
	
	public String toString(){
		return name;
	}
}