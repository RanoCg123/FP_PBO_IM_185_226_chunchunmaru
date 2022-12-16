package entities;
import static utilz.Constants.EnemyConstants.*;
public abstract class Enemy extends Entity {
	private int aniIndex, enemyState, enemytype;
	private int aniTick, aniSpeed=35;
	
	public Enemy(float x, float y, int width, int height, int enemytype) {
		super(x, y, width, height);
		this.enemytype=enemytype;
		initHitbox(x, y, width, height);
	}
	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(aniSpeed, aniIndex)) {
				aniIndex = 0;
			}

		}

	}
	public void update () {
		updateAnimationTick();
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
