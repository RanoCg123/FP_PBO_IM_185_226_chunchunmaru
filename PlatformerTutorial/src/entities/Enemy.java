package entities;

public abstract class Enemy extends Entity {
	private int aniIndex, enemyState, enemytype;
	
	public Enemy(float x, float y, int width, int height, int enemytype) {
		super(x, y, width, height);
		this.enemytype=enemytype;
		initHitbox(x, y, width, height);
	}
	public void update () {
		
	}
	public int getEnemyState() {
		return enemyState;
	}
	public void setEnemyState(int enemyState) {
		this.enemyState = enemyState;
	}

}
