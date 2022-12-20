package object;

import main.Game;

public class Drops extends objects{
	private float hoverOffset;
	private int maxHoverOffset, hoverDir = 1;

	public Drops(int x, int y, int objType) {
		super(x, y, objType);
		doAnimation = false;

		initHitbox(16, 16);

		xDrawOffset = (int) (0 * Game.SCALE);
		yDrawOffset = (int) (0 * Game.SCALE);

		maxHoverOffset = (int) (10 * Game.SCALE);
	}

	public void update() {
		updateHover();
	}

	private void updateHover() {
		hoverOffset += (0.075f * Game.SCALE * hoverDir);

		if (hoverOffset >= maxHoverOffset)
			hoverDir = -1;
		else if (hoverOffset < 0)
			hoverDir = 1;

		hitbox.y = y + hoverOffset;
	}
}

