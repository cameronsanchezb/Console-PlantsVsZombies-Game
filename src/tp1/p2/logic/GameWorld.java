package tp1.p2.logic;

import java.io.IOException;

import tp1.p2.control.Level;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;

public interface GameWorld {

	public static final int NUM_ROWS = 4;

	public static final int NUM_COLS = 8;

	void playerQuits();

	void update() throws GameException;

	void reset() throws GameException;

	void reset(Level level, long seed) throws GameException;

	boolean isPositionNotBlocked(int col, int row);

	boolean addPlant(String plantName, int col, int row, boolean consume) throws GameException;

	GameItem getGameItemInPosition(int i, int row);

	boolean isValidZombiePosition(int numCols, int row);

	void tryToBuy(int cost) throws GameException;

	void checkValidPlantPosition(int col, int row) throws GameException;
	
	void checkValidZombiePosition(int col, int row) throws GameException;

	boolean tryToCatchObject(int col, int row) throws GameException;
	
	boolean addSun();

	boolean addItem(GameObject gameObject);

	int getGeneratedSuns();

	int getCaughtSuns();

	boolean addZombie(int zombieIdx, int col, int row) throws GameException;

	boolean blockAdvance(int i, int row);

	void incrementGeneratedSuns();

	void incrementCaughtSuns();

	void addAction(GameAction explode);

	void zombieSpawned();

	void zombieDied(boolean explosion);

	void zombiesWon();

	int getRecord();
	
	String levelToString();
	
	public void increaseSuncoins();
	
}