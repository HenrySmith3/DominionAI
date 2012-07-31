package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameState {
	public LinkedList<Player> players = new LinkedList<Player>();
	public Player currentPlayer;
	public ArrayList<Deck> communityPiles;
	public Deck coppers;
	public Deck silvers;
	public Deck golds;
	public Deck estates;
	public Deck duchies;
	public Deck provinces;
	public Deck curses;
	public Deck inPlay;
	public ArrayList<Deck> buyOptions;
	public BufferedReader b;
	public int numOfPlayers;
	public int numTurns = 0;
	public GameState(int humanPlayers,int computerPlayers,String actionDecksForGame) throws InstantiationException, IllegalAccessException{
		this.numOfPlayers = computerPlayers + humanPlayers;
		b = new BufferedReader(new InputStreamReader(System.in));		
		for(int i=0;i<humanPlayers;i++){
			Player p = new HumanPlayer("Human"+Integer.toString(i));
			p.newHand();
			players.add(p);
		}
		for(int i=0;i<computerPlayers;i++){
			Player p = new AIPlayer("Computer"+Integer.toString(i), new DefaultPersonality());//CHANGE THIS TO COMPUTERPLAYER WHEN IT EXISTS
			p.newHand();
			players.add(p);
		}
		coppers = new Deck(new Copper(),60-7*numOfPlayers);
		silvers = new Deck(new Silver(),40);
		golds = new Deck(new Gold(),30);
		curses = new Deck(new Curse(),30);
		provinces = new Deck(new Province(),12);
		duchies = new Deck(new Duchy(),12);
		estates = new Deck(new Estate(),24-3*numOfPlayers);
		setupCommunityPiles(actionDecksForGame);
		setupBuyOptions();
		inPlay = new Deck();
	}
	public void nextPlayer(){
		if(currentPlayer != null)
			players.add(currentPlayer);
		currentPlayer = players.removeFirst();
	}
	public void takeTurn(Player p, GameState state){
		//actionPhase(p);
		//buyPhase(p);
		p.playRound(state);
		p.discard.merge(inPlay);
		p.newHand();
		++numTurns;
	}
	public void setupBuyOptions(){
		buyOptions = new ArrayList<Deck>();
		buyOptions.add(estates);
		buyOptions.add(duchies);
		buyOptions.add(provinces);
		buyOptions.add(coppers);
		buyOptions.add(silvers);
		buyOptions.add(golds);
		for(int i=0;i<communityPiles.size();i++){
			buyOptions.add(communityPiles.get(i));
		}		
	}
	public boolean yesOrNo(){
		System.out.println("Y/N");
		String line = "";
		try {
			line = b.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line.equals("Yes") || line.toUpperCase().equals("Y");
	}
	public boolean gameFinished(){
		int totalMissing = 0;
		for(int i=0;i<communityPiles.size();i++){
			if(communityPiles.get(i).isEmpty())
				totalMissing++;
		}
		return provinces.isEmpty() || (totalMissing > 2);
	}
	public void declareWinner() throws IOException{
		Player winner = null;
		int bestVictory = 0;
		while(winner != currentPlayer){
			if(currentPlayer.victoryPoints > bestVictory){
				bestVictory = currentPlayer.victoryPoints;
				winner = currentPlayer;
			}
			nextPlayer();
		}
		System.out.println("Player "+winner.name+" won with "+winner.victoryPoints+" points");
		b.read();
		System.exit(0);
	}
	public void play() throws IOException{
		while(!gameFinished()){
			nextPlayer();
			takeTurn(currentPlayer, this);
		}
		declareWinner();
	}
	//Model clean up
	public void bought(Card card) {
		Deck d = null;
		for (Deck deck : buyOptions) {
			if (deck.getCardAt(0).equals(card)) {
				d = deck;
				break;
			}
		}
		d.removeCardAt(0);
		if(d.isEmpty()){
			buyOptions.remove(d);
		}
	}
	public void setupCommunityPiles(String s) throws InstantiationException, IllegalAccessException {
		String[] decks = s.split(" ");
		communityPiles = new ArrayList<Deck>();
		for(int i=0;i<decks.length;i++){
			ActionCard myCard = new ActionCard(decks[i]);
			communityPiles.add(new Deck(myCard,10));
		}
	}
}