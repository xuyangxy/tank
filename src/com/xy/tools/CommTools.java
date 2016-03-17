package com.xy.tools;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import com.xy.model.RectObject;
import com.xy.model.Tank;

public class CommTools {

	public static int getRandomPointX() {
		Random rd = new Random();
		int r = rd.nextInt(3);
		int x = r == 0 ? 30 : r == 1 ? 230 : 460;
		return x;
	}

	public static int getRandomDirect(int max) {
		Random rd = new Random();
		int r = rd.nextInt(max);
		return r;
	}

	@SuppressWarnings("rawtypes")
	public static Image getImage(Class c, String imgUrl) {
		Image img = Toolkit.getDefaultToolkit().getImage(c.getResource(imgUrl));
		return img;
	}

	public static void sleepTime(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static int getRandomDirect(int min, int max) {
		int x = (int) ((max - min) * Math.random()) + min;
		return x;
	}

	public static boolean isCollide(RectObject rect1, RectObject rect2) {
		int rx1 = rect1.getLoc_x();
		int ry1 = rect1.getLoc_y();
		int rw1 = rect1.getWidth();
		int rh1 = rect1.getHeiget();
		int rx2 = rect2.getLoc_x();
		int ry2 = rect2.getLoc_y();
		int rw2 = rect2.getWidth();
		int rh2 = rect2.getHeiget();
		if (rx1 + rw1 > rx2 + 5 && rx1 - 5 < rx2 + rw2 && ry1 + rh1 > ry2 + 5
				&& ry1 - 5 < ry2 + rh2) {
			return true;
		}
		return false;
	}

	public static boolean isCollide(Tank rect1, RectObject rect2, int direct) {
		int rx1 = rect1.getLoc_x();
		int ry1 = rect1.getLoc_y();
		int rw1 = rect1.getWidth();
		int rh1 = rect1.getHeiget();
		int rx2 = rect2.getLoc_x();
		int ry2 = rect2.getLoc_y();
		int rw2 = rect2.getWidth();
		int rh2 = rect2.getHeiget();
		int d = 1;
		switch (direct) {
		case 0:
			if (rx1 > rx2 - rw1 && rx1 < rx2 + rw2 && ry1 < ry2 + rh2+ d && ry1 > ry2) {
				return true;
			}
			break;
		case 1:
			if (rx1 > rx2 - rw1 && rx1 < rx2 + rw2 && ry1 > ry2 - rh1 - d && ry1 < ry2) {
				return true;
			}
			break;
		case 2:
			if (rx1 < rx2 + rw2 + d && rx1 > rx2 && ry1 > ry2 - rh1 && ry1 < ry2 + rh2) {
				return true;
			}
			break;
		case 3:
			if (rx1 > rx2 - rw1 - d && rx1 < rx2 && ry1 > ry2 - rh1 && ry1 < ry2 + rh2) {
				return true;
			}
			break;
		}

		return false;
	}
}
