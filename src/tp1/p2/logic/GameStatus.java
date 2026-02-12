package tp1.p2.logic;

public interface GameStatus {

	int getCycles();

	int getSuncoins();
	
	int getScore();
	
	/**
	 * Get number of remaining zombies for this game.
	 * 
	 * @return the remaining zombies.
	 */

	String positionToString(int col, int row);

	int getRemainingZombies();

	boolean isPlayerQuits();

	boolean isZombieInHouse();

	int getGeneratedSuns();

	int getCaughtSuns();

}
