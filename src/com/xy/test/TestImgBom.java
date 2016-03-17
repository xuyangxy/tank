package com.xy.test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class TestImgBom extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 773301536412856517L;

	public TestImgBom() {
		this.setBounds(300, 60, 800, 550);
		this.setDefaultCloseOperation(3);
		this.setVisible(true);
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 550, 400);
		
		Image i = Toolkit.getDefaultToolkit().getImage(TestImgBom.class.getResource("explode.gif"));
		g.drawImage(i, 50, 10, 60, 480, this);
		
	}
	
	public static void main(String[] args) {
		new TestImgBom();
	}
}
