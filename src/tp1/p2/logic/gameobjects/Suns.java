package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Suns extends GameObject{

		public Suns(GameWorld game, int col, int row) {
			super(game, col, row);
			frequency = INIFREQUENCY;
			endurance = 1;
		}

		private static final int COIN_VALUE = 10;
		private static final int INIFREQUENCY = 10;
		
		@Override
		protected String getSymbol() {
			return String.format(Messages.GAME_OBJECT_STATUS, getInitials(), frequency);
		}


		@Override
		public String getInitials() {
			return Messages.SUN_SYMBOL;
		}


		@Override
		public int getCost() {
			return 0;
		}


		@Override
		public String getDescription() {
			return null;
		}


		@Override
		public void attack() {
		}


		@Override
		public void onEnter() {
			game.incrementGeneratedSuns();
		}


		@Override
		protected int getDamage() {
			return 0;
		}


		@Override
		public boolean receivePlantAttack(int damage, boolean explosion) {
			return false;
		}


		@Override
		public boolean receiveZombieAttack(int damage) {
			return false;
		}


		@Override
		public int getRange() {
			return 0;
		}

		@Override
		public boolean catchObject() {
			endurance = 0;
			game.increaseSuncoins();
			game.incrementCaughtSuns();
			return true;
		}

		@Override
		public int addedSuns() {
			return 1;
		}

		@Override
		public void update() {
			nextFrequency();
			if(frequency == 0) endurance = 0;
		}

		@Override
		public void nextFrequency() {
			frequency--;
		}


		@Override
		protected int getIniFrequency() {
			return INIFREQUENCY;
		}

		@Override
		public boolean blockAdvance(int col, int row) {
			return false;

		}


		public static int getCoinValue() {
			return COIN_VALUE;
		}


		@Override
		public void onExit() {
		}
}
