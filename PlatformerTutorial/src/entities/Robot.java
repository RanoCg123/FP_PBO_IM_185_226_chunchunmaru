package entities;
import static utilz.Constants.EnemyConstants.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;

import static utilz.Constants.Directions.*;
import main.Game;
public class Robot extends Enemy{
	private Rectangle2D.Float attackBox;
	private int attackBoxOffsetX;
	public Robot(float x, float y) {
		super(x, y, ROBOT_WIDTH, ROBOT_HEIGHT, ROBOT);
		initHitbox(x, y, (int)(30*Game.SCALE), (int)(24*Game.SCALE));
		initAttackBox();
	}
	public void update (int[][]lvldata, Player player) {
		updatemove(lvldata, player);
		updateAnimationTick();
		updateAttackBox();
	}
	private void updateAttackBox() {
		if (walkDir == RIGHT)
			attackBox.x = hitbox.x + hitbox.width + (int) (Game.SCALE * 5);
		else if (walkDir == LEFT)
			attackBox.x = hitbox.x - hitbox.width - (int) (Game.SCALE * 5);

		attackBox.y = hitbox.y + (Game.SCALE * 10);
	}
	private void initAttackBox() {
		attackBox = new Rectangle2D.Float(x, y, (int) (20 * Game.SCALE), (int) (20 * Game.SCALE));
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
				if (aniIndex == 0)
					attackChecked = false;
				if (aniIndex == 2 && !attackChecked)
					checkPlayerHit(attackBox, player);

				break;
			case HIT:
				break;
			case DEAD:
				break;
			}
		}
	
	public void drawAttackBox(Graphics g, int xLvlOffset) {
		g.setColor(Color.red);
		g.drawRect((int) (attackBox.x - xLvlOffset), (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
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
