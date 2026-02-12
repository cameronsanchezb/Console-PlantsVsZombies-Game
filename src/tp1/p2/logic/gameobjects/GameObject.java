package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

/**
 * Base class for game non playable character in the game.
 *
 */
public abstract class GameObject implements GameItem, Cloneable {

	protected GameWorld game;

	protected int col;
	protected int row;
	protected int endurance;
	protected int frequency;


	GameObject() {
	}

	GameObject(GameWorld game, int col, int row) {
		this.game = game;
		this.col = col;
		this.row = row;
	}

	public boolean isInPosition(int col, int row) {
		return this.col == col && this.row == row;
	}

	public boolean isAlive() {
		return endurance > 0;
	}

	@Override
	public String toString() {
		if (isAlive()) {
			return getSymbol();
		} else {
			return "";
		}
	}

	public abstract int getCost();

	protected String getSymbol() {
		return String.format(Messages.GAME_OBJECT_STATUS, getInitials(), endurance);
	}

	protected abstract Object getInitials();

	abstract public String getDescription();

	public void update() {
		if(frequency <= 1) attack();
		else nextFrequency();
	}

	abstract public void attack();

	abstract public void onEnter();

	abstract public void onExit();

	protected abstract int getDamage();

	@Override
	public abstract boolean receivePlantAttack(int damage, boolean explosion);

	@Override
	abstract public boolean receiveZombieAttack(int damage);

	abstract protected int getRange();

	protected void nextFrequency() {
		if(frequency <= 1) frequency = getIniFrequency();
		frequency--;
	}

	protected abstract int getIniFrequency();

	public GameObject createClone(GameWorld game, int col, int row) {
		try {
			GameObject object = (GameObject) this.clone();
			object.col = col;
			object.row = row;
			object.game = game;
			return object;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("CloneNotSupportedException has occured");
		}
	}

	public boolean match(String plantName) {
		return false;
	}

	@Override
	public boolean catchObject() {
		return false;
	}

	public int addedSuns() {
		return 0;
	}

	public boolean blockAdvance(int col, int row) {
		return isInPosition(col, row);
	}

}