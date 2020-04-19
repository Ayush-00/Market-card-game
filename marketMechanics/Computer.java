package marketMechanics;
import java.util.Collections;
import java.util.Comparator;

public class Computer extends Player{
	
	boolean hasSorted;		//Used to check if Computer has played atleast one card or not
							//In first chance, computer has to order cards.
	
	
	public Computer(String name){
		super(name);
		this.hasSorted = false;
	}

	@Override
	public boolean playNextCard() {
		if(hasSorted == false) {
			orderCards();
			hasSorted = true;	
		}
		
		System.out.println("Computer " + name + "'s cards :");
		printCards();
		Card c;
		for(int i = 0; i<cards.size(); i++) {
			c = cards.get(i);
			if(game.isValid(c)) {
				System.out.print(name + " tries to play ");
				c.printCard();
				play(cards.get(i));
				return true;
			}
		}
		System.out.println(name + " couldn't find any valid card to play");
		return false;
	}

	private void orderCards() {
		//Orders cards according to Priority.
		/******Logic : *******
		 * First, find 'benefit' of each card. 
		 * Benefit means:	
		 * 		1.For cards >7 : The highest value you have of that suit - that card's value
		 * 		2.For cards <7 : That card's value - lowest value you have for the suit
		 * 		3.For 7's : Compute both these values. Whichever is higher. (CAN BE IMPROVED)
		 * Example : Spades8 benefit is King - 8 = 5, if you have the King as highest spade card
		 * 
		 * Then, compute Cost of each card
		 * Cost defined as:
		 * 		|The card's value - Extreme card of that end|
		 * 		For 7, cost defined as 12 (King - Ace)
		 * 		Eg. Cost(9) = King - 9 = 4.
		 * 
		 * Now, order the cards based on Benefit(descending order). Sort equal benefit based on increasing cost.
		 */
		
		
		Collections.sort(cards, cardComparator);
		
		
	}
	
	private int getBenefit(Card c) {
			int benefit;
			int extreme;
			if(c.value > 7) {
				extreme = getHighestCardBySuit(c);
				benefit = extreme - c.value;
			}
			else if(c.value < 7) {
				extreme = getLowestCardBySuit(c);
				benefit = c.value - extreme;
			}
			else {
				//Card is a 7
				int upper = getHighestCardBySuit(c);
				int lower = getLowestCardBySuit(c);
				
				benefit = Math.max(upper-7, 7-lower);
			}	
		
		return benefit;
	}
	
	private int getCost(Card c) {
		int cost;
			
			if(c.value>7) {
				cost = 13 - c.value;
			}else if(c.value < 7) {
				cost = c.value-1;
			}else {
				cost = 12;	//Cost of 7
			}

		return cost;
	}
	
	
	
	private int getHighestCardBySuit(Card c) {
			//Computes highest card you have of the suit of c, returns value
		int max = 7;
		for(int i = 0; i<cards.size(); i++) {
			if(cards.get(i).suit.equals(c.suit)) {
				if(cards.get(i).value > max) {
					max = cards.get(i).value;
				}
			}
		}
		return max;
	}
	
		private int getLowestCardBySuit(Card c) {
			//Computes highest card you have of the suit of c, returns value
			int min = 7;
			for(int i = 0; i<cards.size(); i++) {
				if(cards.get(i).suit.equals(c.suit)) {
					if(cards.get(i).value < min) {
						min = cards.get(i).value;
					}
				}
			}
			return min;
		}

		public Comparator<Card> cardComparator = new Comparator<Card>() {
			
			public int compare(Card c1, Card c2) {
				int benefit1 = getBenefit(c1);
				int benefit2 = getBenefit(c2);
				
				if(benefit1 > benefit2) {
					return -1;		//Card 1 should come before Card 2
				}else if(benefit1 < benefit2) {
					return 1;		//Card 2 should be played first
				}
				else {
					int cost1 = getCost(c1);
					int cost2 = getCost(c2);
					
					if(cost1>cost2) {
						return 1;	//Card 1 should be played later
					}else if(cost1 < cost2) {
						return -1;
					}
					else {
						return 0;
					}
				}
			}
			//End of comparator
		};
}
