package com.xy.model;

import java.awt.Image;

import com.xy.tools.CommTools;

public class Bomb extends RectObject {

	public static final Image[] BOBM_IMG_ARR;
	private int life;
	static{
		BOBM_IMG_ARR = new Image[8];
		for (int i = 0; i < 8; i++) {
			String imgUrl = "image/b"+(i+1)+".gif";
			Image img = CommTools.getImage(Bomb.class, imgUrl);
			BOBM_IMG_ARR[i] = img;
		}
	}
	
	public Bomb(int x,int y) {
		this.loc_x = x;
		this.loc_y = y;
		this.isLive = true;
	}

	@Override
	public void init() {
		this.life = 7;
		
	}
	
	public void lifeDown(){
		if(this.life > 0){
			this.life--;
		}else{
			this.isLive = false;
		}
	}
	

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
