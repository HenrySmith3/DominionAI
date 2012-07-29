package model;

import gui.CardSelectionFrame;
import gui.GameStateFrame;

import java.io.IOException;

public class HumanPlayer extends Player {
	public HumanPlayer(String s) throws InstantiationException, IllegalAccessException {
		super(s);
	}

	//Trying to get a grasp on the heirarchy calls
	public Card selectBuy(GameState state){
		GameStateFrame gameStateFrame = new GameStateFrame(state);
		synchronized(this){
			try{
				wait();
			}
			catch (InterruptedException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return selectedCardGUI;
	}

	@Override
	//This should be the real used method
	public Card selectCard(Deck d,CardType t) {
		CardSelectionFrame guiPicker = new CardSelectionFrame(d,this,t);
		synchronized(this){
			try{
				wait();
			}
			catch (InterruptedException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return selectedCardGUI;
	}
}
