package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public abstract class Zombie extends GameObject{

	private int speed;
	
	private boolean diedExplosion;

	public Zombie(GameWorld game, int col, int row) {
		super(game, col, row);
		speed = getIniSpeed();
	}
	
	protected abstract String getInitials();

	protected abstract int getIniSpeed();

	@Override
	public String getDescription() {
		return String.format(Messages.ZOMBIE_DESCRIPTION, getName(), getIniSpeed() - 1, getDamage(), endurance);
	}

	protected abstract String getName();

	@Override
	public void update() {
		advance();
		super.update();
	}

	@Override
	public void onEnter() {
		game.zombieSpawned();
	}

	@Override
	public void onExit() {
		game.zombieDied(diedExplosion);
	}

	@Override
	public void attack() {
		int i = col - 1;
		boolean attacked = false;
		while(i >= col - getRange() && !attacked) {
			GameItem other = game.getGameItemInPosition(i, row);
			if(other != null) attacked = other.receiveZombieAttack(this.getDamage());
			i--;
		}
	}

	@Override
	protected abstract int getDamage();

	@Override
	public boolean receivePlantAttack(int damage, boolean explosion) {
		endurance -= damage;
		if (endurance <= 0 && explosion) diedExplosion = true;
		else diedExplosion = false;
		return true;
	}

	@Override
	public boolean receiveZombieAttack(int damage) { //Los zombies no se pegan entre ellos
		return false;
	}

	@Override
	protected abstract int getIniFrequency();

	@Override
	public int getCost() {
		return 0;
	}

	@Override
	public int getRange() {
		return 1;
	}

	protected void advance() {
		if(!game.blockAdvance(col - 1, row)) { //Comprueba si puede avanzar
			if(speed <= 1) {
				col--;
				if(col < 0) game.zombiesWon();
			}
		}
		nextSpeed();
	}

	protected void nextSpeed() { //Actualiza la frecuencia de avance
		if(speed <= 1) speed = getIniSpeed();
		speed--;
	}

	@Override
	public boolean catchObject() {
		return false;
	}
}
