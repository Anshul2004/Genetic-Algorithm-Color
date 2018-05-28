package Main.Display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Core.Population;

public class Display extends JPanel implements Runnable {
  //Thread stuff
	private boolean isRunning;
	private Thread thread;
	private int targetFPS;
	
  //Constructor
	public Display() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
		targetFPS = 60;
	}
	
  //Can be considered as a "game" loop
	public void run() {
		while(isRunning) {
			try {
				thread.sleep(1000 / targetFPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
	
  //Necassary for graphics in swing
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		//Parent1
		g2.setColor(Color.BLACK);
		g2.drawString("Parent 1", 120, 50);
		g2.setColor(new Color(Population.getFittestGene()[0], Population.getFittestGene()[1], Population.getFittestGene()[2]));
		g2.fillRect(100, 100, 100, 100);
		//Parent2
		g2.setColor(Color.BLACK);
		g2.drawString("Parent 2", 320, 50);
		g2.setColor(new Color(Population.getSecondFittestGene()[0], Population.getSecondFittestGene()[1], Population.getSecondFittestGene()[2]));
		g2.fillRect(300, 100, 100, 100);
	}
}
