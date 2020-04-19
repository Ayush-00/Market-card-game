package marketMechanics;
public class Driver {
	public static void main(String args[]) {
		
		Player p1 = new Computer("Ayush");
		Player p2 = new Computer("Arohi");
		Player p3 = new Computer("Papa");
		Player p4 = new Computer("Mummy");
		
		
			Game g = new Game(p1,p2,p3,p4);
			g.distributeCards();
			
			g.askInitialPlay();
			
			boolean gameOver;
			do {
				g.printLines();
				gameOver = g.nextChance();
			}while(gameOver == false);
						
		}
		
}
