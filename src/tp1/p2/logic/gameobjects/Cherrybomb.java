package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class Cherrybomb extends Plant{

	private final static int COST = 50;
	private final static int DAMAGE = 10;
	private final static int INIENDURANCE = 2;
	private final static int INIFREQUENCY = 3;

	public Cherrybomb(GameWorld game, int col, int row) {
		super(game, col, row);
		endurance = INIENDURANCE;
		frequency = INIFREQUENCY;
	}

	@Override
	protected String getInitials(){
		if(frequency >= 2) return Messages.CHERRY_BOMB_SYMBOL;
		else return Messages.CHERRY_BOMB_SYMBOL.toUpperCase();
	}

	@Override
	public String getName() {
		return Messages.CHERRY_BOMB_NAME_SHORTCUT;
	}

	@Override
	public boolean match(String name) {
		if("cherry-bomb".equals(name) || "c".equals(name)) return true;
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
		return 1;
	}

	@Override
	protected int getIniFrequency() {
		return INIFREQUENCY;
	}

	@Override
	public void attack() {
		if(frequency == 1) {
			game.addAction(new ExplosionAction(col, row, DAMAGE, true));
			endurance = 0;
		}
	}

}
