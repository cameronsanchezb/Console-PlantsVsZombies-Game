package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class PlantFactory {

	/* @formatter:off */
	private static final List<Plant> AVAILABLE_PLANTS = Arrays.asList(
		new Sunflower (null, -1, -1),
		new Peashooter (null, -1, -1),
		new Wallnut (null, -1, -1),
		new Cherrybomb (null, -1, -1)
	);
	/* @formatter:on */

	public static GameObject spawnPlant(String plantName, GameWorld game, int col, int row) throws GameException {
		if(game.isPositionNotBlocked(col, row)) {
			for(GameObject object : AVAILABLE_PLANTS) {
				if(object.match(plantName)) return object.createClone(game, col, row);
			}
		}
		throw new CommandExecuteException(Messages.INVALID_GAME_OBJECT);

	}

	public static List<Plant> getAvailablePlants() {
		return Collections.unmodifiableList(AVAILABLE_PLANTS);
	}

	/*
	 * Avoid creating instances of this class
	 */
	private PlantFactory() {
	}

	public static boolean isValidPlant(String plantName) {
		for(GameObject object : AVAILABLE_PLANTS) {
			if(object.match(plantName)) return true;
		}
		return false;
	}
}
