package model;

import java.util.LinkedList;

public class AttributeVectorEvaluator {
	
	public static float EvaluateVector(AttributeVector vec, GameState state,Player p,Card c) {
		float result = 0;
		boolean givesAction = c.type == CardType.Action;
		givesAction = givesAction && 
					(((ActionCard)c).identity == ActionCardTypes.Cellar ||
					((ActionCard)c).identity == ActionCardTypes.Festival ||
					((ActionCard)c).identity == ActionCardTypes.Labratory ||
					((ActionCard)c).identity == ActionCardTypes.Market ||
					((ActionCard)c).identity == ActionCardTypes.Village);
		//we can't use == because we're comparing floats.
		//if it's less than .001, it's zero.
		//Bro we never multiply by the appropriate attribute hueristic
		result += (vec.percentageLeft < .001) ? 0 : percentageLeft(state)*vec.percentageLeft;
		result += (vec.minTurnsRequiredToEnd < .001) ? 0 : minTurnsRequiredToEnd(state)*vec.minTurnsRequiredToEnd;
		result += (vec.expMoneyInHand < .001) ? 0 : expMoneyInHand(state,p)*vec.expMoneyInHand;
		result += (vec.percentVictoryCards < .001) ? 0 : percentVictoryCards(state,p)*vec.percentVictoryCards;
		result += (vec.expNumBuys < .001) ? 0 : expNumBuys(state,p)*vec.expNumBuys;
		result += (vec.perEstate < .001) ? 0 : perEstate(state,p)*vec.perEstate;
		result += (vec.perDuchy < .001) ? 0 : perDuchy(state,p)*vec.perDuchy;
		result += (vec.perProvince < .001) ? 0 : perProvince(state,p)*vec.perProvince;
		result += (vec.expValueNextCard < .001) ? 0 : expValueNextCard(state,p,p.drawPile, givesAction)*vec.expValueNextCard;
		result += (vec.expValueDiscard < .001) ? 0 : expValueDiscard(state,p)*vec.expValueDiscard;
		result += (vec.expValueHand < .001) ? 0 : expValueHand(state,p)*vec.expValueHand;
		result += (vec.expValueEnemyDecks < .001) ? 0 : expValueEnemyDecks(state,p)*vec.expValueEnemyDecks;
		result += (vec.expValueCosting5 < .001) ? 0 : expValueCosting5(state,p)*vec.expValueCosting5;
		result += (vec.cardsCosting5 < .001) ? 0 : cardsCosting5(state)*vec.cardsCosting5;
		result += (vec.cursesRemaining < .001) ? 0 : cursesRemaining(state)*vec.cursesRemaining;
		result += (vec.expEnemiesDrawingWitches < .001) ? 0 : expEnemiesDrawingWitches(state)*vec.expEnemiesDrawingWitches;
		result += (vec.currentlyWinning < .001) ? 0 : currentlyWinning(state,p)*vec.currentlyWinning;
		result += (vec.moneyDistribution < .001) ? 0 : moneyDistribution(state,p)*vec.moneyDistribution;
		result += (vec.invPercentageLeft < .001) ? 0 : invPercentageLeft(state)*vec.invPercentageLeft;
		result += (vec.victoryEfficiency < .001) ? 0 : victoryEfficiency(state,p)*vec.victoryEfficiency;
		result += (vec.invExpValueEnemyDecks < .001) ? 0 : invExpValueEnemyDecks(state,p)*vec.invExpValueEnemyDecks;
		result += (vec.invPercentVictoryCards < .001) ? 0 : invPercentVictoryCards(state,p)*vec.invPercentVictoryCards;
		result += (vec.perActionCards < .001) ? 0 : perActionCards(state,p)*vec.perActionCards;
		result += (vec.perCopper < .001) ? 0 : perCopper(state,p)*vec.perCopper;
		result += (vec.invPerActionCards < .001) ? 0 : invPerActionCards(state,p)*vec.perActionCards;
		result += (vec.percentMoney < .001) ? 0 : percentMoney(state,p)*vec.percentMoney;
		result += (vec.cardsCosting4 < .001) ? 0 : cardsCosting4(state)*vec.cardsCosting4;
		result += (vec.alwaysOne < .001) ? 0 : alwaysOne(state)*vec.alwaysOne;
		result += (vec.invMoneyDistribution < .001) ? 0 : invMoneyDistribution(state,p)*vec.invMoneyDistribution;
		
		return result;
		
	}
	public static float percentageLeft(GameState state) {
           //perhaps make better later.
           int estimatedTurns = 20*state.numOfPlayers;
           int turns = state.numTurns;
           if (turns >= estimatedTurns) {
                   turns = estimatedTurns;
           }
           
           return 1- turns/estimatedTurns;
   }
	public static float invPercentageLeft(GameState state){
		   return 1-percentageLeft(state);
	}
   
   public static float minTurnsRequiredToEnd (GameState state) {
           boolean canEndInOne = false;
           boolean canEndInTwo = false;
           if (state.provinces.size()<2) {
                   canEndInOne = true;
           }
           int twoCardDecks = 0;
           int oneCardDecks = 0;
           int emptyDecks = 0;
           for (Deck deck : state.communityPiles) {
                   if (deck.size() == 0) {
                           ++emptyDecks;
                   }
                   else if (deck.size() == 1) {
                           ++oneCardDecks;
                   }
                   else if (deck.size() == 2){
                           ++twoCardDecks;
                   }
           }
           if (emptyDecks > 1 && oneCardDecks > 0) {
                   canEndInOne = true;
           }
           if ((emptyDecks >1 && twoCardDecks > 1) || (oneCardDecks>2)) {
                   canEndInTwo = true;
           }
           if (canEndInOne) return 1;
           if (canEndInTwo) return .5f;
           return 0;
   }
   
   public static float expMoneyInHand(GameState state,Player player) {
           Deck playerCards = player.allCards();
           Deck moneyCards = playerCards.makeSubDeck(CardType.Money);
           float percentageCopper = moneyCards.totalOfCard(Copper.class)*1f/moneyCards.size();
           float percentageSilver = moneyCards.totalOfCard(Silver.class)*1f/moneyCards.size();
           float percentageGold = moneyCards.totalOfCard(Gold.class)*1f/moneyCards.size();
           float expectedForDraw = percentageCopper*0.33f + percentageSilver*0.66f + percentageGold; //Hard coded money values
           return expectedForDraw; //Way overestimates your moneydraws, but is not a recursive call
   }
   public static float percentVictoryCards(GameState state,Player player) {
	   Deck playerCards = player.allCards();
       Deck victoryCards = playerCards.makeSubDeck(CardType.Victory);
       
       return ((float)victoryCards.size())/playerCards.size();
   }
   public static float percentMoney(GameState state,Player player) {
       Deck playerCards = player.allCards();
       Deck moneyCards = playerCards.makeSubDeck(CardType.Money);
       
       return ((float)moneyCards.size())/playerCards.size();
   }
   public static float perActionCards(GameState state,Player player) {
       Deck playerCards = player.allCards();
       int numAction = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   if (playerCards.getCardAt(i).type == CardType.Action) {
    		   ++numAction;
    	   }
       }
       return ((float)numAction)/playerCards.size();
   }
   public static float invPerActionCards(GameState state,Player player){
	   return 1-percentageLeft(state);
}
   public static float invPercentVictoryCards(GameState state,Player player){
	   return 1-percentVictoryCards(state,player);
   }
   public static float expNumBuys(GameState state,Player player) {
       Deck playerCards = player.allCards();
       int numBuys = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   Card card = playerCards.getCardAt(i);
    	   if (card instanceof ActionCard) {
    		   ActionCard ac = (ActionCard) card;
    		   if (ac.identity == ActionCardTypes.Market || 
    		       ac.identity == ActionCardTypes.WoodCutter ||
    		       ac.identity == ActionCardTypes.Festival) {
    			   ++numBuys;
    		   }
    	   }
       }
       float response = ((float)numBuys*2)/playerCards.size();
       if (response > 1) {
    	   response = 1;
       }
       return (((float)numBuys)/playerCards.size());
   }
   public static float perEstate(GameState state,Player player) {
       Deck playerCards = player.allCards();
       int numEstate = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   if (playerCards.getCardAt(i) instanceof Estate) {
    		   ++numEstate;
    	   }
       }
       return ((float)numEstate)/playerCards.size();
   }
   public static float perDuchy(GameState state,Player player) {
	   
       Deck playerCards = player.allCards();
       int numDuchy = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   if (playerCards.getCardAt(i) instanceof Estate) {
    		   ++numDuchy;
    	   }
       }
       return ((float)numDuchy)/playerCards.size();
   }
   public static float perCopper(GameState state,Player player) {
	   
       Deck playerCards = player.allCards();
       int numCopper = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   if (playerCards.getCardAt(i) instanceof Copper) {
    		   ++numCopper;
    	   }
       }
       return ((float)numCopper)/playerCards.size();
   }
   public static float perProvince(GameState state,Player player) {
	   
       Deck playerCards = player.allCards();
       int numProvince = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   if (playerCards.getCardAt(i) instanceof Estate) {
    		   ++numProvince;
    	   }
       }
       return ((float)numProvince)/playerCards.size();
   }
   //Total value of deck
   //HELPER FUNCTION
   public static float expValueDeck(GameState state,Player player,Deck d) {
	   Deck playerCards = player.allCards();
	   float expValueTotal = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   Card card = playerCards.getCardAt(i);
    	   if (player instanceof AIPlayer) {
    		   AIPlayer aiPlayer = (AIPlayer)player;
    		   //To fix infinite recursion.
    		   if (aiPlayer.personality.getVector(card).expValueNextCard != 0) {
    			   return .5f;
    		   }
    		   expValueTotal += AttributeVectorEvaluator.EvaluateVector(aiPlayer.personality.getVector(card), state,player, card);
    	   }
    	   
       }
       return expValueTotal/playerCards.size();
   }
   //The value of a draw
   //The deck is used for context
   //The actions wanted are card specific
   public static float expValueNextCard(GameState state,Player player,Deck d,boolean actionsWanted) {
	   float expValueTotal = 0;
       for (int i = 0; i < d.size(); i++) {
    	   Card card = d.getCardAt(i);
    	   if(card.type == CardType.Money){
    		   if(card instanceof Copper)
    			   expValueTotal += 0.33;
    		   else if(card instanceof Silver)
    			   expValueTotal += 0.66;
    		   else if(card instanceof Gold)
    			   expValueTotal += 1;
    	   }
    	   else if(card.type == CardType.Action && actionsWanted){
    		   if (player instanceof AIPlayer) {
        		   AIPlayer aiPlayer = (AIPlayer)player;
        		   expValueTotal += AttributeVectorEvaluator.EvaluateVector(aiPlayer.personality.getVector(card),state,player, card);
        	   }
    	   }
       }
       return expValueTotal/d.size();
   }
   //Card c should be in deck d
   public static float expValueNextCard(GameState state,Player player,Deck d,boolean actionsWanted,Card c) {
	   Deck cloneD = new Deck(d);
	   cloneD.remove(c); //This card must be in the deck
	   return expValueNextCard(state,player,cloneD,actionsWanted);
   }
   
   public static float expValueDiscard(GameState state,Player player) {
	   
       Deck playerCards = player.discard;
       int expValueTotal = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   Card card = playerCards.getCardAt(i);
    	   if (player instanceof AIPlayer) {
    		   AIPlayer aiPlayer = (AIPlayer)player;
    		   //To fix infinite recursion.
    		   if (aiPlayer.personality.getVector(card).expValueDiscard != 0) {
    			   return .5f;
    		   }
    		   expValueTotal += AttributeVectorEvaluator.EvaluateVector(aiPlayer.personality.getVector(card), state,player, card);
    	   }
       }
       return ((float)expValueTotal)/playerCards.size();
   }
   public static float expValueHand(GameState state,Player player) {
	   
       Deck playerCards = player.hand;
       int expValueTotal = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   Card card = playerCards.getCardAt(i);
    	   if (player instanceof AIPlayer) {
    		   AIPlayer aiPlayer = (AIPlayer)player;
    		   //To fix infinite recursion.
    		   if (aiPlayer.personality.getVector(card).expValueHand != 0) {
    			   return .5f;
    		   }
    		   expValueTotal += AttributeVectorEvaluator.EvaluateVector(aiPlayer.personality.getVector(card), state,player, card);
    	   }
       }
       return ((float)expValueTotal)/playerCards.size();
   }
   public static float expValueEnemyDecks(GameState state,Player player) {
	   LinkedList<Player> players = (LinkedList<Player>)state.players.clone();
	   float bestexpValueEnemy = 0;
	   for(Player aPlayer : players){
		   Math.max(bestexpValueEnemy,expValueDeck(state,aPlayer, aPlayer.drawPile));
	   }
	   return bestexpValueEnemy;
   }
   public static float invExpValueEnemyDecks(GameState state,Player player){
	   return 1-expValueEnemyDecks(state,player);
   }
   public static float expValueCosting5(GameState state,Player player) {
	   
       float maxExpValue = 0;
       for (Deck deck : state.buyOptions) {
    	   if (deck.size() == 0) {
    		   continue;
    	   }
    	   Card card = deck.getCardAt(0);
    	   if (player instanceof AIPlayer) {
    		   AIPlayer aiPlayer = (AIPlayer)player;
    		   float expValue = AttributeVectorEvaluator.EvaluateVector(aiPlayer.personality.getVector(card), state,player, card);
    		   if (expValue > maxExpValue) {
    			   maxExpValue = expValue;
    		   }
    	   }
       }
       return maxExpValue;
   }
   public static float cardsCosting5(GameState state) {
	   float cardsCosting5 = 0;
       for (Deck deck : state.buyOptions) {
    	   if (deck.size() == 0) {
    		   continue;
    	   }
    	   Card card = deck.getCardAt(0);
    	   if (card.cost == 5) {
    		   ++cardsCosting5;
    	   }
       }
       if(cardsCosting5/5>=1)
    	   return 1;
       return cardsCosting5/5;
   }
   public static float cardsCosting4(GameState state) {
	   float cardsCosting4 = 0;
       for (Deck deck : state.buyOptions) {
    	   if (deck.size() == 0) {
    		   continue;
    	   }
    	   Card card = deck.getCardAt(0);
    	   if (card.cost == 4) {
    		   ++cardsCosting4;
    	   }
       }
       if(cardsCosting4/5>=1)
    	   return 1;
       return cardsCosting4/5; //Probably not gonna go out of bounds
   }
   public static float cursesRemaining(GameState state) {
	   float curses = 0;
       for (Deck deck : state.buyOptions) {
    	   if (deck.size() == 0) {
    		   continue;
    	   }
    	   Card card = deck.getCardAt(0);
    	   if (card instanceof Curse) {
    		   return deck.size();
    	   }
       }
       return curses;
   }
   public static float expEnemiesDrawingWitches(GameState state) {
	   LinkedList<Player> players = (LinkedList<Player>)state.players.clone();
	   players.remove(state.currentPlayer);
	   float witches;
	   float expWitches = 0;
	   for (Player player : players) {
		   witches = 0;
	       Deck playerCards = player.allCards();
	       float playerExpValue = 0;
	       for (int i = 0; i < playerCards.size(); i++) {
	    	   Card card = playerCards.getCardAt(i);
	    	   if (card instanceof ActionCard) {
	    		   ActionCard ac = (ActionCard)card;
	    		   if (ac.identity == ActionCardTypes.Witch) {
	    			   ++witches;
	    		   }
	    	   }
	       }
	       expWitches += playerExpValue*3/playerCards.size();//Three is an arbitrary boost.
	   }
	   float result = expWitches/players.size();
	   return result;
   }
 //Returns 0 when you have only estates
   //Returns 1 when you have only Provinces
   public static float victoryEfficiency(GameState state,Player player){
	   Deck playerCards = state.currentPlayer.allCards();
	   Deck victoryCards = playerCards.makeSubDeck(CardType.Victory);
	   if(victoryCards.size() == 0)
		   return 0;
	   return ((victoryCards.totalVictory()*1f/victoryCards.size()) - 1)/5;
   }
   public static float moneyDistribution(GameState state,Player player){
       Deck playerCards = player.allCards();
       Deck moneyCards = playerCards.makeSubDeck(CardType.Money);
       int total = 0;
       float unScaledTotal = 0;
       for(int i=0;i<moneyCards.size();i++){
    	   Card c = moneyCards.getCardAt(i);
    	   if(c.getClass() == Silver.class)
    		   unScaledTotal += 0.5;
    	   else if(c.getClass() == Gold.class)
    		   unScaledTotal += 1;
    	   total++;
       }
       return unScaledTotal/total;
   }
   public static float invMoneyDistribution(GameState state,Player player){
	   return 1-moneyDistribution(state,player);
   }
   //I'm going to change this. 0 means we're losing badly. 1 means we're winning well. 0.5 means a tie
   public static float currentlyWinning(GameState state,Player player) {
	   LinkedList<Player> players = (LinkedList<Player>)state.players.clone();
	   players.remove(state.currentPlayer);
	   Deck curPlayerCards = state.currentPlayer.allCards();
	   int playerPoints = 0;
	   int inLeadBy = 120; //a negative value means we're behind the leader
	   for (int i = 0; i < curPlayerCards.size(); i++) { 
		   Card card = curPlayerCards.getCardAt(i);
		   playerPoints += card.victory;
	   }
	   for (Player aPlayer : players) {
		   int enemyPoints = aPlayer.allCards().totalVictory();
	       inLeadBy = Math.min(inLeadBy, playerPoints - enemyPoints);
	   }
	   //If we're ahead, behind by 18 points, we're really secure in our spot
	   //being 3 provinces ahead/behind is a really easy lead to keep, assuming a 20 turn game
	   if(inLeadBy>18)
		   inLeadBy = 18;
	   if(inLeadBy<-18)
		   inLeadBy = -18;
	   return (float) (inLeadBy/36f + 0.5);
   }
   public static float alwaysOne(GameState state) {
	   return 1f;//What else were you expecting?
   }
}