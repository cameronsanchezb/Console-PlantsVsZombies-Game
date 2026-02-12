package tp1.p2.control.commands;

import java.io.IOException;

import tp1.p2.control.Command;
import tp1.p2.control.Level;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.LevelCastException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ResetCommand extends Command {

	private Level level;

	private long seed;

	public ResetCommand() {
	}

	public ResetCommand(Level level, long seed) {
		this.level = level;
		this.seed = seed;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException{
		if(this.level == null) {
			game.reset();
		} else {
			game.reset(this.level, this.seed);
		}
		return true;

	}

	@Override
	public Command create(String[] parameters) throws GameException {
		if(parameters.length == 1) {
			return new ResetCommand();
		}
		else if(parameters.length == 3) {
			try {
			return new ResetCommand(Level.valueOfIgnoreCase(parameters[1]), Integer.parseInt(parameters[2]));
			}
			catch(NumberFormatException e) {
				throw new CommandParseException(Messages.INVALID_COMMAND);
			}
			catch(LevelCastException e) {
				throw new CommandParseException(Messages.INVALID_COMMAND);
			}
		}
		else return null;
	}

	@Override
	public boolean gameShouldUpdate() {
		return false;
	}

}