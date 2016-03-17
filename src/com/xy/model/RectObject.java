package com.xy.model;


public abstract class  RectObject {
	public int width;
	public int heiget;
	public int loc_x;
	public int loc_y;
	public boolean isLive; 
	
	public RectObject() {
		init();
		this.setLive(true);
	}
	
	public abstract void init();
	public abstract void destroy();
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeiget() {
		return heiget;
	}
	public void setHeiget(int heiget) {
		this.heiget = heiget;
	}
	public int getLoc_x() {
		return loc_x;
	}
	public void setLoc_x(int loc_x) {
		this.loc_x = loc_x;
	}
	public int getLoc_y() {
		return loc_y;
	}
	public void setLoc_y(int loc_y) {
		this.loc_y = loc_y;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}
	
	
	
}
