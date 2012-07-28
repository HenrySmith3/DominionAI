package model;

import gui.CardSelectionFrame;
import gui.GameStateFrame;

import java.io.IOException;

public class HumanPlayer extends Player {
	public static Card selectedCardGUI = null;
	public HumanPlayer(String s) throws InstantiationException, IllegalAccessException {
		super(s);
	}

	@Override
	public void playRound(GameState state) {
		numActions = 1;
		totalWorth = 0;
		totalBuys = 1;
		while(hand.totalOfType(CardType.Action) != 0 || selectedCardGUI == null){
			selectCard(hand, state);
			synchronized(this){
				try{
					wait();
				}
				catch (InterruptedException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(!(selectedCardGUI instanceof BlankCard)){
				ActionCard c = (ActionCard)selectedCardGUI;
				handleAction(c,state);
			}
		}
		selectedCardGUI = null;
		//This worth value is wrong
		//I think this is fixed
		totalWorth += hand.totalMoney();
		while(totalBuys > 0){
			selectBuy(state);
			synchronized(this){
				try{
					wait();
				}
				catch (InterruptedException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//Trying to get a grasp on the heirarchy calls
	public Card selectBuy(GameState state){
		GameStateFrame gameStateFrame = new GameStateFrame(state);
		return null;
	}

	//This should be the real used method
	public Card selectCard(Deck d, GameState state) {
		CardSelectionFrame guiPicker = new CardSelectionFrame(d, this);
		return selectedCardGUI;
	}
}
