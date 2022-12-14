package levels;

import static utilz.HelpMethods.GetLevelData;
//import static utilz.HelpMethods.GetPlayerSpawn;
import static utilz.HelpMethods.GetRobots;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Robot;
import main.Game;
import object.Containers;
import object.Drops;
import utilz.HelpMethods;

public class Level {

	private BufferedImage img;
	private int[][] lvlData;
	private ArrayList<Robot> robots;
	private ArrayList<Drops> drops;
	private ArrayList<Containers> containers;
	private int lvlTilesWide;
	private int maxTilesOffset;
	private int maxLvlOffsetX;
	private Point playerSpawn;

	public Level(BufferedImage img) {
		this.img = img;
		createLevelData();
		createEnemies();
		createDrops();
		createContainers();
		calcLvlOffsets();
	}
	private void createContainers() {
	containers = HelpMethods.GetContainers(img);
}

	private void createDrops() {
	drops = HelpMethods.GetDrops(img);
	}
	
	private void calcLvlOffsets() {
		lvlTilesWide = img.getWidth();
		maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
		maxLvlOffsetX = Game.TILES_SIZE * maxTilesOffset;
	}

	private void createEnemies() {
		robots = GetRobots(img);
	}

	private void createLevelData() {
		lvlData = GetLevelData(img);
	}

	public int getSpriteIndex(int x, int y) {
		return lvlData[y][x];
	}

	public int[][] getLevelData() {
		return lvlData;
	}

	public int getLvlOffset() {
		return maxLvlOffsetX;
	}

	public ArrayList<Robot> getRobots() {
		return robots;
	}

	public ArrayList<Drops> getDrops() {
		return drops;
	}
	public void setDrops(ArrayList<Drops> drops) {
		this.drops = drops;
	}
	public ArrayList<Containers> getContainers() {
		return containers;
	}
	public void setContainers(ArrayList<Containers> containers) {
		this.containers = containers;
	}
	public Point getPlayerSpawn() {
		return playerSpawn;
	}

}