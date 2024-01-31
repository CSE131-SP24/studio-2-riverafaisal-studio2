package studio2;

import java.util.Scanner;

public class Ruin {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("How much money are you starting with?");
		double startAmount = in.nextDouble();
		System.out.println("What is the win probability of this game? (Between 0 and 1)");
		double winChance = in.nextDouble();
		System.out.println("How much money do you need to have a successful day?");
		double winLimit = in.nextDouble();
		double currentAmount = startAmount;
		System.out.println("How many days are being simulated?");
		int totalSimulation = in.nextInt();
		int numRuin = 0;
		int numSuccess = 0;
		double expectedRuin = 0;
		
		for(int day = 1; day <= totalSimulation; day ++)
		{
			currentAmount = startAmount;
			int numPlays = 0;
			while (currentAmount > 0 && currentAmount < winLimit)
			{
				double chanceToWin = Math.random();
				if (chanceToWin <= winChance)
				{
					currentAmount ++;
				}
				else
				{
					currentAmount --;
				}
				numPlays ++;
			}
			if (currentAmount == 0)
			{
				System.out.println("Simulation " + day + ": " + numPlays + " LOSE");
				numRuin ++;
			}
			else
			{
				System.out.println("Simulation " + day + ": " + numPlays + " WIN");
				numSuccess ++;
			}
			
		}
		double estimatedRuin = numRuin / (totalSimulation*1.0);
		if (winChance == 0.5)
		{
			expectedRuin = 1 - (startAmount / winLimit);					
		}
		else
		{
			double a = (1 - winChance) / winChance;
			expectedRuin = (Math.pow(a, startAmount) - Math.pow(a, winLimit)) / (1 - Math.pow(a, winLimit));
		}
		System.out.println("Losses: " + numRuin + " Simulations: " + totalSimulation);
		System.out.println("Ruin Rate from Simulation: " + estimatedRuin + " Expected Ruin Rate: " + expectedRuin);
	}

}
