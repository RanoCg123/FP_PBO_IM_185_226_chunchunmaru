package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] RobotArr;
	private ArrayList<Robot> robots = new ArrayList<>();

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
		addEnemies();
	}
	private void addEnemies() {
		robots = LoadSave.GetRobots();
	}
	public void update() {
		for (Robot c : robots)
				c.update();
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawRobots(g, xLvlOffset);
	}

	private void drawRobots(Graphics g, int xLvlOffset) {
		for (Robot c : robots)
				g.drawImage(RobotArr[c.getEnemyState()][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset - ROBOT_DRAWOFFSET_X , (int) c.getHitbox().y - ROBOT_DRAWOFFSET_Y,
						ROBOT_WIDTH , ROBOT_HEIGHT, null);
//				c.drawHitbox(g);
//				c.drawAttackBox(g, xLvlOffset);


	}

	private void loadEnemyImgs() {
		RobotArr = new BufferedImage[1][1];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.ROBOT_ATLAS);
		for (int j = 0; j < RobotArr.length; j++)
			for (int i = 0; i < RobotArr[j].length; i++)
				RobotArr[j][i] = temp.getSubimage(i * ROBOT_WIDTH_DEFAULT, j * ROBOT_HEIGHT_DEFAULT, ROBOT_WIDTH_DEFAULT, ROBOT_HEIGHT_DEFAULT);
	}
}
