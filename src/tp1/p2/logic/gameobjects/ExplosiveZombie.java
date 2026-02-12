package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class ExplosiveZombie extends Zombie{

	private final static int INIENDURANCE = 5;
	private final static int SPEED = 3;
	private final static int INIFREQUENCY = 1;
	private final static int DAMAGE = 1;
	private final static int EXPLOSION_DAMAGE = 3; 

	public ExplosiveZombie(GameWorld game, int col, int row) {
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
		return Messages.EXPLOSIVE_ZOMBIE_SYMBOL;
	}

	@Override
	public String getName() {
		return Messages.EXPLOSIVE_ZOMBIE_NAME;
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
	public void onExit() {
		super.onExit();
		ExplosionAction explode = new ExplosionAction(col, row, EXPLOSION_DAMAGE, false);
		game.addAction(explode);
	}

}
