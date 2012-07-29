package model;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private CardType type;
	private ArrayList<Card> theStack;
	Deck(){
		theStack = new ArrayList<Card>();
	}
	Deck(Card card,int n) throws InstantiationException, IllegalAccessException{
		this();
		int i=0;
		while(i<n){
			theStack.add(card.clone());
			i++;
		}
	}
	Deck(Deck ... pieces){
		this();
		for(int i=0;i<pieces.length;i++){
			merge(pieces[i]);
		}
	}
	public void add(Card c){
		theStack.add(c);
	}
	public Card remove(Card c){
		theStack.remove(c);
		return c;
	}
	public Card getCardAt(int i){
		if(i<0 || i>theStack.size())
			return new BlankCard();
		return theStack.get(i);
	}
	public Card removeCardAt(int i){
		return theStack.remove(i);
	}
	public int size() {
		return theStack.size();
	}
	public void shuffle(){
		ArrayList<Card> newDeck = new ArrayList<Card>();
		Random rand = new Random();
		while(theStack.size() != 0){
			Card c = theStack.remove(rand.nextInt(theStack.size()));
			newDeck.add(c);
		}
		theStack = newDeck;
	}
	public void merge(Deck d){
		while(d.size() != 0){
			theStack.add(d.removeCardAt(0));
		}
	}
	public void mergeShuffle(Deck d){
		this.merge(d);
		this.shuffle();
	}
	public Card draw(){
		return removeCardAt(0);
	}
	public Deck draw(int n){
		Deck newDeck = new Deck();
		int i=0;
		while(i<n)
			newDeck.add(draw());
		return newDeck;
	}
	public boolean isEmpty(){
		return theStack.isEmpty();
	}
	public int totalMoney(){
		int total = 0;
		for(int i=0;i<theStack.size();i++){
			total += theStack.get(i).value;
		}
		return total;
	}
	public int totalVictory(){
		int total = 0;
		for(int i=0;i<theStack.size();i++){
			total += theStack.get(i).victory;
		}
		return total;
	}
	public int totalOfType(CardType t){
		int total = 0;
		for(int i=0;i<theStack.size();i++){
			if(theStack.get(i).type.equals(t))
				total++;
		}
		return total;
	}
	public int totalOfCard(Class c){
		int total = 0;
		for(int i=0;i<theStack.size();i++){
			Card e = theStack.get(i);
			if(e.getClass() == c)
				total++;
		}
		return total;
	}
	public Deck makeSubDeck(CardType t){
		if(t == null)
			return this;
		Deck r = new Deck();
		for(int i=0;i<theStack.size();i++){
			Card c = theStack.get(i);
			if(c.type.equals(t))
				r.add(c);
		}
		return r;
	}
	public String printDeck(){
		String s = "";
		for(int i=0;i<theStack.size();i++){
			s += i+")"+theStack.get(i).toString()+"/n";
		}
		return s;
	}
}