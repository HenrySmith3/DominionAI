package model;

public class ActionHandler {
	public static void handleActionCard(GameState state, Player curPlayer,ActionCard card){
		int i = 0;
		Card temp = new BlankCard();
		int handSize = curPlayer.hand.size();
		Player playerFocus;
		switch (card.identity){
			case Adventurer:
				while(i != 2 && curPlayer.drawPile.size() != 0){
					temp = curPlayer.drawCheck();
					if(temp.type.equals(CardType.Money)){
						curPlayer.hand.add(temp);
						i++;
					}
					else{
						curPlayer.discard.add(temp);
					}
				}
			case Cellar:
				while(i != handSize && temp != null){
					temp = curPlayer.hand.removeCardAt(curPlayer.selectCard(curPlayer.hand, state));
					curPlayer.discard.add(temp);
					i++;
				}
				for(int j=0;j<i;j++){
					curPlayer.draw();
				}
			case Chancellor:
				System.out.println("Would you like to put your discard into your deck?");
				if(state.yesOrNo()){
					curPlayer.drawPile.mergeShuffle(curPlayer.discard);
				}
				state.currentWorth += 2;
			case Chapel:
				while(i != 4 && temp != null){
					temp = curPlayer.hand.removeCardAt(curPlayer.selectCard(curPlayer.hand, state));
					i++;
				}
			case CouncilRoom:
				curPlayer.draw(4);
				for(i=0;i<state.numOfPlayers-1;i++){
					playerFocus = state.players.getFirst();
					playerFocus.draw();
					state.players.add(playerFocus);
				}
			case Feast:
				curPlayer.hand.add(curPlayer.selectBuy(5, state));
				state.inPlay.remove(card);
			case Festival:
				state.numOfActions += 2;
				state.numOfBuys += 1;
				state.currentWorth += 2;
			case Labratory:
				curPlayer.draw();
				curPlayer.draw();
				state.numOfActions += 1;
			/*case Library:
				while(curPlayer.hand.size()<7)
					curPlayer.draw();*/ //Isn't even correct.
			case Market:
				state.numOfActions += 1;
				state.numOfBuys += 1;
				state.currentWorth += 1;
				curPlayer.draw();
			case Mine: //Not technically Right
				System.out.println("Select A Treasure for improving");
				temp = curPlayer.hand.removeCardAt(curPlayer.selectCard(curPlayer.hand,CardType.Money, state));
				if(temp.getClass().equals(Copper.class))
					curPlayer.hand.add(state.silvers.removeCardAt(0));
				if(temp.getClass().equals(Silver.class) || temp.getClass().equals(Gold.class))
					curPlayer.hand.add(state.golds.removeCardAt(0));
			case Moat:
				curPlayer.draw();
				curPlayer.draw();
			case Remodel:
				temp = curPlayer.hand.removeCardAt((curPlayer.selectCard(curPlayer.hand, state))); //A really terrible way to get to select a card from hand
				curPlayer.hand.add(curPlayer.selectBuy(temp.cost+2, state));
			case Village:
				curPlayer.draw();
				state.numOfActions += 2;
			case WoodCutter:
				state.currentWorth += 2;
				state.numOfBuys += 1;
			case Workshop:
				temp = curPlayer.selectBuy(4, state);
				curPlayer.hand.add(temp);
		}
	}
}