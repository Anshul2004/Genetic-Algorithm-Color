package Core;

public class Individual {
  //Necassary attributes for Individual: gene is an int[]
  //Each int in the gene array can be viewed as a base pairing
	private int[] gene;
	
  //Constructor
	public Individual(int[] gene) {
		this.gene = gene;
	}
  
  //Returns gene
	public int[] getGene() {return gene;}
  
  //Takes the average difference 
	public int calcFitness(int[] target) {
		int fitness = 0;
		for(int i = 0; i < gene.length; i++) {
			fitness = fitness + (255 - Math.abs(gene[i] - target[i]));
		}
		fitness = fitness / 3;
		return fitness;
	}
}
