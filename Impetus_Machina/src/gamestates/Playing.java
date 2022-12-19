package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.Gameover;
import ui.PauseMenu;
import utilz.LoadSave;

public class Playing extends State implements Statemethods {
	private Player player;
	private LevelManager levelManager;
	private BufferedImage backgroundImg;
	private EnemyManager enemyManager;
	private PauseMenu pauseMenu;
	private Gameover gameoverlayer;
	private boolean paused = false;

	private int xLvlOffset;
	private int leftBorder = (int) (0.25 * Game.GAME_WIDTH);
	private int rightBorder = (int) (0.75 * game.GAME_WIDTH);
	private int maxLvlOffsetX;

	private boolean gameover;
	private int lvlCompleted = 0;

	public Playing(Game game) {
		super(game);
		initClasses();
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BACKGROUND);

		calcLvlOffset();
		loadStartLevel();
	}

	public void setMaxLvlOffset(int lvlOffset) {
		this.maxLvlOffsetX = lvlOffset;
	}

	public void loadNextLevel() {
		resetAll();
		levelManager.loadNextLevel();
		lvlCompleted++;
		if (lvlCompleted % 2 == 1) {
			backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.STAGE_TWO_BACKGROUND);
		} else {
			backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BACKGROUND);
		}
	}

	private void loadStartLevel() {
		enemyManager.loadEnemies(levelManager.getCurrentLevel());
	}

	private void calcLvlOffset() {
		maxLvlOffsetX = levelManager.getCurrentLevel().getLvlOffset();
	}

	private void initClasses() {
		levelManager = new LevelManager(game);
		enemyManager = new EnemyManager(this);
		player = new Player(200, 500, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE), this);
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		pauseMenu = new PauseMenu(this);
		gameoverlayer = new Gameover(this);
	}

	@Override
	public void update() {
		if (!paused && !gameover) {
			levelManager.update();
			enemyManager.update(levelManager.getCurrentLevel().getLevelData(), getPlayer());
			checkBorder();
			player.update();
		} else {
			pauseMenu.update();
		}
	}

	private void checkBorder() {
		int playerX = (int) player.getHitbox().x;
		int diff = playerX - xLvlOffset;
		if (diff > rightBorder) {
			xLvlOffset += diff - rightBorder;
		} else if (diff < leftBorder) {
			xLvlOffset += diff - leftBorder;
		}

		if (xLvlOffset > maxLvlOffsetX) {
			xLvlOffset = maxLvlOffsetX;
		} else if (xLvlOffset < 0) {
			xLvlOffset = 0;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		g.setColor(new Color(0, 0, 0, 40));
		g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
		levelManager.draw(g, xLvlOffset);
		player.render(g, xLvlOffset);
		enemyManager.draw(g, xLvlOffset);

		if (paused) {
			g.setColor(new Color(0, 0, 0, 100));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
			pauseMenu.draw(g);
		} else if (gameover) {
			gameoverlayer.draw(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			player.setAttacking(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (gameover) {
			gameoverlayer.keyPressed(e);
		} else
			switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				player.setLeft(true);
				break;
			case KeyEvent.VK_D:
				player.setRight(true);
				break;
			case KeyEvent.VK_SPACE:
				player.setJump(true);
				player.jcount();
				break;
			case KeyEvent.VK_BACK_SPACE:
				paused = !paused;
				break;
			case KeyEvent.VK_ESCAPE:
				paused = !paused;
				break;
			}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		}

	}

	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!gameover)
			if (paused)
				pauseMenu.mousePressed(e);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!gameover)
			if (paused)
				pauseMenu.mouseReleased(e);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (!gameover)
			if (paused)
				pauseMenu.mouseMoved(e);

	}

	public void unpauseGame() {
		paused = false;
	}

	public void windowFocusLost() {
		player.resetDirBooleans();
	}

	public Player getPlayer() {
		return player;
	}

	public EnemyManager getEnemyManager() {
		return enemyManager;
	}

	public void resetAll() {
		gameover = false;
		paused = false;
		lvlCompleted = 0;
		player.resetAll();
		enemyManager.resetAllEnemies();
	}

	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		enemyManager.checkEnemyHit(attackBox);

	}

	public void setGameOver(boolean b) {
		this.gameover = b;

	}

	public void setLevelCompleted(int levelCompleted) {
		this.lvlCompleted = levelCompleted;
	}

}
