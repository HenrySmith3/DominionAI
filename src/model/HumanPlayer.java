package model;

import java.io.IOException;

public class HumanPlayer extends Player {
	public HumanPlayer(String s) throws InstantiationException, IllegalAccessException {
		super(s);
	}

	@Override
	public void playRound(GameState state) {
		boolean finished = false;
		if(hand.totalOfType(CardType.Action) != 0 && state.numOfActions != 0 && !finished){
			int i=selectCard(hand,CardType.Action, state);
			if(i != -1){
				ActionCard selected = (ActionCard)hand.getCardAt(i);
				state.inPlay.add(selected);
				ActionHandler.handleActionCard(state, state.currentPlayer, selected);
				state.numOfActions--;
			}
		}
		
		int currentWorth = 0;
		currentWorth += hand.totalMoney();
		Card purchaseChoice = new BlankCard();
		while((currentWorth != 0 || purchaseChoice == null) && state.numOfBuys >= 1){
			purchaseChoice = selectBuy(currentWorth, state);
			if(purchaseChoice != null){
				currentWorth -= purchaseChoice.cost;
				discard.add(purchaseChoice);
				state.bought(purchaseChoice);
			} else {//if they didn't want to buy
				break;
			}
		}
		
	}
	public Card selectBuy(int total, GameState state){
		for(int i=0;i<state.buyOptions.size();i++){
			System.out.println(i+") "+state.buyOptions.get(i).getCardAt(0).toString()
					+ " Cost: " + state.buyOptions.get(i).getCardAt(0).cost);
		}
		Card c = new BlankCard();
		while(c.getClass() == BlankCard.class){
			System.out.println("Please select an appropriate buy, -1 to skip it.\nYou have " +
								state.numOfBuys + " buys remaining, and " + total + " gold to spend.\n");
			String line = "";
			try {
				line = state.b.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int i = Integer.parseInt(line);
			if(i == -1)
				return null;
			else
				c = state.buyOptions.get(i).getCardAt(0);
			if(c.cost > total){
				c = new BlankCard();
				System.out.println("That card cost too much");
			}
		}
		return c;
	}
	public int selectCard(Deck d, GameState state){
		if(d.isEmpty())
			return -1;
		System.out.println("Enter -1 to not select a card, indexing starts at 0");
		d.printDeck();
		String line = "";
		try {
			line = state.b.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int temp = Integer.parseInt(line);
		if((temp <= -1 || temp >= d.size()) && !(hand.getCardAt(temp) instanceof ActionCard)){
			System.out.println("Please pick another value");
			return selectCard(d, state);//I think recursive is more elegant than iterative.
			//also, the old system was causing an infinite loop if you pick the wrong card type.
		}
		else{
			return temp;
		}
	}
	
	public int selectCard(Deck d,CardType t, GameState state){
		System.out.println("Select a card of type"+t);
		int i = selectCard(d, state);
		if(i == -1)
			return i;
		while(!d.getCardAt(i).type.equals(t)){
			System.out.println("Not the right type. Select Again");
			return selectCard(d, t, state);
		}
		return i;
	}
		
}
