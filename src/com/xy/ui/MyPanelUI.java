package com.xy.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.xy.model.Bomb;
import com.xy.model.Bullet;
import com.xy.model.EnemyTank;
import com.xy.model.MyTank;
import com.xy.model.RectMap;
import com.xy.model.RectObject;
import com.xy.tools.CommTools;
import com.xy.tools.DrawShapTools;
import com.xy.tools.TankKeyListener;

@SuppressWarnings("serial")
public class MyPanelUI extends JPanel implements Runnable {

	private MyTank tank;
	public static final int WIN_WIDTH = 560;
	public static final int WIN_HEIGHT = 400;
	private static final int MAX_ENEMY_CURR_TANK = 3;
	private static int max_enemytank = 20;
	private static final int ENEMY_TANK_DISTENCE = 100;
	private Vector<Vector<Bullet>> enemyTankAllBullet;
	// 创建敌方坦克集合
	public static Vector<EnemyTank> enemyTankList;

	// 创建炸弹集合
	private Vector<Bomb> bombList;

	// 0 无
	// 1 砖墙
	// 2 草地
	// 3 水路
	// 4 铁墙
	public static ArrayList<RectMap> rectList;
	private int[][] map = { 
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 4, 4 },
			{ 4, 4, 4, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4 },
			{ 4, 4, 4, 0, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4 },
			{ 4, 0, 0, 0, 4, 4, 0, 0, 0, 4, 4, 4, 4, 4 },
			{ 4, 0, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 4 },
			{ 4, 0, 4, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4 },
			{ 4, 0, 0, 0, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4 },
			{ 1, 4, 4, 0, 0, 4, 4, 4, 0, 0, 0, 0, 0, 0 },
			{ 1, 0, 0, 0, 0, 4, 5, 4, 0, 0, 0, 0, 0, 0 } };

	public MyPanelUI() {
		init();
	}

	public void init() {
		initAttr();
	}

	public void initAttr() {
		enemyTankList = new Vector<EnemyTank>();
		bombList = new Vector<Bomb>();
		tank = new MyTank(150, 350);
		rectList = new ArrayList<RectMap>();
		enemyTankAllBullet = new Vector<Vector<Bullet>>();
		initMap();
		initEnemyTank();

		// 显示第一次爆炸效果
		startBomb(new EnemyTank(0, 0, rectList));
	}

	public void initMap() {
		int x, y, width = 40, height = 40;
		for (int i = 0; i < map.length; i++) {
			y = width * i;
			for (int j = 0; j < map[i].length; j++) {
				x = height * j;
				if (map[i][j] != 0) {
					RectMap rm = new RectMap(map[i][j]);
					rm.setLoc_x(x);
					rm.setLoc_y(y);
					rectList.add(rm);
				}
			}
		}
	}

	private void drawMap(Graphics g) {
		for (RectMap rm : rectList) {
			if (rm.isLive())
				DrawShapTools.drawMap(rm, g, this);
		}
	}

	public void initEnemyTank() {
		for (int i = 0; i < MAX_ENEMY_CURR_TANK; i++) {
			int d = i == 0 ? 30 : i == 1 ? ENEMY_TANK_DISTENCE + 130
					: ENEMY_TANK_DISTENCE + 360;
			EnemyTank et = new EnemyTank(d, 0, rectList);
			enemyTankAllBullet.add(et.getBulletList());
			enemyTankList.add(et);
		}
	}

	public void addEnemyTank() {
		if (max_enemytank < 0)
			return;
		new Thread(new Runnable() {
			@Override
			public void run() {
				CommTools.sleepTime(1000);
				for (EnemyTank t : enemyTankList) {
					if (!t.isLive) {
						t.initStart();
						break;
					}
				}
			}
		}).start();
	}

	public void drawMyTank(Graphics g) {
		if (tank.isLive())
			DrawShapTools.drawTank(tank, g);
	}

	public void drawEnemyTank(Graphics g) {
		for (EnemyTank e : enemyTankList) {
			if (e.isLive()) {
				DrawShapTools.drawTank(e, g);				
			}
			for (Bullet b : e.getBulletList()) {
				g.setColor(b.getColor());
				if (b.isUseflag()) {
					DrawShapTools.drawBullet(b, g);
				}
			}
		}

	}

	private void drawBullet(Graphics g) {
		for (Bullet b : tank.getBulletList()) {
			g.setColor(b.getColor());
			if (b.isUseflag()) {
				DrawShapTools.drawBullet(b, g);
			}
		}
	}

	private void drawBomb(Graphics g) {
		for (int i = 0; i < bombList.size(); i++) {
			Bomb b = bombList.get(i);
			DrawShapTools.drawBomb(b, g, this);
			if (!b.isLive()) {
				bombList.remove(b);
				continue;
			}
		}
	}
	
	private void drawScore(Graphics g) {
		String str = ""+max_enemytank;
		g.setColor(Color.BLACK);
		g.setFont(new Font(str,3,20));
		g.drawString(str, 580, 200);		
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, WIN_WIDTH, WIN_HEIGHT);

		// 画出地图
		drawMap(g);

		// 画出我方坦克
		drawMyTank(g);

		drawEnemyTank(g);

		drawBullet(g);

		drawBomb(g);
		
		drawScore(g);

	}



	public void addTankKeyListener(JFrame jFrame) {
		jFrame.addKeyListener(new TankKeyListener(tank));
	}

	public void battle() {
		// 判断是否击中我方坦克
		bulletAndMyTank();

		// 判断是否击中敌方坦克
		bulletAndEnemyTank();

		// 是否击中障碍
		bulletAndMap();
	}
	

	private void bulletAndEnemyTank() {
		Vector<Bullet> bullet = tank.getBulletList();
		for (Bullet b : bullet) {
			if (b.isUseflag()) {
				for (EnemyTank t : enemyTankList) {
					if (t.isLive()) {
						if (CommTools.isCollide(b, t)) {
							b.setUseflag(false);
							t.setLife(t.getLife() - 1);
							t.setColor(t.lifeChangeColor(t.getLife()));
							if (t.getLife() == 0) {
								// 爆炸
								startBomb(t);
								t.setLive(false);
								max_enemytank--;
								addEnemyTank();
								System.out.println(max_enemytank);
							}
						}
					}
				}
			}
		}
	}

	
	private void bulletAndMyTank(){
		for(Vector<Bullet> vb: enemyTankAllBullet){
			for(Bullet b :vb){
				if (b.isUseflag()) {
					if (CommTools.isCollide(b, tank)) {
						startBomb(tank);
						b.setUseflag(false);
						tank.setLive(false);						
						//设置我的坦克重生
						new Thread(new Runnable() {							
							@Override
							public void run() {
								CommTools.sleepTime(1000);
								tank.setLocation(150, 350);
								tank.setDirect(0);
								tank.setLive(true);										
							}
						}).start();
						
					}
				}
			}
		}
	}
	
	/*
	private void bulletAndMyTank() {
		if (tank.isLive()) {
			for (EnemyTank t : enemyTankList) {
				if (t.isLive()) {
					for (Bullet b : t.getBulletList()) {
						if (b.isUseflag()) {
							if (CommTools.isCollide(b, tank)) {
								startBomb(tank);
								b.setUseflag(false);
								tank.setLive(false);
								
								//设置我的坦克重生
								new Thread(new Runnable() {
									
									@Override
									public void run() {
										CommTools.sleepTime(1000);
										tank.setLocation(150, 350);
										tank.setDirect(0);
										tank.setLive(true);										
									}
								}).start();
								
							}
						}
					}
				}
			}
		}
	}
*/
	private void bulletAndMap() {
		// 判断我方坦克
		for (Bullet b : tank.getBulletList()) {
			if (b.isUseflag()) {
				bulletAndMap(b, rectList);
			}
		}
		// 判断敌方坦克
		for (EnemyTank t : enemyTankList) {
			for (Bullet b : t.getBulletList()) {
				if (b.isUseflag()) {
					bulletAndMap(b, rectList);
				}
			}

		}
	}

	private void bulletAndMap(Bullet b, ArrayList<RectMap> rectList) {
		for (RectMap rm : rectList) {
			int type = rm.getType();
			if (type == 1 || type == 4 || type == 5) {
				if (CommTools.isCollide(b, rm)) {
					if (type == 1 || type == 5) {
						startBomb(rm);
						rm.setType(0);
						rm.setLive(false);
					}
					b.setUseflag(false);
				}
			}
		}
	}

	private void startBomb(RectObject r) {
		Bomb b = new Bomb(r.loc_x, r.loc_y);
		bombList.add(b);
	}

	@Override
	public void run() {
		while (true) {
			CommTools.sleepTime(30);
			battle();
			tank.move();
			this.repaint();
		}
	}
}
