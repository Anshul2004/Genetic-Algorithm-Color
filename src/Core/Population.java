package Core;

import java.util.Random;

public class Population {
  //Preconditions
	public static Individual[] individuals;
	private static int[] target;
	
  //Constructor
	public Population(int populationSize, int[] target) {
		individuals = initializePopulation(individuals, populationSize);
		this.target = target;
	}
	
  //Implied by the name: to initialize Population
	private Individual[] initializePopulation(Individual[] individuals, int populationSize) {
		Random rg = new Random();
		individuals = new Individual[populationSize];
		for(int i = 0; i < individuals.length; i++) {
			int[] gene = {rg.nextInt(256), rg.nextInt(256), rg.nextInt(256)};
			individuals[i] = new Individual(gene);
		}
		return individuals;
	}
	
  //Returns fittest and second fittest individuals
	public static int getFittestIndividual() {
		int bestFitness = 0;
		int individual = 0;
		for(int i = 0; i < individuals.length; i++) {
			if(individuals[i].calcFitness(target) > bestFitness) {
				bestFitness = individuals[i].calcFitness(target);
				individual = i;
			}
		}
		return individual;
	}
  
	private static int getSecondFittestIndividual() {
		int bestFitness = 0;
		int secondBestFitness = 0;
		int individual = 0;
		int secondIndividual = 0;
		for(int i = 0; i < individuals.length; i++) {
			if(individuals[i].calcFitness(target) > bestFitness) {
				secondBestFitness = bestFitness;
				bestFitness = individuals[i].calcFitness(target);
				secondIndividual = individual;
				individual = i;
			}
		}
		return secondIndividual;
	}
	
  //Returns worst and second worst individual
	private int getWorstIndividual() {
		int worstFitness = individuals[0].calcFitness(target);
		int individual = 0;
		for(int i = 0; i < individuals.length; i++) {
			if(individuals[i].calcFitness(target) < worstFitness) {
				worstFitness = individuals[i].calcFitness(target);
				individual = i;
			}
		}
		return individual;
	}
	
	private int getSecondWorstIndividual() {
		int worstFitness = individuals[0].calcFitness(target);
		int secondWorstFitness = 0;
		int individual = 0;
		int secondIndividual = 0;
		for(int i = 0; i < individuals.length; i++) {
			if(individuals[i].calcFitness(target) < worstFitness) {
				secondWorstFitness = worstFitness;
				worstFitness = individuals[i].calcFitness(target);
				secondIndividual = individual;
				individual = i;
			}
		}
		return secondIndividual;
	}
	
  //Main function necassary to compute
	public void breedAndAddFittest() {
		Random rg = new Random();
		int crossingPoint = rg.nextInt(3);
		int[] child1Gene = new int[3];
		int[] child2Gene = new int[3];
		Individual parent1 = individuals[getFittestIndividual()];
		Individual parent2 = individuals[getSecondFittestIndividual()];
		for(int i = 0; i < child1Gene.length; i++) {
			if(i <= crossingPoint) {
				child1Gene[i] = parent1.getGene()[i];
				child2Gene[i] = parent2.getGene()[i];
			}
			child1Gene[i] = parent2.getGene()[i];
			child2Gene[i] = parent1.getGene()[i];
		}
		Individual child1 = new Individual(child1Gene);
		child1 = mutate(child1, 10);
		Individual child2 = new Individual(child2Gene);
		child1 = mutate(child2, 10);
		individuals[getWorstIndividual()] = child1;
		individuals[getSecondWorstIndividual()] = child2;
		
		System.out.print("Parent 1: ");
		for(int i = 0; i < parent1.getGene().length; i++) {
			if(i == 0) {
				System.out.print(parent1.getGene()[i]);
			}
			if(i != 0) {
				System.out.print(", " + parent1.getGene()[i]);
			}
		}
		
    //Prints info to console
		System.out.print("   Parent 2: ");
		for(int i = 0; i < parent1.getGene().length; i++) {
			if(i == 0) {
				System.out.print(parent2.getGene()[i]);
			}
			if(i != 0) {
				System.out.print(", " + parent2.getGene()[i]);
			}
		}
		System.out.println("");
	}
	
  //Mutates at random
	public Individual mutate(Individual individual, int mutationPossibility) {
		Random rg = new Random();
		int mutationPoint = rg.nextInt(3);
		if(rg.nextInt(100) % mutationPossibility == 0) {
			individual.getGene()[mutationPoint] = rg.nextInt(256);
		}
		return individual;
	}
	
  //Some getters
	public static int[] getFittestGene() {
		return individuals[getFittestIndividual()].getGene();
	}
	
	public static int[] getSecondFittestGene() {
		return individuals[getSecondFittestIndividual()].getGene();
	}
}
