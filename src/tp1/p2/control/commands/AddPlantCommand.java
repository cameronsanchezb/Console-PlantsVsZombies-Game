package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPositionException;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class AddPlantCommand extends Command{

	private int col;

	private int row;

	private String plantName;

	protected boolean consumeCoins;

	public AddPlantCommand() {
	}

	public AddPlantCommand(String plantName, int col, int row) {
		this.plantName = plantName;
		this.col = col;
		this.row = row;
		this.consumeCoins = true;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		if(!PlantFactory.isValidPlant(plantName)) throw new CommandParseException(Messages.INVALID_GAME_OBJECT);
		return game.addPlant(this.plantName, this.col, this.row, consumeCoins);
	}

	@Override
	public Command create(String[] parameters) throws GameException{
		if(parameters.length != 4) {
			throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
		}
		AddPlantCommand command = null;
		try {
			command = new AddPlantCommand(parameters[1], Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]));
		} catch(NumberFormatException e){
			throw new InvalidPositionException(Messages.INVALID_POSITION, parameters[2], parameters[3]);
		}
		return command;
	}

}