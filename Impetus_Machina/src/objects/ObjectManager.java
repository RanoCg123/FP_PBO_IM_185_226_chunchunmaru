package objects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import utilz.LoadSave;

public class ObjectManager {
	private BufferedImage[][] SpikeArr;
	private ArrayList<Spike> spikes = new ArrayList<>();

	private void loadSpikes() {
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SPIKE_TRAP);
		SpikeArr = new BufferedImage[5][4];
		for (int j = 0; j < SpikeArr.length; j++)
			for (int i = 0; i < SpikeArr[j].length; i++)
				SpikeArr[j][i] = temp.getSubimage(i * 64, j * 40, 64, 40);
	}
}
