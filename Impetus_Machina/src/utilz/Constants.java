package utilz;

import main.Game;
public class Constants {

	public static class EnemyConstants {
		public static final int ROBOT = 0;

		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int ATTACK = 2;
		public static final int HIT = 3;
		public static final int DEAD = 4;

		public static final int ROBOT_WIDTH_DEFAULT = 64;
		public static final int ROBOT_HEIGHT_DEFAULT = 40;
		public static final int ROBOT_WIDTH = (int) (ROBOT_WIDTH_DEFAULT * Game.SCALE);
		public static final int ROBOT_HEIGHT = (int) (ROBOT_HEIGHT_DEFAULT * Game.SCALE);
		public static final int ROBOT_DRAWOFFSET_X = (int) (15 * Game.SCALE);
		public static final int ROBOT_DRAWOFFSET_Y = (int) (4* Game.SCALE);

		public static int GetSpriteAmount(int enemy_type, int enemy_state) {

			switch (enemy_type) {
			case ROBOT:
				switch (enemy_state) {
				case IDLE:
					return 3;
				case RUNNING:
					return 3;
				case ATTACK:
					return 3;
				case HIT:
					return 3;
				case DEAD:
					return 4;
				}
			}

			return 0;

		}

		public static final float GRAVITY = 0.04f * Game.SCALE;
		public static final int ANI_SPEED = 25;


			
		public static int GetMaxHealth(int enemy_type) {
			switch (enemy_type) {
			case ROBOT:
				return 5;
			default:
				return 1;
			}
		}

		public static int GetEnemyDmg(int enemy_type) {
			switch (enemy_type) {
			case ROBOT:
				return 2;
			default:
				return 0;
			}

		}

	}

	public static class ObjectConstants {
		public static final int SPIKE = 0;

		public static final int TOOLBOX = 1;
		public static final int GEARS = 2;
		public static final int BARREL = 3;
		public static final int BOX = 4;

		public static final int TOOLBOX_VALUE = 5;
		public static final int GEARS_VALUE = 5 ;

		public static final int CONTAINER_WIDTH_DEFAULT = 40;
		public static final int CONTAINER_HEIGHT_DEFAULT = 30;
		public static final int CONTAINER_WIDTH = (int) (Game.SCALE * CONTAINER_WIDTH_DEFAULT);
		public static final int CONTAINER_HEIGHT = (int) (Game.SCALE * CONTAINER_HEIGHT_DEFAULT);

		public static final int DROPS_WIDTH_DEFAULT = 16;
		public static final int DROPS_HEIGHT_DEFAULT = 16;
		public static final int DROPS_WIDTH = (int) (Game.SCALE * DROPS_WIDTH_DEFAULT);
		public static final int DROPS_HEIGHT = (int) (Game.SCALE * DROPS_HEIGHT_DEFAULT);

		public static int GetSpriteAmount(int object_type) {
			switch (object_type) {
			case TOOLBOX, GEARS:
				return 7;
			case BARREL, BOX:
				return 8;
			}
			return 1;
		}
		public static final int SPIKE_WIDTH_DEFAULT = 32;
		public static final int SPIKE_HEIGHT_DEFAULT = 32;
		public static final int SPIKE_WIDTH = (int) (Game.SCALE * SPIKE_WIDTH_DEFAULT);
		public static final int SPIKE_HEIGHT = (int) (Game.SCALE * SPIKE_HEIGHT_DEFAULT);
	}
	
	public static class UI {
		public static class Buttons {
			public static final int B_WIDTH_DEFAULT = 176;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
		}

		public static class Pause_Buttons {
			public static final int P_B_WIDTH_DEFAULT = 144;
			public static final int P_B_HEIGHT_DEFAULT = 48;
			public static final int P_B_WIDTH = (int) (P_B_WIDTH_DEFAULT * Game.SCALE);
			public static final int P_B_HEIGHT = (int) (P_B_HEIGHT_DEFAULT * Game.SCALE);

		}
	}

	public static class Directions {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class PlayerConstants {
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int JUMP = 2;
		public static final int FALLING = 3;
		public static final int GROUND = 4;
		public static final int HIT = 5;
		public static final int ATTACK_1 = 6;
		public static final int ATTACK_JUMP_1 = 7;
		public static final int ATTACK_JUMP_2 = 8;

		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
			case RUNNING:
				return 6;
			case IDLE:
				return 5;
			case HIT:
				return 4;
			case JUMP:
			case ATTACK_1:
			case ATTACK_JUMP_1:
			case ATTACK_JUMP_2:
				return 3;
			case GROUND:
				return 2;
			case FALLING:
			default:
				return 1;
			}
		}
	}

}
