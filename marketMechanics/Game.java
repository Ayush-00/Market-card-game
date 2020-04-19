package marketMechanics;
import java.util.ArrayList;
import java.util.Collections;

public class Game {
	protected ArrayList<Player> players;
	protected Line Lspades;
	protected Line Lhearts;
	protected Line Lclubs;
	protected Line Ldiamonds;
	
	protected int currentPlayer;	//The index(wrt arraylist 'players') of the player whose turn it is.
	public int numberOfPlayers;
	
	//Supports 2,3 or 4 players
	public Game(Player p1, Player p2){
		players = new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		
		numberOfPlayers = 2;
		p1.setGame(this);
		p2.setGame(this);
		
		Lspades = new Line("Spades");
		Lhearts = new Line("Hearts");
		Lclubs = new Line("Clubs");
		Ldiamonds = new Line("Diamonds");
	}
	
	public Game(Player p1, Player p2, Player p3){
		players = new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		
		numberOfPlayers = 3;
		p1.setGame(this);
		p2.setGame(this);
		p3.setGame(this);
		
		Lspades = new Line("Spades");
		Lhearts = new Line("Hearts");
		Lclubs = new Line("Clubs");
		Ldiamonds = new Line("Diamonds");
	}
	
	public Game(Player p1, Player p2, Player p3, Player p4){
		players = new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		
		numberOfPlayers = 4;
		p1.setGame(this);
		p2.setGame(this);
		p3.setGame(this);
		p4.setGame(this);
		
		Lspades = new Line("Spades");
		Lhearts = new Line("Hearts");
		Lclubs = new Line("Clubs");
		Ldiamonds = new Line("Diamonds");
	}
	
	public void distributeCards() {
		Player p;
		for(int i = 0; i<numberOfPlayers; i++) {
			p = players.get(i);
			p.cards = new ArrayList<Card>();
		}
		ArrayList<Card> deck = new ArrayList<Card>();
		for(int i = 1; i<=13; i++) {
			deck.add(new Card("Spades", i));
			deck.add(new Card("Hearts", i));
			deck.add(new Card("Diamonds", i));
			deck.add(new Card("Clubs", i));
		}
		Collections.shuffle(deck);
		
		int indexOfPlayer = 0;		//The index of player to whom the next card will be given
		for(int i = 0; i<deck.size(); i++) {
			p = players.get(indexOfPlayer);
			p.addCard(deck.get(i));
			indexOfPlayer++;
			indexOfPlayer = indexOfPlayer%numberOfPlayers;
		}
	}
	
	boolean isValid(Card c) {
		String s = c.getSuit();
		switch(s) {
		case "Spades":
			return Lspades.isValid(c);
		case "Hearts":
			return Lhearts.isValid(c);
		case "Clubs":
			return Lclubs.isValid(c);
		case "Diamonds":
			return Ldiamonds.isValid(c);
		default:
			System.out.println("ERROR : Checking isValid on illegal suit! Game.isValid()");
			return false;
		}
	}
	
	public void askInitialPlay() {
		//Asks the player with seven of spades to play his 7 of spades card
		//sets current player to the index of the next player to play
		Player p;
		int i;
		for(i = 0; i<numberOfPlayers; i++) {
			p = players.get(i);
			if(p.hasSevenOfSpades()) {
				p.playFirstCard();
				System.out.println("Player " + p.name + " plays first, seven of spades.");
				break;
			}
		}
		currentPlayer = (i+1)%numberOfPlayers;
	}
	
	public boolean nextChance() {
		//Moves the game forward. Asks CurrentPlayer to play, updates current player. Returns true if we have a winner.
		//Returns false if game still continues
		Player p = players.get(currentPlayer);
		p.playNextCard();
		if(p.winner == true) {
			System.out.println("Player " + p.name + " wins!!");
			return true;
		}
		currentPlayer = (currentPlayer+1)%numberOfPlayers;
		return false;
	}
	
	void printLines() {
		System.out.println("******Spades******\n");
		Lspades.printLine();
		System.out.println("\n***********Hearts************");
		Lhearts.printLine();
		System.out.println("\n************Clubs**********");
		Lclubs.printLine();
		System.out.println("\n************Diamonds***********");
		Ldiamonds.printLine();
		System.out.println("******************************************************************");
	}
	
	
}
