package model;

public class ActionHandler {
	public static void handleActionCard(GameState g, Player p,ActionCard a){
		int i = 0;
		Card temp = new BlankCard();
		int handSize = p.hand.size();
		Player playerFocus;
		switch (a.identity){
			case Adventurer:
				while(i != 2){
					temp = p.drawCheck();
					if(temp.type.equals(CardType.Money)){
						p.hand.add(temp);
						i++;
					}
					else{
						p.discard.add(temp);
					}
				}
			case Cellar:
				while(i != handSize && temp != null){
					temp = p.hand.removeCardAt(g.selectCard(p.hand));
					p.discard.add(temp);
					i++;
				}
				for(int j=0;j<i;j++){
					p.draw();
				}
			case Chancellor:
				System.out.println("Would you like to put your discard into your deck?");
				if(g.yesOrNo()){
					p.drawPile.mergeShuffle(p.discard);
				}
				g.currentWorth += 2;
			case Chapel:
				while(i != 4 && temp != null){
					temp = p.hand.removeCardAt(g.selectCard(p.hand));
					i++;
				}
			case CouncilRoom:
				p.draw(4);
				for(i=0;i<g.numOfPlayers-1;i++){
					playerFocus = g.players.getFirst();
					playerFocus.draw();
					g.players.add(playerFocus);
				}
			case Feast:
				p.hand.add(g.selectBuy(5));
			case Festival:
				g.numOfActions += 2;
				g.numOfBuys += 1;
				g.currentWorth += 2;
			case Market:
				g.numOfActions += 1;
				g.numOfBuys += 1;
				g.currentWorth += 1;
				p.draw();
			case Mine: //Not technically Right
				System.out.println("Select A Treasure for improving");
				temp = p.hand.removeCardAt(g.selectCard(p.hand,CardType.Money));
				if(temp.getClass().equals(Copper.class))
					p.hand.add(g.silvers.removeCardAt(0));
				if(temp.getClass().equals(Silver.class) || temp.getClass().equals(Gold.class))
					p.hand.add(g.golds.removeCardAt(0));
			case Village:
				p.draw();
				g.numOfActions += 2;
			case Workshop:
				temp = g.selectBuy(4);
				p.hand.add(temp);
			case Remodel:
				temp = p.hand.removeCardAt((g.selectCard(p.hand))); //A really terrible way to get to select a card from hand
				p.hand.add(g.selectBuy(temp.cost+2));
		}
	}
}