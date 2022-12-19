package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;

import static utilz.Constants.UI.Buttons.B_HEIGHT_DEFAULT;
import static utilz.Constants.UI.Buttons.B_WIDTH_DEFAULT;
import static utilz.Constants.UI.Pause_Buttons.*;

public class PauseSelectButton extends PauseButton {
	private BufferedImage[] imgs;
	private int rowIndex, index;
	private boolean mouseOver, mousePressed;

	public PauseSelectButton(int x, int y, int width, int height, int rowIndex) {
		super(x, y, width, height);
		this.rowIndex = rowIndex;
		loadImgs();
	}

	private void loadImgs() {
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BUTTONS);
		imgs = new BufferedImage[2];
		for (int i = 0; i < imgs.length; i++)
			imgs[i] = temp.getSubimage(i * P_B_WIDTH_DEFAULT, rowIndex * P_B_HEIGHT_DEFAULT, P_B_WIDTH_DEFAULT,
					P_B_HEIGHT_DEFAULT);

	}
 
	public void update() {
		index = 0;
		if (mouseOver)
			index = 1;
		if(mousePressed) {
			index = 1;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(imgs[index], x, y, P_B_WIDTH, P_B_HEIGHT, null);
	}

	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
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

}