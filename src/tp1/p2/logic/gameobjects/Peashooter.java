package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Peashooter extends Plant{

	private final static int COST = 50;
	private final static int DAMAGE = 1;
	private final static int INIENDURANCE = 3;
	private final static int INIFREQUENCY = 1;

	public Peashooter(GameWorld game, int col, int row) {
		super(game, col, row);
		endurance = INIENDURANCE;
		frequency = INIFREQUENCY;
	}

	@Override
	public int getRange() {
		return GameWorld.NUM_COLS - col - 1;
	}

	@Override
	public String getName() {
		return Messages.PEASHOOTER_NAME_SHORTCUT;
	}

	@Override
	public boolean match(String name) {
		if("peashooter".equals(name) || "p".equals(name)) return true;
		else return false;
	}

	@Override
	public String getInitials() {
		return Messages.PEASHOOTER_SYMBOL;
	}

	@Override
	public int getCost() {
		return COST;
	}

	@Override
	protected int getDamage() {
		return DAMAGE;
	}

	@Override
	protected int getIniFrequency() {
		return INIFREQUENCY;
	}

}
