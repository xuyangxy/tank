package com.xy.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;

import com.xy.tools.CommTools;

public class EnemyTank extends Tank{
	
	public EnemyTank(int x, int y ,ArrayList<RectMap> rm) {
		super(x, y);
		this.setDirect(DOWN);
		this.setLife(CommTools.getRandomDirect(3)+1);
		this.setColor(lifeChangeColor(this.getLife()));
		new Thread(this).start();
	}
	
	
	public void initStart(){
		this.setLife(CommTools.getRandomDirect(3)+1);
		this.setColor(lifeChangeColor(this.getLife()));
		this.setLoc_y(0);
		this.setLoc_x(CommTools.getRandomPointX());
		this.setLive(true);
	}

	@Override
	public void init() {
		this.setWidth(TANK_WIDTH);
		this.setHeiget(TANK_HEIGHT);
		initBulletList();
	}

	public void initBulletList() {
		Vector<Bullet> bulletList = new Vector<Bullet>();
		for (int i = 0; i < 3; i++) {
			Bullet b = new Bullet();
			bulletList.add(b);
		}
		this.setBulletList(bulletList);
	}
	
	public Color lifeChangeColor(int life){
		if(life == 3){
			return Color.RED;
		}else if(life == 2){
			return Color.GREEN;
		}else{
			return Color.WHITE;
		}
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				int randomDirect = CommTools.getRandomDirect(4);
				int count = 40;
				int time = CommTools.getRandomDirect(30, 40);
				int isOpenFire = CommTools.getRandomDirect(100);
				this.key_code = randomDirect;
				int temer = 10;
				for (int i = 0; i < count; i++) {
					CommTools.sleepTime(temer);
					this.move();
				}
				if (isOpenFire % 2 == 0)
					this.openFire();

				CommTools.sleepTime(time);
			}
		}
	}

	@Override
	public void destroy() {		
		this.setLive(false);
	}
}
