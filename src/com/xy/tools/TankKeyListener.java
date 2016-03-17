package com.xy.tools;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.xy.model.MyTank;

public class TankKeyListener implements KeyListener {

	private MyTank tank;
	public TankKeyListener(MyTank t) {
		this.tank = t;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case 37:
			tank.key_code = 2;
			break;
		case 38:
			tank.key_code = 0;
			break;
		case 39:
			tank.key_code = 3;
			break;
		case 40:
			tank.key_code = 1;
			break;
		}
		if (key == KeyEvent.VK_SPACE) {
			
			tank.openFire();
//			new Thread(new Runnable() {				
//				@Override
//				public void run() {					
//					CommTools.sleepTime(1);
//					tank.openFire();					
//				}
//			}).start();
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()!=32)
			tank.key_code = -1;
	}

}
