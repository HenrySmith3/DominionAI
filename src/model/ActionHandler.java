package model;

public class ActionHandler {
	public static void handleActionCard(GameState state, Player curPlayer,ActionCard card){
		int i = 0;
		Card temp = null;
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
				break;
			case Cellar:
				while(curPlayer.hand.size() != 0 && !(temp instanceof BlankCard)){
					temp = curPlayer.selectCard(curPlayer.hand,null,state,"Celler");
					if(!(temp instanceof BlankCard)){
						curPlayer.hand.remove(temp);
						curPlayer.discard.add(temp);
						i++;
					}
				}
				for(int j=0;j<i;j++){
					curPlayer.draw();
				}
				HumanPlayer.selectedCardGUI = card;//Shitty programming here
				curPlayer.numActions += 1;
				break;
			case Chancellor:
				System.out.println("Would you like to put your discard into your deck?");
				if(state.yesOrNo()){
					curPlayer.drawPile.mergeShuffle(curPlayer.discard);
				}
				curPlayer.totalWorth += 2;
				break;
			case Chapel:
				while(i != 4 && temp != null){
					temp = curPlayer.selectCard(curPlayer.hand, null, state,"Chapel");
					if(!(temp instanceof BlankCard)){
						temp = curPlayer.hand.remove(temp);
						i++;
					}
				}
				HumanPlayer.selectedCardGUI = card;//Shitty programming here
				break;
			case CouncilRoom:
				curPlayer.draw(4);
				for(i=0;i<state.numOfPlayers-1;i++){
					playerFocus = state.players.removeFirst();
					playerFocus.draw();
					state.players.add(playerFocus);
				}
				break;
			case Feast:
				temp = curPlayer.selectBuy(5, state);
				curPlayer.discard.add(temp);
				state.inPlay.remove(card);
				break;
			case Festival:
				curPlayer.numActions += 2;
				curPlayer.totalBuys += 1;
				curPlayer.totalWorth += 2;
				break;
			case Labratory:
				curPlayer.draw();
				curPlayer.draw();
				curPlayer.numActions += 1;
				break;
			/*case Library:
				while(curPlayer.hand.size()<7)
					curPlayer.draw();*/ //Isn't even correct.
			case Market:
				curPlayer.numActions += 1;
				curPlayer.totalBuys += 1;
				curPlayer.totalWorth += 1;
				curPlayer.draw();
				break;
			case Mine: //Not technically Right
				System.out.println("Select A Treasure for improving");
				temp = curPlayer.hand.remove(curPlayer.selectCard(curPlayer.hand,CardType.Money,state,"Mine"));
				if(temp.getClass().equals(Copper.class))
					curPlayer.hand.add(state.silvers.removeCardAt(0));
				if(temp.getClass().equals(Silver.class) || temp.getClass().equals(Gold.class))
					curPlayer.hand.add(state.golds.removeCardAt(0));
				break;
			case Moat:
				curPlayer.draw();
				curPlayer.draw();
				break;
			case Remodel:
				temp = curPlayer.hand.remove((curPlayer.selectCard(curPlayer.hand,null, state))); //A really terrible way to get to select a card from hand
				curPlayer.hand.add(curPlayer.selectBuy(temp.cost+2, state));
				curPlayer.totalBuys += 1; //To offset the free buy
				break;
			case Smithy:
				curPlayer.draw();
				curPlayer.draw();
				curPlayer.draw();
				break;
			case Village:
				curPlayer.draw();
				curPlayer.numActions += 2;
				break;
			case WoodCutter:
				curPlayer.totalWorth += 2;
				curPlayer.totalBuys += 1;
				break;
			case Workshop:
				temp = curPlayer.selectBuy(4, state);
				curPlayer.hand.add(temp);
				break;
		}
	}
}