package tp1.p2.logic;


import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import tp1.p2.control.Command;
import tp1.p2.control.Level;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPositionException;
import tp1.p2.control.exceptions.NotEnoughCoinsException;
import tp1.p2.control.commands.CatchCommand;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.logic.gameobjects.Suns;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;
import static tp1.p2.view.Messages.error;

public class Game implements GameWorld, GameStatus{

	private static final int INITIAL_SUNCOINS = 50;

	private static final int INITIAL_CYCLES = 0;

	private GameWorld gameWorld;

	private GameStatus gameStatus;

	private GameAction gameAction;

	private int sunCoins;

	private int generatedSuns;

	private int caughtSuns;

	private int cycles;
	
	private int score;

	private long gameSeed;

	private Random rand;

	private Level level;

	private GameObjectContainer gameObjectContainer;

	private boolean playerQuits;

	private PlantFactory plantFactory;

	private ZombieFactory zombieFactory;

	private ZombiesManager zombiesManager;

	private SunsManager sunsManager;

	private Deque<GameAction> actions;
	
	private Record record;

	public Game(long seed, Level level) throws GameException{
		this.gameWorld = this;
		this.gameStatus = this;
		reset(level, seed);

	}

	@Override
	public int getCycles() {
		return cycles;
	}

	@Override
	public int getSuncoins() {
		return sunCoins;
	}
	
	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void playerQuits() {
		playerQuits = true;
	}

	@Override
	public void update() throws GameException{
		executePendingActions();
		zombiesManager.update();
		gameObjectContainer.update();
		sunsManager.update();
		boolean deadRemoved = true;
		while (deadRemoved || areTherePendingActions()) {

			deadRemoved = this.gameObjectContainer.eraseDead();

			executePendingActions();
		}
		addCycle();
		Command.newCycle();
		
		record.updateRecord(score); 
	}

	private void executePendingActions() {
		while (!this.actions.isEmpty()) {
			GameAction action = this.actions.removeLast();
			action.execute(this);
		}
	}

	private boolean areTherePendingActions() {
		return this.actions.size() > 0;
	}

	@Override
	public void addAction(GameAction explode) {
		actions.add(explode);
	}

	public void addCycle() {
		cycles++;
	}

	public boolean isFinished() {
		if(isZombieInHouse() || zombiesManager.noMoreZombies() || playerQuits) return true;
		else return false;
	}

	public boolean execute(Command command) throws GameException{
		boolean a = command.execute(gameWorld);
		if(a && command.gameShouldUpdate()) update();
		return a;
	}
	
	@Override
	public void reset() throws GameException{
		sunCoins = INITIAL_SUNCOINS;
		generatedSuns = 0;
		caughtSuns = 0;
		cycles = INITIAL_CYCLES;
		score = 0;
		this.gameObjectContainer = new GameObjectContainer();
		this.actions = new ArrayDeque<>();
		rand = new Random(this.gameSeed);
		playerQuits = false;
		zombiesManager = new ZombiesManager(gameWorld, this.level, rand);
		sunsManager = new SunsManager(gameWorld, rand);
		record = Record.cargarRecord(level);
		score = 0;
	}

	@Override
	public void reset(Level level, long seed) throws GameException{
		this.level = level;
		this.gameSeed = seed;
		reset();
	}

	@Override
	public boolean isPlayerQuits() {
		return playerQuits;
	}

	@Override
	public boolean isPositionNotBlocked(int col, int row) {
		return !gameObjectContainer.blockPosition(col, row);
	}



	@Override
	public String positionToString(int col, int row) {
		return gameObjectContainer.positionToString(col, row);
	}

	@Override
	public int getRemainingZombies() {
		return zombiesManager.getRemainingZombies();
	}

	@Override
	public boolean isZombieInHouse() {
		return zombiesManager.isZombieInHouse();
	}

	@Override
	public boolean addPlant(String plantName, int col, int row, boolean consume) throws GameException {
		GameObject plant = PlantFactory.spawnPlant(plantName, gameWorld, col, row);
		checkValidPlantPosition(col, row);
		if(consume) tryToBuy(plant.getCost());
		boolean a = gameObjectContainer.addGameObject(plant);
		return a;
	}

	@Override
	public GameItem getGameItemInPosition(int col, int row) {
		return gameObjectContainer.getGameItemInPosition(col, row);
	}

	@Override
	public boolean addItem(GameObject z) {
		return gameObjectContainer.addGameObject(z);
	}

	public String levelToString() {
		return level.toString();
	}

	public long getSeed() {
		return gameSeed;
	}
	
	public int getRecord() {
		return record.getRecord();
	}

	@Override
	public int getGeneratedSuns() {
		return generatedSuns;
	}

	@Override
	public int getCaughtSuns() {
		return caughtSuns;
	}

	@Override
	public boolean addSun() {
		return sunsManager.addSun();
	}
	
	public void increaseSuncoins() {
		sunCoins += Suns.getCoinValue();
	}

	@Override
	public boolean tryToCatchObject(int col, int row) {
		int caught = gameObjectContainer.catchObject(col, row);
		if(caught > 0) {
			CatchCommand.caughtSun();
			return true;
		}
		else return false;
	}

	@Override
	public boolean addZombie(int zombieIdx, int col, int row) throws GameException{
		return zombiesManager.addZombie(zombieIdx, col, row);
	}

	@Override
	public boolean blockAdvance(int col, int row) {
		return gameObjectContainer.blockPosition(col, row);
	}

	@Override
	public void incrementGeneratedSuns() {
		generatedSuns++;
	}

	@Override
	public void incrementCaughtSuns() {
		caughtSuns++;
	}

	@Override
	public void zombieSpawned() {
		zombiesManager.zombieSpawned();
	}

	@Override
	public void zombieDied(boolean explosion) {
		zombiesManager.zombieDied();
		if (explosion) score += 20;
		else score += 10;
	}

	@Override
	public void zombiesWon() {
		zombiesManager.zombiesWon();
	}

	@Override
	public void tryToBuy(int cost) throws GameException {
		if(cost > sunCoins) throw new NotEnoughCoinsException(Messages.NOT_ENOUGH_COINS);
		else sunCoins -= cost;
	}

	@Override
	public void checkValidPlantPosition(int col, int row) throws GameException {
		if(col < 0 || row < 0 || col >= NUM_COLS || row >= NUM_ROWS || !isPositionNotBlocked(col, row)) throw new InvalidPositionException(Messages.INVALID_POSITION, String.valueOf(col), String.valueOf(row));
	}

	@Override
	public void checkValidZombiePosition(int col, int row) throws GameException {
		if(!isValidZombiePosition(col, row) || !isPositionNotBlocked(col, row)) throw new InvalidPositionException(Messages.INVALID_POSITION, String.valueOf(col), String.valueOf(row));
	}

	public void guardarRecord() throws GameException {
		record.guardarRecord();
	}
	
	public boolean isNewRecord() {
		return record.isNewRecord();
	}

	@Override
	public boolean isValidZombiePosition(int col, int row) {
		if(col < 0 || row < 0 || col > NUM_COLS || row > NUM_ROWS) return false;
		return true;
	}

}
