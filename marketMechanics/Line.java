package marketMechanics;

import java.util.Iterator;
import java.util.LinkedList;


public class Line {
	String suit;
	public LinkedList<Card> cards;
	
	Line(String suit){
		this.suit = suit;
		cards = new LinkedList<Card>();
	}
	
	boolean addCard(Card c){
		
		int temp = isValidI(c);
		
		switch(temp) {
		case 1:		//Add in end
			cards.addLast(c);
			return true;
			
		case -1:	//Add first
			cards.addFirst(c);
			return true;
			
		default:	//Do not add
			return false;
		}
	}
	
	int isValidI(Card c)		//IsValid in INTEGER form
	//Return 1 if its valid to add c in the end, -1 if add c first, 0 if not valid
	{
		if(c.getSuit() != this.suit) {
			return 0;
		}
		
		/******Adding 7 in the beginning*******/
		if(cards.isEmpty()) {
			if(c.getValue() == 7) {
				return 1;
			}
			return 0;
		}
		
		Card lowest = cards.getFirst();
		Card highest = cards.getLast();
		
		if(lowest.getValue()-c.getValue() == 1){
			//Add first
			return -1;
		}
		else if(c.getValue() - highest.getValue() == 1) {
			//Add c in end
			return 1;
		}
		//Dont add c
		return 0;
	}
	
	Card getHighest() {
		return cards.getLast();
	}
	
	Card getLowest() {
		return cards.getFirst();
	}
	
	void printLine(){
		Card temp;
		Iterator<Card> it = cards.iterator();
		while(it.hasNext()) {
			temp = it.next();
			temp.printCard();
		}
	}
	
	
	boolean isValid(Card c)
	{
		if(isValidI(c) == 1 || isValidI(c) == -1) {
			return true;
		}else {
			return false;
		}
	}
}
