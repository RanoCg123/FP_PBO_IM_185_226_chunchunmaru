package entities;
import static utilz.Constants.EnemyConstants.*;
public class Robot extends Enemy{

	public Robot(float x, float y) {
		super(x, y, ROBOT_WIDTH, ROBOT_HEIGHT, ROBOT);
	}

}
