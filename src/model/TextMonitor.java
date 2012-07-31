package model;

public class TextMonitor extends GameMonitor {

	@Override
	public void update(GameState state) {
		Player player = state.currentPlayer;
		System.out.println("In your discard: ");
		for (int i = 0; i < player.discard.size(); ++i) {
			System.out.println(player.discard.getCardAt(i));
		}
		System.out.println("In your draw: ");
		for (int i = 0; i < player.drawPile.size(); ++i) {
			System.out.println(player.drawPile.getCardAt(i));
		}
		System.out.println("In your hand: ");
		for (int i = 0; i < player.hand.size(); ++i) {
			System.out.println(player.hand.getCardAt(i));
		}
		
	}

}
