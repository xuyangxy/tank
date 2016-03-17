package com.xy.tools;

import java.awt.Graphics;
import java.awt.Image;

import com.xy.model.Bomb;
import com.xy.model.Bullet;
import com.xy.model.RectMap;
import com.xy.model.Tank;
import com.xy.ui.MyPanelUI;

public class DrawShapTools {

	private DrawShapTools() {
	}

	public static void drawTank(Tank tank, Graphics g) {

		int tempx = tank.getLoc_x();
		int tempy = tank.getLoc_y();
		int direct = tank.getDirect();
		g.setColor(tank.getColor());

		switch (direct) {
		case 0:
			g.fill3DRect(tempx, tempy, 10, 38, false);
			g.fill3DRect(tempx + 10, tempy + 6, 20, 26, false);
			g.fill3DRect(tempx + 30, tempy, 10, 38, false);
			g.fillOval(tempx + 9, tempy + 8, 20, 20);
			g.drawLine(tempx + 19, tempy + 25, tempx + 19, tempy - 6);
			break;
		case 1:
			g.fill3DRect(tempx, tempy, 10, 38, false);
			g.fill3DRect(tempx + 10, tempy + 6, 20, 26, false);
			g.fill3DRect(tempx + 30, tempy, 10, 38, false);
			g.fillOval(tempx + 9, tempy + 8, 20, 20);
			g.drawLine(tempx + 20, tempy + 25, tempx + 20, tempy + 45);
			break;
		case 2:
			g.fill3DRect(tempx, tempy, 38, 10, false);
			g.fill3DRect(tempx + 6, tempy + 10, 26, 20, false);
			g.fill3DRect(tempx, tempy + 30, 38, 10, false);
			g.fillOval(tempx + 8, tempy + 9, 20, 20);
			g.drawLine(tempx + 20, tempy + 20, tempx - 10, tempy + 20);
			break;
		case 3:
			g.fill3DRect(tempx, tempy, 38, 10, false);
			g.fill3DRect(tempx + 6, tempy + 10, 26, 20, false);
			g.fill3DRect(tempx, tempy + 30, 38, 10, false);
			g.fillOval(tempx + 8, tempy + 9, 20, 20);
			g.drawLine(tempx + 20, tempy + 20, tempx + 45, tempy + 20);
			break;
		}

	}

	public static void drawBullet(Bullet b, Graphics g) {
		g.fillOval(b.getLoc_x(), b.getLoc_y(), Bullet.R, Bullet.R);
	}

	public static void drawBomb(Bomb b, Graphics g, MyPanelUI j) {
		Image img = Bomb.BOBM_IMG_ARR[7 - b.getLife()];
		g.drawImage(img, b.getLoc_x(), b.getLoc_y(), 50, 50, j);
		b.lifeDown();
	}

	public static void drawMap(RectMap rm, Graphics g, MyPanelUI j) {
		g.drawImage(rm.getImage(), rm.getLoc_x(), rm.getLoc_y(), 40, 40, j);
	}

}
