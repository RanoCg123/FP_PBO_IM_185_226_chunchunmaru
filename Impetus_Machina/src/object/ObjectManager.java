package object;

import static utilz.Constants.ObjectConstants.*;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import levels.Level;
import objects.*;
import utilz.LoadSave;

public class ObjectManager {
		private Playing playing;
		private BufferedImage[][] DropsImgs, containerImgs;
		private ArrayList<Drops> Dropss;
		private ArrayList<Containers> containers;

		public ObjectManager(Playing playing) {
			this.playing = playing;
			loadImgs();
		}

		public void checkObjectTouched(Rectangle2D.Float hitbox) {
			for (Drops p : Dropss)
				if (p.isActive()) {
					if (hitbox.intersects(p.getHitbox())) {
						p.setActive(false);
						applyEffectToPlayer(p);
					}
				}
		}

		public void applyEffectToPlayer(Drops p) {
			if (p.getObjType() == TOOLBOX)
				playing.getPlayer().changeHealth(TOOLBOX_VALUE);
			else
				playing.getPlayer().changegear(GEARS_VALUE);
		}

		public void checkObjectHit(Rectangle2D.Float attackbox) {
			for (Containers gc : containers)
				if(!gc.isAnimation())
				if (gc.isActive()) {
					if (gc.getHitbox().intersects(attackbox)) {
						gc.setAnimation(true);
						int type = 0;
						if (gc.getObjType() == BARREL)
							type = 1;
						Dropss.add(new Drops((int) (gc.getHitbox().x + gc.getHitbox().width / 2), (int) (gc.getHitbox().y - gc.getHitbox().height / 2), type));
						return;
					}
				}
		}

		public void loadObjects(Level newLevel) {
			Dropss = newLevel.getDrops();
			containers = newLevel.getContainers();
		}

		private void loadImgs() {
			BufferedImage DropsSprite = LoadSave.GetSpriteAtlas(LoadSave.DROPS);
			DropsImgs = new BufferedImage[2][7];

			for (int j = 0; j < DropsImgs.length; j++)
				for (int i = 0; i < DropsImgs[j].length; i++)
					DropsImgs[j][i] = DropsSprite.getSubimage(12 * i, 16 * j, 12, 16);

			BufferedImage containerSprite = LoadSave.GetSpriteAtlas(LoadSave.M_BOXES);
			containerImgs = new BufferedImage[2][8];

			for (int j = 0; j < containerImgs.length; j++)
				for (int i = 0; i < containerImgs[j].length; i++)
					containerImgs[j][i] = containerSprite.getSubimage(40 * i, 30 * j, 40, 30);
		}

		public void update() {
			for (Drops p : Dropss)
				if (p.isActive())
					p.update();

			for (Containers gc : containers)
				if (gc.isActive())
					gc.update();
		}

		public void draw(Graphics g, int xLvlOffset) {
			drawDropss(g, xLvlOffset);
			drawContainers(g, xLvlOffset);
		}

		private void drawContainers(Graphics g, int xLvlOffset) {
			for (Containers gc : containers)
				if (gc.isActive()) {
					int type = 0;
					if (gc.getObjType() == BARREL)
						type = 1;
					g.drawImage(containerImgs[type][gc.getAniIndex()], (int) (gc.getHitbox().x - gc.getxDrawOffset() - xLvlOffset), (int) (gc.getHitbox().y - gc.getyDrawOffset()), CONTAINER_WIDTH,
							CONTAINER_HEIGHT, null);
				}
		}

		private void drawDropss(Graphics g, int xLvlOffset) {
			for (Drops p : Dropss)
				if (p.isActive()) {
					int type = 0;
					if (p.getObjType() == GEARS)
						type = 1;
					g.drawImage(DropsImgs[type][p.getAniIndex()], (int) (p.getHitbox().x - p.getxDrawOffset() - xLvlOffset), (int) (p.getHitbox().y - p.getyDrawOffset()), DROPS_WIDTH, DROPS_HEIGHT,
							null);
				}
		}

		public void resetAllObjects() {
			for (Drops p : Dropss)
				p.reset();

			for (Containers gc : containers)
				gc.reset();
		}

}
