package com.xy.ui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	
	private MyPanelUI myPanel= null;
	
	public MainFrame(){
		myPanel = new MyPanelUI();
		new Thread(myPanel).start();
		
		this.setBounds(300, 60, 800, 550);
		this.setDefaultCloseOperation(3);

		this.add(myPanel);
		
		myPanel.addTankKeyListener(this);
		
		
		this.setVisible(true);
	}
	

}
