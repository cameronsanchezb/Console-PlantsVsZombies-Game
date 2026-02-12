package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public abstract class Plant extends GameObject{

	 public Plant(GameWorld game, int col, int row){
		 super(game, col, row);
	 }
	
	protected abstract String getInitials();

	public String getDescription() {
		return String.format(Messages.PLANT_DESCRIPTION, getName(), getCost(), getDamage(), endurance);
	}
	
	protected abstract String getName();

	@Override
	public void onEnter() {
	}

	@Override
	public void onExit() {
	}

	@Override
	public abstract boolean match(String name);

	@Override
	public boolean receivePlantAttack(int damage, boolean explosion) { //Las plantas no se pegan entre ellas
		return false;
	}

	@Override
	public boolean receiveZombieAttack(int damage){
		endurance -= damage;
		return true;
	}

	@Override
	public void attack() {
		int i = col + 1;
		boolean attacked = false;
		while(i <= col + getRange() && !attacked) {
			GameItem other = game.getGameItemInPosition(i, row);
			if(other != null) attacked = other.receivePlantAttack(this.getDamage(), false);
			i++;
		}
	}

	@Override
	public boolean catchObject() {
		return false;
	}
}
