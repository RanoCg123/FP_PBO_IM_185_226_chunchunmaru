package objects;

import main.Game;

public class Spike extends GameObject{

	public Spike(int x, int y, int objType) {
		super(x,y,objType);
		
		initHitbox(32,24);
		xDrawOffset = 0;
		yDrawOffset = (int)(Game.SCALE * 24);
		hitbox.y += yDrawOffset;
	}
}
