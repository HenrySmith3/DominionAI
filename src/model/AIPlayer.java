package model;

public class AIPlayer extends Player{
	private AttributeVector currentStatus;
	public AIPlayer(String s) throws InstantiationException,
			IllegalAccessException {
		super(s);
	}

	public void playRound(GameState state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Card selectBuy(GameState state) {
		// TODO Auto-generated method stub
		return null;
	}
	public Card selectCard(Deck d, GameState state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card selectCard(Deck d,CardType t) {
		// TODO Auto-generated method stub
		return null;
	}
}