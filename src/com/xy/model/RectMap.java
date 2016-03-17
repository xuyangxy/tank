package com.xy.model;

import java.awt.Image;

import com.xy.tools.CommTools;

public class RectMap extends RectObject {
	
	private static final Image wall;
	private static final Image sea;
	private static final Image steel;
	private static final Image tod;
	private static final Image podium;
	private int type;
	private Image image;
	private boolean isIfUse;
	
	public RectMap(int type) {
		this.type = type;
		initImg();
	}
	
	static{
		wall = CommTools.getImage(RectMap.class, "image/wall.gif");
		sea = CommTools.getImage(RectMap.class, "image/sea.gif");
		steel = CommTools.getImage(RectMap.class, "image/steel.gif");
		tod = CommTools.getImage(RectMap.class, "image/tod.gif");
		podium = CommTools.getImage(RectMap.class, "image/podium.gif");
	}
	@Override
	public void init() {
		this.setHeiget(38);
		this.setWidth(38);	
		this.setIfUse(false);
	}
	
	public void initImg(){
		switch (this.type) {
		case 0:			
			break;
		case 1:
			this.setImage(wall);
			break;
		case 2:
			this.setImage(tod);
			break;
		case 3:
			this.setImage(sea);
			break;
		case 4:
			this.setImage(steel);
			break;
		case 5:
			this.setImage(podium);
			break;
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public boolean isIfUse() {
		return isIfUse;
	}

	public void setIfUse(boolean isIfUse) {
		this.isIfUse = isIfUse;
	}
	
	
}
