package com.xy.model;

import java.awt.Color;
import java.util.Vector;

import com.xy.tools.CommTools;
import com.xy.ui.MyPanelUI;

public abstract class Tank extends RectObject implements Runnable {
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int TANK_WIDTH = 38;
	public static final int TANK_HEIGHT = 38;
	public int key_code = -1;
	private int life = 1;
	private int speed = 1;
	private int direct = 0;
	private Color color;
	private Vector<Bullet> bulletList;

	public void openFire() {

		if (!this.isLive()) {
			return;
		}

		Bullet b = null;

		for (Bullet bullet : bulletList) {
			if (!bullet.isUseflag()) {
				b = bullet;
				break;
			}
		}

		if (b == null)
			return;

		b.setDirect(this.direct);
		b.setUseflag(true);
		switch (b.getDirect()) {
		case 0:
			b.setLocal(this.loc_x + 17, this.loc_y - 12);
			break;
		case 1:
			b.setLocal(this.loc_x + 18, this.loc_y + 48);
			break;
		case 2:
			b.setLocal(this.loc_x - 16, this.loc_y + 18);
			break;
		case 3:
			b.setLocal(this.loc_x + 50, this.loc_y + 18);
			break;
		}
		new Thread(b).start();

	}

	public void move() {
		switch (key_code) {
		case UP:
			if (this.loc_y >= 0) {
				if (!isCollide(UP)) {
					this.loc_y = this.loc_y - speed;
				}
			}
			this.direct = 0;
			break;
		case DOWN:
			if (this.loc_y <= MyPanelUI.WIN_HEIGHT - TANK_WIDTH) {
				if (!isCollide(DOWN)) {
					this.loc_y = this.loc_y + speed;
				}
			}
			this.direct = 1;
			break;
		case LEFT:
			if (this.loc_x >= 0) {
				if (!isCollide(LEFT)) {
					this.loc_x = this.loc_x - speed;
				}
			}
			this.direct = 2;
			break;
		case RIGHT:
			if (this.loc_x <= MyPanelUI.WIN_WIDTH - TANK_WIDTH) {
				if (!isCollide(RIGHT)) {
					this.loc_x = this.loc_x + speed;
				}
			}
			this.direct = 3;
			break;
		}
	}

	private boolean isCollide(int dir) {
		for (RectMap rm : MyPanelUI.rectList) {
			if (rm.isLive()) {
				int type = rm.getType();
				if (type == 1 || type == 3 || type == 4 || type == 5) {
					if (CommTools.isCollide(this, rm, dir)) {
						return true;
					}
				}
				if (MyPanelUI.enemyTankList != null) {
					for (Tank t : MyPanelUI.enemyTankList) {
						if (t != this) {
							if (CommTools.isCollide(this, t, dir)) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public Tank(int x, int y) {
		this.loc_x = x;
		this.loc_y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Vector<Bullet> getBulletList() {
		return bulletList;
	}

	public void setBulletList(Vector<Bullet> bulletList) {
		this.bulletList = bulletList;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

}
