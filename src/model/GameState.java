package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameState {
	LinkedList<Player> players = new LinkedList<Player>();
	Player currentPlayer;
	Deck[] communityPiles;
	Deck coppers;
	Deck silvers;
	Deck golds;
	Deck estates;
	Deck duchies;
	Deck provinces;
	Deck curses;
	Deck inPlay;
	ArrayList<Deck> buyOptions;
	BufferedReader b;
	int currentWorth;
	int numOfActions;
	int numOfBuys;
	int numOfPlayers;
	public GameState(int n) throws InstantiationException, IllegalAccessException{
		this.numOfPlayers = n;
		b = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<numOfPlayers;i++){
			Player p = new Player(Integer.toString(i));
			p.newHand();
			players.add(p);
		}
		currentWorth = 0;
		coppers = new Deck(Copper.class,60-7*n);
		silvers = new Deck(Silver.class,40);
		golds = new Deck(Gold.class,30);
		curses = new Deck(Curse.class,30);
		provinces = new Deck(Province.class,12);
		duchies = new Deck(Duchy.class,12);
		estates = new Deck(Estate.class,24-3*n);
		//Need to initialize communityPiles;
		communityPiles = new Deck[10];
		inPlay = new Deck();
	}
	public void nextPlayer(){
		if(currentPlayer != null)
			players.add(currentPlayer);
		currentPlayer = players.getFirst();
	}
	public void takeTurn(Player p){
		numOfActions = 1;
		numOfBuys = 1;
		actionPhase(p);
		buyPhase(p);
		p.discard.merge(inPlay);
		p.newHand();
	}
	public void actionPhase(Player p){
		boolean finished = false;
		if(p.hand.totalOfType(CardType.Action) != 0 && numOfActions != 0 && !finished){
			int i=selectCard(p.hand,CardType.Action);
			if(i != -1){
				ActionCard selected = (ActionCard)p.hand.getCardAt(i);
				inPlay.add(selected);
				ActionHandler.handleActionCard(this, p, selected);
				numOfActions--;
			}
		}
	}
	public void buyPhase(Player p){
		currentWorth += p.hand.totalMoney();
		Card purchaseChoice = new BlankCard();
		while(currentWorth != 0 || purchaseChoice == null){
			purchaseChoice = selectBuy(currentWorth);
			if(purchaseChoice != null){
				currentWorth -= purchaseChoice.cost;
				p.discard.add(purchaseChoice);
			}
		}
	}
	public Card selectBuy(int total){
		for(int i=0;i<buyOptions.size();i++){
			System.out.println(i+") "+buyOptions.get(i).getClass().toString());
		}
		Card c = new BlankCard();
		while(c.getClass() == BlankCard.class){
			System.out.println("Please select an appropriate buy");
			String line = "";
			try {
				line = b.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int i = Integer.parseInt(line);
			if(i == -1)
				c = null;
			else
				c = buyOptions.get(i).getCardAt(0);
			if(c.cost > total){
				c = new BlankCard();
				System.out.println("That card cost too much");
			}
		}
		return c;
	}
	public void setupBuyOptions(){
		buyOptions = new ArrayList<Deck>();
		buyOptions.add(estates);
		buyOptions.add(duchies);
		buyOptions.add(provinces);
		buyOptions.add(coppers);
		buyOptions.add(silvers);
		buyOptions.add(golds);
		for(int i=0;i<communityPiles.length;i++){
			buyOptions.add(communityPiles[i]);
		}		
	}
	//Not the best selector. Probably broken
	public int selectCard(Deck d,CardType t){
		System.out.println("Select a card of type"+t);
		int i = selectCard(d);
		if(i == -1)
			return i;
		while(!d.getCardAt(i).type.equals(t)){
			System.out.println("Not the right type. Select Again");
		}
		return i;
	}
	public int selectCard(Deck d){
		if(d.isEmpty())
			return -1;
		System.out.println("Enter -1 to not select a card");
		d.printDeck();
		int i = -1;
		while(i == -1){
			String line = "";
			try {
				line = b.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int temp = Integer.parseInt(line);
			if(temp < -1 || temp > d.size()){
				System.out.println("Please pick another value");
			}
			else{
				i = temp;
			}
		}
		return i;
	}
	//A really lawlzy y/n asker
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
		for(int i=0;i<communityPiles.length;i++){
			if(communityPiles[i].isEmpty())
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
			takeTurn(currentPlayer);
		}
		declareWinner();
	}
}