package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static org.example.RockPaperScissorsLizardSpock.*;

public class RockPaperScissorsLizardSpockApp {
    public static Scanner scanner;
    public static Random random;
    public static String[] choices;
    public static int[] scores;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        random = new Random();
        choices = new String[]{"rock", "paper", "scissors", "lizard", "spock"};


        System.out.println("Welcome to Rock, Paper, Scissors, Lizard, Spock! Let's play!");

        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        System.out.print("Enter number of computer players (1-9): ");
        int numComputerPlayers = getValidInput(1, 9);

        System.out.print("Enter number of rounds to play (1-5): ");
        int numRounds = getValidInput(1, 5);

        scores = new int[numComputerPlayers + 1];
        String[] playerNames = new String[numComputerPlayers + 1];
        playerNames[0] = playerName;
        for (int i = 1; i <= numComputerPlayers; i++) {
            playerNames[i] = generateNames(i);
        }

        playGame(playerNames, playerName, numRounds);

        System.out.println("\nGame over! Here are the final scores:");
        for (int i = 0; i <= numComputerPlayers; i++) {
            System.out.println(playerNames[i] + ": " + scores[i]);
        }

        int maxScore = Arrays.stream(scores).max().getAsInt();

        if (scores[0] == maxScore) {
            System.out.println("\nCongratulations, " + playerName + "! You are the winner!");
        } else {
            System.out.println("\nBetter luck next time! You did have the highest victory count.");
        }

        scanner.close();

    }
}
