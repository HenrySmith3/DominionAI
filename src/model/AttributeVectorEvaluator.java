package model;

public class AttributeVectorEvaluator {
	public static float EvaluateVector(AttributeVector vec, GameState state) {
		
	}
	public static float percentageLeft(GameState state) {
           //perhaps make better later.
           int estimatedTurns = 120;
           int turns = state.numTurns;
           if (turns >= estimatedTurns) {
                   turns = estimatedTurns;
           }
           
           return 1- turns/estimatedTurns;
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
                   } else if (deck.size() == 1) {
                           ++oneCardDecks;
                   }
                   else if (deck.size() == 2) {
                           ++twoCardDecks;
                   }
           }
           if (emptyDecks > 1 && oneCardDecks > 1) {
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
           Deck playerCards = new Deck(player.discard, player.drawPile, player.hand);
           return playerCards.totalMoney()/5;
   }

}