package com.xy.model;

import java.awt.Color;
import java.util.Vector;

import com.xy.tools.CommTools;

public class MyTank extends Tank {

	private static final int MAX_BULLET_NUM = 10;
	private boolean moveLock;

	public MyTank(int x, int y) {
		super(x, y);
		this.moveLock = false;
		new Thread(this).start();
	}

	@Override
	public void init() {
		initBulletList();
		this.setWidth(TANK_WIDTH);
		this.setHeiget(TANK_HEIGHT);
		this.setColor(Color.YELLOW);
		this.setLife(2);

	}
	
	public void setLocation(int x, int y){
		this.loc_x = x;
		this.loc_y = y;
	}

	public void initBulletList() {
		Vector<Bullet> bulletList = new Vector<Bullet>();
		for (int i = 0; i < MAX_BULLET_NUM; i++) {
			Bullet b = new Bullet();
			b.setColor(Color.RED);
			bulletList.add(b);
		}
		this.setBulletList(bulletList);
	}

	@Override
	public void destroy() {

	}

	public boolean isMoveLock() {
		return moveLock;
	}

	public void setMoveLock(boolean moveLock) {
		this.moveLock = moveLock;
	}

	@Override
	public void run() {
		synchronized (this) {
			while (true) {
				if (this.key_code > -1) {
					CommTools.sleepTime(8);
					this.move();
				}
			}
		}

	}
}
