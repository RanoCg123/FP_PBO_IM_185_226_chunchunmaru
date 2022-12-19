package ui;

import static utilz.Constants.UI.Buttons.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import utilz.LoadSave;

public class MenuButton {

	private int xPos, yPos, rowIndex, index;
	private int xOffsetCenter = B_WIDTH / 2;
	private Gamestate state;
	private BufferedImage[] imgs;
	private boolean mouseOver, mousePressed;
	private Rectangle bounds;

	public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.rowIndex = rowIndex;
		this.state = state;
		loadImgs();
		initBounds();
	}

	private void initBounds() {
		bounds = new Rectangle((int) ((xPos) * 0.23), (int) (yPos * 0.5) + 125, (int) (B_WIDTH * 0.75),
				(int) (B_HEIGHT * 0.75));
	}

	private void loadImgs() {
		imgs = new BufferedImage[2];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
		for (int i = 0; i < imgs.length; i++)
			imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT,
					B_HEIGHT_DEFAULT);
	}

	
	public void draw(Graphics g) {
		g.drawImage(imgs[index], (int) ((xPos) * 0.23), (int) (yPos * 0.5) + 125, (int) (B_WIDTH * 0.75),
				(int) (B_HEIGHT * 0.75), null);
	}

	public void update() {
		index = 0;
		if (mouseOver)
			index = 1;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void applyGamestate() {
		Gamestate.state = state;
	}

	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}

}
