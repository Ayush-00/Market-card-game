package marketMechanics;
import java.util.ArrayList;

 public class Player{
	public String name;
	public ArrayList<Card> cards;
	boolean winner;
	Game game;
	
	Player(String name){
		this.name = name;
		cards = new ArrayList<Card>();
		winner = false;
		game = null;
	}
	
	public void setGame(Game g) {
		this.game = g;
	}
	
	public void addCard(Card c){
		cards.add(c);
		return;
	}
	
	public void printCards() {
		System.out.println("Player " + name + "'s cards:");
		int l = cards.size();
		Card temp;
		for(int i = 0; i<l; i++) {
			temp = cards.get(i);
			temp.printCard();
		}
	}
	
	public boolean isAnyCardPossible() {
		//If false, then need to pass
		boolean flag = false;	//Is it possible to play atleast one card?
		Card c;
		for(int i = 0; i<cards.size(); i++) {
			c = cards.get(i);
			flag = game.isValid(c);
			if(flag) {
				//Yes, its possible to play one card
				break;
			}
		}
		return flag;
	}

	protected boolean play(Card c) {
		//Assumes that c is a legal card to play
		//When which card to play is already decided, this method used to actually play the card
		//True if played, false if not played
		
		if(cards.contains(c) == false) {
			//Card cant be played
			System.out.println("ERROR: Cant play a card which you dont have!");
			return false;
		}
		cards.remove(c);
		//Add the card to the Correct line!!
		String suit = c.getSuit();
		boolean added = false;
		switch(suit) {
			case "Spades":
				added = game.Lspades.addCard(c);
				break;
			case "Hearts":
				added = game.Lhearts.addCard(c);
				break;
			case "Clubs":
				added = game.Lclubs.addCard(c);
				break;
			case "Diamonds":
				added = game.Ldiamonds.addCard(c);
				break;
			default:
				System.out.println("ERROR : Illegal suit card played");
				break;
		}
		if(added == false) {
			System.out.println("ERROR : Card not added to line, play() method");
		}
		
		if(cards.isEmpty()) {
			winner = true;
		}
		return added;
	}
	
	
	public boolean hasSevenOfSpades() {
		Card c;
		for(int i = 0; i<cards.size(); i++) {
			c = cards.get(i);
			if(c.getSuit().equals("Spades") && c.getValue() == 7){
				return true;
			}
		}
		return false;
	}
	
	public void playFirstCard() {
		Card c;
		for(int i = 0; i<cards.size(); i++) {
			c = cards.get(i);
			if(c.getSuit().equals("Spades") && c.getValue() == 7){
				play(c);
				return;
			}
		}
		System.out.println("ERROR : First player doesnt have seven of spades!");
	}
	
	 public boolean playNextCard() {		//Player decides which card to play, and uses play() to play it.
		 							//Different for Human and Computer
		 System.out.println("ERROR : Player class play() called");
		 return false;
	 }
}
