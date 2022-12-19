package entities;
import static utilz.Constants.EnemyConstants.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;

import static utilz.Constants.Directions.*;
import main.Game;
public class Robot extends Enemy{

	public Robot(float x, float y) {
		super(x, y, ROBOT_WIDTH, ROBOT_HEIGHT, ROBOT);
		initHitbox(x, y, (int)(16*Game.SCALE), (int)(24*Game.SCALE));
	}
	public void update (int[][]lvldata, Player player) {
		updatemove(lvldata, player);
		updateAnimationTick();
		
	}
	private void updatemove(int[][] lvlData, Player player) {
		if (firstUpdate)
			firstUpdateCheck(lvlData);
		if (inAir)
			updateInAir(lvlData);
			switch (enemyState) {
			case IDLE:
				newState(RUNNING);
				break;
			case RUNNING:
				if (canSeePlayer(lvlData, player))
					turnTowardsPlayer(player);
				if (isPlayerCloseForAttack(player))
					newState(ATTACK);
				move(lvlData);
				break;
			case ATTACK:

				break;
			case HIT:
				break;
			}
		}
	public int flipX() {
		if (walkDir == RIGHT)
			return width;
		else
			return 0;
	}

	public int flipW() {
		if (walkDir == RIGHT)
			return -1;
		else
			return 1;

	}
	}
