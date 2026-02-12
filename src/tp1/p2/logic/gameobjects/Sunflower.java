package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sunflower extends Plant{

	private final static int COST = 20;
	private final static int DAMAGE = 0;
	private final static int INIENDURANCE = 1;
	private final static int INIFREQUENCY = 4;

	public Sunflower(GameWorld game, int col, int row){
		super(game, col, row);
		endurance = INIENDURANCE;
		frequency = INIFREQUENCY;
	}

	@Override
	public String getName() {
		return Messages.SUNFLOWER_NAME_SHORTCUT;
	}

	@Override
	public boolean match(String name) {
		if("sunflower".equals(name) || "s".equals(name)) return true;
		else return false;
	}

	@Override
	public String getInitials() {
		return Messages.SUNFLOWER_SYMBOL;
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

	@Override
	public int getRange() {
		return 0;
	}

	@Override
	public void update() {
		if(frequency <= 1) {
			game.addSun();
		}
		nextFrequency();
	}
}
