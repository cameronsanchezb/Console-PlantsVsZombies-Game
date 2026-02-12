package tp1.p2.logic.actions;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;

public class ExplosionAction implements GameAction {

	private int col;

	private int row;

	private int damage;

	private boolean  plantAttack;

	public ExplosionAction(int col, int row, int damage, boolean plantAttack) {
		this.col = col;
		this.row = row;
		this.damage = damage;
		this.plantAttack = plantAttack;
	}

	@Override
	public void execute(GameWorld game) {
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if(game.isValidZombiePosition(col + i, row + j)) {
					GameItem obj = game.getGameItemInPosition(col + i, row + j);
					if(obj != null) {
						if(plantAttack) obj.receivePlantAttack(damage, true);
						else obj.receiveZombieAttack(damage);
					}
				}
			}
		}
	}

}