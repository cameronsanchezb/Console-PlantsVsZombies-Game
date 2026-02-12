package tp1.p2.control;

import static tp1.p2.view.Messages.debug;
import static tp1.p2.view.Messages.error;

import java.util.Scanner;

import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.Game;
import tp1.p2.view.GamePrinter;
import tp1.p2.view.Messages;

/**
 * Accepts user input and coordinates the game execution logic.
 */
public class Controller {

	private Game game;

	private Scanner scanner;

	private GamePrinter gamePrinter;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		this.gamePrinter = new GamePrinter(game);
	}

	/**
	 * Draw / Paint the game.
	 */
	private void printGame() {
		System.out.println(gamePrinter);
	}

	/**
	 * Prints the final message once the match is finished.
	 */
	public void printEndMessage() {
		System.out.println(gamePrinter.endMessage());
	}
	
	/**
	 * Prints the updated/current record 
	 */
	private void printRecord(boolean b) {
		
		if (b) System.out.print(Messages.NEW_RECORD);
		else System.out.print(Messages.RECORD);
		System.out.println(game.getRecord());
		
	}
	
	/**
	 * Show prompt and request command.
	 *
	 * @return the player command as words
	 */
	private String[] prompt() {
		System.out.print(Messages.PROMPT);
		String line = scanner.nextLine();
		String[] words = line.toLowerCase().trim().split("\\s+");

		System.out.println(debug(line));

		return words;
	}

	/**
	 * Runs the game logic.
	 * @throws CloneNotSupportedException
	 */
	public void run(){
		boolean refreshDisplay = true;

		while (!game.isFinished() && !game.isPlayerQuits()) {

			// 1. Draw
			if (refreshDisplay) {
				if(game.getCycles() == 0) System.out.println(menuInicio());
				printGame();
			}

			String[] words = prompt();

			try {
				refreshDisplay = false;
				// 2-4. User action & Game Action & Update
				Command command = Command.parse(words);
				refreshDisplay = game.execute(command);
			} catch (GameException e) {
				System.out.println(error(e.getMessage()));
			}
		}

		if (refreshDisplay) {
			printGame();
		}
		
		printEndMessage();
		
		if(!game.isPlayerQuits()) {
			try {
				game.guardarRecord(); //Guarda en fichero cuando ganan zombies o ganan plantas
				if(!game.isZombieInHouse()) {
						printRecord(game.isNewRecord()); //Escribe solo si ganan plantas
				}
			} catch(GameException e) {
				System.out.println(error(e.getMessage()));
			}
		}
		
	}

	private String menuInicio() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(String.format(Messages.CONFIGURED_LEVEL, game.levelToString()));
		buffer.append(System.lineSeparator());
		buffer.append(String.format(Messages.CONFIGURED_SEED, game.getSeed()));
		return buffer.toString();
	}

}