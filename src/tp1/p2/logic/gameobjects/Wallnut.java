package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Wallnut extends Plant{

	private final static int COST = 50;
	private final static int DAMAGE = 0;
	private final static int INIENDURANCE = 10;
	private final static int INIFREQUENCY = 0;

	public Wallnut(GameWorld game, int col, int row) {
		super(game, col, row);
		endurance = INIENDURANCE;
		frequency = INIFREQUENCY;
	}

	@Override
	protected String getInitials() {
		return Messages.WALL_NUT_SYMBOL;
	}

	@Override
	public String getName() {
		return Messages.WALL_NUT_NAME_SHORTCUT;
	}

	@Override
	public boolean match(String name) {
		if("wall-nut".equals(name) || "wn".equals(name)) return true;
		else return false;
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
	public int getRange() {
		return 0;
	}

	@Override
	protected int getIniFrequency() {
		return INIFREQUENCY;
	}

}
