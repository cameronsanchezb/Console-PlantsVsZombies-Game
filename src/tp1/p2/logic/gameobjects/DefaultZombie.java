package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class DefaultZombie extends Zombie{

	private final static int INIENDURANCE = 5;
	private final static int SPEED = 3;
	private final static int INIFREQUENCY = 1;
	private final static int DAMAGE = 1;

	public DefaultZombie(GameWorld game, int col, int row) {
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
		return Messages.ZOMBIE_SYMBOL;
	}

	@Override
	public String getName() {
		return Messages.ZOMBIE_NAME;
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
