package marketMechanics;
import java.util.Scanner;
public class Human extends Player {
	
	public Human(String name){
		super(name);
	}

	@Override
	public boolean playNextCard() {
		
		System.out.println("***********Player " + name + "'s cards :*********");
		this.printCards();
		
		if(isAnyCardPossible()==false) {
			System.out.println("Player " + name + " passes!");
			return false;
		}
		Card c = cards.get(0);	//Need to initialize to avoid error
		/******<Taking user input>********/
		//Store the card to play in variable 'c'
		Scanner sc = new Scanner(System.in);
		boolean legalCard = false;
		do {
			boolean cardFoundFlag = false;
			do {
				cardFoundFlag = false;
				System.out.println("Player " + name + "'s turn:");
				System.out.println("Enter suit:");
				String inputsuit = sc.nextLine();
				System.out.println("Enter value:");
				int inputv = sc.nextInt();
				//System.out.println("Scanned:" + inputsuit + inputv);
				//Card temp = new Card(inputsuit, inputv);
				String csuit;
				int cval;
				
				for(int i = 0; i<cards.size(); i++) {
					c = cards.get(i);
					csuit = c.getSuit();
					cval = c.getValue();
					
					if(inputsuit.equals(csuit) && inputv == cval) {
						cardFoundFlag = true;
						break;
					}
				}
					
				//cardFoundFlag = cards.contains(temp);
				//System.out.println("temp = ");
				//temp.printCard();
				
				if(cardFoundFlag == false) {
					System.out.println("Card not found, try again");
				}
				
				
				sc.nextLine();
			}while(cardFoundFlag == false);
			legalCard = game.isValid(c);
			if(legalCard == false) {
				System.out.println("Illegal move, try again");
			}
		}while(legalCard == false);
		
		/******</User Input>********/
		
		play(c);
		return true;
	}

}
