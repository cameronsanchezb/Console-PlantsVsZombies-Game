package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sporty extends Zombie{

	private final static int INIENDURANCE = 2;
	private final static int SPEED = 2;
	private final static int INIFREQUENCY = 1;
	private final static int DAMAGE = 1;

	public Sporty(GameWorld game, int col, int row) {
		super(game, col, row);
		endurance = INIENDURANCE;
		frequency = INIFREQUENCY;
	}

	@Override
	protected int getIniSpeed() {
		return SPEED;
	}

	@Override
	protected String getInitials() {
		return Messages.SPORTY_ZOMBIE_SYMBOL;
	}

	@Override
	public String getName() {
		return Messages.SPORTY_ZOMBIE_NAME;
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
