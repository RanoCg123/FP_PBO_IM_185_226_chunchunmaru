package entities;

import static utilz.Constants.EnemyConstants.ROBOT_DRAWOFFSET_X;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] RobotArr;
	private ArrayList<Robot> robots = new ArrayList<>();

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
	}
	
	public void loadEnemies(Level level) {
		robots = level.getRobots();
	}
	public void update(int[][]lvldata, Player player) {
		boolean isAnyActive = false;
		for (Robot c : robots)
			
			if (c.isActive()) {
				c.update(lvldata, player);
				isAnyActive = true;
			}
		if(!isAnyActive)
			playing.loadNextLevel();
//			playing.setLevelCompleted(true);
	}
	
	public void draw(Graphics g, int xLvlOffset) {
		drawRobots(g, xLvlOffset);
	//	for (Robot a : robots)
		//	a.drawHitbox(g, xLvlOffset);
		//for (Robot a : robots)
			//a.drawAttackBox(g, xLvlOffset);
	};

	private void drawRobots(Graphics g, int xLvlOffset) {
		for (Robot c : robots)
			if (c.isActive())
				g.drawImage(RobotArr[c.getEnemyState()][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset - ROBOT_DRAWOFFSET_X + c.flipX(), (int) c.getHitbox().y - 10,
						(int)(64 * 2)*c.flipW(), (int)(40 * 2), null);


	}
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for (Robot c : robots)
			if(c.getCurrentHealth()>0)
			if (c.isActive())
				if (attackBox.intersects(c.getHitbox())) {
					c.hurt(2, playing); 
					return;
				}
	}
	private void loadEnemyImgs() {
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.ROBOT_ATLAS);
		RobotArr = new BufferedImage[5][4];
		for (int j = 0; j < RobotArr.length; j++)
			for (int i = 0; i < RobotArr[j].length; i++)
				RobotArr[j][i] = temp.getSubimage(i * 64, j * 40, 64, 40);
	}

	public void resetAllEnemies() {
		for (Robot c : robots)
			c.resetEnemy();
	}
}
