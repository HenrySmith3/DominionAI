package model;

import java.util.LinkedList;

public class AttributeVectorEvaluator {
	public static float EvaluateVector(AttributeVector vec, GameState state) {
		float result = 0;
		//we can't use == because we're comparing floats.
		//if it's less than .001, it's zero.
		result += (vec.percentageLeft < .001) ? 0 : percentageLeft(state);
		result += (vec.minTurnsRequiredToEnd < .001) ? 0 : minTurnsRequiredToEnd(state);
		result += (vec.expMoneyInHand < .001) ? 0 : expMoneyInHand(state);
		result += (vec.percentVictoryCards < .001) ? 0 : percentVictoryCards(state);
		result += (vec.expNumBuys < .001) ? 0 : expNumBuys(state);
		result += (vec.perEstate < .001) ? 0 : perEstate(state);
		result += (vec.perDuchy < .001) ? 0 : perDuchy(state);
		result += (vec.perProvince < .001) ? 0 : perProvince(state);
		result += (vec.expValueNextCard < .001) ? 0 : expValueNextCard(state);
		result += (vec.expValueDraw < .001) ? 0 : expValueDraw(state);
		result += (vec.expValueDiscard < .001) ? 0 : expValueDiscard(state);
		result += (vec.expValueHand < .001) ? 0 : expValueHand(state);
		result += (vec.expValueEnemyDecks < .001) ? 0 : expValueEnemyDecks(state);
		result += (vec.expValueCosting5 < .001) ? 0 : expValueCosting5(state);
		result += (vec.cardsCosting5 < .001) ? 0 : cardsCosting5(state);
		result += (vec.cursesRemaining < .001) ? 0 : cursesRemaining(state);
		result += (vec.expEnemiesDrawingWitches < .001) ? 0 : expEnemiesDrawingWitches(state);
		result += (vec.currentlyWinning < .001) ? 0 : currentlyWinning(state);
		result += (vec.moneyDistribution < .001) ? 0 : moneyDisribution(state);
		result += (vec.invPercentageLeft < .001) ? 0 : invPercentageLeft(state);
		result += (vec.victoryEfficiency < .001) ? 0 : victoryEfficiency(state);
		result += (vec.invExpValueEnemyDecks < .001) ? 0 : invExpValueEnemyDecks(state);
		result += (vec.invPercentVictoryCards < .001) ? 0 : invPercentVictoryCards(state);
		result += (vec.perActionCards < .001) ? 0 : perActionCards(state);
		result += (vec.perCopper < .001) ? 0 : perCopper(state);
		result += (vec.invPerActionCards < .001) ? 0 : invPerActionCards(state);
		result += (vec.percentMoney < .001) ? 0 : percentMoney(state);
		result += (vec.cardsCosting4 < .001) ? 0 : cardsCosting4(state);
		result += (vec.alwaysOne < .001) ? 0 : alwaysOne(state);
		result += (vec.invMoneyDistribution < .001) ? 0 : invMoneyDistribution(state);
		
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
   
   public static float expMoneyInHand(GameState state) {
           Player player = state.currentPlayer;
           Deck playerCards = player.allCards();
           Deck moneyCards = playerCards.makeSubDeck(CardType.Money);
           float percentageMoney = (moneyCards.size()*1f)/playerCards.size();
           float percentageCopper = moneyCards.totalOfCard(Copper.class)*1f/moneyCards.size();
           float percentageSilver = moneyCards.totalOfCard(Silver.class)*1f/moneyCards.size();
           float percentageGold = moneyCards.totalOfCard(Gold.class)*1f/moneyCards.size();
           float expectedForDraw = percentageCopper*0.33f + percentageSilver*0.66f + percentageGold; //Hard coded money values
           return expectedForDraw; //Way overestimates your moneydraws, but is not a recursive call
   }
   public static float moneyDisribution(GameState state){
	   Player player = state.currentPlayer;
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
   public static float percentVictoryCards(GameState state) {
	   Player player = state.currentPlayer;
       Deck playerCards = player.allCards();
       int numVictory = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   if (playerCards.getCardAt(i).type == CardType.Victory) {
    		   ++numVictory;
    	   }
       }
       return ((float)numVictory)/playerCards.size();
   }
   public static float percentMoney(GameState state) {
	   Player player = state.currentPlayer;
       Deck playerCards = player.allCards();
       int numVictory = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   if (playerCards.getCardAt(i).type == CardType.Money) {
    		   ++numVictory;
    	   }
       }
       return ((float)numVictory)/playerCards.size();
   }
   public static float perActionCards(GameState state) {
	   Player player = state.currentPlayer;
       Deck playerCards = player.allCards();
       int numAction = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   if (playerCards.getCardAt(i).type == CardType.Action) {
    		   ++numAction;
    	   }
       }
       return ((float)numAction)/playerCards.size();
   }
   public static float invPerActionCards(GameState state){
	   return 1-percentageLeft(state);
}
   public static float invPercentVictoryCards(GameState state){
	   return 1-percentVictoryCards(state);
   }
   public static float expNumBuys(GameState state) {
	   Player player = state.currentPlayer;
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
   public static float perEstate(GameState state) {
	   Player player = state.currentPlayer;
       Deck playerCards = player.allCards();
       int numEstate = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   if (playerCards.getCardAt(i) instanceof Estate) {
    		   ++numEstate;
    	   }
       }
       return ((float)numEstate)/playerCards.size();
   }
   public static float perDuchy(GameState state) {
	   Player player = state.currentPlayer;
       Deck playerCards = player.allCards();
       int numDuchy = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   if (playerCards.getCardAt(i) instanceof Estate) {
    		   ++numDuchy;
    	   }
       }
       return ((float)numDuchy)/playerCards.size();
   }
   public static float perCopper(GameState state) {
	   Player player = state.currentPlayer;
       Deck playerCards = player.allCards();
       int numCopper = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   if (playerCards.getCardAt(i) instanceof Copper) {
    		   ++numCopper;
    	   }
       }
       return ((float)numCopper)/playerCards.size();
   }
   public static float perProvince(GameState state) {
	   Player player = state.currentPlayer;
       Deck playerCards = player.allCards();
       int numProvince = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   if (playerCards.getCardAt(i) instanceof Estate) {
    		   ++numProvince;
    	   }
       }
       return ((float)numProvince)/playerCards.size();
   }
   public static float expValueNextCard(GameState state) {
	   Player player = state.currentPlayer;
       Deck playerCards = player.allCards();
       int expValueTotal = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   Card card = playerCards.getCardAt(i);
    	   if (player instanceof AIPlayer) {
    		   AIPlayer aiPlayer = (AIPlayer)player;
    		   expValueTotal += AttributeVectorEvaluator.EvaluateVector(aiPlayer.personality.getVector(card), state);
    	   }
    	   
       }
       return ((float)expValueTotal)/playerCards.size();
   }
   public static float expValueDraw(GameState state) {
	   Player player = state.currentPlayer;
       Deck playerCards = player.drawPile;
       int expValueTotal = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   Card card = playerCards.getCardAt(i);
    	   if (player instanceof AIPlayer) {
    		   AIPlayer aiPlayer = (AIPlayer)player;
    		   expValueTotal += AttributeVectorEvaluator.EvaluateVector(aiPlayer.personality.getVector(card), state);
    	   }      
       }
       return ((float)expValueTotal)/playerCards.size();
   }
   public static float expValueDiscard(GameState state) {
	   Player player = state.currentPlayer;
       Deck playerCards = player.discard;
       int expValueTotal = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   Card card = playerCards.getCardAt(i);
    	   if (player instanceof AIPlayer) {
    		   AIPlayer aiPlayer = (AIPlayer)player;
    		   expValueTotal += AttributeVectorEvaluator.EvaluateVector(aiPlayer.personality.getVector(card), state);
    	   }
       }
       return ((float)expValueTotal)/playerCards.size();
   }
   public static float expValueHand(GameState state) {
	   Player player = state.currentPlayer;
       Deck playerCards = player.hand;
       int expValueTotal = 0;
       for (int i = 0; i < playerCards.size(); i++) {
    	   Card card = playerCards.getCardAt(i);
    	   if (player instanceof AIPlayer) {
    		   AIPlayer aiPlayer = (AIPlayer)player;
    		   expValueTotal += AttributeVectorEvaluator.EvaluateVector(aiPlayer.personality.getVector(card), state);
    	   }
       }
       return ((float)expValueTotal)/playerCards.size();
   }
   public static float expValueEnemyDecks(GameState state) {
	   LinkedList<Player> players = (LinkedList<Player>)state.players.clone();
	   players.remove(state.currentPlayer);
	   float expValueTotal = 0;
	   for (Player player : players) {
	       Deck playerCards = player.allCards();
	       float playerExpValue = 0;
	       for (int i = 0; i < playerCards.size(); i++) {
	    	   Card card = playerCards.getCardAt(i);
	    	   if (player instanceof AIPlayer) {
	    		   AIPlayer aiPlayer = (AIPlayer)player;
	    		   expValueTotal += AttributeVectorEvaluator.EvaluateVector(aiPlayer.personality.getVector(card), state);
	    	   }
	       }
	       expValueTotal += playerExpValue/players.size();
	   }
	   return expValueTotal/players.size();
   }
   public static float invExpValueEnemyDecks(GameState state){
	   return 1-expValueEnemyDecks(state);
   }
   public static float expValueCosting5(GameState state) {
	   Player player = state.currentPlayer;
       float maxExpValue = 0;
       for (Deck deck : state.buyOptions) {
    	   if (deck.size() == 0) {
    		   continue;
    	   }
    	   Card card = deck.getCardAt(0);
    	   if (player instanceof AIPlayer) {
    		   AIPlayer aiPlayer = (AIPlayer)player;
    		   float expValue = AttributeVectorEvaluator.EvaluateVector(aiPlayer.personality.getVector(card), state);
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
       return cardsCosting5;
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
       return cardsCosting4;
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
   public static float victoryEfficiency(GameState state){
	   Deck playerCards = state.currentPlayer.allCards();
	   Deck victoryCards = playerCards.makeSubDeck(CardType.Victory);
	   if(victoryCards.size() == 0)
		   return 0;
	   return ((victoryCards.totalVictory()*1f/victoryCards.size()) - 1)/5;
   }
 //Returns 0 when you have only copper
   //Returns 1 when you have only gold
   public static float moneyDistribution(GameState state){
	   Deck playerCards = state.currentPlayer.allCards();
	   Deck moneyCards = playerCards.makeSubDeck(CardType.Money);
	   if(moneyCards.size() == 0)
		   return 0;
	   return ((moneyCards.totalMoney()*1f/moneyCards.size()) - 1)/2;
   }
   public static float invMoneyDistribution(GameState state){
	   return 1-moneyDisribution(state);
   }
   //I'm going to change this. 0 means we're losing badly. 1 means we're winning well. 0.5 means a tie
   public static float currentlyWinning(GameState state) {
	   LinkedList<Player> players = (LinkedList<Player>)state.players.clone();
	   players.remove(state.currentPlayer);
	   Deck curPlayerCards = state.currentPlayer.allCards();
	   int playerPoints = 0;
	   int inLeadBy = 120; //a negative value means we're behind the leader
	   for (int i = 0; i < curPlayerCards.size(); i++) { 
		   Card card = curPlayerCards.getCardAt(i);
		   playerPoints += card.victory;
	   }
	   for (Player player : players) {
		   int enemyPoints = player.allCards().totalVictory();
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