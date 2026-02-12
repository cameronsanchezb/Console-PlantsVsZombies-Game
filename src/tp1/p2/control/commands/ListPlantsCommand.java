package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class ListPlantsCommand extends Command {

	@Override
	protected String getName() {
		return Messages.COMMAND_LIST_NAME;
	}
	@Override
	protected String getShortcut() {
		return Messages.COMMAND_LIST_SHORTCUT;
	}
	@Override
	public String getDetails() {
		return Messages.COMMAND_LIST_DETAILS;
	}
	@Override
	public String getHelp() {
		return Messages.COMMAND_LIST_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		StringBuilder buffer = new StringBuilder();
		buffer.append(Messages.AVAILABLE_PLANTS);
		buffer.append(System.lineSeparator());
		for(Plant plant : PlantFactory.getAvailablePlants()) {
			buffer.append(plant.getDescription());
			buffer.append(System.lineSeparator());
		}
		System.out.println(buffer.toString());
		return false;
	}

}