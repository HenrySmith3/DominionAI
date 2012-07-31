package model;

import gui.CardSelectionFrame;
import gui.GameStateFrame;

import java.io.IOException;

public class HumanPlayer extends Player {
	public HumanPlayer(String s) throws InstantiationException,
			IllegalAccessException {
		super(s);
	}

	// Trying to get a grasp on the heirarchy calls
	public Card selectBuy(GameState state) {
		GameStateFrame gameStateFrame = new GameStateFrame(state);
		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return selectedCardGUI;
	}

	// This should be the real used method
	public Card selectCard(Deck d, CardType t, GameState state) {
		CardSelectionFrame guiPicker = new CardSelectionFrame(d, this, t);
		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return selectedCardGUI;
	}

	public Card selectCard(Deck d, CardType t, GameState state, String message) {
		return selectCard(d, t, state);
	}

	public void playRound(GameState state) {
		numActions = 1;
		totalWorth = 0;
		totalBuys = 1;
		selectedCardGUI = null;
		while (!(selectedCardGUI instanceof BlankCard)) {
			Card c = selectCard(hand, CardType.Action, state);
			if (!(c instanceof BlankCard)) {
				handleAction((ActionCard) c, state);
			}
		}
		selectedCardGUI = null;
		// This worth value is wrong
		// I think this is fixed
		totalWorth += hand.totalMoney();
		Card c = null;
		while (totalBuys > 0 && !(selectedCardGUI instanceof BlankCard)) {
			buyCard(selectBuy(state), state);
		}
	}
}
