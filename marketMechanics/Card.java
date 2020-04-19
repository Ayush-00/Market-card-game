package marketMechanics;

public class Card {
	
	String suit;
	public int value;
	
	Card(String suit, int value)
	{
		this.suit = suit;
		this.value = value;
	}
	
	public void printCard()
	{
		System.out.printf("%-10s  %d\n", suit, value);
	}

	public String getSuit() {
		return suit;
	}

	public int getValue() {
		return value;
	}

	public boolean equals(Card other) {
		if(this.suit == other.getSuit() && this.value == other.getValue()) {
			return true;
		}
		return false;
	}
	
	public int hashCode() {
		return value;
	}
}
