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
			{	g.setColor(Color.BLACK);
				g.fillRect((int) c.getHitbox().x - xLvlOffset, (int) c.getHitbox().y,ROBOT_WIDTH , ROBOT_HEIGHT);
				g.setColor(Color.BLACK);
				g.drawRect((int) c.getHitbox().x - xLvlOffset, (int) c.getHitbox().y,ROBOT_WIDTH , ROBOT_HEIGHT);
//				c.drawHitbox(g);
//				c.drawAttackBox(g, xLvlOffset);

			}

	}

	private void loadEnemyImgs() {}
}
