package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameState {
	LinkedList<Player> players = new LinkedList<Player>();
	Player currentPlayer;
	ArrayList<Deck> communityPiles;
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
	GameMonitor monitor;
	public GameState(int computerPlayers, int humanPlayers) throws InstantiationException, IllegalAccessException{
		monitor = new TextMonitor();
		this.numOfPlayers = computerPlayers + humanPlayers;
		b = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<computerPlayers;i++){
			Player p = new HumanPlayer(Integer.toString(i));//CHANGE THIS TO COMPUTERPLAYER WHEN IT EXISTS
			p.newHand();
			players.add(p);
		}
		for(int i=0;i<humanPlayers;i++){
			Player p = new HumanPlayer(Integer.toString(i));
			p.newHand();
			players.add(p);
		}
		currentWorth = 0;
		coppers = new Deck(new Copper(),60-7*numOfPlayers);
		silvers = new Deck(new Silver(),40);
		golds = new Deck(new Gold(),30);
		curses = new Deck(new Curse(),30);
		provinces = new Deck(new Province(),12);
		duchies = new Deck(new Duchy(),12);
		estates = new Deck(new Estate(),24-3*numOfPlayers);
		setupBuyOptions();
		inPlay = new Deck();
	}
	public void nextPlayer(){
		numOfActions = 1;
		numOfBuys = 1;
		if(currentPlayer != null)
			players.add(currentPlayer);
		currentPlayer = players.getFirst();
	}
	public void takeTurn(Player p, GameState state){
		//actionPhase(p);
		//buyPhase(p);
		p.playRound(state);
		p.discard.merge(inPlay);
		p.newHand();
	}
	public void setupBuyOptions(){
		buyOptions = new ArrayList<Deck>();
		buyOptions.add(estates);
		buyOptions.add(duchies);
		buyOptions.add(provinces);
		buyOptions.add(coppers);
		buyOptions.add(silvers);
		buyOptions.add(golds);
		setupCommunityPiles();
		for(int i=0;i<communityPiles.size();i++){
			buyOptions.add(communityPiles.get(i));
		}		
	}
	//Not the best selector. Probably broken
	/*public int selectCard(Deck d,CardType t){
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
	}*/
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
			monitor.update(this);
		}
		declareWinner();
	}
	public void bought(Card card) {
		for (Deck deck : buyOptions) {
			if (deck.getCardAt(0).equals(card)) {
				deck.removeCardAt(0);
				break;
			}
		}
		numOfBuys--;
	}
	public void setupCommunityPiles() {
		communityPiles = new ArrayList<Deck>();
		Deck temp;	
		temp = new Deck();
		int size = 10;
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Adventurer));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Bureaucrat));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Cellar));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Chancellor));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Chapel));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.CouncilRoom));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Feast));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Festival));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Labratory));
		}
		communityPiles.add(temp);
		
		/*temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Library));
		}
		communityPiles.add(temp);*/
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Market));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Militia));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Mine));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Moat));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Moneylender));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Remodel));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Smithy));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Spy));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Thief));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.ThroneRoom));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Village));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Witch));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.WoodCutter));
		}
		communityPiles.add(temp);
		
		temp = new Deck();
		for (int i = 0; i < size; i++) {
			temp.add(new ActionCard(ActionCardTypes.Workshop));
		}
		communityPiles.add(temp);
		
		
	}
}

/**public void actionPhase(Player p){
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
}*/