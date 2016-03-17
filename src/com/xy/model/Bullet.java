package com.xy.model;

import java.awt.Color;

import com.xy.tools.CommTools;
import com.xy.ui.MyPanelUI;

public class Bullet extends RectObject implements Runnable{
	public static final int R = 5;
	private int speed = 1;
	private int direct;
	private Color color;
	private boolean isUseflag = false;

	public void setLocal(int x, int y) {
		this.loc_x = x;
		this.loc_y = y;
	}

	public Bullet() {
		color = Color.WHITE;
	}
	
	@Override
	public void init() {
		this.setWidth(R);
		this.setHeiget(R);		
	}

	public boolean isOutFrame() {
		if (this.loc_x > MyPanelUI.WIN_WIDTH || this.loc_x < 0
				|| this.loc_y < 0 || this.loc_y > MyPanelUI.WIN_HEIGHT)
			return true;
		return false;
	}

	public void move() {
		switch (this.direct) {
		case 0:
			this.loc_y = this.loc_y - speed;
			break;
		case 1:
			this.loc_y = this.loc_y + speed;
			break;
		case 2:
			this.loc_x = this.loc_x - speed;
			break;
		case 3:
			this.loc_x = this.loc_x + speed;
			break;
		}

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

	public boolean isUseflag() {
		return isUseflag;
	}

	public void setUseflag(boolean isUseflag) {
		this.isUseflag = isUseflag;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				CommTools.sleepTime(8);
				this.move();
				if (this.isOutFrame()) {
					this.setUseflag(false);
					break;
				}
			}
		}
	}

	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
