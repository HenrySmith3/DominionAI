package model;

public abstract class Player {
	public String name;
	public Deck drawPile;
	public Deck hand;
	public Deck discard;
	public int victoryPoints;
	public Player(String s) throws InstantiationException, IllegalAccessException{
		name = s;
		victoryPoints = 3;
		Card temp = new Copper();
		drawPile = new Deck(temp, 7);
		for(int i=0;i<3;i++)
			drawPile.add(new Estate());
		hand = new Deck();
		discard = new Deck();
	}
	public void draw(){
		hand.add(drawPile.draw());
	}
	public Card drawCheck(){
		if(drawPile.isEmpty())
			drawPile.mergeShuffle(discard);
		return drawPile.removeCardAt(0);
	}
	public void draw(int n){
		for(int i=0;i<n;i++){
			if(drawPile.isEmpty()){
				drawPile.mergeShuffle(discard);
				hand.add(drawPile.draw());
			}
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
	public abstract void playRound(GameState state);
	public abstract Card selectBuy(int total, GameState state);
	public abstract Card selectCard(Deck d, GameState state);
	public Card selectCard(Deck d,CardType t, GameState state){
		Deck subD = d.makeSubDeck(t);
		return selectCard(subD,state);
	}
}