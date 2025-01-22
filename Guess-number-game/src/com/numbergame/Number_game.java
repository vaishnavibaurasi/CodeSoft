package com.numbergame;


import java.util.Random;
import java.util.Scanner;

public class Number_game {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int roundsPlayed = 0;
        int totalScore = 0;
        boolean playAgain;

        System.out.println("Welcome to the Number Guessing Game!");

        do {
            roundsPlayed++;
            System.out.println("\nStarting Round " + roundsPlayed + "!");

            int targetNumber = random.nextInt(100) + 1; // Generates a number between 1 and 100
            int maxAttempts = 7;
            int attempts = 0;
            int roundScore = 0;

            System.out.println("Guess a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess correctly.");

            while (attempts < maxAttempts) {
                attempts++;
                System.out.print("Enter your guess: ");

                try {
                    int guess = Integer.parseInt(scanner.nextLine());

                    if (guess < 1 || guess > 100) {
                        System.out.println("Please guess a number within the range of 1 to 100.");
                        attempts--;
                        continue;
                    }

                    if (guess < targetNumber) {
                        System.out.println("Too low!");
                    } else if (guess > targetNumber) {
                        System.out.println("Too high!");
                    } else {
                        System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                        roundScore = maxAttempts - attempts + 1;
                        totalScore += roundScore;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    attempts--;
                }
            }

            if (roundScore == 0) {
                System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The number was " + targetNumber + ".");
            }

            System.out.println("Round " + roundsPlayed + " score: " + roundScore);
            System.out.println("Total score: " + totalScore);

            System.out.print("Do you want to play another round? (yes/no): ");
            playAgain = scanner.nextLine().trim().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thanks for playing! Goodbye!");
        scanner.close();
    }
}
