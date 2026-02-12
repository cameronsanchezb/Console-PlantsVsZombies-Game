package tp1.p2.logic;

import java.util.Random;

import tp1.p2.control.Level;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;

/**
 * Manage zombies in the game.
 *
 */
public class ZombiesManager {

	private GameWorld game;

	private Level level;

	private Random rand;

	private int remainingZombies;

	private int zombiesAlived;
	
	private boolean zombieInHouse;

	public ZombiesManager(GameWorld game, Level level, Random rand) {
		this.game = game;
		this.level = level;
		this.rand = rand;
		this.remainingZombies = level.getNumberOfZombies();
		this.zombiesAlived = 0;
		this.zombieInHouse = false;
	}

	/**
	 * Checks if the game should add (if possible) a zombie to the game.
	 *
	 * @return <code>true</code> if a zombie should be added to the game.
	 */
	private boolean shouldAddZombie() {
		return rand.nextDouble() < level.getZombieFrequency();
	}

	/**
	 * Return a random row within the board limits.
	 *
	 * @return a random row.
	 */
	private int randomZombieRow() {
		return rand.nextInt(GameWorld.NUM_ROWS);
	}

	private int randomZombieType() {
		return rand.nextInt((ZombieFactory.getAvailableZombies()).size());
	}

	public void update() throws GameException {
		addZombie();
	}

	public boolean addZombie() throws GameException {
		int row = randomZombieRow();
		return addZombie(row);
	}

	public boolean addZombie(int row) throws GameException {
		boolean canAdd = getRemainingZombies() > 0 && shouldAddZombie() && game.isPositionNotBlocked(GameWorld.NUM_COLS, row);
		int zombieType = randomZombieType();

		if (canAdd) {
			Zombie z = ZombieFactory.spawnZombie(zombieType, game, GameWorld.NUM_COLS, row);
			if(z != null && game.isPositionNotBlocked(GameWorld.NUM_COLS, row)) {
				game.addItem(z);
				remainingZombies--;
			}
		}
		return canAdd;
	}


	int getRemainingZombies() {
		return this.remainingZombies;
	}

	public boolean noMoreZombies() {
		return getRemainingZombies() == 0 && zombiesAlived == 0;
	}

	public boolean addZombie(int zombieIdx, int col, int row) throws GameException{
		Zombie z = ZombieFactory.spawnZombie(zombieIdx, game, col, row);
		game.checkValidZombiePosition(col, row);
		game.addItem(z);
		return true;
	}

	public void zombieSpawned() {
		zombiesAlived++;
	}

	public void zombieDied() {
		zombiesAlived--;
	}

	public boolean isZombieInHouse() {
		return zombieInHouse;
	}

	public void zombiesWon() {
		zombieInHouse = true;
	}
}