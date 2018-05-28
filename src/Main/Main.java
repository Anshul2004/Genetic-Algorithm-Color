package Main;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import Core.Individual;
import Core.Population;
import Main.Display.Display;

public class Main {
	public static void main(String[] args) {
    //For visual purposes
		Timer timer = new Timer();
    
		JFrame window = new JFrame();
		window.setSize(500, 500);
		window.setLocationRelativeTo(null);
		window.add(new Display());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
    //Preconditions example
		int[] target = {255, 0, 0};
		Population population = new Population(20, target);
		
    //Visual purposes again
		TimerTask task = new TimerTask() {
			public void run() {
				if(population.individuals[population.getFittestIndividual()].getGene() != target){
					population.breedAndAddFittest();
				}
			}
		};
		timer.scheduleAtFixedRate(task, 2000, 100);
	}
}
