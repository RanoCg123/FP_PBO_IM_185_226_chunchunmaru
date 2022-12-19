package ui;

import static utilz.Constants.UI.Pause_Buttons.*;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

public class PauseMenu {

	private Playing playing;
	private BufferedImage backgroundImg;
	private int bgX, bgY, bgW, bgH;
	private PauseSelectButton menuB, replayB, unpauseB;

	public PauseMenu(Playing playing) {
		this.playing = playing;
		loadBackground();
		createPauseButtons();
	}

	private void createPauseButtons() {
		int buttonX = (int) (313 * Game.SCALE);
		int buttonY = (int) (325 * Game.SCALE);

		menuB = new PauseSelectButton(buttonX + 60, buttonY - 100, P_B_WIDTH_DEFAULT, P_B_HEIGHT_DEFAULT, 2);
		replayB = new PauseSelectButton(buttonX + 60, buttonY - 200, P_B_WIDTH_DEFAULT, P_B_HEIGHT_DEFAULT, 1);
		unpauseB = new PauseSelectButton(buttonX + 60, buttonY - 300, P_B_WIDTH_DEFAULT, P_B_HEIGHT_DEFAULT, 0);
	}

	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_MENU_BACKGROUND);
		bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
		bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = (int) (25 * Game.SCALE);
	}

	public void update() {
		menuB.update();
		replayB.update();
		unpauseB.update();
	}

	public void draw(Graphics g) {
		// Background
		g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);

		// Buttons
		menuB.draw(g);
		replayB.draw(g);
		unpauseB.draw(g);
	}

	public void mousePressed(MouseEvent e) {
		if (isIn(e, menuB))
			menuB.setMousePressed(true);
		else if (isIn(e, replayB))
			replayB.setMousePressed(true);
		else if (isIn(e, unpauseB))
			unpauseB.setMousePressed(true);
	}

	public void mouseReleased(MouseEvent e) {
		if (isIn(e, menuB)) {
			if (menuB.isMousePressed()) {
				Gamestate.state = Gamestate.MENU;
				playing.unpauseGame();
			}
		} else if (isIn(e, replayB)) {
			if (replayB.isMousePressed()) {
				System.out.println("replay lvl!");
				playing.resetAll();
				playing.unpauseGame();
			}
		} else if (isIn(e, unpauseB)) {
			if (unpauseB.isMousePressed())
				playing.unpauseGame();
		}
		menuB.resetBools();
		replayB.resetBools();
		unpauseB.resetBools();
	}

	public void mouseMoved(MouseEvent e) {
		menuB.setMouseOver(false);
		replayB.setMouseOver(false);
		unpauseB.setMouseOver(false);

		if (isIn(e, menuB))
			menuB.setMouseOver(true);
		else if (isIn(e, replayB))
			replayB.setMouseOver(true);
		else if (isIn(e, unpauseB))
			unpauseB.setMouseOver(true);

	}

	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

}