package model;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private CardType type;
	private ArrayList<Card> theStack;
	Deck(){
		theStack = new ArrayList<Card>();
	}
	Deck(Class<? extends Card> class1,int n) throws InstantiationException, IllegalAccessException{
		int i=0;
		while(i<n){
			theStack.add(class1.newInstance());
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
	public int totalOfType(CardType t){
		int total = 0;
		for(int i=0;i<theStack.size();i++){
			if(theStack.get(i).type.equals(t))
				total++;
		}
		return total;
	}
	public String printDeck(){
		String s = "";
		for(int i=0;i<theStack.size();i++){
			s += i+")"+theStack.get(i).toString()+"/n";
		}
		return s;
	}
}