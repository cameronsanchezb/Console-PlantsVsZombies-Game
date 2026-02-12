package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ZombieFactory {

	private static final List<Zombie> AVAILABLE_ZOMBIES = Arrays.asList(
			new DefaultZombie (null, -1, -1),
			new BucketHead (null, -1, -1),
			new Sporty (null, -1, -1),
			new ExplosiveZombie (null, -1, -1)
		);

	public static List<Zombie> getAvailableZombies() {
		return Collections.unmodifiableList(AVAILABLE_ZOMBIES);
	}

	public static Zombie spawnZombie(int zombieType, GameWorld game, int col, int row) throws GameException {
		if(game.isPositionNotBlocked(col, row)) {
			for(GameObject object : AVAILABLE_ZOMBIES) {
				if(AVAILABLE_ZOMBIES.indexOf(object) == zombieType) return (Zombie) object.createClone(game, col, row);
			}
		}
		throw new CommandExecuteException(Messages.INVALID_GAME_OBJECT);
	}

	public static boolean isValidZombie(int zombieIdx) {
		return zombieIdx >= 0 && zombieIdx < AVAILABLE_ZOMBIES.size();
	}

	private ZombieFactory() {
	}

}
