package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPositionException;
import tp1.p2.view.Messages;

public class AddPlantCheatCommand extends AddPlantCommand{

	public AddPlantCheatCommand() {
	}
	
	public AddPlantCheatCommand(String plantName, int col, int row) {
		super(plantName, col, row);
		this.consumeCoins = false;
	}
	
	@Override
	protected String getName() {
		return Messages.COMMAND_CHEAT_PLANT_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CHEAT_PLANT_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_CHEAT_PLANT_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_CHEAT_PLANT_HELP;
	}

	@Override
	public Command create(String[] parameters) throws GameException {
		if(parameters.length != 4) {
			throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
		}
		AddPlantCommand command = null;
		try {
		command = new AddPlantCheatCommand(parameters[1], Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]));
		} catch(NumberFormatException e){
			throw new InvalidPositionException(Messages.INVALID_POSITION, parameters[2], parameters[3]);
		}

		return command;
	}
}
