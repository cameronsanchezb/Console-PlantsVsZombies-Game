package tp1.p2.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.view.Messages;

public class GameObjectContainer {

	private List<GameObject> gameObjects;

	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}

	public boolean isPositionEmpty(int col, int row) {
		for(GameObject object : gameObjects) {
			if(object.isInPosition(col, row)) return false;
		}
		return true;
	}

	public boolean addGameObject(GameObject object) {
		boolean added = gameObjects.add(object);
		if(added) object.onEnter();
		return added;
	}

	public String positionToString(int col, int row) {
		StringBuilder buffer = new StringBuilder();
		boolean sunPainted = false;
		boolean sunAboutToPaint = false;

		for (GameObject g : gameObjects) {
			if(g.isAlive() && g.isInPosition(col, row)) {
				String objectText = g.toString();
				sunAboutToPaint = objectText.indexOf(Messages.SUN_SYMBOL) >= 0;
				if (sunAboutToPaint) {
					if (!sunPainted) {
						buffer.append(objectText);
						sunPainted = true;
					}
				} else {
					buffer.append(objectText);
				}
			}
		}

		return buffer.toString();
	}

	public GameItem getGameItemInPosition(int col, int row) {
		for(GameObject object : gameObjects) {
			if(object.blockAdvance(col, row)) {
				GameItem item = object;
				return item;
			}
		}
		return null;
	}

	public void update() {
		int fin = gameObjects.size();
		for(int i = 0; i < fin; i++) {
			updateObject(i);
		}
	}

	private void updateObject(int i) {
		GameObject obj = gameObjects.get(i);
		obj.update();
	}

	boolean eraseDead() {
		ArrayList<GameObject> objList = new ArrayList<>();
		for(GameObject object : gameObjects) {
			if(object.isAlive()) {
				objList.add(object);
			}
			else {
				object.onExit();
			}
		}
		boolean erased = true;
		if(objList.size() == gameObjects.size()) erased = false;
		gameObjects = objList;
		return erased;
	}

	public boolean isSunInPosition(int col, int row) {
		for(GameObject g : gameObjects) {
			if(g.catchObject() && g.isAlive()) return true;
		}
		return false;
	}

	public int catchObject(int col, int row) {
		int generated = 0;
		for(GameObject g : gameObjects) {
			if(g.isInPosition(col, row) && g.catchObject()) generated += g.addedSuns();
		}
		eraseDead();
		return generated;
	}

	public boolean blockPosition(int col, int row) {
		for(GameObject object : gameObjects) {
			if(object.blockAdvance(col, row)) return true;
		}
		return false;
	}
}