package utilz;

import static utilz.Constants.EnemyConstants.ROBOT;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Robot;
import main.Game;;

public class LoadSave {

	public static final String PLAYER_ATLAS = "player_sprites.png";
	public static final String LEVEL_ATLAS = "outside_sprites.png";
	public static final String MENU_BUTTONS = "button_atlas.png";
	public static final String PLAYING_BACKGROUND = "wallpaper.png";
	public static final String MAIN_MENU_BACKGROUND = "main_menu_background.png";
	public static final String MAIN_MENU_LOGO = "menu_logo.png";
	public static final String ROBOT_ATLAS = "junkyard_robot.png";
	public static final String PAUSE_MENU_BACKGROUND = "pause_menu_background.png";
	public static final String PAUSE_BUTTONS = "pause_buttons.png";
	public static final String HEALTH_BAR = "Healthbar.png";
	public static final String SPIKE_TRAP = "spike_trap.png";
	public static final String STAGE_TWO_BACKGROUND = "stage_two.png";
	public static final String DROPS = "drops_sprite.png";
	public static final String G_COUNTER = "gear_counter.png";
	public static final String M_BOXES = "metalstuff_sprite.png";
	public static final String W_BOXES = "woodstuff_sprites.png";
	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

	public static BufferedImage[] GetAllLevels() {
		URL url = LoadSave.class.getResource("/lvls");
		File file = null;

		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		File[] files = file.listFiles();
		File[] filesSorted = new File[files.length];

		for (int i = 0; i < filesSorted.length; i++)
			for (int j = 0; j < files.length; j++) {
				if (files[j].getName().equals((i + 1) + ".png"))
					filesSorted[i] = files[j];

			}

		BufferedImage[] imgs = new BufferedImage[filesSorted.length];

		for (int i = 0; i < imgs.length; i++)
			try {
				imgs[i] = ImageIO.read(filesSorted[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		return imgs;
	}
	
}
