package entities;
import static utilz.Constants.Directions.*;
import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.*;


import main.Game;

public abstract class Enemy extends Entity {
	protected int aniIndex, enemyState, enemytype;
	protected int aniTick, aniSpeed=35;
	protected boolean firstUpdate = true;
	protected boolean inAir;
	protected float fallSpeed;
	protected float gravity = 0.04f * Game.SCALE;
	protected float walkSpeed = 0.35f * Game.SCALE;
	protected int walkDir = LEFT;
	protected int tileY;
	protected float attackDistance = Game.TILES_SIZE;
	protected int maxHealth;
	protected int currentHealth;
	protected boolean active = true;
	protected boolean attackChecked;
	
	public Enemy(float x, float y, int width, int height, int enemytype) {
		super(x, y, width, height);
		this.enemytype=enemytype;
		initHitbox(x, y, width, height);
		maxHealth = GetMaxHealth(enemytype);
		currentHealth = maxHealth;
	}
	protected void firstUpdateCheck(int[][] lvlData) {
		if (!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
		firstUpdate = false;
	}
	protected void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(enemytype, enemyState)) {
				aniIndex = 0;
				switch (enemyState) {
				case ATTACK, HIT -> enemyState = IDLE;
				case DEAD -> active = false;
				}
			}
		}
	}

	protected void updateInAir(int[][] lvlData) {
		if (CanMoveHere(hitbox.x, hitbox.y + fallSpeed, hitbox.width, hitbox.height, lvlData)) {
			hitbox.y += fallSpeed;
			fallSpeed += gravity;
		} else {
			inAir = false;
			hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, fallSpeed);
			tileY = (int) (hitbox.y / Game.TILES_SIZE);
		}
	}
	protected void move(int[][] lvlData) {
		float xSpeed = 0;

		if (walkDir == LEFT)
			xSpeed = -walkSpeed;
		else
			xSpeed = walkSpeed;

		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
			if (IsFloor(hitbox, xSpeed, lvlData)) {
				hitbox.x += xSpeed;
				return;
			}
		changeWalkDir();
	}
	
	protected void turnTowardsPlayer(Player player) {
		if (player.hitbox.x > hitbox.x)
			walkDir = RIGHT;
		else
			walkDir = LEFT;
	}

	protected boolean canSeePlayer(int[][] lvlData, Player player) {
		int playerTileY = (int) (player.getHitbox().y / Game.TILES_SIZE);
		if (playerTileY == tileY)
			if (isPlayerInRange(player)) {
				if (IsSightClear(lvlData, hitbox, player.hitbox, tileY))
					return true;
			}

		return false;
	}

	protected boolean isPlayerInRange(Player player) {
		int absValue = (int) Math.abs(player.hitbox.x - hitbox.x);
		return absValue <= attackDistance * 5;
	}

	protected boolean isPlayerCloseForAttack(Player player) {
		int absValue = (int) Math.abs(player.hitbox.x - hitbox.x);
		
		return absValue <= attackDistance;
	}
	protected void changeWalkDir() {
		if (walkDir == LEFT)
			walkDir = RIGHT;
		else
			walkDir = LEFT;

	}
	protected void newState(int enemyState) {
		this.enemyState = enemyState;
		aniTick = 0;
		aniIndex = 0;
	}
	public int getEnemyState() {
		return enemyState;
	}
	public void setEnemyState(int enemyState) {
		this.enemyState = enemyState;
	}
	public int getAniIndex() {
		return aniIndex;
	}
	public void setAniIndex(int aniIndex) {
		this.aniIndex = aniIndex;
	}

}
